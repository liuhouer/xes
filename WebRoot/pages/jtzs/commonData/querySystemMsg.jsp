<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
	"systemMsgList":[
	<c:if test="${!empty systemMsgList}">
		<c:forEach items="${systemMsgList.result}" var="item" varStatus="status">
		    {
		    	"content":"${item.content}",
		    	"questionId":"${item.questionId}",
		    	"imgPath":"${ctx}${item.addUser.imgPath}",
		    	"userName":"${item.addUser.nickName}",
		    	"addTimeStr":""
		    }
		    <c:if test="${!status.last}">,</c:if>
		</c:forEach>
	</c:if>
	],
	"isReload":"${isReload}",
	"startTime":"${systemMsgList.result[0].addTime}",
	"stopTime":"${systemMsgList.result[fn:length(systemMsgList.result)-1].addTime}"
}}
