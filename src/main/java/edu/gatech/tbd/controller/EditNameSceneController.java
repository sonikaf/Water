package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import edu.gatech.tbd.model.UserManager;

public class EditNameSceneController extends SceneController {
	
    @FXML
    private TextField nameField;
    
    @FXML
    public void initialize() {
    	nameField.setText(UserManager.getLoggedInUser().getName());
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
    	UserManager.updateUserInformation(UserManager.getLoggedInUser(), nameField.getText(), UserManager.getLoggedInUser().getUsername(), 
    			UserManager.getLoggedInUser().getPassword(), UserManager.getLoggedInUser().getType(), 
    			UserManager.getLoggedInUser().getEmail(), UserManager.getLoggedInUser().getAddress());
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
