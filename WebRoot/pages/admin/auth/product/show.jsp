<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Product.ALIAS_NAME%>：</th>
      <td><c:out value='${product.name}'/></td>
      <th><%=Product.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${product.description}'/></td>
    </tr>
  </table>
</div>
