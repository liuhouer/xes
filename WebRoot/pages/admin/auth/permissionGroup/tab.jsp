<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<up72:override name="head">
<title><%=PermissionGroup.TABLE_ALIAS%> 维护</title>
	
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
	
	<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>
</up72:override>

<up72:override name="content">

<%
String id = request.getParameter("id");
String productCode = request.getParameter("productCode");
%>
	<div class="head_content">
   		<div class="navBar" style="display: none;">  »  <a class="" href="${ctx}/admin/auth/product" ><%=Product.TABLE_ALIAS%>列表</a> »
			<a class="" href="${ctx}/pages/admin/auth/product/tab.jsp?id=<%= productCode%>" ><%=Product.TABLE_ALIAS%>管理</a> »
			<a class="" href="${ctx}/pages/admin/auth/permissionGroup/tab.jsp?id=<%= id%>&productCode=<%= productCode%>" ><%=Product.TABLE_ALIAS%>权限组管理</a>
		</div>
  	</div>
	
<div id="permissionGroupTab_<%= id%>">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/admin/auth/permissionGroup/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
			<span rel="${ctx}/admin/auth/permissionGroup/<%=id %>/tabEdit?permissionGroupCode=<%= id%>&productCode=<%= productCode%>"><a href="javascript:;">编辑</a></span>
			<span rel="${ctx}/admin/auth/permissionGroup/<%=id %>/tabPermission?permissionGroupCode=<%= id%>&productCode=<%= productCode%>"><a href="javascript:;">权限</a></span>
		</div>
	</div>
<!-- tab end -->
</div>
 <script type="text/javascript">
	$("#permissionGroupTab_<%= id%>").find(".tabs_con span").up72Tabs(
		"#permissionGroupTab_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>
</up72:override>
<%@ include file="base.jsp" %>