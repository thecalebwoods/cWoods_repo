package com.revature.doas.interfaces;

import com.revature.models.User;

public interface UserDao {
	/*
	 * Retreive a user instance from a 
	 * provided username, or null if no user with provided username 
	 */
	User getUserByUsername(String username);
}
