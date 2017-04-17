package com.foxera.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.foxera.dao.UserDao;
import com.foxera.models.User;
import com.foxera.util.AbstractBaseMongoTemplete;

@Repository
public class UsersDaoImpl extends AbstractBaseMongoTemplete implements UserDao {

	@Override
	public boolean insertUser(User user) {
		try {
			mongoTemplate.insert(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertAllUser(List<User> users) {
		try {
			mongoTemplate.insertAll(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUserById(String id) {
		try {
			//User user=new User(id,null,0);
			//mongoTemplate.remove(user);
			Criteria criteria=Criteria.where("id").is(id);
			Query query=new Query(criteria);
			mongoTemplate.remove(query, User.class);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User criteriaUser) {
		try {
			Criteria criteria=Criteria.where("age").gt(criteriaUser.getAge());
			Query query=new Query(criteria);
			mongoTemplate.remove(query, User.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAll() {
		try {
			mongoTemplate.dropCollection(User.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean ModifyById(User user) {
		try {
			Criteria criteria=Criteria.where("id").is(user.getId());
			Query query=new Query(criteria);
			Update update=Update.update("age", user.getAge()).set("userName", user.getUserName());
			mongoTemplate.updateFirst(query, update, User.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Modify(User criteriaUser, User user) {
		try {
			Criteria criteria=Criteria.where("age").gt(criteriaUser.getAge());
			Query query=new Query(criteria);
			Update update=Update.update("age", user.getAge()).set("userName", user.getUserName());
			mongoTemplate.updateMulti(query, update, User.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User SelectById(String id) {
		try {
			User user=mongoTemplate.findById(id, User.class);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<User> FindAll() {
		try {
			List<User> userList=mongoTemplate.findAll(User.class);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> FindUserByCriteria(User criteriaUser, int skip, int limit) {
		try {
			Query query=getQuery(criteriaUser);
			query.skip(skip*limit);
			query.limit(limit);
			List<User> userList=mongoTemplate.find(query, User.class);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User FindAndModify(User criteriaUser, User updateUser) {
		try {
			Query query=getQuery(criteriaUser);
			Update update=Update.update("age", updateUser.getAge()).set("userName", updateUser.getUserName());
			User user=mongoTemplate.findAndModify(query, update, User.class);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User FindAndDelete(User criteriaUser) {
		try {
			Query query=getQuery(criteriaUser);
			User user=mongoTemplate.findAndRemove(query, User.class);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long Count(User criteriaUser) {
		try {
			Query query=getQuery(criteriaUser);
			return mongoTemplate.count(query, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Query getQuery(User criteriaUser)
	{
		if(criteriaUser == null)
		{
			criteriaUser=new User();
		}
		Query query=new Query();
		if(criteriaUser.getId()!=null)
		{
			Criteria critaria=Criteria.where("id").is(criteriaUser.getId());
			query.addCriteria(critaria);
		}
		if(criteriaUser.getAge()!=null && criteriaUser.getAge()>0)
		{
			Criteria critaria=Criteria.where("age").gt(criteriaUser.getAge());
			query.addCriteria(critaria);
		}
		if(criteriaUser.getUserName()!=null)
		{
			Criteria critaria=Criteria.where("userName").regex("^"+criteriaUser.getUserName());
			query.addCriteria(critaria);
		}
		return query;
	}
}
