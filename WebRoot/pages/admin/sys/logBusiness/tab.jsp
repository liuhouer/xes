<%@page import="com.up72.sys.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>

<%
String id = request.getParameter("id");
%>
<div id="TabDemo_<%= id%>" class="infoContent" style="visibility:visible; opacity:1;">
    <div class="up72_tabs skin_tabs">      
        <!-- tab start -->
        <div class="up72_tabs_con"> 
            <span rel="${ctx}/admin/sys/logBusiness/<%=id %>/tabShow"><a href="javascript:;">基本信息</a></span>
            <span rel="${ctx}/admin/sys/logBusiness/<%=id %>/tabEdit"><a href="javascript:;">编辑</a></span>
        </div>
        <!-- tab end -->
    </div>
</div>
<script type="text/javascript">
	$("#TabDemo_<%= id%>").find(".up72_tabs_con span").up72Tabs(
		"#TabDemo_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>