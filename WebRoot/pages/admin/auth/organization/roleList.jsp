<%@ page contentType="text/html;charset=UTF-8" %>

<form id="admin_auth_role_page_form" name="admin_auth_role_page_form">
  <table id="gridTable${organization.id}">
    <thead>
      <tr>
        <th width="180">角色名称</th>
        <th width="150">所属机构</th>
        <th width="100">是否启用</th>
        <th width="350">描述</th>
        <th width="100"><label>操作</label></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${organization.roleList}" var="role" varStatus="status">
        <tr>
          <td><c:out value='${role.name}'/>&nbsp;</td>
          <td><c:out value='${role.organization.name}'/>&nbsp;</td>
          <td><c:choose>
              <c:when test="${role.enabled == 1}">启用</c:when>
              <c:when test="${role.enabled == 0}">禁用</c:when>
            </c:choose>
            &nbsp;</td>
          <td><c:out value='${role.description}'/>
            &nbsp;</td>
          <td><a href="javascript:;" onclick="show('${ctx}/pages/admin/auth/role/tab.jsp?id=${role.id}&AUTH_PERM_ID=${AUTH_PERM_ID}','查看角色',600);" class="sysiconBtnNoIcon">查看</a>&nbsp; <a href="javascript:;" onclick="deleteRole(${role.id});" class="sysiconBtnNoIcon">删除</a>&nbsp; </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</form>
<script type="text/javascript">
	// 表格列表处理
	$('#gridTable'+${organization.id}).flexigrid({
		height: 'auto',
	});
</script>