<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String id = request.getParameter("id");
%>
<div id="TabDemo_<%= id%>" class="infoContent" style="visibility:visible; opacity:1;">
        <div class="up72_tabs">      
			  <!-- tab start -->
             <div class="tabs_con"> 
				  <span  rel="${ctx}/admin/auth/productAbout/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
				  <span  rel="${ctx}/admin/auth/productAbout/<%=id %>/tabEdit"><a href="javascript:;">编辑</a></span>
			  </div>
              <!-- tab end -->
       </div>
</div>
 <script type="text/javascript">
	$("#TabDemo_<%= id%>").find(".tabs_con span").up72Tabs(
		"#TabDemo_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>