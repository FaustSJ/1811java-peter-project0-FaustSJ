package com.revature.service;

import com.revature.model.Customer;

public interface CustomerService {
	public boolean registerNewCustomer(Customer customer);
	public Customer getCustomerByUsernameAndPassword(String username, String password);
	public boolean updateCustomerBalance(double balance);
}
