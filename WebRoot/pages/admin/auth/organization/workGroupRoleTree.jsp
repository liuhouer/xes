<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>

<!--  
		<div id="menuCtrl">
			<a title="" href="#">关闭全部</a>
			<a title="" href="#">展开全部</a>
		</div>
		
		<ul id="menuPermtree" class="filetree">
		<c:forEach items="${organizationList}" var="organization" varStatus="pgSta">
			

			<c:if test="${fn:length(organization.roleList)>0}">
			<li class="expandable">
				<span class="<c:choose><c:when test="${null != organization.roleList && fn:length(organization.roleList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
					<input type="checkbox" value="${organization.id}" name="organizationIds" /><c:out value="${organization.name}" />
				</span>
				<ul>
				<c:forEach items="${organization.roleList}" var="role" varStatus="permSta">
					<li class="expandable">
						<span class="file">
							<input type="checkbox" value="${role.id}" name="roleIds" /><c:out value="${role.name}" />
						</span>
					</li>
				</c:forEach>
				</ul>
			</li>
			</c:if>
			

		</c:forEach>
		</ul>
		
<script type="text/javascript">
//菜单权限  menuPermBox  permMenuIds
$("#menuPermtree").treeview({
	control: "#menuCtrl"
});
</script> 
-->

<ul class="">
		<c:forEach items="${organization.roleList}" var="role" varStatus="permSta">
			<li class="">
				<span class="file">
					<input type="checkbox" value="${role.id}" name="roleIds" /><c:out value="${role.name}" />
				</span>
			</li>
		</c:forEach>
</ul>

