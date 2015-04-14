<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${ctx}/scripts/rowsel.js"></script>
<script type="text/javascript">
	function chooseWorkGroup(dom){
		if(isNull($("#organizationId").val())){
			alert("请先选择机构");
			return ;
		}
		var url = "${ctx}/admin/auth/role/workGroup?orgId="+$("#organizationId").val()+"&id="+$("#admin_auth_role_tab_edit_form").find("input[id='id']").val();
		
		showSelectPage(dom,{url:url,title:'选择用户组',selBox:'workGroupBox',selList:"#role_workGroupSel .selrow",selId:"#workGroupId",selName:"#workGroupName"});
	}
</script>
<style type="text/css">
.overcss {
	color: #FF0000;
	background: #999999;
}
.clickcss {
	background: #006600;
	color: #000099;
}
</style>
<form:form id="admin_auth_role_tab_edit_form" method="put" action="${ctx}/admin/auth/role/${role.id}" modelAttribute="role">

<div class="up72_edit">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
    <input type="hidden" name="return_url" id="return_url" value="redirect:/pages/admin/auth/product/tab.jsp?id=${productCode}" />
    <input type="hidden" id="id" name="id" value="${role.id}"/>
    <input type="hidden" id="productId" name="productId" value="${productId}"/>
    <form:hidden path="productCode" id="productCode" />
    <!-- 角色是否为系统默认角色判断处理开始 -->
    <input type="hidden" name="organizationId" id="organizationId" value="0" />
    <input type="hidden" name="workGroupId" id="workGroupId" value="0" />
    <!-- 角色是否为系统默认角色判断处理结束 -->
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=Role.ALIAS_NAME%>:</label></th>
      <td class="pb_frmtd"><form:input path="name" id="name" class="required input_txt" maxlength="255" />
        <span class="required">*</span><font color='red'>
        <form:errors path="name"/>
        </font></td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=Role.ALIAS_ENABLED%>:</label></th>
      <td class="pb_frmtd"><form:radiobutton path="enabled" value="1" class="required" checked="checked" />
        启用
        <form:radiobutton path="enabled" value="0" class="required" />
        禁用 </td>
    </tr>
    <tr class="pb_frmtr">
      <th class="pb_frmth"><label><%=Role.ALIAS_DESCRIPTION%>：</label></th>
      <td class="pb_frmtd"><form:textarea rows="5" cols="25" path="description" id="description" class="input_txt" maxlength="100" />
        <font color='red'>
        <form:errors path="description"/>
        </font></td>
    </tr>
  </table>
  <div class="up72_submit">
     <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn"  name="submit_btn" value="完成" /></div>
     <div class="btn btn_cel" title="返回"><input type="button" id="close_button" value="返回" onclick="showPro('${ctx}/admin/auth/role/${role.id }/proTabShow?orgId=${orgId }');" /></div>
   </div>
</div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_role_tab_edit_form").validate({
			submitHandler:function(){
				$("#submit_btn").unbind("click");
				saveAndSubmit();
			}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_auth_role_tab_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#id").val();
		var productId = $("#productId").val();
		var AUTH_PERM_ID = $("#AUTH_PERM_ID").val();
		$.ajax({
			url : "${ctx}/admin/auth/role/"+id+"/updateRole",
			type : "post",
			data : $("#admin_auth_role_tab_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					showPro("${ctx}/admin/auth/role/"+id+"/proTabShow?productId="+productId+"&AUTH_PERM_ID=${AUTH_PERM_ID}");
				}
				
			}
		});
	}
</script> 
