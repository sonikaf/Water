package edu.gatech.tbd.controllers;

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
}
