<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用开拓明天运营平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="开拓明天运营平台">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
	
	<script src="${ctx}/scripts/weebox/bgiframe.js" type="text/javascript"></script>
	<script src="${ctx}/scripts/weebox/weebox.js" type="text/javascript"></script>
	<link href="${ctx}/scripts/weebox/weebox.css" type="text/css" rel="stylesheet"  />
	
	<script type="text/javascript" src="${ctx}/scripts/main.js"></script>
	
	<script type="text/javascript">
		function uploadCall(event, ID, fileObj, response, data){
			var ids = response.ids;
			var filePaths = response.filePaths;
			var uploadFileNames = response.uploadFileNames;
			
			// 获得父级的显示层
			var div = window.parent.document.getElementById("upfileDiv");
			
			// 更新层信息
			for(var i=0;i<ids.length;i++){
				div.innerHTML += "<a href='"+filePaths[i]+"' target='_blank'>"
			  		+ uploadFileNames[i]
			  		+"<img src='"+filePaths[i]+"'/></a><br />";
			}
			
		}
	</script>
  </head>
  
  <body>
    <a href="javascript:" onClick="showCommonUpload({autoUpload:false,width:550,height:250,uploadFolder:'/uploads',queueLimit:1,isSave:0,callBack:'window.parent.uploadCall(event, ID, fileObj, response, data)'})">上传</a>
    <div id="upfileDiv">
    </div>
  </body>
</html>
