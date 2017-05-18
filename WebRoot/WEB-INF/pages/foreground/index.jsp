<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../included/path_lib.jsp"%>
<%@ include file="../included/taglib_lib.jsp"%>
<%@ include file="../included/jquery_lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	
		$(function() {
		
			$("#adduserbtn").click(function() {
				var name=$("#userName").val();
				//var sex=$("input[name='sex']:checked").val();
				var age=$("#age").val();
				$.post(
					"${path}/addUsers.action",{
						"name":name,
						//"sex":sex,
						"age":age
					},function(data,status){
						if(status=="success"){
							alert(data);
							window.location.reload();
						}
					}
				);
			});
			$(".deleteuserbtn").click(function() {
				var  userid=$(this).siblings("#userid").val();
				$.post(
					"${path}/deleteusers.action",{
						"userid":userid
					},function(data,status){
						if(status=="success"){
							alert(data);
							window.location.reload();
						}
					}
				);
			});
			$(".userdetailbtn").click(function() {
				var  userid=$(this).siblings(".userid").val();
				/* 三种跳转方式 */
				/*第一种，ajax方式，地址栏不变，可局部刷新  */
				$.get(
					"${path}/getuserdetail.html",{
						"userid":userid
					},function(data,status){
						if(status=="success"){
							$("body").html(data);
						}
					}
				);
				
				/*第二种，href本页跳转  */
				/* window.location.href="${path}/getuserdetail.html?userid="+userid; */
				
				/*第三种，打开新窗口  */
				/* window.open("${path}/getuserdetail.html?userid="+userid, "_blank"); */
			});
			
			$(".usermodifybtn").click(function() {
				var  userid=$(this).siblings("#userid").val();
				$.get(
					"${path}/modifyusers.html",{
						"userid":userid
					},function(data,status){
						if(status=="success"){
							$("body").html(data);
						}
					}
				);
			});
			$("#eamil").click(function(){
				window.location.href="${path}/getEmailPage.html";
			});
		});
	</script>
  </head>
  
  <body>
  	新增用户：
  	姓名：<input type="text" id="userName">
  	年龄：<input id="age" type="text">
  	<br><input type="button" id="adduserbtn" value="新增"><br>
  	<c:if test="${requestScope.users!=null }">
	    <c:forEach items="${requestScope.users }" var="user">
	    	<div class="useritem">
	    		<span>姓名:</span>${user.userName }
		    	<span>年龄:</span>${user.age }
		    	<input type="hidden" value="${user.id }" id="userid">
		    	<button class="userdetailbtn">查看详情</button>
		    	<button class="usermodifybtn">修改</button>
		    	<button class="deleteuserbtn">删除</button>
	    	</div>
	    </c:forEach>
    </c:if>
    <c:if test="${requestScope.users==null }">
    	<div>
    		<span><font>没有数据</font></span>
    	</div>
    </c:if>
    <button id="eamil">发送邮件</button>
  </body>
</html>
