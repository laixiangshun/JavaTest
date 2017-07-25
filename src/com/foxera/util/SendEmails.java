package com.foxera.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.foxera.models.MailModel;
import com.foxera.models.ResultModel;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 *发送邮件工具类
 * @author lailai
 *
 */
public class SendEmails {
	
	public static ResultModel sendEmail(MailModel email)
	{
		ResultModel result=new ResultModel();
		String eamilFrom=null;
		if(email.getEmailFrom()!=null)
		{
			eamilFrom=email.getEmailFrom();
		}
		//通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		final String smtpHostName="smtp."+eamilFrom.split("@")[1];
		email.setEmailHost(smtpHostName);
		
		try {
			if(email.getEmailHost().equals("") || email.getEmailFrom().equals("") || email.getEmailUserName().equals("")
					|| email.getEmailPassword().equals("") || email.getToEmails().equals(""))
			{
				result.setSccessful(false);
				result.setMessage("邮件信息不完整，不能发送");
				return result;
			}
			
			JavaMailSenderImpl  senderImpl=new JavaMailSenderImpl();
			//设定邮件服务
			senderImpl.setHost(email.getEmailHost());
			//建立邮件信息
			MimeMessage message=senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"UTF-8");
			//设置发件人
			if(email.getEmailFrom()!=null)
			{
				messageHelper.setFrom(email.getEmailFrom());
			}
			/*else{
				messageHelper.setFrom(new SimpleMailMessage().getFrom());
			}*/
			//设置收件人邮箱
			if(email.getToEmails()!=null)
			{
				String[] toemails=email.getToEmails().split(";");
				List<String> toemailList=new ArrayList<>();
				if(toemails==null || toemails.length<=0)
				{
					result.setSccessful(false);
					result.setMessage("收件人邮箱不能空");
					return result;
				}
				else
				{
					for(String s: toemails)
					{
						if(!s.equals(""))
						{
							toemailList.add(s);
						}
					}
					if(toemailList==null || toemailList.size()<=0)
					{
						return null;
					}else
					{
						toemails=new String[toemailList.size()];
						for(int i=0;i<toemailList.size();i++)
						{
							toemails[i]=toemailList.get(i);
						}
					}
				}
				messageHelper.setTo(toemails);
			}
			//设置邮件主题
			if(email.getSubject()!=null)
			{
				messageHelper.setSubject(email.getSubject());
			}
			//true表示发送html格式的邮件
			messageHelper.setText(email.getContent(), true);
			//发送图片
			if(email.getPictures()!=null)
			{
				for(Iterator<Map.Entry<String, String>> it=email.getPictures().entrySet().iterator();it.hasNext();)
				{
					Map.Entry<String, String> entry=it.next();
					String cid=entry.getKey();
					String filepath=entry.getValue();
					if(cid==null || filepath==null)
					{
						result.setSccessful(false);
						result.setMessage("请确定每张图片id和路径是否齐全");
						return result;
					}
					File file=new File(filepath);
					if(!file.exists())
					{
						result.setSccessful(false);
						result.setMessage("图片"+filepath+"不存在");
						return result;
					}
					FileSystemResource img=new FileSystemResource(file);
					messageHelper.addInline(cid, img);
				}
			}
			//添加邮件附件
			if(email.getAttachments()!=null)
			{
				for(Iterator<Map.Entry<String, String>> it=email.getAttachments().entrySet().iterator();it.hasNext();)
				{
					Map.Entry<String, String> entry=it.next();
					String cid=entry.getKey();
					String filepath=entry.getValue();
					if(cid==null || filepath==null)
					{
						result.setSccessful(false);
						result.setMessage("请确定每个附件id和路径是否齐全");
						return result;
					}
					File file=new File(filepath);
					if(!file.exists())
					{
						result.setSccessful(false);
						result.setMessage("附件"+filepath+"不存在");
						return result;
					}
					FileSystemResource fileResource=new FileSystemResource(file);
					messageHelper.addAttachment(cid, fileResource);
				}
			}
			Properties prop=new Properties();
			prop.put("mail.smtp.auth", "true");//让邮件服务验证用户名和密码是否正确
			prop.put("mail.smtp.timeout", "25000");
			//QQ邮箱ssl加密，其他邮箱不用
			if(email.getEmailHost().equals("qq.com"))
			{
				MailSSLSocketFactory sf=new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
				prop.put("mail.smtp.ssl.enable", "true");
				prop.put("mail.smtp.ssl.socketFactory", sf);
			}
			
			//添加验证֤
			MyAuthenticator authenticator=new MyAuthenticator(email.getEmailUserName(), email.getEmailPassword());
			//链接邮件服务地址ַ
			Session session=Session.getInstance(prop, authenticator);
			senderImpl.setSession(session);
			//发送邮件
			senderImpl.send(message);
			
			result.setSccessful(true);
			result.setMessage("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
}
