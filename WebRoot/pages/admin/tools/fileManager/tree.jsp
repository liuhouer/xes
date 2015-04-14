<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=CrawlerResource.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	 
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.js"></script>
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.async.js"></script>
  <link type="text/css" href="${ctx}/scripts/jq_tree/jquery.treeview.css" rel="stylesheet" />
  <script type="text/javascript">
  	function target2Resource(catId){
		window.resource_frame.target="";
	}
  </script>
</up72:override>

<up72:override name="content">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="up72_treeperm">
  <tr>
    <td class="up72_filetree"><div class="filetree_scr"><div class="filetree"><ul id="category_tree"></ul></div></div></td>
    <td><iframe style="border:none; height:640px; width:100%;" scrolling="no" frameborder="0" id="resource_frame" name="resource_frame" src="${ctx}/admin/tools/fileManager/"></iframe></td>
  </tr>
</table>
<script type="text/javascript">
$("#category_tree").treeview({
	url: "${ctx}/admin/tools/fileManager/catTree"
});
</script>
</up72:override>
<%@ include file="base.jsp" %>