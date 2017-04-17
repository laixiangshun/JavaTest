package com.foxera.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	/**
	 * 首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/index.html",method=RequestMethod.GET)
	public String ToIndex(HttpServletRequest request,HttpServletResponse response){
		return "foreground/index";
	}
	
	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String ToLogin(HttpServletRequest request,HttpServletResponse response){
		return "login";
	}
	
	/**
	 * 执行登录操作
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public void Login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		String userName=request.getParameter("loginName");
		String userPassword=request.getParameter("loginPassword");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			if(true){
				session.setAttribute("user", "user");
				response.getWriter().print("true");
			}else {
				response.getWriter().print("false");
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 执行注销操作
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/logout.action",method=RequestMethod.GET)
	public void toLogout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
//		注销session
		session.invalidate();
		try {
			//重定向到登录界面
			response.sendRedirect(request.getContextPath()+"/login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据传递过来的值判断main 区域该显示哪一块内容
	 * @param request
	 * @param v
	 * @return
	 */
	@RequestMapping(value="/changeMainView.action",method=RequestMethod.GET)
	public ModelAndView toUserHome(HttpServletRequest request,ModelAndView v){
		String url=request.getParameter("toViewUrl");
		v.setViewName("foreground/"+url);
		return v;
	}
}
