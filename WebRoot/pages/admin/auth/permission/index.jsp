<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=Permission.TABLE_ALIAS%>维护</title>
<script src="${ctx}/scripts/rest.js" ></script>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('admin_auth_permission_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</up72:override>
<up72:override name="content"> 
  <!--当前位置-->
  <div class="head_content">
    <div class="navBar" style="display:none;"> » <a class="" href="${ctx}/admin/auth/permission"><%=Permission.TABLE_ALIAS%>管理</a> </div>
  </div>
  <!--end当前位置--> 
  <!--查询-->
  <div class="up72_search">
    <form method="get" id="admin_auth_member_search_form" name="admin_auth_member_search_form" action="${ctx}/admin/auth/member/user">
      <input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERMISSION.id}" />
      <div class="search_con">
        <div class="search_txt">
          <select id="searchColumnSel" name="searchColumnSel"  onchange="syncValue(this,'#searchColumn')">
            <option <c:if test="${'permissionGroupCode' eq query.searchColumn}"> selected</c:if> value="permissionGroupCode"><%=Permission.ALIAS_PERMISSION_GROUP_ID%></option>
            <option <c:if test="${'name' eq query.searchColumn}"> selected</c:if> value="name"><%=Permission.ALIAS_NAME%></option>
            <option <c:if test="${'description' eq query.searchColumn}"> selected</c:if> value="description"><%=Permission.ALIAS_DESCRIPTION%></option>
            <option <c:if test="${'url' eq query.searchColumn}"> selected</c:if> value="url"><%=Permission.ALIAS_URL%></option>
            <option <c:if test="${'enabled' eq query.searchColumn}"> selected</c:if> value="enabled"><%=Permission.ALIAS_ENABLED%></option>
            <option <c:if test="${'sort' eq query.searchColumn}"> selected</c:if> value="sort"><%=Permission.ALIAS_SORT%></option>
            <option <c:if test="${'type' eq query.searchColumn}"> selected</c:if> value="type"><%=Permission.ALIAS_TYPE%></option>
          </select>
        </div>
        <div class="search_txt">
          <input id="searchColumnInputCreate" name="searchColumnSelValue" value="${query.searchColumnValue}" onkeyup="syncValue(this,'#searchColumnValue')" class="input_txt">
        </div>
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit" onclick="getReferenceForm(this).action='${ctx}/admin/auth/permission'" class="button" value="查询"><span>查询</span></button>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!--end查询-->
  
<form id="admin_auth_permission_page_form" name="admin_auth_permission_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="name" sortColumn="NAME" width="100"><%=Permission.ALIAS_NAME%></th>
				<th showColumn="url" sortColumn="URL" width="260"><%=Permission.ALIAS_URL%></th>
				<th showColumn="permissionGroupCode" sortColumn="PERMISSION_GROUP_ID"  width="80"><%=Permission.ALIAS_PERMISSION_GROUP_ID%></th>
				<th showColumn="type" sortColumn="TYPE" width="60"><%=Permission.ALIAS_TYPE%></th>
				<th showColumn="enabled" sortColumn="ENABLED" width="50"><%=Permission.ALIAS_ENABLED%></th>
				<th showColumn="sort" sortColumn="SORT" width="60"><%=Permission.ALIAS_SORT%></th>
				<th showColumn="description" sortColumn="DESCRIPTION" width="120"><%=Permission.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;" onclick="show('${ctx}/admin/auth/permission/${item.id}/edit','<%=Permission.TABLE_ALIAS%>',600, 500)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="url"><c:out value='${item.url}'/>&nbsp;</td>
				<td showColumn="permissionGroup"><c:out value='${item.permissionGroup.name}'/>&nbsp;</td>
				<td showColumn="type"><c:choose>
                  <c:when test="${item.type == 1}">菜单</c:when>
                  <c:when test="${item.type == 2}">操作</c:when>
                  <c:when test="${item.type == 3}">选项卡</c:when>
                </c:choose>&nbsp;</td>
				<td showColumn="enabled"><c:choose>
                  <c:when test="${item.enabled == 0}">未启用</c:when>
                  <c:when test="${item.enabled == 1}">启用</c:when>
                </c:choose>&nbsp;</td>
				<td showColumn="sort">&nbsp;</td>
                <td showColumn="description"><c:out value='${item.description}'/>&nbsp;</td>
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
		form : "#admin_auth_permission_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}&AUTH_PERM_ID=${AUTH_PERMISSION.id}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	$("#gridTable").rowAction({	
		url : "${ctx}/admin/auth/permission/tab",
		except : ["checkbox","index","option"],
		"pop" : true,
		poptitle : "权限管理",
		popw : 800
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: '450',
		striped : true,
		buttons : [
			{name: '导出', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/permission/new','<%=Permission.TABLE_ALIAS%>添加',600)}},
			{name: '导入', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录到回收站吗？',url:'${ctx}/admin/auth/permission',batchBox:'items',boxCon:'#admin_auth_permission_page_form',form:'#admin_auth_permission_page_form',method:'delete'})}},
		]
	});
	
</script>
</up72:override>
<%@ include file="base.jsp" %>
