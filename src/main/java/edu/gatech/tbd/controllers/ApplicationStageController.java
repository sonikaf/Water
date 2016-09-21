package edu.gatech.tbd.controllers;

import edu.gatech.tbd.Main;
import javafx.fxml.FXML;

public class ApplicationStageController {
    
    /**
     * reference to main application class
     */
    Main mainApp;
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    protected void onLogoutButtonPressed() {
        mainApp.showLoginStage();
    }
    
    /**
     * sets the mainApp field
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
