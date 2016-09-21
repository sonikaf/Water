package edu.gatech.tbd.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import edu.gatech.tbd.Main;

public class LoginStageController {
	
    /**
     * Reference to main application class
     */
    Main mainApp;
    
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
        
	    // Getting username/password data from textfields
	    String username = usernameField.getText();
	    String password = passwordField.getText(); 
	    
	    // if username/password combo matches stored data
	    if (username.equals("user") && password.equals("pass")) {
	        mainApp.showApplicationStage();
	    }
	    // if username/password combo does not match stored data
	    else {
	        errorLabel.setText(
	                "Login Failed: Invalid Username or Password");
	        usernameField.setText("");
	        passwordField.setText("");
	    }
	}
	
	/**setter for mainApp*/
	public void setMainApp(Main mainApp) {
	    this.mainApp = mainApp;
	}

}
