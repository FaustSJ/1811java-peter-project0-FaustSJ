package com.revature.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Customer;
import com.revature.model.Transactions;

/*
 * ctrl+shift+o
 * to clean up imports
 * 
 * ctrl+space
 * to auto-complete for main(), sysout
 * 
 * ctrl+shift+numPad/ (num pad back-slash)
 * to collapse code blocks
 */

public class CustomerServiceTest {
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceTest.class);
	
	private Customer mockCustomer;
	private Transactions mockTransaction;
	
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
	}
	
	@Test
	public void testInsert() {
		// TODO Auto-generated method stub
	}
	
	@After
	public void tearDown() {
		mockCustomer = null;
		mockTransaction = null;
	}
}
