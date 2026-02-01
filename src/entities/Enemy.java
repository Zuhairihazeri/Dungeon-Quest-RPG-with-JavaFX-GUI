package entities;

/**
 * Enemy character that players fight against
 */
public class Enemy extends Character {
    private int experienceReward;
    private int goldReward;

    public Enemy(String name, int level, int maxHp, int baseAttack, int baseDefense, int experienceReward,
            int goldReward) {
        super(name, level, maxHp, baseAttack, baseDefense);
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public int getGoldReward() {
        return goldReward;
    }
}
