package com.foxera.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ע��@Entity��ʾ����һ��ʵ����
 * ע��@Table��Ӧ�����ݿ��еı���
 * ����ormʵ���Զ��������ݿ�
 * 
 * ��Ա���������ע���Ƕ�Ӧ�˱��е��ֶΣ�@Id��ʾ����������@GeneratedValueע����ԣ�strategy=GenerationType.IDENTITY��ʾ��������
 * ע��@Column���ñ��ж�Ӧ�ֶε����ԣ�name=�ֶ�����nullable�ܲ���Ϊ�գ�unique�Ƿ�Ψһֵ
 * 
 * 
 * @date 2016-11-24
 */
@Document
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8429021858299930484L;
	
	@Id
	@Indexed
	private String id;
	private String userName;
	private Integer age;
	
	public User(){}
	public User(String id,String name,Integer age)
	{
		this.id=id;
		this.userName=name;
		this.age=age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
