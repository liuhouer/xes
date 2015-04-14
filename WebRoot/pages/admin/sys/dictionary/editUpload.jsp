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
  <form:form id="admin_upload_edit_form" method="put" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <%@ include file="upload_ form_include.jsp" %>
    </table>
    <div class="edit_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_upload_edit_form").validate({
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
</up72:override>
<%@ include file="base.jsp" %>