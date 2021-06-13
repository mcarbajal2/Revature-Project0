package dev.carbajal.app;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.carbajal.models.User;
import dev.carbajal.models.Account;
import dev.carbajal.repositories.UserDAO;
import dev.carbajal.repositories.UserDAOImpl;
import dev.carbajal.services.UserService;
import dev.carbajal.services.UserServiceImpl;
import dev.carbajal.repositories.AccountDAO;
import dev.carbajal.repositories.AccountDAOImpl;
import dev.carbajal.services.AccountService;
import dev.carbajal.services.AccountServiceImpl;
import dev.carbajal.services.TransactionServiceImpl;
import dev.carbajal.repositories.TransactionDAO;
import dev.carbajal.repositories.TransactionDAOImpl;


public class Driver {

	private static Scanner scanner = new Scanner(System.in);

	private static int introChoice;
	private static int cMenuChoice;
	private static int eMenuChoice;
	private static boolean programStatus;
	private static User currentUser;
	private static Account temp;
	private static Account temp2;
	private static int tempInput;
	private static float tempAmount;
	private static String tempString;

	public static final Logger logger = LogManager.getLogger(Driver.class);

	private static UserDAO userDAO = new UserDAOImpl();
	private static UserService userS = new UserServiceImpl();
	private static AccountDAO acctDAO = new AccountDAOImpl();
	private static AccountService acctS = new AccountServiceImpl();
	private static TransactionDAO tranDAO = new TransactionDAOImpl();
	private static TransactionServiceImpl tranS = new TransactionServiceImpl();


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

		System.out.println("- Log In -\n");
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

		System.out.println("- Create Account -\n");
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

		System.out.println("\n- Main Menu -");
		System.out.println("Welcome back " + currentUser.getFirstName() + "!\n");

		if (acctDAO.getAllUserAcct(currentUser) == null) {

			System.out.println("Your current accounts: None, please open an account to user the banking features.\n");
		}

		System.out.println("Options:\n"
				+ "1. Open a new account\n"
				+ "2. View existing account(s) balance\n"
				+ "3. Deposit funds\n"
				+ "4. Withdraw funds\n"
				+ "5. Transfer funds to another account\n"
				+ "6. Log out");

		cMenuChoice = scanner.nextInt();
		parseCustomerActions();
	}

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
				newCheckingAcct.setAccountBal(0.00F);
				newCheckingAcct.setAccountType("checking");
				newCheckingAcct.setPendingAccount(true);
				acctDAO.addAcct(newCheckingAcct);
				acctS.addAcct(newCheckingAcct);

				logger.info("New checking account created for " + currentUser.getFirstName() + " " + currentUser.getLastName());
				System.out.println("Checking account is now pending review. You can check the status from the main menu.");
				break;

			case 2:

				Account newSavingsAcct = new Account();
				newSavingsAcct.setUserId(currentUser.getId());
				newSavingsAcct.setAccountBal(0.00F);
				newSavingsAcct.setAccountType("savings");
				newSavingsAcct.setPendingAccount(true);
				acctDAO.addAcct(newSavingsAcct);
				acctS.addAcct(newSavingsAcct);

				logger.info("New savings account created for " + currentUser.getFirstName() + " " + currentUser.getLastName());
				System.out.println("Savings account is now pending review. You can check the status from the main menu.\n");
				break;

			default:
				System.out.println("Our system doesn't recognize that number.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				break;
			}

			break;

		case 2: // view an account balance

			System.out.println("Your available accounts:\n");
			System.out.println(acctDAO.getAllUserAcct(currentUser).toString());
			System.out.println("\nWhich account would you like to view?");
			tempInput = scanner.nextInt();

			if (acctDAO.checkIfAcctExists(tempInput)) {

				System.out.println("Account balance: " + acctDAO.getBalByNum(acctDAO.getAcctByNum(tempInput)));

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				customerActionsMenu();
			}

			break;

		case 3: // deposit

			System.out.println("Your available accounts:\n");
			System.out.println(acctDAO.getAllUserAcct(currentUser).toString());
			System.out.println("\nWhich account would you like to deposit to?");
			tempInput = scanner.nextInt();

			if (acctDAO.checkIfAcctExists(tempInput)) {

				temp = acctDAO.getAcctByNum(tempInput);

				if (temp.isPendingAccount() == false) {

					while (true) {

						System.out.println("Amount to deposit:");
						tempAmount = scanner.nextFloat();

						if (tempAmount > 0.00F) {

							System.out.println("Is $" + tempAmount + " correct? Y/N");
							tempString = scanner.next();

							if (tempString.equalsIgnoreCase("Y")) {

								acctDAO.deposit(acctDAO.getAcctByNum(tempInput), tempAmount);
//								tranDAO.addTransaction(currentUser.getId(), tempInput, tempAmount, "deposit");
								tranS.addTransaction(currentUser.getId(), tempInput, tempAmount, "deposit");
								logger.info("New deposit created for Account (" + tempInput + ") in the amount of $" + tempAmount);
								System.out.println("Deposit complete!");
								break;

							} else if (tempString.equalsIgnoreCase("N")) {

								continue;

							} else {

								System.out.println("Our system doesn't recognize that input.\n"
										+ "Please try again.\n");

								continue;
							}

						} else {

							System.out.println("Deposit amount must be greater than zero.\n"
									+ "Exiting out of the transaction screen for security reasons, please try again.\n");
							customerActionsMenu();
						}
					}

				} else {

					System.out.println("Account must be approved before you can use it.\n"
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

			System.out.println("Your available accounts:\n");
			System.out.println(acctDAO.getAllUserAcct(currentUser).toString());
			System.out.println("\nWhich account would you like to withdraw from?");
			tempInput = scanner.nextInt();

			if (acctDAO.checkIfAcctExists(tempInput)) {

				temp = acctDAO.getAcctByNum(tempInput);

				if (temp.isPendingAccount() == false) {

					while (true) {

						System.out.println("Amount to withdraw:");
						tempAmount = scanner.nextFloat();

						if (tempAmount < temp.getAccountBal()) {

							System.out.println("Is $" + tempAmount + " correct? Y/N");
							tempString = scanner.next();

							if (tempString.equalsIgnoreCase("Y")) {

								acctDAO.withdraw(acctDAO.getAcctByNum(tempInput), tempAmount); 
//								tranDAO.addTransaction(currentUser.getId(), tempInput, tempAmount, "withdrawal");
								tranS.addTransaction(currentUser.getId(), tempInput, tempAmount, "withdrawal");
								logger.info("New withdrawal created for Account (" + tempInput + ") in the amount of $" + tempAmount);
								System.out.println("Withdrawal complete! Returning to the main menu.");
								break;

							} else if (tempString.equalsIgnoreCase("N")) {

								continue;

							} else {

								System.out.println("Our system doesn't recognize that input.\n"
										+ "Please try again.\n");

								continue;
							}

						} else {

							System.out.println("Withdrawal amount more than account balance.\n"
									+ "Exiting out of the transaction screen for security reasons, please try again.\n");
							customerActionsMenu();
						}
					}

				} else {

					System.out.println("Account must be approved before you can use it.\n"
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

			System.out.println("Your available accounts:\n");
			System.out.println(acctDAO.getAllUserAcct(currentUser).toString());
			System.out.println("\nWhich account would you like to transfer from?");
			tempInput = scanner.nextInt();

			if (acctDAO.checkIfAcctExists(tempInput)) {

				temp = acctDAO.getAcctByNum(tempInput);

				if (temp.isPendingAccount() == false) {

					System.out.println("\nWhich account would you like to transfer to?");
					tempInput = scanner.nextInt();

					if (acctDAO.checkIfAcctExists(tempInput)) {

						temp2 = acctDAO.getAcctByNum(tempInput);

						if (temp2.isPendingAccount() == false) {

							while (true) {

								System.out.println("Amount to transfer:");
								tempAmount = scanner.nextFloat();

								if (tempAmount < temp.getAccountBal()) {

									System.out.println("Is $" + tempAmount + " correct? Y/N");
									tempString = scanner.next();

									if (tempString.equalsIgnoreCase("Y")) {

										acctDAO.transfer(temp, temp2, tempAmount); 
//										tranDAO.addTransaction(currentUser.getId(), temp.getAccountNum(), tempAmount, "transfer");
										tranS.addTransaction(currentUser.getId(), temp.getAccountNum(), tempAmount, "transfer");
										tranS.addTransaction(currentUser.getId(), temp2.getAccountNum(), tempAmount, "transfer");
										logger.info("New transfer created for Account (" + temp.getAccountNum() + ") to Account (" + temp2.getAccountNum() + ") in the amount of $" + tempAmount);
										System.out.println("Transfer complete! Returning to the main menu.");
										break;

									} else if (tempString.equalsIgnoreCase("N")) {

										continue;

									} else {

										System.out.println("Our system doesn't recognize that input.\n"
												+ "Please try again.\n");

										continue;
									}

								} else {

									System.out.println("Withdrawal amount more than account balance.\n"
											+ "Exiting out of the transaction screen for security reasons, please try again.\n");
									customerActionsMenu();
								}
							}

						} else {

							System.out.println("Account must be approved before you can use it.\n"
									+ "Exiting out of the transaction screen for security reasons, please try again.\n");
							customerActionsMenu();
						}

					} else {

						System.out.println("An account with that number was not found.\n"
								+ "Exiting out of the transaction screen for security reasons, please try again.\n");
						customerActionsMenu();		
					}

				} else {

					System.out.println("Account must be approved before you can use it.\n"
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

			logger.info("Customer, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", has logged out.");
			userS.logout();
			currentUser = null;
			printIntroMenu();
			break;

		default:

			System.out.println("Our system doesn't recognize that option, please try again.\n");
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
				+ "4. View all recent transactions\n"
				+ "5. Log out");

		eMenuChoice = scanner.nextInt();
		parseEmployeeActions();
	}

	public static void parseEmployeeActions() {

		switch (eMenuChoice) {

		case 1: // review accounts

			System.out.println("\nAll accounts:\n\n"
					+ acctDAO.getAllAcct() + "\n\n"
					+ "Which account would you like to approve?");
			tempInput = scanner.nextInt();

			if (acctDAO.checkIfAcctExists(tempInput)) {

				temp = acctDAO.getAcctByNum(tempInput);

				if (temp.isPendingAccount() == true) {

					acctDAO.updateAcc(tempInput);
					acctS.updateAcc(tempInput);

					System.out.println("Account (" + tempInput + ") successfully updated!\n");

				} else {

					System.out.println("That account is already approved and does not require reviewing.\n"
							+ "Exiting out of the transaction screen for security reasons, please try again.\n");
					employeeActionsMenu();
				}
			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				employeeActionsMenu();
			}

			break;

		case 2: // view a user's accounts

			System.out.println("\nAll Users:\n\n"
					+ userDAO.getAllUsers() + "\n\n"
					+ "Which user (id number) would you like to view?");
			tempInput = scanner.nextInt();

			if (userDAO.checkIfUserExists(tempInput)) {

				System.out.println(acctDAO.getAllUserAcct(userDAO.getUser(tempInput)));

			} else {

				System.out.println("An account with that number was not found.\n"
						+ "Exiting out of the transaction screen for security reasons, please try again.\n");
				employeeActionsMenu();
			}

			break;

		case 3: // view all accounts

			System.out.println("\nAll accounts:\n\n"
					+ acctDAO.getAllAcct() + "\n\n");

			break;

		case 4: // view transaction

			System.out.println("All User transactions:\n\n"
					+ tranDAO.getAllTransactions() + "\n\n");

			employeeActionsMenu();
			break;

		case 5: // log out

			logger.info("Employee, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", has logged out.");
			userS.logout();
			currentUser = null;
			printIntroMenu();
			break;

		default:

			System.out.println("Our system doesn't recognize that number, please try again.\n");
			employeeActionsMenu();
			break;
		}
	}
}
