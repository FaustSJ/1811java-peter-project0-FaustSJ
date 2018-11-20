package com.revature.repository;

import java.util.Set;

import com.revature.model.Customer;
import com.revature.model.Transactions;

/**
 * DATA ACCESS ONLY!
 * no business logic
 *
 */
public interface TransactionDao {

	boolean insert(Transactions transaction);
	Set<Transactions> getTransactionsForCustomer(Customer customer);
}
