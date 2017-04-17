package com.foxera.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class BasicEntity {
	private Integer id;
	private Integer df;//删除标识,默认0
	private Integer md;//修改时间，默认0
	private Timestamp cd;//创建时间
	public BasicEntity(){}
	public BasicEntity(Integer df, Integer md, Timestamp cd) {
		this.df = df;
		this.md = md;
		this.cd = cd;
	}
	
	//oracle的自增序列注解方式
	@Id
	@SequenceGenerator(name = "sequenceid",allocationSize=1,initialValue=1, sequenceName = "sequence_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceid")
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	
	//mysql的自增序列注解方式
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="id",nullable=false,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="df",nullable=false)
	public Integer getDf() {
		return df;
	}
	public void setDf(Integer df) {
		this.df = df;
	}
	@Column(name="md",nullable=false)
	public Integer getMd() {
		return md;
	}
	public void setMd(Integer md) {
		this.md = md;
	}
	@Column(name="cd",nullable=false)
	public Timestamp getCd() {
		return cd;
	}
	public void setCd(Timestamp cd) {
		this.cd = cd;
	}
	

}
