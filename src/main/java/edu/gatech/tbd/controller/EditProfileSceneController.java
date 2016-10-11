package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserProperty;

public class EditProfileSceneController extends SceneController{
	
    @FXML
    private Text nameField;
    
    @FXML
    private Text emailField;
    
    @FXML
    private Text usernameField;
    
    @FXML
    private Text passwordField;
    
    @FXML
    private Text addressField;
    
    @FXML
    private Text typeField;
    
    @FXML
    public void initialize() {
        nameField.setText(UserManager.getLoggedInUser().getName());
        emailField.setText(UserManager.getLoggedInUser().getEmail());
        usernameField.setText(UserManager.getLoggedInUser().getUsername());
        passwordField.setText(UserManager.getLoggedInUser().getPassword());
        addressField.setText(UserManager.getLoggedInUser().getAddress());
        typeField.setText(UserManager.getLoggedInUser().getType().toString());
	}
	
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.closePopup();
    }
    
    @FXML
    protected void onEditNameButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Name);
    }	
    
    @FXML
    protected void onEditEmailButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Email);
    }	

    @FXML
    protected void onEditUsernameButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Username);
    }	

    @FXML
    protected void onEditPasswordButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Password);
    }
    
    @FXML
    protected void onEditAddressButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Address);
    }	

    @FXML
    protected void onEditTypeButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileDropdownScene");
    	EditProfileDropdownSceneController c = (EditProfileDropdownSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Type);
    }	

}
