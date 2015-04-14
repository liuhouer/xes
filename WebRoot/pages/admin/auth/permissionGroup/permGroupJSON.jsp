<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach items="${permissionGroupList}" var="permGroup" varStatus="status">
	{
		"id":"${permGroup.id}",
		"name":"${permGroup.name}",
		"code":"${permGroup.code}",
		"description":"${permGroup.description}",
		"productCode":"${permGroup.productCode}",
		"sortId":"${permGroup.sortId}",
		"imgPath":"${permGroup.imgPath}",
		"status":"${permGroup.status}"
	}
	<c:if test="${!(status.last)}">,</c:if>
</c:forEach>
]