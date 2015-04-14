<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Product.ALIAS_NAME%>：</th>
      <td><c:out value='${product.name}'/></td>
    </tr>
    <tr>
      <th><%=Product.ALIAS_CODE%>：</th>
      <td><c:out value='${product.code}'/></td>
    </tr>
    <tr>
      <th><%=Product.ALIAS_IMG_PATH%>：</th>
      <td><c:if test="${null!=product.imgPath && product.imgPath!=''}"></c:if>
        <img src="<c:out value='${product.imgPath}'/>" /></td>
    </tr>
    <tr>
      <th><%=Product.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${product.description}'/></td>
    </tr>
  </table>
  <div class="up72_submit">
  	  <div class="btn btn_sub" title="编辑"><input type="button" onclick="showPro('${ctx}/admin/auth/product/${product.id}/edit?AUTH_PERM_ID=${AUTH_PERM_ID}');$('.tabs').hide();"  value="编辑" /></div>
      <div class="btn btn_sub" title="删除"><input type="button" onclick="deletePro(${product.id});"  value="删除" /></div>
    </div>
</div>
