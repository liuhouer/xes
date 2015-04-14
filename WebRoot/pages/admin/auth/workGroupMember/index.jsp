<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=WorkGroupMember.TABLE_ALIAS%> 维护</title>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('admin_auth_workGroupMember_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
	<div class="navBar"  style="display: none;">  » <a class="" href="${ctx}/admin/auth/workGroup" ><%=WorkGroup.TABLE_ALIAS%>管理</a>
   	 » <a class="" href="${ctx}/admin/auth/workGroupMember" ><%=WorkGroupMember.TABLE_ALIAS%>管理</a>
   	 </div>
</div>
<!-- END 当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_auth_workGroupMember_search_form" name="admin_auth_workGroupMember_search_form" action="${ctx}/admin/auth/workGroupMember">
	<div class="search_con">
		<div class="search_txt">部门：<select id="workGroupId" name="workGroupId">
	            <option value="">全部</option>
	            <c:forEach items="${workGroupList }" var="item">
	            	<option <c:if test="${item.id == query.workGroupId }">selected="selected"</c:if> value="${item.id }">${item.name }</option>
	            </c:forEach>
            </select>
        </div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询-->
<form id="admin_auth_workGroupMember_page_form" name="admin_auth_workGroupMember_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="150"><label>操作</label></th>
				<th showColumn="workGroupId" sortColumn="WORK_GROUP_ID"  width="150"><%=WorkGroupMember.ALIAS_WORK_GROUP_ID%></th>
				<th showColumn="memberId" sortColumn="MEMBER_ID"  width="150"><%=WorkGroupMember.ALIAS_MEMBER_ID%></th>
				<th showColumn="isManager" sortColumn="IS_MANAGER" width="150"><%=WorkGroupMember.ALIAS_IS_MANAGER%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option">
					<a href="javascript:;"  onclick="show('${ctx}/admin/auth/workGroupMember/${item.id}/edit','<%=WorkGroupMember.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>
					<c:if test="${item.isManager != 1 }"><a href="javascript:;"  onclick="changeIsManager(${item.id });" class="sysiconBtnNoIcon" id="isManagerButton${item.id }">设置为部门管理员</a></c:if>
    	        	<c:if test="${item.isManager == 1 }"><a href="javascript:;"  onclick="changeIsManager(${item.id });" class="sysiconBtnNoIcon" id="isManagerButton${item.id }">取消部门管理员</a></c:if>&nbsp;
				</td>
				<td showColumn="name"><c:out value='${item.workGroup.name}'/>&nbsp;</td>
				<td showColumn="nickName"><c:out value='${item.member.nickName}'/>&nbsp;</td>
				<td showColumn="isManager" id="isManagerDiv${item.id }"><c:out value='${item.isManager == 1 ? "是":"否"}'/></td>
			</tr>
		</c:forEach>
 	 	</tbody>
	</table>
	<simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
</form>

<script type="text/javascript">
function changeIsManager(id){
	$.ajax({
		 type: "POST",
	     url: "${ctx}/admin/auth/workGroupMember/"+id+"/setManager/",
	     dataType:"json",
	     success: function(){
	     	if($("#isManagerDiv"+id).children().html() == "否"){
	     		$("#isManagerButton"+id).html("取消部门管理员");
	     		$("#isManagerDiv"+id).children().html("是");
	     	}else{
	     		$("#isManagerButton"+id).html("设置为部门管理员");
	     		$("#isManagerDiv"+id).children().html("否");
	     	}
	     }
				
	});
}

// 排序处理
$.sortcolumn({
	form : "#admin_auth_workGroupMember_search_form",
	data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
	columns : $("#gridTable th[sortColumn]"),
	sortColumns : "${pageRequest.sortColumns}"
});
$("#gridTable").rowAction({	
	url : "${ctx}/pages/admin/auth/workGroupMember/tab.jsp",
	except : ["checkbox","index","option"],
	"pop" : true,
	poptitle : ""
});
// 表格列表处理
$('#gridTable').flexigrid({
	height: 'auto',
	striped : true
});
</script>
</up72:override>
<%@ include file="base.jsp" %>