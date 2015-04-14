<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
<form:form id="admin_auth_permissionGroup_batchEdit_form" method="put" action="${ctx}/admin/auth/permissionGroup/batchUpdate">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
		<tr class="pb_frmtr">
			<input type="checkbox" name="batchNames" value="name" />
			<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="name" name="name" class="required input_txt" maxlength="30" disabled="disabled" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<input type="checkbox" name="batchNames" value="productCode" />
			<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_PRODUCT_ID%>：</label></th>
			<td class="pb_frmtd">
			<select id="productCode" name="productCode" disabled="disabled">
				<option value="">==请选择==</option>
				<c:forEach items="${productList}" var="items" varStatus="true">
				<option value="${items.code}">${items.name}</option>
				</c:forEach>
			</select>
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<input type="checkbox" name="batchNames" value="description" />
			<th class="pb_frmth"><label><%=PermissionGroup.ALIAS_DESCRIPTION%>：</label></th>
			<td class="pb_frmtd"><input id="description" name="description" class="input_txt" maxlength="100" disabled="disabled" /></td>
		</tr>
	</table>
</form:form>
</div>
<script type="text/javascript">
	doRestBatchEditUI({form:"#admin_auth_permissionGroup_batchEdit_form",batchBox:"batchNames"});
	
	$("#admin_auth_permissionGroup_batchEdit_form").validate();
</script>