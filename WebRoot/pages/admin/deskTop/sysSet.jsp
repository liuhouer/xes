<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
</up72:override>

<up72:override name="content">
	<div class="up72_dashboard">
	    <div class="dashboard_info">
	    	<c:forEach items="${permissionMap}" var="item"> 
	        <div class="promptsnews_tit"><c:choose><c:when test="${(item.key == null) || ('' eq item.key)}">基本管理</c:when>
	        	<c:otherwise>${item.key}</c:otherwise>
	        </c:choose>
	        </div>
	        <div class="promptsnews_con">
				<div class="up72_quickbtns">
					<ul>
						<c:forEach items="${item.value}" var="permission">
						<c:choose>
							<c:when test="${(permission.imgPath == null) || ('' eq permission.imgPath)}"><c:set var="imgPath" value="${ctx}/styles/system/skins/mac/images/icon_default.png"/></c:when>
	        				<c:otherwise><c:set var="imgPath" value="${permission.imgPath}"/></c:otherwise>
	        			</c:choose>
				        <li><a href="${ctx}/admin/sysCofig/${permission.id}?url=${permission.url}"  title="${permission.name}"><span class="quickbtns_icon"><img src="${ctx}${imgPath}" /></span><span class="quickbtns_title">${permission.name}</span></a></li>
				        </c:forEach>
				    </ul>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</up72:override>
<jsp:include page="/admin/adminBase" />