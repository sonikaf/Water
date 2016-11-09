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

/**
 * Controller for the Registration Scene.
 */
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
    
    /**
     * Initializes the user type combobox with available options.
     */
	@FXML
	public void initialize() {
        List<UserType> list = new ArrayList<>();
        list.add(UserType.User);
        list.add(UserType.Worker);
        list.add(UserType.Manager);
        list.add(UserType.Administrator);
        ObservableList<UserType> obList = FXCollections.observableList(list);
        typeField.setItems(obList);
        typeField.setValue(UserType.User);
	} 
	
	/**
	 * Handler for the Go Back button.
	 */
	@FXML
	protected void onGoBackButtonPressed() {
		 mainApp.changeScene("WelcomeScene");
	}
	
	/**
     * Handler for the Register button.
     */
	@FXML
	protected void onRegisterButtonPressed() throws UserException {
		
	    // Validating that user has entered all required data 
	    if (nameField.getText().equals("")) {
			errorLabel.setText("You must enter a valid name.");
		} else if (emailField.getText().equals("")) {
			errorLabel.setText("You must enter a valid email.");
		} else if (usernameField.getText().equals("")) {
			errorLabel.setText("You must enter a valid username.");
		} else if (passwordField.getText().equals("")) {
			errorLabel.setText("You must enter a valid password.");
		}else if (addressField.getText().equals("")) {
			errorLabel.setText("You must enter a valid address.");
		}
	    
		// Register User
		try {	
		    User user = UserManager.registerUser(nameField.getText(),
		            usernameField.getText(), passwordField.getText(),
                    typeField.getSelectionModel().getSelectedItem(),
		            emailField.getText(), addressField.getText());
		    
		    // route administrator to admin screen
            // and route user, worker, manager to main app
		    routeUserOnLogin(user);
		
		// If exception is thrown, notify user that username is not available
		} catch (UserException e) {
		    errorLabel.setText("Selected username is not avaialable.");
		}
	}

}
