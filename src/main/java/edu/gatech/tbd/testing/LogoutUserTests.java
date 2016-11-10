//written by Mike Adams

package edu.gatech.tbd.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;

public class LogoutUserTests {
	@Before
    public void setUp() {
        UserManager.clearUserManager();
        UserManager.registerUser("Mike", "mike", "pass",
                UserType.User, "mike@email.com",
                "321 Second Street");
	}
	
	/**
     * Tests logging out user
     */
    @Test ()
    public void testLogout() {
        UserManager.logoutUser();
        assertEquals(null, UserManager.getLoggedInUser());
    }
    
    /**
     * Tests logging out user
     */
    @Test (expected = UserException.class)
    public void testLogoutTwice() {
        UserManager.logoutUser();
        UserManager.logoutUser();
    }
    
}
