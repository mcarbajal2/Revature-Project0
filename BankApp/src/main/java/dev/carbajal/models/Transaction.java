package dev.carbajal.models;

public class Transaction {

	private static double transactionAmount;

	public void withdrawal(double transactionAmount) {

		this.transactionAmount = transactionAmount;
	}

	public void deposit(double transactionAmount) {

		this.transactionAmount = transactionAmount;
	}

	public void transfer(double transactionAmount) {

		this.transactionAmount = transactionAmount;
	}

}
