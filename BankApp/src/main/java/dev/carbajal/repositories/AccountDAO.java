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
	
	public Account getBal(Account acc);
	
	// Update
	
	public boolean updateAcc(Account acc);
	
	// Delete
	
	public boolean deleteAcc(Account acc);

}
