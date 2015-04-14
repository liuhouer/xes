<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.model.Score"%>
<%@page import="com.xes.jtzs.model.ScoreLog"%>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_edit">
  <form:form id="admin_jtzs_score_edit_form" method="put" action="${ctx}/admin/jtzs/score/${score.id}/doAddScore">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${score.id}"/>
		<tr class="frmtr"> 
		  <th class="frmth"><label><%=Score.ALIAS_USER_ROLE%>:</label></th>
		  <td class="frmtd">${score.userRoleStr }</td>
		  </tr>
		<tr class="frmtr"> 
		  <th class="frmth"><label><%=Score.ALIAS_USER_ID%>:</label></th>
		  <td class="frmtd">    
		  	<c:if test="${score.userRole==0}">${score.student.loginName}</c:if>
		  	<c:if test="${score.userRole!=0}">${score.teacher.loginName}</c:if>
		  </td>
		  </tr>
		<tr class="frmtr"> 
		  <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE_TYPE%>:</label></th>
		  <td class="frmtd">
	         <select id="scoreType" name="scoreType" style="width: 120px" class="required" >
                <option value="">请选择</option>
			 	<option value="<%=JTZSConstants.ScoreType.ADD.getIndex()%>"><%=JTZSConstants.ScoreType.ADD.getName()%></option>
			 	<option value="<%=JTZSConstants.ScoreType.DEL.getIndex()%>"><%=JTZSConstants.ScoreType.DEL.getName()%></option>
			 </select>
			 <font color='red'>*<form:errors path="useScore"/></font>
		  </td>
		  </tr>
		<tr class="frmtr"> 
		  <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE%>:</label></th>
		  <td class="frmtd"><input type="text" id="score" name="score" class="{required:true,byteMax:5,digits:true,messages:{required:'不能为空',byteMax:'不能大于5位'}} input_txt"/>
		    <font color='red'>*<form:errors path="useScore"/></font>
		  </td>
		  </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_jtzs_score_edit_form").validate({
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
</script>