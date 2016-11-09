package edu.gatech.tbd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import edu.gatech.tbd.model.LocationException;

/**
 * Abstract base Controller for the submit Availability and Purity Report Scenes.
 */
abstract class ReportController extends SceneController {

    @FXML
    protected TextField locationLatField;

    @FXML
    protected TextField locationLongField;

    @FXML
    protected Label errorLabel;

    @FXML
    public void initialize() {

    }

    /**
     * Handler for the Cancel button.
     */
    @FXML
    protected void onCancelButtonPressed() {
    	mainApp.closePopup();
    }

    /**
     * Handler for the Submit button.
     */
    @FXML
    abstract protected void onSubmitButtonPressed();

    /**
     * Checks that the entered lattitude and logitude are correct values.
     *
     */
    void validateLocation() {
        if (locationLongField.getText().equals("") || locationLatField.getText().equals("")) {
            throw new LocationException("textfields empty");
        }
        double lat = Double.parseDouble(locationLatField.getText());
        double lon = Double.parseDouble(locationLongField.getText());
        if (lat > 85 || lat < -85 || lon > 180 || lon < -180) {
            throw new LocationException("location out of bounds");
        }
    }
}