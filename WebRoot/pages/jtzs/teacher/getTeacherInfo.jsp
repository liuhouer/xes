<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
<c:if test="${!empty teacher}">
	"teacher":{
		"id":"${teacher.id}",
		"nickName":"${teacher.nickName}",
		"realName":"${teacher.realName}",
		"provinceId":"${teacher.provinceId}",
		"province":"${teacher.province.name}",
		"cityId":"${teacher.cityId}",
		"city":"${teacher.city.name}",
		"sex":"${teacher.sex}",
		"sexStr":"${teacher.sexStr}",
		"addTime":"${teacher.addTime}",
		"levelStr":"${teacher.levelStr}",
		"level":"${teacher.level}",
		"imgPath":"${teacher.imgPath}",
		"expertSubjectId":"${teacher.expertSubjectId}",
		"expertSubject":"${teacher.expertSubject.name}",
		"expertGradeList":[
			<c:forEach items="${teacher.expertGradeList}" var="item" varStatus="status">
				<c:if test="${status.index!=0}">,</c:if>
				{
					"name":"${item.name}",
					"id":"${item.id}"
				}
			</c:forEach>
		]
		
	},
	<c:set var="commentCount" value="${teacher.commentCount}"/> 
	"satisfied":"${commentCount.satisfied}",
	"unsatisfied":"${commentCount.unsatisfied}",
	<c:set var="answerCount" value="${teacher.answerCount}"/> 
	"quitCount":"${answerCount.quitCount}",
	"answerCount":"${answerCount.answerCount}",
	"scoreCount":"${teacher.score.remainScore}",
	"messageCount":"0",
	"eventCount":"${eventSize }"
</c:if>
}}
