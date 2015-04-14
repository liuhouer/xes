<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Role.ALIAS_NAME%>：</th>
      <td><c:out value='${role.name}'/></td>
    </tr>
    <!-- 角色是否为系统默认角色判断处理开始 -->
    <input type="hidden" name="organizationId" id="organizationId" value="0" />
    <input type="hidden" name="workGroupId" id="workGroupId" value="0" />
    <!-- 角色是否为系统默认角色判断处理结束 -->
    <tr>
      <th><%=Role.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${role.enabled == 1}">启用</c:when>
          <c:when test="${role.enabled == 0}">禁用</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=Role.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${role.description}'/></td>
    </tr>
  </table>
   <div class="up72_submit">
     <div class="btn btn_sub" title="权限"><input type="button" id="" name="" onclick="show('${ctx}/admin/auth/role/${role.id }/proPermission?productCode=${role.productCode }&productId=${productId }&AUTH_PERM_ID=${AUTH_PERM_ID}&orgId=${orgId}','编辑权限')" value="权限" /></div>
     <div class="btn btn_sub" title="编辑"><input type="button" id="" name="" onclick="showPro('${ctx}/admin/auth/role/${role.id }/proTabEdit?productCode=${role.productCode }&productId=${productId }&AUTH_PERM_ID=${AUTH_PERM_ID}&orgId=${orgId}')" value="编辑" /></div>
     <div class="btn btn_sub" title="删除"><input type="button" id="" name="" onclick="deleteRole(${role.id },'org',${orgId});" value="删除" /></div>
     <div class="btn btn_cel" title="返回"><input type="button" id="" value="返回" onclick="showPro('${ctx}/admin/auth/organization/${orgId}/roleList')" /></div>
   </div>
</div>
