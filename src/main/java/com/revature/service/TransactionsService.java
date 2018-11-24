package com.revature.service;

import java.util.ArrayList;

import com.revature.model.Customer;
import com.revature.model.Transactions;

public interface TransactionsService {
	public boolean addNewTransaction(String transactionType, double originalBalance, double newBalance, Customer customer);
	public ArrayList<Transactions> getTransactionsForCustomer(Customer customer);
}
