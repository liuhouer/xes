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
<div id="role_workGroupSel">
<table border="1px" width="90%" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30%"><%=WorkGroup.ALIAS_NAME%></td>
    <td><%=WorkGroup.ALIAS_ORGANIZATION_ID%></td>
	<!--
	<td>操作</td>
	-->
  </tr>
  <c:forEach items="${workGroupList}" var="item" varStatus="status">
    <tr class="selrow">
      <td width="40%">${item.name}&nbsp;</td>
      <td>${item.organization.name}&nbsp;</td>
	  <input style="display:none" type="radio" name="workGroupBox" value="${item.id}" />
	  <input type="hidden" value="${item.name}" />
    </tr>
  </c:forEach>
</table>
</div>
<script type="text/javascript">
$("#role_workGroupSel .selrow").rowsel({overCss:"overcss",clickCss:"clickcss"});
</script>