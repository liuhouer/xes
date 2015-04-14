<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/styles/app/appmarket.css"/>" />
</head>
<body>
<div class="AppMarket_myapp_container">
 <c:forEach items="${permissionTreeMap}" var="item" varStatus="status">
 <c:forEach items="${item.value.permissionList}" var="permission" >
    <div class="AppMarket_myapp_app_item">
    	<a href="${ctx}${permission.url}" title="${permission.name}">
        <span class="AppMarket_myapp_icon_container">
            <span class="AppMarket_myapp_icon"><img src="${ctx}/styles/app/images/icon_default.png" /></span>
        </span>
        <span class="AppMarket_myapp_title">${permission.name}</span>
        </a>
    </div>
    </c:forEach>
 </c:forEach> 
</div>
</body>
