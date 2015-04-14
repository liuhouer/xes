<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_area_edit_form" method="put" action="${ctx}/admin/jtzs/area/${area.id}" modelAttribute="area">
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
		$("#admin_jtzs_area_edit_form").validate({
			/*  
			// ajax提交方式
			submitHandler:function(form){   
				form.submit();
			},
			*/
			rules: {
				name:{
					required:true,
					byteMax:50,
					remote:{
						url:'${ctx}/admin/jtzs/area/isUnique',
						data:{
							id:'${city.id}',
							cityId:function(){
								return $("#admin_jtzs_area_edit_form #cityId option:selected").val();
							}
						}
					}
				}
			},
			messages:{
				name:{
				 remote:'内容不能重复',
				 required:'请填写内容',
				 byteMax:'不能大于50字'
				}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
	});
</script>