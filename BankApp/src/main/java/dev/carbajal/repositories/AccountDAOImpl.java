package dev.carbajal.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.carbajal.models.Account;
import dev.carbajal.models.User;
import dev.carbajal.utils.JDBCConnection;

public class AccountDAOImpl implements AccountDAO {

	private Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean addAcct(Account acc) { 

		String sql = "insert into accounts values(?, default, ?, ?, ?);";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acc.getUserId());
			ps.setDouble(2, acc.getAccountBal());
			ps.setString(3, acc.getAccountType());
			ps.setBoolean(4, acc.isPendingAccount());

			boolean success = ps.execute();

			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Account> getAllUserAcct(User u) { 

		List<Account> accts = new ArrayList<Account>();

		String sql = "select * from accounts where u_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(u.getId()));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Account a = new Account();
				a.setAccountNum(rs.getInt("acc_num"));
				a.setAccountBal(rs.getDouble("balance"));
				a.setAccountType(rs.getString("acc_type"));
				a.setPendingAccount(rs.getBoolean("balance"));
				a.setUserId(rs.getInt("u_id"));
				accts.add(a);
			}

			return accts;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Account> getAllAcct() { 

		List<Account> accts = new ArrayList<Account>();

		String sql = "select * from accounts;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account a = new Account();
				a.setAccountNum(rs.getInt("acc_num"));
				a.setAccountBal(rs.getDouble("balance"));
				a.setAccountType(rs.getString("acc_type"));
				a.setPendingAccount(rs.getBoolean("balance"));
				a.setUserId(rs.getInt("u_id"));
				accts.add(a);
			}

			System.out.println("Your Accounts:");
			System.out.println("\n");

			for (Account a : accts) {

				System.out.print("Account Num: ");
				System.out.print(a.getAccountNum());
				System.out.println("\n");
				System.out.print("Balance: ");
				System.out.print(a.getAccountBal());
				System.out.println("\n");
				System.out.print("Type: ");
				System.out.print(a.getAccountType());
				System.out.println("\n");
				System.out.print("Status: ");
				System.out.print(a.isPendingAccount());
				System.out.println("\n");
			}

			return accts;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Account getBal(Account acc) { 

		String sql = "select * from bank_accounts where account_number = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(acc.getAccountNum()));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Account a = new Account();
				a.setAccountNum(rs.getInt("acc_num"));
				a.setAccountBal(rs.getDouble("balance"));
				a.setAccountType(rs.getString("acc_type"));
				a.setPendingAccount(rs.getBoolean("balance"));
				a.setUserId(rs.getInt("u_id"));
				return a;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateAcc(Account acc) { 
		
		String sql = "update accounts set is_pending = false where u_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(acc.getUserId()));

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteAcc(Account acc) { 
		
		String sql = "delete from accounts where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(acc.getAccountNum()));

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}
}
