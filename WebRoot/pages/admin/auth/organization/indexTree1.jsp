<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
  <up72:override name="head">
  <title><%=Organization.TABLE_ALIAS%>维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.js"></script>
  <link type="text/css" href="${ctx}/scripts/jq_tree/jquery.treeview.css" rel="stylesheet" />
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <style type="text/css">
.name {
	font-weight: bold;
}
.org {
	color: #006;
}
.workGroup {
	color: #060;
}
.role {
	font-style: italic;
	color: #666;
}
</style>
  <script type="text/javascript">
  	function deleteOrg(id){
  	confirm("确认删除该机构吗？",function (){
  		$.ajax({
  			url : "${ctx}/admin/auth/organization/"+id+"/delete",
  			type : "post",
  			dataType : "json",
  			success : function(jsonDatas){
  				alert("删除成功",3);
  				window.location.reload();
  			}
  		});
  		}
  		);
  	}
  	
  	function deleteWorkGroup(wgid){
  	confirm("确认删除该部门吗？", function(){
  		$.ajax({
  			url : "${ctx}/admin/auth/workGroup/"+wgid+"/delete",
  			type : "post",
  			dataType : "json",
  			success : function(jsonDatas){
  				alert("删除成功");
  				window.location.reload();
  			}
  		});
  		}
  		);
  	}
  	
  	function deleteRole(id){
  	confirm("确认删除该角色吗？", function(){
  		$.ajax({
  			url : "${ctx}/admin/auth/role/"+id+"/delete",
  			type : "post",
  			dataType : "json",
  			success : function(jsonDatas){
  				alert("删除成功");
  				window.location.reload();
  			}
  		});
  		}
  		);
  	}
  </script>
</up72:override>
  <up72:override name="content">
  <!-- 当前位置 -->
<div class="head_content">
  <div class="navBar" style="display:none;"><a class="" href="${ctx}/admin/auth/organization" ><%=Organization.TABLE_ALIAS%>列表</a></div>
</div>
<!-- END 当前位置 -->

<div class="flexigrid">
  <div class="tDiv">
    <div class="tDiv2">
      <div class="fbutton">
        <div><span class="addorder" style="padding-left: 20px;" onclick="show('${ctx}/admin/auth/organization/new?AUTH_PERM_ID=${AUTH_PERM_ID}','添加机构',600);">添加机构</span></div>
      </div>
      <div class="fbutton">
        <div><span class="addorder" style="padding-left: 20px;" onclick="show('${ctx}/admin/auth/role/new?AUTH_PERM_ID=${AUTH_PERM_ID}','添加角色',600);">添加角色</span></div>
      </div>
    </div>
  </div>
</div>
<div class="up72_tabs" id="organizationTab">
  <div class="tabs_con">
  	<div class="tabs_more"><a href="javascript:;" class="tmore_l"><</a><a href="javascript:;" class="tmore_r">></a></div>
    <c:forEach items="${organizationList}" var="organization">
    <span rel="#organization${organization.id}"><a href="javascript:;">${organization.name}</a><a href="javascript:;" onclick="deleteOrg(${organization.id})" class="del" title="删除">X</a></span>
    </c:forEach>
  </div>
</div>
<c:forEach items="${organizationList}" var="organization">
  <div id="organization${organization.id}">
    <div class="up72_tabs" id="info${organization.id}">
      <div class="tabs_con"><span rel="#organization_info${organization.id}"><a href="javascript:;">详细信息</a></span> <span rel="#organization_role${organization.id}"><a href="javascript:;">角色列表</a></span> <span rel="${ctx}/admin/auth/organization/${organization.id}/edit"><a href="javascript:;">编辑机构</a></span></div>
    </div>
    <div id="organization_info${organization.id}">
      <%@ include file="tab_show.jsp" %>
    </div>
    <div id="organization_role${organization.id}">
      <%@ include file="roleList.jsp" %>
    </div>
  </div>
</c:forEach>
<c:forEach items="${organizationList}" var="organization"> 
  <script language="javascript" type="text/javascript">
		$("#info"+${organization.id}).find(".tabs_con span").up72Tabs(
			"#info"+${organization.id},
			{
				selCss : "current",
				showCss : "tabs"
			}
		);
	</script> 
</c:forEach>
<script language="javascript" type="text/javascript">
	$("#organizationTab").find(".tabs_con span").up72Tabs(
		"#organizationTab",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>
</up72:override>
<%@ include file="base.jsp" %>
