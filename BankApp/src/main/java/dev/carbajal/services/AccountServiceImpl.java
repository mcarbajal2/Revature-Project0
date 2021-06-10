package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Account;
import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;
import dev.carbajal.repositories.AccountDAO;
import dev.carbajal.repositories.AccountDAOImpl;
import dev.carbajal.repositories.TransactionDAO;
import dev.carbajal.repositories.TransactionDAOImpl;

public class AccountServiceImpl implements AccountService {
	
	private static AccountDAO adao = new AccountDAOImpl();
	private static TransactionDAO tdao = new TransactionDAOImpl();

	@Override
	public boolean addAcct(Account acc) {
		
		return adao.addAcct(acc);
	}

	@Override
	public Account getBal(Account acc) {
		
		return adao.getBal(acc);
	}

	@Override
	public Account deposit(Account acc, double total) {
		
		acc.setAccountBal(acc.getAccountBal() + total);
		adao.updateAcc(acc);
		
		Transaction t = new Transaction(acc.getAccountNum(), total, "deposit");
		tdao.addTransaction(t);
		
		return acc;
	}

	@Override
	public Account withdraw(Account acc, double total) {
		
		acc.setAccountBal(acc.getAccountBal() - total);
		adao.updateAcc(acc);
		
		Transaction t = new Transaction(acc.getAccountNum(), total, "withdrawal");
		tdao.addTransaction(t);
		
		return acc;
	}
	
	@Override
	public Account transfer(Account acc1, Account acc2, double total) {
		
		acc1.setAccountBal(acc1.getAccountBal() - total);
		adao.updateAcc(acc1);
		
		acc2.setAccountBal(acc2.getAccountBal() + total);
		adao.updateAcc(acc2);
		
		Transaction t = new Transaction(acc1.getAccountNum(), total, "transfer funds");
		tdao.addTransaction(t);
		
		return acc1;
	}

	@Override
	public List<Account> getAllUserAcct(User uid) {
		
		return adao.getAllUserAcct(uid);
	}

	@Override
	public List<Account> getAllAcct() {
		
		return adao.getAllAcct();
	}

	@Override
	public boolean updateAcc(Account acc) {
		
		return adao.updateAcc(acc);
	}

	@Override
	public boolean deleteAcc(Account acc) {
		
		return adao.deleteAcc(acc);
	}
}
