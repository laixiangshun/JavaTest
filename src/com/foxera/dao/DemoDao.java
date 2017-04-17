package com.foxera.dao;

import java.util.List;

import oracle.jdbc.driver.Message;

import com.foxera.models.RtvHead;
import com.foxera.models.User;

public interface DemoDao {
	public boolean insertUser(User user);
	
	public boolean deleteUser(User user);
	
	public boolean updateUser(User user);
	
	public List<User> selectUsers();
	
	public User selectUser(Integer userid);
	
	public User selectUser(String userName);
	
	public  List<RtvHead>  selectRtv();
	
	public RtvHead selectRtvForOne(String rtvNo,int rtvStatus);
    
	public boolean deleteRtv(RtvHead rtvHead);

	public List<RtvHead> selectRtvs(String rtvNo,int rtvStatus);	

}

