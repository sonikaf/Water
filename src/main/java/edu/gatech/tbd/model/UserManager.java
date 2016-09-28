package edu.gatech.tbd.model;

import java.util.HashMap;

public class UserManager {

	private static HashMap<String, User> userList = new HashMap<String, User>();
	private static User currentUser;

	/**
	 * Logs in a User to the system.
	 * 
	 * @param username
	 * @param password
	 */
	public static void loginUser(String username, String password) throws UserException {
		if (currentUser != null) {
			throw new UserException("Another user is alreday logged in.");
		}
		User u = userList.get(username);

		// if user does not exist or password is incorrect then throw
		if (u == null || !u._pass.equals(password)) {
			throw new UserException("Invalid Username or Password.");
		}

		// store our currently logged in user
		currentUser = u;
	}

	/**
	 * Log out the current user
	 */
	public static void logoutUser() {
		currentUser = null;
	}

	/**
	 * Create a new user object, and logs it in
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @param email
	 * @param address
	 */
	public static void registerUser(String username, String password, UserType type, String email, String address)
			throws UserException {
		// *NOTE* M5 doesn't really say anything about what is needed to store per
		// user so add any other data fields that seem necessary
		if (currentUser != null) {
			throw new UserException("Another user is alreday logged in.");
		}
		if (userList.get(username) != null) {
			throw new UserException("Another user with that username already exists.");
		}

		currentUser = new User(username, password, type, email, address);
		userList.put(username, currentUser);
	}

	public void updateUserInformation(User u, String username, String password, UserType type, String email,
			String address) {
		u._name = username;
		u._pass = password;
		u._type = type;
		u._email = email;
		u._address = address;
	}

	public User getLoggedInUser() {
		return currentUser;
	}

}
