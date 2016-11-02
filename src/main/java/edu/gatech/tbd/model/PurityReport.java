package edu.gatech.tbd.model;

/**
 * Holds all of the data in a water purity report.
 */
public class PurityReport extends Report {

    /**
     * TODO Javadocs
     */
	protected OverallCondition _overallCondition;
	protected int _virusPPM;
	protected int _contaminantPPM;

	/**
	 * Creates a new Availability Report.
	 */
	protected PurityReport(int rNumber, String reporter, double locLat,
	        double locLong, OverallCondition condition, int virusPPM,
	        int contaminantPPM, String date) {
	    super(rNumber, reporter, locLat, locLong, date);
		_overallCondition = condition;
		_virusPPM = virusPPM;
		_contaminantPPM = contaminantPPM;
	}


	/**
     * @return the Overall Water Condition
     */
    public OverallCondition getOverallCondition() {
        return _overallCondition;
    }

    /**
     * @param OverallCondition the OverallCondition to set
     */
    public void setOverallCondition(OverallCondition overallCondition) {
        _overallCondition = overallCondition;
    }

    /**
     * @return the virusPPM
     */
    public int getVirusPPM() {
        return _virusPPM;
    }

    /**
     * @param virusPPM the virusPPM to set
     */
    public void set_virusPPM(int virusPPM) {
        _virusPPM = virusPPM;
    }

    /**
     * @return the contaminantPPM
     */
    public int getContaminantPPM() {
        return _contaminantPPM;
    }

    /**
     * @param contaminantPPM the contaminantPPM to set
     */
    public void set_contaminantPPM(int contaminantPPM) {
        _contaminantPPM = contaminantPPM;
    }



    @Override
	public boolean equals(Object o) {
		if (!(o instanceof PurityReport)) {
			return false;
		} else {
			PurityReport other = (PurityReport) o;
			return super.equals((Report)other)
			        && other._overallCondition.equals(_overallCondition)
			        && other._virusPPM == _virusPPM
			        && other._contaminantPPM == _contaminantPPM;
		}
	}

	@Override
	public String toString() {
		return String.format("Purity Report %02d (%f, %f) by %s", _reportNumber, _locationLat, _locationLong, _reporter);
	}

	public int getMonth() {
		if ( _dateTime.charAt(5) == 0) {
			return _dateTime.charAt(6);
		} else {
			String month = String.valueOf(_dateTime.charAt(5)) + String.valueOf(_dateTime.charAt(6));
			return Integer.parseInt(month);
		}
	}
}
