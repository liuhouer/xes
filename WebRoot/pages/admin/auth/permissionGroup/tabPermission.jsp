<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<form id="admin_auth_permission_page_form" name="admin_auth_permission_page_form">
  	<table id="gridTable2">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="name" sortColumn="NAME" width="150"><%=Permission.ALIAS_NAME%></th>
				<th showColumn="url" sortColumn="URL" width="150"><%=Permission.ALIAS_URL%></th>
				<th showColumn="type" sortColumn="TYPE" width="150"><%=Permission.ALIAS_TYPE%></th>
				<th showColumn="enabled" sortColumn="ENABLED" width="150"><%=Permission.ALIAS_ENABLED%></th>
			</tr>
		</thead>
		<tbody>
	  	<c:forEach items="${permissionGroup.permissionList}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index+1}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/permission/${item.id}/edit','<%=Permission.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="url"><c:out value='${item.url}'/>&nbsp;</td>
				<td showColumn="type"><c:choose><c:when test="${item.type == 1}">菜单</c:when><c:when test="${item.type == 2}">操作</c:when><c:when test="${item.type == 3}">选项卡</c:when></c:choose>&nbsp;</td>
				<td showColumn="enabled"><c:choose><c:when test="${item.enabled == 1}">启用</c:when><c:when test="${item.enabled == 0}">禁用</c:when></c:choose>&nbsp;</td>
		</c:forEach>
 	 	</tbody>
	</table>
</form>
<script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	/*
		// 排序处理
		$.sortcolumn({
			form : "#admin_auth_role_page_form",
			data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
			columns : $("#gridTable th[sortColumn]"),
			sortColumns : "${pageRequest.sortColumns}"
		});
	*/
	$("#gridTable2").rowAction({	
		url : "${ctx}/pages/admin/auth/permission/tab.jsp",
		except : ["checkbox","index","option"],
		"pop" : true,
		poptitle : "权限编辑",
	});
	// 表格列表处理
	$('#gridTable2').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/permission/new?productCode=${permissionGroup.product.id}&permissionGroupCode=${permissionGroup.id}','<%=Permission.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function (){confirm("确认要删除选定记录吗？", function(){doRestEdit({url:'${ctx}/admin/auth/permission',batchBox:'items',boxCon:'#admin_auth_permission_page_form',form:'#admin_auth_permission_page_form',method:'delete'});});}}
		]
	});
</script>