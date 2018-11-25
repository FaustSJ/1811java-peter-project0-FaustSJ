package com.revature.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.InvalidLoginCredentialsException;
import com.revature.exception.OverdraftException;
import com.revature.exception.UsernameIsAlreadyTakenException;
import com.revature.model.Customer;
import com.revature.model.Transactions;
import com.revature.service.CustomerService;
import com.revature.service.CustomerServiceImpl;
import com.revature.service.TransactionsService;
import com.revature.service.TransactionsServiceImpl;

public class Session {

	private static final Logger LOGGER = Logger.getLogger(Session.class);
	
	private CustomerService service = new CustomerServiceImpl();
	private TransactionsService tranService = new TransactionsServiceImpl();
	private Scanner scanner;
	private Customer loginCustomer;
	
	public Session() {
		scanner = new Scanner(System.in);
		loginCustomer = null;
	}
	
	/*
	 * Displays the Welcome Screen, where the user can select to go either to the Login Screen
	 * 	or to the Registration Screen. Or they can Quit the program.
	 * 
	 * Leaving the Registration Screen or Login Screen will bring the user back here.
	 * Logging out will also bring the user back here.
	 */
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
				System.out.println("\n");
				register();
			}
			
			if(input.equals("2")) {
				System.out.println("\n");
				getLogin();
			}
		}
		scanner.close();
		System.out.println("GOODBYE");
		//exits the program
	}
	
	/*
	 * Displays the Register Screen,
	 * 	where the user can register their own username and password
	 * 
	 * The user comes here from the Welcome Screen.
	 * 
	 * Completing registration or typing 'q' will return the user to the Welcome Screen.
	 */
	public void register() {
		System.out.println("Register Here!");
		System.out.println("(Enter 'q' to return to the Welcome Screen)");
		System.out.print("First name: ");
		String firstName = scanner.nextLine();
		if(firstName.equals("q")) {
			System.out.println("\n");
			return;
		}
		
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();
		if(lastName.equals("q")) {
			System.out.println("\n");
			return;
		}
		
		String username = "";
		boolean usernameIsTaken = true;
		while(usernameIsTaken) {
			System.out.print("Username: ");
			username = scanner.nextLine();
			if(username.equals("q")) {
				System.out.println("\n");
				return;
			}
			try {
				usernameIsTaken = service.checkIfUsernameIsTaken(username);
			} catch (UsernameIsAlreadyTakenException e) {
				System.out.println("That username is taken!");
				System.out.println("Try another one.");
			}
				
		}
		System.out.print("Password: ");
		String password = scanner.nextLine();
		if(password.equals("q")) {
			System.out.println("\n");
			return;
		}
		
		Customer newCustomer = new Customer(username, password, firstName, lastName, 0.00);
		service.registerNewCustomer(newCustomer);
		System.out.println("You have been added!");
		System.out.println("\n");
		//automatically returns to the Welcome Screen
	}

	/*
	 * Displays the Login Screen, where the user can log in to their account.
	 * 
	 * The user comes here from the Welcome Screen.
	 * 
	 * Completing login will send the user to the Main Menu.
	 * The user can return to the Welcome Screen by inputting 'q'.
	 */
	
	public void getLogin() {
		String username = "";
		String password = "";
		System.out.println("Log In Here!");
		System.out.println("(Enter 'q' to return to the Welcome Screen)");
		boolean notValidLogin = true;
		while(notValidLogin) {
			System.out.print("Username: ");
			username = scanner.nextLine();
			if(username.equals("q")) {
				System.out.println("\n");
				return;
			}
			System.out.print("Password: ");
			password = scanner.nextLine();
			if(password.equals("q")) {
				System.out.println("\n");
				//returns to the Welcome Screen
				return;
			}
			
			try {
				loginCustomer = service.getCustomerByUsernameAndPassword(username, password);
				notValidLogin = false;
			} catch (InvalidLoginCredentialsException e) {
				System.out.println("Invalid login. Try again.");
			}
		}
		System.out.println("\n\n");
		mainMenu();
	}
	
	/*
	 * Displays the Main Menu.
	 * 
	 * Users are initially sent here from the Login Screen.
	 * They are sent back here after depositing, withdrawing, or viewing transactions.
	 * 
	 * Entering '4' will take the user back to the Welcome Screen.
	 */
	public void mainMenu() {
		String input = "";
		while(!input.equals("4")) {
			System.out.println("Hello "+loginCustomer.getFirstName()+"!");
			System.out.println(String.format("Your current balance is $%.2f",loginCustomer.getBalance()));
			System.out.println("1) Deposit");
			System.out.println("2) Withdraw");
			System.out.println("3) View Transactions");
			System.out.println("4) Logout");
			input = scanner.nextLine();
			
			if(input.equals("1")) {
				System.out.println("\n");
				deposit();
			}else if(input.equals("2")) {
				System.out.println("\n");
				withdraw();
			}else if(input.equals("3")) {
				System.out.println("\n");
				viewTransactions();
			}else if(!input.equals("4")) {
				System.out.println("Sorry, I didn't get that.\n");
			}
		}
		//logging out
		System.out.println("Come again!\n");
		//automatically returns to Welcome Screen
	}
	
	/*
	 * The Deposit Screen.
	 * 
	 * The user comes here from the Main Menu.
	 * 
	 * The user will stay on this screen until they input 'q',
	 * 	which will return them to the Main Menu.
	 */
	public void deposit() {
		String input = "";
		double deposit = 0.0;
		while(!input.equals("q")) {
			System.out.println(String.format("Your current balance is $%.2f",loginCustomer.getBalance()));
			System.out.println("How much would you like to deposit?");
			System.out.println("(Enter 'q' to return to the Main Menu)");
			input = scanner.nextLine();
			if(input.equals("q")) {
				continue;
			}
			input = input.replaceAll("$", "");
			input = input.replaceAll("-", "");
			try {
				deposit = Double.parseDouble(input);
			} catch(Exception e) {
				System.out.println("Please enter a monetary value.\n");
				continue;
			}
			
			tranService.addNewTransaction("deposit", loginCustomer.getBalance(), (loginCustomer.getBalance()+deposit), loginCustomer);
			service.updateCustomerBalance(loginCustomer, loginCustomer.getBalance()+deposit);
			loginCustomer.setBalance(loginCustomer.getBalance()+deposit);
			
			System.out.println("Your deposit has been processed.");
		}
		System.out.println("\n");
		//automatically returns to the mainMenu()
	}
	
	/*
	 * The Withdraw Screen.
	 * 
	 * The user comes here from the Main Menu.
	 * 
	 * The user will stay on this screen until they input 'q',
	 * 	which will return them to the Main Menu.
	 */
	public void withdraw() {
		String input = "";
		double withdraw = 0.0;
		while(!input.equals("q")) {
			System.out.println(String.format("Your current balance is $%.2f",loginCustomer.getBalance()));
			System.out.println("How much would you like to withdraw?");
			System.out.println("(Enter 'q' to return to the Main Menu)");
			input = scanner.nextLine();
			if(input.equals("q")) {
				continue;
			}
			input = input.replaceAll("$", "");
			input = input.replaceAll("-", "");
			try {
				withdraw = Double.parseDouble(input);
			} catch(Exception e) {
				System.out.println("Please enter a monetary value.\n");
				continue;
			}
			try {
				tranService.addNewTransaction("withdraw", loginCustomer.getBalance(), (loginCustomer.getBalance()-withdraw), loginCustomer);
			} catch (OverdraftException e) {
				System.out.println("Please be reasonable. You don't have that much!");
				continue;
			}
			service.updateCustomerBalance(loginCustomer, loginCustomer.getBalance()-withdraw);
			loginCustomer.setBalance(loginCustomer.getBalance()-withdraw);
			
			System.out.println("Your withdraw has been processed.");
		}
		System.out.println("\n");
		//automatically returns to the mainMenu()
	}
	
	/*
	 * Displays a list of transaction that apply to the user.
	 * 
	 * Users are brought here from the Main Menu.
	 * 
	 * Pressing 'Enter' will return the user to the Main Menu.
	 */
	public void viewTransactions() {
		ArrayList<Transactions> transactions = tranService.getTransactionsForCustomer(loginCustomer);
		System.out.println(String.format(" %-18s | %-18s | %-18s | %-18s ", "Transaction Date", "Transaction Type", "Original Balance", "Updated Balance"));
		System.out.println("-------------------------------------------------------------------------------");
		if(transactions.isEmpty()) {
			System.out.println("It appears you haven't made any transactions since your account's creation.");
			System.out.println("To make a transaction, simply select 'Deposit' or 'Withdraw' from the Main Menu.\n");
			return;
		} else {
			for(Transactions transaction: transactions) {
				System.out.println(transaction.toString());
			}
		}
		System.out.println("Press enter to return to the Main Menu.");
		scanner.nextLine();
		System.out.println("\n");
		//automatically returns to the mainMenu()
	}
	
}
