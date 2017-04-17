package com.foxera.serivce.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxera.dao.DemoDao;
import com.foxera.models.RtvHead;
import com.foxera.models.User;
import com.foxera.service.DemoService;

/**
 * 注解@Transactional表示这个service是一个事务，遵循事务的四大特性：原子性、一致性、分离性、持久性    
 * 注解@Service表示这是一个服务层
 * @author fox
 * @date 2016-11-24
 */
@Transactional
@Service("demoservice")
public class DemoServiceImpl implements DemoService{
	@Autowired
	private DemoDao demoDao;//通过接口调用，而不是实现类

	@Override
	public boolean addUser(String name,Integer age,Integer sex) {
		//User user=new User(0, 0, new Timestamp(System.currentTimeMillis()), name, age, sex);
		User user=new User();
		user.setAge(age);
		user.setUserName(name);
		return this.demoDao.insertUser(user);
	}

	@Override
	public boolean removeUser(Integer userid) {
		User user=this.demoDao.selectUser(userid);
		return this.demoDao.deleteUser(user);
	}

	@Override
	public boolean modifyUser(Integer userid,String name,Integer age,Integer sex) {
		User user=this.demoDao.selectUser(userid);
		if(user!=null){
			user.setAge(age);
			//user.setMd(Integer.parseInt(System.currentTimeMillis()/1000+""));
			//user.setSex(sex);
			user.setUserName(name);
			boolean flag=this.demoDao.updateUser(user);
			return flag;
		}
		return false;
	}

	@Override
	public List<User> getUsers() {
		return this.demoDao.selectUsers();
	}

	@Override
	public User getUser(Integer userid) {
		return this.demoDao.selectUser(userid);
	}

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<RtvHead> selectRtv(){
		return this.demoDao.selectRtv();
	}

	@Override
	public boolean deleteRtv(String rtvno) {
		RtvHead rtvHead=this.demoDao.selectRtvForOne(rtvno,0);
		
		return this.demoDao.deleteRtv(rtvHead);
	}
	@Override
    public List<RtvHead> selectRtvForOne(String rtvno,int rtvStatus) {
    	List<RtvHead> rtvHead=this.demoDao.selectRtvs(rtvno, rtvStatus);
		return rtvHead;
	}
}
