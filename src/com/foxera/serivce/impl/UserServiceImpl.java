package com.foxera.serivce.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxera.dao.UserDao;
import com.foxera.models.User;
import com.foxera.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userdao;

	@Override
	public boolean AddUser(String name, String age) {
		try {
			User user=new User();
			user.setAge(Integer.parseInt(age));
			user.setUserName(name);
			boolean flag=this.userdao.insertUser(user);
			if(flag)
			{
				return true;
			}else
			{
				throw new RuntimeException("����û�ʧ��");
			}
		} catch (Exception e) {
			throw new RuntimeException("����û�ʧ��");
		}
	}

	@Override
	public List<User> SelectUser(User criteriaUser, int index, int pageSize) {
		try {
			List<User> users=this.userdao.FindUserByCriteria(criteriaUser, index, pageSize);
			if(users==null || users.size()==0)
			{
				return null;
			}
			return users;	
		} catch (Exception e) {
			throw new RuntimeException("�����û�ʧ��");
		}
	}

	@Override
	public User GetUserById(String userid) {
		try {
			User user=this.userdao.SelectById(userid);
			if(user==null)
			{
				return null;
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException("�����û�ʧ��");
		}
	}

	@Override
	public boolean DeleteUserById(String userid) {
		try {
			boolean flag=this.userdao.deleteUserById(userid);
			if(flag==false)
			{
				throw new RuntimeException("ɾ���û�ʧ��");
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException("ɾ���û�ʧ��");
		}
	}

	@Override
	public boolean ModifyUser(String id, String name, String age) {
		try {
			int userage=Integer.valueOf(age).intValue();
			User user=new User();
			user.setId(id);
			user.setAge(userage);
			user.setUserName(name);
			boolean flag=this.userdao.ModifyById(user);
			if(flag==false)
			{
				throw new RuntimeException("�û��޸��쳣");
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException("�û��޸��쳣");
		}
	}

}
