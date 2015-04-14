<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en"><head>
<meta charset="utf-8">
<title>iphone</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="author">
<link rel="stylesheet" href="${ctx}/pages/jtzs/css/style.css">
<!-- 
<link rel="stylesheet" href="${ctx}/styles/qmt/css/preview/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/pages/admin/preview/frame/css/style.css" type="text/css" />
 -->
<link rel="stylesheet" href="${ctx}/pages/admin/preview/compose/css/style.css" type="text/css">
</head>
<body>
<div class="container">
<div class="center" style="background:none;">
	<div class="main">
	<div class="con">
		<div class="eventstime">
	        <div class="w100 oblique f12 right bold">&nbsp;本期活动时间：<span class="ff00 f125"><fmt:formatDate value="${event.startTimeDate}" pattern="yyyy-MM-dd"/> ~ <fmt:formatDate value="${event.endTimeDate}" pattern="yyyy-MM-dd"/></span></div>
	        <p class="w100 f666 f125 m2"><c:out value="${event.title }"/></p>
	        <p class="m2"><img src="${ctx}/pages/jtzs/images/img.jpg" onerror="this.src='${ctx}/pages/jtzs/images/img.jpg'"></p>
	    	<ul>
	        	<c:if test="${event.question1 ne '' }"><li><input class="checkbox" name="question" id="question1" type="checkbox"  onchange="setIdvalue(this)"  value="1"><label for="question1"><span>A:<c:out value="${event.question1 }"/></span></label></li></c:if>
	            <c:if test="${event.question2 ne '' }"><li><input class="checkbox" name="question" id="question2" type="checkbox"  onchange="setIdvalue(this)"  value="2"><label for="question2"><span>B:<c:out value="${event.question2 }"/></span></label></li></c:if>
	            <c:if test="${event.question3 ne '' }"><li><input class="checkbox" name="question" id="question3" type="checkbox"  onchange="setIdvalue(this)"  value="3"><label for="question3"><span>C:<c:out value="${event.question3 }"/></span></label></li></c:if>
	            <c:if test="${event.question4 ne '' }"><li><input class="checkbox" name="question" id="question4" type="checkbox"  onchange="setIdvalue(this)"  value="4"><label for="question4"><span>D:<c:out value="${event.question4 }"/></span></label></li></c:if>
	            <c:if test="${event.question5 ne '' }"><li><input class="checkbox" name="question" id="question5" type="checkbox"  onchange="setIdvalue(this)"  value="5"><label for="question5"><span>E:<c:out value="${event.question5 }"/></span></label></li></c:if>
	            <c:if test="${event.question6 ne '' }"><li><input class="checkbox" name="question" id="question6" type="checkbox"  onchange="setIdvalue(this)"  value="6"><label for="question6"><span>F:<c:out value="${event.question6 }"/></span></label></li></c:if>
	            <c:if test="${event.question7 ne '' }"><li><input class="checkbox" name="question" id="question7" type="checkbox"  onchange="setIdvalue(this)"  value="7"><label for="question7"><span>G:<c:out value="${event.question7 }"/></span></label></li></c:if>
	        </ul>
	        <div class="neir"><input name="idValue"  id="" class="submit_bg f125 pointer" type="button" onclick="" value="提交答案">
	        </div>
	    </div> 
	</div>
	
	</div>
</div>
</div>
</body>
</html>