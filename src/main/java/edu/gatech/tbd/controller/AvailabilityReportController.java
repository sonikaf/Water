package edu.gatech.tbd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.tbd.model.LocationException;
import edu.gatech.tbd.model.WaterCondition;
import edu.gatech.tbd.model.WaterReportManager;
import edu.gatech.tbd.model.WaterType;

/**
 * Controller for the Water Report Scene.
 */
public class AvailabilityReportController extends ReportController {

    @FXML
    private ComboBox<WaterType> typeField;

    @FXML
    private ComboBox<WaterCondition> conditionField;

    @FXML
    public void initialize() {

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

    @Override
    @FXML
    protected void onSubmitButtonPressed() {
		try {
			validateLocation();
			double locLat = Double.parseDouble(locationLatField.getText());
			double locLong = Double.parseDouble(locationLongField.getText());
				
			WaterReportManager.registerAvailabilityReport(locLat, locLong,
			        typeField.getSelectionModel().getSelectedItem(),
			        conditionField.getSelectionModel().getSelectedItem());
			
			errorLabel.setText("You have submitted a water availability report.");
			
			((ApplicationSceneController)mainApp.getCurrentController())
			    .updateAvailabilityReportList();
			
			mainApp.closePopup();
		} catch (LocationException e) {
			errorLabel.setText("You must enter a valid location");
			//e.printStackTrace();
		}
    	((ApplicationSceneController)mainApp.getCurrentController())
    	    .populateAvailabilityMap();
    }

}