package com.revature.repository;

import com.revature.model.Customer;

/**
 * DATA ACCESS ONLY!
 * no business logic
 *
 */

public interface CustomerDao {

	boolean insert(Customer customer);
}
