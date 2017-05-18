<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../included/path_lib.jsp"%>
<%@ include file="../included/taglib_lib.jsp"%>
<%@ include file="../included/jquery_lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>email</title>
    
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
    <div>
    	<div>
    		发件人
    		<input type="text" id="eamilFrom">
    	</div>
    	<div>
    		收件人
    		<input type="text" id="emailTo">
    	</div>
    	<div>
    		主题
    		<input type="text" id="emailSubject">
    	</div>
    	<div>
    		图片<input type="file" id="picture">
    			<button id="sendPicture">上传图片</button>
    		附件<input type="file" id="attachment" onchange="filechange(this);">
    			<button id="sendAttachment">上传</button>
    	</div>
    	<div>
    		<!-- 内容<textarea rows="30" cols="50" id="content"></textarea> -->
    		<!-- <iframe frameborder="1" id="content" src="javascript:;" style="width:100%;">  -->
    		<div contentEditable="true" id="content"  style="height:300px;width:500px;border-style:solid; border-width:1px; border-color:#AEEEEE"></div> 
    	</div>
    	<button id="send">发送</button>
    </div>
    <script type="text/javascript">
    	/* $(document).ready(function(){
    		$("#content").contentWindow.document.designMode="On";
    	}); */
    	var maxH = 1000;
		var maxW = 1000;
    	function DrawImageExam(ImgD) {
			var preW = 300;
			var preH = 300;
			var image = new Image();
			image.src = ImgD.src;
			if (image.width > maxW || image.height > maxH) {
				alert("图片尺寸过大，请选择" + maxW + "*" + maxH + "的图片！");
				return;
			}
			if (image.width > 0 && image.height > 0) {
				if (image.width / image.height >= preW / preH) {
					if (image.width > preW) {
						ImgD.width = preW;
						ImgD.height = (image.height * preW) / image.width;
					} else {
						ImgD.width = image.width;
						ImgD.height = image.height;
					}
					ImgD.alt = image.width + "×" + image.height;
				} else {
					if (image.height > preH) {
						ImgD.height = preH;
						ImgD.width = (image.width * preH) / image.height;
					} else {
						ImgD.width = image.width;
						ImgD.height = image.height;
					}
					ImgD.alt = image.width + "×" + image.height;
				}
			}
		}
    	$("#picture").change(function(){
    			var innerHtml="";
	    		var index=1;
	    		
	    		if (/msie/.test(navigator.userAgent.toLowerCase()))//判断是否是IE浏览器
				{
					innerHtml+="<img id='uploadimage"+index+"'/>";
					$("#content").append(innerHtml);
					$("#uploadimage"+index).attr("src", $(this).val());
					index++;
				}else
				{
					innerHtml+="<img id='uploadimage"+index+"'>";
					$("#content").append(innerHtml);
					/* var file = $("#picture")[0].files[0];
					var reader = new FileReader();
					if (file) {
						reader.readAsDataURL(file);
					} else {
						//preview.src = "";
						$("#uploadimage"+index).attr("src","");
					}
					reader.onloadend = function() {
						//preview.src = reader.result;
						$("#uploadimage"+index).attr("src",reader.result);
					}; */
					var objurl = getObjectUrl($("#picture").get(0).files[0]);
					if (objurl) {
						$("#uploadimage"+index).attr("src", objurl);
						
					}
					$("#uploadimage"+index).css("width","150px");
					$("#uploadimage"+index).css("height","150px");
					index++;
				}
				
				DrawImageExam($("#picture").val());
				
    	});
    	//建立一个可存取到该file的url
		function getObjectUrl(file) {
			var url = null;
			if (window.createObjectURL != undefined) {
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) {
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) {
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}
    	$("#sendPicture").click(function(){
    		var url="${path}/uploadPicture.action";
    		var formData=new FormData();
    		var name=$("#picture").val();
    		
    		formData.append("file",$("#picture")[0].files[0]);
    		formData.append("name",name);
    		
    		$.ajax({
    			url:url,
    			type: "post",
    			data: formData,
    			processData: false,
    			contentType: false,
    			success: function(data){
    				if(data.sccessful==true)
    				{
    					alert(data.message);
    				}else
    				{
    					alert("上传失败");
    				}
    			}
    		});
    	});
    	
    	$("#send").click(function(){
    		var url="${path}/sendEmail.action";
    		var formData=new FormData();
    		var name=$("#attachment").val();
    		var eamilFrom=$("#eamilFrom").val();
    		var emailTo=$("#emailTo").val();
    		var emailSubject=$("#emailSubject").val();
    		var content=$("#content").val();
    		formData.append("file",$("#attachment")[0].files[0]);
    		formData.append("name",name);
    		formData.append("eamilFrom",eamilFrom);
    		formData.append("emailTo",emailTo);
    		formData.append("emailSubject",emailSubject);
    		formData.append("content",content);
    		$.ajax({
    			url:url,
    			type: "post",
    			data: formData,
    			processData: false,
    			contentType: false,
    			success: function(data){
    				if(data.sccessful==true)
    				{
    					alert(data.message);
    				}else
    				{
    					alert("发送失败");
    				}
    			}
    		});
    	});
    	/* function filechange(target){
    		var fileSize = 0;         
			if (isIE() && !target.files) {     
			  var filePath = target.value;     
			  var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
			  var file = fileSystem.GetFile (filePath);     
			  fileSize = file.Size;    
			} else {    
				fileSize = target.files[0].size;     
			}   
			var size = fileSize / 1024;    
			if(size>8000){  
			 	alert("文件不能大于8M");
			 	target.value="";
			 	return
			}
			var name = target.value;
			var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
			if(fileName !="pdf"){
			    alert("请选择pdf格式文件上传！");
			    target.value="";
			    return
			}
    	}
    	 function isIE() { //ie?  
		    if (!!window.ActiveXObject || "ActiveXObject" in window)  
		        return true;  
		    else  
		        return false;  
		} */
    </script>
  </body>
</html>
