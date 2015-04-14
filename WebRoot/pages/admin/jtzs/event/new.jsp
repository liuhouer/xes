<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_event_edit_form" method="post" action="${ctx}/admin/jtzs/event/" modelAttribute="event" >
    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
      <%@ include file="form_include.jsp" %>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submitButton" onclick="checkQuestion()" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_jtzs_event_edit_form").validate({
			/*  
			// ajax提交方式
			submitHandler:function(form){   
				form.submit();
			},
			*/
			errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}
		});	
	});
	
	
	
	function checkQuestion(){
	
		var i = 0; 
		var index = "";
		var full = "";
		$(".quest").each(function(){
			var value = $(this).val();
			if(value!=null&&value!=""){
				full+=i+",";
			}
			i++;
		});
		if(full!=""){
			var ind = full.substring(0,full.length-1).split(",");
			var lastIndex = ind[ind.length-1];
			var indexx = Number(lastIndex)+1;
			var s = $(".quest");
			var ans = $("#answer").val();

			for(var k=1;k<=7;k++){
			    if(ans==k&& (s[k-1].value==null||s[k-1].value=="")){
			    alert("您没有填写选择的这道题，请先填写问题！");
			    return;
			    }
			}
			for(var j=0;j<lastIndex;j++){
				if(s[j].value==""||s[j].value==null){
					alert("第"+indexx+"个问题前存在内容为空，请添加！");
					return;
				}
			}
		}else{
		      var ans = $("#answer").val();
		      if(ans!=0){
		       alert("您没有填写任何题，不能选择这个答案！");
		       return;
		      }
		}
		
		if(checkTime() == true){
		$("#admin_jtzs_event_edit_form").submit();
		}
	
	}
</script> 
