package dev.carbajal.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

	private Integer accountNum;
	private String accountType;
	private boolean pendingAccount;
	private double accountBal;

	// Constructor

	public Account() {
		// empty constructor
	}

	public Account(int accountNum) {

		this.accountNum = accountNum;
	}

	public Account(int accountNum, String accountType, boolean pendingAccount, double accountBal) {

		this.accountNum = accountNum;
		this.accountType = accountType;
		this.pendingAccount = pendingAccount;
		this.accountBal = accountBal;
	}

	// Getters and Setters

	public int getAccountNum() {

		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public boolean isPendingAccount() {
		return pendingAccount;
	}

	public void setPendingAccount(boolean pendingAccount) {
		this.pendingAccount = pendingAccount;
	}

	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}

	@Override
	public String toString() {
		return "Account [accountNum = " + accountNum + ", accountType = " + accountType + ", accountBal = " + accountBal
				+ "]";
	}

	public void getCurrentAccount(int accountNum) {

		// needed to keep track of account being looked at?
		// enhanced for loop to iterate through accounts for index of accountNum? or maybe return acctNum from user table?

	}

	public void setCurrentAccount() {

	}

	///// Customer-specific Methods /////

	public void createAccount() {

		// implement a check for invalid starting balance and account type
		// then add to custAccounts list
	}

	public void viewAccountBal() {

	}

	///// Employee-specific Methods ///// - implement a check for employee boolean before allowing a call

	public void reviewAccounts() {

		// display pending accounts info
		// ask for input: "approve/deny acctNum#")
		// if no more pending accounts, no more accounts to review, exit

	}

	public void viewCustAccounts() {

		// view a customer's accounts by retrieving all associated with that user name

	}

	public void viewTransactions() {

		// return database transactions table (PK, Account#, USer, Amount, (maybe time stamp stored as string in java))

	}

}
