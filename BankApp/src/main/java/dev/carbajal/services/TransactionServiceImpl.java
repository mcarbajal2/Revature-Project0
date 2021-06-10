package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;
import dev.carbajal.repositories.TransactionDAO;
import dev.carbajal.repositories.TransactionDAOImpl;

public class TransactionServiceImpl implements TransactionService {
	
	private static TransactionDAO tdao = new TransactionDAOImpl();

	@Override
	public boolean addTransaction(Transaction t) {
		
		return tdao.addTransaction(t);
	}

	@Override
	public Transaction getTransaction(Transaction t) {
		
		return tdao.getTransaction(t);
	}

	@Override
	public boolean updateTransaction(Transaction t) {
		
		return tdao.updateTransaction(t);
	}

	@Override
	public boolean deleteTransaction(Transaction t) {
		
		return tdao.deleteTransaction(t);
	}

	@Override
	public List<Transaction> getUserTransaction(User u) {
		
		return tdao.getUserTransaction(u);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		return tdao.getAllTransactions();
	}
}
