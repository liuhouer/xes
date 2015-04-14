<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
function checkProduct(){
	if($("#productCode").val()==""){
		alert("请选择产品！");
		return;
	}
	$("#admin_auth_permissionGroup_edit_form").submit();
}
</script>

<div class="up72_edit">
  <form:form id="admin_auth_permissionGroup_edit_form" method="put" action="${ctx}/admin/auth/permissionGroup/${permissionGroup.id}/updatePermissionGroup" modelAttribute="permissionGroup">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn" value="完成" /></div>
      <div class="btn btn_cel" title="返回"><input type="button" id="close_button" value="返回" onclick="showPro('${ctx}/admin/auth/permissionGroup/${permissionGroup.id}/tabShow?AUTH_PERM_ID=${AUTH_PERM_ID}');" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_permissionGroup_edit_form").validate({
			submitHandler:function(){
				$("#submit_btn").unbind("click");
				saveAndSubmit();
			}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_auth_permissionGroup_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#id").val();
		var AUTH_PERM_ID = $("#AUTH_PERM_ID").val();
		$.ajax({
			url : "${ctx}/admin/auth/permissionGroup/"+id+"/updatePermissionGroup",
			type : "post",
			data : $("#admin_auth_permissionGroup_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					showPro("${ctx}/admin/auth/permissionGroup/"+id+"/tabShow");
				}
				
			}
		});
	}
</script>