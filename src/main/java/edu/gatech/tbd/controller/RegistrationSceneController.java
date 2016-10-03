package edu.gatech.tbd.controller;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.tbd.model.User;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationSceneController extends SceneController{

    @FXML
    private ComboBox<UserType> typeField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField nameField;
    @FXML
    private Label errorLabel;
    
	@FXML
	public void initialize() {
        List<UserType> list = new ArrayList<UserType>();
        list.add(UserType.User);
        list.add(UserType.Worker);
        list.add(UserType.Manager);
        list.add(UserType.Administrator);
        ObservableList<UserType> obList = FXCollections.observableList(list);
        typeField.setItems(obList);
        typeField.setValue(UserType.User);
	} 
	
	@FXML
	protected void onGoBackButtonPressed() {
		 mainApp.changeScene("WelcomeScene");
	}
	
	@FXML
	protected void onRegisterButtonPressed() throws UserException {
		if (nameField.getText().equals("")) {
			errorLabel.setText("You must enter a valid name");
		} else if (passwordField.getText().equals("")) {
			errorLabel.setText("You must enter a valid password");
		} else if (usernameField.getText().equals("")) {
			errorLabel.setText("You must enter a valid username");
		} else if (emailField.getText().equals("")) {
			errorLabel.setText("You must enter a valid email");
		} else if (addressField.getText().equals("")) {
			errorLabel.setText("You must enter a valid address");
		} else {
			UserManager.registerUser(nameField.getText(), usernameField.getText(), passwordField.getText(), (UserType) typeField.getSelectionModel().getSelectedItem(), emailField.getText(), addressField.getText());
			mainApp.changeScene("ApplicationScene");
		}
	}

}
