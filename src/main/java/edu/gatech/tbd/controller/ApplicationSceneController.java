package edu.gatech.tbd.controller;

import edu.gatech.tbd.model.UserManager;
import javafx.fxml.FXML;

public class ApplicationSceneController extends SceneController {
    
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    protected void onLogoutButtonPressed() {
    	UserManager.logoutUser();
        mainApp.changeScene("WelcomeScene");
    }
    
    @FXML
    protected void onEditProfileButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
