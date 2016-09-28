package edu.gatech.tbd.model;

public enum UserType {
	User(0), Worker(1), Manager(2), Administrator(3);

	private int val;

	UserType(int i) {
		val = i;
	}

	public int getLevel() {
		return val;
	}
}
