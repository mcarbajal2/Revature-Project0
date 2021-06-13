package dev.carbajal.models;

public class Account {

	private Integer accountNum;
	private Float accountBal;
	private String accountType;
	private boolean isPending;
	private Integer userId;
	
	// Constructor

	public Account() {
		super();
	}

	public Account(int userId) {
		
		this.setUserId(userId);
	}

	public Account(int accountNum, String accountType, boolean isPending, Float accountBal, Integer userId) {

		this.accountNum = accountNum;
		this.accountType = accountType;
		this.isPending = true;
		this.accountBal = 0.00F;
		this.userId = userId;
	}

	// Getters and Setters
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {	
		this.accountNum = accountNum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public boolean isPendingAccount() {
		return isPending;
	}

	public void setPendingAccount(boolean isPending) {
		this.isPending = isPending;
	}

	public float getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(Float accountBal) {
		this.accountBal = accountBal;
	}

	@Override
	public String toString() {
		return "Account (" + accountNum + ") - Type: " + accountType
				+ ", Pending Status: " + isPending + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBal == null) ? 0 : accountBal.hashCode());
		result = prime * result + ((accountNum == null) ? 0 : accountNum.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + (isPending ? 1231 : 1237);
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountBal == null) {
			if (other.accountBal != null)
				return false;
		} else if (!accountBal.equals(other.accountBal))
			return false;
		if (accountNum == null) {
			if (other.accountNum != null)
				return false;
		} else if (!accountNum.equals(other.accountNum))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (isPending != other.isPending)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
