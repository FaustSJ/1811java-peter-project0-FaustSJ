package com.revature.service;

import java.util.Set;

import com.revature.model.Customer;
import com.revature.model.Transactions;
import com.revature.repository.TransactionDao;
import com.revature.repository.TransactionDaoJdbc;

/*
 * implements methods for TransactionsService
 * 		using methods provided by TransactionDaoJdbc
 */

public class TransactionsServiceImpl implements TransactionsService {

	TransactionDao dao = new TransactionDaoJdbc();
	
	@Override
	public boolean addNewTransaction(String transactionType, double originalBalance, double newBalance, Customer customer) {
		return dao.insert(transactionType, originalBalance, newBalance, customer);
	}

	@Override
	public Set<Transactions> getTransactionsForCustomer(Customer customer) {
		return dao.getTransactionsForCustomer(customer);
	}

}

