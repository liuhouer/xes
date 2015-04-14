<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_workGroupMember_tab_edit_form" method="put" action="${ctx}/admin/auth/workGroupMember/${workGroupMember.id}" modelAttribute="workGroupMember">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${workGroupMember.id}"/>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=WorkGroupMember.ALIAS_WORK_GROUP_ID%>：</label></th>
        <td class="pb_frmtd"><form:input path="workGroupId" id="workGroupId" class="required digits input_txt" maxlength="19" />
          <span class="required">*</span><font color='red'>
          <form:errors path="workGroupId"/>
          </font></td>
        <th class="pb_frmth"><label><%=WorkGroupMember.ALIAS_MEMBER_ID%>：</label></th>
        <td class="pb_frmtd"><form:input path="memberId" id="memberId" class="required digits input_txt" maxlength="19" />
          <span class="required">*</span><font color='red'>
          <form:errors path="memberId"/>
          </font></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_workGroupMember_tab_edit_form").validate();	
	});
</script> 
