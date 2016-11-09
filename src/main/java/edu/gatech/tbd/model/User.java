package edu.gatech.tbd.model;

import edu.gatech.tbd.persistence.PersistenceManager;

public class User {

	String _name;
	String _pass;
	String _email;
	String _address;
	UserType _type;
	String _username;

	/**
	 * Creates a new user object.
	 */
    User(String name, String uname, String pass, UserType type, String email, String address) {
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
	 * @return user's username
	 */
	public String getUsername() {
		return _username;
	}

	/**
	 * Gets the user's name.
	 * 
	 * @return user's name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Gets the user's email.
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * Gets the user's address.
	 * 
	 * @return user's address
	 */
	public String getAddress() {
		return _address;
	}

	/**
	 * Gets the user's password hash string.
	 * 
	 * @return user's password
	 */
	public String getPassword() {
		return _pass;
	}

	/**
	 * Gets the user's type.
	 * 
	 * @return user's user type (ex: Manager, Worker, ect)
	 */
	public UserType getType() {
		return _type;
	}
	
	/**
     * Returns true if the user is an Administrator, false otherwise.
	 *
	 * @return if user is an administrator
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
	
	public String toString() {
		return "{User -> Name: " + this._name + ", Username: " + this._username + "}";
	}
	
	public int hashCode() {
		return PersistenceManager.generateObjectHash(this);
	}
}
