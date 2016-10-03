package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import edu.gatech.tbd.model.UserManager;

public class EditProfileSceneController extends SceneController{
	
    @FXML
    private Text nameField;
    
    @FXML
    private Text emailField;
    
    @FXML
    private Text usernameField;
    
    @FXML
    private Text passwordField;
    
    @FXML
    private Text addressField;
    
    @FXML
    private Text typeField;
    
    @FXML
    public void initialize() {
        nameField.setText(UserManager.getLoggedInUser().getName());
        emailField.setText(UserManager.getLoggedInUser().getEmail());
        usernameField.setText(UserManager.getLoggedInUser().getUsername());
        passwordField.setText(UserManager.getLoggedInUser().getPassword());
        addressField.setText(UserManager.getLoggedInUser().getAddress());
        typeField.setText(UserManager.getLoggedInUser().getType().toString());
	}
	
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("ApplicationScene");
    }
    
    @FXML
    protected void onEditNameButtonPressed() {
    	mainApp.changeScene("EditNameScene");
    }	
    
    @FXML
    protected void onEditEmailButtonPressed() {
    	mainApp.changeScene("EditEmailScene");
    }	

    @FXML
    protected void onEditUsernameButtonPressed() {
    	mainApp.changeScene("EditUsernameScene");
    }	

    @FXML
    protected void onEditPasswordButtonPressed() {
    	mainApp.changeScene("EditPasswordScene");
    }
    
    @FXML
    protected void onEditAddressButtonPressed() {
    	mainApp.changeScene("EditAddressScene");
    }	

    @FXML
    protected void onEditTypeButtonPressed() {
    	mainApp.changeScene("EditTypeScene");
    }	

}
