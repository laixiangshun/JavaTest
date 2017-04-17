<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../included/path_lib.jsp"%>
<%@ include file="../included/taglib_lib.jsp"%>
<%@ include file="../included/jquery_lib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    
    <title>消息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	$(function(){
	$("#searchbtn").click(function() {
				var rtvNo=$("#rtvNo").val();
				$.get(
					"${path}/rtv.action",
					function(data,status){
						if(status=="success"){
							alert(data);
							window.location.reload();
						}
					}
				);
			});
	$(".deletertvbtn").click(function() {
				var rtvno=$(this).siblings(".rtvNo").val();
				$.post(
					"${path}/deletertv.action",{
						"rtvno":rtvno
					},function(data,status){
						if(status=="success"){
							alert(data);
							window.location.reload();
						}
					}
				);
			});
			});
	</script>

  </head>
  
  <body>
    消息管理: <br>
    RTV单号:<input type="text" id="rtvNo">
    RTV状态:<input type="text" id="rtvStatus"><br>
    <br><input type="button" id="searchbtn"  class="glyphicon glyphicon-search" value="查询">
    <c:forEach items="${requestScope.rtvs}" var="rtv">
    <div class="messageitem">
    <input type="hidden" class="rtvNo" value="${rtv.rtvNo }">
    RTV单号:${rtv.rtvNo }
    DC仓库:${rtv.dcCode }
    RTV状态:${rtv.rtvStatus }
    <button class="deletertvbtn">删除</button>
    </div>
    
    </c:forEach>
    
  </body>
</html>
