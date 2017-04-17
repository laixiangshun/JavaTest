package com.foxera.dao;

import java.util.List;

import com.foxera.models.User;

public interface UserDao {
	
	//�����û�
	public boolean insertUser(User user);
	//��������û�
	public boolean insertAllUser(List<User> users);
	
	//ɾ���û�
	public boolean deleteUserById(String id);
	//��������ɾ���û�
	public boolean deleteUser(User criteriaUser);
	//ɾ��ȫ��
	public boolean deleteAll();
	
	//�޸�
	public boolean ModifyById(User user);
	//�޸Ķ���
	public boolean Modify(User criteriaUser,User user);
	
	//����id��ѯ
	public User SelectById(String id);
	//��ѯȫ��
	public List<User> FindAll();
	//��������ѯ
	public List<User> FindUserByCriteria(User criteriaUser,int skip,int limit);
	
	//��������ѯ���������޸�
	public User FindAndModify(User criteriaUser,User updateUser);
	//��������ѯ��������ɾ��
	public User FindAndDelete(User criteriaUser);
	
	//��ѯ����
	public long Count(User criteriaUser);
}
