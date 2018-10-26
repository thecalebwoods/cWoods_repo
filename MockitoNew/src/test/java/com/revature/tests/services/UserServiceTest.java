package com.revature.tests.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.revature.doas.interfaces.UserDao;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.exceptions.BadRequestException;
import com.revature.util.exceptions.NotFoundException;

public class UserServiceTest {

	

	// Mock implementation

	UserDao mockUserDao = mock(UserDao.class);

	UserService userService = new UserService(mockUserDao);

	

	@Test(expected=BadRequestException.class)

	public void authenticateNoUsernameTest() throws Exception {

		Credentials credentials = new Credentials(null, "password");

		userService.authenticate(credentials);

	}

	

	@Test(expected=BadRequestException.class)

	public void authenticateNoPasswordTest() throws Exception {

		Credentials credentials = new Credentials("username", null);

		userService.authenticate(credentials);

	}

	

	@Test(expected=NotFoundException.class)

	public void authenticateNoUserWithUsername() throws Exception {

		/*

		 * When the userService calls the getUserByUsername method on the dao

		 * with the String "NotFound" the dao will return null.

		 * This is an example of a stub method.

		 */

		when(mockUserDao.getUserByUsername("NotFound")).thenReturn(null);

		when(mockUserDao.getUserByUsername("password")).thenThrow(new RuntimeException());

		Credentials credentials = new Credentials("NotFound", "password");

		userService.authenticate(credentials);

	}

	

	@Test(expected=NotFoundException.class)

	public void authenticateIncorrectPassword() throws Exception {

		User user = new User(1, "BadPassword", "abc");

		when(mockUserDao.getUserByUsername("BadPassword")).thenReturn(user);

		Credentials credentials = new Credentials("BadPassword", "def");

		userService.authenticate(credentials);

	}

	

	@Test

	public void authenticateGoodData() throws Exception {

		Credentials credentials = new Credentials("good", "goodpass");

		User user = new User(1, "good", "goodpass");

		when(mockUserDao.getUserByUsername("good")).thenReturn(user);

		String str = userService.authenticate(credentials);

		assertNotNull(str);

	}

	

}





