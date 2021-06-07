package dev.carbajal.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.carbajal.models.User;
import dev.carbajal.utils.JDBCConnection;

public class UserRepo implements GenericRepo<User> {

	private Connection conn = JDBCConnection.getConnection();

	public User add(User u) {

		String sql = "insert into user values (default, ?, ?, ?, ?, ?) returning *;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setBoolean(5, u.isEmployeeAcct());

			// ResultSet rs = ps.executeQuery();

			boolean success = ps.execute();

			if (success) {

				ResultSet rs = ps.getResultSet();

				if (rs.next()) {

					u.setId(rs.getInt("id"));
					return u;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public User getById(Integer id) {

		// retrieves the user's info by id

		String sql = "select * from customers where id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User u = new User();
				
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("f_name"));
				u.setLastName(rs.getString("l_name"));
				u.setEmployeeAcct(rs.getBoolean("employee"));

				return u;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public List<User> getAll() {

		List<User> users = new ArrayList<User>();
		String sql = "select * from users;";
		
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("f_name"));
				u.setLastName(rs.getString("l_name"));
				u.setEmployeeAcct(rs.getBoolean("employee"));
				users.add(u);
			}
			return users;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public boolean update(User u) {

		String sql = "call update_user(?,?,?,?,?,?);";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getFirstName());
			ps.setString(5, u.getLastName());
			ps.setBoolean(6, u.isEmployeeAcct());

			boolean success = ps.execute();

			if (success) {

				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean delete(User u) {

		String sql = "delete from users where id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			
			boolean success = ps.execute();
			
			if (success) {
				
				return true;
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}

}
