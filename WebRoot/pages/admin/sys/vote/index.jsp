<%@page import="com.up72.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=LogBusiness.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/main.js"></script>
	
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_sys_logBusiness_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
			var selectType = $("#type").val();
			var selectUserGuid = $("#userGuid").val();
			$("#inputtype").val(selectType);
			$("#inputuserGuid").val(selectUserGuid);
		});
		
		function subForm(){
			$("#admin_sys_logBusiness_search_form").submit();
		}
	</script>
<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">

<!--当前位置-->
  <div class="head_content">
   <div class="navBar" style="display: none;"> » <a class="" href="${ctx}/admin/sys/logBusiness/"><%=LogBusiness.TABLE_ALIAS%>管理</a> </div>
  </div>
<!--end当前位置--> 
<!--搜索-->
    <div class="up72_search">
	<form id="admin_sys_logBusiness_search_form" name="admin_sys_logBusiness_search_form" method="get">
		<div class="search_con">
				<div class="search_txt"><%=LogBusiness.ALIAS_USER_GUID%>：
					<select name="userGuid" id="userGuid">
		        		<option value="">--查询全部--</option>
		        		<c:forEach items="${authUserList}" var="authUser" varStatus="status">
		        		<option value="${authUser.id }" <c:if test="${authUser.id == query.userGuid }">selected="selected"</c:if>>${authUser.userName }</option>
		        		</c:forEach>
        			</select>
				</div>
				<div class="search_txt"><%=LogBusiness.ALIAS_TYPE%>：
					<select name="type" id="type">
		        		<option value="">--查询全部--</option>
		        		<option value="用户登录" <c:if test="${'用户登录' == query.type }">selected="selected"</c:if>>用户登录</option>
		        		<option value="用户退出" <c:if test="${'用户退出' == query.type }">selected="selected"</c:if>>用户退出</option>
		        		<option value="创建" <c:if test="${'创建' == query.type }">selected="selected"</c:if>>创建</option>
		        		<option value="编辑" <c:if test="${'编辑' == query.type }">selected="selected"</c:if>>编辑</option>
		        		<option value="删除" <c:if test="${'删除' == query.type }">selected="selected"</c:if>>删除</option>
        			</select>
				</div>
				<%--<div class="search_txt">开始时间：
          			<input type="text" id="timeBeginStr" name="timeBeginStr" class="calendar input_txt" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${query.timeBeginDate}" pattern="yyyy-MM-dd"/>">
        		</div>
        		<div class="search_txt">结束时间：
         			 <input type="text" id="timeEndStr" name="timeEndStr" class="calendar input_txt" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${query.timeEndDate}" pattern="yyyy-MM-dd"/>">
        		</div>
				<div class="search_txt"><%=LogBusiness.ALIAS_RESULT%>：<input type="text" id="result" name="result" class="input_txt" value="${query.result}"></div>
				<div class="search_txt"><%=LogBusiness.ALIAS_IP%>：<input type="text" id="ip" name="ip" class="input_txt" value="${query.ip}"></div>
				<div class="search_txt"><%=LogBusiness.ALIAS_FUNCTION%>：<input type="text" id="function" name="function" class="input_txt" value="${query.function}"></div>
			--%><div class="search_btn"><div class="input_button"><button name="btnU" type="button" onclick="subForm()" class="button" value="搜索"><span>搜索</span></button></div></div>
		</div>
	</form>
	</div>
<!--end搜索-->

<form id="admin_sys_logBusiness_page_form" name="admin_sys_logBusiness_page_form" method="get">
	<input type="hidden" name="type" id="inputtype" value=""/>
	<input type="hidden" name="userGuid" id="inputuserGuid" value=""/>
	<table id="admin_sys_logBusiness_table">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th sortColumn="USER_GUID" showColumn="userGuid" width="100"><%=LogBusiness.ALIAS_USER_GUID%></th>
				<th sortColumn="TIME" showColumn="time" width="200"><%=LogBusiness.ALIAS_TIME%></th>
				<th sortColumn="TYPE" showColumn="type" width="100"><%=LogBusiness.ALIAS_TYPE%></th>
				<th sortColumn="RESULT" showColumn="result" width="100"><%=LogBusiness.ALIAS_RESULT%></th>
				<th sortColumn="IP" showColumn="ip" width="100"><%=LogBusiness.ALIAS_IP%></th>
				<th sortColumn="FUNCTION" showColumn="function" width="200"><%=LogBusiness.ALIAS_FUNCTION%></th>
				<th sortColumn="DESCRIPTION" showColumn="description" width="200"><%=LogBusiness.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	<c:forEach items="${page.result}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/sys/logBusiness/tab.jsp?id=${item.id}">
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="userGuid"><c:out value='${item.authUser.userName}'/>&nbsp;</td>
				<td showColumn="time"><fmt:formatDate value="${item.timeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
				<td showColumn="type"><c:out value='${item.type}'/>&nbsp;</td>
				<td showColumn="result"><c:out value='${item.result}'/>&nbsp;</td>
				<td showColumn="ip"><c:out value='${item.ip}'/>&nbsp;</td>
				<td showColumn="function"><c:out value='${item.function}'/>&nbsp;</td>
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
		form : "#admin_sys_logBusiness_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_sys_logBusiness_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	 
	
	// 表格列表处理
	$('#admin_sys_logBusiness_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/sys/logBusiness/',batchBox:'items',boxCon:'#admin_sys_logBusiness_page_form',form:'#admin_sys_logBusiness_page_form',method:'delete'})}},
			{name: '清空日志', bclass: 'delete', onpress : function(){deleteAll()}},
			{name: '导出到Excel', bclass: '', onpress : function(){sysLogBusinessExport()}},
			{name: '导出到文本', bclass: '', onpress : function(){exportTxt()}}
			]
	});
	function sysLogBusinessExport(){
		$("#admin_sys_logBusiness_page_form").attr("action", "${ctx}/admin/sys/logBusiness/export");
		$("#admin_sys_logBusiness_page_form").submit();
		$("#admin_sys_logBusiness_page_form").removeAttr("action");
	}
	function deleteAll(){
		confirm("确认清空日志吗？",function (){
		 window.location.href = "${ctx}/admin/sys/logBusiness/deleteAll";
		 //window.location.reload();
		},null);
	}
	function exportTxt(){
		$("#admin_sys_logBusiness_page_form").attr("action", "${ctx}/admin/sys/logBusiness/exportTxt");
		$("#admin_sys_logBusiness_page_form").submit();
		$("#admin_sys_logBusiness_page_form").removeAttr("action");
	}
</script>
</up72:override>
<%@ include file="base.jsp" %>