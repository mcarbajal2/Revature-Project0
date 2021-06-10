package dev.carbajal.services;

import java.util.List;

import dev.carbajal.models.User;

public interface UserService {

	public boolean logIn(User u, String user, String pass);

	public void logout();

	public boolean addUser(User u);

	public User getUser(User u);
	
	public User getUserByUsername(String username);

	public List<User> getAllUsers();

	public boolean updateUser(User u);

	public boolean deleteUser(User u);

}
