<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=WorkGroup.ALIAS_ORGANIZATION_ID%>：</th>
      <td><c:out value='${workGroup.organization.name}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_NAME%>：</th>
      <td><c:out value='${workGroup.name}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${workGroup.enabled == 1}">启用</c:when>
          <c:when test="${workGroup.enabled == 0}">禁用</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_ADD_TIME%>：</th>
      <td><fmt:formatDate value="${workGroup.addTimeDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_REMARK%>：</th>
      <td><c:out value='${workGroup.remark}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_PRODUCT_LIST%>：</th>
      <td><c:forEach items="${workGroup.productList}" var="item"> [
          <c:out value="${item.name}" />
          ]&nbsp;&nbsp; </c:forEach></td>
    </tr>
    <!--
    <tr>
      <th><%=WorkGroup.ALIAS_DEPT_LEVEL%>：</th>
      <td><c:out value='${workGroup.deptLevel}'/></td>
    </tr>
    -->
  </table>
</div>
