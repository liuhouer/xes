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

<form id="admin_auth_role_page_form" name="admin_auth_role_page_form">
<input type="hidden" name="return_url" id="return_url" value="redirect:/pages/admin/auth/workGroup/tab.jsp?id=${workGroup.id}&organizationId=${organizationId}" />
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="name" sortColumn="NAME" width="180"><%=Role.ALIAS_NAME%></th>
				<th showColumn="organizationId" sortColumn="ORGANIZATION_ID" width="100"><%=Role.ALIAS_ORGANIZATION_ID%></th>
				<th showColumn="workGroupId" sortColumn="WORK_GROUP_ID" width="100"><%=Role.ALIAS_WORK_GROUP_ID%></th>
				<th showColumn="enabled" sortColumn="ENABLED" width="80"><%=Role.ALIAS_ENABLED%></th>
			</tr>
		</thead>
		<tbody>
	  	<c:forEach items="${roleList}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index+1}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/role/${item.id}/edit?workGroupId=${workGroup.id}&organizationId=${organizationId}','<%=Role.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="organizationId"><c:out value='${item.organization.name}'/>&nbsp;</td>
				<td showColumn="workGroupId"><c:out value='${item.workGroup.name}'/>&nbsp;</td>
				<td showColumn="enabled"><c:choose><c:when test="${item.enabled == 1}">启用</c:when><c:when test="${item.enabled == 0}">禁用</c:when>	</c:choose>&nbsp;</td>
			</tr>
		</c:forEach>
		<c:forEach items="${workGroup.productList}" var="product" varStatus="status">
			<c:forEach items="${product.roleList}" var="item" varStatus="status">
				<tr>
					<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"  disabled="disabled"></td>
					<td showColumn="index">${page.thisPageFirstElementNumber + status.index+1}</td>
					<td showColumn="option">&nbsp;</td>
					<td showColumn="name"><c:out value='${item.name}'/>&nbsp;</td>
					<td showColumn="organizationId"><c:out value='${item.organization.name}'/>&nbsp;</td>
					<td showColumn="workGroupId"><c:out value='${item.workGroup.name}'/>&nbsp;</td>
					<td showColumn="enabled"><c:choose><c:when test="${item.enabled == 1}">启用</c:when><c:when test="${item.enabled == 0}">禁用</c:when>	</c:choose>&nbsp;</td>
				</tr>
			</c:forEach>
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
	$("#gridTable").rowAction({	
		url : "${ctx}/pages/admin/auth/role/tab.jsp?workGroupId=${workGroup.id}&organizationId=${organizationId}",
		except : ["checkbox","index","option"],
		"pop" : true,
		poptitle : ""
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/role/new?workGroupId=${workGroup.id}&organizationId=${organizationId}','<%=Role.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function (){doRestBatchDelete('${ctx}/admin/auth/role','items',document.forms.admin_auth_role_page_form)}}
		]
	});
</script>