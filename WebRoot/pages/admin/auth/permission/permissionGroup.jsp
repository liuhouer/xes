<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
.overcss {
color:#FF0000;
background:#999999;
}

.clickcss{
background:#006600;
color:#000099;
}
</style>
<div id="permission_permissionGroupSel">
<table border="1px" width="90%" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30%"><%=PermissionGroup.ALIAS_NAME%></td>
    <td><%=PermissionGroup.ALIAS_PRODUCT_ID%></td>
  </tr>
  <c:forEach items="${page.result}" var="item" varStatus="status">
    <tr class="selrow" <c:if test="${item.id == permissionGroupCode}"> bgcolor="#666666"</c:if>>
      <td width="30%">${item.name}&nbsp;</td>
      <td>${item.product.name}&nbsp;</td>
	  <input style="display:none" type="radio" name="permissionGroupBox" value="${item.id}" />
	  <input type="hidden" value="${item.name}" />
    </tr>
  </c:forEach>
</table>
</div>
<script type="text/javascript">
$("#permission_permissionGroupSel .selrow").rowsel({overCss:"overcss",clickCss:"clickcss"});
</script>