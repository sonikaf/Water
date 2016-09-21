package edu.gatech.tbd.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginStageController {
	
	@FXML
	Label errorLabel;
	
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	public void initialize() {
		
	} 
	
	@FXML
	protected void onLoginButtonPressed(ActionEvent e) {
		 
	}

}
