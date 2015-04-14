<%@page import="com.up72.sys.model.*" %>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=DataChangLog.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_sys_dataChangLog_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">

<!--当前位置-->
  <div class="head_content">
   <div class="navBar" style="display: none;"> » <a class="" href="${ctx}/admin/sys/dataChangLog/"><%=DataChangLog.TABLE_ALIAS%>管理</a> </div>
  </div>
<!--end当前位置--> 
<!--查询-->
    <div class="up72_search">
	<form id="admin_sys_dataChangLog_search_form" name="admin_sys_dataChangLog_search_form" method="get">
		<div class="search_con">
				<div class="search_txt"><%=DataChangLog.ALIAS_CHANG_TABLE%>：<input type="text" id="changTable" name="changTable" class="input_txt" value="${query.changTable}"></div>
				<div class="search_txt"><%=DataChangLog.ALIAS_CHANG_ID%>：<input type="text" id="changId" name="changId" class="input_txt" value="${query.changId}"></div>
				<div class="search_txt"><%=DataChangLog.ALIAS_CHANG_TIME%>：<input type="text" id="changTime" name="changTime" class="input_txt" value="${query.changTime}"></div>
				<div class="search_txt"><%=DataChangLog.ALIAS_CHANG_TYPE%>：<input type="text" id="changType" name="changType" class="input_txt" value="${query.changType}"></div>
			<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
		</div>
	</form>
	</div>
<!--end查询-->

<form id="admin_sys_dataChangLog_page_form" name="admin_sys_dataChangLog_page_form" method="get">
	<table id="admin_sys_dataChangLog_table">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th sortColumn="CHANG_TABLE" showColumn="changTable" width="50"><%=DataChangLog.ALIAS_CHANG_TABLE%></th>
				<th sortColumn="CHANG_ID" showColumn="changId" width="50"><%=DataChangLog.ALIAS_CHANG_ID%></th>
				<th sortColumn="CHANG_TIME" showColumn="changTime" width="50"><%=DataChangLog.ALIAS_CHANG_TIME%></th>
				<th sortColumn="CHANG_TYPE" showColumn="changType" width="50"><%=DataChangLog.ALIAS_CHANG_TYPE%></th>
			</tr>
		</thead>
		<tbody>
	<c:forEach items="${page.result}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/sys/dataChangLog/tab.jsp?id=${item.id}">
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/sys/dataChangLog/${item.id}/edit','<%=DataChangLog.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="changTable"><c:out value='${item.changTable}'/>&nbsp					</td>
				<td showColumn="changId"><c:out value='${item.changId}'/>&nbsp					</td>
				<td showColumn="changTime"><fmt:formatDate value="${item.changTimeDate}" pattern="yyyy-MM-dd HH:ss"/>&nbsp;					</td>
				<td showColumn="changType"><c:out value='${item.changType}'/>&nbsp					</td>
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
		form : "#admin_sys_dataChangLog_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_sys_dataChangLog_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	$("#admin_sys_dataChangLog_table").rowAction({	
		"url" : "/pages/admin/sys/dataChangLog/tab.jsp",
		"except" : ["checkbox","index","option"],
		"pop" : true,
		"poptitle" : "<%=DataChangLog.TABLE_ALIAS%>标签",
		"popw" : 600
	});
	// 表格列表处理
	$('#admin_sys_dataChangLog_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/sys/dataChangLog/new","<%=DataChangLog.TABLE_ALIAS%>添加",600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/sys/dataChangLog/',batchBox:'items',boxCon:'#admin_sys_dataChangLog_page_form',form:'#admin_sys_dataChangLog_page_form',method:'delete'})}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>