package com.foxera.models;

import java.util.Map;

/**
 * �ʼ���
 * @author lailai
 *
 */
public class MailModel {
	/**
	 *  发件人邮箱服务器
	 */
	private String emailHost;
	/**
	 * 发件人邮箱
	 */
	private String emailFrom;
	/**
	 * 发件人姓名
	 */
	private String emailUserName;
	/**
	 *发件人密码
	 */
	private String emailPassword;
	/**
	 * 收件人邮箱，多个用“；”隔开
	 */
	private String toEmails;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容
	 */
	private String content;
	/**
	 *  邮件中的图片，key为图片id，value为图片的地址ַ
	 */
	private Map<String,String> pictures;
	/**
	 * 邮件的附件，key为附件的id，value为附件地址ַ
	 */
	private Map<String,String> attachments;
	
	
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailUserName() {
		return emailUserName;
	}
	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getToEmails() {
		return toEmails;
	}
	public void setToEmails(String toEmails) {
		this.toEmails = toEmails;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, String> getPictures() {
		return pictures;
	}
	public void setPictures(Map<String, String> pictures) {
		this.pictures = pictures;
	}
	public Map<String, String> getAttachments() {
		return attachments;
	}
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

	
}
