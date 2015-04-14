<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_wrongRules_edit_form" method="post" action="${ctx}/admin/jtzs/wrongRules/" modelAttribute="wrongRules" >
    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_jtzs_wrongRules_edit_form").validate({
			/*  
			// ajax提交方式
			submitHandler:function(form){   
				form.submit  ();
			},
			*/
			rules: {
				wrongNum:{
					required:true,
					remote:{
						url:'${ctx}/admin/jtzs/wrongRules/isUnique',
						data:{
							id:'${wrongRules.id}',
							role:function(){
								return $("#admin_jtzs_wrongRules_edit_form #role option:selected").val();
							}
						}
					}
				},
				role:{
					required:true,
					remote:{
						url:'${ctx}/admin/jtzs/wrongRules/isUnique',
						data:{
							id:'${wrongRules.id}',
							wrongNum:function(){
								return $("#admin_jtzs_wrongRules_edit_form #wrongNum option:selected").val();
							}
						}
					}
				}
			},
			messages:{
				wrongNum:{
					remote:'相同角色违规次数不能相同',
					required:'请选择违规次数'
				},
				role:{
					remote:'相同角色违规次数不能相同',
				}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
	});
	
	 
	 
</script> 
