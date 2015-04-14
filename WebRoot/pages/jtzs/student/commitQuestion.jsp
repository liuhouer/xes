<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
<c:if test="${!empty data}">
	"id":"${data.id}"		
</c:if>
}}
