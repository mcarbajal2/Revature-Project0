package daotests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dev.carbajal.models.User;
import dev.carbajal.repositories.UserDAO;
import dev.carbajal.repositories.UserDAOImpl;

public class UserDAOTests {

	private UserDAO udao = new UserDAOImpl();

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
}





/*All Users:


User ID: 1

Username: admin

Password: password

First Name: John

Last Name: Smith

Is Employee: true

User ID: 2

Username: mcarbajal

Password: pass

First Name: Mina

Last Name: Carbajal

Is Employee: false

User ID: 3

Username: nevergonna

Password: giveyouup

First Name: Rick

Last Name: Astley

Is Employee: false*/

//

/*java.lang.AssertionError: expected:
 * 
 * <[User [username = admin, password = password, firstName = John, lastName = Smith, employeeAcct = false], 
 * User [username = mcarbajal, password = pass, firstName = Mina, lastName = Carbajal, employeeAcct = false], 
 * User [username = nevergonna, password = giveyouup, firstName = Rick, lastName = Astley, employeeAcct = false]]> 
 * 
 * but was:<[User [username = admin, password = password, firstName = John, lastName = Smith, employeeAcct = true], 
 * User [username = mcarbajal, password = pass, firstName = Mina, lastName = Carbajal, employeeAcct = false], 
 * User [username = nevergonna, password = giveyouup, firstName = Rick, lastName = Astley, employeeAcct = false]]>
 * 
	at org.junit.Assert.fail(Assert.java:89)
	at org.junit.Assert.failNotEquals(Assert.java:835)
	at org.junit.Assert.assertEquals(Assert.java:120)
	at org.junit.Assert.assertEquals(Assert.java:146)
	at daotests.UserDAOTests.getAllUsersTest(UserDAOTests.java:30)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:89)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:541)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:768)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:464)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)

*/
