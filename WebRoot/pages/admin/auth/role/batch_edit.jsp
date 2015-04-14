<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Role.ALIAS_WORK_GROUP_ID%>：</label></th>
			<td class="pb_frmtd"><input id="permissionGroupCode" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Role.ALIAS_DESCRIPTION%>：</label></th>
			<td class="pb_frmtd"><input id="description" class="input_txt" maxlength="100" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Role.ALIAS_REMARK%>：</label></th>
			<td class="pb_frmtd"><input id="remark" class="input_txt" maxlength="65535" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Role.ALIAS_ENABLED%>：</label></th>
			<td class="pb_frmtd"><input id="enabled" class="required digits input_txt" maxlength="3" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Role.ALIAS_ORGANIZATION_ID%>：</label></th>
			<td class="pb_frmtd"><input id="organizationId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
	</table>
</div>