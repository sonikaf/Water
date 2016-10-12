package edu.gatech.tbd.controller;

import javafx.event.ActionEvent;
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
	 * @param e
	 */
	@FXML
	protected void onSelectLoginButtonPressed(ActionEvent e) {
		mainApp.changeScene("LoginScene");
	}
	
	/**
	 * Handler for the Register button.
	 * @param e
	 */
	@FXML
	protected void onSelectRegisterButtonPressed(ActionEvent e) {
		mainApp.changeScene("RegistrationScene");
	}

}