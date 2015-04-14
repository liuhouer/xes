<%@page import="com.up72.auth.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/scripts/rest.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css"
	rel="stylesheet">
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/simpletable/simpletable.js"></script>

<form id="admin_auth_role_page_form" name="admin_auth_role_page_form">
	<input type="hidden" id="return_url" name="return_url"
		value="redirect:/pages/admin/auth/product/tab.jsp?id=&AUTH_PERM_ID=${AUTH_PERM_ID}" />
	<table id="gridTable2">
		<thead>
			<tr>
				<!-- <th showColumn="checkbox" width="30">
					<input type="checkbox" id="checkall"
						onclick="setAllCheckboxState('items',this.checked);" />
				</th>-->
				<th showColumn="index" width="30">
					序号
				</th> 
				<th showColumn="option" width="50">
					<label>
						操作
					</label>
				</th>
				<th showColumn="name" sortColumn="NAME" width="250"><%=WorkGroup.ALIAS_NAME%></th>
				<th showColumn="organizationId" sortColumn="organizationId" width="250"><%=WorkGroup.ALIAS_ORGANIZATION_ID%></th>
				<th showColumn="enabled" sortColumn="ENABLED" width="100"><%=WorkGroup.ALIAS_ENABLED%></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${workGroupList}" begin="1" var="item" varStatus="status">
				<tr>
					<!-- <td showColumn="checkbox">
						<input type="checkbox" id="items" name="items" value="${item.id}"
							class="sel" tags="null">
					</td> -->
					<td showColumn="index">
						<!-- ${page.thisPageFirstElementNumber + status.index+1} -->
						${status.index }
					</td>
					<td showColumn="option">
						<a href="javascript:;"
							onclick="showPro('${ctx}/admin/auth/workGroup/${item.id }/view')"
							class="sysiconBtnNoIcon">查看</a>&nbsp;
					</td>
					<td showColumn="name">
						<c:forEach begin="2" end="${item.deptLevel}">&nbsp;&nbsp;</c:forEach>
						<c:if test="${status.index > 1}">|-</c:if>
						<c:out value='${item.name}' />
						&nbsp;
					</td>
					<td showColumn="name">
						<c:out value='${item.organization.name}' />
						&nbsp;
					</td>
					<td showColumn="enabled">
						<c:choose>
							<c:when test="${item.enabled == 1}">启用</c:when>
							<c:when test="${item.enabled == 0}">禁用</c:when>
						</c:choose>
						&nbsp;
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
<script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	
	//$("#gridTable2").rowAction({	
	//	url : "${ctx}/pages/admin/auth/product/role/tab.jsp?AUTH_PERM_ID=${AUTH_PERM_ID}",
	//	except : ["checkbox","index","option"],
	//	"pop" : false,
	//	poptitle : "编辑角色"
	//});
	// 表格列表处理
	$('#gridTable2').flexigrid({
		height: 'auto',
		striped : true,
//		buttons : [
//			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/role/proNew?productCode=${product.id}','<%=Role.TABLE_ALIAS%>添加',600)}},
//			{name: '删除', bclass: 'delete', onpress : function (){confirm("确认要删除选定记录吗？", function(){doRestEdit({url:'${ctx}/admin/auth/role',batchBox:'items',boxCon:'#admin_auth_role_page_form',form:'#admin_auth_role_page_form',method:'delete'});});}}
//		]
	});
</script>