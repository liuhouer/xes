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
<div class="up72_tabs">
<div class="tabs_con"> 
<div id="member_roleSel">
<table border="1px" width="90%" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30%"><%=Role.ALIAS_NAME%></td>
    <td><%=Role.ALIAS_WORK_GROUP_ID%></td>
  </tr>
  <c:forEach items="${roleList}" var="item" varStatus="status">
    <tr class="selrow">
      <td width="30%">${item.name}&nbsp;</td>
      <td>${item.workGroup.name}&nbsp;</td>
	  <input style="display:none" type="radio" name="roleBox" value="${item.id}" <c:if test="${item.id == roleId}"> checked="checked"</c:if> />
	  <input type="hidden" value="${item.name}" />
    </tr>
  </c:forEach>
  <c:forEach items="${workGroup.productList}" var="product" varStatus="status">
	<c:forEach items="${product.roleList}" var="item" varStatus="status">
		<tr class="selrow">
		  <td width="30%">${item.name}&nbsp;
		  <input style="" type="radio" name="roleBox" value="${item.id}" <c:if test="${item.id == roleId}"> checked="checked"</c:if> />
		  <input type="hidden" value="${item.name}" /></td>
		  <td>${productItem.workGroup.name}&nbsp;</td>
		</tr>
	</c:forEach>
  </c:forEach>
</table>
</div>
</div>
</div>
<script type="text/javascript">
$("#member_roleSel .selrow").rowsel({overCss:"overcss",clickCss:"clickcss"});
</script>