package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Account;
import dev.carbajal.models.User;

public interface AccountService {
	
	public boolean addAcct(Account acc);
	
	public Account getBal(Account acc);
	
	public Account deposit(Account acc, double total);
	
	public Account withdraw(Account acc, double total);
	
	public Account transfer(Account acc1, Account acc2, double total);
	
	public List<Account> getAllUserAcct(User uid);
	
	public List<Account> getAllAcct();
	
	public boolean updateAcc(Account acc);
	
	public boolean deleteAcc(Account acc);

}
