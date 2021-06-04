package dev.carbajal.models;

public class User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String accountType;
	
	
	public User(String username, String password, String firstName, String lastName, String accountType) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
	}
	
	// Customer Methods
	
	public void createAccount() {

		// implement a check for invalid starting bal and account type
		// then add to custAccounts list
	}

	public void viewAccountBal() {

	}
	
	// Employee Methods
	
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
