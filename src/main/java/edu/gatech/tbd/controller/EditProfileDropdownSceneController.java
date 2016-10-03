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

import java.util.List;
import javafx.collections.FXCollections;

public class EditProfileDropdownSceneController extends SceneController {

	@FXML
	private ComboBox<UserType> typeField;

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
		case Type:
			typeField.setValue(u.getType());
			List<UserType> list = new ArrayList<UserType>();
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
