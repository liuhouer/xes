<%@page import="com.up72.auth.model.*" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="up72_edit">
  <form:form id="admin_auth_productAbout_tab_edit_form" method="post" action="${ctx}/admin/auth/productAbout/${productAbout.id}/updateProductAbout" modelAttribute="productAbout">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submit_btn" name="submit_btn" onclick="saveAndSubmit()" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_productAbout_tab_edit_form").validate({
			submitHandler:function(form){
				if($.trim($("#title").val()) == ""){
						alert("请输入标题");
						return;
					}
					var editor = CKEDITOR.instances.content.getData();
					if($.trim(editor) == ""){
						alert("请输入内容");
						return;
					}
					$("#content").val(editor+"");
				   $("#submit_btn").unbind("click");
					saveAndSubmit();
			},
			errorPlacement: function(error, element) {
					error.appendTo(element.parent());
				}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_auth_productAbout_tab_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#id").val();
		var AUTH_PERM_ID = $("#AUTH_PERM_ID").val();
		$.ajax({
			url : "${ctx}/admin/auth/productAbout/${productAbout.id}/updateProductAbout",
			type : "post",
			data : $("#admin_auth_productAbout_tab_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					showPro("${ctx }/admin/auth/productAbout/"+id+"/tabShow");
				}
				
			}
		});
	}
</script> 
