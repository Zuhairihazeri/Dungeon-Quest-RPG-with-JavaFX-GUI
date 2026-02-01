package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gui.SceneManager;
import entities.Player;
import items.Item;
import items.Equipment;

/**
 * Controller for the Inventory screen
 */
public class InventoryController {

    @FXML
    private ListView<String> inventoryList;
    @FXML
    private Button useEquipButton;
    @FXML
    private Label weaponLabel;
    @FXML
    private Label weaponBonusLabel;
    @FXML
    private Label armorLabel;
    @FXML
    private Label armorBonusLabel;
    @FXML
    private Label totalAtkLabel;
    @FXML
    private Label totalDefLabel;

    private Player player;
    private ObservableList<String> inventoryItems;

    @FXML
    private void initialize() {
        player = SceneManager.getInstance().getCurrentPlayer();
        inventoryItems = FXCollections.observableArrayList();
        inventoryList.setItems(inventoryItems);

        updateUI();
    }

    private void updateUI() {
        if (player == null)
            return;

        // Update inventory list
        inventoryItems.clear();
        for (Item item : player.getInventory().getItems()) {
            inventoryItems.add(item.getDisplayString());
        }

        // Update equipment display
        Equipment weapon = player.getEquippedWeapon();
        Equipment armor = player.getEquippedArmor();

        if (weapon != null) {
            weaponLabel.setText(weapon.getName());
            weaponBonusLabel.setText("ATK +" + weapon.getAttackBonus());
        } else {
            weaponLabel.setText("None");
            weaponBonusLabel.setText("");
        }

        if (armor != null) {
            armorLabel.setText(armor.getName());
            armorBonusLabel.setText("DEF +" + armor.getDefenseBonus());
        } else {
            armorLabel.setText("None");
            armorBonusLabel.setText("");
        }

        // Update total stats
        totalAtkLabel.setText(String.valueOf(player.getAttack()));
        totalDefLabel.setText(String.valueOf(player.getDefense()));
    }

    @FXML
    private void handleUseEquip() {
        int selectedIndex = inventoryList.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showAlert("No Selection", "Please select an item from your inventory.", AlertType.WARNING);
            return;
        }

        Item item = player.getInventory().getItem(selectedIndex);

        if (item instanceof Equipment) {
            Equipment equipment = (Equipment) item;
            player.getInventory().removeItem(item);
            player.equip(equipment);

            showAlert("Equipped", "Equipped " + equipment.getName() + "!", AlertType.INFORMATION);
        } else {
            // Use item (e.g., potion)
            boolean consumed = item.use(player);
            if (consumed) {
                player.getInventory().removeItem(item);
            }
        }

        updateUI();
    }

    @FXML
    private void handleDrop() {
        int selectedIndex = inventoryList.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showAlert("No Selection", "Please select an item to drop.", AlertType.WARNING);
            return;
        }

        Item item = player.getInventory().getItem(selectedIndex);

        Alert confirm = new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("Drop Item");
        confirm.setHeaderText("Are you sure?");
        confirm.setContentText("Drop " + item.getName() + "?");
        styleAlert(confirm);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                player.getInventory().removeItem(item);
                updateUI();
            }
        });
    }

    @FXML
    private void handleUnequipWeapon() {
        if (player.getEquippedWeapon() != null) {
            player.unequipWeapon();
            updateUI();
        }
    }

    @FXML
    private void handleUnequipArmor() {
        if (player.getEquippedArmor() != null) {
            player.unequipArmor();
            updateUI();
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
