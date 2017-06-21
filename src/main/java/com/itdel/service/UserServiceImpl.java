package com.itdel.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itdel.model.Role;
import com.itdel.model.User;
import com.itdel.repository.RoleRepository;
import com.itdel.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		Role userRole1 = roleRepository.findByRole("MANAGER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole,userRole1)));
		System.out.println(user.getRoles().toString());
		userRepository.save(user);
	}

}
