<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<c:forEach items = "${pagerResourceList}" var= "resource" varStatus="resourceStatus">
   <c:if test="${resourceStatus.count==1}">
    <div class="wap_center spanh5">
    	<div class="spanv5">
            <div class="wap_title"><a href="javascript:toDetail(${resource.id});">${resource.title }</a></div>
            <div class="wap_time"><fmt:formatDate value="${resource.addTimeDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></div>
            <div class="wap_detail"><c:choose><c:when test="${fn:length(resource.noHtmlContent) > 21}">${fn:substring(resource.noHtmlContent, 0, 21)}...</c:when><c:otherwise>${resource.noHtmlContent}</c:otherwise></c:choose></div>
            <div class="up_img"><a href="javascript:toDetail(${resource.id});"><img src="${ctx}${resource.imgPath}"/></a></div>
        </div>
    </c:if>    
     <c:if test="${resourceStatus.count==2}">
        <div class="spanv5 wap_none">
        	<div class="up_img"><a href="javascript:toDetail(${resource.id});"><img src="${ctx}${resource.imgPath}"/></a></div>
            <div class="wap_title"><a href="javascript:toDetail(${resource.id});">${resource.title }</a></div>
            <div class="wap_time"><fmt:formatDate value="${resource.addTimeDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></div>
            <div class="wap_detail"><c:choose><c:when test="${fn:length(resource.noHtmlContent) > 21}">${fn:substring(resource.noHtmlContent, 0, 21)}...</c:when><c:otherwise>${resource.noHtmlContent}</c:otherwise></c:choose></div>
        </div>
    </div>
    <div class="wap_lines"></div>
    </c:if> 
    <c:if test="${resourceStatus.count==3}">
	  <div class="wap_center spanh5">
    	<div class="spanv3">
        	<div class="up_img"><a href="javascript:toDetail(${resource.id});"><img src="${ctx}${resource.imgPath}"/></a></div>
            <div class="wap_title"><a href="javascript:toDetail(${resource.id});">${resource.title }</a></div>
            <div class="wap_time"><fmt:formatDate value="${resource.addTimeDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></div>
            <div class="wap_detail"><c:choose><c:when test="${fn:length(resource.noHtmlContent) > 13}">${fn:substring(resource.noHtmlContent, 0, 13)}...</c:when><c:otherwise>${resource.noHtmlContent}</c:otherwise></c:choose></div>
       </div>
    </c:if>     
    <c:if test="${resourceStatus.count==4}">
        <div class="spanv3">
            <div class="wap_title"><a href="javascript:toDetail(${resource.id});">${resource.title }</a></div>
            <div class="wap_time"><fmt:formatDate value="${resource.addTimeDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></div>
            <div class="wap_detail"><c:choose><c:when test="${fn:length(resource.noHtmlContent) > 13}">${fn:substring(resource.noHtmlContent, 0, 13)}...</c:when><c:otherwise>${resource.noHtmlContent}</c:otherwise></c:choose></div>
            <div class="up_img"><a href="javascript:toDetail(${resource.id});"><img src="${ctx}${resource.imgPath}"/></a></div>
        </div>
    </c:if>
    <c:if test="${resourceStatus.count==5}">    
        <div class="spanv3">
        	<div class="up_img"><a href="javascript:toDetail(${resource.id});"><img src="${ctx}${resource.imgPath}"/></a></div>
            <div class="wap_title"><a href="javascript:toDetail(${resource.id});">${resource.title }</a></div>
            <div class="wap_time"><fmt:formatDate value="${resource.addTimeDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></div>
            <div class="wap_detail"><c:choose><c:when test="${fn:length(resource.noHtmlContent) > 13}">${fn:substring(resource.noHtmlContent, 0,13)}...</c:when><c:otherwise>${resource.noHtmlContent}</c:otherwise></c:choose></div>
        </div>
    </div>
 		</c:if>
   </c:forEach> 