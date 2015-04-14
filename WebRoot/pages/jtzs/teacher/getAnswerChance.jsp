<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
<c:if test="${!empty question}">
	"id":"${question.id}",
	"content":"<c:out value="${question.content}"/>",
	"imgPath":"${question.imgPath}",
	"grade":"${question.grade.name}",
	"subject":"${question.subject.name}",
	"addTime":"${question.addTimeStr}",
	"stopTime":"${question.stopTime}",
	"stopTimeStr":"${question.restTimeStr}",
	"sourceType":"${question.sourceType}",
	"sourceTypeStr":"${question.sourceTypeStr}",
	"studentNickName":"${question.student.nickName}",
	"studentImgPath":"${question.student.imgPath}"
</c:if>
}}