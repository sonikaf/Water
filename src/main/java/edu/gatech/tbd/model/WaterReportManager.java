package edu.gatech.tbd.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class manages the water reports.
 */
public class WaterReportManager {
    
    /**
     * List of the water reports.
     */
	private static ArrayList<AvailabilityReport> reportList = new ArrayList<AvailabilityReport>();
	
	/**
	 * The most recent water report.
	 */
	private static AvailabilityReport LatestReport;
	
	/**
	 * The number of reports submitted.
	 */
	private static int reportCount = 0;

	/**
	 * Registers a new report and adds it to the list.
	 *
	 * @param rNumber
	 * @param reporter
	 * @param location
	 * @param type
	 * @param condition
	 * @param dateTime
	 */
	public static void registerReport(double locLat, double locLong, WaterType type, WaterCondition condition) {
		LatestReport = new AvailabilityReport(reportCount++, UserManager.getLoggedInUser().getName(), locLat, locLong, type, condition, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		reportList.add(LatestReport);
	}
	
	/**
	 * Updates a reports.
	 * 
	 * @param r
	 * @param rNumber
	 * @param reporter
	 * @param locLat
	 * @param locLong
	 * @param type
	 * @param condition
	 * @param dateTime
	 */
	public static void updateReportInformation(AvailabilityReport r, int rNumber, String reporter, double locLat, double locLong, WaterType type, WaterCondition condition, String dateTime) {

		r._reportNumber = rNumber;
		r._reporter = reporter;
		r._locationLat = locLat;
		r._locationLong = locLong;
		r._type = type;
		r._condition = condition;
		r._dateTime = dateTime;
	}

	/**
	 * Returns the most recent water report.
	 * 
	 * @return
	 */
	public static AvailabilityReport getLatestReport() {
		return LatestReport;
	}
	
	/**
	 * Returns the list of water reports.
	 * 
	 * @return
	 */
	public static List<AvailabilityReport> getReportList() {
		return new ArrayList<AvailabilityReport>(reportList);
	}

}