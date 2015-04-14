<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String file = request.getParameter("file");
long id = System.currentTimeMillis();
%>
<div id="file_<%= id%>">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/pages/admin/tools/file/tabZoom.jsp?file=<%= file%>"><a href="javascript:;">缩放</a></span>
			<span rel="${ctx}/pages/admin/tools/file/tabCut.jsp?file=<%= file%>"><a href="javascript:;">裁剪</a></span>
			<span rel="${ctx}/pages/admin/tools/file/tabWater.jsp?file=<%= file%>"><a href="javascript:;">水印</a></span>
		</div>
	</div>
<!-- tab end -->
</div>
 <script type="text/javascript">
	$("#file_<%= id%>").find(".tabs_con span").up72Tabs(
		"#file_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>