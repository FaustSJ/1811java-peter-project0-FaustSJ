package com.revature.repository;

import com.revature.model.Customer;

/**
 * DATA ACCESS ONLY!
 * no business logic
 *
 */

/*
 * implemented by CustomerDaoJdbc,
 * 		which is used by CustomerServiceImpl,
 * 			to implement methods provided by CustomerService
 */
public interface CustomerDao {

	boolean insert(Customer customer);
	Customer findByUserNameAndPassword(String username, String password);
	boolean modifyCustomerBalance(Customer customer, double balance);
	boolean isUsernameTaken(String username);
}
