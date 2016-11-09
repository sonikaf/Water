package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the Welcome Scene.
 */
public class WelcomeSceneController extends SceneController{
	
	@FXML
	Button selectLoginButton;
	
	@FXML
	Button selectRegisterButton;
	
	/**
	 * Handler for the Login button.
	 */
	@FXML
	protected void onSelectLoginButtonPressed() {
		mainApp.changeScene("LoginScene");
	}
	
	/**
	 * Handler for the Register button.
	 */
	@FXML
	protected void onSelectRegisterButtonPressed() {
		mainApp.changeScene("RegistrationScene");
	}

}