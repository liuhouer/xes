<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=SearchHistory.TABLE_ALIAS%> 维护</title>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('admin_tools_searchHistory_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
	<div class="navBar" style="display:none;"> »
		<a class=""  href="${ctx }/admin/tools/searchHistory"><%=SearchHistory.TABLE_ALIAS %>管理</a>
	</div>
</div>
<!-- END 当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_tools_searchHistory_search_form" name="admin_tools_searchHistory_search_form" action="${ctx}/admin/tools/searchHistory">
	<div class="search_con">
		<div class="search_txt">关键词：<input type="text" id="keyWords" name="keyWords" class="input_txt" value="${query.keyWords}"></div>
		<div class="search_txt">类型：<select id="type" name="type">
				<option value="-1" <c:if test="${-1 == query.type}">selected</c:if>>所有</option>
				<option value="0" <c:if test="${0 == query.type}">selected</c:if>>用户检索</option>
				<option value="1" <c:if test="${1 == query.type}">selected</c:if>>后台添加</option>
			</select>
		</div>
		<div class="search_txt">时间：<input type="text" id="startAddTimeStr" name="startAddTimeStr" class="input_txt" value="${startAddTimeStr}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'});" readonly="readonly">-<input type="text" id="endAddTimeStr" name="endAddTimeStr" class="input_txt" value="${endAddTimeStr}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询--> 
<form id="admin_tools_searchHistory_page_form" name="admin_tools_searchHistory_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="keyWords" sortColumn="KEY_WORDS" width="400"><%=SearchHistory.ALIAS_KEY_WORDS%></th>
				<th showColumn="type" sortColumn="TYPE" width="150"><%=SearchHistory.ALIAS_TYPE%></th>
				<th showColumn="addTime" sortColumn="ADD_TIME" width="100"><%=SearchHistory.ALIAS_ADD_TIME%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="keyWords"><c:out value='${item.keyWords}'/>&nbsp;</td>
				<td showColumn="type"><c:if test="${0 == item.type}">用户检索</c:if><c:if test="${0 != item.type}">后台添加</c:if>&nbsp;</td>
				<td showColumn="addTime"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.addTimeDate}" />&nbsp;</td>
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
		form : "#admin_tools_searchHistory_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '新增', bclass: 'addorder', onpress : function(){show('${ctx}/admin/tools/searchHistory/new','<%=SearchHistory.TABLE_ALIAS%>添加',600);}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestBatchDelete('${ctx}/admin/tools/searchHistory/','items',document.forms.admin_tools_searchHistory_page_form);}},
			{name: 'TOP统计', bclass: '', onpress : function(){show('${ctx}/pages/admin/tools/searchHistory/top.jsp','<%=SearchHistory.TABLE_ALIAS%>TOP统计',600);}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>