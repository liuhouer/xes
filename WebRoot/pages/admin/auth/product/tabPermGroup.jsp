<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<form id="admin_auth_permissionGroup_page_form" name="admin_auth_permissionGroup_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="name" sortColumn="NAME" width="180"><%=Product.ALIAS_NAME%></th>
				<th showColumn="description" sortColumn="DESCRIPTION" width="150"><%=Product.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${product.permissionGroupList}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${status.index+1}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/permissionGroup/${item.id}/edit?productCode=${product.id}','权限组编辑',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><img style="vertical-align: top;" height="15" src="${item.imgPath}" onerror="$(this).hide();" /><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="description"><c:out value='${item.description}'/>&nbsp;</td>
			</tr>
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
		form : "#admin_auth_permissionGroup_page_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	*/
	$("#gridTable").rowAction({	
		url : "${ctx}/pages/admin/auth/permissionGroup/tab.jsp",
		except : ["checkbox","index","option"],
		"pop" : false,
		poptitle : "权限组编辑"
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/permissionGroup/new?productCode=${product.id}','<%=Product.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function (){confirm("确认要删除选定记录吗", function(){doRestEdit({confirmMsg:'您所选择删除的栏目为一级栏目，删除此栏目会将其下所有子栏目一并删除，是否继续？',url:'${ctx}/admin/auth/permissionGroup',batchBox:'items',boxCon:'#admin_auth_permissionGroup_page_form',form:'#admin_auth_permissionGroup_page_form',method:'delete'}	);})}}
		]
	});
</script>