package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import javafx.fxml.FXML;

public class EditAddressSceneController extends SceneController {
    
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
