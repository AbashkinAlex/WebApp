package com.webapp.dao;

import com.webapp.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);

	void save(UserProfile userProfile);
}
