<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
 	<c:forEach items="${AUTH_PERMISSIONLIST}" var="perm" varStatus="status" >
    	<li>
	 	<a href="javascript:;" onclick="$.forward('${ctx}${perm.url}',${perm.id});" class="<c:if test="${AUTH_PERMISSION.id == perm.id}">navover</c:if>" title="${perm.name}" >
	 		<!-- 
	 		<span class="nav_ico${status.index}">
	 			<img height="14" src="<c:choose><c:when test="${null!=perm.imgPath && ''!=perm.imgPath}">${perm.imgPath}</c:when><c:otherwise>/images/xiao.gif</c:otherwise></c:choose>" onerror="$(this).attr('src','/images/xiao.gif')"  />
	 		</span> -->
	 		${perm.name}
	 	</a>
        </li>
    </c:forEach>