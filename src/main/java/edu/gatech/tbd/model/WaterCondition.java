package edu.gatech.tbd.model;

/**
 * Available water conditions to choose from.
 */
public enum WaterCondition {
	Waste(0, "Waste"), TreatableClear(1, "Treatable-Clear"), TreatableMuddy(2, "Treatable-Muddy"), Potable(3, "Potable");

	private int val;
	private String Wcondition;

	WaterCondition(int i, String type) {
		val = i;
		Wcondition = type;
	}

	public int getLevel() {
		return val;
	}

	public String toString(){
		return Wcondition;
	}

}
