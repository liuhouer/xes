<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"type":"${type}","split":${split},"is_end":${is_end},"start":${start},"range":${range},"result":[
<c:if test="${!empty result}">
	<c:forEach items="${result}" var="question" varStatus="status">
	<c:set value="${question.student}" var="student"/>
		<c:if test="${status.index!=0}">,</c:if>
		{
			"id":"${question.id}",
			"imgPath":"${ctx}${question.imgPath}",
			"addTimeStr":"${question.addTimeStr}",
			"sourceType":"${question.sourceType}",
			"content":"<c:out value='${question.content}' />",
			"stopTime":"${question.stopTime}",
			"satisfiedCount":"${question.satisfiedCount}",
			"unsatisfiedCount":"${question.unsatisfiedCount}",
			"pageViewCount":"${question.pageViewCount}",
			"status":"${question.status}",
			"status":"${question.status}",
			"subject":"${question.subject.name}",
			"grade":"${question.grade.name}",
			"student":{
				"nickName":"${student.nickName}",
				"imgPath":"${ctx}${student.imgPath}"
			},
			"type":"${type}"
		}
	</c:forEach>
</c:if>
]}