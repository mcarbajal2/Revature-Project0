package dev.carbajal.models;

public class Transaction {
	
	private Integer id;
	private Integer userId;
	private Integer accountNum;
	private Float tAmount;
	private String tType;
	
	// Constructors
	
	public Transaction() {
		super();
	}
	
	public Transaction(Integer userId, Integer accountNum, Float tAmount, String tType) {
		super();
		this.accountNum = accountNum;
		this.tAmount = tAmount;
		this.tType = tType;
	}

	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getAccountNum() {
		return accountNum;
	}
	
	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}
	
	public Float gettAmount() {
		return tAmount;
	}
	
	public void settAmount(Float tAmount) {
		this.tAmount = tAmount;
	}
	
	public String gettType() {
		return tType;
	}
	
	public void settType(String tType) {
		this.tType = tType;
	}
	
	@Override
	public String toString() {
		return "Transaction(" + id + ") - User ID: " + userId + ", Account Number: " + accountNum + ", Transaction Amount: " + tAmount
				+ ", Transaction Type: " + tType + "\n";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNum == null) ? 0 : accountNum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tAmount == null) ? 0 : tAmount.hashCode());
		result = prime * result + ((tType == null) ? 0 : tType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (accountNum == null) {
			if (other.accountNum != null)
				return false;
		} else if (!accountNum.equals(other.accountNum))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tAmount == null) {
			if (other.tAmount != null)
				return false;
		} else if (!tAmount.equals(other.tAmount))
			return false;
		if (tType == null) {
			if (other.tType != null)
				return false;
		} else if (!tType.equals(other.tType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
