package edu.gatech.tbd.controller;

import edu.gatech.tbd.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.*;
import com.lynden.gmapsfx.javascript.object.*;

/**
 * Controller for the main application scene.
 */
public class ApplicationSceneController extends SceneController
		implements Initializable, MapComponentInitializedListener {

	@FXML
	private GoogleMapView mapView;

	private GoogleMap map;

	/**
     * The list of water reports available for selection.
     */
	@FXML
	ListView<AvailabilityReport> reportList;

	/**
     * Textfield displaying the report number of the currently viewed report.
     */
	@FXML
	TextField reportView_num;

	/**
     * Textfield displaying the user who submitted the currently viewed report.
     */
	@FXML
	TextField reportView_reporter;

	/**
     * Textfield displaying the latitude of the currently viewed report.
     */
	@FXML
	TextField reportView_lat;

	/**
     * Textfield displaying the longitude of the currently viewed report.
     */
	@FXML
	TextField reportView_long;

	/**
     * Textfield displaying the submitted date of the currently viewed report.
     */
	@FXML
	TextField reportView_date;

	/**
     * Combobox displaying the water type of the currently viewed report.
     */
	@FXML
	ComboBox<WaterType> reportView_type;

	/**
	 * Combobox displaying the water condition of the currently viewed report.
	 */
	@FXML
	ComboBox<WaterCondition> reportView_cond;

	/**
	 * Handler for the Logout button.
	 */
	@FXML
	protected void onLogoutButtonPressed() {
		UserManager.logoutUser();
		mainApp.changeScene("WelcomeScene");
	}

	/**
     * Handler for the Edit Profile button.
     */
	@FXML
	protected void onEditProfileButtonPressed() {
		mainApp.doPopupWindow("EditProfileScene");
	}

	/**
	 * Handler for the Submit Report button.
	 */
	@FXML
	protected void onSubmitReportButtonPressed() {
		mainApp.doPopupWindow("WaterReportScene");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		mapView.addMapInializedListener(this);
		System.out.print("started init for map...");
		reportList.setOnMouseClicked((e) -> {
			setCurrentReport(reportList.getSelectionModel().getSelectedItem());
		});

		createComboBoxes();
		updateReportList();
		
		// temporarily not allowing non-admins to edit reports
		if (!UserManager.getLoggedInUser().isAdmin()) {
		    reportView_num.setEditable(false);
		    reportView_reporter.setEditable(false);
		    reportView_lat.setEditable(false);
		    reportView_long.setEditable(false);
		    reportView_date.setEditable(false);
		    
		    // this greys out the comboboxes... maybe switch these to textfields
		    // for non-admins
		    reportView_type.setDisable(true);
		    reportView_cond.setDisable(true);
		}
	}

	/**
	 * Private helper method to populate combo boxes.
	 */
	private void createComboBoxes() {
		List<WaterType> list = new ArrayList<WaterType>();
        list.add(WaterType.Bottled);
        list.add(WaterType.Well);
        list.add(WaterType.Stream);
        list.add(WaterType.Lake);
        list.add(WaterType.Spring);
        list.add(WaterType.Other);
        ObservableList<WaterType> obList = FXCollections.observableList(list);
        reportView_type.setItems(obList);
        reportView_type.setValue(WaterType.Bottled);

        List<WaterCondition> list2 = new ArrayList<WaterCondition>();
        list2.add(WaterCondition.Waste);
        list2.add(WaterCondition.TreatableClear);
        list2.add(WaterCondition.TreatableMuddy);
        list2.add(WaterCondition.Potable);
        ObservableList<WaterCondition> obList2 = FXCollections.observableList(list2);
        reportView_cond.setItems(obList2);;
        reportView_cond.setValue(WaterCondition.Waste);
	}

	public void updateReportList() {
		List<AvailabilityReport> reports = AvailabilityReportManager.getAvailabilityReportList();

		reportList.setItems(FXCollections.observableList(reports));
	}

	/**
	 * Marker for the currently selected water report.
	 */
	private static Marker curMarker;

	/**
	 * Sets the current report.
	 * @param r
	 */
	public void setCurrentReport(AvailabilityReport r) {
		if(curMarker != null) {
			map.removeMarker(curMarker);
			curMarker = null;
		}
		reportView_num.setText("" + r.getReportNumber());
		reportView_reporter.setText(r.getReporter());
		reportView_lat.setText("" + r.getLocationLat());
		reportView_long.setText("" + r.getLocationLong());
		reportView_date.setText(r.getDateTime());

		reportView_type.setValue(r.getType());
		reportView_cond.setValue(r.getCondition());

		LatLong waterLoc = new LatLong(r.getLocationLat(), r.getLocationLong());
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(waterLoc);

		curMarker = new Marker(markerOptions);

		map.addMarker(curMarker);
		map.setCenter(waterLoc);
		map.setZoom(16);
	}

	@Override
	public void mapInitialized() {
		System.out.print("map done loading, configuring...");

		LatLong gatechLocation = new LatLong(33.774804, -84.3976288);

		MapOptions mapOptions = new MapOptions();

		mapOptions.center(gatechLocation).mapType(MapTypeIdEnum.HYBRID).overviewMapControl(false).panControl(true)
				.rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).zoom(16);

		map = mapView.createMap(mapOptions);

		System.out.println("done!");
	}
}