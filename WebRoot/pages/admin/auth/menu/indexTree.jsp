<%@page import="com.up72.auth.model.*" %>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=Menu.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.js"></script>
	<link type="text/css" href="${ctx}/scripts/jq_tree/jquery.treeview.css" rel="stylesheet" />
</up72:override>

<up72:override name="content">

<!--当前位置-->
  <div class="head_content">
   <div class="navBar" style="display: none;"> » <a class="" href="${ctx}/admin/auth/menu/"><%=Menu.TABLE_ALIAS%>管理</a> </div>
  </div>
<!--end当前位置--> 


<div class="flexigrid">
    <div class="tDiv">
      <div class="tDiv2">
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="show('${ctx}/admin/auth/product/new?AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Product.TABLE_ALIAS%>添加',600);">添加产品</span></div>
        </div>
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="show('${ctx}/admin/auth/permissionGroup/new?AUTH_PERM_ID=${AUTH_PERM_ID}','<%=PermissionGroup.TABLE_ALIAS%>添加',600);">添加权限组</span></div>
        </div>
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="show('${ctx}/admin/auth/permission/new?AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Permission.TABLE_ALIAS%>添加',600);">添加权限</span></div>
        </div>
        <div class="fbutton">
          <div><span class="addorder" style="padding-left: 20px;" onclick="show('${ctx}/admin/auth/role/newProductRole?AUTH_PERM_ID=${AUTH_PERM_ID}','<%=Role.TABLE_ALIAS%>添加',600);">添加角色</span></div>
        </div>
      </div>
    </div>
</div>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="up72_treeperm">
    <tr>
      <td class="up72_filetree" valign="top" style="width:250px;">
        <div class="filetree_scr">
          <div class="filetree">
            <form id="admin_auth_product_page_form" name="admin_auth_product_page_form">
                <div id="treecontrol">
                    <a title="" href="#">关闭全部</a>
                    <a title="" href="#">展开全部</a>
                </div>
                <ul id="menu_tree" class="filetree">
                </ul>
            </form>
        </div>
        </div>
      </td>
      <td><iframe style="border:none; height:750px; width:100%;" id="category_frame" name="category_frame" src="${ctx}/admin/cms/category/dashboard"></iframe></td>
    </tr>
</table>

<script type="text/javascript">
$("#menu_tree").treeview({
	url: "${ctx}/admin/auth/menu/tree",
	control: "#treecontrol"
});
</script>

</up72:override>
<%@ include file="base.jsp" %>