<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${rolePermission.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=RolePermission.ALIAS_ROLE_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="roleId" id="roleId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="roleId"/></font>
		</td>
		<th class="pb_frmth"><label><%=RolePermission.ALIAS_PERMISSION_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="permissionId" id="permissionId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="permissionId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=RolePermission.ALIAS_ORGANIZATION_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="organizationId" id="organizationId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="organizationId"/></font>
		</td>
	</tr>	
		