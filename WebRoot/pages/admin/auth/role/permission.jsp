<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/jquery.treeview.js"></script>
<script type="text/javascript" src="${ctx}/scripts/permtree/permtree.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/scripts/permtree/jquery.treeview.css" />
<c:choose>
  <c:when test="${null!=productList && fn:length(productList)>0}">
    <form action="${ctx}/admin/auth/role/${role.id}/assignPerm" id="op_role_permission_form" name="op_role_permission_form" method="post">
      <input type="hidden" name="return_url" id="return_url" value="redirect:${ctx}/admin/auth/role" />
      <input type="hidden" name="AUTH_PERM_ID" id="AUTH_PERM_ID" value="${AUTH_PERM_ID}">
      <table cellpadding="0" cellspacing="0" width="100%" style="margin-top:10px">
        <tr>
          <td width="33%" valign="top"><%@include file="include/menuPermTree.jsp" %></td>
          <td width="33%" valign="top"><%@include file="include/optionPermTree.jsp" %></td>
          <td width="33%" valign="top"><%@include file="include/tabPermTree.jsp" %></td>
        </tr>
      </table>
      <div class="up72_submit">
        <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
        <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
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
</script>
  </c:when>
  <c:otherwise>
    <div style="font-weight:bold;margin-left: 10px;color: #666;">该角色所在用户组，没有分配用户组职能，不能分配权限。</div>
  </c:otherwise>
</c:choose>
