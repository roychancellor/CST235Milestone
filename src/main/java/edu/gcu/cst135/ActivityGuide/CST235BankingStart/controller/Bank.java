package edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

public class Bank {
	
	// Class attributes
	private String name; // Hold the bank name
	private static int currentCustomer;  // Hold the current (picked) customer 
	List<Customer> customers = new ArrayList<>(); // Hold a list of ALL customers
	
	// Constructor
	public Bank(String name) {
		this.name = name;	
	}
	
	// Starting point
	public void start() { 
		int option; 
		// Keep going until they select a valid option
		do {
			option = Menus.custMenu();
		}while (option < 0 || option > 2);
		processCustMenu(option);
	}
	
	// Process what menu item the user picked
	private void processCustMenu(int option) {
		switch (option) {
			case 1: createCustomer(); break;
			case 2: pickCustomer(); break;
			default : System.exit(0);
		}
	}
	
	// Create a customer and add to the customers list
	private void createCustomer() {
		
		String firstName = Menus.userStrInput("Enter the customer's first name: "); 
		String lastName = Menus.userStrInput("Enter the customer's last name: "); 
		// CST235 TASK: REQUIRE THE CUSTOMER TO ENTER A USERNAME
		// CST235 TASK: REQUIRE THE CUSTOMER TO ENTER A PASSWORD
		customers.add(new Customer(firstName, lastName));
		// Display the customer menu
		processCustMenu(Menus.custMenu());
		
	}
	
	// Process the selected customer to use for banking transactions
	private void pickCustomer() {
		do {
			// CST235 TASK: REMOVE THE LIST ARGUMENT
			currentCustomer = Menus.pickCustomerMenu(customers) - 1 ;
		}while (currentCustomer > (customers.size() - 1));
		
		// If the use picked -1 (e.g. 0-1) send them back to the beginning
		if (currentCustomer == -1)
			start();
		else {
			// send the current user to bank transaction menu
			// CST235 TASK: ADD A LOGIN PIECE FOR CUSTOMER
			// CST235 TASK: REQUIRE USER FOR USERNAME AND PASSWORD
			// CST235 TASK: BEFORE THEY GET TO THE TRANSACTION MENU
			processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer), name));
		}
	}	
	
	// Process the bank transaction menu option
	private void processCustomerMenu(int parseInt) {

		switch(parseInt) {
		case 1: 
			viewDepositChecking();viewBalances();
			break;
		case 2: 
			viewDepositSavings();viewBalances();
			break;
		case 3: 
			viewWithdrawalChecking();viewBalances();
			break;
		case 4: 
			viewWithdrawalSavings();viewBalances();
			break;
		case 5: 
			viewBalances();
			break;
		case 6: 
			viewLoanPayment();viewBalances();
			break;
		case 7: 
			viewEndOfMonth();viewBalances();
			break;  
		default: 
			// Back to the starting point and reset current customer
			currentCustomer = 0;
			processCustMenu(Menus.custMenu());
		}
	}

	// Execute each end of month methods in each class
	private void viewEndOfMonth() {
		customers.get(currentCustomer).getSaving().doEndOfMonth();
		customers.get(currentCustomer).getChecking().doEndOfMonth();
		customers.get(currentCustomer).getLoan().doEndOfMonth();		
	}

	// display and process loan payment
	private void viewLoanPayment() {
		customers.get(currentCustomer).getLoan().doDebit(Menus.userDblInput("How much to pay on your loan?"));
	}
	
	// display and process savings withdraw
	private void viewWithdrawalSavings() {
		customers.get(currentCustomer).getSaving().doDebit(Menus.userDblInput("How much to withdraw from savings?"));	
	}

	// display and process checking withdraw
	private void viewWithdrawalChecking() {
		customers.get(currentCustomer).getChecking().doDebit(Menus.userDblInput("What is you check amount to withdraw from checking?"));			
	}

	// display and process savings deposit
	private void viewDepositSavings() {
		customers.get(currentCustomer).getSaving().doCredit(Menus.userDblInput("How much to deposit into savings?"));	
	}
	// display and process checking deposit	
	private void viewDepositChecking() {
		customers.get(currentCustomer).getChecking().doCredit(Menus.userDblInput("How much to deposit into checking?"));	
	}
	
	// display balances
	private void viewBalances() {
		Menus.viewBalances(customers.get(currentCustomer));
		processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer), name));	
	}

}
