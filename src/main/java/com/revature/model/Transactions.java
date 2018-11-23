package com.revature.model;
//T_ID NUMBER(10,0),
//T_STAMP TIMESTAMP,
//T_TRANSACTION_TYPE VARCHAR(10),
//T_ORIGINAL_BALANCE NUMBER(8,2),
//T_UPDATED_BALANCE NUMBER(8,2),
//C_USERNAME VARCHAR2(40),
//CONSTRAINT PK_TRANSACTIONS PRIMARY KEY (T_ID),
//CONSTRAINT FK_CUSTOMER FOREIGN KEY (C_USERNAME) REFERENCES CUSTOMER (C_USERNAME)
public class Transactions {
	/**
	 * T_ID
	 */
	private long id;
	/**
	 * T_STAMP
	 */
	private String stamp;
	/**
	 * T_TRANSACTION_TYPE
	 */
	private String transactionType;
	/**
	 * T_ORIGINAL_BALANC
	 */
	private double originalBalance;
	/**
	 * T_UPDATED_BALANCE
	 */
	private double updatedBalance;
	/**
	 * FOREIGN KEY
	 */
	private Customer customer;
	
	/* Constructors */
	
	public Transactions() { }
	
	public Transactions(long id, String stamp, String transactionType, double originalBalance, double updatedBalance,
			Customer customer) {
		super();
		this.id = id;
		this.stamp = stamp;
		this.transactionType = transactionType;
		this.originalBalance = originalBalance;
		this.updatedBalance = updatedBalance;
		this.customer = customer;
	}

	/* Getters and Setters */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getOriginalBalance() {
		return originalBalance;
	}

	public void setOriginalBalance(double originalBalance) {
		this.originalBalance = originalBalance;
	}

	public double getUpdatedBalance() {
		return updatedBalance;
	}

	public void setUpdatedBalance(double updatedBalance) {
		this.updatedBalance = updatedBalance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
