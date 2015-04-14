<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=Role.TABLE_ALIAS%> 维护</title>
	
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_auth_role_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
	<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
 	<div class="navBar" style="display: none">  » <a class="" href="${ctx}/admin/auth/role" ><%=Role.TABLE_ALIAS%>设置</a></div>
</div>
<!-- END  当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_auth_role_search_form" name="admin_auth_role_search_form" action="${ctx}/admin/auth/role">
  <input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERMISSION.id}" />
	<div class="search_con">
		<div class="search_txt"><%=Role.ALIAS_NAME%>：<input type="text" id="name" name="name" class="input_txt" value="${query.name}"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询-->

<form id="admin_auth_role_page_form" name="admin_auth_role_page_form">
	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th sortColumn="NAME" showColumn="name" width="150" ><%=Role.ALIAS_NAME%></th>
				<th sortColumn="ORGANIZATION_ID" width="100" showColumn="organizationId" ><%=Role.ALIAS_ORGANIZATION_ID%></th>
				<th sortColumn="WORK_GROUP_ID" width="100" showColumn="workGroupId" ><%=Role.ALIAS_WORK_GROUP_ID%></th>
				<th sortColumn="ENABLED" width="50" ><%=Role.ALIAS_ENABLED%></th>
				<th sortColumn="DESCRIPTION" showColumn="description" width="400" ><%=Role.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/auth/role/tab.jsp?id=${item.id}">
		  		<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            	<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/role/${item.id}/edit?AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Role.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="organizationId"><c:out value='${item.organization.name}'/>&nbsp;</td>
				<td showColumn="workGroupId"><c:out value='${item.workGroup.name}'/>&nbsp;</td>
				<td showColumn="enabled">
				<c:choose>
				<c:when test="${item.enabled == 1}"><a href="javascript:;" onclick="changeState(${item.id},1)">启用</a></c:when>
				<c:when test="${item.enabled == 0}"><a href="javascript:;" onclick="changeState(${item.id},0)">禁用</a></c:when>
				</c:choose>
				&nbsp;</td>
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
		form : "#admin_auth_role_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	$("#gridTable").rowAction({	
		url : "${ctx}/pages/admin/auth/role/tab.jsp?AUTH_PERM_ID=${AUTH_PERM_ID}",
		except : ["checkbox","index","option","enabled"],
		"pop" : true,
		poptitle : "角色管理"
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/role/new','<%=Role.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function(){confirm("确认要删除选定记录吗？", function(){doRestEdit({confirmMsg:'您所选择删除的栏目为一级栏目，删除此栏目会将其下所有子栏目一并删除，是否继续？',url:'${ctx}/admin/auth/role',batchBox:'items',boxCon:'#admin_auth_role_page_form',form:'#admin_auth_role_page_form',method:'delete'})});}}
		]
	});
	
	function changeState(id,state){
		var flag ="null";
		if(state == "1"){
			flag = "禁用";
		}
		if(state == "0"){
			flag = "启用";
		}
		confirm("确定"+flag+"该角色？",function(){
		 	$.ajax({
			"url" : "${ctx}/admin/auth/role/changeState?id="+id+"&state="+state,
			"type" : "post",
			"dataType" : "json",
			"success" : function (data){
				if(data.message=="success"){
					alert("已"+flag+"！",3,function(){
 					window.location.reload();
			  		});
  				}
  				if(data.message=="error"){
  				   alert("操作失败",3,function(){
  					window.location.reload();
				  });
  				}
			}
		});
	  });
	}
</script>
</up72:override>
<%@ include file="base.jsp" %>