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
     * List of the water availability reports.
     */
    private static ArrayList<AvailabilityReport> availabilityReportList = new ArrayList<AvailabilityReport>();

    /**
     * List of the water availability reports.
     */
    private static ArrayList<PurityReport> purityReportList = new ArrayList<PurityReport>();


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
    protected static int reportCount = 0;


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
        latestAvailiabilityReport = new AvailabilityReport(reportCount++, UserManager.getLoggedInUser().getName(), locLat, locLong, type, condition, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        reportList.add(latestAvailiabilityReport);
        availabilityReportList.add(latestAvailiabilityReport);
    }

    /**
     * Registers a new purity report and adds it to the list.
     *
     * @param locLat
     * @param locLong
     * @param overallCondition
     * @param virusPPM
     * @param contaminantPPM
     */
    public static void registerPurityReport(double locLat, double locLong, OverallCondition overallCondition, int virusPPM, int contaminantPPM) {
        latestPurityReport = new PurityReport(reportCount,
                UserManager.getLoggedInUser().getName(), locLat, locLong,
                overallCondition, virusPPM, contaminantPPM,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        reportList.add(latestPurityReport);
        purityReportList.add(latestPurityReport);
    }

    /**
     * Updates an availability report.
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
        updateReportInformation(r, rNumber, reporter, locLat, locLong, dateTime);
        r._type = type;
        r._condition = condition;
    }

    /**
     * Updates a purity report.
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

        updateReportInformation(r, rNumber, reporter,
                locLat, locLong, dateTime);
        r._overallCondition = overallCondition;
        r._virusPPM = virusPPM;
        r._contaminantPPM = contaminantPPM;
    }

	/**
	 * Private helper to update a report.
	 *
	 * @param r
	 * @param rNumber
	 * @param reporter
	 * @param locLat
	 * @param locLong
	 * @param dateTime
	 */
	private static void updateReportInformation(Report r, int rNumber,
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
     * Returns the most recent water availability report.
     *
     * @return
     */
    public static AvailabilityReport getLatestAvailabilityReport() {
        return latestAvailiabilityReport;
    }

    /**
     * Returns the most recent water purity report.
     *
     * @return
     */
    public static PurityReport getLatestPurityReport() {
        return latestPurityReport;
    }


	/**
	 * Returns the list of water reports.
	 *
	 * @return
	 */
	public static List<Report> getReportList() {
		return new ArrayList<Report>(reportList);
	}

	/**
     * Returns the list of water availability reports.
     *
     * @return
     */
    public static List<AvailabilityReport> getAvailabilityReportList() {
        return new ArrayList<AvailabilityReport>(availabilityReportList);
    }

	/**
     * Returns the list of water purity reports.
     *
     * @return
     */
    public static List<PurityReport> getPurityReportList() {
        return new ArrayList<PurityReport>(purityReportList);
    }

}