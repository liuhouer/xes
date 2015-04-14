<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>

<!--end查询-->
<form id="admin_auth_workGroup_page_form" name="admin_auth_workGroup_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="name" sortColumn="NAME" width="180"><%=WorkGroup.ALIAS_NAME%></th>
				<th showColumn="addTime" sortColumn="ADD_TIME" width="180"><%=WorkGroup.ALIAS_ADD_TIME%></th>
				<th showColumn="enabled" sortColumn="ENABLED" width="100"><%=WorkGroup.ALIAS_ENABLED%></th>
			</tr>
		</thead>
		<tbody>
	  	<c:forEach items="${workGroupList}" var="item" varStatus="status">
	  	<input type="hidden" name="return_url" id="return_url" value="redirect:/pages/admin/auth/organization/tab.jsp?id=${organization.id}" />
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/workGroup/${item.id}/edit?organizationId=${organization.id}','<%=WorkGroup.TABLE_ALIAS%>编辑',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="addTime"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.addTimeDate}" />&nbsp;</td>
				<td showColumn="enabled">
					<c:choose>
				 		<c:when test="${item.enabled == 1}">启用</c:when>
				 		<c:when test="${item.enabled == 0}">禁用</c:when>
				 	</c:choose>&nbsp;
				</td>
			</tr>
		</c:forEach>
 	 	</tbody>
	</table>
	<simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
</form>
<script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 排序处理
	$.sortcolumn({
		form : "#admin_auth_workGroup_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	$("#gridTable").rowAction({	
		url : "${ctx}/pages/admin/auth/workGroup/tab.jsp?organizationId=${organization.id}",
		except : ["checkbox","index","option"],
		"pop" : false,
		poptitle : "部门编辑"
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/workGroup/new?organizationId=${organization.id}','<%=WorkGroup.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'您所选择删除的栏目为一级栏目，删除此栏目会将其下所有子栏目一并删除，是否继续？',url:'${ctx}/admin/auth/workGroup',batchBox:'items',boxCon:'#admin_auth_workGroup_page_form',form:'#admin_auth_workGroup_page_form',method:'delete'})}}
		]
	});
</script>
