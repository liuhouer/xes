<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.js"></script>
<link type="text/css" href="${ctx}/js/uploadify/uploadify.css" rel="stylesheet" />

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
	pageContext.setAttribute("autoUpload",Boolean.parseBoolean(autoUpload));
	
	// 允许上传的最大队列数
	String queueLimit = request.getParameter("queueLimit");
	if(null == queueLimit
		|| queueLimit.trim().equals("")){
		queueLimit = "1";
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
	if(null == fileExt
		|| fileExt.trim().equals("")){
		fileExt = "*.jpg;*.gif;*.png";
	}
	pageContext.setAttribute("fileExt",fileExt);
	
	// 是否做数据库保存
	String isCreateMini = request.getParameter("isCreateMini");
	pageContext.setAttribute("isCreateMini",isCreateMini);
	
	String zoom = request.getParameter("zoom");
	pageContext.setAttribute("zoom",zoom);
%>

<script type="text/javascript">

function show(event, ID, fileObj, response, data){
	alert(fileObj.filePath);
}

$(function() {
	$('#file_upload').uploadify({
	  'uploader'       : '/js/uploadify/uploadify.swf',
	  'script'         : '/common/uploadFile/doUpload.jhtml?isCreateMini=${isCreateMini}',
	  'cancelImg'      : '/js/uploadify/cancel.png',
	  'buttonImg'	   : '/js/uploadify/selectfiles.jpg',
	  'folder'         : '${uploadFolder}',
	  <c:if test="${queueLimit != '1'}">
	  'multi'          : true,
	  </c:if>
	  'auto'           : false,
	  'fileExt'        : '${fileExt}',
	  'sizeLimit'      : ${sizeLimit},
	  'fileDesc'       : '文件类型(${fileExt})',
	  'queueID'        : 'uploadFileList',
	  <c:if test="${queueLimit != '1'}">
	  'queueSizeLimit' : ${queueLimit},
	  </c:if>
	  'simUploadLimit' : 2,
	  'removeCompleted': false,
	  'onComplete' 	   : function(event, ID, fileObj, response, data){
	  		// 获得上传后返回的数据
	  		if(typeof(response).toLowerCase() == 'string'){
				response = $.parseJSON(response);
			}
			// id列表
			var ids = response.data.ids;
			if(typeof(ids).toLowerCase() == 'string'){
				ids = eval(ids);
			}
			// 文件路径列表
			var filePaths = response.data.filePaths;
			if(typeof(filePaths).toLowerCase() == 'string'){
				filePaths = eval(filePaths);
			}
			// 保存文件名
			var fileNames = response.data.fileNames;
			if(typeof(fileNames).toLowerCase() == 'string'){
				fileNames = eval(fileNames);
			}
			// 上传的文件名列表
			var uploadFileNames = response.data.uploadFileNames;
			if(typeof(uploadFileNames).toLowerCase() == 'string'){
				uploadFileNames = decodeURIComponent(uploadFileNames);
				uploadFileNames = eval(uploadFileNames);
			}
			response.data.ids = ids;
			response.data.filePaths = filePaths;
			response.data.fileNames = fileNames;
			response.data.uploadFileNames = uploadFileNames;
	  	  var callBack = "${callBack}";
		  if(callBack != ""){
		  	eval(callBack);
		  }
	  },
	  'onSelectOnce'   : function(event,data) {
		  $('#status-message').text(data.filesSelected + ' 文件添加至任务列表。');
	  },
	  'onAllComplete'  : function(event,data) {
		  $('#status-message').text(data.filesUploaded + ' 个文件成功上传, ' + data.errors + ' 个发生错误。');
		},
	  'onQueueFull'	   : function(event,data){
	  	  alert("选择的文件大于${queueLimit}个。多选的文件将不会上传。");
	   }
	});				
});

function uploadFile(){
	var zoom = $("input[name='zoomSize']")[0];

	if($(zoom).attr("checked")){
		zoom = $(zoom).val();
	}else{
		zoom = "";
	}
	
	$('#file_upload').uploadifySettings('scriptData',
		{
			"zoomSize" : zoom
		});
	$('#file_upload').uploadifyUpload();
}
</script>

<div id="status-message">选择要上传的文件<c:if test="${queueLimit != '1'}">(最多可选${queueLimit}个)</c:if>:</div>
<input width="120" height="30" type="file" name="file" id="file_upload" style="display: none;">
<div id="uploadFileList"></div>

<div>
<form id="uploadParamForm" name="uploadParamForm" onsubmit="return false;">
是否缩放：<input type="checkbox" id="zoom	Size" name="zoomSize" value="${zoom}" /><br />
<input type="button" id="upload_button" value="上传" onclick="uploadFile()" />
</form>
</div>