package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.revature.model.Customer;
import com.revature.model.Transactions;
import com.revature.util.ConnectionUtil;

/*
 * used by TransactionsServiceImpl
 * 		to implement methods listed in TransactionsService
 */

public class TransactionDaoJdbc implements TransactionDao {

	private static final Logger LOGGER = Logger.getLogger(TransactionDaoJdbc.class);
	
	@Override
	public boolean insert(String transactionType, double originalBalance, double newBalance, Customer customer) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			//next 2 lines cause issues with the newly added trigger
			//String sub = "(SELECT MAX(A_ID)+1 FROM ANIMAL)";//NOT IDEAL FOR LARGE dbS
			//String sql = "INSERT INTO ANIMAL VALUES("+sub+",?,?,NULL,?,?,NULL)";
			
			//the newly added trigger auto-increments the ID
			String sql = "(INSERT INTO TRANSACTIONS VALUES(?, CURRENT_TIMESTAMP,?,?,?,?))";
//Timestamp timestamp = new TimeStamp(System.surrentTimeMilis);
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// these fill out the '?'(interrogation signs) above in order
			statement.setLong(++parameterIndex, this.generateNextTransactionIDNumber());
			statement.setString(++parameterIndex, "");
			statement.setString(++parameterIndex, transactionType);
			statement.setDouble(++parameterIndex, originalBalance);
			statement.setDouble(++parameterIndex, newBalance);
			statement.setString(++parameterIndex, customer.getUsername());

			//executeUpdate returns the num of rows affected
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		}catch (SQLException e) {
			LOGGER.error("Error inserting animal: ", e);
		}
		return false;
	}

	@Override
	public Set<Transactions> getTransactionsForCustomer(Customer customer) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "(SELECT * FROM TRANSACTIONS WHERE C_USERNAME = ?)";
			
			//we don't need a prepared one since there's no input
			PreparedStatement statement = connection.prepareStatement(sql); //a child of preparedStatement
			statement.setString(++parameterIndex, customer.getUsername());
			
			ResultSet result = statement.executeQuery();
			
			//if there is one record. there's no hasNext(). next() stands on the first row.
			Set<Transactions> transactions = new TreeSet<>();
			while(result.next()) {
				//try result.get and look at all you have access to for that row
				//use ctrl+space to see what parameters are needed
				transactions.add(new Transactions(
						result.getLong("T_ID"),
						result.getString("T_STAMP"),
						result.getString("T_TRANSACTION_TYPE"),
						result.getDouble("T_ORIGINAL_BALANCE"),
						result.getDouble("T_UPDATED_BALANCE"),
						new Customer(result.getString("C_USERNAME"),"filler","filler","filler",0.0)
						));
			}
			return transactions;
		}catch (SQLException e) {
			LOGGER.error("Error inserting animal: ", e);
		}
		return null;
	}
	
	private long generateNextTransactionIDNumber() {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "(SELECT MAX(T_ID) FROM TRANSACTIONS)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getLong("MAX(T_ID)")+1;
			}
			
		}catch (SQLException e) {
			LOGGER.error("Error getting latest transaction ID: ", e);
		}
		
		return 0;
	}

}
