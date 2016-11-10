// Written by Sonika Finch

package edu.gatech.tbd.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import edu.gatech.tbd.model.User;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.persistence.PasswordStorage;
import edu.gatech.tbd.persistence.PasswordStorage.CannotPerformOperationException;
import edu.gatech.tbd.persistence.PasswordStorage.InvalidHashException;



public class UpdateUserInfoTests {
	
        
    @Before
    public void setUp() {
        UserManager.clearUserManager();
        UserManager.registerUser("Sonika", "sonika", "pass",
                UserType.User, "sonika@email.com",
                "123 First Street");
    }
    
    
    /**
     * Tests null name
     */
    @Test (expected = UserException.class)
    public void testNullName() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), null, "sonika", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
    }
    
    /**
     * Tests null Username
     */
    @Test (expected = UserException.class)
    public void testNullUsername() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", null, "pass",
                UserType.User, "sonika@email.com", "123 First Street");
    }

    /**
     * Tests null Password
     */
    @Test (expected = UserException.class)
    public void testNullPassword() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", null,
                UserType.User, "sonika@email.com", "123 First Street");
    }

    /**
     * Tests null Email
     */
    @Test (expected = UserException.class)
    public void testNullEmail() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", "pass",
                UserType.User, null, "123 First Street");
    }
    
    /**
     * Tests null Address
     */
    @Test (expected = UserException.class)
    public void testNullAddress() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", "pass",
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
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "JaneDoe", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
    }
    
    /**
     * Tests that Name is updated 
     */
    @Test
    public void testNameUpdate() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "foobar", "sonika", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getName());
    }
    
 
    
    /**
     * Tests that Username is updated
     */
    @Test ()
    public void testUsernameUpdate() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "foobar", "pass",
                UserType.User, "sonika@email.com", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getUsername());
    }   
    
    /**
     * Tests that Password is updated
     */
    @Test
    public void testPasswordUpdate() throws CannotPerformOperationException, InvalidHashException {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", "foobar",
                UserType.User, "sonika@email.com", "123 First Street");

		assertEquals(true, PasswordStorage.verifyPassword("foobar", UserManager.getLoggedInUser().getPassword()));
	
    }
    
    
    /**
     * Tests that Type is updated
     */
    @Test
    public void testTypeUpdate() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", "pass",
                UserType.Administrator, "sonika@email.com", "123 First Street");
        assertEquals(UserType.Administrator, UserManager.getLoggedInUser().getType());
    }

    /**
     * Tests that Email is updated
     */
    @Test
    public void testEmailUpdate() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", "pass",
                UserType.User, "foobar", "123 First Street");
        assertEquals("foobar", UserManager.getLoggedInUser().getEmail());
    }
    
    /**
     * Tests that Address is updated
     */
    @Test
    public void testAddressUpdate() {
        UserManager.updateUserInformation(UserManager.getLoggedInUser(), "Sonika", "sonika", "pass",
                UserType.User, "sonika@email.com", "foobar");
        assertEquals("foobar", UserManager.getLoggedInUser().getAddress());
    }
    
}
