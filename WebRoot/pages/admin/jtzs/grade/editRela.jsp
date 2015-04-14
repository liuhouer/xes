<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="com.xes.jtzs.model.*"%>

<div class="up72_edit">
  <form:form id="admin_jtzs_subject_edit_form" method="post" action="${ctx}/admin/jtzs/grade/${grade.id}/upstatus" modelAttribute="grade">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
     <input type="hidden" id="id" name="id" value="${grade.id}" />
     <input type="hidden" id="sort" name="sort" value="${grade.sort}" />

      <tr class="frmtr">
	   <th class="frmth"><label><font size="4" ><c:out value="${grade.name}"></c:out> </font>  </label></th>
      </tr>

		<c:forEach items="${gradeSubjectList}" var="itemss">
	    <tr class="frmtr">
	         <td>
	         <c:out value="${itemss.subject.name} "></c:out>
 			 </td>
			
			<td> <input type="checkbox" id="subjectId" name="subjectId" <c:if test="${itemss.status == 1}">checked="checked"</c:if>   value="${itemss.subjectId}"/> </td>
	    </tr>
	
		</c:forEach>

    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_jtzs_subject_edit_form").validate({
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