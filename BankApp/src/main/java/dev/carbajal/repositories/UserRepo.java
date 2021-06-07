package dev.carbajal.repositories;

import java.util.List;

import dev.carbajal.models.User;

public class UserRepo implements GenericRepo<User> {

	public User add(User u) {

		// check if user exists and if not add them

		return null;
	}

	public User getById(Integer id) {

		// retrieves the user's info by id

		return null;
	}

	public List<User> getAll() {

		return null;
	}

	public boolean update(User change) {

		return false;
	}

	public boolean delete(User u) {

		return false;
	}

}
