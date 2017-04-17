package com.foxera.service;

import java.util.List;

import com.foxera.models.User;

public interface UserService {

	public boolean AddUser(String name,String age);
	
	public List<User> SelectUser(User criteriaUser,int index,int pageSize);
	
	public User GetUserById(String userid);
	
	public boolean DeleteUserById(String userid);
	
	public boolean ModifyUser(String id,String name,String age);
}
