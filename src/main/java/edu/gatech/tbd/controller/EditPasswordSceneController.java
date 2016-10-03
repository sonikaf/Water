package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import edu.gatech.tbd.model.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditPasswordSceneController extends SceneController {
    @FXML
    private TextField passwordField;
    
    @FXML
    public void initialize() {
        passwordField.setText(UserManager.getLoggedInUser().getPassword());
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
    	UserManager.updateUserInformation(UserManager.getLoggedInUser(), UserManager.getLoggedInUser().getName(), UserManager.getLoggedInUser().getUsername(), 
    			passwordField.getText(), UserManager.getLoggedInUser().getType(), 
    			UserManager.getLoggedInUser().getEmail(), UserManager.getLoggedInUser().getAddress());
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
