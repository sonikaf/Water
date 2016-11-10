// Written by Sonika Finch

package edu.gatech.tbd.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.User;



public class UpdateUserInfoTests {
	
	private User u;

        
    @Before
    public void setUp() {
        UserManager.clearUserManager();
        UserManager.registerUser("Sonika", "sonika", "pass",
                UserType.User, "sonika@email.com",
                "123 First Street");
        u = UserManager.loginUser("sonika", "pass");
    }
    
    
    /**
     * Tests null name
     */
    @Test (expected = UserException.class)
    public void testNullName() {
        UserManager.updateUserInformation(u, null, "sonika", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
    }
    
    /**
     * Tests null Username
     */
    @Test (expected = UserException.class)
    public void testNullUsername() {
        UserManager.updateUserInformation(u, "Sonika", null, "pass",
                UserType.User, "sonika@email.com", "123 First Street");
    }

    /**
     * Tests null Password
     */
    @Test (expected = UserException.class)
    public void testNullPassword() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", null,
                UserType.User, "sonika@email.com", "123 First Street");
    }

    /**
     * Tests null Email
     */
    @Test (expected = UserException.class)
    public void testNullEmail() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", "pass",
                UserType.User, null, "123 First Street");
    }
    
    /**
     * Tests null Address
     */
    @Test (expected = UserException.class)
    public void testNullAddress() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", "pass",
                UserType.User, "sonika@email.com", null);
    }


    
    /**
     * Tests that new username is unique
     */
    @Test (expected = UserException.class)
    public void testUniqueUsername() {
        UserManager.registerUser("Jane", "JaneDoe", "pass",
                UserType.User, "jane@email.com",
                "123 First Street");
        UserManager.updateUserInformation(u, "Sonika", "JaneDoe", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
    }
    
    /**
     * Tests that Name is updated 
     */
    @Test
    public void testNameUpdate() {
        UserManager.updateUserInformation(u, "foobar", "sonika", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getName());
    }
    
 
    
    /**
     * Tests that Username is updated
     */
    @Test ()
    public void testUsernameUpdate() {
        UserManager.updateUserInformation(u, "Sonika", "foobar", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getUsername());
    }
    
    
    /**
     * Tests that Password is updated
     */
    @Test
    public void testPasswordUpdate() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", "foobar",
                UserType.User, "sonika@email.com", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getPassword());
    }
    
    
    /**
     * Tests that Type is updated
     */
    @Test
    public void testTypeUpdate() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", "pass",
                UserType.Administrator, "sonika@email.com", "123 First Street");
        assertEquals(UserType.Administrator, UserManager.getLoggedInUser().getType());
    }

    /**
     * Tests that Email is updated
     */
    @Test
    public void testEmailUpdate() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", "pass",
                UserType.User, "foobar", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getEmail());
    }
    
    /**
     * Tests that Address is updated
     */
    @Test
    public void testAddressUpdate() {
        UserManager.updateUserInformation(u, "Sonika", "sonika", "pass",
                UserType.User, "sonika@email.com", "foobar");
        assertEquals("foobar", UserManager.getLoggedInUser().getAddress());
    }
    
}
