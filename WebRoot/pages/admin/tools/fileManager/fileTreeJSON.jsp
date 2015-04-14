<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty filePathList}">
[
	<c:forEach items="${filePathList}" var="filePath" varStatus="status">
	{
		"text": "<a href='${ctx}/admin/tools/fileManager?dir=${filePath.filePath}' target='resource_frame'>${filePath.name}</a>",
		"id": "${filePath.filePath}",
		"classes" : "<c:choose><c:when test="${filePath.childFileCount > 0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>",
		"hasChildren":<c:choose><c:when test="${filePath.childFileCount > 0}">true</c:when><c:otherwise>false</c:otherwise></c:choose>
	}<c:if test="${!(status.last)}">,</c:if>
	</c:forEach>
]
</c:if>