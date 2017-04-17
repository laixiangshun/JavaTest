package com.foxera.models;

import java.util.Vector;

//单例模式的练习
public class SingletonTest {
	
	
	/*private SingletonTest(){}
	
	private static class SingletonFactory{
		private static SingletonTest instance=new SingletonTest();
	}
	public static SingletonTest getInstance(){
		return SingletonFactory.instance;
	}
	//如果该对象被用于序列化，可以保证对象在序列化前后保持一致 
	public Object readResolve(){
		return getInstance();
	}*/
	
	//采用影子实例的方法为单例对象的属性同步更新
	
	//私有静态对象，防止被引用，赋值为null，实现延迟加载
	private static SingletonTest instance=null;
	private Vector properties=null;
	public Vector getProperties(){
		return properties;
	}
	//私有构造方法，防止被实例化
	private SingletonTest(){}
	//用一个内部类来维护单例
	private static synchronized void syncInit(){
		if(instance==null)
		{
			instance=new SingletonTest();
		}
	}
	//获取实例
	public static SingletonTest getIstance(){
		if(instance==null)
		{
			syncInit();
		}
		return instance;
	}
	public void updateProperties(){
		SingletonTest singleton=new SingletonTest();
		properties=singleton.getProperties();
	}
}
