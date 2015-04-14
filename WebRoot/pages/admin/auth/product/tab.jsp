<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<up72:override name="head">
<title><%=Product.TABLE_ALIAS%> 维护</title>
	
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>

	<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>
	<script type="text/javascript">
		function uploadIconPath(){
			showCommonUpload({
		   		width : 450,
		   		height : 250,
		   		callBack : "window.parent.uploadIconPathCall(event, ID, fileObj, response, data)",
		   		fileExt : "*.jpg;*.gif;*.png"
	   		});
		}
		
		function uploadIconPathCall(event, ID, fileObj, response, data){
			var file = response.savePath;
			$("#imgPath").val(file);
			if(data.fileCount <= 0){
				window.parent.closeBox();
			}
		}
		
		function loadViewImg(){
			var imgPath = $("#imgPath").val();
			if(!isNull(imgPath)){
				$("#viewImg").attr("href",imgPath);
				$("#viewImg").show();
			}
		}
	</script>
</up72:override>

<up72:override name="content">

<%
String id = request.getParameter("id");
pageContext.setAttribute("id",id);
String AUTH_PERM_ID = request.getParameter("AUTH_PERM_ID");
pageContext.setAttribute("AUTH_PERM_ID",AUTH_PERM_ID);
%>
	<div class="head_content">
   		<div class="navBar" style="display: none;">  » <a class="" href="${ctx}/admin/auth/product" ><%=Product.TABLE_ALIAS%>列表</a> »
			<a class="" href="${ctx}/pages/admin/auth/product/tab.jsp?id=<%=id %>" ><%=Product.TABLE_ALIAS%>管理</a> 
		</div>
  	</div>
<div id="productTab_<%= id%>">
<!-- tab start -->
	<div class="up72_tabs">
		<div class="tabs_con"> 
			<span rel="${ctx}/admin/auth/product/<%=id %>/tabRole?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">角色</a></span> 
			<span rel="${ctx}/admin/auth/product/<%=id %>/tabEdit?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">编辑</a></span>
			<span rel="${ctx}/admin/auth/product/<%=id %>/tabShow?AUTH_PERM_ID=${AUTH_PERM_ID}"><a href="javascript:;">基本信息</a></span>
			<!-- 
			<span rel="${ctx}/admin/auth/product/<%=id %>/tabPermGroup"><a href="javascript:;">权限组</a></span>
			 -->
		</div>
	</div>
<!-- tab end -->
</div>
 <script type="text/javascript">
	$("#productTab_<%= id%>").find(".tabs_con span").up72Tabs(
		"#productTab_<%= id%>",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
	
</script>
</up72:override>
<%@ include file="base.jsp" %>