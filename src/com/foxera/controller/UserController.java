package com.foxera.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.foxera.models.MailModel;
import com.foxera.models.ResultModel;
import com.foxera.models.User;
import com.foxera.service.UserService;
import com.foxera.util.BeanToJson;
import com.foxera.util.HttpUtil;
import com.foxera.util.SendEmails;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUsers.action",method=RequestMethod.POST)
	public void AddUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		boolean flag=this.userService.AddUser(name,age);
		if(flag)
		{
			response.getWriter().write("添加用户成功");
		}else
		{
			response.getWriter().write("添加用户失败");
		}
	}
	@RequestMapping(value="/selectUser.html",method=RequestMethod.GET)
	public String ShowUsers(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<User> users=this.userService.SelectUser(null,0,10);
		request.setAttribute("users", users);
		return "foreground/index";
	}
	@RequestMapping(value="/modifyusers.html",method=RequestMethod.GET)
	public String Modify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userid=request.getParameter("userid");
		User user=this.userService.GetUserById(userid);
		if(user!=null)
		{
			request.setAttribute("user", user);
		}
		return "foreground/modifyuser";
	}
	@RequestMapping(value="/deleteusers.action",method=RequestMethod.POST)
	public void DeleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userid=request.getParameter("userid");
		User user=this.userService.GetUserById(userid);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;UTF-8");
		if(user!=null)
		{
			boolean flag=this.userService.DeleteUserById(userid);
			if(flag)
			{
				response.getWriter().write("删除用户成功");
			}else
			{
				response.getWriter().write("删除用户失败");
			}
		}else{
			response.getWriter().write("你要删除的用户不存在");
		}
	}
	@RequestMapping(value="/modifyusers.action",method=RequestMethod.POST)
	public void ModifyUserPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userid=request.getParameter("userid");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;UTF-8");
		boolean flag=this.userService.ModifyUser(userid,name,age);
		if(flag)
		{
			response.getWriter().write("修改成功");
		}else
		{
			response.getWriter().write("修改失败");
		}
	}
	
	@RequestMapping(value="/getEmailPage.html",method=RequestMethod.GET)
	public String EmailPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "foreground/email";
	}
	@RequestMapping(value="/uploadPicture.action",method=RequestMethod.POST)
	public void UploadImg(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		ResultModel result=new ResultModel();
		try {
			if(!file.isEmpty())
			{
				String path=(HttpUtil.getString("upload")+"Email"+"/"+"Img"+"/").replace("/", File.separator);
				String name=file.getOriginalFilename();
				char[] chars=name.substring(0, name.lastIndexOf(".")).toCharArray();
				String filename="";
				for(int i=0;i<chars.length;i++)
				{
					filename+=Integer.toString(chars[i],16);
				}
				String absoluteDirectory=request.getSession().getServletContext().getRealPath(path);
				File filesDirectory =new File(absoluteDirectory);
				if(!filesDirectory.exists())
				{
					filesDirectory.mkdirs();
				}
				String absolutePath=(absoluteDirectory+"/").replace("/", File.separator)+filename+new Date().getTime()+"."+name.substring(name.lastIndexOf(".")+1,name.length());
				FileOutputStream out=new FileOutputStream(absolutePath);
				FileInputStream in=(FileInputStream) file.getInputStream();
				int b=0;
				while((b=in.read())!=-1)
				{
					out.write(b);
				}
				out.flush();
				out.close();
				in.close();
				HttpSession session=request.getSession();
				//session.setAttribute("", arg1);
			}else
			{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	@RequestMapping(value="/sendEmail.action",method=RequestMethod.POST)
	public void SendEmail(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		ResultModel result=new ResultModel();
		//String name=request.getParameter("name");
		String eamilFrom=request.getParameter("eamilFrom");
		String emailTo=request.getParameter("emailTo");
		String emailSubject=request.getParameter("emailSubject");
		String content=request.getParameter("content");
		//通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		final String smtpHostName="smtp."+eamilFrom.split("@")[1];
		if(!file.isEmpty())
		{
			String path=(HttpUtil.getString("upload")+"Email"+"/").replace("/", File.separator);
			String name=file.getOriginalFilename();
			char[] chars=name.substring(0, name.lastIndexOf(".")).toCharArray();
			String filename="";
			for(int i=0;i<chars.length;i++)
			{
				filename+=Integer.toString(chars[i],16);
			}
			String absoluteDirectory=request.getSession().getServletContext().getRealPath(path);
			File filesDirectory =new File(absoluteDirectory);
			if(!filesDirectory.exists())
			{
				filesDirectory.mkdirs();
			}
			String absolutePath=(absoluteDirectory+"/").replace("/", File.separator)+filename+new Date().getTime()+"."+name.substring(name.lastIndexOf(".")+1,name.length());
			FileOutputStream out=new FileOutputStream(absolutePath);
			FileInputStream in=(FileInputStream) file.getInputStream();
			int b=0;
			while((b=in.read())!=-1)
			{
				out.write(b);
			}
			out.flush();
			out.close();
			in.close();
			
			Map<String,String> attachment=new HashMap<String, String>();
			attachment.put(name, absolutePath);
			StringBuilder builder=new StringBuilder();
			builder.append("<html><body>看看附件中的内容吧<br/>");
			builder.append("<div>"+content+"</div></br>");
			builder.append("看看下面的图片，你会更美好：<br/>");
			//builder.append("<img src=\"cid:p1\"/><br/>");
			builder.append("</body></html>");
			MailModel email=new MailModel();
			email.setEmailFrom(eamilFrom);
			email.setEmailHost(smtpHostName);
			email.setContent(builder.toString());
			//email.setEmailPassword("dsijgtjemyxhbcgg");
			email.setEmailPassword("ftpryluwfwaebfac");
			email.setEmailUserName(eamilFrom);
			email.setSubject(emailSubject);
			email.setToEmails(emailTo);
			email.setAttachments(attachment);
			
			result=SendEmails.sendEmail(email);
			
			String str=BeanToJson.beanToJson(result);
			response.getWriter().write(str);
		}else
		{
			StringBuilder builder=new StringBuilder();
			builder.append("<html><body>看看附件中的内容吧<br/>");
			builder.append("<div>"+content+"</div></br>");
			builder.append("看看下面的图片，你会更美好：<br/>");
			//builder.append("<img src=\"cid:p1\"/><br/>");
			builder.append("</body></html>");
			MailModel email=new MailModel();
			email.setEmailFrom(eamilFrom);
			email.setEmailHost(smtpHostName);
			email.setContent(builder.toString());
			email.setEmailPassword("dsijgtjemyxhbcgg");
			email.setEmailUserName(eamilFrom);
			email.setSubject(emailSubject);
			email.setToEmails(emailTo);
			//email.setAttachments(attachment);
			
			result=SendEmails.sendEmail(email);
			
			String str=BeanToJson.beanToJson(result);
			response.getWriter().write(str);
		}
	}
}
