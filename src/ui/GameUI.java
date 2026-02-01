package ui;

import entities.Player;
import entities.Character;
import utils.Utils;
import java.util.Scanner;

/**
 * Handles all UI display and user input
 */
public class GameUI {
    private static final Scanner scanner = Utils.getScanner();

    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    /**
     * Display a title banner
     */
    public static void displayTitle(String title) {
        int width = 50;
        int padding = (width - title.length() - 2) / 2;

        System.out.println("\n" + "═".repeat(width));
        System.out.print("║");
        System.out.print(" ".repeat(padding));
        System.out.print(BOLD + title + RESET);
        System.out.print(" ".repeat(width - padding - title.length() - 2));
        System.out.println("║");
        System.out.println("═".repeat(width));
    }

    /**
     * Display a menu and get user choice
     */
    public static int displayMenu(String title, String[] options) {
        System.out.println("\n" + BOLD + CYAN + title + RESET);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        return getIntInput("Choose an option: ", 1, options.length);
    }

    /**
     * Get integer input with validation
     */
    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println(RED + "Please enter a number between " + min + " and " + max + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    /**
     * Get string input
     */
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Display a health bar
     */
    public static String getHealthBar(Character character, int barWidth) {
        double hpPercent = character.getHpPercentage();
        int filledBars = (int) (barWidth * hpPercent);
        int emptyBars = barWidth - filledBars;

        String color;
        if (hpPercent > 0.5) {
            color = GREEN;
        } else if (hpPercent > 0.25) {
            color = YELLOW;
        } else {
            color = RED;
        }

        return color + "█".repeat(Math.max(0, filledBars)) + RESET +
                "░".repeat(Math.max(0, emptyBars));
    }

    /**
     * Display combat status
     */
    public static void displayCombatStatus(Player player, Character enemy) {
        System.out.println("\n" + "─".repeat(50));

        // Player status
        System.out.println(CYAN + "⚔ " + player.getName() + " (Lv." + player.getLevel() + ")" + RESET);
        System.out.println("HP: " + getHealthBar(player, 20) + " " +
                player.getCurrentHp() + "/" + player.getMaxHp());

        System.out.println();

        // Enemy status
        System.out.println(RED + "☠ " + enemy.getName() + " (Lv." + enemy.getLevel() + ")" + RESET);
        System.out.println("HP: " + getHealthBar(enemy, 20) + " " +
                enemy.getCurrentHp() + "/" + enemy.getMaxHp());

        System.out.println("─".repeat(50));
    }

    /**
     * Display damage dealt
     */
    public static void displayDamage(String attackerName, String targetName, int damage) {
        System.out.println("\n" + YELLOW + attackerName + RESET + " attacks " +
                YELLOW + targetName + RESET + " for " +
                RED + BOLD + damage + " damage!" + RESET);
    }

    /**
     * Display victory message
     */
    public static void displayVictory() {
        System.out.println("\n" + GREEN + BOLD + "═".repeat(50) + RESET);
        System.out.println(GREEN + BOLD + "          🎉 VICTORY! 🎉" + RESET);
        System.out.println(GREEN + BOLD + "═".repeat(50) + RESET);
    }

    /**
     * Display defeat message
     */
    public static void displayDefeat() {
        System.out.println("\n" + RED + BOLD + "═".repeat(50) + RESET);
        System.out.println(RED + BOLD + "          ☠ DEFEAT ☠" + RESET);
        System.out.println(RED + BOLD + "═".repeat(50) + RESET);
    }

    /**
     * Display a message with a border
     */
    public static void displayMessage(String message) {
        System.out.println("\n" + message);
    }

    /**
     * Display game logo
     */
    public static void displayGameLogo() {
        System.out.println(CYAN + BOLD);
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║        ⚔️  DUNGEON QUEST RPG  ⚔️               ║");
        System.out.println("║                                                  ║");
        System.out.println("║           An Epic Text Adventure                ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println(RESET);
    }
}
