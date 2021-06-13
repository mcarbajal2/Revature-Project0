package dev.carbajal.repositories;

//import java.sql.CallableStatement;
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

//		String sql = "call add_acct(?, ?, ?, ?);";
		String sql = "insert into accounts values (default, ?, ?, ?, ?);";

		try {

//			CallableStatement cs = conn.prepareCall(sql);
//			cs.setInt(1, acc.getUserId());
//			cs.setFloat(2, acc.getAccountBal());
//			cs.setString(3, acc.getAccountType());
//			cs.setBoolean(4, true);
//
//			boolean success = cs.execute();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acc.getUserId());
			ps.setFloat(2, acc.getAccountBal());
			ps.setString(3, acc.getAccountType());
			ps.setBoolean(4, true);

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
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Account a = new Account();
				a.setAccountNum(rs.getInt("acc_num"));
				a.setAccountBal(rs.getFloat("balance"));
				a.setAccountType(rs.getString("acc_type"));
				a.setPendingAccount(rs.getBoolean("is_pending"));
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
				a.setAccountBal(rs.getFloat("balance"));
				a.setAccountType(rs.getString("acc_type"));
				a.setPendingAccount(rs.getBoolean("is_pending"));
				a.setUserId(rs.getInt("u_id"));
				accts.add(a);
			}

			//			System.out.println("\n");
			//
			//			for (Account a : accts) {
			//
			//				System.out.print("Account Num: ");
			//				System.out.print(a.getAccountNum());
			//				System.out.println("\n");
			//				System.out.print("Balance: ");
			//				System.out.print(a.getAccountBal());
			//				System.out.println("\n");
			//				System.out.print("Type: ");
			//				System.out.print(a.getAccountType());
			//				System.out.println("\n");
			//				System.out.print("Status: ");
			//				System.out.print(a.isPendingAccount());
			//				System.out.println("\n");
			//			}

			return accts;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Float getBalByNum(Account acc) {

		String sql = "select balance from accounts where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acc.getAccountNum());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Float bal = rs.getFloat("balance");
				return bal;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Account getAcctByNum(int accnum) {

		String sql = "select * from accounts where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accnum);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Account a = new Account();
				a.setAccountNum(rs.getInt("acc_num"));
				a.setAccountBal(rs.getFloat("balance"));
				a.setAccountType(rs.getString("acc_type"));
				a.setPendingAccount(rs.getBoolean("is_pending"));
				a.setUserId(rs.getInt("u_id"));
				return a;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean checkIfAcctExists(int accnum) {

		String sql = "select * from accounts where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accnum);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateAcc(int accnum) { 

		String sql = "update accounts set is_pending = false where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, accnum);

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deposit(Account acc, Float total) {

		acc.setAccountBal((acc.getAccountBal() + total));

		String sql = "update accounts set balance = ? where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, acc.getAccountBal());
			ps.setInt(2, acc.getAccountNum());

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		//		Transaction tD = new Transaction(acc.getUserId(), acc.getAccountNum(), total, "deposit");
		//		tdao.addTransaction(tD);
		//		tranS.addTransaction(tD);

		return false;
	}

	@Override
	public boolean withdraw(Account acc, Float total) {

		acc.setAccountBal((acc.getAccountBal() - total));

		String sql = "update accounts set balance = ? where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, acc.getAccountBal());
			ps.setInt(2, acc.getAccountNum());

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		//		Transaction tW = new Transaction(acc.getUserId(), acc.getAccountNum(), total, "withdrawal");
		//		tdao.addTransaction(tW);
		//		tranS.addTransaction(tW);
		return false;
	}

	@Override
	public boolean transfer(Account acc1, Account acc2, Float total) {

		acc1.setAccountBal((acc1.getAccountBal() - total));
		acc2.setAccountBal((acc2.getAccountBal() + total));

		String sql = "update accounts set balance = ? where acc_num = ?;";
		String sql2 = "update accounts set balance = ? where acc_num = ?;";
		
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, acc1.getAccountBal());
			ps.setInt(2, acc1.getAccountNum());

			boolean success = ps.execute();
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setFloat(1, acc2.getAccountBal());
			ps2.setInt(2, acc2.getAccountNum());

			boolean success2 = ps2.execute();

			if (success == true && success2 == true) {
				
				return success;
			}
	

		} catch (SQLException e) {

			e.printStackTrace();
		}

		//		Transaction tT = new Transaction(acc1.getUserId(), acc1.getAccountNum(), total, "transfer funds");
		//		tdao.addTransaction(tT);
		//		tranS.addTransaction(tT);

		return false;
	}


	@Override
	public boolean deleteAcc(Account acc) { 

		String sql = "delete from accounts where acc_num = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, acc.getAccountNum());

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
