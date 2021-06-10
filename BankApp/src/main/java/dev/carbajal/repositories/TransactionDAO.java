package dev.carbajal.repositories;

import java.util.List;

import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;

public interface TransactionDAO {
	
	// Create
	
	public boolean addTransaction(Transaction t);
	
	// Read
	
	public Transaction getTransaction(Transaction t);
	
	public List<Transaction> getUserTransaction(User u);
	
	public List<Transaction> getAllTransactions();
	
	// Update
	
	public boolean updateTransaction(Transaction t);
	
	// Delete
	
	public boolean deleteTransaction(Transaction t);

}
