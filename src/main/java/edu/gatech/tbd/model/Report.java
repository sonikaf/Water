package edu.gatech.tbd.model;

public class Report {
    
    /**
     * TODO Javadocs
     */
    protected int _reportNumber;
    protected String _reporter;
    protected double _locationLat;
    protected double _locationLong;
    protected String _dateTime;

    /**
     * Creates a new Water Report.
     */
    protected Report(int rNumber, String reporter, double locLat, double locLong, String date) {
        _reportNumber = rNumber;
        _reporter = reporter;
        _locationLat = locLat;
        _locationLong = locLong;
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
     * Gets the location's latitude.
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
     * Gets the date the report was created.
     *
     * @return
     */
    public String getDateTime() {
        return _dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Report)) {
            return false;
        } else {
            AvailabilityReport other = (AvailabilityReport) o;
            return other._reportNumber == (_reportNumber)
                    && other._reporter.equals(_reporter)
                    && other._locationLat == _locationLat
                    && other._locationLong == _locationLong
                    && other._dateTime.equals(_dateTime);
        }
    }

    @Override
    public String toString() {
        return String.format("Report %02d (%f, %f) by %s", _reportNumber, _locationLat, _locationLong, _reporter);
    }
}
