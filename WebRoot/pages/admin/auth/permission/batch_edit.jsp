<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_PERMISSION_GROUP_ID%>：</label></th>
			<td class="pb_frmtd"><input id="permissionGroupCode" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="name" class="required input_txt" maxlength="30" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_DESCRIPTION%>：</label></th>
			<td class="pb_frmtd"><input id="description" class="input_txt" maxlength="100" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_URL%>：</label></th>
			<td class="pb_frmtd"><input id="url" class="required url input_txt" maxlength="100" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_ENABLED%>：</label></th>
			<td class="pb_frmtd"><input id="enabled" class="required digits input_txt" maxlength="3" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_ORGANIZATION_ID%>：</label></th>
			<td class="pb_frmtd"><input id="organizationId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_SORT%>：</label></th>
			<td class="pb_frmtd"><input id="sort" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_IS_OPEN%>：</label></th>
			<td class="pb_frmtd"><input id="isOpen" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_FILE_PATH%>：</label></th>
			<td class="pb_frmtd"><input id="filePath" class="input_txt" maxlength="300" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_SEARCH_PATH%>：</label></th>
			<td class="pb_frmtd"><input id="searchPath" class="input_txt" maxlength="300" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_MENU_LEVEL%>：</label></th>
			<td class="pb_frmtd"><input id="menuLevel" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_IS_MENU%>：</label></th>
			<td class="pb_frmtd"><input id="isMenu" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_TYPE%>：</label></th>
			<td class="pb_frmtd"><input id="type" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Permission.ALIAS_MODE_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="modeName" class="required input_txt" maxlength="100" />
		<span class="required">*</span>
		</td>
		</tr>
	</table>
</div>