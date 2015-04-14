<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=WorkGroup.ALIAS_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="name" class="required input_txt" maxlength="125" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=WorkGroup.ALIAS_ADD_TIME%>：</label></th>
			<td class="pb_frmtd"><input id="addTime" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=WorkGroup.ALIAS_REMARK%>：</label></th>
			<td class="pb_frmtd"><input id="remark" class="input_txt" maxlength="65535" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=WorkGroup.ALIAS_ENABLED%>：</label></th>
			<td class="pb_frmtd"><input id="enabled" class="required digits input_txt" maxlength="3" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=WorkGroup.ALIAS_ORGANIZATION_ID%>：</label></th>
			<td class="pb_frmtd"><input id="organizationId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=WorkGroup.ALIAS_DEPT_LEVEL%>：</label></th>
			<td class="pb_frmtd"><input id="deptLevel" class="required digits input_txt" maxlength="10" />
		<span class="required">*</span>
		</td>
		</tr>
	</table>
</div>