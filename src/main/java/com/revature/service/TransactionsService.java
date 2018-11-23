package com.revature.service;

import java.util.Set;

import com.revature.model.Customer;
import com.revature.model.Transactions;

public interface TransactionsService {
	public boolean addNewTransaction(String transactionType, double originalBalance, double newBalance, Customer customer);
	public Set<Transactions> getTransactionsForCustomer(Customer customer);
}
