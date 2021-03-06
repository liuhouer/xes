<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_permissionGroup_tab_edit_form" method="put" action="${ctx}/admin/auth/permissionGroup/${permissionGroup.id}" modelAttribute="permissionGroup">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${permissionGroup.id}"/>
      <input type="hidden" name="return_url" id="return_url" value="redirect:${ctx}/pages/admin/auth/permissionGroup/tab.jsp?id=${permissionGroupCode}&productCode=${productCode}" />
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=PermissionGroup.ALIAS_NAME%>：</label></th>
        <td class="pb_frmtd"><form:input path="name" id="name" class="required input_txt" maxlength="30" />
          <span class="required">*</span><font color='red'>
          <form:errors path="name"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=PermissionGroup.ALIAS_PRODUCT_ID%>：</label></th>
        <td class="pb_frmtd"><form:select path="productCode" id="productCode">
            <form:option value="">==请选择==</form:option>
            <c:forEach items="${productList}" var="items" varStatus="true">
              <form:option value="${items.id}">${items.name}</form:option>
            </c:forEach>
          </form:select>
          <span class="required">*</span><font color='red'>
          <form:errors path="productCode"/>
          </font></td>
      </tr>
      <c:if test="${null!=permissionGroup && null!=permissionGroup.id && permissionGroup.id>0}">
        <tr class="pb_frmtr">
          <th class="pb_frmth"><label><%=PermissionGroup.ALIAS_CODE%>:</label></th>
          <td class="pb_frmtd"><form:input path="code" id="code" readonly="readonly" class="input_txt" maxlength="100" />
            <font color='red'>
            <form:errors path="code"/>
            </font></td>
        </tr>
      </c:if>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=PermissionGroup.ALIAS_DESCRIPTION%>：</label></th>
        <td class="pb_frmtd"><form:textarea path="description" id="description" class="input_txt" cols="25" rows="5" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
          <font color='red'>
          <form:errors path="description"/>
          </font></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_permissionGroup_tab_edit_form").validate();	
	});
</script> 
