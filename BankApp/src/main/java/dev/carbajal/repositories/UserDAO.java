package dev.carbajal.repositories;

import java.util.List;

import dev.carbajal.models.User;

public interface UserDAO {
	
	// Create
	
	public boolean addUser(User u);
	
	// Read
	
	public User getUser(User u);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	// Update
	
	public boolean updateUser(User u);
	
	// Delete
	
	public boolean deleteUser(User u);

}
