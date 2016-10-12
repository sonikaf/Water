package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserProperty;

/**
 * Controller for the Edit Profile Scene.
 */
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
    
    /**
     * Initializes the fields with the logged-in user's data.
     */
    @FXML
    public void initialize() {
        nameField.setText(UserManager.getLoggedInUser().getName());
        emailField.setText(UserManager.getLoggedInUser().getEmail());
        usernameField.setText(UserManager.getLoggedInUser().getUsername());
        passwordField.setText(UserManager.getLoggedInUser().getPassword());
        addressField.setText(UserManager.getLoggedInUser().getAddress());
        typeField.setText(UserManager.getLoggedInUser().getType().toString());
	}
	
    /**
     * Handler for the Go Back button.
     */
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.closePopup();
    }
    
    /**
     * Handler for the Edit Name button.
     */
    @FXML
    protected void onEditNameButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Name);
    }	
    
    /**
     * Handler for the Edit Email button.
     */
    @FXML
    protected void onEditEmailButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Email);
    }	

    /**
     * Handler for the Edit Username button.
     */
    @FXML
    protected void onEditUsernameButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Username);
    }	
    
    /**
     * Handler for the Edit Password button.
     */
    @FXML
    protected void onEditPasswordButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Password);
    }
    
    /**
     * Handler for the Edit Address button.
     */
    @FXML
    protected void onEditAddressButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileTextScene");
    	EditProfileTextSceneController c = (EditProfileTextSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Address);
    }	
    
    /**
     * Handler for the Edit Type button.
     */
    @FXML
    protected void onEditTypeButtonPressed() {
    	mainApp.closePopup();
    	mainApp.doPopupWindow("EditProfileDropdownScene");
    	EditProfileDropdownSceneController c = (EditProfileDropdownSceneController)mainApp.getPopupController();
    	c.setInfo(UserManager.getLoggedInUser(), UserProperty.Type);
    }	

}
