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
<c:if test = "${type eq 'detail'}">
<link rel="stylesheet" href="${ctx}/styles/qmt/css/preview/style.css" type="text/css" />
</c:if>
<link rel="stylesheet" href="${ctx}/pages/admin/qmt/preview/frame/css/style.css">

</head>
<body>
<div class="container">
<div class="center" style="background:none;">
	
	<c:choose>
<c:when test = "${type eq 'detail'}">
	<div class="wrap">
            <div class="slip"></div>
            <div class="header">
            <div class="icon"></div>
            <div class="channelTitle">${channel.title }</div>
            <div class="time">${time }</div>
        </div>
            
        <div class="title">
            <h1>${title }</h1>
            </div>
            <div id="device_content" style="overflow-y: scroll;height:330px;width:100%;border-top: solid 2px #dad8d6; margin-bottom:1%;float:left;">
				 		${content }"
        </div>
</c:when>
<c:otherwise>
<jsp:include page="${ctx}/admin/qmt/preview?id=${id}&type=${type }&channelId=${channelId }"></jsp:include>
</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>