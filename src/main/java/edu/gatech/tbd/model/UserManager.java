package edu.gatech.tbd.model;

import java.util.HashMap;

public class UserManager {
    
    /**
     * Contains all registered users.
     */
	private static HashMap<String, User> userList = new HashMap<String, User>();
	
	/**
	 * The currently logged-in user.
	 */
	private static User currentUser;

	/**
	 * Logs a User into the system.
	 * 
	 * @param username The user's username.
	 * @param password The user's password.
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
	 * Log out the current user.
	 */
	public static void logoutUser() {
		currentUser = null;
	}

	/**
	 * Create a new user object, and logs it in.
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @param email
	 * @param address
	 */
	public static void registerUser(String name, String username, String password, UserType type, String email, String address)
			throws UserException {
		// *NOTE* M5 doesn't really say anything about what is needed to store per
		// user so add any other data fields that seem necessary
		if (currentUser != null) {
			throw new UserException("Another user is alreday logged in.");
		}
		if (userList.get(username) != null) {
			throw new UserException("Another user with that username already exists.");
		}

		currentUser = new User(name, username, password, type, email, address);
		userList.put(username, currentUser);
	}
	
	/**
	 * Updates a user's information.
	 * 
	 * @param u
	 * @param name
	 * @param username
	 * @param password
	 * @param type
	 * @param email
	 * @param address
	 * @throws UserException
	 */
	public static void updateUserInformation(User u, String name, String username, String password, UserType type, String email,
			String address) throws UserException {
		// here if there is a user with the specified username and it isn't the same object as our User then we can't change
		if (userList.containsKey(username) && !userList.containsValue(u)) {
			throw new UserException("A User with that username already exists.");
		}
		u._name = name;
		u._username = username;
		u._pass = password;
		u._type = type;
		u._email = email;
		u._address = address;
	}
	
	/**
	 * Returns the current user.
	 * 
	 * @return
	 */
	public static User getLoggedInUser() {
		return currentUser;
	}

}
