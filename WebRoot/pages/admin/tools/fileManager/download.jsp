<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="com.bruce.common.CommonConstants"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.up72.sys.service.FileManager"%>
<%@page import="java.util.zip.ZipOutputStream"%>
<%@page import="java.util.zip.ZipEntry"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.bruce.common.CommonUtils"%>
<%!
public static Vector<File> expandFileList(String[] files, boolean inclDirs) {
	Vector<File> v = new Vector<File>();
	if (files == null)
		return v;
	files = new FileManager().filterFilePath(files,new HashSet());
	
	for (int i = 0; i < files.length; i++) {
		String filePath = files[i];
		if (null == filePath || filePath.trim().equals("")) {
//			filePath = CommonConstants.ROOTPATH;
		} else {
			filePath = CommonUtils.stringUtil.parseToPath(CommonConstants.ROOTPATH + "/" + filePath);
		}
		v.add(new File(filePath));
	}
	
	for (int i = 0; i < v.size(); i++) {
		File f = (File) v.get(i);
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			for (int n = 0; n < fs.length; n++)
				v.add(fs[n]);
			if (!inclDirs) {
				v.remove(i);
				i--;
			}
		}
	}
	return v;
}
%>
<%
	String[] filePath = request.getParameterValues("items");
	if(filePath == null || filePath.length==0){
		return;
	}
	Vector<File> v = expandFileList(filePath, false);
	String splitPath = CommonConstants.ROOTPATH;
	if(filePath.length == 1){
		if(CommonUtils.fileUtil.isFirectory(splitPath + filePath[0])){
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition","attachment;filename=\""+filePath[0].substring(filePath[0].lastIndexOf("/")+1)+".zip\"");
		}else{
			response.setContentType(CommonUtils.fileUtil.getExtType(filePath[0].substring(filePath[0].lastIndexOf(".")))+";charset=UTF-8");  
            response.setCharacterEncoding("UTF-8");  
            response.setHeader("Content-Disposition", "attachment;filename=\""+filePath[0].substring(filePath[0].lastIndexOf("/")+1)+"\""); 
            BufferedInputStream br=new BufferedInputStream(new FileInputStream(splitPath + filePath[0]));  
            int len=0;  
            byte[] b=new byte[1024];  
            OutputStream outs=response.getOutputStream();  
            while((len=br.read(b))>0){  
                outs.write(b,0,len);  
            }  
            outs.close();  
            br.close();
            return;
		}
	}else{
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition","attachment;filename=\"netfolder.zip\"");
	}
	out.clearBuffer();
	try {
		ZipOutputStream zipout = new ZipOutputStream(response.getOutputStream());
//		zipout.setComment("Created by JSP 文件管理器 1.001");
		for (int i = 0; i < v.size(); i++) {
			File f = v.get(i);
			if (f.canRead()) {
				  if(splitPath.endsWith("/")){
					  splitPath = splitPath.substring(0,splitPath.length()-1);
				  }
				  String path = f.getAbsolutePath();
				  path = path.replace('\\', '/');
				  for(int j=0;j<filePath.length;j++){
					  if(path.indexOf((splitPath + filePath[j]))!=-1){
						  splitPath += filePath[j].substring(0,filePath[j].lastIndexOf("/", filePath[j].length()-2));
						  break;
					  }
				  }
				zipout.putNextEntry(new ZipEntry(path.substring(splitPath.length()+1)));
				BufferedInputStream fr = new BufferedInputStream(new FileInputStream(f));
				int b;
				while ((b = fr.read()) != -1)
					zipout.write(b);
				fr.close();
				zipout.closeEntry();
			}
		}
		zipout.finish();
		zipout.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	out.flush();
%>