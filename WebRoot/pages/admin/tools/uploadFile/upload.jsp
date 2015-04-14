<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.bruce.common.CommonUtils"%>
<%@page import="com.bruce.util.StringUtil"%>
<%@page import="com.bruce.util.FileUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%! 
StringUtil stringUtil = new StringUtil();
FileUtil fileUtil = new FileUtil();
%>
<%
 	// 上传文件的保存目录
 	String uploadFolder = request.getParameter("uploadFolder");
 	if(null == uploadFolder
		|| uploadFolder.trim().equals("")){
		uploadFolder = "/upload";
	}else if(!uploadFolder.startsWith("/")){
		uploadFolder = "/"+uploadFolder;
	}
	uploadFolder = uploadFolder.replace('\\','/');
	uploadFolder = uploadFolder.replaceAll("/{2,}","/");
	pageContext.setAttribute("uploadFolder",uploadFolder);
	
	// 回调函数
	String callBack = request.getParameter("callBack");
	if(null == callBack
		|| callBack.trim().equals("")){
		callBack = "";
	}
 	
	pageContext.setAttribute("callBack",callBack);
	
	// 是否自动上传
	String autoUpload = request.getParameter("autoUpload");
	if(null == autoUpload
		|| autoUpload.trim().equals("")
		|| (!autoUpload.trim().toLowerCase().equals("true") 
			|| !autoUpload.trim().toLowerCase().equals("false"))){
		autoUpload = "true";
	}
	pageContext.setAttribute("autoUpload",autoUpload);
	
	// 允许上传的最大队列数
	String queueLimit = request.getParameter("queueLimit");
	if(null == queueLimit
		|| queueLimit.trim().equals("")){
		queueLimit = "10";
	}
	pageContext.setAttribute("queueLimit",queueLimit);
	
	// 允许上传的文件大小最大
	String sizeLimit = request.getParameter("sizeLimit");
	if(null == sizeLimit
		|| sizeLimit.trim().equals("")){
		sizeLimit = 1024 * 1024 * 3 + "";
	}
	pageContext.setAttribute("sizeLimit",sizeLimit);
	
	// 允许上传的文件类型
	String fileExt = request.getParameter("fileExt");
	if(null == fileExt || fileExt.trim().equals("")){
		fileExt = "*.jpg;*.gif;*.png";
	}
	pageContext.setAttribute("fileExt",fileExt);
	
	// 是否做数据库保存
	String isSave = request.getParameter("isSave");
	pageContext.setAttribute("isSave",isSave);
	
	String url = request.getParameter("url");
	if(null == url
		|| url.trim().equals("")){
		url = "/common/upload/upload?isSave="+isSave;
	}
	pageContext.setAttribute("url",url);
	
	long maxSize = stringUtil.parseLong(sizeLimit);

	String fileSizeString = fileUtil.convertFileSize(maxSize);
	pageContext.setAttribute("fileSizeString",fileSizeString);
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="<c:url value="/scripts/jquery.js"/>" type="text/javascript"></script>
<link href="<c:url value="/scripts/upload/css/uploadify.css"/>" type="text/css" rel="stylesheet"  />
<script src="<c:url value="/scripts/upload/js/swfobject.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/upload/js/jquery.uploadify.v2.1.0.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
function show(event, ID, fileObj, response, data){
	alert(fileObj.filePath);
}

$(function() {
	$('#file_upload').uploadify({
	  'uploader'       : '<c:url value="/scripts/upload/js/uploadify.swf" />',
	  'script'         : '<c:url value="${url}"/>',
	  'cancelImg'      : '<c:url value="/scripts/upload/cancel.png"/>',
	  'buttonImg'	   : '<c:url value="/scripts/upload/selectfiles.jpg"/>',
	  'folder'         : '${uploadFolder}',
	  <c:if test="${queueLimit != '1'}">
	  'multi'          : true,
	  </c:if>
	  'auto'           : ${autoUpload},
	  'fileExt'        : '${fileExt}',
	  'sizeLimit'      : ${sizeLimit},
	  'fileDesc'       : '文件类型(${fileExt})',
	  'queueID'        : 'uploadFileList',
	  <c:if test="${queueLimit != '1'}">
	  'queueSizeLimit' : ${queueLimit},
	  </c:if>
	  'simUploadLimit' : 2,
	  'removeCompleted': false,
	  'fileDataName' : "file", 
	  'onComplete' 	 : function(event, ID, fileObj, response, data){
	  		// 获得上传后返回的数据
	  		if(typeof(response).toLowerCase() == 'string'){
				response = $.parseJSON(response);
			}
			
	  	  	var callBack = "${callBack}";
		 	if(callBack != ""){
		  		eval(callBack);
		 	}
	  },
	  'onSelectOnce'   : function(event,data) {
		  $('#status-message').text(data.filesSelected + ' 文件添加至任务列表');
	  },
	  'onAllComplete'  : function(event,data) {
		  $('#status-message').text(data.filesUploaded + ' 个文件成功上传, ' + data.errors + ' 个发生错误');
		},
	  'onQueueFull'	   : function(event,data){
	  	  alert("<div class='alert_del'>选择的文件大于${queueLimit}个，多选的文件将不会上传</div>");
	   },
	   'onError' : function(event,queueId,fileObj,errorObj){
	   		if(errorObj.type=="File Size"){
	   			errorObj.type = "文件大小超过${fileSizeString}"
	   		}else if(errorObj.type=="HTTP"){
	   			errorObj.type = "网络连接错误";
	   		}else if(errorObj.type=="Security"){
	   			errorObj.type = "浏览器安全设置偏高";
	   		}else{
	   			errorObj.type = "未知错误，上传失败";
	   		}
	   }
	});
});
</script>
</head>
<body style="height: 120px;overflow: hidden;">
<div class="up72_upload">
<fieldset>
    <legend>
		<div id="status-message">
			<div class="alert_del">选择要上传的文件 <c:if test="${queueLimit != '1'}">(最多可选${queueLimit}个)</c:if></div>
		</div>
	</legend>
		<div class="upload_btn">
			<input width="120" height="30" type="file" name="file" id="file_upload" style="display: none;">
			<div id="uploadFileList"></div>
			<c:if test="${!autoUpload}">
			<input type="button" id="upload_button" value="上传" onclick="$('#file_upload').uploadifyUpload()" />
			</c:if>
		</div>
</fieldset>
</div>
</body>