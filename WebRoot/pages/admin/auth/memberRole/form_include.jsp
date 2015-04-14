<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${memberRole.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=MemberRole.ALIAS_MEMBER_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="memberId" id="memberId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="memberId"/></font>
		</td>
		<th class="pb_frmth"><label><%=MemberRole.ALIAS_ROLE_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="roleId" id="roleId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="roleId"/></font>
		</td>
	</tr>	
		