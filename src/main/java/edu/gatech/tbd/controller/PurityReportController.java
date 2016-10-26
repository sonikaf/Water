package edu.gatech.tbd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.tbd.model.LocationException;
import edu.gatech.tbd.model.OverallCondition;
import edu.gatech.tbd.model.WaterReportManager;

/**
 * Controller for the Water Report Scene.
 */
public class PurityReportController extends ReportController {

    @FXML
    private ComboBox<OverallCondition> oConditionBox;

    @FXML
    private TextField virusppmField;

    @FXML
    private TextField contaminantppmField;

    @FXML
    public void initialize() {

    	List<OverallCondition> list = new ArrayList<OverallCondition>();
        list.add(OverallCondition.Safe);
        list.add(OverallCondition.Treatable);
        list.add(OverallCondition.Unsafe);
        ObservableList<OverallCondition> obList = FXCollections.observableList(list);
        oConditionBox.setItems(obList);
        oConditionBox.setValue(OverallCondition.Safe);
    }

    @Override
    @FXML
    protected void onSubmitButtonPressed() {
		try {
	        validateLocation();
			double locLat = Double.parseDouble(locationLatField.getText());
			double locLong = Double.parseDouble(locationLongField.getText());
			
			validatePPM();
	        int virusPPM = Integer.parseInt(virusppmField.getText());
	        int contPPM = Integer.parseInt(contaminantppmField.getText());

			WaterReportManager.registerPurityReport(locLat, locLong,
			        oConditionBox.getSelectionModel().getSelectedItem(),
			        virusPPM, contPPM);
			
			errorLabel.setText("You have submitted a water purity report.");
			((ApplicationSceneController)mainApp.getCurrentController())
			    .updatePurityReportList();
			
			mainApp.closePopup();
		} catch (LocationException e) {
			errorLabel.setText("You must enter a valid location");
			//e.printStackTrace();
		} catch (NumberFormatException e1) {
		    errorLabel.setText("You must enter valid PPM numbers");
		    //e1.printStackTrace();
		}
    }

    /**
     * Checks that the entered virus PPM and contaminant PPM are appropriate
     * values.
     *
     */
    private void validatePPM() {
        if (virusppmField.getText().equals("")
                || contaminantppmField.getText().equals("")) {
            throw new NumberFormatException("textfields empty");
        }

    }
}