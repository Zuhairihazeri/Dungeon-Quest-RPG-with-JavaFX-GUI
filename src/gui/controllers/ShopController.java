package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import gui.SceneManager;
import entities.Player;
import items.Item;
import factories.ItemFactory;

/**
 * Controller for the Shop screen
 */
public class ShopController {

    @FXML
    private Label goldLabel;

    private Player player;

    @FXML
    private void initialize() {
        player = SceneManager.getInstance().getCurrentPlayer();
        updateGold();
    }

    private void updateGold() {
        if (player != null) {
            goldLabel.setText("Your Gold: 💰" + player.getGold());
        }
    }

    @FXML
    private void buySmallPotion() {
        buyItem(ItemFactory.createSmallPotion(), 15);
    }

    @FXML
    private void buyMediumPotion() {
        buyItem(ItemFactory.createMediumPotion(), 30);
    }

    @FXML
    private void buyLargePotion() {
        buyItem(ItemFactory.createLargePotion(), 50);
    }

    @FXML
    private void buyDagger() {
        buyItem(ItemFactory.createDagger(), 25);
    }

    @FXML
    private void buyIronSword() {
        buyItem(ItemFactory.createIronSword(), 50);
    }

    @FXML
    private void buyWarhammer() {
        buyItem(ItemFactory.createWarhammer(), 90);
    }

    @FXML
    private void buySteelSword() {
        buyItem(ItemFactory.createSteelSword(), 120);
    }

    @FXML
    private void buyKatana() {
        buyItem(ItemFactory.createKatana(), 250);
    }

    @FXML
    private void buyClothRobe() {
        buyItem(ItemFactory.createClothRobe(), 20);
    }

    @FXML
    private void buyChainmail() {
        buyItem(ItemFactory.createChainmail(), 60);
    }

    @FXML
    private void buyMithrilVest() {
        buyItem(ItemFactory.createMithrilVest(), 120);
    }

    @FXML
    private void buyPlateArmor() {
        buyItem(ItemFactory.createPlateArmor(), 150);
    }

    @FXML
    private void buyHolyAegis() {
        buyItem(ItemFactory.createHolyAegis(), 500);
    }

    private void buyItem(Item item, int price) {
        if (player.getGold() >= price) {
            if (player.getInventory().addItem(item)) {
                player.removeGold(price);
                updateGold();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Purchase Successful");
                alert.setHeaderText("Item Purchased!");
                alert.setContentText("You bought " + item.getName() + " for " + price + " gold!");
                styleAlert(alert);
                alert.showAndWait();
            } else {
                showAlert("Inventory Full", "Your inventory is full! Cannot purchase item.", AlertType.WARNING);
            }
        } else {
            showAlert("Insufficient Gold",
                    "You need " + price + " gold, but only have " + player.getGold() + " gold.",
                    AlertType.WARNING);
        }
    }

    @FXML
    private void handleBack() {
        SceneManager.getInstance().showGameHub();
    }

    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        styleAlert(alert);
        alert.showAndWait();
    }

    private void styleAlert(Dialog<?> alert) {
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/gui/resources/styles.css").toExternalForm());
    }
}
