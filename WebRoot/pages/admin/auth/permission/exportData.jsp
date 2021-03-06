﻿<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=Product.TABLE_ALIAS%> 维护</title>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	function exportPerm(){
	confirm('确定导出所选数据吗?',function(){
		datas = $("#admin_auth_product_page_form").serialize();
  		$.ajax({
  			url : "${ctx}/admin/auth/permission/doExportPerm",
  			type : "post",
  			data : datas,
  			dataType : "json",
  			success : function(data){
  				if(data.message=="success"){
			  		window.location.href = "/admin/auth/permission/downPerm?filePath="+data.xmlPath;
  				}else{
					alert(decodeURI(data.message));
				}
  			} 
  		});
		},closeBox());
}

function goBack(){
	window.history.go(-1);
}
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
 	<div class="navBar" style="display: none">  » <a class="" href="${ctx}/admin/auth/product" ><%=Product.TABLE_ALIAS%>设置</a></div>
</div>
<!-- END  当前位置 -->
<form id="admin_auth_product_page_form" name="admin_auth_product_page_form" action="${ctx}/admin/auth/permission/goBack">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="name" sortColumn="NAME" width="180"><%=Product.ALIAS_NAME%></th>
				<th showColumn="description" sortColumn="DESCRIPTION" width="380"><%=Product.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${productList}" var="item" varStatus="status">
			<tr>
				<input type="hidden" name="items" value="${item.code}"/>
				<td showColumn="index">${status.index+1}</td>
				<td showColumn="name"><img style="vertical-align: top;" height="15" src="${item.imgPath}" onerror="$(this).hide();" /><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="description"><c:out value='${item.description}'/>&nbsp;</td>
			</tr>
		</c:forEach>
 	 	</tbody>
	</table>
	<div class="up72_quickbtns">
		<div class="btn btn_sub" title="上一步"><input type="submit"  value="上一步" /></div>
		<div class="btn btn_sub" title="完成"><input type="button"  value="完成" onclick="exportPerm();"/></div>
	</div>
</form>
<script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 排序处理
	$.sortcolumn({
		form : "#admin_auth_product_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>