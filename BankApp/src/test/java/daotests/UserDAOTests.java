package daotests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import dev.carbajal.models.User;
import dev.carbajal.repositories.UserDAO;
import dev.carbajal.repositories.UserDAOImpl;

public class UserDAOTests {

	private UserDAO udao = new UserDAOImpl();

	@Ignore
	@Test
	public void getAllUsersTest() {

		List<User> users = new ArrayList<User>();

		User u1 = new User(1, "admin", "password", "John", "Smith", true);
		User u2 = new User(2, "mcarbajal", "pass", "Mina", "Carbajal", false);
		User u3 = new User(3, "nevergonna", "giveyouup", "Rick", "Astley", false);

		users.add(u1);
		users.add(u2);
		users.add(u3);

		Assert.assertEquals(users, udao.getAllUsers());	
	}

	@Ignore
	@Test
	public void addUserTest() {
		
		User u = new User();

		u.setUsername("testUser");
		u.setPassword("testPass");
		u.setFirstName("Bob");
		u.setLastName("Ross");
		u.setEmployeeAcct(false);
		
		Assert.assertEquals(false, udao.addUser(u));
	}
	
	@Test
	public void getUserTest() {
		
		User u = new User();
		u.setId(1);
		
		Assert.assertEquals(u.getId(), udao.getUser(1).getId());
		
	}
}


