<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<form id="admin_auth_productAbout_page_form" name="admin_auth_productAbout_page_form" method="get">
	<input type="hidden" name="code" id="code" value="${code }" />
	<table id="admin_auth_productAbout_table">
		<thead>
			<tr>
				<th  width="25">序号</th>
				<th  width="100">排序</th>
				<th  width="100"><label>操作</label></th>
				<th  width="80">产品</th>
				<th  width="250"><%=ProductAbout.ALIAS_TITLE%></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${aboutList}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/auth/productAbout/tab.jsp?id=${item.id}">
				<td >${status.index+1}</td>
				<td >
				<c:if test="${!status.first}"><a   href="javascript:updateSortId('${item.id}',1);" >上移&nbsp;&nbsp;</a></c:if>
				<c:if test="${status.first}"><a   href="javascript:;" >上移&nbsp;&nbsp;</a></c:if>
				<c:if test="${!status.last}"> <a href="javascript:updateSortId('${item.id}',0);"  >下移&nbsp;&nbsp;</a></c:if>
				<c:if test="${status.last}"> <a href="javascript:;"  >下移&nbsp;&nbsp;</a></c:if>	&nbsp;
				</td>
				<td >
				<a href="javascript:;"  onclick="showPro('${ctx }/admin/auth/productAbout/${item.id }/tabShow')" class="sysiconBtnNoIcon">查看</a>
				<a href="javascript:;"  onclick="show('iframe#${ctx}/admin/auth/productAbout/${item.id }/edit?productCode=${code}','编辑',700,600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td ><c:out value='${item.product.name}'/>&nbsp;</td>
				<td ><c:out value='${item.title}'/>&nbsp;</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>

<script type="text/javascript">
	// 列选择显示处理
	/*$.showcolumn(${showColumn});*/
	// 排序处理
	/*$.sortcolumn({
		form : "#admin_auth_productAbout_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_auth_productAbout_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});*/
	
	// 表格列表处理
	$('#admin_auth_productAbout_table').flexigrid({
		height: 'auto',
		striped : true,
		showToggleBtn:false,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("iframe#${ctx}/admin/auth/productAbout/new?productCode=${code}","添加",700,600)}}
		]
	});
	function aboutEdit(id){
		window.open("${ctx}/admin/auth/productAbout/"+id+"/edit");
	}
	
	function updateSortId(id,type){
	 $.ajax({
			url : "${ctx}/admin/auth/productAbout/"+id+"/updateSortId",
			dataType : "json",
			data : "type="+type+"&productCode=${code}",
			success : function(data){
				if(data.message == "success"){
					 showPro('${ctx}/admin/auth/productAbout/${code}/tabIndex');
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
