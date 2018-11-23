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

	private CustomerService service = new CustomerServiceImpl();
	private TransactionsService tranService = new TransactionsServiceImpl();
	private Scanner scanner;
	
	public Session() {
		this.scanner = new Scanner(System.in);
	}
	
	public void welcomeScreen(){
		String input = "0";
		
		while(!input.equals("3")){
			System.out.println("WELCOME");
			System.out.println("1) Register");
			System.out.println("2) Login");
			System.out.println("3) Quit");
			input = scanner.nextLine();
			input = input.trim();
			
			if(input.equals("1")) {
				System.out.println("\n\n");
				register();
			}
			if(input.equals("2")) {
				System.out.println("\n\n");
				login();
			}
		}
		
		scanner.close();
	}
	
	
	public void register() {
		System.out.print("First name: ");
		String firstName = scanner.nextLine();
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();
		String username = "";
		boolean usernameIsTaken = true;
		while(usernameIsTaken) {
			System.out.print("Username: ");
			username = scanner.nextLine();
			usernameIsTaken = service.checkIfUsernameIsTaken(username);
			if(usernameIsTaken) {
				System.out.println("That username is taken!");
				System.out.println("Try another one.");
		}	}
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		Customer newCustomer = new Customer(username, password, firstName, lastName, 0.00);
		service.registerNewCustomer(newCustomer);
		System.out.println("You have been added!");
		System.out.println("\n\n");
		welcomeScreen();
	}
	
	public void login() {
		String username = "";
		String password = "";
		Customer customer = null;
		boolean notValidLogin = true;
		while(notValidLogin) {
			System.out.print("Username: ");
			username = scanner.nextLine();
			System.out.print("Password: ");
			password = scanner.nextLine();
			customer = service.getCustomerByUsernameAndPassword(username, password);
			if(customer != null) {
				notValidLogin = false;
			} else {
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
			input = scanner.nextLine();
			
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
			input = scanner.nextLine();
			input = input.replaceAll("$", "");
			input = input.replaceAll("-", "");
			try {
				deposit = Double.parseDouble(input);
			} catch(Exception e) {
				System.out.println("Please enter a monetary value.\n");
				continue;
			}
			
			tranService.addNewTransaction("deposit", customer.getBalance(), (customer.getBalance()+deposit), customer);
			service.updateCustomerBalance(customer, customer.getBalance()+deposit);
			customer.setBalance(customer.getBalance()+deposit);
			
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
			input = scanner.nextLine();
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
			tranService.addNewTransaction("withdraw", customer.getBalance(), (customer.getBalance()-deposit), customer);
			service.updateCustomerBalance(customer, customer.getBalance()-deposit);
			customer.setBalance(customer.getBalance()-deposit);
			
			System.out.println("Your withdraw has been processed.");
		}
		System.out.println("\n\n");
		//automatically returns to the mainMenu()
	}
	
	void viewTransactions(Customer customer) {
		Set<Transactions> transactions = tranService.getTransactionsForCustomer(customer);
		System.out.println("-----TRANSACTIONS-------");
		for(Transactions transaction: transactions) {
			System.out.println(transaction.toString());
		}
		System.out.println("Press enter to go back.");
		scanner.nextLine();
		System.out.println("\n\n");
		//automatically returns to the mainMenu()
	}
	
}
