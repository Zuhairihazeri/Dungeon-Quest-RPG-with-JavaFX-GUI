package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import gui.SceneManager;
import entities.Player;
import factories.ItemFactory;

/**
 * Controller for the Main Menu screen
 */
public class MainMenuController {

    @FXML
    private Button newGameButton;

    @FXML
    private Button howToPlayButton;

    @FXML
    private Button exitButton;

    @FXML
    private void handleNewGame() {
        // Create dialog for character name
        TextInputDialog dialog = new TextInputDialog("Hero");
        dialog.setTitle("New Game");
        dialog.setHeaderText("Create Your Character");
        dialog.setContentText("Enter your hero's name:");

        // Style the dialog
        dialog.getDialogPane().getStylesheets().add(
                getClass().getResource("/gui/resources/styles.css").toExternalForm());

        dialog.showAndWait().ifPresent(name -> {
            if (name.trim().isEmpty()) {
                name = "Hero";
            }

            // Create new player
            Player player = new Player(name);

            // Give starting equipment
            player.getInventory().addItem(ItemFactory.createWoodenSword());
            player.getInventory().addItem(ItemFactory.createLeatherArmor());
            player.getInventory().addItem(ItemFactory.createSmallPotion());
            player.getInventory().addItem(ItemFactory.createSmallPotion());

            // Set current player
            SceneManager.getInstance().setCurrentPlayer(player);

            // Show welcome message
            Alert welcome = new Alert(AlertType.INFORMATION);
            welcome.setTitle("Welcome");
            welcome.setHeaderText("Welcome, " + name + "!");
            welcome.setContentText("You have received starting equipment!\n\n" +
                    "Don't forget to equip your items from the inventory!");
            welcome.getDialogPane().getStylesheets().add(
                    getClass().getResource("/gui/resources/styles.css").toExternalForm());
            welcome.showAndWait();

            // Go to game hub
            SceneManager.getInstance().showGameHub();
        });
    }

    @FXML
    private void handleHowToPlay() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("How to Play");
        alert.setHeaderText("⚔ Dungeon Quest RPG - Guide ⚔");

        String guide = "🎯 OBJECTIVE:\n" +
                "  Explore the dungeon, defeat monsters, and become stronger!\n\n" +

                "⚔️ COMBAT:\n" +
                "  • Attack enemies to deal damage\n" +
                "  • Use potions to restore HP during battle\n" +
                "  • Flee if the fight is too difficult (50% success rate)\n\n" +

                "📊 PROGRESSION:\n" +
                "  • Gain XP by defeating enemies\n" +
                "  • Level up to increase your stats\n" +
                "  • Find and equip better weapons and armor\n\n" +

                "💰 LOOT:\n" +
                "  • Enemies drop gold and items\n" +
                "  • Visit the shop to buy equipment and potions\n\n" +

                "Good luck, adventurer!";

        alert.setContentText(guide);
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/gui/resources/styles.css").toExternalForm());

        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }
}
