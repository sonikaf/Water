// Written by Scott Wolfe

package edu.gatech.tbd.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import edu.gatech.tbd.model.UserException;


class LoginUserTests {
        
    @Before
    public void setUp() {
        UserManager.clearUserManager();
        UserManager.registerUser("Scott", "scott", "pass",
                UserType.User, "scott@email.com",
                "123 First Street");
        UserManager.logoutUser();
    }
    
    
    /**
     * Tests null username
     */
    @Test (expected = UserException.class)
    public void testNullUsername() {
        UserManager.loginUser(null, "123");
    }
    
    /**
     * Tests null password
     */
    @Test (expected = UserException.class)
    public void testNullPassword() {
        UserManager.loginUser("scott", null);
    }
    
    /**
     * Tests username not in system
     */
    // test missing username
    @Test (expected = UserException.class)
    public void testMissingUsername() {
        UserManager.loginUser("incorrectName", "pass");
    }
    
    /**
     * Tests incorrect password
     */
    @Test (expected = UserException.class)
    public void testIncorrectPassword() {
        UserManager.loginUser("scott", "incorrectPass");
    }
    
    /**
     * Tests when user is alreadly logged in
     */
    @Test (expected = UserException.class)
    public void testCurrentUserNotNull() {
        UserManager.loginUser("scott", "pass");
        UserManager.loginUser("dummy", "password");
    }
    
    /**
     * Tests that current user is correct
     */
    @Test ()
    public void testCurrentUser () {
        UserManager.loginUser("scott", "pass");
        assertEquals("scott", UserManager.getLoggedInUser().getUsername());
    }
    
    /**
     * Tests the return value
     */
    @Test
    public void testMethodReturn() {
        assertEquals(UserManager.getUser("scott"),
                UserManager.loginUser("scott", "pass"));
                
    }
    
}
