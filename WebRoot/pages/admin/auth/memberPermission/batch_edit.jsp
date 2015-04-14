<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=MemberPermission.ALIAS_MEMBER_ID%>：</label></th>
			<td class="pb_frmtd"><input id="memberId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=MemberPermission.ALIAS_PERMISSION_ID%>：</label></th>
			<td class="pb_frmtd"><input id="permissionId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=MemberPermission.ALIAS_STATE%>：</label></th>
			<td class="pb_frmtd"><input id="state" class="required digits input_txt" maxlength="3" />
		<span class="required">*</span>
		</td>
		</tr>
	</table>
</div>