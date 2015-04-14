<%@page import="com.up72.auth.model.*" %>
<%@page import="com.up72.auth.AuthUtils"%>
<%@page import="com.bruce.common.CommonUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String id = request.getParameter("id");
String returnUrl = request.getParameter("return_url");
String workGroupId = request.getParameter("workGroupId");
//产品才有的参数
String organizationId = request.getParameter("organizationId");
String AUTH_PERM_ID = AuthUtils.paramUtils.getParameter(request, "AUTH_PERM_ID", "");
request.setAttribute("AUTH_PERM_ID", AUTH_PERM_ID);
%>
<div id="Role_<%= id%>">            
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/admin/auth/role/<%=id %>/permission?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">权限分配</a></span>
			<!-- 
			<span rel="${ctx}/admin/auth/role/<%=id %>/permission?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">分配站点</a></span>
			 -->
			<span rel="${ctx}/admin/auth/role/<%=id %>/tabEdit?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">编辑</a></span>
			<span rel="${ctx}/admin/auth/role/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
		</div>
	</div>
<!-- tab end -->
</div>
 <script type="text/javascript">
	$("#Role_<%= id%>").find(".tabs_con span").up72Tabs(
		"#Role_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>