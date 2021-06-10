package dev.carbajal.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.carbajal.models.Transaction;
import dev.carbajal.models.User;
import dev.carbajal.utils.JDBCConnection;

public class TransactionDAOImpl implements TransactionDAO {

	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean addTransaction(Transaction t) {

		String sql = "insert into transactions values (default, ?, ?, ?, ?);";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(t.getId()));
			ps.setString(2, Integer.toString(t.getUserId()));
			ps.setString(3, Double.toString(t.gettAmount()));
			ps.setString(4, t.gettType());

			boolean success = ps.execute();

			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Transaction getTransaction(Transaction t) {
		
		String sql = "select * from transactions where t_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(t.getId()));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				Transaction tr = new Transaction();

				t.setId(rs.getInt("t_id"));
				t.setUserId(rs.getInt("u_id"));
				t.setAccountNum(rs.getInt("acc_num"));
				t.settAmount(rs.getDouble("t_amount"));
				t.settType(rs.getString("t_name"));

				System.out.println(tr);
				return tr;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Transaction> getUserTransaction(User u) {

		List<Transaction> allUserTransactions = new ArrayList<Transaction>();

		String sql = "select * from transactions where u_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(u.getId()));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Transaction t = new Transaction();

				t.setId(rs.getInt("t_id"));
				t.setUserId(rs.getInt("u_id"));
				t.setAccountNum(rs.getInt("acc_num"));
				t.settAmount(rs.getDouble("t_amount"));
				t.settType(rs.getString("t_name"));

				allUserTransactions.add(t);
			}

			for (Transaction t : allUserTransactions) {

				System.out.println(t);
			}
			return allUserTransactions;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() {

		List<Transaction> allTransactions = new ArrayList<Transaction>();

		String sql = "select * from transactions;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Transaction t = new Transaction();

				t.setId(rs.getInt("t_id"));
				t.setUserId(rs.getInt("u_id"));
				t.setAccountNum(rs.getInt("acc_num"));
				t.settAmount(rs.getDouble("t_amount"));
				t.settType(rs.getString("t_name"));

				allTransactions.add(t);
			}

			for (Transaction t : allTransactions) {

				System.out.println(t);
			}
			return allTransactions;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean updateTransaction(Transaction t) {

		String sql = "update transactions set u_id = ?, acc_num = ?, "
					+ "t_amount = ?, t_name = ?, where t_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(t.getUserId()));
			ps.setString(2, Integer.toString(t.getAccountNum()));
			ps.setString(3, Double.toString(t.gettAmount()));
			ps.setString(4, t.gettType());
			ps.setString(5, Integer.toString(t.getId()));

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteTransaction(Transaction t) {

		String sql = "delete from transactions where t_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(t.getId()));

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}
}
