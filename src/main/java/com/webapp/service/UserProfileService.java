package com.webapp.service;

import com.webapp.model.UserProfile;

import java.util.List;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);

	void save(UserProfile userProfile);
}
