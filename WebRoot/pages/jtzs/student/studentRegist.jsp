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
		"sex":"${data.student.sex}",
		"sexStr":"${data.student.sexStr}",
		"grade":"${data.student.grade.name}",
		"gradeId":"${data.student.gradeId}",
		"school":"${data.student.school.name}",
		"schoolId":"${data.student.schoolId}",
		"province":"${data.student.province.name}",
		"provinceId":"${data.student.provinceId}"
	}
	</c:if>
</c:if>
}}
