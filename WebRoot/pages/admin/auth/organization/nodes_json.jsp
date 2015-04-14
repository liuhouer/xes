<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:choose>
<c:when test="${type == 'o'}">
[
	<c:forEach items="${itemList}" var="item" varStatus="status">
	{
		"id" : "o_${item.id}",
		"name" : "${item.name}",
		"isParent" :"${item.isParent}"
	}<c:if test="${!status.last}">,</c:if>
	</c:forEach>
]
</c:when>
<c:otherwise>
[
	<c:forEach items="${itemList}" var="item" varStatus="status">
	{
		"id" : "w_${item.id}",
		"name" : "${item.name}",
		"isParent" :"${item.isParent}"
	}<c:if test="${!status.last}">,</c:if>
	</c:forEach>
]
</c:otherwise>
</c:choose>

