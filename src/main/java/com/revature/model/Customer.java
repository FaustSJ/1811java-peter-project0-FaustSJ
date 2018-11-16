package com.revature.model;
/*
 *     C_ID NUMBER(10,0),
    C_USERNAME VARCHAR2(40),
    C_PASSWORD VARCHAR2(40),
    C_BALANCE NUMBER(8,2),
    CONSTRAINT PK_CUSTOMER PRIMARY KEY (C_ID)
 */
public class Customer {
	/**
	 * C_ID
	 * always add as null to toggle auto-increment
	 */
	private long id;
	/**
	 * C_USERNAME
	 */
	private String username;
	/**
	 * C_PASSWORD
	 */
	private String password;
	/**
	 * C_BALANCE
	 */
	private double balance;
	/**
	 * C_FIRSTNAME
	 */
	private String firstName;
	/**
	 * C_LASTNAME
	 */
	private String lastName;
	
	/* Constructors */
	
	public Customer() { }

	public Customer(long id, String username, String password, double balance, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/* Getters and Setters */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
