<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${workGroupMember.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=WorkGroupMember.ALIAS_WORK_GROUP_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="workGroupId" id="workGroupId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="workGroupId"/></font>
		</td>
		<th class="pb_frmth"><label><%=WorkGroupMember.ALIAS_MEMBER_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="memberId" id="memberId" class="required digits input_txt" maxlength="19" />
		<span class="required">*</span><font color='red'><form:errors path="memberId"/></font>
		</td>
	</tr>	
		