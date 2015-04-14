<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String id = request.getParameter("id");
String productCode = request.getParameter("productCode");
String AUTH_PERM_ID = request.getParameter("AUTH_PERM_ID");
pageContext.setAttribute("AUTH_PERM_ID",AUTH_PERM_ID);
%>
<!-- <div id="Role_<%= id%>">           
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/admin/auth/role/<%=id %>/proPermission?productCode=<%= productCode%>&AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">权限分配</a></span>
			<span rel="${ctx}/admin/auth/role/<%=id %>/proTabEdit?productCode=<%= productCode%>&AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">编辑</a></span>
			<span rel="${ctx}/admin/auth/role/<%=id %>/proTabShow?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">基本信息</a></span>
		</div> 
	</div>
<!-- tab end -->
</div>-->
 <script type="text/javascript">
	$("#Role_<%= id%>").find(".tabs_con span").up72Tabs(
		"#Role_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>