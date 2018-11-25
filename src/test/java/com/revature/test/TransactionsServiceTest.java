package com.revature.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.OverdraftException;
import com.revature.model.Customer;
import com.revature.service.TransactionsService;
import com.revature.service.TransactionsServiceImpl;


public class TransactionsServiceTest {
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceTest.class);
	
	private Customer mockCustomer;
	private TransactionsService service;
	
	@Before
	public void setUp() {
		mockCustomer = new Customer("user1", "pass1", "first1", "last1", 0.00);
		service = new TransactionsServiceImpl();
	}
	
	@Test
	public void addNewTransaction() {
		assertTrue(service.addNewTransaction("test", 0.00, 7.77, mockCustomer));
	}
	
	@Test (expected = OverdraftException.class) 
	public void withdrawingTooMuch() {
		service.addNewTransaction("test", 0.00, -2.22, mockCustomer);
	}
	
	@Test
	public void getTransactionsForCustomer() {
		assertFalse(service.getTransactionsForCustomer(mockCustomer).isEmpty());
	}
	
	@After
	public void tearDown() {
		mockCustomer = null;
		service = null;
	}
}
 

