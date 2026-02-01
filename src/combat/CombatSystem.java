package combat;

import entities.Player;
import entities.Enemy;
import items.Item;
import items.Equipment;
import items.Potion;
import ui.GameUI;
import utils.Utils;

/**
 * Manages turn-based combat between player and enemies
 */
public class CombatSystem {
    private Player player;
    private Enemy enemy;
    private boolean combatActive;

    public CombatSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.combatActive = true;
    }

    /**
     * Start and run the combat encounter
     * Returns true if player won, false if player lost or fled
     */
    public boolean startCombat() {
        System.out
                .println("\n" + GameUI.RED + GameUI.BOLD + "⚔ A wild " + enemy.getName() + " appears!" + GameUI.RESET);
        Utils.pressEnterToContinue();

        while (combatActive && player.isAlive() && enemy.isAlive()) {
            // Display combat status
            GameUI.displayCombatStatus(player, enemy);

            // Player turn
            playerTurn();

            if (!enemy.isAlive()) {
                handleVictory();
                return true;
            }

            if (!combatActive) {
                // Player fled
                return false;
            }

            // Enemy turn
            if (combatActive) {
                enemyTurn();
            }

            if (!player.isAlive()) {
                handleDefeat();
                return false;
            }
        }

        return false;
    }

    /**
     * Handle player's turn
     */
    private void playerTurn() {
        String[] options = { "Attack", "Use Item", "Flee" };
        int choice = GameUI.displayMenu("Your Turn:", options);

        switch (choice) {
            case 1: // Attack
                int damage = player.attack(enemy);
                GameUI.displayDamage(player.getName(), enemy.getName(), damage);
                Utils.pressEnterToContinue();
                break;

            case 2: // Use Item
                useItemInCombat();
                break;

            case 3: // Flee
                if (attemptFlee()) {
                    System.out.println("\n" + GameUI.YELLOW + "You fled from combat!" + GameUI.RESET);
                    combatActive = false;
                } else {
                    System.out.println("\n" + GameUI.RED + "Failed to flee!" + GameUI.RESET);
                }
                Utils.pressEnterToContinue();
                break;
        }
    }

    /**
     * Use an item during combat
     */
    private void useItemInCombat() {
        if (player.getInventory().isEmpty()) {
            System.out.println("\n" + GameUI.RED + "No items in inventory!" + GameUI.RESET);
            Utils.pressEnterToContinue();
            return;
        }

        // Show only usable items (potions) in combat
        player.getInventory().displayInventory();

        System.out.println("\n0. Cancel");
        int choice = GameUI.getIntInput("Select item to use (or 0 to cancel): ",
                0, player.getInventory().getSize());

        if (choice == 0) {
            return;
        }

        Item item = player.getInventory().getItem(choice - 1);

        if (item instanceof Potion) {
            boolean consumed = item.use(player);
            if (consumed) {
                player.getInventory().removeItem(item);
            }
            Utils.pressEnterToContinue();
        } else {
            System.out.println("\n" + GameUI.RED + "Cannot use this item in combat!" + GameUI.RESET);
            Utils.pressEnterToContinue();
            useItemInCombat(); // Try again
        }
    }

    /**
     * Handle enemy's turn
     */
    private void enemyTurn() {
        System.out.println("\n" + GameUI.RED + enemy.getName() + "'s turn!" + GameUI.RESET);

        // Simple AI: always attack
        int damage = enemy.attack(player);
        GameUI.displayDamage(enemy.getName(), player.getName(), damage);
        Utils.pressEnterToContinue();
    }

    /**
     * Attempt to flee from combat
     */
    private boolean attemptFlee() {
        // 50% chance to flee successfully
        return Utils.getRandom(1, 100) <= 50;
    }

    /**
     * Handle victory
     */
    private void handleVictory() {
        GameUI.displayVictory();

        System.out.println(enemy.getName() + " has been defeated!");
        player.gainExperience(enemy.getExperienceReward());
        player.addGold(enemy.getGoldReward());
        System.out.println("+" + enemy.getGoldReward() + " Gold!");

        // Random item drop (30% chance)
        if (Utils.getRandom(1, 100) <= 30) {
            Item droppedItem = getRandomLoot();
            if (droppedItem != null) {
                player.getInventory().addItem(droppedItem);
                System.out.println("\n" + GameUI.GREEN + "Found: " + droppedItem.getName() + "!" + GameUI.RESET);
            }
        }

        Utils.pressEnterToContinue();
        combatActive = false;
    }

    /**
     * Handle defeat
     */
    private void handleDefeat() {
        GameUI.displayDefeat();
        System.out.println("You have been defeated...");
        System.out.println("Game Over!");
        Utils.pressEnterToContinue();
        combatActive = false;
    }

    /**
     * Get random loot based on enemy level
     */
    private Item getRandomLoot() {
        int roll = Utils.getRandom(1, 100);

        if (roll <= 60) {
            // Potion drop
            return factories.ItemFactory.createSmallPotion();
        } else if (roll <= 80) {
            return factories.ItemFactory.createMediumPotion();
        } else {
            return factories.ItemFactory.createLargePotion();
        }
    }
}
