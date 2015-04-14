<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_wrongRules_edit_form" method="put" action="${ctx}/admin/jtzs/wrongRules/${wrongRules.id}" modelAttribute="wrongRules">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
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
				form.subm it();
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
				}
			},
			messages:{
				wrongNum:{
					remote:'违规次数不能重复',
					required:'请选择违规次数'
				}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
	});
</script>