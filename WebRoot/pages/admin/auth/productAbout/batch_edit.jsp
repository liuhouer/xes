<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
	
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=ProductAbout.ALIAS_PRODUCT_ID%>：</label></th>
			<td class="pb_frmtd"><input id="productId" class="digits input_txt" maxlength="19" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=ProductAbout.ALIAS_TITLE%>：</label></th>
			<td class="pb_frmtd"><input id="title" class="input_txt" maxlength="50" /></td>
		</tr>
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label><%=ProductAbout.ALIAS_CONTENT%>：</label></th>
			<td class="pb_frmtd"><input id="content" class="input_txt" maxlength="65535" /></td>
		</tr>
	</table>
</div>