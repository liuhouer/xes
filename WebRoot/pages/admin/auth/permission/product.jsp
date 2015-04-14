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
<div id="permission_productSel">
<table border="1px" width="90%" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30%"><%=Product.ALIAS_NAME%></td>
    <td><%=Product.ALIAS_DESCRIPTION%></td>
  </tr>
  <c:forEach items="${page.result}" var="item" varStatus="status">
    <tr class="selrow" <c:if test="${item.code == productCode}"> bgcolor="#666666"</c:if>>
      <td width="30%">${item.name}&nbsp;</td>
      <td>${item.description}&nbsp;</td>
	  <input style="display:none" type="radio" name="productBox" value="${item.code}" />
	  <input type="hidden" value="${item.name}" />
    </tr>
  </c:forEach>
</table>
</div>
<script type="text/javascript">
$("#permission_productSel .selrow").rowsel({overCss:"overcss",clickCss:"clickcss"});
</script>