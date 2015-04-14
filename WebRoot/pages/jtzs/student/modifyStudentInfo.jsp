<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
<c:if test="${!empty student}">
	"student":{
		"id":"${student.id}",
		"nickName":"${student.nickName}",
		"provinceId":"${student.provinceId}",
		"province":"${student.province.name}",
		"cityId":"${student.cityId}",
		"city":"${student.city.name}",
		"areaId":"${student.areaId}",
		"area":"${student.area.name}",
		"schoolId":"${student.schoolId}",
		"school":"${student.school.name}",
		"gradeId":"${student.gradeId}",
		"grade":"${student.grade.name}",
		"division":"${student.grade.division}",
		"sex":"${student.sex}",
		"sexStr":"${student.sexStr}",
		"addTime":"${student.addTime}",
		"imgPath":"${student.imgPath}"
	}
</c:if>
}}