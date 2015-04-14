<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_auth_role_edit_form" method="post" action="${ctx}/admin/auth/role" modelAttribute="role" >
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
			// 加载校验组件
		$("#admin_auth_role_edit_form").validate({
			// ajax提交方式
			submitHandler:function(form){   
				$("#submitButton").unbind("click");
				submitRecommendForm();
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
		// 绑定提交事件
		$("#submitButton").bind("click",function(){
			$("#admin_auth_role_edit_form").submit();
		});
	});
	function submitRecommendForm(){
		alert("提交服务器处理中，请等待。<img style='vertical-align:bottom;' src='/scripts/weebox/img/common_loading.gif' width='30' height='30' />");
		$.ajax({
			url : "${ctx}/admin/auth/role",
			type : "post",
			data : $("#admin_auth_role_edit_form").serialize(),
			dataType : "json",
			success : function(jsondatas){
				var organizationId=$("#organizationId").val();
				var workGroupId=$("#workGroupId").val();
				//hiddenAllBox();
				closeAllBox();
				if(jsondatas.status=='success'){
					alert(decodeURIComponent(jsondatas.message));
					
					<c:if test="${parentType == ''}">
					window.parent.location.reload();
					</c:if>
					
					<c:if test="${parentType == 'org'}">
					window.parent.getClass("organization_tab3","${ctx}/admin/auth/organization/"+organizationId+"/roleList");
					</c:if>
					
					<c:if test="${parentType == 'wg'}">
					window.parent.getClass("work_group_tab3","${ctx}/admin/auth/workGroup/"+workGroupId+"/roleList");
					</c:if>
					
					
				}else if(jsondatas.status=='error'){
					alert("提交失败,"+decodeURIComponent(jsondatas.message));
				}else{
					alert("系统未知异常，请刷新页面重试。");
				}
			},
			error : function(){
				alert("请求错误，请检查网络。");
			}
		});
	}
</script> 
