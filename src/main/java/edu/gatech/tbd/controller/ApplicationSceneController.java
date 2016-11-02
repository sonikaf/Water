package edu.gatech.tbd.controller;

import edu.gatech.tbd.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lynden.gmapsfx.*;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

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
	private LineChart<String,Number> historicalReportGraph;

	@FXML
	protected TextField histReportView_lat;

	@FXML
	protected TextField histReportView_long;

	@FXML
	protected TextField histReportView_year;

	 @FXML
	 protected Label errorLabel;

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

	@FXML
	protected void onGraphHistReportButtonPressed() {

		try {
			validateLocation();
			validateYear();
			updateLineChart();


		} catch (LocationException e) {
			errorLabel.setText("You must enter a valid location");
			//e.printStackTrace();
		} catch (NumberFormatException e1) {
			errorLabel.setText("You must enter a valid year");
			//e.printStackTrace();
		}

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

	public void updateLineChart() {

		List<PurityReport> purityReports = WaterReportManager.getPurityReportList();

		double locLat = Double.parseDouble(histReportView_lat.getText());
		double locLong = Double.parseDouble(histReportView_long.getText());
	    String year = histReportView_year.getText();

		List<PurityReport> yearlyPurityReportList = new ArrayList<PurityReport>();

		for (int i = 0; i < purityReports.size(); i++) {
			if (purityReports.get(i).getDateTime().charAt(0) == year.charAt(0)
					&& purityReports.get(i).getDateTime().charAt(1) == year.charAt(1)
					&& purityReports.get(i).getDateTime().charAt(2) == year.charAt(2)
					&& purityReports.get(i).getDateTime().charAt(3) == year.charAt(3)
					&& purityReports.get(i).getLocationLat() == locLat
					&& purityReports.get(i).getLocationLong() == locLong) {
				yearlyPurityReportList.add(purityReports.get(i));
			}
		}

		List<PurityReport> janPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> febPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> marchPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> aprilPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> mayPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> junePurityReports = new ArrayList<PurityReport>();
		List<PurityReport> julyPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> augPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> sepPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> octPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> novPurityReports = new ArrayList<PurityReport>();
		List<PurityReport> decPurityReports = new ArrayList<PurityReport>();

		for (int i = 0; i < yearlyPurityReportList.size(); i++) {
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '1') {
				janPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '2') {
				febPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '3') {
				marchPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '4') {
				aprilPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '4') {
				aprilPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '5') {
				mayPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '6') {
				junePurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '7') {
				julyPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '8') {
				augPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '0' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '9') {
				sepPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '1' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '0') {
				octPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '1' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '1') {
				novPurityReports.add(yearlyPurityReportList.get(i));
			}
			if (yearlyPurityReportList.get(i).getDateTime().charAt(5) == '1' && yearlyPurityReportList.get(i).getDateTime().charAt(6) == '2') {
				decPurityReports.add(yearlyPurityReportList.get(i));
			}
		}

        int avgJanVirusPPM;
        int avgJanContaminantPPM;
        int janVirusSum = 0;
        int janContamSum = 0;

        if (janPurityReports.size() == 0) {
        	avgJanVirusPPM = 0;
        	avgJanContaminantPPM = 0;
        } else {
        	for (int i = 0; i < janPurityReports.size(); i++) {
        		janVirusSum = janVirusSum + janPurityReports.get(i).getVirusPPM();
        		janContamSum = janContamSum + janPurityReports.get(i).getContaminantPPM();
        	}

        	avgJanVirusPPM = janVirusSum / janPurityReports.size();
        	avgJanContaminantPPM = janContamSum / janPurityReports.size();
        }

        int avgFebVirusPPM;
        int avgFebContaminantPPM;
        int febVirusSum = 0;
        int febContamSum = 0;

        if (febPurityReports.size() == 0) {
        	avgFebVirusPPM = 0;
        	avgFebContaminantPPM = 0;
        } else {
        	for (int i = 0; i < febPurityReports.size(); i++) {
        		febVirusSum = febVirusSum + febPurityReports.get(i).getVirusPPM();
        		febContamSum = febContamSum + febPurityReports.get(i).getContaminantPPM();
        	}

        	avgFebVirusPPM = febVirusSum / febPurityReports.size();
        	avgFebContaminantPPM = febContamSum / febPurityReports.size();
        }

        int avgMarchVirusPPM;
        int avgMarchContaminantPPM;
        int marchVirusSum = 0;
        int marchContamSum = 0;

        if (marchPurityReports.size() == 0) {
        	avgMarchVirusPPM = 0;
        	avgMarchContaminantPPM = 0;
        } else {
        	for (int i = 0; i < marchPurityReports.size(); i++) {
        		marchVirusSum = marchVirusSum + marchPurityReports.get(i).getVirusPPM();
        		marchContamSum = marchContamSum + marchPurityReports.get(i).getContaminantPPM();
        	}

        	avgMarchVirusPPM = marchVirusSum / marchPurityReports.size();
        	avgMarchContaminantPPM = marchContamSum / marchPurityReports.size();
        }

        int avgAprilVirusPPM;
        int avgAprilContaminantPPM;
        int aprilVirusSum = 0;
        int aprilContamSum = 0;

        if (aprilPurityReports.size() == 0) {
        	avgAprilVirusPPM = 0;
        	avgAprilContaminantPPM = 0;
        } else {
        	for (int i = 0; i < aprilPurityReports.size(); i++) {
        		aprilVirusSum = aprilVirusSum + aprilPurityReports.get(i).getVirusPPM();
        		aprilContamSum = aprilContamSum + aprilPurityReports.get(i).getContaminantPPM();
        	}

        	avgAprilVirusPPM = aprilVirusSum / aprilPurityReports.size();
        	avgAprilContaminantPPM = aprilContamSum / aprilPurityReports.size();
        }

        int avgMayVirusPPM;
        int avgMayContaminantPPM;
        int mayVirusSum = 0;
        int mayContamSum = 0;

        if (mayPurityReports.size() == 0) {
        	avgMayVirusPPM = 0;
        	avgMayContaminantPPM = 0;
        } else {
        	for (int i = 0; i < mayPurityReports.size(); i++) {
        		mayVirusSum = mayVirusSum + mayPurityReports.get(i).getVirusPPM();
        		mayContamSum = mayContamSum + mayPurityReports.get(i).getContaminantPPM();
        	}

        	avgMayVirusPPM = mayVirusSum / mayPurityReports.size();
        	avgMayContaminantPPM = mayContamSum / mayPurityReports.size();
        }

        int avgJuneVirusPPM;
        int avgJuneContaminantPPM;
        int juneVirusSum = 0;
        int juneContamSum = 0;

        if (junePurityReports.size() == 0) {
        	avgJuneVirusPPM = 0;
        	avgJuneContaminantPPM = 0;
        } else {
        	for (int i = 0; i < junePurityReports.size(); i++) {
        		juneVirusSum = juneVirusSum + junePurityReports.get(i).getVirusPPM();
        		juneContamSum = juneContamSum + junePurityReports.get(i).getContaminantPPM();
        	}

        	avgJuneVirusPPM = juneVirusSum / junePurityReports.size();
        	avgJuneContaminantPPM = juneContamSum / junePurityReports.size();
        }

        int avgJulyVirusPPM;
        int avgJulyContaminantPPM;
        int julyVirusSum = 0;
        int julyContamSum = 0;

        if (julyPurityReports.size() == 0) {
        	avgJulyVirusPPM = 0;
        	avgJulyContaminantPPM = 0;
        } else {
        	for (int i = 0; i < julyPurityReports.size(); i++) {
        		julyVirusSum = julyVirusSum + julyPurityReports.get(i).getVirusPPM();
        		julyContamSum = julyContamSum + julyPurityReports.get(i).getContaminantPPM();
        	}

        	avgJulyVirusPPM = julyVirusSum / julyPurityReports.size();
        	avgJulyContaminantPPM = julyContamSum / julyPurityReports.size();
        }

        int avgAugustVirusPPM;
        int avgAugustContaminantPPM;
        int augVirusSum = 0;
        int augContamSum = 0;

        if (augPurityReports.size() == 0) {
        	avgAugustVirusPPM = 0;
        	avgAugustContaminantPPM = 0;
        } else {
        	for (int i = 0; i < augPurityReports.size(); i++) {
        		augVirusSum = augVirusSum + augPurityReports.get(i).getVirusPPM();
        		augContamSum = augContamSum + augPurityReports.get(i).getContaminantPPM();
        	}

        	avgAugustVirusPPM = augVirusSum / augPurityReports.size();
        	avgAugustContaminantPPM = augContamSum / augPurityReports.size();
        }

        int avgSeptemberVirusPPM;
        int avgSeptemberContaminantPPM;
        int sepVirusSum = 0;
        int sepContamSum = 0;

        if (sepPurityReports.size() == 0) {
        	avgSeptemberVirusPPM = 0;
        	avgSeptemberContaminantPPM = 0;
        } else {
        	for (int i = 0; i < sepPurityReports.size(); i++) {
        		sepVirusSum = sepVirusSum + sepPurityReports.get(i).getVirusPPM();
        		sepContamSum = sepContamSum + sepPurityReports.get(i).getContaminantPPM();
        	}

        	avgSeptemberVirusPPM = sepVirusSum / sepPurityReports.size();
        	avgSeptemberContaminantPPM = sepContamSum / sepPurityReports.size();
        }

        int avgOctoberVirusPPM;
        int avgOctoberContaminantPPM;
        int octVirusSum = 0;
        int octContamSum = 0;

        if (octPurityReports.size() == 0) {
        	avgOctoberVirusPPM = 0;
        	avgOctoberContaminantPPM = 0;
        } else {
        	for (int i = 0; i < octPurityReports.size(); i++) {
        		octVirusSum = octVirusSum + octPurityReports.get(i).getVirusPPM();
        		octContamSum = octContamSum + octPurityReports.get(i).getContaminantPPM();
        	}

        	avgOctoberVirusPPM = octVirusSum / octPurityReports.size();
        	avgOctoberContaminantPPM = octContamSum / octPurityReports.size();
        }

        int avgNovemberVirusPPM;
        int avgNovemberContaminantPPM;
        int novVirusSum = 0;
        int novContamSum =0;

        if (novPurityReports.size() == 0) {
        	avgNovemberVirusPPM = 0;
        	avgNovemberContaminantPPM = 0;
        } else {
        	for (int i = 0; i < novPurityReports.size(); i++) {
        		novVirusSum = novVirusSum + novPurityReports.get(i).getVirusPPM();
        		novContamSum = novContamSum + novPurityReports.get(i).getContaminantPPM();
        	}

        	avgNovemberVirusPPM = marchVirusSum / novPurityReports.size();
        	avgNovemberContaminantPPM = novContamSum / novPurityReports.size();
        }

        int avgDecemberVirusPPM;
        int avgDecemberContaminantPPM;
        int decVirusSum = 0;
        int decContamSum = 0;

        if ( decPurityReports.size() == 0) {
        	avgDecemberVirusPPM = 0;
        	avgDecemberContaminantPPM = 0;
        } else {
        	for (int i = 0; i < decPurityReports.size(); i++) {
        		decVirusSum = decVirusSum + decPurityReports.get(i).getVirusPPM();
        		decContamSum = decContamSum + decPurityReports.get(i).getContaminantPPM();
        	}

        	avgDecemberVirusPPM = decVirusSum / decPurityReports.size();
        	avgDecemberContaminantPPM = decContamSum / decPurityReports.size();
        }

        historicalReportGraph.setTitle("Historical Purity Graph");

        ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList();

        LineChart.Series<String, Number> series = new LineChart.Series<String, Number>();
        series.setName("Virus PPM");

        series.getData().add(new XYChart.Data<String, Number>("Jan", avgJanVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Feb", avgFebVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Mar", avgMarchVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Apr", avgAprilVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("May", avgMayVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Jun", avgJuneVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Jul", avgJulyVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Aug", avgAugustVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Sep", avgSeptemberVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Oct", avgOctoberVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Nov", avgNovemberVirusPPM));
        series.getData().add(new XYChart.Data<String, Number>("Dec", avgDecemberVirusPPM));

        lineChartData.add(series);

        LineChart.Series<String, Number> series1 = new LineChart.Series<String, Number>();
        series1.setName("Contaminent PPM");

        series1.getData().add(new XYChart.Data<String, Number>("Jan", avgJanContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Feb", avgFebContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Mar", avgMarchContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Apr", avgAprilContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("May", avgMayContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Jun", avgJuneContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Jul", avgJulyContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Aug", avgAugustContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Sep", avgSeptemberContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Oct", avgOctoberContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Nov", avgNovemberContaminantPPM));
        series1.getData().add(new XYChart.Data<String, Number>("Dec", avgDecemberContaminantPPM));

        lineChartData.add(series1);

        historicalReportGraph.setData(lineChartData);
        historicalReportGraph.createSymbolsProperty();


	}

	protected void validateLocation() {
        if (histReportView_long.getText().equals("") || histReportView_lat.getText().equals("")) {
            throw new LocationException("location textfield empty");
        }
        double lat = Double.parseDouble(histReportView_lat.getText());
        double lon = Double.parseDouble(histReportView_long.getText());
        if (lat > 85 || lat < -85 || lon > 180 || lon < -180) {
            throw new LocationException("location out of bounds");
        }
    }

	protected void validateYear() {
		if (histReportView_year.getText().equals("")) {
			throw new NumberFormatException("year textfield empty");
		}
		if (histReportView_year.getText().matches("[a-zA-Z]+") || histReportView_year.getText().length() < 4) {
			throw new NumberFormatException("Must enter valid year");
		}
		int year = Integer.parseInt(histReportView_year.getText());
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		List<Report> reports = WaterReportManager.getReportList();
		char[] yearChars = new char[4];
		reports.get(0).getDateTime().getChars(0, 3, yearChars, 0);
		String yearString = new String(yearChars);
		Integer firstYear = Integer.parseInt(yearString);
		if (year < firstYear || year > currentYear) {
			throw new NumberFormatException("Year out of bounds");
		}
	}
}