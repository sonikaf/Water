package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import javafx.fxml.FXML;

public class ApplicationSceneController extends SceneController {
    
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    protected void onLogoutButtonPressed() {
        mainApp.changeScene("WelcomeScene");
    }
    
    @FXML
    protected void onEditProfileButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
