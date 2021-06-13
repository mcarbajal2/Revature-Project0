package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.Account;
import dev.carbajal.models.User;

public interface AccountService {
	
	public boolean addAcct(Account acc);
	
	public boolean checkIfAcctExists(int accnum);
	
	public Float getBalByNum(Account acc);
	
	public Account getAcctByNum(int accnum);
	
	public List<Account> getAllUserAcct(User uid);
	
	public List<Account> getAllAcct();
	
	public boolean updateAcc(int accnum);
	
	public boolean deleteAcc(Account acc);

}
