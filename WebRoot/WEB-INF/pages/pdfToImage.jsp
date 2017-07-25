<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="included/path_lib.jsp"%>
<%@ include file="included/jquery_lib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <input type="button" value="pdf转Image" onclick="pdfToImage();">
   	<div>
   		<img alt="pdf" src="" id="pdftoimg">
   	</div>
    <script type="text/javascript">
    	//window.location.href="${path}/pdfToImageSave.action";
    	function pdfToImage(){
    		var url="${path}/pdfToImageSave.action";
    		$.ajax({
    			url: url,
    			type: "post",
    			cache: false,
    			success: function(data){
    				//alert(data);
    				if(data!=null && data!=""){
    					$("#pdftoimg").attr("src",data);
    				}
    			}
    		});
    	}
    </script>
  </body>
</html>
