<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=OrganizationProduct.ALIAS_ORGANIZATION_ID%>：</th>
      <td><c:out value='${organizationProduct.organizationId}'/></td>
    </tr>
    <tr>
      <th><%=OrganizationProduct.ALIAS_PRODUCT_ID%>：</th>
      <td><c:out value='${organizationProduct.productCode}'/></td>
    </tr>
  </table>
</div>
