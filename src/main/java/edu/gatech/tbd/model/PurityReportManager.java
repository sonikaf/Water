package edu.gatech.tbd.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class manages the water purity reports.
 */
public class PurityReportManager extends WaterReportManager {
        
    /**
     * List of the water availability reports.
     */
	private static ArrayList<PurityReport> purityReportList = new ArrayList<PurityReport>();
		    
	/**
	 * The most recent water availability report.
	 */
	private static PurityReport latestPurityReport;
		

	/**
	 * Registers a new purity report and adds it to the list.
	 *
	 * @param locLat
	 * @param locLong
	 * @param overallCondition
	 * @param virusPPM
	 * @param contaminantPPM
	 */
	public static void registerAvailabilityReport(double locLat, double locLong, OverallCondition overallCondition, int virusPPM, int contaminantPPM) {
	    // NOTE: reportCount is incremented in WaterReportManager's registerReport method below
	    latestPurityReport = new PurityReport(reportCount,
	            UserManager.getLoggedInUser().getName(), locLat, locLong,
	            overallCondition, virusPPM, contaminantPPM,
	            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		purityReportList.add(latestPurityReport);
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
	 * @param overallCondition
	 * @param virusPPM
	 * @param contaminantPPM
	 * @param dateTime
	 */
	public static void updatePurityReportInformation(PurityReport r,
	        int rNumber, String reporter, double locLat, double locLong,
	        OverallCondition overallCondition, int virusPPM, int contaminantPPM,
	        String dateTime) {
	    
	    WaterReportManager.updateReportInformation(r, rNumber, reporter,
	            locLat, locLong, dateTime);
		
	    r._reportNumber = rNumber;
		r._reporter = reporter;
		r._locationLat = locLat;
		r._locationLong = locLong;
		r._overallCondition = overallCondition;
		r._virusPPM = virusPPM;
		r._contaminantPPM = contaminantPPM;
		r._dateTime = dateTime;
	}

	/**
	 * Returns the most recent water report.
	 * 
	 * @return
	 */
	public static PurityReport getLatestReport() {
		return latestPurityReport;
	}
	
	/**
	 * Returns the list of water reports.
	 * 
	 * @return
	 */
	public static List<PurityReport> getPurityReportList() {
		return new ArrayList<PurityReport>(purityReportList);
	}

}