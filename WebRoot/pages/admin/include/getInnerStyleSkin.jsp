<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.UserUtils"%>
<%@page import="com.up72.base.UserDetails"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	UserUtils styleSkinUserUtils = new UserUtils();
	UserDetails userDetails = styleSkinUserUtils.getSessionUser(request, 0);
	String style = "system";
	String skin = "mac";
	if(null!=userDetails 
		&& null!=userDetails.getStyle() 
		&& !"".equals(userDetails.getStyle().trim())){
		style = userDetails.getStyle();
	}
	if(null!=userDetails  
		&& null!=userDetails.getSkin() 
		&& !"".equals(userDetails.getSkin().trim())){
		skin = userDetails.getSkin();
	}
	request.setAttribute("style", style);
	request.setAttribute("skin", skin);
%>
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style}/global.css"/>" />

<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style}/structs_inner.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${style}/skins/${skin}/skin_inner.css"/>" />