package edu.gatech.tbd.model;

public enum WaterType {
	Bottled(0, "Bottled"), Well(1, "Well"), Stream(2, "Stream"), Lake(3, "Lake"), Spring(4, "Spring"), Other(5, "Other");

	private int val;
	private String Wtype;

	WaterType(int i, String type) {
		val = i;
		Wtype = type;
	}

	public int getLevel() {
		return val;
	}

	public String toString(){
		return Wtype;
	}

}
