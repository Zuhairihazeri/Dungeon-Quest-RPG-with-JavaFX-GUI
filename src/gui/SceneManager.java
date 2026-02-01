package gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import entities.Player;
import java.io.IOException;

/**
 * Manages scene transitions and game state
 */
public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;
    private Player currentPlayer;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    /**
     * Load a scene from FXML file
     */
    private Scene loadScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/" + fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/gui/resources/styles.css").toExternalForm());
        return scene;
    }

    /**
     * Show main menu
     */
    public void showMainMenu() {
        try {
            Scene scene = loadScene("MainMenu.fxml");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show game hub
     */
    public void showGameHub() {
        try {
            Scene scene = loadScene("GameHub.fxml");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show combat screen
     */
    public void showCombat() {
        try {
            Scene scene = loadScene("Combat.fxml");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show character status
     */
    public void showCharacterStatus() {
        try {
            Scene scene = loadScene("CharacterStatus.fxml");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show inventory
     */
    public void showInventory() {
        try {
            Scene scene = loadScene("Inventory.fxml");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show shop
     */
    public void showShop() {
        try {
            Scene scene = loadScene("Shop.fxml");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
