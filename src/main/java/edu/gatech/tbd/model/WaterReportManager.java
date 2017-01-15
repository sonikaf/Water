package edu.gatech.tbd.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.gatech.tbd.persistence.PersistenceManager;

/**
 * This class manages the water reports.
 */
public class WaterReportManager {

    /**
     * List of all reports (Availability and Purity).
     */
    private static List<Report> reportList;

    /**
     * List of the water availability reports.
     */
    private static List<AvailabilityReport> availabilityReportList;

    /**
     * List of the water availability reports.
     */
    private static List<PurityReport> purityReportList;


    /**
     * The most recent water report (availability or purity).
     */
    private static Report latestReport;

    /**
     * The most recent water availability report.
     */
    private static AvailabilityReport latestAvailiabilityReport;

    /**
     * The most recent water purity report.
     */
    private static PurityReport latestPurityReport;


    /**
     * The number of reports submitted (availability and purity).
     */
    private static int reportCount = 1;


    /**
     * Registers a new availability report and adds it to the list.
     *
     * @param locLat report's latitude
     * @param locLong report's longitude
     * @param type report's water type
     * @param condition report's water condition
     */
    public static void registerAvailabilityReport(double locLat, double locLong, WaterType type, WaterCondition condition) {
        latestAvailiabilityReport = new AvailabilityReport(reportCount++, UserManager.getLoggedInUser().getName(), locLat, locLong, type, condition, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        reportList.add(latestAvailiabilityReport);
        availabilityReportList.add(latestAvailiabilityReport);
        try {
        	PersistenceManager.addObject(latestAvailiabilityReport);
        } catch (IOException e) {
        	System.err.println("Filesysem Error: " + e.getMessage());
        }
    }

    /**
     * Registers a new purity report and adds it to the list.
     *
     * @param locLat report's latitude
     * @param locLong report's longitude
     * @param overallCondition report's overall condition
     * @param virusPPM report's virus PPM
     * @param contaminantPPM report's contaminant PPM
     */
    public static void registerPurityReport(double locLat, double locLong, OverallCondition overallCondition, int virusPPM, int contaminantPPM) {
        latestPurityReport = new PurityReport(reportCount++,
                UserManager.getLoggedInUser().getName(), locLat, locLong,
                overallCondition, virusPPM, contaminantPPM,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        reportList.add(latestPurityReport);
        purityReportList.add(latestPurityReport);
        try {
        	PersistenceManager.addObject(latestPurityReport);
	    } catch (IOException e) {
	    	System.err.println("Filesysem Error: " + e.getMessage());
	    }
    }

    /**
     * method to test historical graph by generating reports from different months
     *
     * @param locLat report's latitude
     * @param locLong report's longitude
     * @param overallCondition report's overall condition
     * @param virusPPM report's virus PPM
     * @param contaminantPPM report's contaminant PPM
     */
    public static void testRegisterPurityReport(double locLat, double locLong, OverallCondition overallCondition, int virusPPM, int contaminantPPM, int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        latestPurityReport = new PurityReport(reportCount++,
                UserManager.getLoggedInUser().getName(), locLat, locLong,
                overallCondition, virusPPM, contaminantPPM,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(calendar));
        reportList.add(latestPurityReport);
        purityReportList.add(latestPurityReport);
        try {
        	PersistenceManager.addObject(latestPurityReport);
        } catch (IOException e) {
        	System.err.println("Filesysem Error: " + e.getMessage());
        }
    }

    /**
     * Updates an availability report.
     *
     * @param r Availability report to be updated
     * @param rNumber report's number
     * @param reporter name or user who reported it
     * @param locLat report's latitude
     * @param locLong report's longitude
     * @param type report's type
     * @param condition report's condition
     * @param dateTime report's date/time
     */
    public static void updateAvailabilityReportInformation(AvailabilityReport r, int rNumber, String reporter, double locLat, double locLong, WaterType type, WaterCondition condition, String dateTime) {

    	int oldHash = r.hashCode();

    	r._type = type;
        r._condition = condition;

        PersistenceManager.updateObject(r, oldHash);

        updateReportInformation(r, rNumber, reporter, locLat, locLong, dateTime);
    }

    /**
     * Updates a purity report.
     *
     * @param r purity report to be updated
     * @param rNumber report's number
     * @param reporter user who reported the report
     * @param locLat report's latitude
     * @param locLong report's longitude
     * @param overallCondition report's overall condition
     * @param virusPPM report's virus PPM
     * @param contaminantPPM report's contaminant PPM
     * @param dateTime report's data/ time
     */
    public static void updatePurityReportInformation(PurityReport r,
            int rNumber, String reporter, double locLat, double locLong,
            OverallCondition overallCondition, int virusPPM, int contaminantPPM,
            String dateTime) {

    	int oldHash = r.hashCode();

    	r._overallCondition = overallCondition;
        r._virusPPM = virusPPM;
        r._contaminantPPM = contaminantPPM;

        PersistenceManager.updateObject(r, oldHash);

        updateReportInformation(r, rNumber, reporter,
                locLat, locLong, dateTime);
    }

	/**
	 * Private helper to update a report.
	 *
	 * @param r report to be updated
	 * @param rNumber number of report
	 * @param reporter person who submitted the report
	 * @param locLat report's latitude
	 * @param locLong report's longitude
	 * @param dateTime report's date/time
	 */
	private static void updateReportInformation(Report r, int rNumber,
	        String reporter, double locLat, double locLong, String dateTime) {
		int oldHash = r.hashCode();

		r._reportNumber = rNumber;
		r._reporter = reporter;
		r._locationLat = locLat;
		r._locationLong = locLong;
		r._dateTime = dateTime;

		PersistenceManager.updateObject(r, oldHash);
	}


	/**
	 * Returns the most recent water report.
	 *
	 * @return latest water report
	 */
	public static Report getLatestReport() {
		return latestReport;
	}

    /**
     * Returns the most recent water availability report.
     *
     * @return latest availability report
     */
    public static AvailabilityReport getLatestAvailabilityReport() {
        return latestAvailiabilityReport;
    }

    /**
     * Returns the most recent water purity report.
     *
     * @return latest purity report
     */
    public static PurityReport getLatestPurityReport() {
        return latestPurityReport;
    }


	/**
	 * Returns the list of water reports.
	 *
	 * @return list of current water reports
	 */
	public static List<Report> getReportList() {
		return new ArrayList<>(reportList);
	}

	/**
     * Returns the list of water availability reports.
     *
     * @return list of current water availability reports
     */
    public static List<AvailabilityReport> getAvailabilityReportList() {
        return new ArrayList<>(availabilityReportList);
    }

	/**
     * Returns the list of water purity reports.
     *
     * @return list of current water purity reports
     */
    public static List<PurityReport> getPurityReportList() {
        return new ArrayList<>(purityReportList);
    }

    /**
     * loads all of our data from the disk
     */
    public static void setup() {
    	availabilityReportList = PersistenceManager.getObjects(AvailabilityReport.class);
    	purityReportList = PersistenceManager.getObjects(PurityReport.class);

    	reportList = new ArrayList<>();
    	reportList.addAll(availabilityReportList);
    	reportList.addAll(purityReportList);
    }

    /**
     * Setter for reportCount
     */
    public static void setReportCount(int count) {
        reportCount = count;
    }

    /**
     * Setter for availabilityReportList
     */
    public static void setAvailabilityReportList(List<AvailabilityReport> list) {
        availabilityReportList = list;
    }

    /**
     * Setter for purityReportList
     */
    public static void setPurityReportList(List<PurityReport> list) {
        purityReportList = list;
    }
}
