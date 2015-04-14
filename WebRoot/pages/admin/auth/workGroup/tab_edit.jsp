<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_workgroup_tab_edit_form" method="put" action="${ctx}/admin/auth/workGroup/${workGroup.id}" modelAttribute="workGroup">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${workGroup.id}"/>
      <form:hidden path="addTime" id="addTime" />
      <%@ include file="form_include.jsp"%>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn" name="submit_btn" value="完成" /></div>
      <div class="btn btn_cel" title="返回"><input type="button" id="close_button" value="返回" onclick="showPro('${ctx}/admin/auth/workGroup/${workGroup.id }/view');" /></div>
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
					showPro("${ctx}/admin/auth/workGroup/"+id+"/view");
				}
				
			}
		});
	}
	
function selProduct(){
	var selproductCodes = '${productCodes}';
	if(!isNull(selproductCodes)){
		selproductCodes = eval(selproductCodes);
		var boxes = $("input[name='productCodes']");
		if(isNull(boxes)){
			return ;
		}
		
		for(var j=0;j<selproductCodes.length;j++){
			for(var i=0;i<boxes.length;i++){
				var boxPro = parseInt($(boxes[i]).val());
				if(boxPro == selproductCodes[j]){
					$(boxes[i]).attr("checked","checked");
				}
			}
		}
	}
}
selProduct();
</script> 
