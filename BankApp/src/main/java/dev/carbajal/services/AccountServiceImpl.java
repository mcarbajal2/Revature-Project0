package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Account;
//import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;
import dev.carbajal.repositories.AccountDAO;
import dev.carbajal.repositories.AccountDAOImpl;
//import dev.carbajal.repositories.TransactionDAO;
//import dev.carbajal.repositories.TransactionDAOImpl;

public class AccountServiceImpl implements AccountService {
	
	private static AccountDAO adao = new AccountDAOImpl();
//	private static TransactionDAO tdao = new TransactionDAOImpl();
//	private static TransactionService tranS = new TransactionServiceImpl();

	@Override
	public boolean addAcct(Account acc) {
		
		return adao.addAcct(acc);
	}

	@Override
	public Float getBalByNum(Account acc) {
		
		return adao.getBalByNum(acc);
	}
	
	@Override
	public Account getAcctByNum(int accnum) {
		
		return adao.getAcctByNum(accnum);
	}
	
	@Override
	public boolean checkIfAcctExists(int accnum) {
		
		return adao.checkIfAcctExists(accnum);
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
	public boolean updateAcc(int accnum) {
		
		return adao.updateAcc(accnum);
	}

	@Override
	public boolean deleteAcc(Account acc) {
		
		return adao.deleteAcc(acc);
	}
}
