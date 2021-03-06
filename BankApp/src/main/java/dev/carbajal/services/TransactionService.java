package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;

public interface TransactionService {

	public boolean addTransaction(int uId, int acctnum, Float amount, String type);

	public Transaction getTransaction(Transaction t);

	public boolean updateTransaction(Transaction t);

	public boolean deleteTransaction(Transaction t);

	public List<Transaction> getUserTransaction(User u);

	public List<Transaction> getAllTransactions();

}
