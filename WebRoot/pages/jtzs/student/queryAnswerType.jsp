<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
{"rlt":"${rlt}","msg":"${msg}","code":"${code}","data":{
<c:if test="${null!=data && fn:length(data)>0}">
	"answerTypeList":[
	<c:forEach items="${data}" var="item" varStatus="status">
		{
		"id":"${item.id}",
		"name":<c:if test="${item.ruleType==5}">"${item.minute}分钟(扣除${item.score}积分)"</c:if><c:if test="${item.ruleType==6}">"专家解答(扣除${item.score}积分)"</c:if>,
		"isDefault":<c:if test="${item.isDefault==1}">"true"</c:if><c:if test="${item.isDefault!=1}">"false"</c:if>
		}
		<c:if test="${!status.last}">,</c:if>
	</c:forEach>
	]
</c:if>
}}
