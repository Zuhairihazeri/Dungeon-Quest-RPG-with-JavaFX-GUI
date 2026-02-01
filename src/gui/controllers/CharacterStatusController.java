package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import gui.SceneManager;
import entities.Player;
import items.Equipment;

/**
 * Controller for the Character Status screen
 */
public class CharacterStatusController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label levelValue;
    @FXML
    private Label maxHpValue;
    @FXML
    private Label currentHpValue;
    @FXML
    private Label baseAtkValue;
    @FXML
    private Label totalAtkValue;
    @FXML
    private Label baseDefValue;
    @FXML
    private Label totalDefValue;
    @FXML
    private Label goldValue;
    @FXML
    private Label xpLabel;
    @FXML
    private ProgressBar xpBar;
    @FXML
    private Label weaponLabel;
    @FXML
    private Label armorLabel;

    private Player player;

    @FXML
    private void initialize() {
        player = SceneManager.getInstance().getCurrentPlayer();
        updateUI();
    }

    private void updateUI() {
        if (player == null)
            return;

        nameLabel.setText(player.getName());
        levelValue.setText(String.valueOf(player.getLevel()));
        maxHpValue.setText(String.valueOf(player.getMaxHp()));
        currentHpValue.setText(String.valueOf(player.getCurrentHp()));

        baseAtkValue.setText(String.valueOf(player.getBaseAttack()));
        totalAtkValue.setText(String.valueOf(player.getAttack()));

        baseDefValue.setText(String.valueOf(player.getBaseDefense()));
        totalDefValue.setText(String.valueOf(player.getDefense()));

        goldValue.setText(String.valueOf(player.getGold()));

        // XP Progress
        xpLabel.setText(player.getExperience() + " / " + player.getExperienceToNextLevel());
        double xpPercent = (double) player.getExperience() / player.getExperienceToNextLevel();
        xpBar.setProgress(xpPercent);

        // Equipment
        Equipment weapon = player.getEquippedWeapon();
        Equipment armor = player.getEquippedArmor();

        weaponLabel.setText(weapon != null ? weapon.getName() : "None");
        armorLabel.setText(armor != null ? armor.getName() : "None");
    }

    @FXML
    private void handleBack() {
        SceneManager.getInstance().showGameHub();
    }
}
