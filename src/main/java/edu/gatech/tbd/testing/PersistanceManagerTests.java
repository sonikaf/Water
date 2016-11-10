package edu.gatech.tbd.testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.gatech.tbd.persistence.PersistenceManager;

class PersistanceManagerTests {
	
	private class SavableObject {
		int test;
		String test2;
		
		public SavableObject(int t, String t2) {
			test = t;
			test2 = t2;
		}
		
		public int hashCode() {
			return PersistenceManager.generateObjectHash(this);
		}
	}
    
    
    /**
     * Tests null username
     */
    @Test
    public void testRegularSave() {
    	SavableObject ob = new SavableObject(99, "hello world");
    	try {
    		PersistenceManager.addObject(ob);
    	} catch (IOException e) {
    		Assert.fail(e.getMessage());
    	}
    	
    	String fn = String.format("data/%s_%x.txt", ob.getClass().getTypeName(), ob.hashCode());
    	Assert.assertTrue(new File(fn).exists());
    }
    
    @Test
    public void testNullObject() {
    	try {
    		PersistenceManager.addObject(null);
    	} catch (IOException e) {
    		Assert.assertEquals(e.getMessage(), "Can't save null object!");
    	}
    }

}
