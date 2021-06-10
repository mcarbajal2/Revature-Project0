package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.User;
import dev.carbajal.repositories.UserDAO;
import dev.carbajal.repositories.UserDAOImpl;

public class UserServiceImpl implements UserService {

	private static UserDAO udao = new UserDAOImpl();
	
	@Override
	public boolean logIn(User u, String user, String pass) {

		if (user.equals(u.getUsername()) && pass.equals(u.getPassword()))		 {

			return true;

		} else {

			return false;
		}
	}

	@Override
	public void logout() {
		
		System.out.println("Thank you for banking with Totally A Real Bank Name Bank!\n"
				+ "Have a wonderful day.\n\n");
	}

	@Override
	public boolean addUser(User u) {

		return udao.addUser(u);
	}

	@Override
	public User getUser(User u) {

		return udao.getUser(u);
	}
	
	@Override
	public User getUserByUsername(String username) {
		
		return udao.getUserByUsername(username);
		
	}

	@Override
	public List<User> getAllUsers() {

		return udao.getAllUsers();
	}

	@Override
	public boolean updateUser(User u) {

		return udao.updateUser(u);
	}

	@Override
	public boolean deleteUser(User u) {

		return udao.deleteUser(u);
	}


}
