package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.model.Customer;
import com.revature.model.Transactions;
import com.revature.service.CustomerService;
import com.revature.service.CustomerServiceImpl;
import com.revature.service.TransactionsService;
import com.revature.service.TransactionsServiceImpl;

public class Session {

	CustomerService service = new CustomerServiceImpl();
	TransactionsService tranService = new TransactionsServiceImpl();
	
	public void welcomeScreen(){
		String input = "0";
		Scanner inputGetter = new Scanner(null);
		
		while(!input.equals("3")){
			System.out.println("WELCOME");
			System.out.println("1) Register");
			System.out.println("2) Login");
			System.out.println("3) Quit");
			input = Scanner.nextLine();
			
			if(input.equals("1")) {
				System.out.println("\n\n");
				register();
			}
			if(input.equals("2")) {
				System.out.println("\n\n");
				login();
			}
		}
	}
	
	
	public void register() {
		String firstName = Scanner.getLine();
		String lastName = Scanner.getLine();
		String username = "";
		boolean usernameIsTaken = true;
		while(usernameIsTaken) {
			String username = Scanner.getLine();
			usernameIsTaken = service.checkIfUsernameIsTaken(username);
			if(usernameIsTaken) {
				System.out.println("That username is taken!");
				System.out.print("Try another one: ");
		}	}
		String password = Scanner.getLine();
		
		Customer newCustomer = new Customer(firstName, lastName, username, password);
		service.registerNewCustomer(newCustomer);
		System.out.println("You have been added!");
		Thread.sleep(3000);
		System.out.println("\n\n");
		welcomeScreen();
	}
	
	public void login() {
		String username = "";
		String password = "";
		Customer customer;
		boolean notValidLogin = true;
		while(notValidLogin) {
			username = Scanner.getLine();
			password = Scanner.getLine();
			try {
				customer = service.getCustomerByUsernameAndPassword(username, password);
				notValidLogin = false;
			} catch () {
				System.out.println("Invalid login. Try again.");
			}
		}
		System.out.println("\n\n");
		mainMenu(customer);
	}
	
	void mainMenu(Customer customer) {
		String input = "";
		while(!input.equals("4")) {
			System.out.println("Hello "+customer.getFirstName()+"!");
			System.out.println("Your current balance is $"+customer.getBalance());
			System.out.println("1) Deposit");
			System.out.println("2) Withdraw");
			System.out.println("3) View Transactions");
			System.out.println("4) Logout");
			input = Scanner.getLine();
			
			if(input.equals("1")) {
				System.out.println("\n\n");
				deposit(customer);
			}else if(input.equals("2")) {
				System.out.println("\n\n");
				withdraw(customer);
			}else if(input.equals("3")) {
				System.out.println("\n\n");
				viewTransactions(customer);
			}else if(!input.equals("4")) {
				System.out.println("Sorry, I didn't get that.\n");
			}
		}
		//logging out
		System.out.println("Come again!");
		welcomeScreen();
	}
	
	void deposit(Customer customer) {
		String input = "";
		double deposit = 0.0;
		while(input.equals("back")) {
			System.out.println("Your current balance is $"+customer.getBalance());
			System.out.println("How much would you like to deposit?");
			System.out.println("(Type 'back' to go the main menu)");
			input = Scanner.getLine();
			input = input.replaceAll("$", "");
			input = input.replaceAll("-", "");
			try {
				deposit = Double.parseDouble(input);
			} catch(Exception e) {
				System.out.println("Please enter a monetary value.\n");
				continue;
			}
			service.updateCustomerBalance(customer, customer.getBalance()+deposit);
			Transactions = new Transactions(customer, "Deposit", customer.getBalance()+deposit);
			tranService.addNewTransaction(newTransaction);
			
			System.out.println("Your deposit has been processed.");
		}
		System.out.println("\n\n");
		//automatically returns to the mainMenu()
	}
	
	void withdraw(Customer customer) {
		String input = "";
		double deposit = 0.0;
		while(input.equals("back")) {
			System.out.println("Your current balance is $"+customer.getBalance());
			System.out.println("How much would you like to deposit?");
			System.out.println("(Type 'back' to go the main menu)");
			input = Scanner.getLine();
			input = input.replaceAll("$", "");
			input = input.replaceAll("-", "");
			try {
				deposit = Double.parseDouble(input);
			} catch(Exception e) {
				System.out.println("Please enter a monetary value.\n");
				continue;
			}
			if(deposit>customer.getBalance()) {
				System.out.println("Please be reasonable. You don't have that much!");
				continue;
			}
			updateCustomerBalance(customer, customer.getBalance()-deposit);
			addNewTransaction(customer, "Deposit", customer.getBalance()-deposit);
			System.out.println("Your withdrawl has been processed.");
		}
		System.out.println("\n\n");
		//automatically returns to the mainMenu()
	}
	
	void viewTransactions(Customer customer) {
		Set<Transactions> transactions = getTransactionsForCustomer(customer);
		System.out.println("-----TRANSACTIONS-------");
		for(Transactions transaction: transactions) {
			System.out.println(transaction.toString());
		}
		System.out.println("Press enter to go back.");
		Scanner.getLine();
		System.out.println("\n\n");
		//automatically returns to the mainMenu()
	}
	
}
