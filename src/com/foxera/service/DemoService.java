package com.foxera.service;

import java.util.List;

import com.foxera.models.RtvHead;
import com.foxera.models.User;

public interface DemoService {
	public boolean addUser(String name,Integer age,Integer sex);
	
	public boolean removeUser(Integer userid);
	
	public boolean modifyUser(Integer userid,String name,Integer age,Integer sex);
	
	public List<User> getUsers();
	
	public User getUser(Integer userid);
	
	public User getUser(String userName);
	
    public List<RtvHead> selectRtv();
    
    public boolean  deleteRtv(String rtvno);
    public List<RtvHead> selectRtvForOne(String rtvno,int rtvStatus);
}
