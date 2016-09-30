package edu.gatech.tbd.controller;

import java.util.ArrayList;
import java.util.List;
import edu.gatech.tbd.model.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RegistrationSceneController extends SceneController{

    @FXML
    private ComboBox<String> typeField;
    
	@FXML
	public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add(UserType.User.toString());
        list.add(UserType.Worker.toString());
        list.add(UserType.Manager.toString());
        list.add(UserType.Administrator.toString());
        ObservableList<String> obList = FXCollections.observableList(list);
        typeField.setItems(obList);
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
