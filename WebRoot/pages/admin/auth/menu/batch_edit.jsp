<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_PARENT_ID%>：</label></th>
			<td class="pb_frmtd"><input id="parentId" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_PERMISSION_ID%>：</label></th>
			<td class="pb_frmtd"><input id="permissionId" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="name" class="input_txt" maxlength="256" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_ICON%>：</label></th>
			<td class="pb_frmtd"><input id="icon" class="input_txt" maxlength="256" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_SORT_ID%>：</label></th>
			<td class="pb_frmtd"><input id="sortId" class="digits input_txt" maxlength="10" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_LEVEL%>：</label></th>
			<td class="pb_frmtd"><input id="level" class="digits input_txt" maxlength="10" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_ROLE_ID%>：</label></th>
			<td class="pb_frmtd"><input id="roleId" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Menu.ALIAS_ADD_TIME%>：</label></th>
			<td class="pb_frmtd"><input onclick="WdatePicker({dateFmt:'<%=Menu.FORMAT_ADD_TIME%>'})" id="addTimeString" name="addTimeString"  maxlength="0" class="input_txt" /></td>
		</tr>
	</table>
</div>