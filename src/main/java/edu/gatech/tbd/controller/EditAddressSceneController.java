package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import edu.gatech.tbd.model.UserManager;

public class EditAddressSceneController extends SceneController {
    @FXML
    private TextField addressField;
    
    @FXML
    public void initialize() {
        addressField.setText(UserManager.getLoggedInUser().getAddress());
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
    	UserManager.updateUserInformation(UserManager.getLoggedInUser(), UserManager.getLoggedInUser().getName(), UserManager.getLoggedInUser().getUsername(), 
    			UserManager.getLoggedInUser().getPassword(), UserManager.getLoggedInUser().getType(), 
    			UserManager.getLoggedInUser().getEmail(), addressField.getText());
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
