<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${memberPermission.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=MemberPermission.ALIAS_MEMBER_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="memberId" id="memberId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="memberId"/></font>
		</td>
		<th class="pb_frmth"><label><%=MemberPermission.ALIAS_PERMISSION_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="permissionId" id="permissionId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="permissionId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=MemberPermission.ALIAS_STATE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="state" id="state" class="required digits input_txt" maxlength="3" />
		<span class="required">*</span><font color='red'><form:errors path="state"/></font>
		</td>
	</tr>	
		