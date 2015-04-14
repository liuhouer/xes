<%@page import="com.up72.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_TABLE%>：</label></th>
			<td class="pb_frmtd"><input id="changTable" class="input_txt" maxlength="256" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_ID%>：</label></th>
			<td class="pb_frmtd"><input id="changId" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_TIME%>：</label></th>
			<td class="pb_frmtd"><input onclick="WdatePicker({dateFmt:'<%=DataChangLog.FORMAT_CHANG_TIME%>'})" id="changTimeString" name="changTimeString"  maxlength="0" class="input_txt" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_TYPE%>：</label></th>
			<td class="pb_frmtd"><input id="changType" class="input_txt" maxlength="50" /></td>
		</tr>
	</table>
</div>