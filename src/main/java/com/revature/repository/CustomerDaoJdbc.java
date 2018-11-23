package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

/*
 * 	used by CustomerServiceImpl,
 * 		to implement methods provided by CustomerService
 */

public class CustomerDaoJdbc implements CustomerDao {

	private static final Logger LOGGER = Logger.getLogger(CustomerDaoJdbc.class);
	
	@Override
	public boolean insert(Customer customer) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;

			String sql = "(INSERT INTO CUSTOMER VALUES(?,?,?,?,?))";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// these fill out the '?'(interrogation signs) above in order
			statement.setString(++parameterIndex, customer.getUsername());
			statement.setString(++parameterIndex, customer.getPassword());
			statement.setString(++parameterIndex, customer.getFirstName());
			statement.setString(++parameterIndex, customer.getLastName());
			statement.setDouble(++parameterIndex, customer.getBalance());

			//executeUpdate returns the num of rows affected
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		}catch (SQLException e) {
			LOGGER.error("Error inserting customer: ", e);
		}
		return false;
	}

	@Override
	public Customer findByUserNameAndPassword(String username, String password) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "(SELECT * FROM CUSTOMER WHERE C_USERNAME = ? AND C_PASSWORD = ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			//replaces '?'
			statement.setString(++parameterIndex, username);
			statement.setString(++parameterIndex, password);

			//instead of executUpdate, we do executeQuery
			ResultSet result = statement.executeQuery();
			
			//if there is one record. there's no hasNext(). next() stands on the first row.
			if(result.next()) {
				//try result.get and look at all you have access to for that row
				//use ctrl+space to see what parameters are needed
				return new Customer(
						result.getString("C_USERNAME"),
						result.getString("C_PASSWORD"),
						result.getString("C_FIRSTNAME"),
						result.getString("C_LASTNAME"),
						result.getDouble("C_BALANCE")
						);
			}
			
		}catch (SQLException e) {
			LOGGER.error("Error finding customer with username and password: ", e);
		}
		return null;
	}

	@Override
	public boolean modifyCustomerBalance(Customer customer, double balance) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "(UPDATE CUSTOMER SET C_BALANCE = ? WHERE C_USERNAME = ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			//replaces '?'
			statement.setDouble(++parameterIndex, balance);
			statement.setString(++parameterIndex, customer.getUsername());
			
			//executeUpdate returns the num of rows affected
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		}catch (SQLException e) {
			LOGGER.error("Error modifying customer balance: ", e);
		}
		return false;
	}

	@Override
	public boolean isUsernameTaken(String username) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "(SELECT * FROM CUSTOMER WHERE C_USERNAME = ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			//replaces '?'
			statement.setString(++parameterIndex, username);

			//instead of executUpdate, we do executeQuery
			ResultSet result = statement.executeQuery();
			
			//if there is one record. there's no hasNext(). next() stands on the first row.
			if(result.getFetchSize()>0) {
				//try result.get and look at all you have access to for that row
				//use ctrl+space to see what parameters are needed
				return true;
			}
			
		}catch (SQLException e) {
			LOGGER.error("Error finding customer with username: ", e);
		}
		return false;
	}

}
