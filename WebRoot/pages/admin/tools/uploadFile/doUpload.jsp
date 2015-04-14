<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
private java.util.Map<String, Object> $uploadFile(
			org.springframework.web.multipart.MultipartFile file, String root,
			String folder, String fileext) {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>();
		String savePath = root + folder;
		java.io.File path = new java.io.File(savePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		savePath = savePath.replace('\\', '/');
		savePath = savePath.replaceAll("/{2,}", "/");
		if (!savePath.endsWith("/")) {
			savePath = savePath + "/";
		}

		String ext = file.getOriginalFilename();
		if("jsp,asp,php,class,bin,sh,bat".indexOf(ext.toLowerCase()) >= 0){
			return result;
		}
		ext = ext.substring(ext.lastIndexOf('.'));
		String fileName = System.currentTimeMillis() + ext;
		int id = 0;
		try {
			folder = folder.startsWith("/") ? folder : "/" + folder;
			folder = folder.endsWith("/") ? folder : (folder + "/");
			String fileSize = file.getSize() + "";
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					new java.io.File(savePath + fileName));
			fos.write(file.getBytes());
			fos.close();
			result.put("id", id);
			result.put("filePath", (folder + fileName));
			result.put("fileName", fileName);
			result.put("fileSize", fileSize);
			result.put("uploadFileName", (file.getOriginalFilename()));
			result.put("status", "0");
		} catch (Exception e) {
			result.put("status", "1");
			e.printStackTrace();
		}
		return result;
	}%>
<%
	String root = application.getRealPath("/").replace("\\", "/");
	String folder = request.getParameter("folder");
	if(folder == null || "".equals(folder)){
		folder = "upload";
	}
	String date = new java.text.SimpleDateFormat("yyyy-MM-dd")
			.format(new java.util.Date());
	folder = folder + "/" + date;
	String fileext = request.getParameter("fileext");
	StringBuffer ids = new StringBuffer("[");
	StringBuffer statuses = new StringBuffer("[");
	StringBuffer filePaths = new StringBuffer("[");
	StringBuffer fileNames = new StringBuffer("[");
	StringBuffer fileSizes = new StringBuffer("[");
	StringBuffer uploadFileNames = new StringBuffer("[");
	org.springframework.web.multipart.commons.CommonsMultipartResolver commonsMultipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver(
			request.getSession().getServletContext());
	commonsMultipartResolver.setDefaultEncoding("utf-8");
	//org.springframework.web.multipart.MultipartHttpServletRequest multipartRequest = (org.springframework.web.multipart.MultipartHttpServletRequest) request;
	org.springframework.web.multipart.MultipartHttpServletRequest multipartRequest = commonsMultipartResolver
			.resolveMultipart(request);
	java.util.Iterator<String> it = multipartRequest.getFileNames();
	while (it.hasNext()) {
		String fileName = it.next();
		org.springframework.web.multipart.MultipartFile file = multipartRequest
				.getFile(fileName);
		java.util.Map<String, Object> upFileResult = $uploadFile(file,
				root, folder, fileext);
		ids.append(upFileResult.get("id").toString() + ",");
		statuses.append(upFileResult.get("status").toString() + ",");
		filePaths.append("\"" + upFileResult.get("filePath").toString()
				+ "\",");
		fileNames.append("\"" + upFileResult.get("fileName").toString()
				+ "\",");
		fileSizes.append("\"" + upFileResult.get("fileSize").toString() + "\",");
		uploadFileNames.append("\""
				+ upFileResult.get("uploadFileName").toString() + "\",");
	}
	ids.deleteCharAt(ids.length() - 1).append("]");
	statuses.deleteCharAt(statuses.length() - 1).append("]");
	filePaths.deleteCharAt(filePaths.length() - 1).append("]");
	fileNames.deleteCharAt(fileNames.length() - 1).append("]");
	fileSizes.deleteCharAt(fileSizes.length() - 1).append("]");
	uploadFileNames.deleteCharAt(uploadFileNames.length() - 1).append("]");
	StringBuffer outStr = new StringBuffer("{");
	outStr.append("\"ids\":").append(ids).append(",");
	outStr.append("\"statuses\":").append(statuses).append(",");
	outStr.append("\"filePaths\":").append(filePaths).append(",");
	outStr.append("\"fileNames\":").append(fileNames).append(",");
	outStr.append("\"fileSizes\":").append(fileSizes).append(",");
	outStr.append("\"uploadFileNames\":").append(uploadFileNames);
	outStr.append("}");
	ServletOutputStream os = response.getOutputStream();
	os.write(outStr.toString().getBytes());
	os.flush();
	os.close();
%>