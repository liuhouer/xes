<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
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
		<div class="search_txt"><%=AuthUser.ALIAS_NICK_NAME%>：<input type="text" id="nickName" name="nickName" class="input_txt" value="${query.nickName}"></div>
		<div class="search_txt"><%=AuthUser.ALIAS_EMAIL%>：<input type="text" id="email" name="email" class="input_txt" value="${query.email}"></div>
		<div class="search_txt"><%=AuthUser.ALIAS_MOBILE%>：<input type="text" id="mobile" name="mobile" class="input_txt" value="${query.mobile}"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询-->

<form id="admin_auth_member_page_form" name="admin_auth_member_page_form">
	<input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERMISSION.id}" />
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="userName" sortColumn="USER_NAME" width="100"><%=AuthUser.ALIAS_USER_NAME%></th>
				<th showColumn="nickName" sortColumn="NICK_NAME" width="80"><%=AuthUser.ALIAS_NICK_NAME%></th>
				<th showColumn="imgPath" sortColumn="IMG_PATH"  width="50" style="display: none;"><%=AuthUser.ALIAS_IMG_PATH%></th>
				<th showColumn="loginAnswer" sortColumn="LOGIN_ANSWER" width="50"  style="display: none;"><%=AuthUser.ALIAS_LOGIN_ANSWER%></th>
				<th showColumn="secques" sortColumn="SECQUES" width="50" style="display: none;"><%=AuthUser.ALIAS_SECQUES%></th>
				<th showColumn="code" sortColumn="CODE" width="60"><%=AuthUser.ALIAS_CODE%></th>
				<th showColumn="lastIp" sortColumn="LAST_IP" width="120"><%=AuthUser.ALIAS_LAST_IP%></th>
				<th showColumn="lastVisiTime" sortColumn="LAST_VISI_TIME" width="120"  style="display: none;"><%=AuthUser.ALIAS_LAST_VISI_TIME%></th>
				<th showColumn="email" sortColumn="EMAIL" width="120"  style="display: none;"><%=AuthUser.ALIAS_EMAIL%></th>
				<th showColumn="mobileValidate" sortColumn="MOBILE_VALIDATE" width="60"><%=AuthUser.ALIAS_MOBILE_VALIDATE%></th>
				<th showColumn="mobile" sortColumn="MOBILE" width="120"  style="display: none;"><%=AuthUser.ALIAS_MOBILE%></th>
				<th showColumn="enabled" sortColumn="ENABLED" width="50"><%=AuthUser.ALIAS_ENABLED%></th>
				<th showColumn="problem" sortColumn="PROBLEM" width="120"  style="display: none;"><%=AuthUser.ALIAS_PROBLEM%></th>
				<th showColumn="anser" sortColumn="ANSER" width="120"  style="display: none;"><%=AuthUser.ALIAS_ANSER%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/member/${item.id}/edit','<%=AuthUser.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="userName"><c:out value='${item.userName}'/>&nbsp;</td>
				<td showColumn="nickName"><c:out value='${item.nickName}'/>&nbsp;</td>
				<td showColumn="imgPath" style="display: none;"><c:out value='${item.imgPath}'/>&nbsp;</td>
				<td showColumn="loginAnswer" style="display: none;"><c:out value='${item.loginAnswer}'/>&nbsp;</td>
				<td showColumn="secques" style="display: none;"><c:out value='${item.secques}'/>&nbsp;</td>
				<td showColumn="code"><c:choose><c:when test="${item.code == 1}">用户</c:when><c:when test="${item.code == 2}">管理员</c:when><c:when test="${item.code == 5}">系统管理员</c:when></c:choose>&nbsp;</td>
				<td showColumn="lastIp"><c:out value='${item.lastIp}'/>&nbsp;</td>
				<td showColumn="lastVisiTime" style="display: none;"><fmt:formatDate value="${item.lastVisiTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
				<td showColumn="email" style="display: none;"><c:out value='${item.email}'/>&nbsp;</td>
				<td showColumn="mobileValidate"><c:choose><c:when test="${item.mobileValidate == 0}">未认证</c:when><c:when test="${item.mobileValidate == 1}">已认证</c:when></c:choose>&nbsp;</td>
				<td showColumn="emailVisible" style="display: none;"><c:choose><c:when test="${item.emailVisible == 0}">保密</c:when><c:when test="${item.emailVisible == 1}">可见</c:when></c:choose>&nbsp;</td>
				<td showColumn="enabled"><c:choose><c:when test="${item.enabled == 0}">禁用</c:when><c:when test="${item.enabled == 1}">开启</c:when></c:choose>&nbsp;</td>
				<td showColumn="problem" style="display: none;"><c:out value='${item.problem}'/>&nbsp;</td>
				<td showColumn="anser" style="display: none;"><c:out value="${item.anser}" /> &nbsp;</td>
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
	$("#gridTable").rowAction({	
		url : "${ctx}/admin/auth/member/tab",
		except : ["checkbox","index","option"],
		"pop" : true,
		poptitle : "会员标签",
		popw : 800
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/member/new','<%=AuthUser.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录到回收站吗？',url:'${ctx}/admin/auth/member/recycleDelete',batchBox:'items',boxCon:'#admin_auth_member_page_form',form:'#admin_auth_member_page_form',method:'delete'})}},
			{name: '回收站', bclass: '', onpress : function(){window.location.href="${ctx}/admin/auth/member/recycle";}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>