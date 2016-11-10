// Written by William Hamrick

package edu.gatech.tbd.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import edu.gatech.tbd.model.UserException;


public class RegisterUserTests {

    @Before
    public void setUp() {
        UserManager.clearUserManager();
        UserManager.registerUser("Scott", "scott", "pass",
                UserType.User, "scott@email.com",
                "123 First Street");
    }

    /**
     * Tests exception on trying to register when user already logged in
     */
    @Test (expected = UserException.class)
    public void testUserAlreadyLoggedIn() {
        UserManager.registerUser("Chase", "chase", "pass",
        		UserType.User, "chase@email.com",
        		"123 second street");
    }

    /**
     * Tests exception when using same user name
     */
    @Test (expected = UserException.class)
    public void testUsernameAlreadyUsed() {
    	UserManager.logoutUser();
        UserManager.registerUser("Scott", "scott", "pass",
        		UserType.User, "scott@email.com",
        		"123 First Street");
    }

    /**
     * Tests that current user is correct after register
     */
    @Test ()
    public void testCurrentUser () {
    	UserManager.logoutUser();
        UserManager.registerUser("Chase", "chase", "pass",
        		UserType.User, "chase@email.com",
        		"123 second street");
        assertEquals("chase", UserManager.getLoggedInUser().getUsername());
    }

    /**
     * Tests the user is in user managers user list
     */
    @Test
    public void testUserIsInList() {
    	UserManager.logoutUser();
    	UserManager.registerUser("Mike", "mike", "pass",
    			UserType.User, "mike@email.com",
    			"123 fourth street");
        assertEquals(UserManager.getUser("mike"),
                UserManager.getLoggedInUser());

    }

    /**
     * Tests the return value
     */
    @Test
    public void testMethodReturn() {
    	UserManager.logoutUser();
        assertEquals(UserManager.registerUser("Sonika", "sonika", "pass",
        		UserType.User, "sonika@email.com",
        		"123 Third Street"),
                UserManager.getUser("sonika"));

    }

}
