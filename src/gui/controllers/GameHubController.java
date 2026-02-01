package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import gui.SceneManager;
import entities.Player;
import entities.Enemy;
import factories.EnemyFactory;

/**
 * Controller for the Game Hub screen
 */
public class GameHubController {

    @FXML
    private Label playerNameLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label floorsLabel;
    @FXML
    private Label goldLabel;
    @FXML
    private Label hpLabel;
    @FXML
    private Label xpLabel;
    @FXML
    private ProgressBar hpBar;
    @FXML
    private ProgressBar xpBar;

    private Player player;
    private int floorsCleared = 0;

    @FXML
    private void initialize() {
        player = SceneManager.getInstance().getCurrentPlayer();
        updateUI();
    }

    /**
     * Update all UI elements with current player stats
     */
    private void updateUI() {
        if (player == null)
            return;

        playerNameLabel.setText(player.getName());
        levelLabel.setText("Level: " + player.getLevel());
        floorsLabel.setText("Floors Cleared: " + floorsCleared);
        goldLabel.setText("💰 Gold: " + player.getGold());

        // HP
        hpLabel.setText(player.getCurrentHp() + "/" + player.getMaxHp());
        double hpPercent = (double) player.getCurrentHp() / player.getMaxHp();
        hpBar.setProgress(hpPercent);

        // Update HP bar color
        hpBar.getStyleClass().removeAll("health-high", "health-medium", "health-low");
        if (hpPercent > 0.5) {
            hpBar.getStyleClass().add("health-high");
        } else if (hpPercent > 0.25) {
            hpBar.getStyleClass().add("health-medium");
        } else {
            hpBar.getStyleClass().add("health-low");
        }

        // XP
        xpLabel.setText(player.getExperience() + "/" + player.getExperienceToNextLevel());
        double xpPercent = (double) player.getExperience() / player.getExperienceToNextLevel();
        xpBar.setProgress(xpPercent);
    }

    @FXML
    private void handleExplore() {
        // Create random enemy (including bosses every 10 floors)
        Enemy enemy = EnemyFactory.createRandomEnemy(player.getLevel(), floorsCleared + 1);

        // Store the enemy in a temporary field in SceneManager for combat
        SceneManager sceneManager = SceneManager.getInstance();

        // We need to pass the enemy to combat controller
        // We'll create a method in SceneManager to handle this
        CombatController.setCurrentEnemy(enemy);
        CombatController.setGameHubController(this);

        sceneManager.showCombat();
    }

    @FXML
    private void handleStatus() {
        SceneManager.getInstance().showCharacterStatus();
    }

    @FXML
    private void handleInventory() {
        SceneManager.getInstance().showInventory();
    }

    @FXML
    private void handleShop() {
        SceneManager.getInstance().showShop();
    }

    @FXML
    private void handleRest() {
        player.setCurrentHp(player.getMaxHp());

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Rest");
        alert.setHeaderText("You rest and recover your strength...");
        alert.setContentText("HP fully restored!");
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/gui/resources/styles.css").toExternalForm());
        alert.showAndWait();

        updateUI();
    }

    @FXML
    private void handleQuit() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Your progress will be lost.");
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/gui/resources/styles.css").toExternalForm());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                SceneManager.getInstance().showMainMenu();
            }
        });
    }

    /**
     * Call this after winning combat
     */
    public void onCombatVictory() {
        floorsCleared++;
        updateUI();
    }

    /**
     * Call this when returning from other screens
     */
    public void refresh() {
        updateUI();
    }
}
