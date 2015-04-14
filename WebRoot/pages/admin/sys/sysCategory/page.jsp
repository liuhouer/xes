<%@page import="com.up72.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=SysCategory.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_cms_category_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
		
		function addCategory(){
	
			show("${ctx}/admin/sys/sysCategory/new","<%=SysCategory.TABLE_ALIAS%>添加",600)
	
		}
	  	function editCategory(categoryId){
	  		var url = "${ctx}/admin/cms/category/goEdit?id="+categoryId;
	  		show(url,"编辑分类",600,350);
	  	}
	  	
	  	function deleteCategory(){
	  		doRestEdit({
	  			confirmMsg:'确认删除选中的记录吗？',
	  			url:'${ctx}/admin/sys/sysCategory/doDelete',
	  			batchBox:'category_ids',
	  			boxCon:'#admin_cms_category_page_form',
	  			form:'#admin_cms_category_page_form',
	  			method:'delete'
	  			});
	  	}
	  	
	function deleteCheckedCategory(){
		if(isSelect('category_ids')){
		confirm('确定删除吗 ?',function(){
		datas = $("#admin_cms_category_page_form").serialize();
  		$.ajax({
  			url : "${ctx}/admin/sys/sysCategory/doDelete",
  			type : "post",
  			data : datas,
  			success : function(response){
  			window.location.reload();
  				closeBox();
  				alert("删除成功",3,function(){
  					window.location.reload();
  				});
  			} 
  		});
		},closeBox());
		}else{
			alert("请选择数据");
		}
	
}
	  	
	</script>
<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">

<!--当前位置-->
  <div class="head_content">
   <div class="navBar" style="display: none;"> » <a class="" href="${ctx}/admin/cms/category/"><%=SysCategory.TABLE_ALIAS%>管理</a> </div>
  </div>
<!--end当前位置--> 
<!--查询-->
    <div class="up72_search">
	 <form id="admin_cms_category_search_form" name="admin_cms_category_search_form" method="get" action="${ctx}/admin/sys/sysCategory/search">
	    <div class="search_con">
	      <div class="search_txt"><%=SysCategory.ALIAS_PARENT_GUID%>：
	    	 <select id="parentGuid" name="parentGuid">
	  	 		 <option value="">请选择上级分类</option>
	  	 		 <c:forEach items="${categoryList}" var="item">
	  	  		<option value="${item.guid}" <c:if test="${item.guid==parentGuid}">selected="selected"</c:if>>
	  	  			<c:forEach begin="2" end="${item.level}">&nbsp;&nbsp;</c:forEach>
            		<c:if test="${item.level > 1}">|-</c:if>
	  	  			${item.catName}</option>
	  		 	 </c:forEach>
  			</select>
	      </div>
	      <div class="search_txt"><%=SysCategory.ALIAS_CAT_NAME%>：
	        <input type="text" id="catName" name="catName" class="input_txt" value="${query.catName}">
	      </div>
	      <div class="search_btn">
	        <div class="input_button">
	          <button name="btnU" type="submit" id="btnU" class="button" value="查询"><span>查询</span></button>
	        </div>
	      </div>
	    </div>
	  </form>
	</div>
<!--end查询-->

<form id="admin_cms_category_page_form" name="admin_cms_category_page_form" method="get">
	<input type="hidden" id="catName" name="catName" value="${catName}" />
	<input type="hidden" id="parentGuid" name="parentGuid" value="${parentGuid}" />
	<input type="hidden" id="level" name="level" value="${level}" />
	
	<div class="flexigrid">
    <div class="tDiv">
      <div class="tDiv2">
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="addCategory()">添加</span></div>
        </div>
        <div class="fbutton">
          <div><span class="delete" style="padding-left: 20px;" onclick="deleteCheckedCategory()">删除</span></div>
        </div>
      </div>
    </div>
  </div>
	<table id="admin_cms_category_table">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('category_ids',this.checked);" /></th>
				<th showColumn="index" width="20">序号</th>
				<th showColumn="option" width="30"><label>操作</label></th>
				<th sortColumn="parent_id" showColumn="parentId" width="100"><%=SysCategory.ALIAS_PARENT_GUID%></th>
				<th sortColumn="name" showColumn="name" width="200"><%=SysCategory.ALIAS_CAT_NAME%></th>
			</tr>
		</thead>
		<tbody>
	<c:forEach items="${page.result}" var="item" varStatus="status">
			<tr rel="${ctx}/pages/admin/cms/category/tab.jsp?id=${item.id}">
				<td showColumn="checkbox"><input type="checkbox" id="category_ids" name="category_ids" value="${item.guid}" class="sel" tags="null"></td>
				<td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
				<td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/sys/sysCategory/${item.id}/edit','<%=SysCategory.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
				<td showColumn="parentId"><c:out value='${item.parent.catName}'/>&nbsp					</td>
				<td showColumn="name"><c:out value='${item.catName}'/>&nbsp					</td>
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
		form : "#admin_cms_category_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_cms_category_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_cms_category_table').flexigrid({
		height: 'auto',
		striped : true
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>