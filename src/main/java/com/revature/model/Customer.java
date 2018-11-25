package com.revature.model;

public class Customer {
	/**
	 * C_USERNAME
	 */
	private String username;
	/**
	 * C_PASSWORD
	 */
	private String password;
	/**
	 * C_FIRSTNAME
	 */
	private String firstName;
	/**
	 * C_LASTNAME
	 */
	private String lastName;
	/**
	 * C_BALANCE
	 */
	private double balance;
	
	
	/* Constructors */
	
	public Customer() { }

	public Customer(String username, String password, String firstName, String lastName, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/* Getters and Setters */

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
	
	/* String Representation */
	
	public String toString() {
		return (firstName + " " + lastName);
	}
	
}
