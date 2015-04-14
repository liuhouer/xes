<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=ProductAbout.ALIAS_PRODUCT_ID%>：</th>
      <td><c:out value='${productAbout.productId}'/></td>
      <th><%=ProductAbout.ALIAS_TITLE%>：</th>
      <td><c:out value='${productAbout.title}'/></td>
    </tr>
    <tr>
      <th><%=ProductAbout.ALIAS_CONTENT%>：</th>
      <td><c:out value='${productAbout.content}'/></td>
    </tr>
  </table>
</div>
