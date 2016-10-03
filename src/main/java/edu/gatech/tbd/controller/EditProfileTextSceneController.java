package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import edu.gatech.tbd.model.User;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserProperty;

public class EditProfileTextSceneController extends SceneController {

	@FXML
	private TextField nameField;

	@FXML
	private Label propertyLabel;

	@FXML
	private Label promptLabel;

	@FXML
	private Label errorLabel;

	private UserProperty p;

	@FXML
	public void initialize() {

	}

	public void setInfo(User u, UserProperty p) {
		this.p = p;
		switch (p) {
		case Name:
			nameField.setText(u.getName());
			propertyLabel.setText("Name:");
			promptLabel.setText("Please enter your new Name.");
			break;
		case Address:
			nameField.setText(u.getAddress());
			propertyLabel.setText("Address:");
			promptLabel.setText("Please enter your new Address.");
			break;
		case Email:
			nameField.setText(u.getEmail());
			propertyLabel.setText("Email:");
			promptLabel.setText("Please enter your new Email.");
			break;
		case Password:
			nameField.setText(u.getPassword());
			propertyLabel.setText("Password:");
			promptLabel.setText("Please enter your new Password.");
			break;
		case Username:
			nameField.setText(u.getUsername());
			propertyLabel.setText("Username:");
			promptLabel.setText("Please enter your new Username.");
			break;
		default:
			break;
		}
	}

	@FXML
	protected void onSubmitButtonPressed() {
		try {
			User u = UserManager.getLoggedInUser();
			String name = u.getName();
			String username = u.getUsername();
			String password = u.getPassword();
			String email = u.getEmail();
			String address = u.getAddress();
			switch (p) {
			case Name:
				name = nameField.getText();
				break;
			case Address:
				address = nameField.getText();
				break;
			case Email:
				email = nameField.getText();
				break;
			case Password:
				password = nameField.getText();
				break;
			case Username:
				username = nameField.getText();
				break;
			default:
				break;
			}
			UserManager.updateUserInformation(UserManager.getLoggedInUser(), name, username, password, u.getType(),
					email, address);
			mainApp.changeScene("EditProfileScene");
		} catch (UserException e) {
			errorLabel.setText(e.getMessage());
		}
	}

	@FXML
	protected void onGoBackButtonPressed() {
		mainApp.changeScene("EditProfileScene");
	}
}
