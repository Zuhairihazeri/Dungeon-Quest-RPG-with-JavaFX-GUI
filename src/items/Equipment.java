package items;

import entities.Player;

/**
 * Equipment items that can be equipped by the player
 */
public class Equipment implements Item {
    public enum EquipmentType {
        WEAPON,
        ARMOR
    }

    private String name;
    private String description;
    private EquipmentType type;
    private int attackBonus;
    private int defenseBonus;

    public Equipment(String name, String description, EquipmentType type, int attackBonus, int defenseBonus) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
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
        // Equipping is handled through Player.equip(), not through use()
        System.out.println("Use the 'Equip' option to equip this item.");
        return false;
    }

    public EquipmentType getType() {
        return type;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public String getDisplayString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(type).append(")");

        if (attackBonus > 0) {
            sb.append(" | ATK+").append(attackBonus);
        }
        if (defenseBonus > 0) {
            sb.append(" | DEF+").append(defenseBonus);
        }

        return sb.toString();
    }
}
