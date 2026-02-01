package entities;

import items.Equipment;
import items.Inventory;

/**
 * Player character with inventory, equipment, and progression system
 */
public class Player extends Character {
    private int experience;
    private int experienceToNextLevel;
    private int gold;
    private Inventory inventory;
    private Equipment equippedWeapon;
    private Equipment equippedArmor;

    public Player(String name) {
        super(name, 1, 100, 10, 5);
        this.experience = 0;
        this.experienceToNextLevel = 100;
        this.gold = 50;
        this.inventory = new Inventory(20);
        this.equippedWeapon = null;
        this.equippedArmor = null;
    }

    @Override
    public int getAttack() {
        int attack = baseAttack;
        if (equippedWeapon != null) {
            attack += equippedWeapon.getAttackBonus();
        }
        return attack;
    }

    @Override
    public int getDefense() {
        int defense = baseDefense;
        if (equippedArmor != null) {
            defense += equippedArmor.getDefenseBonus();
        }
        return defense;
    }

    /**
     * Gain experience and check for level up
     */
    public void gainExperience(int exp) {
        experience += exp;
        System.out.println("\n+" + exp + " EXP!");

        while (experience >= experienceToNextLevel) {
            levelUp();
        }
    }

    /**
     * Level up and increase stats
     */
    private void levelUp() {
        level++;
        experience -= experienceToNextLevel;
        experienceToNextLevel = (int) (experienceToNextLevel * 1.5);

        // Increase stats
        int hpIncrease = 20;
        int attackIncrease = 3;
        int defenseIncrease = 2;

        maxHp += hpIncrease;
        currentHp = maxHp; // Full heal on level up
        baseAttack += attackIncrease;
        baseDefense += defenseIncrease;

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          ⭐ LEVEL UP! ⭐              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Level: " + (level - 1) + " → " + level);
        System.out.println("Max HP: " + (maxHp - hpIncrease) + " → " + maxHp);
        System.out.println("Attack: " + (baseAttack - attackIncrease) + " → " + baseAttack);
        System.out.println("Defense: " + (baseDefense - defenseIncrease) + " → " + baseDefense);
    }

    /**
     * Add gold to player
     */
    public void addGold(int amount) {
        gold += amount;
    }

    /**
     * Remove gold from player
     */
    public boolean removeGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }

    /**
     * Equip a weapon or armor
     */
    public void equip(Equipment equipment) {
        if (equipment.getType() == Equipment.EquipmentType.WEAPON) {
            if (equippedWeapon != null) {
                inventory.addItem(equippedWeapon);
            }
            equippedWeapon = equipment;
            System.out.println("Equipped " + equipment.getName() + "!");
        } else if (equipment.getType() == Equipment.EquipmentType.ARMOR) {
            if (equippedArmor != null) {
                inventory.addItem(equippedArmor);
            }
            equippedArmor = equipment;
            System.out.println("Equipped " + equipment.getName() + "!");
        }
    }

    /**
     * Unequip weapon
     */
    public void unequipWeapon() {
        if (equippedWeapon != null) {
            inventory.addItem(equippedWeapon);
            System.out.println("Unequipped " + equippedWeapon.getName() + "!");
            equippedWeapon = null;
        }
    }

    /**
     * Unequip armor
     */
    public void unequipArmor() {
        if (equippedArmor != null) {
            inventory.addItem(equippedArmor);
            System.out.println("Unequipped " + equippedArmor.getName() + "!");
            equippedArmor = null;
        }
    }

    // Getters
    public int getExperience() {
        return experience;
    }

    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public int getGold() {
        return gold;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquippedWeapon() {
        return equippedWeapon;
    }

    public Equipment getEquippedArmor() {
        return equippedArmor;
    }

    /**
     * Display full player stats
     */
    public void displayStats() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           CHARACTER STATUS            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("HP: " + currentHp + "/" + maxHp);
        System.out.println("Attack: " + getAttack() + " (Base: " + baseAttack + ")");
        System.out.println("Defense: " + getDefense() + " (Base: " + baseDefense + ")");
        System.out.println("Experience: " + experience + "/" + experienceToNextLevel);
        System.out.println("Gold: " + gold);
        System.out.println("\n--- Equipment ---");
        System.out.println("Weapon: " + (equippedWeapon != null ? equippedWeapon.getName() : "None"));
        System.out.println("Armor: " + (equippedArmor != null ? equippedArmor.getName() : "None"));
    }
}
