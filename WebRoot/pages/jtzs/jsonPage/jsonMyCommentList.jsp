<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"type":"${type}","split":${split},"is_end":${is_end},"start":${start},"range":${range},"result":[
<c:if test="${!empty result}">
	<c:forEach items="${result}" var="comment" varStatus="status">
	<c:set value="${comment.student}" var="student"/>
		<c:if test="${status.index!=0}">,</c:if>
		{
			"id":"${comment.id}",
			"isSatisfied":"${comment.isSatisfied}",
			"content":"${comment.content}",
			"addTimeStr":"${comment.addTimeStr}",
			"student":{
				"nickName":"${student.nickName}",
				"imgPath":"${ctx}${student.imgPath}"
			},
			"type":"${type}"
		}
	</c:forEach>
</c:if>
]}