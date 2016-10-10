package edu.gatech.tbd.model;

public class WaterReport {

	protected int _reportNumber;
	protected String _reporter;
	protected String _location;
	protected WaterType _type;
	protected WaterCondition _condition;
	protected String _dateTime;

	/**
	 * Creates a new Water Report
	 */
	protected WaterReport(int rNumber, String reporter, String location, WaterType type, WaterCondition condition, String date) {
		_reportNumber = rNumber;
		_reporter = reporter;
		_location = location;
		_type = type;
		_condition = condition;
		_dateTime = date;
	}

	/**
	 * Gets the user's username
	 *
	 * @return
	 */
	public int getReportNumber() {
		return _reportNumber;
	}

	/**
	 * Gets the user's name
	 *
	 * @return
	 */
	public String getReporter() {
		return _reporter;
	}

	/**
	 * Gets the user's email
	 *
	 * @return
	 */
	public String getLocation() {
		return _location;
	}

	/**
	 * Gets the user's address
	 *
	 * @return
	 */
	public WaterType getType() {
		return _type;
	}

	/**
	 * Gets the user's password
	 *
	 * @return
	 */
	public WaterCondition getCondition() {
		return _condition;
	}

	/**
	 * Gets the user's type
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
			return other._reportNumber == (_reportNumber) && other._reporter.equals(_reporter) && other._location.equals(_location)
					&& other._type.equals(_type) && other._condition.equals(_condition) && other._dateTime.equals(_dateTime);
		}
	}
}
