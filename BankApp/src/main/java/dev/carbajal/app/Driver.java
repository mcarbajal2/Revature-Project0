package dev.carbajal.app;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.carbajal.models.User;
import dev.carbajal.models.Account;
import dev.carbajal.models.Transaction;
import dev.carbajal.repositories.UserDAO;
import dev.carbajal.repositories.UserDAOImpl;
import dev.carbajal.services.UserService;
import dev.carbajal.services.UserServiceImpl;
import dev.carbajal.repositories.AccountDAO;
import dev.carbajal.repositories.AccountDAOImpl;
import dev.carbajal.services.AccountService;
import dev.carbajal.services.AccountServiceImpl;
import dev.carbajal.repositories.TransactionDAO;
import dev.carbajal.repositories.TransactionDAOImpl;
import dev.carbajal.services.TransactionService;
import dev.carbajal.services.TransactionServiceImpl;


public class Driver {

	private static Scanner scanner = new Scanner(System.in);

	private static int introChoice;
	private static int cMenuChoice;
	private static int eMenuChoice;
	private static boolean programStatus;
	private static User currentUser;
	private static List<Account> tempList;
	private static Account temp;
	private static Account temp2;
	private static String tempInput;

	public static final Logger logger = LogManager.getLogger(Driver.class);

	private static UserDAO userDAO = new UserDAOImpl();
	private static UserService userS = new UserServiceImpl();
	private static AccountDAO acctDAO = new AccountDAOImpl();
	private static AccountService acctS = new AccountServiceImpl();
	private static TransactionDAO tranDAO = new TransactionDAOImpl();
	private static TransactionService tranS = new TransactionServiceImpl();


	public static void main(String[] args ) {

		// Start Main Method

		printIntroMenu();

		while (programStatus) {

			if (currentUser.isEmployeeAcct() == true) {

				employeeActionsMenu();

			} else if (currentUser.isEmployeeAcct() == false) {

				customerActionsMenu();
			}
		}
	}

	// Class Methods

	public static void printIntroMenu() {

		System.out.println("Welcome to Totally A Real Bank Name Bank!!\n\n"
				+ "Please select one of the following options:\n"
				+ "1. If you already have an account with us, please log in.\n"
				+ "2. If you would like to open a new account, please sign up today!\n"
				+ "3. Quit");

		introChoice = scanner.nextInt();
		parseIntroInput();
	}

	public static void parseIntroInput() {

		switch (introChoice) {

		case 1:
			programStatus = true;
			logInMenu();
			break;

		case 2:
			programStatus = true;
			newUserMenu();
			break;

		case 3:
			programStatus = false;
			break;

		default:
			System.out.println("Our system doesn't recognize that number, please try again.\n");
			printIntroMenu();
			break;
		}
	}

	public static void logInMenu() {

		System.out.println("- Log In -\n\n");
		System.out.println("Please enter your username:");
		String uName = scanner.next();
		System.out.println("Please enter your password:");
		String uPass = scanner.next();

		if(userDAO.getUserByUsername(uName) == null) {

			System.out.println("Incorrect log in information, please try again.\n");
			logInMenu();
		} else {

			currentUser = userDAO.getUserByUsername(uName);	

			if (userS.logIn(currentUser, uName, uPass)) {

				if (uName.equals("admin") && uPass.equals("password")) {

					logger.info("Employee, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", has logged in.");
					employeeActionsMenu();

				} else {
					logger.info("Customer, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", has logged in.");
					customerActionsMenu();
				}

			} else {

				System.out.println("Incorrect log in information, please try again.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				printIntroMenu();
			} 
		}
	}

	public static void newUserMenu() {	

		System.out.println("- Create Account -\n\n");
		System.out.println("Please enter the following information\n");
		System.out.println("Username: ");
		String uName = scanner.next();
		System.out.println("Password: ");
		String uPass = scanner.next();
		System.out.println("First Name: ");
		String uFName = scanner.next();
		System.out.println("Last Name: ");
		String uLName = scanner.next();

		User newUser = new User(uName, uPass, uFName, uLName, false);

		userDAO.addUser(newUser);

		currentUser = newUser;

		System.out.println("\nUser account created!\n");
		logger.info("New Customer, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", account created.");
		logInMenu();
	}

	public static void customerActionsMenu() {

		System.out.println("Welcome back " + currentUser.getFirstName() + "!\n\n"
				+ "Options:\n"
				+ "1. Open a new account\n"
				+ "2. View existing account(s) balance\n"
				+ "3. Deposit funds\n"
				+ "4. Withdraw funds\n"
				+ "5. Transfer funds to another account\n"
				+ "6. Log out");

		cMenuChoice = scanner.nextInt();
		parseCustomerActions();
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void parseCustomerActions() {

		switch (cMenuChoice) {

		case 1: // opening a new account
			System.out.println("New Account Type:\n"
					+ "1. Checking\n"
					+ "2. Savings");
			int tempAccType = scanner.nextInt();

			switch(tempAccType) {
			case 1:
				Account newCheckingAcct = new Account();
				newCheckingAcct.setUserId(currentUser.getId());
				newCheckingAcct.setAccountBal(0.00);
				newCheckingAcct.setAccountType("checking");
				newCheckingAcct.setPendingAccount(true);
				acctDAO.addAcct(newCheckingAcct);
				acctS.addAcct(newCheckingAcct);

				Transaction newCheckingTrans = new Transaction();
				newCheckingTrans.settType("checking account opened");
				tranDAO.addTransaction(newCheckingTrans);
				tranS.addTransaction(newCheckingTrans);

				System.out.println("Checking account is now pending review. You can check the status from the main menu.");
				break;

			case 2:
				Account newSavingsAcct = new Account();
				newSavingsAcct.setUserId(currentUser.getId());
				newSavingsAcct.setAccountBal(0.00);
				newSavingsAcct.setAccountType("savings");
				newSavingsAcct.setPendingAccount(true);
				acctDAO.addAcct(newSavingsAcct);
				acctS.addAcct(newSavingsAcct);

				Transaction newSavingsTrans = new Transaction();
				newSavingsTrans.settType("savings account opened");
				newSavingsTrans.setUserId(currentUser.getId());
				tranDAO.addTransaction(newSavingsTrans);
				tranS.addTransaction(newSavingsTrans);

				System.out.println("Savings account is now pending review. You can check the status from the main menu.");
				break;

			default:
				System.out.println("Our system doesn't recognize that number.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				break;
			}

			break;

		case 2: // view an account balance
			tempList = acctDAO.getAllUserAcct(currentUser);

			System.out.println("Your available accounts:\n\n"
					+ acctDAO.getAllUserAcct(currentUser) + "\n\n"
					+ "Which account would you like to view?");
			tempInput = scanner.next();

			if (tempList.contains(tempInput)) {

				acctDAO.getBal(tempList.get(Integer.valueOf(tempInput)));

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				customerActionsMenu();
			}

			break;

		case 3: // deposit
			tempList = acctDAO.getAllUserAcct(currentUser);

			System.out.println("Your available accounts:\n\n"
					+ acctDAO.getAllUserAcct(currentUser) + "\n\n"
					+ "Which account would you like to deposit to?");
			tempInput = scanner.next();

			if (tempList.contains(tempInput)) {

				temp = tempList.get(Integer.valueOf(tempInput));
				System.out.println("How much would you like to deposit?");
				tempInput = scanner.next();

				// system invalid transaction check

				if (Integer.valueOf(tempInput) > 0) {

					acctS.deposit(temp, Integer.valueOf(tempInput));

					Transaction newDepositTrans = new Transaction();
					newDepositTrans.settType("credit");
					newDepositTrans.setAccountNum(temp.getAccountNum());
					newDepositTrans.settAmount(Double.valueOf(tempInput));
					newDepositTrans.setUserId(currentUser.getId());

					tranS.addTransaction(newDepositTrans);

				} else {

					System.out.println("Deposit amount must be greater than zero.\n"
							+ "Exiting out of the transaction screen for security reasons, please try again.\n");
					customerActionsMenu();
				}

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				customerActionsMenu();
			}

			break;

		case 4: // withdraw
			tempList = acctDAO.getAllUserAcct(currentUser);

			System.out.println("Your available accounts:\n\n"
					+ acctDAO.getAllUserAcct(currentUser) + "\n\n"
					+ "Which account would you like to withdraw from?");
			tempInput = scanner.next();

			if (tempList.contains(tempInput)) {

				temp = tempList.get(Integer.valueOf(tempInput));
				System.out.println("How much would you like to withdraw?");
				tempInput = scanner.next();

				// system invalid transaction check

				if (Integer.valueOf(tempInput) < temp.getAccountBal()) {

					acctS.withdraw(temp, Integer.valueOf(tempInput));

					Transaction newWithdrawTrans = new Transaction();
					newWithdrawTrans.settType("debit");
					newWithdrawTrans.setAccountNum(temp.getAccountNum());
					newWithdrawTrans.settAmount(Double.valueOf(tempInput));
					newWithdrawTrans.setUserId(currentUser.getId());

					tranS.addTransaction(newWithdrawTrans);

				} else {

					System.out.println("Withdrawl amount more than account balance.\n"
							+ "Exiting out of the transaction screen for security reasons, please try again.\n");
					customerActionsMenu();
				}

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				customerActionsMenu();
			}

			break;

		case 5: // transfer funds
			tempList = acctDAO.getAllUserAcct(currentUser);

			System.out.println("Your available accounts:\n\n"
					+ acctDAO.getAllUserAcct(currentUser) + "\n\n"
					+ "Which account would you like to start transfering from?");
			tempInput = scanner.next();

			if (tempList.contains(tempInput)) {

				temp = tempList.get(Integer.valueOf(tempInput));
				System.out.println("Which account whould you like to transfer funds to?");
				tempInput = scanner.next();

				if(tempList.contains(tempInput)) {

					temp2 = tempList.get(Integer.valueOf(tempInput));
					System.out.println("Amount to transfer:?");
					tempInput = scanner.next();

					// system invalid transaction check

					if (Integer.valueOf(tempInput) < temp.getAccountBal()) {

						System.out.println("Transfer x to account x. Is this correct Y/N?");
						String answer = scanner.next();

						if (answer.equalsIgnoreCase("yes")) {

							acctS.withdraw(temp, Integer.valueOf(tempInput));
							acctS.deposit(temp2, Integer.valueOf(tempInput));

							Transaction newTransferTrans = new Transaction();
							newTransferTrans.settType("transfer - withdrawal");
							newTransferTrans.setAccountNum(temp.getAccountNum());
							newTransferTrans.settAmount(Double.valueOf(tempInput));
							newTransferTrans.setUserId(currentUser.getId());

							tranS.addTransaction(newTransferTrans);

							newTransferTrans.settType("transfer - deposit");
							newTransferTrans.setAccountNum(temp2.getAccountNum());
							newTransferTrans.settAmount(Double.valueOf(tempInput));
							newTransferTrans.setUserId(currentUser.getId());

							tranS.addTransaction(newTransferTrans);

						} else {

							System.out.println("Exiting out of the transaction screen for security reasons, please try again.\n");
							customerActionsMenu();
						}

					} else {

						System.out.println("Withdrawl amount more than account balance.\n"
								+ "Exiting out of the transaction screen for security reasons, please try again.\n");
						customerActionsMenu();
					}

				} else {

					System.out.println("An account with that number was not found.\n"
							+ "Exiting out of the transaction screen for security reasons, please try again.\n");
					customerActionsMenu();
				}

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				customerActionsMenu();
			}

			break;

		case 6: // log out
			currentUser = null;
			userS.logout();
			logger.info("Customer, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", has logged out.");
			printIntroMenu();
			break;

		default:
			System.out.println("Our system doesn't recognize that number, please try again.\n");
			customerActionsMenu();
			break;
		}
	}

	public static void employeeActionsMenu() {

		System.out.println("Welcome back " + currentUser.getFirstName() + "!\n\n"
				+ "Options:\n"
				+ "1. Review pending accounts\n"
				+ "2. View a user's accounts\n"
				+ "3. View all accounts\n"
				+ "4. View a recent transaction\n"
				+ "5. View all recent transactions\n"
				+ "6. Log out");

		eMenuChoice = scanner.nextInt();
		parseEmployeeActions();
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void parseEmployeeActions() {

		switch (eMenuChoice) {

		case 1: // review accounts
			tempList = acctDAO.getAllAcct();

			System.out.println("Accounts currently awaiting review:\n\n"
					+ acctDAO.getAllUserAcct(currentUser) + "\n\n"
					+ "Which account would you like to view?");
			tempInput = scanner.next();

			for (Account a : tempList) {

				if (a.isPendingAccount() == true) {

					acctDAO.updateAcc(a);
					acctS.updateAcc(a);

					Transaction newUpdateTrans = new Transaction();
					newUpdateTrans.settType("account approved");
					newUpdateTrans.setUserId(currentUser.getId());
					tranS.addTransaction(newUpdateTrans);
					
					System.out.println("Account " + a.getAccountNum() + " successfully updated!\n");
					
				} else {

					System.out.println("An account with that number was not found.\n"
							+ "Exiting out of the transction screen for security reasons, please try again.\n");
					employeeActionsMenu();
				}
			}
			break;

		case 2: // view a user's accounts
			System.out.println("All available accounts");
			for(Account a : tempList) {

				System.out.println(a.getUserId());
			}

			System.out.println("Which user's accounts would you like to view?");
			tempInput = scanner.next();

			if (tempList.contains(tempInput)) {

				currentUser.setId(Integer.valueOf(tempInput));
				System.out.println(acctDAO.getAllUserAcct(currentUser));

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transction screen for security reasons, please try again.\n");
				employeeActionsMenu();
			}

			break;

		case 3: // view all accounts
			tempList = acctDAO.getAllAcct();
			System.out.println("All User accounts:\n\n");

			for(Account a : tempList) {

				System.out.println(a);
			}

			break;

		case 4: // view transaction
			System.out.println("All available account transactions:");

			for (Transaction t : tranDAO.getAllTransactions()) {

				System.out.println(tranDAO.getTransaction(t));
			}

			System.out.println("Which user's transactions would you like to view?");
			tempInput = scanner.next();

			if (tempList.contains(tempInput)) {

				currentUser.setId(Integer.valueOf(tempInput));
				System.out.println(acctDAO.getAllUserAcct(currentUser));

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transction screen for security reasons, please try again.\n");
				employeeActionsMenu();
			}

			break;

		case 5: // view all transactions
			
			System.out.println("All User transactions:\n\n");

			for (Transaction t : tranDAO.getAllTransactions()) {

				System.out.println(tranDAO.getTransaction(t));
			}
			
			employeeActionsMenu();
			break;

		case 6: // log out
			currentUser = null;
			userS.logout();
			logger.info("Employee, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", has logged out.");
			printIntroMenu();
			break;

		default:
			System.out.println("Our system doesn't recognize that number, please try again.\n");
			employeeActionsMenu();
			break;
		}
	}
}
