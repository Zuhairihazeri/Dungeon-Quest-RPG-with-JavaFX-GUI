package items;

import entities.Player;

/**
 * Consumable potion that heals the player
 */
public class Potion implements Item {
    private String name;
    private String description;
    private int healAmount;

    public Potion(String name, int healAmount) {
        this.name = name;
        this.healAmount = healAmount;
        this.description = "Restores " + healAmount + " HP";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean use(Player player) {
        int hpBefore = player.getCurrentHp();
        player.heal(healAmount);
        int hpRestored = player.getCurrentHp() - hpBefore;

        System.out.println("\n" + player.getName() + " used " + name + "!");
        System.out.println("Restored " + hpRestored + " HP!");

        return true; // Potion is consumed
    }

    public int getHealAmount() {
        return healAmount;
    }
}
