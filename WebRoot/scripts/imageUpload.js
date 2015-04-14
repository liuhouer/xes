function uploadImgCall(event, ID, fileObj, response, data){
	var ids = response.ids;
	var filePaths = response.filePaths;
	var uploadFileNames = response.uploadFileNames;
	var fileNames = response.fileNames;
	// 更新层信息
	for(var i=0;i<ids.length;i++){
		$("#imgPath").val(filePaths[i]);
	}
}

$(function() {
	$('#file_upload_img').uploadify({
	  'uploader'       : '/scripts/uploadify/uploadify.swf',
	  'script'         : '/common/uploadFile/upload?isSave=1',
	  'cancelImg'      : '/scripts/uploadify/cancel.png',
	  'buttonImg'	   : '/images/upload_button.jpg',
	  'folder'         : '/uploads',
	  'auto'           : true,
	  'height'		   : '25',
	  'width'		   : '64',
	  'fileExt'        : '*.jpg;*.gif;*.jpeg;*.bmp;',
	  'sizeLimit'      : 1024*1024*20,
	  'fileDesc'       : '文件类型*.jpg;*.gif;*.jpeg;*.bmp;',
	  'queueID'        : 'uploadFileList',
	  'simUploadLimit' : 2,
	  'removeCompleted': false,
	  'onComplete' 	   : function(event, ID, fileObj, response, data){
	  		// 获得上传后返回的数据
	  		if(typeof(response).toLowerCase() == 'string'){
				response = $.parseJSON(response);
			}
			// id列表
			var ids = response.ids;
			if(typeof(ids).toLowerCase() == 'string'){
				ids = eval(ids);
			}
			// 文件路径列表
			var filePaths = response.filePaths;
			if(typeof(filePaths).toLowerCase() == 'string'){
				filePaths = eval(filePaths);
			}
			// 保存文件名
			var fileNames = response.fileNames;
			if(typeof(fileNames).toLowerCase() == 'string'){
				fileNames = eval(fileNames);
			}
			// 上传的文件名列表
			var uploadFileNames = response.uploadFileNames;
			if(typeof(uploadFileNames).toLowerCase() == 'string'){
				uploadFileNames = decodeURIComponent(uploadFileNames);
				uploadFileNames = eval(uploadFileNames);
			}
			response.ids = ids;
			response.filePaths = filePaths;
			response.fileNames = fileNames;
			response.uploadFileNames = uploadFileNames;
			
		  	uploadImgCall(event, ID, fileObj, response, data);
	  },
	  'onSelectOnce'   : function(event,data) {
		  $('#status-message').text(data.filesSelected + ' 文件添加至任务列表。');
		  $("#uploadButton").show();
	  },
	  'onAllComplete'  : function(event,data) {
		},
	  'onQueueFull'	   : function(event,data){
	  	  alert("选择的文件大于1个。多选的文件将不会上传。");
	   }
	});				
});