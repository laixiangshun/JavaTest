package com.foxera.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;


public class HttpUtil {
	
	/**
	 * ��ȡ�����ļ���Ϣ
	 */
	public static String getString(String name)
	{
		Configuration config=null;
		try {
			config=new PropertiesConfiguration("/resources/config.properties");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return config.getString(name);
	}
}
