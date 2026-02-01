package factories;

import entities.Enemy;
import utils.Utils;

/**
 * Factory for creating enemy instances
 */
public class EnemyFactory {

    public static Enemy createGoblin() {
        return new Enemy("Goblin", 1, 30, 8, 2, 50, 10);
    }

    public static Enemy createOrc() {
        return new Enemy("Orc", 3, 60, 15, 5, 120, 25);
    }

    public static Enemy createTroll() {
        return new Enemy("Troll", 5, 100, 20, 8, 200, 40);
    }

    public static Enemy createDarkKnight() {
        return new Enemy("Dark Knight", 7, 150, 28, 12, 350, 70);
    }

    public static Enemy createDragon() {
        return new Enemy("Dragon", 10, 250, 40, 15, 500, 100);
    }

    public static Enemy createSlime() {
        return new Enemy("Green Slime", 1, 20, 5, 1, 30, 8);
    }

    public static Enemy createRat() {
        return new Enemy("Giant Rat", 1, 25, 6, 2, 40, 10);
    }

    public static Enemy createSkeleton() {
        return new Enemy("Skeleton", 2, 45, 12, 4, 80, 20);
    }

    public static Enemy createWraith() {
        return new Enemy("Wraith", 5, 80, 22, 6, 250, 50);
    }

    public static Enemy createGolem() {
        return new Enemy("Stone Golem", 8, 180, 30, 20, 450, 80);
    }

    // ===== BOSSES =====

    public static Enemy createSkeletonKing() {
        return new Enemy("👑 Skeleton King", 10, 300, 45, 15, 1000, 250);
    }

    public static Enemy createAncientGolem() {
        return new Enemy("👑 Ancient Golem", 20, 600, 70, 40, 2500, 600);
    }

    public static Enemy createDemonLord() {
        return new Enemy("🔥 Demon Lord 🔥", 30, 1500, 120, 80, 10000, 2000);
    }

    /**
     * Create a random enemy appropriate for the player's level and floor
     */
    public static Enemy createRandomEnemy(int playerLevel, int floor) {
        // Special Boss Spawn every 10 floors
        if (floor > 0 && floor % 10 == 0) {
            if (floor == 10)
                return createSkeletonKing();
            if (floor == 20)
                return createAncientGolem();
            return createDemonLord();
        }

        int roll = Utils.getRandom(1, 100);

        if (playerLevel <= 2) {
            // Level 1-2: Slimes, Rats, Goblins
            if (roll <= 30)
                return createSlime();
            if (roll <= 60)
                return createRat();
            return createGoblin();
        } else if (playerLevel <= 4) {
            // Level 3-4: Goblins, Orcs, Skeletons
            if (roll <= 40)
                return createGoblin();
            if (roll <= 70)
                return createOrc();
            return createSkeleton();
        } else if (playerLevel <= 6) {
            // Level 5-6: Orcs, Trolls, Wraiths
            if (roll <= 40)
                return createOrc();
            if (roll <= 70)
                return createTroll();
            return createWraith();
        } else if (playerLevel <= 8) {
            // Level 7-8: Trolls, Dark Knights, Golems
            if (roll <= 30)
                return createTroll();
            if (roll <= 70)
                return createDarkKnight();
            return createGolem();
        } else {
            // Level 9+: Dark Knights, Dragons, Golems
            if (roll <= 40)
                return createDarkKnight();
            if (roll <= 70)
                return createGolem();
            return createDragon();
        }
    }
}
