package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import edu.gatech.tbd.model.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditUsernameSceneController extends SceneController {
    @FXML
    private TextField usernameField;
    
    @FXML
    public void initialize() {
        usernameField.setText(UserManager.getLoggedInUser().getUsername());
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
    	UserManager.updateUserInformation(UserManager.getLoggedInUser(), UserManager.getLoggedInUser().getName(), usernameField.getText(), 
    			UserManager.getLoggedInUser().getPassword(), UserManager.getLoggedInUser().getType(), 
    			UserManager.getLoggedInUser().getEmail(), UserManager.getLoggedInUser().getAddress());
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
