package edu.gatech.tbd.model;

public class User {

	protected String _name;
	protected String _pass;
	protected String _email;
	protected String _address;
	protected UserType _type;

	/**
	 * Creates a new user object
	 */
	protected User(String name, String pass, UserType type, String email, String address) {
		_name = name;
		_pass = pass;
		_type = type;
		_email = email;
		_address = address;
	}

	/**
	 * Gets the user's username
	 * @return
	 */
	public String getUsername() {
		return _name;
	}

	/**
	 * Gets the user's email
	 * @return
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * Gets the user's address
	 * @return
	 */
	public String getAddress() {
		return _address;
	}

	/**
	 * Checks if this user object can perform an action required by the provided
	 * UserLevel
	 * 
	 * @param minUserLevel the minimum user level required to perform the action
	 * @return if the user can perform the action
	 */
	public boolean canPerformAction(UserType minUserLevel) {
		return _type.getLevel() >= minUserLevel.getLevel();
	}
}
