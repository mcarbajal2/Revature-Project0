package dev.carbajal.repositories;

import java.util.List;

import dev.carbajal.models.User;

public interface UserDAO {
	
	// Create
	
	public boolean addUser(User u);
	
	// Read
	
	public User getUser(int id);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public boolean checkIfUserExists(int id);
	
	// Update
	
	public boolean updateUser(User u);
	
	// Delete
	
	public boolean deleteUser(User u);

}
