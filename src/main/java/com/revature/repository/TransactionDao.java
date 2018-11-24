package com.revature.repository;

import java.util.ArrayList;
import java.util.Set;

import com.revature.model.Customer;
import com.revature.model.Transactions;

/**
 * DATA ACCESS ONLY!
 * no business logic
 *
 */
public interface TransactionDao {

	boolean insert(String transactionType, double originalBalance, double newBalance, Customer customer);
	ArrayList<Transactions> getTransactionsForCustomer(Customer customer);
}
