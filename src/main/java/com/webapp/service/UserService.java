package com.webapp.service;

import com.webapp.model.User;

public interface UserService {

	//@Override
	void update(User user) ;//throws SQLException;

	void save(User user);
	
	User findById(int id);
	
	User findByEmail(String email);
	
}