package edu.gatech.tbd.controller;

import edu.gatech.tbd.model.User;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


/**
 * Controller for the Login Scene.
 */
public class LoginSceneController extends SceneController{
	   
	@FXML
	Label errorLabel;
	
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField passwordField;

	@FXML
	Button loginButton;

	@FXML
	Button goBackButton;

	@FXML
	public void initialize() {
		
	} 
	
	/**
	 * Handler for the Login button.
	 */
	@FXML
	protected void onLoginButtonPressed() {
        
	    // Getting username/password data from textfields
	    String username = usernameField.getText();
	    String password = passwordField.getText(); 
	    
	    // Clear username and password textfields
	    usernameField.setText("");
	    passwordField.setText("");
	    
	    // if username/password combo matches stored data
	    try {
	    	User user = UserManager.loginUser(username, password);
	    	
	    	// route administrator to admin screen
	    	// and route user, worker, manager to main app
	    	routeUserOnLogin(user);
	    	
	    } catch (UserException ex) {
	    	errorLabel.setText(ex.getMessage());
	    }
	}
	
	/**
	 * Handler for the Go Back button.
	 */
	@FXML
	protected void onGoBackButtonPressed() {
		mainApp.changeScene("WelcomeScene");
	}

}
