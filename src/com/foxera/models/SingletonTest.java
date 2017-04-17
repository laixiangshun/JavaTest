package com.foxera.models;

import java.util.Vector;

//����ģʽ����ϰ
public class SingletonTest {
	
	
	/*private SingletonTest(){}
	
	private static class SingletonFactory{
		private static SingletonTest instance=new SingletonTest();
	}
	public static SingletonTest getInstance(){
		return SingletonFactory.instance;
	}
	//����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� 
	public Object readResolve(){
		return getInstance();
	}*/
	
	//����Ӱ��ʵ���ķ���Ϊ�������������ͬ������
	
	//˽�о�̬���󣬷�ֹ�����ã���ֵΪnull��ʵ���ӳټ���
	private static SingletonTest instance=null;
	private Vector properties=null;
	public Vector getProperties(){
		return properties;
	}
	//˽�й��췽������ֹ��ʵ����
	private SingletonTest(){}
	//��һ���ڲ�����ά������
	private static synchronized void syncInit(){
		if(instance==null)
		{
			instance=new SingletonTest();
		}
	}
	//��ȡʵ��
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
