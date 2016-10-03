package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.collections.FXCollections;


public class EditTypeSceneController extends SceneController {
    
    @FXML
    private ComboBox<UserType> typeField;
    
    @FXML
    public void initialize() {
        List<UserType> list = new ArrayList<UserType>();
        list.add(UserType.User);
        list.add(UserType.Worker);
        list.add(UserType.Manager);
        list.add(UserType.Administrator);
        ObservableList<UserType> obList = FXCollections.observableList(list);
        typeField.setItems(obList);
        typeField.setValue(UserManager.getLoggedInUser().getType());
    }
    
    @FXML
    protected void onSubmitButtonPressed() {
    	UserManager.updateUserInformation(UserManager.getLoggedInUser(), UserManager.getLoggedInUser().getName(), UserManager.getLoggedInUser().getUsername(), 
    			UserManager.getLoggedInUser().getPassword(), typeField.getSelectionModel().getSelectedItem(), 
    			UserManager.getLoggedInUser().getEmail(), UserManager.getLoggedInUser().getAddress());
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
