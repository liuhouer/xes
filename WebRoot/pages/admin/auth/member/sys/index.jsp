<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@page import="com.bruce.common.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=AuthUser.TABLE_ALIAS%> 维护</title>
<script src="${ctx}/scripts/rest.js" ></script>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script src="${ctx}/scripts/clearbox/clearbox.js" type="text/javascript"></script> 
<script type="text/javascript" >
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('admin_auth_member_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
   		<div class="navBar"  style="display: none;">  » <a class="" href="${ctx}/admin/auth/member" ><%=AuthUser.TABLE_ALIAS%>列表</a></div>
</div>
<!-- END 当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_auth_member_search_form" name="admin_auth_member_search_form" action="${ctx}/admin/auth/member/sys">
  	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERMISSION.id}" />
	<div class="search_con">
		<div class="search_txt"><%=AuthUser.ALIAS_USER_NAME%>：<input type="text" id="userName" name="userName" class="input_txt" value="${query.userName}"></div>
		<div class="search_txt"><%=AuthUser.ALIAS_ENABLED%>：<input type="text" id="nickName" name="nickName" class="input_txt" value="${query.nickName}"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询-->

<form id="admin_auth_member_page_form" name="admin_auth_member_page_form">
	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERMISSION.id}" />
	<input type="hidden" id="userType" name="userType" value="${userType}" />
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="index" width="30">序号</th>
				<th showColumn="enabled" sortColumn="ENABLED" width="50" ><%=AuthUser.ALIAS_ENABLED%></th>
				<th showColumn="nickName" sortColumn="NICK_NAME" width="100"><%=AuthUser.ALIAS_NICK_NAME%></th>
				<th showColumn="userName" sortColumn="USER_NAME" width="100"><%=AuthUser.ALIAS_USER_NAME%></th>
	            <th sortColumn="IMG_PATH" showColumn="imgPath" width="100"><%=AuthUser.ALIAS_IMG_PATH%></th>
				<th showColumn="lastIp" sortColumn="LAST_IP" width="120" ><%=AuthUser.ALIAS_LAST_IP%></th>
				<th showColumn="lastVisiTime" sortColumn="LAST_VISI_TIME" width="160"><%=AuthUser.ALIAS_LAST_VISI_TIME%></th>
				<th showColumn="addTime" sortColumn="ADD_TIME" width="160"><%=AuthUser.ALIAS_ADD_TIME%></th>
				<th showColumn="option" width="120"><label>操作</label></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="enabled"><c:choose><c:when test="${item.enabled == 0}">冻结</c:when><c:when test="${item.enabled == 1}">正常</c:when></c:choose></td>
				<td showColumn="nickName"><c:out value='${item.nickName}'/></td>
				<td showColumn="userName"><c:out value='${item.userName}'/></td>
				<td showColumn="imgPath"><a href="${item.imgPath}" rel="clearbox" ><img src="${ctx}${item.imgPath}" width="15px" height="15px"/></a></td>
			    <td showColumn="lastIp"><c:out value='${item.lastIp}'/>&nbsp;</td>
				<td showColumn="lastVisiTime"><fmt:formatDate value="${item.lastVisiTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td showColumn="option">
					<a href="javascript:;"  onclick="show('${ctx}/admin/auth/member/${item.id}/edit','<%=AuthUser.TABLE_ALIAS%>',800)" class="sysiconBtnNoIcon">编辑</a>
					<c:choose>
						<c:when test="${item.enabled == 0}">
							<a href="javascript:;" onclick="doValid(${item.id},this)" id="statusBtn" class="sysiconBtnNoIcon">解冻</a>
						</c:when>
						<c:when test="${item.enabled == 1}">
							<a href="javascript:;" onclick="doValid(${item.id},this)" id="statusBtn" class="sysiconBtnNoIcon">冻结</a>	
						</c:when>
					</c:choose>
					<a href="javascript:;" onclick="show('${ctx}/admin/auth/member/modifyPassword','<%=AuthUser.TABLE_ALIAS%>',800)" class="sysiconBtnNoIcon">修改密码</a>
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
		form : "#admin_auth_members_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}&AUTH_PERM_ID=${AUTH_PERMISSION.id}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});

	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/member/sys/new','<%=AuthUser.TABLE_ALIAS%>添加',800)}}
			//{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录到回收站吗？',url:'${ctx}/admin/auth/member/recycleDelete?userType=sys',batchBox:'items',boxCon:'#admin_auth_member_page_form',form:'#admin_auth_member_page_form',method:'delete'})}}
		]
	});

	function doValid(id,item){
		var $item = $(item);
		$.ajax({
			url : "/admin/auth/member/"+id+"/doValid",
			type : "post",
			dataType : "json",
			success : function(jsondatas){
				if(jsondatas.status=='success'){
					if(jsondatas.valid==1){
						$item.text('冻结');
						$item.closest("tr").find("td[showColumn='enabled'] div").text('正常');
					}else{
						$item.text('解冻');
						$item.closest("tr").find("td[showColumn='enabled'] div").text('冻结');
					}
				}
			},
			error : function(){
			}
		});
	}	
</script>
</up72:override>
<%@ include file="../base.jsp" %>