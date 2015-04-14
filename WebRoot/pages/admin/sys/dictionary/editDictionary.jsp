<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
  <title></title>
  
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
<div class="up72_edit">
  <form:form id="admin_image_edit_form" method="put" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="setting_ form_include.jsp" %>
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
				var inputValues =$("input[type = 'text']");
					var items = "";
					$(inputValues).each(
						function(i,obj){
							if(i!=0){items +="&";}
							items +="items"+"="+ $(obj).val()+"_"+$(obj).attr("id");
						}
					);
				 $.ajax({
					url : "${ctx}/admin/sys/dictionary/updateDictionary",
					type : "post",
					dataType : "json",
					data: "items =" +items,
					success : function (jsondatas){
							if(jsondatas.status=='success'){
								alert("更新成功！",3,function(){
									window.location.reload();
						  		});
							}else if(jsondatas.status=='error'){
								alert("提交失败,"+decodeURIComponent(jsondatas.message));
							}else{
								alert("系统未知异常，请刷新页面重试。");
							}
							
		  				},error : function(){
								alert("请求错误，请检查网络。");
						}
				});
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
		
	});

</script>
</up72:override>
<%@ include file="base.jsp" %>