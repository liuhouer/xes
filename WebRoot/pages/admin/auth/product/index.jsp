<%@page import="com.up72.auth.model.*" %>
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
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('admin_auth_product_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
	
	function uploadIconPath(){
		showCommonUpload({
	   		width : 450,
	   		height : 250,
	   		callBack : "window.parent.uploadIconPathCall(event, ID, fileObj, response, data)",
	   		fileExt : "*.jpg;*.gif;*.png"
   		});
	}
	
	function uploadIconPathCall(event, ID, fileObj, response, data){
		var file = response.savePath;
		$("#imgPath").val(file);
		if(data.fileCount <= 0){
			window.parent.closeBox();
		}
	}
	
	function loadViewImg(){
		var imgPath = $("#imgPath").val();
		if(!isNull(imgPath)){
			$("#viewImg").attr("href",imgPath);
			$("#viewImg").show();
		}
	}
	
	function showSort(frm,name,url){
		getSelected(name,frm);
		show("iframe#"+url,"调整排序",600,400);
	}
	
	
</script>
</up72:override>

<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
 	<div class="navBar" style="display: none">  » <a class="" href="${ctx}/admin/auth/product" ><%=Product.TABLE_ALIAS%>设置</a></div>
</div>
<!-- END  当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_auth_product_search_form" name="admin_auth_product_search_form" action="${ctx}/admin/auth/product">
  <input type="hidden" id="AUTH_PERM_ID" name="AUTH_PERM_ID" value="${AUTH_PERMISSION.id}" />
	<div class="search_con">
		<div class="search_txt"><%=Product.ALIAS_NAME%>：<input type="text" id="name" name="name" class="input_txt" value="${query.name}"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询-->

<form id="admin_auth_product_page_form" name="admin_auth_product_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th showColumn="name" sortColumn="NAME" width="180"><%=Product.ALIAS_NAME%></th>
				<th showColumn="description" sortColumn="DESCRIPTION" width="380"><%=Product.ALIAS_DESCRIPTION%></th>
			</tr>
		</thead>
		<tbody>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/product/${item.id}/edit','<%=Product.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="name"><img style="vertical-align: top;" height="15" src="${item.imgPath}" onerror="$(this).hide();" /><c:out value='${item.name}'/>&nbsp;</td>
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
		form : "#admin_auth_product_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#gridTable th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	$("#gridTable").rowAction({	
		url : "${ctx}/pages/admin/auth/product/tab.jsp",
		except : ["checkbox","index","option"],
		"pop" : false,
		poptitle : "产品管理"
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '添加', bclass: 'addorder', onpress : function(){show('${ctx}/admin/auth/product/new','<%=Product.TABLE_ALIAS%>添加',600)}},
			{name: '删除', bclass: 'delete', onpress : function(){confirm("确认要删除选定记录吗？", function(){doRestEdit({confirmMsg:'您所选择删除的栏目为一级栏目，删除此栏目会将其下所有子栏目一并删除，是否继续？',url:'${ctx}/admin/auth/product',batchBox:'items',boxCon:'#admin_auth_product_page_form',form:'#admin_auth_product_page_form',method:'delete'})});}},
			{name: '排序', bclass: 'sort', onpress : function(){
				showSort("#admin_auth_product_page_form","items","/admin/auth/product/showSort");
			}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>