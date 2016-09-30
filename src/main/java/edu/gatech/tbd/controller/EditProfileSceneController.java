package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

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
        nameField.setText("Joe");
        emailField.setText("joe@gmail.com");
        usernameField.setText("joe123");
        passwordField.setText("joe<3'scats");
        addressField.setText("123 Sesame St");
        typeField.setText("User");
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
