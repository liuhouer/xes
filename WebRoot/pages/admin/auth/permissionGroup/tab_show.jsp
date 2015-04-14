<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=PermissionGroup.ALIAS_NAME%>：</th>
      <td><c:out value='${permissionGroup.name}'/></td>
    </tr>
    <tr>
      <th><%=PermissionGroup.ALIAS_PRODUCT_ID%>：</th>
      <td><c:out value='${permissionGroup.product.name}'/></td>
    </tr>
    <tr>
      <th><%=PermissionGroup.ALIAS_CODE%>：</th>
      <td><c:out value='${permissionGroup.code}'/></td>
    </tr>
    <tr>
      <th><%=PermissionGroup.ALIAS_IMG_PATH%>：</th>
      <td><c:if test="${null!=permissionGroup.imgPath&&permissionGroup.imgPath!=''}"> <img src="<c:out value='${permissionGroup.imgPath}'/>" /> </c:if></td>
    </tr>
    <tr>
      <th><%=PermissionGroup.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${permissionGroup.description}'/></td>
    </tr>
  </table>
   <div class="up72_submit">
   	  <div class="btn btn_sub" title="编辑"><input type="button" onclick="showPro('${ctx}/admin/auth/permissionGroup/${permissionGroup.id }/edit?AUTH_PERM_ID=${AUTH_PERM_ID}');"  value="编辑" /></div>
      <div class="btn btn_sub" title="删除"><input type="button" onclick="deletePermissionGroup(${permissionGroup.id});"  value="删除" /></div>
    </div>
</div>
