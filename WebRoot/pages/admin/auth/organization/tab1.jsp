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
%>

<!-- 当前位置 -->
<div class="head_content">
	<div class="navBar"  style="display: none;">  » 
	    <a class=""  href="${ctx}/admin/auth/organization" ><%=Organization.TABLE_ALIAS%>列表</a>  » 
		<a class=""  href="${ctx}/pages/admin/auth/organization/tab.jsp?id=<%=id %>"><%=Organization.TABLE_ALIAS%>管理</a> 
	</div>
</div>
<!-- END 当前位置 -->
<div id="Organization_<%= id%>">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/admin/auth/organization/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
			<span rel="${ctx}/admin/auth/organization/<%=id %>/tabEdit"><a href="javascript:;">编辑</a></span>
			<span rel="${ctx}/admin/auth/organization/<%=id %>/tabWorkGroup"><a href="javascript:;">部门</a></span>
		</div>
	</div>
<!-- tab end -->
</div>
 <script type="text/javascript">
	$("#Organization_<%= id%>").find(".tabs_con span").up72Tabs(
		"#Organization_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>
</up72:override>
<%@ include file="base.jsp" %>