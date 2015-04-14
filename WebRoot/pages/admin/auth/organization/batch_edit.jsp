<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Organization.ALIAS_PARENT%>：</label></th>
			<td class="pb_frmtd"><input id="parent" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Organization.ALIAS_DOMAIN%>：</label></th>
			<td class="pb_frmtd"><input id="domain" class="required input_txt" maxlength="125" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Organization.ALIAS_REMARK%>：</label></th>
			<td class="pb_frmtd"><input id="remark" class="input_txt" maxlength="65535" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Organization.ALIAS_ENABLED%>：</label></th>
			<td class="pb_frmtd"><input id="enabled" class="required digits input_txt" maxlength="3" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Organization.ALIAS_OLEVEL%>：</label></th>
			<td class="pb_frmtd"><input id="olevel" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
	</table>
</div>