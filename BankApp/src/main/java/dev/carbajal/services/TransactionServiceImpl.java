package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;
import dev.carbajal.repositories.TransactionDAO;
import dev.carbajal.repositories.TransactionDAOImpl;

public class TransactionServiceImpl implements TransactionService {
	
	private static TransactionDAO tdao = new TransactionDAOImpl();

	@Override
	public boolean addTransaction(int uId, int acctnum, Float amount, String type) {
		
//		Transaction t = new Transaction(uId, acctnum, amount, type);
		return tdao.addTransaction(uId, acctnum, amount, type);
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
