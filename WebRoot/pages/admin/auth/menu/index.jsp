<%@page import="com.up72.auth.model.*" %>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=Menu.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_auth_menu_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">

<!--当前位置-->
  <div class="head_content">
   <div class="navBar" style="display: none;"> » <a class="" href="${ctx}/admin/auth/menu/"><%=Menu.TABLE_ALIAS%>管理</a> </div>
  </div>
<!--end当前位置--> 
<!--查询-->
    <div class="up72_search">
	<form id="admin_auth_menu_search_form" name="admin_auth_menu_search_form" method="get">
		<div class="search_con">
				<div class="search_txt"><%=Menu.ALIAS_NAME%>：<input type="text" id="name" name="name" class="input_txt" value="${query.name}"></div>
			<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
		</div>
	</form>
	</div>
<!--end查询-->

<form id="admin_auth_menu_page_form" name="admin_auth_menu_page_form" method="get">
	<table id="admin_auth_menu_table">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th sortColumn="PARENT_ID" showColumn="parentId" width="50"><%=Menu.ALIAS_PARENT_ID%></th>
				<th sortColumn="PERMISSION_ID" showColumn="permissionId" width="50"><%=Menu.ALIAS_PERMISSION_ID%></th>
				<th sortColumn="NAME" showColumn="name" width="50"><%=Menu.ALIAS_NAME%></th>
				<th sortColumn="ICON" showColumn="icon" width="50"><%=Menu.ALIAS_ICON%></th>
				<th sortColumn="SORT_ID" showColumn="sortId" width="50"><%=Menu.ALIAS_SORT_ID%></th>
				<th sortColumn="LEVEL" showColumn="level" width="50"><%=Menu.ALIAS_LEVEL%></th>
				<th sortColumn="ROLE_ID" showColumn="roleId" width="50"><%=Menu.ALIAS_ROLE_ID%></th>
				<th sortColumn="ADD_TIME" showColumn="addTime" width="50"><%=Menu.ALIAS_ADD_TIME%></th>
			</tr>
		</thead>
		<tbody>
	<c:forEach items="${page.result}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/auth/menu/tab.jsp?id=${item.id}">
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/menu/${item.id}/edit','<%=Menu.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="parentId"><c:out value='${item.parentId}'/>&nbsp;					</td>
				<td showColumn="permissionId"><c:out value='${item.permissionId}'/>&nbsp;					</td>
				<td showColumn="name"><c:out value='${item.name}'/>&nbsp;					</td>
				<td showColumn="icon"><c:out value='${item.icon}'/>&nbsp;					</td>
				<td showColumn="sortId"><c:out value='${item.sortId}'/>&nbsp;					</td>
				<td showColumn="level"><c:out value='${item.level}'/>&nbsp;					</td>
				<td showColumn="roleId"><c:out value='${item.roleId}'/>&nbsp;					</td>
				<td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:ss"/>&nbsp;					</td>
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
		form : "#admin_auth_menu_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_auth_menu_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	$("#admin_auth_menu_table").rowAction({	
		"url" : "/pages/admin/auth/menu/tab.jsp",
		"except" : ["checkbox","index","option"],
		"pop" : true,
		"poptitle" : "<%=Menu.TABLE_ALIAS%>标签",
		"popw" : 600
	});
	// 表格列表处理
	$('#admin_auth_menu_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/auth/menu/new","<%=Menu.TABLE_ALIAS%>添加",600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/auth/menu/',batchBox:'items',boxCon:'#admin_auth_menu_page_form',form:'#admin_auth_menu_page_form',method:'delete'})}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>