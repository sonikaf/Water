package edu.gatech.tbd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.WaterCondition;
import edu.gatech.tbd.model.WaterReportManager;
import edu.gatech.tbd.model.WaterType;

public class UserWaterReportController extends SceneController {

	@FXML
	private static int reportCounter = 0;

	@FXML
	private int reportNumber;

	@FXML
	private String reporter;

	@FXML
	private String date;

    @FXML
    private TextField locationField;

    @FXML
    private ComboBox<WaterType> typeField;;

    @FXML
    private ComboBox<WaterCondition> conditionField;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {

    	reporter = UserManager.getLoggedInUser().getName();

    	date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());


    	List<WaterType> list = new ArrayList<WaterType>();
        list.add(WaterType.Bottled);
        list.add(WaterType.Well);
        list.add(WaterType.Stream);
        list.add(WaterType.Lake);
        list.add(WaterType.Spring);
        list.add(WaterType.Other);
        ObservableList<WaterType> obList = FXCollections.observableList(list);
        typeField.setItems(obList);
        typeField.setValue(WaterType.Bottled);

        List<WaterCondition> list2 = new ArrayList<WaterCondition>();
        list2.add(WaterCondition.Waste);
        list2.add(WaterCondition.TreatableClear);
        list2.add(WaterCondition.TreatableMuddy);
        list2.add(WaterCondition.Potable);
        ObservableList<WaterCondition> obList2 = FXCollections.observableList(list2);
        conditionField.setItems(obList2);;
        conditionField.setValue(WaterCondition.Waste);

    }

    @FXML
    protected void onHomeButtonPressed() {
    	mainApp.changeScene("ApplicationScene");
    }

    @FXML
    protected void onSubmitButtonPressed() {
    	if (locationField.getText().equals("")) {
			errorLabel.setText("You must enter a valid location");
		} else {
			reportCounter++;
	    	reportNumber = reportCounter;
			WaterReportManager.registerReport(reportNumber, reporter, locationField.getText(), typeField.getSelectionModel().getSelectedItem(), conditionField.getSelectionModel().getSelectedItem(), date);
			errorLabel.setText("You have submitted a water availability report.");
			mainApp.changeScene("ApplicationScene");
		}
    }

    @FXML
    protected void onCancelButtonPressed() {
    	errorLabel.setText("No report submitted.");
    	mainApp.changeScene("ApplicationScene");
    }
}
