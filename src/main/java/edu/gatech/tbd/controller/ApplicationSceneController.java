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

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.lynden.gmapsfx.*;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

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
		    System.out.println("Started Graph Method");
			validateLocation();
	        System.out.println("Validated Location");
			validateYear();
	        System.out.println("Validated Year");
			updateLineChart();
	        System.out.println("Updated Line Chart");



		} catch (LocationException e) {
			errorLabel.setText("You must enter a valid location");
			//e.printStackTrace();
		} catch (NumberFormatException e1) {
			errorLabel.setText("You must enter a valid year");
			//e1.printStackTrace();
		} catch (Exception e2) {
		    e2.printStackTrace();
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
			mapView.addMapInializedListener(this::mapInitialized);

		//if(specificMapView != null)
			//specificMapView.addMapInializedListener(() -> specificMapInitialized());


		availReportList.setOnMouseClicked((e) ->
			setCurrentAvailabilityReport(availReportList.getSelectionModel().getSelectedItem())
		);

		purityReportList.setOnMouseClicked((e) ->
            setCurrentPurityReport(purityReportList.getSelectionModel().getSelectedItem())
        );

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


	/*
	 * Marker for the currently selected water report.
	 *
	 * private static Marker curMarker;
	 */

	/**
	 * Sets the current availability report.
	 * @param r Availability report to set
	 */
	private void setCurrentAvailabilityReport(AvailabilityReport r) {

		/*
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


		/*
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
     * @param r Purity report to set
     */
	private void setCurrentPurityReport(PurityReport r) {

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

	private void mapInitialized() {
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



		/*
		MapOptions specificMapOptions = new MapOptions();

		specificMapOptions.center(gaTechLoc).mapType(MapTypeIdEnum.HYBRID).overviewMapControl(false).panControl(true)
		        .rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).zoom(16);

		specificMap = specificMapView.createMap(specificMapOptions);
		*/
	}

	private void updateLineChart() {

		List<PurityReport> purityReports = WaterReportManager.getPurityReportList();

		double locLat = Double.parseDouble(histReportView_lat.getText());
		double locLong = Double.parseDouble(histReportView_long.getText());
	    String year = histReportView_year.getText();

		List<PurityReport> yearlyPurityReportList = purityReports.stream().filter(purityReport -> purityReport.getDateTime().charAt(0) == year.charAt(0)
				&& purityReport.getDateTime().charAt(1) == year.charAt(1)
				&& purityReport.getDateTime().charAt(2) == year.charAt(2)
				&& purityReport.getDateTime().charAt(3) == year.charAt(3)
				&& purityReport.getLocationLat() == locLat
				&& purityReport.getLocationLong() == locLong).collect(Collectors.toList());

		int[] virusSumsByMonth = new int[12];
		int[] contamSumsByMonth = new int[12];
		int[] virusCountByMonth = new int[12];
		int[] contamCountByMonth = new int[12];

		for (PurityReport report: yearlyPurityReportList) {
			String month = (String.valueOf(report.getDateTime().charAt(5)) + String.valueOf(report.getDateTime().charAt(6)));
			System.out.println(month);
			int monthInt = Integer.parseInt(month);
			virusSumsByMonth[monthInt-1] += report.getVirusPPM();
			virusCountByMonth[monthInt-1]++;
			contamSumsByMonth[monthInt-1] += report.getContaminantPPM();
			contamCountByMonth[monthInt-1]++;

		}

		int[] virusAveragesByMonth = new int[12];
		int[] contamAveragesByMonth = new int[12];

		for (int i = 0; i < 12; i++) {
			if (virusCountByMonth[i] == 0) {
				virusAveragesByMonth[i] = 0;
			} else {
				virusAveragesByMonth[i] = virusSumsByMonth[i]/virusCountByMonth[i];
			}
			if (contamCountByMonth[i] == 0) {
				contamAveragesByMonth[i] = 0;
			} else {
				contamAveragesByMonth[i] = contamSumsByMonth[i]/contamCountByMonth[i];
			}
		}

        historicalReportGraph.setTitle("Historical Purity Graph");

        ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList();

        LineChart.Series<String, Number> series = new LineChart.Series<>();
        series.setName("Virus PPM");

        series.getData().add(new XYChart.Data<>("Jan", virusAveragesByMonth[0]));
        series.getData().add(new XYChart.Data<>("Feb", virusAveragesByMonth[1]));
        series.getData().add(new XYChart.Data<>("Mar", virusAveragesByMonth[2]));
        series.getData().add(new XYChart.Data<>("Apr", virusAveragesByMonth[3]));
        series.getData().add(new XYChart.Data<>("May", virusAveragesByMonth[4]));
        series.getData().add(new XYChart.Data<>("Jun", virusAveragesByMonth[5]));
        series.getData().add(new XYChart.Data<>("Jul", virusAveragesByMonth[6]));
        series.getData().add(new XYChart.Data<>("Aug", virusAveragesByMonth[7]));
        series.getData().add(new XYChart.Data<>("Sep", virusAveragesByMonth[8]));
        series.getData().add(new XYChart.Data<>("Oct", virusAveragesByMonth[9]));
        series.getData().add(new XYChart.Data<>("Nov", virusAveragesByMonth[10]));
        series.getData().add(new XYChart.Data<>("Dec", virusAveragesByMonth[11]));

        lineChartData.add(series);

        LineChart.Series<String, Number> series1 = new LineChart.Series<>();
        series1.setName("Contaminant PPM");

        series1.getData().add(new XYChart.Data<>("Jan", contamAveragesByMonth[0]));
        series1.getData().add(new XYChart.Data<>("Feb", contamAveragesByMonth[1]));
        series1.getData().add(new XYChart.Data<>("Mar", contamAveragesByMonth[2]));
        series1.getData().add(new XYChart.Data<>("Apr", contamAveragesByMonth[3]));
        series1.getData().add(new XYChart.Data<>("May", contamAveragesByMonth[4]));
        series1.getData().add(new XYChart.Data<>("Jun", contamAveragesByMonth[5]));
        series1.getData().add(new XYChart.Data<>("Jul", contamAveragesByMonth[6]));
        series1.getData().add(new XYChart.Data<>("Aug", contamAveragesByMonth[7]));
        series1.getData().add(new XYChart.Data<>("Sep", contamAveragesByMonth[8]));
        series1.getData().add(new XYChart.Data<>("Oct", contamAveragesByMonth[9]));
        series1.getData().add(new XYChart.Data<>("Nov", contamAveragesByMonth[10]));
        series1.getData().add(new XYChart.Data<>("Dec", contamAveragesByMonth[11]));

        lineChartData.add(series1);

        historicalReportGraph.setData(lineChartData);
        historicalReportGraph.createSymbolsProperty();

	}

	private void validateLocation() {
        if (histReportView_long.getText().equals("") || histReportView_lat.getText().equals("")) {
            throw new LocationException("location textfield empty");
        }
        double lat = Double.parseDouble(histReportView_lat.getText());
        double lon = Double.parseDouble(histReportView_long.getText());
        if (lat > 85 || lat < -85 || lon > 180 || lon < -180) {
            throw new LocationException("location out of bounds");
        }
    }

	private void validateYear() {
	    System.out.println("Year: " + histReportView_year.getText());
		if (histReportView_year.getText().equals("")) {
			throw new NumberFormatException("year textfield empty");
		}
		if (histReportView_year.getText().matches("[a-zA-Z]+") || histReportView_year.getText().length() < 4) {
			throw new NumberFormatException("Must enter valid year");
		}

		int year = Integer.parseInt(histReportView_year.getText());
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		List<PurityReport> reports = WaterReportManager.getPurityReportList();
		char[] yearChars = new char[4];
		reports.get(0).getDateTime().getChars(0, 3, yearChars, 0);
		//String yearString = new String(yearChars);
		//Integer firstYear = Integer.parseInt(yearString); // TODO issue here
		Integer firstYear = 2015;
		if (year < firstYear || year > currentYear) {
			throw new NumberFormatException("Year out of bounds");
		}
	}
}