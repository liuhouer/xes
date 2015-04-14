<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Product.ALIAS_NAME%>：</label></th>
			<td class="pb_frmtd"><input id="name" class="required input_txt" maxlength="30" />
		<span class="required">*</span>
		</td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=Product.ALIAS_DESCRIPTION%>：</label></th>
			<td class="pb_frmtd"><input id="description" class="input_txt" maxlength="100" /></td>
		</tr>
	</table>
</div>