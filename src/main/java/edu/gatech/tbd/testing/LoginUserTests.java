// Written by Scott Wolfe

package edu.gatech.tbd.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import edu.gatech.tbd.model.UserException;


public class LoginUserTests {
        
    @Before
    public void setUp() {
        UserManager.clearUserManager();
        UserManager.registerUser("Scott", "scott", "pass",
                UserType.User, "scott@email.com",
                "123 First Street");
    }
    
    
    @Test (expected = UserException.class)
    public void testNullUsername() {
        UserManager.loginUser(null, "123");
    }
    
    @Test (expected = UserException.class)
    public void testNullPassword() {
        
        UserManager.loginUser("scott", null);
    }

}
