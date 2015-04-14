<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty categoryList}">
[
	<c:forEach items="${categoryList}" var="category" varStatus="status">
	{
		"text": "<input type=\"checkbox\" id=\"cateogryId_${category.id}\" name=\"category_ids\" value=\"${category.guid}\" /><a href='${ctx}/admin/sys/sysCategory/indexForm?id=${category.id}' target='resource_frame'>${category.catName}</a>",
		"id": "${category.guid}",
		"classes" : "<c:choose><c:when test="${category.childCount > 0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>",
		"hasChildren":<c:choose><c:when test="${category.childCount > 0}">true</c:when><c:otherwise>false</c:otherwise></c:choose>
	}<c:if test="${!(status.last)}">,</c:if>
	</c:forEach>
]
</c:if>