package com.webapp.dao;

import com.webapp.model.User;

public interface UserDao {

	void save(User user);

	void update(User user);
	
	User findById(int id);
	
	User findByEmail(String email);
	
}

