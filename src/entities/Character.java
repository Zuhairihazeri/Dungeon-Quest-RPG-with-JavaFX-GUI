package entities;

/**
 * Abstract base class for all characters in the game (Player and Enemies)
 */
public abstract class Character {
    protected String name;
    protected int level;
    protected int maxHp;
    protected int currentHp;
    protected int baseAttack;
    protected int baseDefense;
    
    public Character(String name, int level, int maxHp, int baseAttack, int baseDefense) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }
    
    /**
     * Calculate total attack (base + bonuses)
     */
    public int getAttack() {
        return baseAttack;
    }
    
    /**
     * Calculate total defense (base + bonuses)
     */
    public int getDefense() {
        return baseDefense;
    }
    
    /**
     * Attack another character
     */
    public int attack(Character target) {
        int damage = Math.max(1, this.getAttack() - target.getDefense());
        target.takeDamage(damage);
        return damage;
    }
    
    /**
     * Take damage and reduce HP
     */
    public void takeDamage(int damage) {
        currentHp -= damage;
        if (currentHp < 0) {
            currentHp = 0;
        }
    }
    
    /**
     * Heal by specified amount
     */
    public void heal(int amount) {
        currentHp += amount;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
    }
    
    /**
     * Check if character is alive
     */
    public boolean isAlive() {
        return currentHp > 0;
    }
    
    /**
     * Get HP percentage for display
     */
    public double getHpPercentage() {
        return (double) currentHp / maxHp;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getMaxHp() {
        return maxHp;
    }
    
    public int getCurrentHp() {
        return currentHp;
    }
    
    public void setCurrentHp(int hp) {
        this.currentHp = Math.min(hp, maxHp);
    }
    
    public int getBaseAttack() {
        return baseAttack;
    }
    
    public int getBaseDefense() {
        return baseDefense;
    }
    
    /**
     * Display character stats
     */
    public String getStatsString() {
        return String.format("%s (Lv.%d) | HP: %d/%d | ATK: %d | DEF: %d",
            name, level, currentHp, maxHp, getAttack(), getDefense());
    }
}
