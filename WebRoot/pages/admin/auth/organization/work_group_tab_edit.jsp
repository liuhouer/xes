<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_workgroup_tab_edit_form" method="put" action="${ctx}/admin/auth/workGroup/${workGroup.id}" modelAttribute="workGroup">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${workGroup.id}"/>
      <form:hidden path="addTime" id="addTime" />
      <input type="hidden" name="deptLevel" id="deptLevel" value="${workGroup.deptLevel }"/>
      <input type="hidden" name="parent" id="parent" value="${workGroup.parent }"/>
      <form:hidden path="organizationId" id="organizationId" />
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=WorkGroup.ALIAS_ORGANIZATION_ID%>：</label></th>
        <td class="pb_frmtd"><input disabled="disabled" type="text" id="organizationName" value="${workGroup.organization.name}" />
          <font color='red'>
          <form:errors path="organizationId"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=WorkGroup.ALIAS_NAME%>：</label></th>
        <td class="pb_frmtd"><form:input path="name" id="name" class="required input_txt" maxlength="10" />
          <span class="required">*</span><font color='red'>
          <form:errors path="name"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=WorkGroup.ALIAS_ENABLED%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="enabled" value="1" label="启用" checked="checked"/>
          <form:radiobutton path="enabled" value="0" label="禁用"/>
          <span class="required"></span><font color='red'>
          <form:errors path="enabled"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=WorkGroup.ALIAS_REMARK%>：</label></th>
        <td colspan="2" class="pb_frmtd"><form:textarea cols="80" rows="5" path="remark" id="remark" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
          <font color='red'>
          <form:errors path="remark"/>
          </font></td>
      </tr>
     <!--  <tr class="pb_frmtr">
        <th class="pb_frmth"><label>:</label></th>
        <td class="pb_frmtd"><c:forEach items="${productList}" var="items" varStatus="true"> <span style="padding-right:10px">
            <input type="checkBox" name="productCodes" value="${items.id}" />
            ${items.name } </span> </c:forEach></td>
      </tr> -->
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn" name="submit_btn" value="完成" /></div>
      <div class="btn btn_cel" title="返回"><input type="button" id="close_button" value="返回" onclick="showPro('${ctx}/admin/auth/organization/${workGroup.id }/workGroupView');" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_workgroup_tab_edit_form").validate({
			submitHandler:function(){
				$("#submit_btn").unbind("click");
				saveAndSubmit();
			}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_auth_workgroup_tab_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#id").val();
		var AUTH_PERM_ID = $("#AUTH_PERM_ID").val();
		$.ajax({
			url : "${ctx}/admin/auth/workGroup/"+id+"/updateWorkGroup",
			type : "post",
			data : $("#admin_auth_workgroup_tab_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					showPro("${ctx}/admin/auth/organization/"+id+"/workGroupView");
				}
				
			}
		});
	}
</script> 
