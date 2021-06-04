package dev.carbajal.models;

public class Transaction {
	
	private static double depositAmount;
	private static double withdrawAmount;
	private static double transferAmount;
	
	public void withdrawal(double withdrawAmount) {
		
		this.withdrawAmount = withdrawAmount;
	}

	public void deposit(double depositAmount) {
		
		this.depositAmount = depositAmount;
	}

	public void transferFunds(double transferAmount) {
		
		this.transferAmount = transferAmount;
	}

}