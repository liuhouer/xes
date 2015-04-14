<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<up72:override name="head">
<title><%=Organization.TABLE_ALIAS%> 维护</title>
	
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
String organizationId = request.getParameter("organizationId");
%>
	<div class="head_content">
   		<div class="navBar" style="display: none;">  » <a class="" href="${ctx}/admin/auth/organization" ><%=Organization.TABLE_ALIAS%>列表</a> » 
			<a class="" href="${ctx}/pages/admin/auth/organization/tab.jsp?id=<%= organizationId%>" ><%=Organization.TABLE_ALIAS%>管理</a> »
			<a class="" href="${ctx}/pages/admin/auth/workGroup/tab.jsp?id=<%=id %>&organizationId=<%= organizationId%>" ><%=WorkGroup.TABLE_ALIAS%>管理</a> 
		</div>
  	</div>
	
<div id="WorkGroup_<%= id%>">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/admin/auth/workGroup/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
			<span rel="${ctx}/admin/auth/workGroup/<%=id %>/tabEdit?organizationId=<%= organizationId%>"><a href="javascript:;">编辑</a></span>
			<span rel="${ctx}/admin/auth/workGroup/<%=id %>/tagRole?organizationId=<%= organizationId%>"><a href="javascript:;">角色</a></span>
		</div>
	</div>
<!-- tab end -->
</div>
 <script type="text/javascript">
	$("#WorkGroup_<%= id%>").find(".tabs_con span").up72Tabs(
		"#WorkGroup_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>
</up72:override>
<%@ include file="base.jsp" %>