<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
	"score":"${score}",
	"useScore":"${useScore}",
	"leftScore":"${leftScore}",
	"scoreList":[
	<c:if test="${!empty logList}">
		<c:forEach items="${logList.result}" var="item" varStatus="status">
		    "${item.content}"
		    <c:if test="${!status.last}">,</c:if>
		</c:forEach>
	</c:if>
	],
	"isReload":"${isReload}",
	"startTime":"${logList.result[0].addTime}",
	"stopTime":"${logList.result[fn:length(logList.result)-1].addTime}"
}}
