package com.webapp.service;

import com.webapp.model.User;

public interface UserService {

	void update(User user) ;

	void save(User user);
	
	User findById(int id);
	
	User findByEmail(String email);
	
}