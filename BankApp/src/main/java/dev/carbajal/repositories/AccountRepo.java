package dev.carbajal.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import dev.carbajal.utils.JDBCConnection;
import dev.carbajal.models.Account;
import dev.carbajal.models.User;

public class AccountRepo implements GenericRepo<Account> {
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public Account add(Account a) {
		
		String sql = "insert into account values (default, ?, ?, ?, ?) returning *;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getAccountNum());
			ps.setString(2, a.getAccountType());
			ps.setBoolean(3, a.isPendingAccount());
			ps.setDouble(4, a.getAccountBal());

			boolean success = ps.execute();

			if (success) {

				ResultSet rs = ps.getResultSet();

				if (rs.next()) {

					a.setId(rs.getInt("id"));
					return a;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account getById(Integer id) {
		
		String sql = "select * from accounts where id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				Account a = new Account();
				
				a.setId(rs.getInt("id"));
				a.setAccountNum(rs.getInt("account_number"));
				a.setAccountType(rs.getString("account_type"));
				a.setPendingAccount(rs.getBoolean("pending_account"));
				a.setAccountBal(rs.getDouble("account_bal"));
				
				User u = new User();
				
				u.setId(rs.getInt("user_id"));
				
				return a;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Account> getAll() {
		
		return null;
	}

	@Override
	public boolean update(Account change) {
		
		return false;
	}

	@Override
	public boolean delete(Account t) {
		
		return false;
	}

}
