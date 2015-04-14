<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_organization_tab_edit_form" method="put" action="${ctx}/admin/auth/organization/${organization.id}" modelAttribute="organization">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" name="return_url" id="return_url" value="redirect:/pages/admin/auth/organization/tab.jsp?id=${organization.id}" />
      <input type="hidden" id="id" name="id" value="${organization.id}"/>
      <input type="hidden" id="olevel" name="olevel" value="1" />
      <input type="hidden" id="parent" name="parent" value="1" />
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Organization.ALIAS_NAME%>：</label></th>
        <td class="pb_frmtd"><form:input path="name" id="name" class="required input_txt" maxlength="19" />
          <span class="required">*</span><font color='red'>
          <form:errors path="name"/>
          </font></td>
      </tr>
      <!--
        <tr class="pb_frmtr">
            <th class="pb_frmth"><label><%=Organization.ALIAS_PARENT%>：</label></th>
            <td class="pb_frmtd"><form:input path="parent" id="parent" class="digits input_txt" maxlength="19" /><font color='red'><form:errors path="parent"/></font></td>
        </tr>
      -->
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Organization.ALIAS_DOMAIN%>：</label></th>
        <td class="pb_frmtd"><form:input path="domain" id="domain" class="required input_txt" maxlength="125" />
          <span class="required">*</span><font color='red'>
          <form:errors path="domain"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Organization.ALIAS_ENABLED%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="enabled" value="1" label="启用" checked="checked"/>
          <form:radiobutton path="enabled" value="0" label="禁用"/></td>
      </tr>
      
      <tr class="pb_frmtr">
		<th class="pb_frmth"><label>用户职能组:</label></th>	
		<td class="pb_frmtd">
		 <c:forEach items="${productList}" var="items" varStatus="statusPro">
			<span style="padding-right:10px">
			<input type="checkBox" 
			<c:forEach items="${organization.productList}" var="opItm" varStatus="statusOp">
				<c:if test="${opItm.code==items.code}">checked="checked"</c:if>
			</c:forEach>
			name="productIds" value="${items.code}" />${items.name }
			</span>
		 </c:forEach>
		</td>
	</tr>
      
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Organization.ALIAS_REMARK%>：</label></th>
        <td class="pb_frmtd"><form:textarea rols="5" cols="25" path="remark" id="remark" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
          <font color='red'>
          <form:errors path="remark"/>
          </font></td>
      </tr>
      <!--
    <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=Organization.ALIAS_OLEVEL%>：</label></th>
        <td class="pb_frmtd"><form:input path="olevel" id="olevel" class="required digits input_txt" maxlength="10" /><span class="required">*</span><font color='red'><form:errors path="olevel"/></font></td>
    </tr>
    -->
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn" name="submit_btn" value="完成" /></div>
      <div class="btn btn_cel" title="返回"><input type="button" id="close_button" value="返回" onclick="showPro('${ctx}/admin/auth/organization/${organization.id }/tabShow');" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_organization_tab_edit_form").validate({
			submitHandler:function(){
				$("#submit_btn").unbind("click");
				saveAndSubmit();
			}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_auth_organization_tab_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#id").val();
		var AUTH_PERM_ID = $("#AUTH_PERM_ID").val();
		$.ajax({
			url : "${ctx}/admin/auth/organization/"+id+"/updateOrganization",
			type : "post",
			data : $("#admin_auth_organization_tab_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					showPro("${ctx}/admin/auth/organization/"+id+"/tabShow");
				}
				
			}
		});
	}
</script>
