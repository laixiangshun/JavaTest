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
 * 注解@Entity表示这是一个实体类
 * 注解@Table对应了数据库中的表名
 * 采用orm实体自动生成数据库
 * 
 * 成员属性上面的注解是对应了表中的字段，@Id表示这是主键，@GeneratedValue注解策略，strategy=GenerationType.IDENTITY表示主键自增
 * 注解@Column设置表中对应字段的属性，name=字段名，nullable能不能为空，unique是否唯一值
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
