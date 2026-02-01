package factories;

import items.Equipment;
import items.Potion;
import items.Item;

/**
 * Factory for creating items and equipment
 */
public class ItemFactory {

    // ===== POTIONS =====

    public static Potion createSmallPotion() {
        return new Potion("Small Health Potion", 30);
    }

    public static Potion createMediumPotion() {
        return new Potion("Medium Health Potion", 60);
    }

    public static Potion createLargePotion() {
        return new Potion("Large Health Potion", 100);
    }

    // ===== WEAPONS =====

    public static Equipment createWoodenSword() {
        return new Equipment(
                "Wooden Sword",
                "A basic wooden training sword",
                Equipment.EquipmentType.WEAPON,
                3, // attack bonus
                0 // defense bonus
        );
    }

    public static Equipment createDagger() {
        return new Equipment("Rusty Dagger", "A short, rusted blade", Equipment.EquipmentType.WEAPON, 5, 0);
    }

    public static Equipment createIronSword() {
        return new Equipment(
                "Iron Sword",
                "A reliable iron blade",
                Equipment.EquipmentType.WEAPON,
                8,
                0);
    }

    public static Equipment createWarhammer() {
        return new Equipment("Heavy Warhammer", "A massive hammer that crushes armor", Equipment.EquipmentType.WEAPON,
                12, 0);
    }

    public static Equipment createRapier() {
        return new Equipment("Fine Rapier", "A light and elegant fencing sword", Equipment.EquipmentType.WEAPON, 10, 2);
    }

    public static Equipment createSteelSword() {
        return new Equipment(
                "Steel Sword",
                "A well-crafted steel sword",
                Equipment.EquipmentType.WEAPON,
                15,
                0);
    }

    public static Equipment createKatana() {
        return new Equipment("Swift Katana", "A razor-sharp blade from the East", Equipment.EquipmentType.WEAPON, 22,
                0);
    }

    public static Equipment createLegendaryBlade() {
        return new Equipment(
                "Legendary Blade",
                "A mythical weapon of immense power",
                Equipment.EquipmentType.WEAPON,
                35,
                8);
    }

    // ===== ARMOR =====

    public static Equipment createClothRobe() {
        return new Equipment("Cloth Robe", "Simple fabric offering minimal protection", Equipment.EquipmentType.ARMOR,
                0, 2);
    }

    public static Equipment createLeatherArmor() {
        return new Equipment(
                "Leather Armor",
                "Basic leather protection",
                Equipment.EquipmentType.ARMOR,
                0,
                5);
    }

    public static Equipment createChainmail() {
        return new Equipment(
                "Chainmail",
                "Interlocking metal rings",
                Equipment.EquipmentType.ARMOR,
                0,
                10);
    }

    public static Equipment createMithrilVest() {
        return new Equipment("Mithril Vest", "Light as a feather, hard as dragon scale", Equipment.EquipmentType.ARMOR,
                0, 15);
    }

    public static Equipment createPlateArmor() {
        return new Equipment(
                "Plate Armor",
                "Heavy metal plates",
                Equipment.EquipmentType.ARMOR,
                0,
                20);
    }

    public static Equipment createDragonArmor() {
        return new Equipment(
                "Dragon Scale Armor",
                "Armor forged from dragon scales",
                Equipment.EquipmentType.ARMOR,
                5,
                28);
    }

    public static Equipment createHolyAegis() {
        return new Equipment("Holy Aegis", "Ancient plate armor blessed by the gods", Equipment.EquipmentType.ARMOR, 10,
                40);
    }
}
