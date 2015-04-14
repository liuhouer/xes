<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <form:form id="admin_image_edit_form" method="put" action="${ctx}/admin/ask/askQuestion/" modelAttribute="askQuestion">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="image_ form_include.jsp" %>
    </table>
    <div class="edit_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_image_edit_form").validate({
			// ajax提交方式
			submitHandler:function(form){   
				/*$.ajax({
					"url" : "${ctx}/admin/ask/askQuestion/${askQuestion.id}/update",
					"type" : "post",
					dataType : "json",
					"data" : $("#admin_ask_askQuestion_edit_form").serialize(),
					"success" : function (data){
						hiddenAllBox();
						if(data.message=="success"){
							alert("更新成功！",3,function(){
		  						var innerWindow = getInnerFrame(["TabFrame"]);
		  						innerWindow.location.reload();
						  	});
		  				}
		  				if(data.message=="error"){
		  					alert("更新失败！",3,function(){
								var innerWindow = getInnerFrame(["TabFrame"]);
		  						innerWindow.location.reload();
						  	});
		  				}
					}
				});*/
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
		
	});

</script>