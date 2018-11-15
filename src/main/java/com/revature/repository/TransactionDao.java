package com.revature.repository;

import com.revature.model.Transactions;

/**
 * DATA ACCESS ONLY!
 * no business logic
 *
 */
public interface TransactionDao {

	boolean insert(Transactions transaction);
}
