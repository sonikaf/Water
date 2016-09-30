package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import edu.gatech.tbd.model.UserType;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.collections.FXCollections;


public class EditTypeSceneController extends SceneController {
    
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
    protected void onSubmitButtonPressed() {
        mainApp.changeScene("EditProfileScene");
    }
    
    @FXML
    protected void onGoBackButtonPressed() {
    	mainApp.changeScene("EditProfileScene");
    }
}
