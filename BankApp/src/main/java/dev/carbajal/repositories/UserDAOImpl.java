package dev.carbajal.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.carbajal.models.User;
import dev.carbajal.utils.JDBCConnection;

public class UserDAOImpl implements UserDAO {

	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean addUser(User u) {

		String sql = "insert into users values (default, ?, ?, ?, ?, ?);";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setBoolean(5, u.isEmployeeAcct());

			boolean success = ps.execute();

			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User getUser(int id) {

		String sql = "select * from users where u_id = ?";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("u_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("f_name"));
				user.setLastName(rs.getString("l_name"));
				user.setEmployeeAcct(rs.getBoolean("is_employee"));
				return user;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public boolean checkIfUserExists(int id) {
		
		String sql = "select * from users where u_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
	public User getUserByUsername(String username) {

		String sql = "select * from users where username = ?";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("u_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("f_name"));
				user.setLastName(rs.getString("l_name"));
				user.setEmployeeAcct(rs.getBoolean("is_employee"));
				return user;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<User> getAllUsers() {

		List<User> allUsers = new ArrayList<User>();

		String sql = "select * from users;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("u_id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("f_name"));
				u.setLastName(rs.getString("l_name"));
				u.setEmployeeAcct(rs.getBoolean("is_employee"));
				allUsers.add(u);
			}

			System.out.println("All Users:");
			System.out.println("\n");

			for (User u : allUsers) {

				System.out.print("User ID: ");
				System.out.print(u.getId());
				System.out.println("\n");
				System.out.print("Username: ");
				System.out.print(u.getUsername());
				System.out.println("\n");
				System.out.print("Password: ");
				System.out.print(u.getPassword());
				System.out.println("\n");
				System.out.print("First Name: ");
				System.out.print(u.getFirstName());
				System.out.println("\n");
				System.out.print("Last Name: ");
				System.out.print(u.getLastName());
				System.out.println("\n");
				System.out.print("Is Employee: ");
				System.out.print(u.isEmployeeAcct());
				System.out.println("\n");
			}

			return allUsers;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateUser(User u) {

		String sql = "update users set username = ?, password = ?, f_name = ?, "
				+ "l_name = ?, is_employee = ?, where u_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setBoolean(5, u.isEmployeeAcct());
			ps.setInt(6, u.getId());

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteUser(User u) {

		String sql = "delete from users where u_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, u.getId());

			boolean success = ps.execute();
			return success;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
