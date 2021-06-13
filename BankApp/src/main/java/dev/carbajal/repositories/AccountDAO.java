package dev.carbajal.repositories;

import java.util.List;

import dev.carbajal.models.Account;
import dev.carbajal.models.User;

public interface AccountDAO {
	
	// Create
	
	public boolean addAcct(Account acc);
	
	// Read
	
	public List<Account> getAllUserAcct(User uid);
	
	public List<Account> getAllAcct();
	
	public Float getBalByNum(Account acc);
	
	public Account getAcctByNum(int accnum);
	
	public boolean checkIfAcctExists(int accnum);
	
	// Update
	
	public boolean updateAcc(int accnum);
	
	public boolean deposit(Account acc, Float total);
	
	public boolean withdraw(Account acc, Float total);
	
	public boolean transfer(Account acc1, Account acc2, Float total);
	
	// Delete
	
	public boolean deleteAcc(Account acc);

}
