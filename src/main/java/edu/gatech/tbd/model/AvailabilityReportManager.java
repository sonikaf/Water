package edu.gatech.tbd.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class manages the water availability reports.
 */
public class AvailabilityReportManager extends WaterReportManager {
        
    /**
     * List of the water availability reports.
     */
	private static ArrayList<AvailabilityReport> availabilityReportList = new ArrayList<AvailabilityReport>();
		    
	/**
	 * The most recent water availability report.
	 */
	private static AvailabilityReport latestAvailiabilityReport;
		

	/**
	 * Registers a new availability report and adds it to the list.
	 *
	 * @param rNumber
	 * @param reporter
	 * @param location
	 * @param type
	 * @param condition
	 * @param dateTime
	 */
	public static void registerAvailabilityReport(double locLat, double locLong, WaterType type, WaterCondition condition) {
	    // NOTE: reportCount is incremented in WaterReportManager's registerReport method
	    latestAvailiabilityReport = new AvailabilityReport(reportCount, UserManager.getLoggedInUser().getName(), locLat, locLong, type, condition, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		availabilityReportList.add(latestAvailiabilityReport);
		WaterReportManager.registerReport(locLat, locLong);
	}
		
	/**
	 * Updates a report.
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
	public static void updateAvailabilityReportInformation(AvailabilityReport r, int rNumber, String reporter, double locLat, double locLong, WaterType type, WaterCondition condition, String dateTime) {
	    
	    WaterReportManager.updateReportInformation(r, rNumber, reporter, locLat, locLong, dateTime);
		
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
		return latestAvailiabilityReport;
	}
	
	/**
	 * Returns the list of water reports.
	 * 
	 * @return
	 */
	public static List<AvailabilityReport> getAvailabilityReportList() {
		return new ArrayList<AvailabilityReport>(availabilityReportList);
	}

}