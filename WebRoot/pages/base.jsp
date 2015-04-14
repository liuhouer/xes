<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<%@ include file="/common/meta.jsp" %>
	<base href="<%=basePath%>">
	<up72:block name="head"/>
</head>
<body>
	<%@ include file="/common/messages.jsp" %>
	<up72:block name="content"/>
</body>
</html>	