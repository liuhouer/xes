<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.UserUtils"%>
<%@page import="com.up72.base.UserDetails"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	UserUtils outStyleOutSkinUserUtils = new UserUtils();
	UserDetails outUserDetails = outStyleOutSkinUserUtils.getSessionUser(request, 0);
	String outStyle = "system";
	String outSkin = "mac";
	if(null!=outUserDetails 
		&& null != outUserDetails.getStyle() 
		&& !"".equals(outUserDetails.getStyle().trim())){
		outStyle = outUserDetails.getStyle();
	}
	if(
		null!=outUserDetails 
		&& null!=outUserDetails.getSkin() 
		&& !"".equals(outUserDetails.getSkin().trim())){
		outSkin = outUserDetails.getSkin();
	}
	request.setAttribute("outStyle", outStyle);
	request.setAttribute("outSkin", outSkin);
%>
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${outStyle}/global.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${outStyle}/structs_outter.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${outStyle}/skins/${outSkin}/skin_outter.css"/>" />

<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${outStyle}/structs_inner.css"/>" />
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/${outStyle}/skins/${outSkin}/skin_inner.css"/>" />