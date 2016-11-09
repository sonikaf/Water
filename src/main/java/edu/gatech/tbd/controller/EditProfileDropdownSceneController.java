package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import edu.gatech.tbd.model.User;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserProperty;
import edu.gatech.tbd.model.UserType;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.util.List;
import javafx.collections.FXCollections;

/**
 * Controller for the Edit Profile Dropdown Scene.
 */
public class EditProfileDropdownSceneController extends SceneController {
    
    /**
     * Combobox for selecting the user type.
     */
	@FXML
	private ComboBox<UserType> typeField;

	@FXML
	private Label propertyLabel;

	@FXML
	private Label promptLabel;

	@FXML
	Button submitButton;

	@FXML
	Button goBackButton;

	
	/**
	 * Displays error message if user does not enter data correctly.
	 */
	@FXML
	Label errorLabel;
	
	private UserProperty p;

	@FXML
	public void initialize() {

	}
	
	/**
	 * Sets the user's information with the given property data.
	 * 
	 * @param u User to be edited
	 * @param p Property of the User u to be edited
	 */
	public void setInfo(User u, UserProperty p) {
		this.p = p;
		switch (p) {
		case Type:
			typeField.setValue(u.getType());
			List<UserType> list = new ArrayList<>();
			list.add(UserType.User);
			list.add(UserType.Worker);
			list.add(UserType.Manager);
			list.add(UserType.Administrator);
			ObservableList<UserType> obList = FXCollections.observableList(list);
			typeField.setItems(obList);

			propertyLabel.setText("Type:");
			promptLabel.setText("Please select your new Type.");
			break;
		default:
			break;
		}
	}
	
	/**
	 * Handler for the Submit button.
	 */
	@FXML
	protected void onSubmitButtonPressed() {
		try {
			User u = UserManager.getLoggedInUser();
			UserType type = u.getType();
			switch (p) {
			case Type:
				type = typeField.getValue();
				break;
			default:
				break;
			}
			UserManager.updateUserInformation(UserManager.getLoggedInUser(), u.getName(), u.getUsername(),
					u.getPassword(), type, u.getEmail(), u.getAddress());
			mainApp.closePopup();
			mainApp.doPopupWindow("EditProfileScene");
		} catch (UserException e) {
			errorLabel.setText(e.getMessage());
		}
	}
	
	/**
	 * Handler for the Go Back button.
	 */
	@FXML
	protected void onGoBackButtonPressed() {
		mainApp.closePopup();
		mainApp.doPopupWindow("EditProfileScene");
	}
}
