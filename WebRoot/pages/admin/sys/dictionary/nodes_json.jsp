<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
[
	<c:forEach items="${dictionaryList}" var="item" varStatus="status">
	{
		"id" : "${item.id}",
		"name" : "${item.name}",
		"isParent" :"${item.parent}" 
	}<c:if test="${!status.last}">,</c:if>
	</c:forEach>
]

