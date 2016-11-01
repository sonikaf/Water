package edu.gatech.tbd.model;

/**
 * Holds all of the data in a water availability report.
 */
public class AvailabilityReport extends Report {

    /**
     * TODO Javadocs
     */
	protected WaterType _type;
	protected WaterCondition _condition;

	/**
	 * Creates a new Availability Report.
	 */
	protected AvailabilityReport(int rNumber, String reporter, double locLat,
	        double locLong, WaterType type, WaterCondition condition,
	        String date) {
	    super(rNumber, reporter, locLat, locLong, date);
		_type = type;
		_condition = condition;
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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AvailabilityReport)) {
			return false;
		} else {
			AvailabilityReport other = (AvailabilityReport) o;
			return super.equals(other) && other._type.equals(_type)
			        && other._condition.equals(_condition);
		}
	}

	@Override
	public String toString() {
		return String.format("Availability Report %02d (%f, %f) by %s", _reportNumber, _locationLat, _locationLong, _reporter);
	}
	
	
	/**
     * Method to format Availability Report for google map view.
     */
    public String createMapPopupText() {
        String s = new String(
                "<table>" +
                "<tr><td><b>Water Type: </b></td>" + "<td>" + _type.toString() + "</td>" +
                "<tr><td><b>Condition:  </b></td>" + "<td>" +_condition.toString() + "</td></tr>" +
                "<tr><td>&nbsp;</td></tr>" +
                "<tr><td><b>Lattitude:  </b></td>" + "<td>" + _locationLat + "</td><tr>" +
                "<tr><td><b>Longitude:  </b></td>" + "<td>" +_locationLong + "</td></tr>" +
                "<tr><td>&nbsp;</td></tr>" +
                "<tr><td><b>Reporter:   </b></td>" + "<td>" + _reporter + "</td></tr>" +
                "<tr><td><b>Date:       </b></td>" + "<td>" + _dateTime + "</td></tr>" +
                "</table>");
        
        return s;
    }
	
	public String toString2() {
		String returnString = new String();
		returnString = ("Reporter: " + _reporter + "\n" + getLocation() + "\nCreated: " + _dateTime + "\nWater Type: " + _type.toString() + "\nCondition: " + _condition.toString());
		return returnString;
	}
	
}
