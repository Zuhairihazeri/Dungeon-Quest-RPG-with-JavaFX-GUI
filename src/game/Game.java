package game;

import entities.Player;
import entities.Enemy;
import combat.CombatSystem;
import factories.EnemyFactory;
import factories.ItemFactory;
import items.Item;
import items.Equipment;
import ui.GameUI;
import utils.Utils;

/**
 * Main game controller managing game flow and state
 */
public class Game {
    private Player player;
    private boolean gameRunning;
    private int floorsCleared;

    public Game() {
        this.gameRunning = false;
        this.floorsCleared = 0;
    }

    /**
     * Start the game
     */
    public void start() {
        GameUI.displayGameLogo();

        String[] mainMenuOptions = { "New Game", "How to Play", "Exit" };
        int choice = GameUI.displayMenu("Main Menu", mainMenuOptions);

        switch (choice) {
            case 1:
                startNewGame();
                break;
            case 2:
                displayHowToPlay();
                start(); // Return to main menu
                break;
            case 3:
                System.out.println("\nThanks for playing!");
                System.exit(0);
                break;
        }
    }

    /**
     * Display how to play instructions
     */
    private void displayHowToPlay() {
        GameUI.displayTitle("HOW TO PLAY");
        System.out.println("\n🎯 OBJECTIVE:");
        System.out.println("  Explore the dungeon, defeat monsters, and become stronger!");

        System.out.println("\n⚔️ COMBAT:");
        System.out.println("  - Attack enemies to deal damage");
        System.out.println("  - Use potions to restore HP during battle");
        System.out.println("  - Flee if the fight is too difficult (50% success rate)");

        System.out.println("\n📊 PROGRESSION:");
        System.out.println("  - Gain XP by defeating enemies");
        System.out.println("  - Level up to increase your stats");
        System.out.println("  - Find and equip better weapons and armor");

        System.out.println("\n💰 LOOT:");
        System.out.println("  - Enemies drop gold and items");
        System.out.println("  - Visit the shop to buy equipment and potions");

        Utils.pressEnterToContinue();
    }

    /**
     * Start a new game
     */
    private void startNewGame() {
        GameUI.displayTitle("NEW GAME");

        String playerName = GameUI.getStringInput("\nEnter your hero's name: ");
        if (playerName.isEmpty()) {
            playerName = "Hero";
        }

        player = new Player(playerName);

        // Give player starting equipment
        player.getInventory().addItem(ItemFactory.createWoodenSword());
        player.getInventory().addItem(ItemFactory.createLeatherArmor());
        player.getInventory().addItem(ItemFactory.createSmallPotion());
        player.getInventory().addItem(ItemFactory.createSmallPotion());

        System.out.println("\n" + GameUI.GREEN + "Welcome, " + playerName + "!" + GameUI.RESET);
        System.out.println("You have received starting equipment!");

        Utils.pressEnterToContinue();

        gameRunning = true;
        gameLoop();
    }

    /**
     * Main game loop
     */
    private void gameLoop() {
        while (gameRunning && player.isAlive()) {
            displayMainGameMenu();
        }

        if (!player.isAlive()) {
            System.out.println("\n" + GameUI.RED + "GAME OVER" + GameUI.RESET);
            Utils.pressEnterToContinue();
        }
    }

    /**
     * Display main game menu
     */
    private void displayMainGameMenu() {
        GameUI.displayTitle("ADVENTURER'S CAMP");

        System.out.println("\nFloors Cleared: " + floorsCleared);
        System.out.println("Level: " + player.getLevel() + " | HP: " + player.getCurrentHp() +
                "/" + player.getMaxHp() + " | Gold: " + player.getGold());

        String[] options = {
                "Explore Dungeon (Fight)",
                "Character Status",
                "Inventory & Equipment",
                "Shop",
                "Rest (Restore HP)",
                "Quit Game"
        };

        int choice = GameUI.displayMenu("What would you like to do?", options);

        switch (choice) {
            case 1:
                exploreDungeon();
                break;
            case 2:
                displayCharacterStatus();
                break;
            case 3:
                manageInventory();
                break;
            case 4:
                visitShop();
                break;
            case 5:
                rest();
                break;
            case 6:
                gameRunning = false;
                System.out.println("\nThanks for playing!");
                break;
        }
    }

    /**
     * Explore dungeon and encounter enemies
     */
    private void exploreDungeon() {
        GameUI.displayTitle("DUNGEON EXPLORATION");

        System.out.println("\nYou venture deeper into the dungeon...");
        Utils.pressEnterToContinue();

        // Create enemy based on player level (including bosses every 10 floors)
        Enemy enemy = EnemyFactory.createRandomEnemy(player.getLevel(), floorsCleared + 1);

        // Start combat
        CombatSystem combat = new CombatSystem(player, enemy);
        boolean victory = combat.startCombat();

        if (victory) {
            floorsCleared++;
        }
    }

    /**
     * Display character status
     */
    private void displayCharacterStatus() {
        player.displayStats();
        Utils.pressEnterToContinue();
    }

    /**
     * Manage inventory and equipment
     */
    private void manageInventory() {
        while (true) {
            GameUI.displayTitle("INVENTORY & EQUIPMENT");

            player.getInventory().displayInventory();

            System.out.println("\n--- Current Equipment ---");
            System.out.println(
                    "Weapon: " + (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None"));
            System.out.println(
                    "Armor: " + (player.getEquippedArmor() != null ? player.getEquippedArmor().getName() : "None"));

            String[] options = { "Use/Equip Item", "Unequip Weapon", "Unequip Armor", "Back" };
            int choice = GameUI.displayMenu("Inventory Options", options);

            switch (choice) {
                case 1:
                    useOrEquipItem();
                    break;
                case 2:
                    player.unequipWeapon();
                    Utils.pressEnterToContinue();
                    break;
                case 3:
                    player.unequipArmor();
                    Utils.pressEnterToContinue();
                    break;
                case 4:
                    return;
            }
        }
    }

    /**
     * Use or equip an item from inventory
     */
    private void useOrEquipItem() {
        if (player.getInventory().isEmpty()) {
            System.out.println("\n" + GameUI.RED + "No items in inventory!" + GameUI.RESET);
            Utils.pressEnterToContinue();
            return;
        }

        System.out.println("\n0. Cancel");
        int choice = GameUI.getIntInput("Select item (or 0 to cancel): ",
                0, player.getInventory().getSize());

        if (choice == 0) {
            return;
        }

        Item item = player.getInventory().getItem(choice - 1);

        if (item instanceof Equipment) {
            player.getInventory().removeItem(item);
            player.equip((Equipment) item);
        } else {
            boolean consumed = item.use(player);
            if (consumed) {
                player.getInventory().removeItem(item);
            }
        }

        Utils.pressEnterToContinue();
    }

    /**
     * Visit the shop
     */
    private void visitShop() {
        while (true) {
            GameUI.displayTitle("MERCHANT'S SHOP");

            System.out.println("\nYour Gold: " + player.getGold());
            System.out.println("\n--- Items for Sale ---");
            System.out.println("1. Small Health Potion - 15 Gold");
            System.out.println("2. Medium Health Potion - 30 Gold");
            System.out.println("3. Iron Sword - 50 Gold");
            System.out.println("4. Chainmail - 60 Gold");
            System.out.println("5. Steel Sword - 120 Gold");
            System.out.println("6. Plate Armor - 150 Gold");
            System.out.println("7. Leave Shop");

            int choice = GameUI.getIntInput("What would you like to buy? ", 1, 7);

            if (choice == 7) {
                break;
            }

            buyItem(choice);
        }
    }

    /**
     * Buy an item from the shop
     */
    private void buyItem(int itemChoice) {
        Item itemToBuy = null;
        int price = 0;

        switch (itemChoice) {
            case 1:
                itemToBuy = ItemFactory.createSmallPotion();
                price = 15;
                break;
            case 2:
                itemToBuy = ItemFactory.createMediumPotion();
                price = 30;
                break;
            case 3:
                itemToBuy = ItemFactory.createIronSword();
                price = 50;
                break;
            case 4:
                itemToBuy = ItemFactory.createChainmail();
                price = 60;
                break;
            case 5:
                itemToBuy = ItemFactory.createSteelSword();
                price = 120;
                break;
            case 6:
                itemToBuy = ItemFactory.createPlateArmor();
                price = 150;
                break;
        }

        if (itemToBuy != null) {
            if (player.getGold() >= price) {
                if (player.getInventory().addItem(itemToBuy)) {
                    player.removeGold(price);
                    System.out.println("\n" + GameUI.GREEN + "Purchased " + itemToBuy.getName() + "!" + GameUI.RESET);
                }
            } else {
                System.out.println("\n" + GameUI.RED + "Not enough gold!" + GameUI.RESET);
            }
        }

        Utils.pressEnterToContinue();
    }

    /**
     * Rest and restore HP
     */
    private void rest() {
        System.out.println("\n" + GameUI.GREEN + "You rest and recover your strength..." + GameUI.RESET);
        player.setCurrentHp(player.getMaxHp());
        System.out.println("HP fully restored!");
        Utils.pressEnterToContinue();
    }
}
