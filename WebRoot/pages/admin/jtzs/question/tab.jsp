<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>
<%
String id = request.getParameter("id");
%>
<div id="TabDemo_<%= id%>" class="infoContent">
  <!-- tab start -->
  <div class="up72_tabs">    
    <div class="tabs_con">
      <span rel="${ctx}/admin/jtzs/question/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
      <span rel="${ctx}/admin/jtzs/question/<%=id %>/tabEdit"><a href="javascript:;">编辑</a></span>
    </div>    
  </div>
  <!-- tab end --> 
</div>
<script type="text/javascript">
	$("#TabDemo_<%= id%>").find(".tabs_con span").up72Tabs(
		"#TabDemo_<%= id%>",
		{
			selCss : "uncur",
			showCss : "tabs"
		}
	);
</script>