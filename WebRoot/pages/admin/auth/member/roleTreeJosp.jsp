<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty productList}">
[
	<c:forEach items="${productList}" var="product" varStatus="status">
	{
		"text": "<a href='${ctx}/admin/auth/member?id=${product.id}' target='member_frame'>${product.name}</a>",
		"id": "${product.id}",
		"classes" : "<c:choose><c:when test="${fn:length(permGroup.menuPermList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>",
		"hasChildren":<c:choose><c:when test="${fn:length(permGroup.menuPermList)>0}">true</c:when><c:otherwise>false</c:otherwise></c:choose>
	}<c:if test="${!(status.last)}">,</c:if>
	</c:forEach>
]
</c:if>
