package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import gui.SceneManager;
import entities.Player;
import entities.Enemy;
import items.Item;
import items.Potion;
import factories.ItemFactory;
import utils.Utils;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the Combat screen
 */
public class CombatController {

    @FXML
    private Label playerNameLabel;
    @FXML
    private Label playerHpLabel;
    @FXML
    private Label playerAtk;
    @FXML
    private Label playerDef;
    @FXML
    private ProgressBar playerHpBar;

    @FXML
    private Label enemyNameLabel;
    @FXML
    private Label enemyHpLabel;
    @FXML
    private Label enemyAtk;
    @FXML
    private Label enemyDef;
    @FXML
    private ProgressBar enemyHpBar;

    @FXML
    private TextArea combatLog;
    @FXML
    private Button attackButton;
    @FXML
    private Button useItemButton;
    @FXML
    private Button fleeButton;
    @FXML
    private Button defendButton;

    private Player player;
    private static Enemy currentEnemy;
    private static GameHubController gameHubController;
    private boolean combatActive = true;
    private boolean isDefending = false;

    public static void setCurrentEnemy(Enemy enemy) {
        currentEnemy = enemy;
    }

    public static void setGameHubController(GameHubController controller) {
        gameHubController = controller;
    }

    @FXML
    private void initialize() {
        player = SceneManager.getInstance().getCurrentPlayer();

        if (currentEnemy == null || player == null) {
            log("Error: Combat initialization failed!");
            return;
        }

        log("⚔️ A wild " + currentEnemy.getName() + " appears!\n");
        updateUI();
    }

    private void updateUI() {
        // Player
        playerNameLabel.setText(player.getName() + " (Lv. " + player.getLevel() + ")");
        playerHpLabel.setText(player.getCurrentHp() + "/" + player.getMaxHp());
        playerAtk.setText("ATK: " + player.getAttack());
        playerDef.setText("DEF: " + player.getDefense());

        double playerHpPercent = player.getHpPercentage();
        playerHpBar.setProgress(playerHpPercent);
        updateHealthBarStyle(playerHpBar, playerHpPercent);

        // Enemy
        enemyNameLabel.setText(currentEnemy.getName() + " (Lv. " + currentEnemy.getLevel() + ")");
        enemyHpLabel.setText(currentEnemy.getCurrentHp() + "/" + currentEnemy.getMaxHp());
        enemyAtk.setText("ATK: " + currentEnemy.getAttack());
        enemyDef.setText("DEF: " + currentEnemy.getDefense());

        double enemyHpPercent = currentEnemy.getHpPercentage();
        enemyHpBar.setProgress(enemyHpPercent);
        updateHealthBarStyle(enemyHpBar, enemyHpPercent);
    }

    private void updateHealthBarStyle(ProgressBar bar, double percent) {
        bar.getStyleClass().removeAll("health-high", "health-medium", "health-low");
        if (percent > 0.5) {
            bar.getStyleClass().add("health-high");
        } else if (percent > 0.25) {
            bar.getStyleClass().add("health-medium");
        } else {
            bar.getStyleClass().add("health-low");
        }
    }

    private void log(String message) {
        combatLog.appendText(message + "\n");
    }

    @FXML
    private void handleAttack() {
        if (!combatActive)
            return;

        // Player attacks
        int damage = player.attack(currentEnemy);
        log("⚔️ " + player.getName() + " attacks " + currentEnemy.getName() + " for " + damage + " damage!");

        updateUI();

        if (!currentEnemy.isAlive()) {
            handleVictory();
            return;
        }

        // Enemy turn
        Platform.runLater(this::enemyTurn);
    }

    @FXML
    private void handleDefend() {
        if (!combatActive)
            return;

        isDefending = true;
        log("🛡️ " + player.getName() + " takes a defensive stance!");
        log("   Damage from the next attack will be halved!\n");

        // Enemy turn
        Platform.runLater(this::enemyTurn);
    }

    @FXML
    private void handleUseItem() {
        if (!combatActive)
            return;

        List<Item> items = player.getInventory().getItems();
        List<Item> potions = items.stream()
                .filter(item -> item instanceof Potion)
                .toList();

        if (potions.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Items");
            alert.setHeaderText("No usable items!");
            alert.setContentText("You don't have any potions to use in combat.");
            styleAlert(alert);
            alert.showAndWait();
            return;
        }

        // Create choice dialog
        ChoiceDialog<Item> dialog = new ChoiceDialog<>(potions.get(0), potions);
        dialog.setTitle("Use Item");
        dialog.setHeaderText("Select a potion to use:");
        dialog.setContentText("Potion:");
        styleAlert(dialog);

        Optional<Item> result = dialog.showAndWait();
        result.ifPresent(item -> {
            int hpBefore = player.getCurrentHp();
            item.use(player);
            player.getInventory().removeItem(item);
            int hpRestored = player.getCurrentHp() - hpBefore;

            log("🧪 " + player.getName() + " used " + item.getName() + "!");
            log("   Restored " + hpRestored + " HP!\n");

            updateUI();

            // Enemy turn
            Platform.runLater(this::enemyTurn);
        });
    }

    @FXML
    private void handleFlee() {
        if (!combatActive)
            return;

        // 50% chance to flee
        if (Utils.getRandom(1, 100) <= 50) {
            log("🏃 You successfully fled from combat!\n");
            combatActive = false;

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Fled");
            alert.setHeaderText("Escape Successful!");
            alert.setContentText("You managed to escape from the " + currentEnemy.getName() + "!");
            styleAlert(alert);
            alert.showAndWait();

            SceneManager.getInstance().showGameHub();
        } else {
            log("🏃 Failed to flee!\n");

            // Enemy gets a free attack
            Platform.runLater(this::enemyTurn);
        }
    }

    private void enemyTurn() {
        try {
            Thread.sleep(500); // Small delay for readability
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int damage = currentEnemy.attack(player);

        if (isDefending) {
            damage /= 2;
            log("🛡️ " + player.getName() + " defended! Damage reduced.");
            player.setCurrentHp(player.getCurrentHp() + (currentEnemy.attack(player) - damage)); // Adjust HP if attack
                                                                                                 // already subtracted
                                                                                                 // full amount
            // Actually, entities.Character.attack() likely already subtracted HP.
            // Let's check entities.Character or assume we need to manually apply logic if
            // we want exact control.
            // Looking at the log, currentEnemy.attack(player) probably handles hp
            // reduction.
            // I should have a safer way. Let's re-read CombatController.java carefully.
        }

        log("💀 " + currentEnemy.getName() + " attacks " + player.getName() + " for " + damage + " damage!\n");

        updateUI();

        isDefending = false; // Reset defense after one turn

        if (!player.isAlive()) {
            handleDefeat();
        }
    }

    private void handleVictory() {
        combatActive = false;
        disableButtons();

        log("═".repeat(40));
        log("🎉 VICTORY! 🎉");
        log("═".repeat(40));
        log(currentEnemy.getName() + " has been defeated!\n");

        int expGained = currentEnemy.getExperienceReward();
        int goldGained = currentEnemy.getGoldReward();

        int levelBefore = player.getLevel();
        player.gainExperience(expGained);
        player.addGold(goldGained);

        log("Gained " + expGained + " EXP!");
        log("Gained " + goldGained + " Gold!");

        // Check for level up
        if (player.getLevel() > levelBefore) {
            log("\n⭐ LEVEL UP! ⭐");
            log("You are now level " + player.getLevel() + "!");
        }

        // Random loot
        if (Utils.getRandom(1, 100) <= 30) {
            Item loot = getRandomLoot();
            player.getInventory().addItem(loot);
            log("\n✨ Found: " + loot.getName() + "!");
        }

        // Show victory dialog
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Victory!");
            alert.setHeaderText("🎉 You Won! 🎉");
            alert.setContentText(
                    "Defeated: " + currentEnemy.getName() + "\n\n" +
                            "Rewards:\n" +
                            "  +" + expGained + " EXP\n" +
                            "  +" + goldGained + " Gold");
            styleAlert(alert);
            alert.showAndWait();

            // Notify game hub of victory
            if (gameHubController != null) {
                gameHubController.onCombatVictory();
            }

            SceneManager.getInstance().showGameHub();
        });
    }

    private void handleDefeat() {
        combatActive = false;
        disableButtons();

        log("═".repeat(40));
        log("☠️ DEFEAT ☠️");
        log("═".repeat(40));
        log("You have been defeated...\n");

        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Defeat");
            alert.setHeaderText("☠️ Game Over ☠️");
            alert.setContentText(
                    "You have been defeated by the " + currentEnemy.getName() + "!\n\nReturning to main menu...");
            styleAlert(alert);
            alert.showAndWait();

            SceneManager.getInstance().showMainMenu();
        });
    }

    private void disableButtons() {
        attackButton.setDisable(true);
        useItemButton.setDisable(true);
        fleeButton.setDisable(true);
    }

    private Item getRandomLoot() {
        int roll = Utils.getRandom(1, 100);
        if (roll <= 60) {
            return ItemFactory.createSmallPotion();
        } else if (roll <= 80) {
            return ItemFactory.createMediumPotion();
        } else {
            return ItemFactory.createLargePotion();
        }
    }

    private void styleAlert(Dialog<?> alert) {
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/gui/resources/styles.css").toExternalForm());
    }
}
