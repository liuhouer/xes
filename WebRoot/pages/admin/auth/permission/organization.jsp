<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div style="width:400px; height:300px;overflow:scroll; border:#000 solid 1px">
<table border="1px" width="90%" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30%"><%=Organization.ALIAS_NAME%></td>
    <td><%=Organization.ALIAS_DOMAIN%></td>
	<td>操作</td>
  </tr>
  <c:forEach items="${page.result}" var="item" varStatus="status">
    <tr>
      <td width="30%">${item.name}&nbsp;</td>
      <td>${item.domain}&nbsp;</td>
	  <td>
	  <input type="radio" name="organizationBox" value="${item.id}" />
	  <input type="hidden" value="${item.name}" /></td>
    </tr>
  </c:forEach>
</table>
</div>