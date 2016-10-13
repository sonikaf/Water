package edu.gatech.tbd.model;

public class User {

	protected String _name;
	protected String _pass;
	protected String _email;
	protected String _address;
	protected UserType _type;
	protected String _username;

	/**
	 * Creates a new user object.
	 */
	protected User(String name, String uname, String pass, UserType type, String email, String address) {
		_name = name;
		_username = uname;
		_pass = pass;
		_type = type;
		_email = email;
		_address = address;
	}

	/**
	 * Gets the user's username.
	 * 
	 * @return
	 */
	public String getUsername() {
		return _username;
	}

	/**
	 * Gets the user's name.
	 * 
	 * @return
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Gets the user's email.
	 * 
	 * @return
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * Gets the user's address.
	 * 
	 * @return
	 */
	public String getAddress() {
		return _address;
	}

	/**
	 * Gets the user's password.
	 * 
	 * @return
	 */
	public String getPassword() {
		return _pass;
	}

	/**
	 * Gets the user's type.
	 * 
	 * @return
	 */
	public UserType getType() {
		return _type;
	}
	
	/**
     * Returns true if the user is an Administrator, false otherwise.
     */
     public boolean isAdmin() {
         return _type == UserType.Administrator;
     }

     
	/**
	 * Checks if this user object can perform an action required by the provided
	 * UserLevel.
	 * 
	 * @param minUserLevel
	 *            the minimum user level required to perform the action
	 * @return true if the user can perform the action, false otherwise
	 */
	public boolean canPerformAction(UserType minUserLevel) {
		return _type.getLevel() >= minUserLevel.getLevel();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User)) {
			return false;
		} else {
			User other = (User) o;
			return other._name.equals(_name) && other._username.equals(_username) && other._email.equals(_email)
					&& other._address.equals(_address) && other._pass.equals(_pass) && other._type.equals(_type);
		}
	}
}
