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
     * List of all reports (Availability and Purity).
     */
    private static ArrayList<Report> reportList = new ArrayList<Report>();
    	
    /**
     * The most recent water report (availability or purity).
     */
    private static Report latestReport;
    
    /**
     * The number of reports submitted (availability and purity).
     */
    protected static int reportCount = 0;


    /**
     * Registers a new report and adds it to the list.
     */
    public static void registerReport(double locLat, double locLong) {
        latestReport = new Report(reportCount++, UserManager.getLoggedInUser().getName(), locLat, locLong, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        reportList.add(latestReport);
    }
    	
	/**
	 * Updates a report.
	 * 
	 * @param r
	 * @param rNumber
	 * @param reporter
	 * @param locLat
	 * @param locLong
	 * @param dateTime
	 */
	public static void updateReportInformation(Report r, int rNumber,
	        String reporter, double locLat, double locLong, String dateTime) {
		r._reportNumber = rNumber;
		r._reporter = reporter;
		r._locationLat = locLat;
		r._locationLong = locLong;
		r._dateTime = dateTime;
	}
	
	/**
	 * Returns the most recent water report.
	 * 
	 * @return
	 */
	public static Report getLatestReport() {
		return latestReport;
	}
	
	/**
	 * Returns the list of water reports.
	 * 
	 * @return
	 */
	public static List<Report> getReportList() {
		return new ArrayList<Report>(reportList);
	}
		
}