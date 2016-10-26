package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import edu.gatech.tbd.model.UserManager;

/**
 * Controller for the main application scene.
 */
public class AdminSceneController extends SceneController {

    /**
     * Handler for the Edit Profile button.
     */
    @FXML
    protected void onEditProfileButtonPressed() {
        mainApp.doPopupWindow("EditProfileScene");
    }

    /**
     * Handler for the Logout button.
     */
    @FXML
    protected void onLogoutButtonPressed() {
        UserManager.logoutUser();
        mainApp.changeScene("WelcomeScene");
    }
}