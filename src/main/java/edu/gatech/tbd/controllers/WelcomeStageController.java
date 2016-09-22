package edu.gatech.tbd.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeStageController extends SceneController{
	
	@FXML
	Button selectLoginButton;
	
	@FXML
	Button selectRegisterButton;
	
	
	@FXML
	protected void onSelectLoginButtonPressed(ActionEvent e) {
		mainApp.changeScene("LoginScene");
	}
	
	@FXML
	protected void onSelectRegisterButtonPressed(ActionEvent e) {
		 
	}

}