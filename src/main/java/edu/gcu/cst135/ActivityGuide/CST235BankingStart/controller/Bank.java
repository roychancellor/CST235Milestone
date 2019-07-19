package edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

public class Bank {
	
	private String name;
	private static int currentCustomer; 
	List<Customer> customers = new ArrayList<>();
	
	public Bank(String name) {
		this.name = name;
		processCustMenu(Menus.custMenu());
	}
	
	private void processCustMenu(int option) {
		switch (option) {
			case 1: createCustomer(); break;
			case 2: pickCustomer(); break;
			default : System.exit(0);
		}
	}
	
	private void createCustomer() {
		
		String firstName = Menus.userStrInput("Enter the customer's first name: "); 
		String lastName = Menus.userStrInput("Enter the customer's last name: "); 
		customers.add(new Customer(firstName, lastName));
		processCustMenu(Menus.custMenu());
		
	}
	
	private void pickCustomer() {
		
		currentCustomer = Menus.pickCustomerMenu(customers) - 1 ;
		processCustomerMenu(Menus.viewCustomerMenu());
		
	}	
	
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
			processCustMenu(Menus.custMenu());
		}
	}

	private void viewEndOfMonth() {
		customers.get(currentCustomer).getSaving().doEndOfMonth();
		customers.get(currentCustomer).getChecking().doEndOfMonth();
		customers.get(currentCustomer).getLoan().doEndOfMonth();		
	}

	private void viewLoanPayment() {
		customers.get(currentCustomer).getLoan().doCredit(Menus.userDblInput("How much to pay on your loan?"));
	}

	private void viewWithdrawalSavings() {
		customers.get(currentCustomer).getSaving().doCredit(Menus.userDblInput("How much to withdraw from savings?"));	
	}

	private void viewWithdrawalChecking() {
		customers.get(currentCustomer).getChecking().doCredit(Menus.userDblInput("What is you check amount to withdraw from checking?"));			
	}

	private void viewDepositSavings() {
		customers.get(currentCustomer).getSaving().doDebit(Menus.userDblInput("How much to deposit into savings?"));	
	}

	private void viewBalances() {
		Menus.viewBalances(customers.get(currentCustomer));
		processCustomerMenu(Menus.viewCustomerMenu());	
	}

	private void viewDepositChecking() {
		customers.get(currentCustomer).getChecking().doDebit(Menus.userDblInput("How much to deposit into checking?"));	
		
	}

}
