<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<%@include file="include/getOutterStyleSkin.jsp" %>
<%@include file="include/resource.jsp" %>
<up72:block name="head" />
</head>
<body>
<div class="up72_dowrap">
	<!--top start-->
     	<%@include file="include/header.jsp" %>
    <!--top end-->
    <%@ include file="/common/messages.jsp"%>
    <!--main start-->
    <div class="up72_domain skin_domain">
	<up72:block name="content" />
    </div>
    <!--main end-->
    
    <!--foot start-->
		<%@include file="/pages/admin/include/footer.jsp" %>
    <!--foot end-->
</div>
</body>
</html>