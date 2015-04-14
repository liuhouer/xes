<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.bruce.util.TokenUtil"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_auth_product_edit_form" method="post" action="${ctx}/admin/auth/product" modelAttribute="product" >
  	<%=TokenUtil.getInstance().writeHidden(request, response) %>
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
		$("#admin_auth_product_edit_form").validate({
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
			$("#admin_auth_product_edit_form").submit();
		});
	});
	function submitRecommendForm(){
		alert("提交服务器处理中，请等待。<img style='vertical-align:bottom;' src='/scripts/weebox/img/common_loading.gif' width='30' height='30' />");
		$.ajax({
			url : "${ctx}/admin/auth/product",
			type : "post",
			data : $("#admin_auth_product_edit_form").serialize(),
			dataType : "json",
			success : function(jsondatas){
				hiddenAllBox();
				if(jsondatas.status=='success'){
					alert(decodeURIComponent(jsondatas.message),3,function(){
		  			  window.location.reload();
					});
				}else if(jsondatas.status=='formError')
				{
					window.location.href="${ctx}/admin/formSubmitError?referer="+jsondatas.referer;
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
