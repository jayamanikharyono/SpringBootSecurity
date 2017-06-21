package com.itdel.service;

import com.itdel.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}