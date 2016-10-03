package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import edu.gatech.tbd.model.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditEmailSceneController extends SceneController {
    @FXML
    private TextField emailField;
    
    @FXML
    public void initialize() {
        emailField.setText(UserManager.getLoggedInUser().getEmail());
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
    	UserManager.updateUserInformation(UserManager.getLoggedInUser(), UserManager.getLoggedInUser().getName(), UserManager.getLoggedInUser().getUsername(), 
    			UserManager.getLoggedInUser().getPassword(), UserManager.getLoggedInUser().getType(), 
    			emailField.getText(), UserManager.getLoggedInUser().getAddress());
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
