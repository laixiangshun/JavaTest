package com.foxera.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foxera.models.RtvHead;
import com.foxera.models.User;
import com.foxera.service.DemoService;
import com.foxera.util.ExportExcel;

/**
 * 注解@Controller表示这是一个控制器，也是拦截器，所有对系统的请求都会经过控制器拦截
 * @author fox
 * @date 2016-11-24
 */
@Controller
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	
	@RequestMapping(value="/userindex.html",method=RequestMethod.GET)
	public String ToIndex(HttpServletRequest request,HttpServletResponse response){
		List<User> users=this.demoService.getUsers();
		request.setAttribute("users", users);
		return "foreground/index";
	}
	
	@RequestMapping(value="/rtv.html",method=RequestMethod.GET)
	public String ToMessage(HttpServletRequest request,HttpServletResponse response)throws IOException{
		//String rtvNo=request.getParameter("rtvNo");
		List<RtvHead> rtv=this.demoService.selectRtv();
		request.setAttribute("rtvs", rtv);
		return "foreground/message";
	}
	
	@RequestMapping(value="/rtv.action",method=RequestMethod.GET)
	public String searchString(HttpServletRequest request,HttpServletResponse response)throws IOException {
		
		return "foreground/message";
	}
	@RequestMapping(value="/adduser.action",method=RequestMethod.POST)
	public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		boolean flag=this.demoService.addUser(name, Integer.parseInt(age), Integer.parseInt(sex));
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		if(flag){
			response.getWriter().write("新增成功");
		}else {
			response.getWriter().write("新增失败");
		}
	}
	
	@RequestMapping(value="/deleteuser.action",method=RequestMethod.POST)
	public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userid=request.getParameter("userid");
		boolean flag=this.demoService.removeUser(Integer.parseInt(userid));
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		if(flag){
			response.getWriter().write("删除成功");
		}else {
			response.getWriter().write("删除失败");
		}
	}
	
    @RequestMapping(value="/deletertv.action",method=RequestMethod.POST)
	public void deleteRtv(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String rtvno=request.getParameter("rtvno");
		boolean flag=this.demoService.deleteRtv(rtvno);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		if (flag) {
			response.getWriter().write("删除成功");
			
		} else {
			response.getWriter().write("删除失败");

		}
	}
	
	
	@RequestMapping(value="/getuserdetail.html",method=RequestMethod.GET)
	public String detailhtml(HttpServletRequest request){
		String userid=request.getParameter("userid");
		User user=this.demoService.getUser(Integer.parseInt(userid));
		request.setAttribute("user", user);
		return "userdetail";
	}
	
	@RequestMapping(value="/modifyuser.html",method=RequestMethod.GET)
	public String modifyuser(HttpServletRequest request){
		String userid=request.getParameter("userid");
		User user=this.demoService.getUser(Integer.parseInt(userid));
		request.setAttribute("user", user);
		return "foreground/modifyuser";
	}
	
	@RequestMapping(value="/modifyuser.action",method=RequestMethod.POST)
	public void modifyuseraction(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userid=request.getParameter("userid");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		boolean flag=this.demoService.modifyUser(Integer.parseInt(userid), name, Integer.parseInt(age), Integer.parseInt(sex));
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		if(flag){
			response.getWriter().write("修改成功");
		}else {
			response.getWriter().write("修改失败");
		}
	}
	
	/**
	 * 导出到excel
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/exporttoexcel.action",method=RequestMethod.GET)
	public void exportToExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<User> userlist=this.demoService.getUsers();
		String[] title={"用户编号","用户姓名","用户年龄","用户性别"};
		String[] field={"id","userName","age","sex"};
		String result=ExportExcel.exportExcel("用户统计表.xls", title,field, userlist,new User(), response);
		
	}
	
	
}
