<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.treeview.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/permtree.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/scripts/permtree/jquery.treeview.css" />
<!--
<div id="sidetreecontrol" style="margin-top:20px;"><a href="#">关闭</a> | <a href="#">展开</a></div>
-->
<form action="${ctx}/admin/auth/role/${role.id}/assignPerm" id="op_role_permission_form" name="op_role_permission_form" method="post">
  <input type="hidden" name="return_url" id="return_url" value="redirect:${ctx}/pages/admin/auth/product/tab.jsp?id=${role.productCode}&AUTH_PERM_ID=${AUTH_PERM_ID}" />
  <input type="hidden" name="AUTH_PERM_ID" id="AUTH_PERM_ID" value="${AUTH_PERM_ID}">
   <input type="hidden" name="id" id="id" value="${role.id}">
   <input type="hidden" name="productId" id="productId" value="${productId}">
   <input type="hidden" name="productCode" id="productCode" value="${role.productCode}">
  <table cellpadding="0" cellspacing="0" width="100%" style="margin-top:10px">
    <tr>
      <td width="33%" valign="top"><%@include file="include/menuPermTree.jsp" %></td>
      <td width="33%" valign="top"><%@include file="include/optionPermTree.jsp" %></td>
      <td width="33%" valign="top"><%@include file="include/tabPermTree.jsp" %></td>
    </tr>
  </table>
  <div class="up72_submit">
     <div class="btn btn_sub" title="完成"><input type="button" onclick="saveAndSubmit();" id="" name="" value="完成" /></div>
     <div class="btn btn_cel" title="返回"><input type="button" id="close_button" value="返回" onclick="closeBox()" /></div>
   </div>
</form>
<script type="text/javascript">
//菜单权限  menuPermBox  permMenuIds
$("#menuPermtree").treeview({
	control: "#menuCtrl"
});
//操作权限  optionPermBox   permOptionIds
$("#optionPermtree").treeview({
	control: "#optionCtrl"
});
//选项卡权限  tagPermBox   permTagIds
$("#tagPermtree").treeview({
	control: "#tagCtrl"
});

$("#menuPermtree").permtree({checked:{box:'menuPermIds',array:'${permMenuIds}'}});
$("#optionPermtree").permtree({checked:{box:'optionPermIds',array:'${permOptionIds}'}});
$("#tagPermtree").permtree({checked:{box:'tagPermIds',array:'${permTagIds}'}});
function saveAndSubmit(){
		var id = $("#id").val();
		var productId = $("#productId").val();
		var productCode = $("#productCode").val();
		var AUTH_PERM_ID = $("#AUTH_PERM_ID").val();
		$.ajax({
			url : "${ctx}/admin/auth/role/"+id+"/assignPermission",
			type : "post",
			data : $("#op_role_permission_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					closeBox();
					alert("更新成功");
					//showPro("${ctx}/admin/auth/role/"+id+"/proPermission?productCode="+productCode+"&productId="+productId+"&AUTH_PERM_ID=${AUTH_PERM_ID}");
				}
				
			}
		});
}
</script>