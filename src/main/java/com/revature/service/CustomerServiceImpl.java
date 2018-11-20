package com.revature.service;

import com.revature.model.Customer;
import com.revature.repository.CustomerDao;
import com.revature.repository.CustomerDaoJdbc;

/*
 * uses the CustomerDaoJdbc class methods
 * 	to implement CustomerService methods
 */
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao = new CustomerDaoJdbc();
	
	@Override
	public boolean registerNewCustomer(Customer customer) {
		return dao.insert(customer);
	}

	@Override
	public Customer getCustomerByUsernameAndPassword(String username, String password) {
		return dao.findByUserNameAndPassword(username, password);
	}

	@Override
	public boolean updateCustomerBalance(Customer customer, double balance) {
		return dao.modifyCustomerBalance(customer, balance);
	}

	@Override
	public boolean checkIfUsernameIsTaken(String username) {
		return dao.isUsernameTaken(username);
	}

}
