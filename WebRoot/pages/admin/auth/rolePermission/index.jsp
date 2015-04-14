<%@page import="com.up72.auth.model.*" %>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=RolePermission.TABLE_ALIAS%> 维护</title>
	
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_auth_rolePermission_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
<script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script>
</up72:override>

<up72:override name="content">

<div class="workground">
<form id="admin_auth_rolePermission_page_form" name="admin_auth_rolePermission_page_form" method="get">
  <div class="head_content">
    <!--当前位置-->
   <div class="navBar"> <a href="#">桌面</a> » <a class="" href="${ctx}/admin/auth/rolePermission"><%=RolePermission.TABLE_ALIAS%>管理</a> </div>
    <!--end当前位置-->
    <!--查询-->
    <jsp:include page="search.jsp" />
    <!--end查询--> 
  </div>

<!--list header start-->
    <div class="mainHead">
      <div class="headContent">
        <div class="finder_head">
          <div class="sysiconBtnNoIcon mycolumn"><span class="list_set_style" onclick="$('#list_button_rel').toggle();bindCloseAction(['#list_button_rel']);" rel="list_button_rel">列表项</span>»</div>
          <div class="span_2"><input type="checkbox" id="option1" onclick="setAllCheckboxState('items',this.checked)">全选</div>
		  <div class="span_1 hand" title=""> 序号</div>
          <div class="span_3" title=""> 操作</div>
            <!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				 <div class="span_4" title="" sortColumn="ROLE_ID" ><%=RolePermission.ALIAS_ROLE_ID%></div>
				 <div class="span_4" title="" sortColumn="PERMISSION_ID" ><%=RolePermission.ALIAS_PERMISSION_ID%></div>
				 <div class="span_4" title="" sortColumn="ORGANIZATION_ID" ><%=RolePermission.ALIAS_ORGANIZATION_ID%></div>
        </div>
        
        <!--更多列表项层-->
        <div id="list_button_rel" style="position:absolute; z-index:65535; right:0pt; padding:5px; display:none; border:1px solid #ccc;" class="finder_colChanger">
          <div> <span>请打勾选择需要显示在列表上的项</span> </div>
          <div style="overflow-y:auto;" class="x_colbody">
				 <div>
		              <label><input type="checkbox"  value="roleId" name="show_lable_value"><%=RolePermission.ALIAS_ROLE_ID%></label>
           		 </div>
				 <div>
		              <label><input type="checkbox"  value="permissionId" name="show_lable_value"><%=RolePermission.ALIAS_PERMISSION_ID%></label>
           		 </div>
				 <div>
		              <label><input type="checkbox"  value="organizationId" name="show_lable_value"><%=RolePermission.ALIAS_ORGANIZATION_ID%></label>
           		 </div>
          </div>
          <div class="txt_c"> <span class="sysiconBtn buttonsave" onclick="$('#list_button_rel').hide()">保存结果</span> </div>
        </div>
        <!--end更多列表项层--> 
      </div>
    </div>
 	<!--list header end-->
</form>
  <div class="pb_main" style="visibility:visible; opacity:1;">
    <div class="finder">
	
	
	<!--- item list start --->
      <div class="finder_list">
      <!-- item1 start -->
	  <c:forEach items="${page.result}" var="item" varStatus="status">
        <div class="highlight_row row">
        
		<!-- item1 simple info start -->
          <div class="row_line hand" rel="${ctx}/pages/admin/auth/rolePermission/tab.jsp?id=${item.id}"> 

            <div class="span_2"><input type="checkbox" name="items" value="${item.id}" class="sel" tags="null"></div>
			<div class="span_1 hand">${page.thisPageFirstElementNumber + status.index}</div>
            <div class="span_3"><a href="javascript:;"  onclick="show('${ctx}/admin/auth/rolePermission/${item.id}/edit','<%=RolePermission.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</div>
				 <div class="span_4"><c:out value='${item.roleId}'/>&nbsp;</div>
				 <div class="span_4"><c:out value='${item.permissionId}'/>&nbsp;</div>
				 <div class="span_4"><c:out value='${item.organizationId}'/>&nbsp;</div>
          </div>
          <!-- item1 simple info end -->
		  
          <!--item1 info start-->
		   <!--item1 info end-->
        </div>
		</c:forEach>
		<!-- item1 end -->
		<simpletable:pageToolbar page="${page}">
		</simpletable:pageToolbar>
       
        <div style="display:block; height:0px;"></div>
      </div>
	  <!--- item list end --->
    </div>
  </div>
</div>
<script type="text/javascript">
$(".finder_list .row_line").extendDiv("",{
		openCss : "highlight",
		width: 600
});
</script>
</up72:override>
<%@ include file="base.jsp" %>