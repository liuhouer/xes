<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
 <c:forEach items="${permissionTreeMap}" var="item" varStatus="status">
 	<c:forEach items="${item.value.permissionList}" var="permission" >
 	<li><a href="${ctx}${permission.url}" class="" title="${permission.name}" >
 	<span class="nav_ico${status.index}"></span>
 	${permission.name}[${item.key}]</a></li>
    </c:forEach>
 </c:forEach> 
