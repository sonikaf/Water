package edu.gatech.tbd.model;

/**
 * Available user types.
 */
public enum UserType {
	User(0, "User"), Worker(1, "Worker"), Manager(2, "Manager"), Administrator(3, "Administrator");

	private int val;
	private String Utype;

	UserType(int i, String type) {
		val = i;
		Utype = type;
	}

	public int getLevel() {
		return val;
	}
	
	public String toString(){
		return Utype;
	}
	
}
