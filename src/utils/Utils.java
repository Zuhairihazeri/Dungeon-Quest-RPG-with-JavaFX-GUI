package utils;

import java.util.Scanner;
import java.util.Random;

/**
 * Utility class providing helper methods and shared resources
 */
public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    
    /**
     * Get the global Scanner instance
     */
    public static Scanner getScanner() {
        return scanner;
    }
    
    /**
     * Get a random integer between min (inclusive) and max (inclusive)
     */
    public static int getRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    /**
     * Pause execution until user presses Enter
     */
    public static void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Clear console (works on most terminals)
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
