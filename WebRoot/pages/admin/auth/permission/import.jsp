<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.bruce.common.xml.DatasXmlVo"%>
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
	function doImportPerm(){
	  if(isSelect('items')){
	  		confirm("确定导入所选数据吗？",function(){
	  			$("#admin_auth_product_page_form").submit();
 		});
			
		}else{
			alert("请选择要导入的数据！");
		}
	
}

function goBack(){
	window.location.href = "/admin/auth/permission/goBackImport?filePath=${filePath}";
}
$(document).ready(function(){
	$("input[type=checkbox]").attr("checked",true);
});
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
 	<div class="navBar" style="display: none">  » <a class="" href="${ctx}/admin/auth/product" ><%=Product.TABLE_ALIAS%>设置</a></div>
</div>
<!-- END  当前位置 -->
<form id="admin_auth_product_page_form" name="admin_auth_product_page_form" action="${ctx}/admin/auth/permission/doImportPerm">
	<input type="hidden" name="filePath" value="${filePath}"/>
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="name" sortColumn="NAME" width="180"><%=Product.ALIAS_NAME%></th>
				<th showColumn="description" sortColumn="DESCRIPTION" width="380"><%=Product.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${productList}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="${item.code}" name="items" value="${item.code}" class="sel" tags="null"></td>
				<td showColumn="index">${status.index+1}</td>
				<td showColumn="name"><img style="vertical-align: top;" height="15" src="${item.imgPath}" onerror="$(this).hide();" /><c:out value='${item.name}'/>&nbsp;</td>
				<td showColumn="description"><c:out value='${item.description}'/>&nbsp;</td>
			</tr>
		</c:forEach>
 	 	</tbody>
	</table>
	<div class="up72_quickbtns">
		<div class="btn btn_sub" title="下一步"><input type="button"  value="下一步" onclick="doImportPerm()"/></div>
		<div class="btn btn_sub" title="上一步"><input type="button"  value="上一步" onclick="goBack();" /></div>
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