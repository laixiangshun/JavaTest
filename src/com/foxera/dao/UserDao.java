package com.foxera.dao;

import java.util.List;

import com.foxera.models.User;

public interface UserDao {
	
	//新增用户
	public boolean insertUser(User user);
	//新增多个用户
	public boolean insertAllUser(List<User> users);
	
	//删除用户
	public boolean deleteUserById(String id);
	//根据条件删除用户
	public boolean deleteUser(User criteriaUser);
	//删除全部
	public boolean deleteAll();
	
	//修改
	public boolean ModifyById(User user);
	//修改多条
	public boolean Modify(User criteriaUser,User user);
	
	//根据id查询
	public User SelectById(String id);
	//查询全部
	public List<User> FindAll();
	//按条件查询
	public List<User> FindUserByCriteria(User criteriaUser,int skip,int limit);
	
	//按条件查询出来后再修改
	public User FindAndModify(User criteriaUser,User updateUser);
	//按条件查询出来后再删除
	public User FindAndDelete(User criteriaUser);
	
	//查询数量
	public long Count(User criteriaUser);
}
