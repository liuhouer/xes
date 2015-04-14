<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8" src="${ctx}/scripts/ckeditor/ckeditor.js"></script>
<div class="up72_edit">
	<form:form id="admin_auth_product_about_edit_form" method="put"
		action="${ctx}/admin/auth/productAbout/${code }/updateProductAbout"
		modelAttribute="productAbout">
		<input type="hidden" name="productCode" id="productCode" value="${code }"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="edit_table">
			<tr class="pb_frmtr">
				<th class="pb_frmth">
					<label>
						标题:
					</label>
				</th>
				<td class="pb_frmtd">
					<input path="title" id="title" name="title" class="required input_txt"
						maxlength="30" value="${productAbout.title }" />
					<span class="required">*</span><font color='red'><form:errors
							path="title" />
					</font>
				</td>
			</tr>
			<tr class="pb_frmtr">
				<th class="pb_frmth">
					<label>
						内容:
					</label>
				</th>
				<td class="pb_frmtd">
					<textarea name="content" id="content" class="ckeditor" style="width:600px;height:300px;visibility:hidden;">${productAbout.content}</textarea>
				</td>
			</tr>
		</table>
		<div class="up72_submit">
			<div class="btn btn_sub" title="完成">
				<input id="submit_btn" type="button" value="完成" />
			</div>
			<div class="btn btn_cel" title="取消">
				<input type="button" id="close_button" value="取消"
					onclick="closeBox();" />
			</div>
		</div>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_product_about_edit_form").validate({
			submitHandler:function(){
				$("#submit_btn").unbind("click");
				saveAndSubmit();
			}
		});	
		$("#submit_btn").bind("click",function(){
			$("#admin_auth_product_about_edit_form").submit();
		});
	});
	function saveAndSubmit(){
		var id = $("#productCode").val();
		$.ajax({
			url : "${ctx}/admin/auth/productAbout/"+id+"/updateProductAbout",
			type : "post",
			data : $("#admin_auth_product_about_edit_form").serialize(),
			dataType : "text",
			success : function (result){
				if(result =="1"){
					alert("更新成功");
				}else{
					alert("更新失败");
				}
				
			}
		});
	}
</script>