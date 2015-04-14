<%@page import="com.bruce.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String id = request.getParameter("id");
%>
            <div id="TabDemo_<%= id%>" class="infoContent" style="visibility:visible; opacity:1;">
              
			  <!-- tab start -->
              <div class="handles">
				  <span class="t_handle" rel="${ctx}/admin/lucene/searchHistory/<%=id %>/tabShow">基本信息</span>&nbsp;
				  <span class="t_handle" rel="${ctx}/admin/lucene/searchHistory/<%=id %>/tabEdit">编辑</span>
			  </div>
              <!-- tab end -->
 </div>
 <script type="text/javascript">
	$("#TabDemo_<%= id%>").find(".handles span").up72Tabs(
		"#TabDemo_<%= id%>",
		{
			selCss : "t_handle_current",
			showCss : "tabs"
		}
	);
</script>