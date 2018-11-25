package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.InvalidLoginCredentialsException;
import com.revature.exception.UsernameIsAlreadyTakenException;
import com.revature.model.Customer;
import com.revature.service.CustomerService;
import com.revature.service.CustomerServiceImpl;

public class CustomerServiceTest {
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceTest.class);
	
	private Customer mockCustomer;
	private CustomerService service;
	//Keeps the username unique
	private int customerCount = 1; 
	
	@Before
	public void setUp() {
		mockCustomer = new Customer(("test"+(++customerCount)), "pass", "first", "last", 0.00);
		service = new CustomerServiceImpl();
	}
	
	@Test
	public void registerNewCustomer() {
		assertTrue(service.registerNewCustomer(mockCustomer));
	}
	
	@Test
	public void getCustomerByUsernameAndPassword() {
		assertEquals(service.getCustomerByUsernameAndPassword("user1", "pass1").getUsername(), "user1");
	}
	
	@Test
	public void pickedUsernameIsntTaken() {
		assertFalse(service.checkIfUsernameIsTaken("thisIsntTaken"));
	}

	@Test (expected = UsernameIsAlreadyTakenException.class)
	public void pickedUsernameIsTaken() {
		service.checkIfUsernameIsTaken("user1");
	}
	
	@Test (expected = InvalidLoginCredentialsException.class)
	public void invalidLoginInSession() {
		service.getCustomerByUsernameAndPassword("phonyUsername", "phonyPassword");
	}
	
	
	@After
	public void tearDown() {
		mockCustomer = null;
		service = null;
	}
	
}