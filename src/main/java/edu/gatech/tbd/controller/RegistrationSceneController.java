package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import edu.gatech.tbd.Main;

public class RegistrationSceneController extends SceneController{

	@FXML
	public void initialize() {
		
	} 
	
	@FXML
	protected void onGoBackButtonPressed() {
		 mainApp.changeScene("WelcomeScene");
	}
	
	@FXML
	protected void onRegisterButtonPressed() {
		 mainApp.changeScene("ApplicationScene");
	}

}
