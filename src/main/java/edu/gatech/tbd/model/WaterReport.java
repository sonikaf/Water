package edu.gatech.tbd.model;

/**
 * Holds all of the data in a water availability report.
 */
public class WaterReport {

	protected int _reportNumber;
	protected String _reporter;
	protected double _locationLat;
	protected double _locationLong;
	protected WaterType _type;
	protected WaterCondition _condition;
	protected String _dateTime;

	/**
	 * Creates a new Water Report.
	 */
	protected WaterReport(int rNumber, String reporter, double locLat, double locLong, WaterType type,
			WaterCondition condition, String date) {
		_reportNumber = rNumber;
		_reporter = reporter;
		_locationLat = locLat;
		_locationLong = locLong;
		_type = type;
		_condition = condition;
		_dateTime = date;
	}

	/**
	 * Gets the report number.
	 *
	 * @return
	 */
	public int getReportNumber() {
		return _reportNumber;
	}

	/**
	 * Gets the user's name.
	 *
	 * @return
	 */
	public String getReporter() {
		return _reporter;
	}

	/**
	 * Gets the water location.
	 *
	 * @return
	 */
	public String getLocation() {
		return "(" + _locationLat + ", " + _locationLong + ")";
	}
	
	/**
	 * Gets the location's lattitude.
	 * @return
	 */
	public double getLocationLat() {
		return _locationLat;
	}
	
	/**
	 * Gets the location's longitude.
	 * @return
	 */
	public double getLocationLong() {
		return _locationLong;
	}

	/**
	 * Gets the water type.
	 *
	 * @return
	 */
	public WaterType getType() {
		return _type;
	}

	/**
	 * Gets the water condition.
	 *
	 * @return
	 */
	public WaterCondition getCondition() {
		return _condition;
	}

	/**
	 * Gets the date the report was created.
	 *
	 * @return
	 */
	public String getDateTime() {
		return _dateTime;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof WaterReport)) {
			return false;
		} else {
			WaterReport other = (WaterReport) o;
			return other._reportNumber == (_reportNumber) && other._reporter.equals(_reporter)
					&& other._locationLat == _locationLat && other._locationLong == _locationLong
					&& other._type.equals(_type) && other._condition.equals(_condition)
					&& other._dateTime.equals(_dateTime);
		}
	}
	
	@Override
	public String toString() {
		return String.format("Report %02d (%f, %f) by %s", _reportNumber, _locationLat, _locationLong, _reporter);
	}
}
