<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title>数据字典管理</title>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	$(document).ready(function() {
		window.simpleTable = new SimpleTable('admin_sys_dictionary_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
	<div class="navBar" style="display:none;">  » <a class="" target="up71_iframe_main" href="${ctx }/admin/sys/dictionary/">数据字典管理</a></div>
</div>
<!-- END 当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_sys_dictionary_search_form" name="admin_sys_dictionary_search_form" action="${ctx}/admin/sys/dictionary/">
	<div class="search_con">
		<div class="search_txt">字典Code：<input type="text" id="code" name="code" class="input_txt" value="${code}"></div>
		<div class="search_txt">名  称：<input type="text" id="name" name="name" class="input_txt" value="${name}"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询--> 
<form id="admin_sys_dictionary_page_form" name="admin_sys_dictionary_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="code" width="150">字典Code</th>
				<th showColumn="name" width="150">字典名称</th>
				<th showColumn="sortId" width="50">排序值</th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/sys/dictionary/${item.id}/edit','字典编辑',500)" class="sysiconBtnNoIcon">编辑</a></td>
				<td showColumn="code"><c:out value='${item.refCode}'/>&nbsp;</td>
				<td showColumn="name"><a href="${ctx}/admin/sys/dictionaryValue/?dictionaryId=${item.id}"><c:out value='${item.name}'/></a>&nbsp;</td>
				<td showColumn="sortId"><c:out value='${item.sort}'/>&nbsp;</td>
			</tr>
			<tr style="display:none;"><td colspan="8"></td></tr>
		</c:forEach>
 	 	</tbody>
	</table>
	<simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
</form>
<script type="text/javascript">
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/sys/dictionary/new','添加字典',600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除该词典吗？',url:'${ctx}/admin/sys/dictionary',batchBox:'items',boxCon:'#admin_sys_dictionary_page_form',form:'#admin_sys_dictionary_page_form',method:'delete'})}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>