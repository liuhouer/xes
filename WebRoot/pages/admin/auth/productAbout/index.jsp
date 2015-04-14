<%@page import="com.up72.auth.model.*" %>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=ProductAbout.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_auth_productAbout_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
<script type="text/javascript">
 function updateSortId(id,type){
	 $.ajax({
			url : "${ctx}/admin/auth/productAbout/"+id+"/updateSortId",
			dataType : "json",
			data : "type="+type,
			success : function(data){
				if(data.message == "success"){
					 window.location.reload();
				}else{
					alert("排序失败，请重试。");
				}	
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
}

</script>
</up72:override>

<up72:override name="content">

<!--当前位置-->
  <div class="head_content">
   <div class="navBar" style="display: none;"> » <a class="" href="${ctx}/admin/auth/productAbout/"><%=ProductAbout.TABLE_ALIAS%>管理</a> </div>
  </div>
<!--end当前位置--> 
<!--查询-->
    <div class="up72_search">
	<form id="admin_auth_productAbout_search_form" name="admin_auth_productAbout_search_form" method="get">
		<div class="search_con">
				<div class="search_txt">产品：
				<select id="productCode" name="productCode">
					<option value="">全部</option>
					<c:forEach items="${productList}" var="product">
						<option value="${product.code}" <c:if test="${query.productCode == product.code}">selected="selected"</c:if>>${product.name}</option>
					</c:forEach>
				</select>
				</div>
				<div class="search_txt"><%=ProductAbout.ALIAS_TITLE%>：<input type="text" id="title" name="title" class="input_txt" value="${query.title}"></div>
			<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button></div></div>
		</div>
	</form>
	</div>
<!--end查询-->

<form id="admin_auth_productAbout_page_form" name="admin_auth_productAbout_page_form" method="get">
	<table id="admin_auth_productAbout_table">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th sortColumn="sortId" showColumn="sortId" width="100">排序</th>
				<th showColumn="option" width="100"><label>操作</label></th>
				<th sortColumn="PRODUCT_ID" showColumn="productCode" width="100">产品</th>
				<th sortColumn="TITLE" showColumn="title" width="200"><%=ProductAbout.ALIAS_TITLE%></th>
				<th sortColumn="CONTENT" showColumn="content" width="400"><%=ProductAbout.ALIAS_CONTENT%></th>
			</tr>
		</thead>
		<tbody>
	<c:forEach items="${page.result}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/auth/productAbout/tab.jsp?id=${item.id}">
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="sortId"><c:if test="${status.count!=1}"><a href="javascript:updateSortId('${item.id}',1);">上移&nbsp;&nbsp;</a></c:if><c:if test="${fn:length(page.result)!=status.count}"><a href="javascript:updateSortId('${item.id}',0);">下移&nbsp;&nbsp;</a></c:if>	&nbsp;</td>
				<td showColumn="option"><a href="javascript:;"  onclick="aboutEdit('${item.id}');" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="productCode"><c:out value='${item.product.name}'/>&nbsp;					</td>
				<td showColumn="title"><c:out value='${item.title}'/>&nbsp;					</td>
				<td showColumn="content"><c:out value='${item.content}'/>&nbsp;					</td>
				
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
		form : "#admin_auth_productAbout_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_auth_productAbout_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_auth_productAbout_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){open("${ctx}/admin/auth/productAbout/new")}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/auth/productAbout/',batchBox:'items',boxCon:'#admin_auth_productAbout_page_form',form:'#admin_auth_productAbout_page_form',method:'delete'})}}
		]
	});
	function aboutEdit(id){
		window.open("${ctx}/admin/auth/productAbout/"+id+"/edit");
	}
</script>
</up72:override>
<%@ include file="base.jsp" %>