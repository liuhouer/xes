<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach items="${workGroupList}" var="workGroup" varStatus="status">
	{
		"id":"${workGroup.id}",
		"name":"${workGroup.name}",
		"remark":"${workGroup.remark}",
		"parent":"${workGroup.parent}",
		"enabled":"${workGroup.enabled}",
		"deptLevel":"${workGroup.deptLevel}",
		"organizationId":"${workGroup.organizationId}"
	}
	<c:if test="${!(status.last)}">,</c:if>
</c:forEach>
]