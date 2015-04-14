<%@ page language="java" import="com.bruce.util.*" pageEncoding="utf-8"%>
<%!
/**
 * 根据urlString抓取页面代码
 */
private static final String $crawHtml(String urlString){
	return CrawlUtil.getHtml(urlString);
}
/**
 * 根据实际文件夹filePath 文件名 fileName 文件内容 content 生成文件
 */
private static final void $createFile(String filePath,String fileName,String content){
	FileUtil.createFile(filePath,fileName,content);
}
%>