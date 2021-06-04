package dev.carbajal.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	
	int accountNum;
	String accountAppStatus;
	String accountType;
	double accountBal;
	
	List<Account> accounts = new ArrayList<Account>();
	
	// Constructor
	
	public Account() {
		
	}
	
	public Account(int accountNum) {
		
		this.accountNum = accountNum;
	}
	
	public Account(int accountNum, String accountAppStatus, String accountType, double accountBal) {
		
		this.accountNum = accountNum;
		this.accountAppStatus = accountAppStatus;
		this.accountType = accountType;
		this.accountBal = accountBal;
	}
	
	// Getters and Setters
	// user table FK to account table to get correct account info
	
	public void getCurrentAccount(int accountNum) {
		
		// enhanced for loop to iterate through accounts for index of accountNum? or maybe return acctNum from user table?
		
	}
	
	public void setCurrentAccount() {
		
	}

	public int getAccountNumber() {
		
		return accountNum;
	}

	public void setAccountNumber(int accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
	
}