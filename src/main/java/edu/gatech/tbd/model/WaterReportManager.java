package edu.gatech.tbd.model;

import java.util.ArrayList;

public class WaterReportManager {

	private static ArrayList<WaterReport> reportList = new ArrayList<WaterReport>();
	private static WaterReport LatestReport;

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
	public static void registerReport(int rNumber, String reporter, String location, WaterType type, WaterCondition condition, String dateTime) {

		LatestReport = new WaterReport(rNumber, reporter, location, type, condition, dateTime);
		reportList.add(LatestReport);
	}

	public static void updateReportInformation(WaterReport r, int rNumber, String reporter, String location, WaterType type, WaterCondition condition, String dateTime) {

		r._reportNumber = rNumber;
		r._reporter = reporter;
		r._location = location;
		r._type = type;
		r._condition = condition;
		r._dateTime = dateTime;
	}

	public static WaterReport getReportNumber(int n) {
        if (n - 1 > reportList.size()) {
        	return LatestReport;
        } else {
        	return reportList.get(n - 1);
        }
	}

	public static WaterReport getLatestReport() {
		return LatestReport;
	}

}
