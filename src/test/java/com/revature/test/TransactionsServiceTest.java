package com.revature.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Customer;
import com.revature.model.Transactions;

/*
 * one sequence per table
 * one trigger per table
 */

public class TransactionsServiceTest {
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
