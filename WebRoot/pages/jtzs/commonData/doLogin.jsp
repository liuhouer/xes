<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
<c:if test="${!empty data.id && !empty data.role}">
"id":"${data.id}","role":"${data.role}",
	<c:if test="${data.role==0}">
		"student":{
			"id":"${data.student.id}",
			"loginName":"${data.student.loginName}",
			"nickName":"${data.student.nickName}",
			"imgPath":"${data.student.imgPath}",
			"sexStr":"${data.student.sexStr}",
			"sex":"${data.student.sex}",
			"provinceId":"${data.student.provinceId}",
			"province":"${data.student.province.name}",
			"cityId":"${data.student.cityId}",
			"city":"${data.student.city.name}",
			"areaId":"${data.student.areaId}",
			"area":"${data.student.area.name}",
			"schoolId":"${data.student.schoolId}",
			"school":"${data.student.school.name}",
			"gradeId":"${data.student.gradeId}",
			"grade":"${data.student.grade.name}",
			"division":"${data.student.grade.division}",
			"profile":"${profile}"
		}
	</c:if>
	<c:if test="${data.role!=0}">
		"teacher":{
			"id":"${data.teacher.id}",
			"loginName":"${data.teacher.loginName}",
			"nickName":"${data.teacher.nickName}",
			"imgPath":"${data.teacher.imgPath}",
			"sexStr":"${data.teacher.sexStr}",
			"sex":"${data.teacher.sex}",
			"addTime":"${data.teacher.addTime}",
			"province":"${data.teacher.province.name}",
			"provinceId":"${data.teacher.provinceId}",
			"cityId":"${data.teacher.cityId}",
			"city":"${data.teacher.city.name}",
			"levelStr":"${data.teacher.levelStr}",
			"level":"${data.teacher.level}",
			"expertSubjectId":"${data.teacher.expertSubjectId}",
			"expertSubject":"${data.teacher.expertSubject.name}",
			"expertGradeList":[
				<c:forEach items="${data.teacher.expertGradeList}" var="item" varStatus="status">
					<c:if test="${status.index!=0}">,</c:if>
					{
					"name":"${item.name}",
					"id":"${item.id}"
					}
				</c:forEach>
			]
		}
	</c:if>
	<c:if test="${null!=data.question}">
		,"hasQuestion":"true",
		"question":{
			"id":"${data.question.id}",
			"imgPath":"${data.question.imgPath}",
			"grade":"${data.question.grade.name}",
			"subject":"${data.question.subject.name}",
			"content":"${data.question.content}",
			"addTime":"${data.question.addTimeStr}",
			"stopTime":"${data.question.stopTime}",
			"stopTimeStr":"${data.question.restTimeStr}",
			"sourceType":"${data.question.sourceType}",
			"sourceTypeStr":"${data.question.sourceTypeStr}",
			"studentNickName":"${data.question.student.nickName}",
			"studentImgPath":"${data.question.student.imgPath}"
		}
	</c:if>
</c:if>
}}
