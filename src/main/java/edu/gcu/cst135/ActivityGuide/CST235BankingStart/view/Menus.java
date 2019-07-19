package edu.gcu.cst135.ActivityGuide.CST235BankingStart.view;

import java.util.List;
import java.util.Scanner;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Account;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Transaction;

public class Menus {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static int custMenu() {
		int option = 0;
		try {
			do {
				System.out.println("***************************");
				System.out.println("  BANK CUSTOMER MENU  ");
				System.out.println("***************************");
				System.out.println(" 1. Create a customer");
				System.out.println(" 2. Pick a customer");
				System.out.println("---------------------------");
				System.out.println(" 0. Exit");
				System.out.println("***************************");
				System.out.println("What is your choice?");
				String opt = sc.nextLine();
				option = Integer.parseInt(opt);
			}while (option > 2);
		} catch (Exception e) {
			System.out.println("Bad customer menu input. Try again!");
			option = -1;
		}
		
		return option;
	}
	
	public static int pickCustomerMenu(List<Customer> custs) {
		int number;
		int cust = 0;
		try {
			do {
				number = 1;
				System.out.println("***************************");
				System.out.println("  PICK CUSTOMER MENU  ");
				System.out.println("***************************");
	
				for (Customer c : custs) {
					System.out.println(number + ". " + c.toString());
					number++;
				}
				
				System.out.println("---------------------------");
				System.out.println(" 0. Exit");
				System.out.println("***************************");
				System.out.println("Who is your choice?");
				cust = sc.nextInt();
				sc.nextLine();
			}while (cust > number);
		} catch (Exception e) {
			System.out.println("Bad customer select. Try again!");
			custMenu();
		}
		
		return cust;
	}
	
	public static String userStrInput(String message) {
		System.out.println(message);
		return sc.nextLine();
	}
	
	public static double userDblInput(String message) {	
		double amount = 0.0;
		try {
			System.out.println(message);
			String input = sc.nextLine();
			amount = Double.parseDouble(input);
		}catch(Exception e) {
			System.out.println("Wrong input\n");
			amount = -1.0;
		}
		return amount;
	}
	
	public static int viewCustomerMenu() {

		try {
			String option;
			do {
				System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("                MAIN MENU");
				System.out.println("                GCU BANK");
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("Pick an option: ");
				System.out.println("-----------------------");
				System.out.println(" 1: Deposit to Checking");
				System.out.println(" 2: Deposit to Savings");
				System.out.println(" 3: Withdraw from Checking");
				System.out.println(" 4: Withdraw from Savings");			
				System.out.println(" 5: Get balance");
				System.out.println(" 6: Make Loan Payment");
				System.out.println(" 7: Get monthly statement");
				System.out.println("------------------------");
				System.out.println(" 9: : Logout");
				option = sc.nextLine();
				return Integer.parseInt(option);
			} while (Integer.parseInt(option) != 9);
		}catch(Exception e) {
			System.out.println("Wrong transaction menu input\n");
			viewCustomerMenu();
		}
		return 0;
	}
	
	public static void viewBalances(Customer cust) {
		System.out.println("------------------------");
		System.out.println("CUSTOMER BALANCES");
		System.out.println("------------------------");
		System.out.println("CHECKING : \t" + cust.getChecking().getAccountNumber() + " \t $" + cust.getChecking().getAccountBalance() );
		System.out.println("SAVING :   \t" + cust.getSaving().getAccountNumber() + " \t $" + cust.getSaving().getAccountBalance() );
		System.out.println("LOAN :     \t" + cust.getLoan().getAccountNumber() + " \t $" + cust.getLoan().getAccountBalance() );
		System.out.println("------------------------");
	}
	
	public static void printOut(String message) {
		System.out.println(" > " + message);
	}

	public static <T> void printBalance(T obj){
		System.out.println(((Account) obj).getAccountNumber() + " : $" + ((Account) obj).getAccountBalance());
	}
}
