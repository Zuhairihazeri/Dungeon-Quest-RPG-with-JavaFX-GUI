package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Main JavaFX application for Dungeon Quest RPG
 */
public class MainApp extends Application {

    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 650;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Set up the primary stage
            primaryStage.setTitle("⚔ Dungeon Quest RPG ⚔");
            primaryStage.setResizable(false);

            // Initialize scene manager with the primary stage
            SceneManager.getInstance().setPrimaryStage(primaryStage);

            // Show main menu
            SceneManager.getInstance().showMainMenu();

            // Show the stage
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
