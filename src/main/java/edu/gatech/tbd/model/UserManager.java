package edu.gatech.tbd.model;

import java.util.HashMap;
import java.util.List;

import edu.gatech.tbd.persistence.PasswordStorage;
import edu.gatech.tbd.persistence.PersistenceManager;
import edu.gatech.tbd.persistence.PasswordStorage.CannotPerformOperationException;

public class UserManager {
    
    /**
     * Contains all registered users.
     */
	private static HashMap<String, User> userList = new HashMap<>();
	
	/**
	 * The currently logged-in user.
	 */
	private static User currentUser;

	/**
	 * Logs a User into the system.
	 * 
	 * @param username The user's username.
	 * @param password The user's password.
	 * @return the logged-in user.
	 */
	public static User loginUser(String username, String password) {
		if (currentUser != null) {
			throw new UserException("Another user is already logged in.");
		}
		User u = userList.get(username);
		
		// if user does not exist or password is incorrect then throw
		try {
			if (u == null || !PasswordStorage.verifyPassword(password, u._pass)) {
				throw new UserException("Invalid Username or Password.");
			}
		} catch (Exception e) {
			throw new UserException("Error validating credentials: " + e.getMessage());
		}

		// store our currently logged in user
		currentUser = u;
		
		// return user
		return u;
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
	 * @param username username of user
	 * @param password password of user
	 * @param type user type of user
	 * @param email email of user
	 * @param address address of user
	 * 
	 * @return user
	 */
	public static User registerUser(String name, String username, String password, UserType type, String email, String address)
			throws UserException {
		// *NOTE* M5 doesn't really say anything about what is needed to store per
		// user so add any other data fields that seem necessary
		if (currentUser != null) {
			throw new UserException("Another user is alreday logged in.");
		}
		if (userList.get(username) != null) {
			throw new UserException("Another user with that username already exists.");
		}
		
		String hashedPassword = "";
		try {
			 hashedPassword = PasswordStorage.createHash(password);
		} catch (CannotPerformOperationException e) {
			e.printStackTrace();
		}

		currentUser = new User(name, username, hashedPassword, type, email, address);
		userList.put(username, currentUser);
		
		PersistenceManager.addObject(currentUser);
		
		return currentUser;
	}
	
	/**
	 * Updates a user's information.
	 * 
	 * @param u user to be updated
	 * @param name updated name of user
	 * @param username updated username of user
	 * @param password updated password of user
	 * @param type updated type of user
	 * @param email updated email of user
	 * @param address updated address of user
	 * @throws UserException thrown if username is taken
	 */
	public static void updateUserInformation(User u, String name, String username, String password, UserType type, String email,
			String address) throws UserException {
		// here if there is a user with the specified username and it isn't the same object as our User then we can't change
		
		if (name == null || username == null || password == null || email == null || address == null) {
			throw new UserException("One or more fields is not completed.");
		}
		
		if (userList.containsKey(username) && !userList.containsValue(u)) {
			throw new UserException("A User with that username already exists.");
		}
		
		String hashedPassword = "";
		try {
			 hashedPassword = PasswordStorage.createHash(password);
		} catch (CannotPerformOperationException e) {
			e.printStackTrace();
		}
		
		int oldHash = u.hashCode();
		
		u._name = name;
		u._username = username;
		u._pass = hashedPassword;
		u._type = type;
		u._email = email;
		u._address = address;
		
		PersistenceManager.updateObject(u, oldHash);
	}
	
	/**
	 * Returns the current user.
	 * @return user currently logged in
	 */
	public static User getLoggedInUser() {
		return currentUser;
	}
	
	/**
	 * gets the number of registered users
	 * @return number of registered users
	 */
	public static int numUsers() {
		return userList.size();
	}
	
	/**
	 * loads all of the user information from the disk
	 */
	public static void setup() {
		List<User> users = PersistenceManager.getObjects(User.class);
		for(User u : users) {
			userList.put(u.getUsername(), u);
		}
	}
	
	/**
	 * used in JUnit to test loginMethod. Clears all
	 * class data.
	 */
	public static void clearUserManager() {
	    userList = new HashMap<>();
	    currentUser = null;
	}
	
	/**
	 * Used in JUnit to test loginMethod.
	 */
	public static User getUser(String username) {
	    return userList.get(username);
	}
	
}
