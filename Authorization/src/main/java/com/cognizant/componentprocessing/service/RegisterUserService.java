package com.cognizant.componentprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.componentprocessing.model.Users;
import com.cognizant.componentprocessing.repository.UserRepository;

@Service
public class RegisterUserService {
	
	@Autowired
	UserRepository repository;
	
	public void newUser(String username,String password) {
		
		int id = (int)repository.count()+1;
		repository.save(new Users(id, username, encoder().encode(password),"ROLE_USER"));
	}
	
	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
