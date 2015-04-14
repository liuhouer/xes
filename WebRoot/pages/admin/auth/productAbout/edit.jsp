<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="content">
<div class="up72_edit">
	<form:form id="admin_auth_productAbout_edit_form" method="put" action="${ctx}/admin/auth/productAbout/${productAbout.id}/updateProductAbout" modelAttribute="productAbout">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
		<%@ include file="form_include.jsp" %>
		</table>		
		<div class="up72_submit">
          <div class="btn btn_sub" title="完成"><input type="button" id="submitButton" name="submitButton" value="完成" /></div>
          <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
        </div>
	</form:form>
</div>	
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_productAbout_edit_form").validate({
		
				// ajax提交方式
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
					var patrn = /^[-+]?[0-9]+(\.[0-9]+)?$/;
					var sorts = $.trim($("#sortId").val());
					if (sorts != "" && !patrn.exec(sorts)) {
						alert("排序请输入数字");
				  		return;
				 	}
					$("#content").val(editor+"");
					var code = $("#code").val();
					$("#submitButton").unbind("click");
					$.ajax({
						"url" : "${ctx}/admin/auth/productAbout/${productAbout.id}/updateProductAbout",
						"type" : "post",
						"data" : $("#admin_auth_productAbout_edit_form").serialize(),
						"success" : function (data){
							window.parent.hiddenAllBox();
							alert("修改成功");
							window.parent.showPro("${ctx}/admin/auth/productAbout/"+$("#productCode").val()+"/tabIndex");
						}
					});
				},
				errorPlacement: function(error, element) {
					error.appendTo(element.parent());
				}
		});	
		
		$("#submitButton").bind("click",function(){
			$("#admin_auth_productAbout_edit_form").submit();
		});
	});
</script>
</up72:override>
<%@ include file="editBase.jsp" %>
