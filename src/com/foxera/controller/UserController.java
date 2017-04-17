package com.foxera.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foxera.models.User;
import com.foxera.service.UserService;

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
}
