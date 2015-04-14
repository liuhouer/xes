<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Permission.ALIAS_NAME%>：</th>
      <td><c:out value='${permission.name}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_TYPE%>：</th>
      <td><c:choose>
          <c:when test="${permission.type == 1}">菜单</c:when>
          <c:when test="${permission.type == 2}">操作</c:when>
          <c:when test="${permission.type == 3}">选项卡</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_PERMISSION_GROUP_ID%>：</th>
      <td><c:out value='${permission.permissionGroup.name}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_PRODUCT_ID%>：</th>
      <td><c:out value='${permission.product.name}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_URL%>：</th>
      <td><c:out value='${permission.url}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_CODE%>：</th>
      <td><c:out value='${permission.code}'/></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_IMG_PATH%>：</th>
      <td><c:if test="${null!=permission.imgPath && permission.imgPath!=''}"> <img src="<c:out value='${permission.imgPath}'/>" /> </c:if></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${permission.enabled == 1}">启用</c:when>
          <c:when test="${permission.enabled == 0}">禁用</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=Permission.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${permission.description}'/></td>
    </tr>
  </table>
  <div class="up72_submit">
      <div class="btn btn_sub" title="编辑"><input type="button" onclick="showPro('${ctx}/admin/auth/permission/${permission.id}/edit?AUTH_PERM_ID=${AUTH_PERM_ID}');"  value="编辑" /></div>
      <div class="btn btn_sub" title="删除"><input type="button" onclick="deletePermission(${permission.id});"  value="删除" /></div>
    </div>
</div>

