package edu.gatech.tbd.controller;

import edu.gatech.tbd.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import netscape.javascript.JSObject;

import java.util.List;

import com.lynden.gmapsfx.*;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

/**
 * Controller for the main application scene.
 */
public class ApplicationSceneController extends SceneController {

	@FXML
	private GoogleMapView mapView;

	private GoogleMap map;


	/*
	 * named the little map on Report List screen specificMap, and commented out
	 * the code until we can figure out how to get two maps working at same time
	 * then we can add specific map back to the scene the same way alex had it
	 *
	 */
	//private GoogleMapView specificMapView;
	//private GoogleMap specificMap;

	@FXML
	ListView<AvailabilityReport> availReportList;
	
	@FXML
	TextField availReportView_num;

	@FXML
	TextField availReportView_reporter;

	@FXML
	TextField availReportView_lat;

	@FXML
	TextField availReportView_long;

	@FXML
	TextField availReportView_date;

	@FXML
	TextField availReportView_type;

	@FXML
	TextField availReportView_cond;

	    
    @FXML
    ListView<PurityReport> purityReportList;

    @FXML
    TextField purityReportView_num;

    @FXML
    TextField purityReportView_reporter;

    @FXML
    TextField purityReportView_lat;

    @FXML
    TextField purityReportView_long;

    @FXML
    TextField purityReportView_date;

    @FXML
    TextField purityReportView_ocond;
    
    @FXML
    TextField purityReportView_virusppm;
    
    @FXML
    TextField purityReportView_contppm;

	
	@FXML
	TabPane tabPane;

    @FXML
    Tab availabilityMapTab;

    @FXML
    Tab reportListTab;
    
    @FXML
    Tab historicalReportTab;
    
    @FXML
    HBox submitReportButtonHBox;

    @FXML
    Button submitPurityReportButton;


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
	 * Handler for the Submit Availability Report button.
	 */
	@FXML
	protected void onSubmitAvailabilityReportButtonPressed() {
		mainApp.doPopupWindow("AvailabilityReportScene");
	}
	
	/**
     * Handler for the Submit Purity Report button.
     */
	@FXML
	protected void onSubmitPurityReportButtonPressed() {
	    mainApp.doPopupWindow("PurityReportScene");
	}

	public void initialize() {
		// these might be null depending on which tabs are open
		if(mapView != null)
			mapView.addMapInializedListener(() -> mapInitialized());
		
		//if(specificMapView != null)
			//specificMapView.addMapInializedListener(() -> specificMapInitialized());
		

		availReportList.setOnMouseClicked((e) -> {
			setCurrentAvailabilityReport(availReportList.getSelectionModel().getSelectedItem());
		});
		
		purityReportList.setOnMouseClicked((e) -> {
            setCurrentPurityReport(purityReportList.getSelectionModel().getSelectedItem());
        });

		//createComboBoxes();
		updateAvailabilityReportList();
		updatePurityReportList();

		// if user has UserType user
		if (UserManager.getLoggedInUser().getType() != UserType.Manager) {
		    tabPane.getTabs().remove(historicalReportTab);
		    tabPane.getTabs().remove(reportListTab);
		    
		    if (UserManager.getLoggedInUser().getType() == UserType.User) {
		        submitReportButtonHBox.getChildren()
		            .remove(submitPurityReportButton);
		    }
		}
	}

	/**
	 * Private helper method to populate combo boxes.
	 */
	/*
	private void createComboBoxes() {
	    List<WaterType> list = new ArrayList<WaterType>();
        list.add(WaterType.Bottled);
        list.add(WaterType.Well);
        list.add(WaterType.Stream);
        list.add(WaterType.Lake);
        list.add(WaterType.Spring);
        list.add(WaterType.Other);
        ObservableList<WaterType> obList = FXCollections.observableList(list);
        availReportView_type.setItems(obList);
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
	*/
	/**
	 * TODO javadocs
	 */
	public void updateAvailabilityReportList() {
		List<AvailabilityReport> availReports = WaterReportManager.getAvailabilityReportList();
		availReportList.setItems(FXCollections.observableList(availReports));
	}

	/**
     * TODO javadocs
     */
	public void updatePurityReportList() {
        List<PurityReport> purityReports = WaterReportManager.getPurityReportList();
        purityReportList.setItems(FXCollections.observableList(purityReports));
    }


	/**
	 * Marker for the currently selected water report.
	 *
	private static Marker curMarker;
	 */

	/**
	 * Sets the current availability report.
	 * @param r
	 */
	public void setCurrentAvailabilityReport(AvailabilityReport r) {

		/**
		if(curMarker != null) {
			specificMap.removeMarker(curMarker);
			curMarker = null;
		}
		*/
		availReportView_num.setText("" + r.getReportNumber());
		availReportView_reporter.setText(r.getReporter());
		availReportView_lat.setText("" + r.getLocationLat());
		availReportView_long.setText("" + r.getLocationLong());
		availReportView_date.setText(r.getDateTime());
		availReportView_type.setText("" + r.getType());
		availReportView_cond.setText("" + r.getCondition());


		/**
		LatLong waterLoc = new LatLong(r.getLocationLat(), r.getLocationLong());
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(waterLoc);

		curMarker = new Marker(markerOptions);

		specificMap.addMarker(curMarker);
		specificMap.setCenter(waterLoc);
		specificMap.setZoom(16);
		*/
	}
	
	/**
     * Sets the current purity report.
     * @param r
     */
    public void setCurrentPurityReport(PurityReport r) {

        purityReportView_num.setText("" + r.getReportNumber());
        purityReportView_reporter.setText(r.getReporter());
        purityReportView_lat.setText("" + r.getLocationLat());
        purityReportView_long.setText("" + r.getLocationLong());
        purityReportView_date.setText(r.getDateTime());
        purityReportView_ocond.setText("" + r.getOverallCondition());
        purityReportView_virusppm.setText("" + r.getVirusPPM());
        purityReportView_contppm.setText("" + r.getContaminantPPM());

    }
	
	public void specificMapInitialized() {
		LatLong center = new LatLong(0, 0);
		//LatLong gaTechLoc = new LatLong(33.774804, -84.3976288);

		MapOptions mapOptions = new MapOptions();

		mapOptions.center(center).mapType(MapTypeIdEnum.HYBRID).overviewMapControl(false).panControl(true)
				.rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).zoom(2);

		
		//specificMap = specificMapView.createMap(mapOptions);

		populateAvailabilityMap();
	}

	public void mapInitialized() {
		LatLong center = new LatLong(0, 0);
		//LatLong gaTechLoc = new LatLong(33.774804, -84.3976288);

		MapOptions mapOptions = new MapOptions();

		mapOptions.center(center).mapType(MapTypeIdEnum.HYBRID).overviewMapControl(false).panControl(true)
				.rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).zoom(2);

		
		map = mapView.createMap(mapOptions);

		populateAvailabilityMap();
	}

	public void populateAvailabilityMap() {
		List<AvailabilityReport> reports = WaterReportManager.getAvailabilityReportList();

		for (AvailabilityReport r: reports) {
			MarkerOptions markerOptions = new MarkerOptions();
			LatLong loc = new LatLong(r.getLocationLat(), r.getLocationLong());

			markerOptions.position(loc).visible(Boolean.TRUE).title("Report Number " + r.getReportNumber());
			Marker marker = new Marker(markerOptions);

			map.addUIEventHandler(marker,
					UIEventType.click,
					(JSObject obj) -> {
						InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
						infoWindowOptions.content(r.createMapPopupText());

						InfoWindow window = new InfoWindow(infoWindowOptions);
						window.open(map, marker);});
			map.addMarker(marker);

		}



		/**
		MapOptions specificMapOptions = new MapOptions();

		specificMapOptions.center(gaTechLoc).mapType(MapTypeIdEnum.HYBRID).overviewMapControl(false).panControl(true)
		        .rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).zoom(16);

		specificMap = specificMapView.createMap(specificMapOptions);
		*/
	}
}