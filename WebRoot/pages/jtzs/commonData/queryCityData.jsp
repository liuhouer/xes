<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
"cityList":[
<c:if test="${null!=data && fn:length(data)>0}">
	<c:forEach items="${data}" var="item" varStatus="status">
		{"id":"${item.id}",
		"name":"${item.name}",
		"en":"${item.en}"}
		<c:if test="${!status.last}">,</c:if>
	</c:forEach>
</c:if>
]
}}
