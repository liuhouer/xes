<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=SearchHistory.ALIAS_USER_ID%>：</label></th>
			<td class="pb_frmtd"><input id="userId" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=SearchHistory.ALIAS_USER_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="userName" class="input_txt" maxlength="50" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=SearchHistory.ALIAS_TYPE%>：</label></th>
			<td class="pb_frmtd"><input id="type" class="digits input_txt" maxlength="3" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=SearchHistory.ALIAS_KEY_WORDS%>：</label></th>
			<td class="pb_frmtd"><input id="keyWords" class="input_txt" maxlength="200" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=SearchHistory.ALIAS_ADD_TIME%>：</label></th>
			<td class="pb_frmtd"><input id="addTime" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span>
		</td>
		</tr>
	</table>
</div>