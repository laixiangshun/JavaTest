package com.foxera.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.aspectj.weaver.ast.And;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foxera.dao.DemoDao;
import com.foxera.models.RtvHead;
import com.foxera.models.User;

/**
 * 注解@Repository表示这是dao层，里面的值demodao可以随便取
 * 注解@Autowired 采用注入方式，倒置原则，不需重复new对象
 * @author fox
 * @date 2016-11-24
 */
@Repository("demodao")
public class DemoDaoImpl implements DemoDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertUser(User user) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			session.clear();
			session.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			session.clear();
			session.delete(user);
			/*Query query=session.createQuery("delete from User e where e.id in()");
			int i=query.executeUpdate();*/
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	

	@Override
	public boolean updateUser(User user) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			session.clear();
			session.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectUsers() {
		Session session=this.sessionFactory.getCurrentSession();
		List<User> users=session.createQuery("FROM User u WHERE u.df=0").list();
		return users;
	}

	@Override
	public User selectUser(Integer userid) {
		Session session=this.sessionFactory.getCurrentSession();
		User user=(User) session.createQuery("FROM User u WHERE u.df=0 and u.id=:id")
				.setInteger("id", userid).uniqueResult();
		return user;
	}

	@Override
	public User selectUser(String userName) {
		Session session=this.sessionFactory.getCurrentSession();
		User user=(User) session.createQuery("FROM User u WHERE u.df=0 and u.userName=:name")
				.setString("userName", userName).uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<RtvHead>  selectRtv() {
		Session session=this.sessionFactory.getCurrentSession();
		List<RtvHead> message= session.createQuery("FROM RtvHead u WHERE rownum<5")
				.list();
		return message;
	}
	@Override
	public List<RtvHead> selectRtvs(String rtvNo,int rtvStatus) {
		Session session=this.sessionFactory.getCurrentSession();
		/*RtvHead message=(RtvHead) session.createQuery("FROM RtvHead u WHERE  u.rtvNo=:rtvNo")
				.setString("rtvNo", rtvNo).uniqueResult();*/
		
		String tableName="";
		tableName="RtvHead rh";
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("rh.rtvNo is not null");
		if(rtvNo!="")
		{
			sBuilder.append("and rh.rtvno='"+rtvNo+"'");
		}
		if(rtvStatus!=0)
		{
			sBuilder.append("and rh.rtvStatus='"+rtvStatus+"'");
		}
		
		String orderBy="rh.rtvno desc";
		String sql="select *"+"from"+tableName+"where"+sBuilder.toString()+"order by"+orderBy;
		Query query=session.createQuery(sql);
		if(query.getMaxResults()>0)
		{
			List<RtvHead> rtvHead= session.createQuery(sql).list();
		return rtvHead;
		}
		else return null;
	}
	
	public RtvHead selectRtvForOne(String rtvNo,int rtvStatus) {
		Session session=this.sessionFactory.getCurrentSession();
		/*RtvHead message=(RtvHead) session.createQuery("FROM RtvHead u WHERE  u.rtvNo=:rtvNo")
				.setString("rtvNo", rtvNo).uniqueResult();*/
		
		String tableName="";
		tableName="RtvHead rh";
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("rh.rtvNo is not null");
		if(rtvNo!="")
		{
			sBuilder.append("and rh.rtvno='"+rtvNo+"'");
		}
		if(rtvStatus!=0)
		{
			sBuilder.append("and rh.rtvStatus='"+rtvStatus+"'");
		}
		
		String orderBy="rh.rtvno desc";
		String sql="select *"+"from"+tableName+"where"+sBuilder.toString()+"order by"+orderBy;
		Query query=session.createQuery(sql);
		if(query.getMaxResults()>0)
		{
			RtvHead rtvHead= (RtvHead)session.createQuery(sql).uniqueResult();
		return rtvHead;
		}
		else return null;
	}
    @Override
	public boolean deleteRtv(RtvHead rtvHead) {
		Session session=this.sessionFactory.getCurrentSession();
		try{
			session.clear();
			session.delete(rtvHead);
			return true;
		}catch(Exception exception)
		{return false;}		
	}
	
	
}
