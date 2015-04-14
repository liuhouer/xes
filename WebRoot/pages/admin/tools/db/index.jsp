<%@page import="com.bruce.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>

<up72:override name="head">
	<title><%=DatabaseLink.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js"></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css"
		rel="stylesheet">
	<script type="text/javascript"
		src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>

	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css"
		rel="stylesheet">
	<script type="text/javascript"
		src="${ctx}/scripts/grid/flexigrid-source.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_cm_databaseLink_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
		function admin_tools_db_backup(id){
			alert("数据库备份中，请到备份列表中查看操作日志！", 3);
			$.ajax({
				type: "GET",
				url: "${ctx}/admin/tools/db/backup/" + id,
				dataType: "json",
			});
		}
		function admin_tools_db_backupList(id){
			show("${ctx}/admin/tools/db/backupList/" + id, "备份列表", 600 , 300);
		}
	</script>
	<script type="text/javascript"
		src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">

	<!--当前位置-->
	<div class="head_content">
		<div class="navBar" style="display: none;">
			»
			<a class="" href="${ctx}/admin/cm/dbLink/">数据库备份还原</a>
		</div>
	</div>
	<!--end当前位置-->
	<!--查询-->
	<div class="up72_search">
		<form id="admin_cm_databaseLink_search_form"
			name="admin_cm_databaseLink_search_form" method="get">
			<div class="search_con">
				<div class="search_txt"><%=DatabaseLink.ALIAS_CM_DIALECT%>：
					<input type="text" id="cmDialect" name="cmDialect"
						class="input_txt" value="${query.cmDialect}">
				</div>
				<div class="search_txt"><%=DatabaseLink.ALIAS_CM_DB_NAME%>：
					<input type="text" id="cmDbName" name="cmDbName" class="input_txt"
						value="${query.cmDbName}">
				</div>
				<div class="search_btn">
					<div class="input_button">
						<button name="btnU" type="submit"
							onclick="$(this).parents('form').submit();" class="button"
							value="查询">
							<span>查询</span>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!--end查询-->

	<form id="admin_cm_databaseLink_page_form"
		name="admin_cm_databaseLink_page_form" method="get">
		<table id="admin_cm_databaseLink_table">
			<thead>
				<tr>
					<th showColumn="checkbox" width="25">
						<input type="checkbox" id="checkall"
							onclick="setAllCheckboxState('items',this.checked);" />
					</th>
					<th showColumn="index" width="20">
						序号
					</th>
					<th showColumn="option" width="80">
						<label>
							操作
						</label>
					</th>
					<th sortColumn="CM_DIALECT" showColumn="cmDialect" width="80"><%=DatabaseLink.ALIAS_CM_DIALECT%></th>
					<th sortColumn="CM_DB_NAME" showColumn="cmDbName" width="100"><%=DatabaseLink.ALIAS_CM_DB_NAME%></th>
					<th sortColumn="CM_LINK_IP" showColumn="cmLinkIp" width="150"><%=DatabaseLink.ALIAS_CM_LINK_IP%></th>
					<th sortColumn="CM_LINK_PORT" showColumn="cmLinkPort" width="80"><%=DatabaseLink.ALIAS_CM_LINK_PORT%></th>
					<th sortColumn="CM_USER_NAME" showColumn="cmUserName" width="100"><%=DatabaseLink.ALIAS_CM_USER_NAME%></th>
					<th sortColumn="CM_PASSWORD" showColumn="cmPassword" width="100"><%=DatabaseLink.ALIAS_CM_PASSWORD%></th>
					<th sortColumn="CM_ENCODING" showColumn="cmEncoding" width="100"><%=DatabaseLink.ALIAS_CM_ENCODING%></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr>
						<td showColumn="checkbox">
							<input type="checkbox" id="items" name="items" value="${item.id}"
								class="sel" tags="null">
						</td>
						<td showColumn="index">
							${page.thisPageFirstElementNumber + status.index}
						</td>
						<td showColumn="option">
							<a href="javascript:" onclick="admin_tools_db_backup(${item.id});" class="sysiconBtnNoIcon">备份</a>&nbsp;
							<a href="javascript:" onclick="admin_tools_db_backupList(${item.id});" class="sysiconBtnNoIcon">备份列表</a>&nbsp;
						</td>
						<td showColumn="cmDialect">
							<c:out value='${item.cmDialect}' />
							&nbsp;
						</td>
						<td showColumn="cmDbName">
							<c:out value='${item.cmDbName}' />
							&nbsp;
						</td>
						<td showColumn="cmLinkIp">
							<c:out value='${item.cmLinkIp}' />
							&nbsp;
						</td>
						<td showColumn="cmLinkPort">
							<c:out value='${item.cmLinkPort}' />
							&nbsp;
						</td>
						<td showColumn="cmUserName">
							<c:out value='${item.cmUserName}' />
							&nbsp;
						</td>
						<td showColumn="cmPassword">
							<c:out value='${item.cmPassword}' />
							&nbsp;
						</td>
						<td showColumn="cmEncoding">
							<c:out value='${item.cmEncoding}' />
							&nbsp;
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
		form : "#admin_cm_databaseLink_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_cm_databaseLink_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_cm_databaseLink_table').flexigrid({
		height: 'auto',
		striped : true
	});
</script>
</up72:override>
<%@ include file="base.jsp"%>