<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_edit">
  <form:form id="admin_auth_member_edit_form" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
		<tr class="pb_frmtr">
          <th class="pb_frmth"><label>旧密码:</label></th>
          <td class="pb_frmtd"><input type="text" value="" name="oldpassword" id="oldpassword" class="{required:true,byteMax:18,messages:{required:'请填写内容',byteMax:'不能大于18字'}} input_txt">
            <span class="required">*</span><font color="red"></font></td>
        </tr>
        <tr class="pb_frmtr">
          <th class="pb_frmth"><label>新密码:</label></th>
          <td class="pb_frmtd"><input type="text" value="" name="newpassword" id="newpassword" class="{required:true,byteMax:18, messages:{required:'请填写内容',byteMax:'不能大于18字'}} input_txt">
            <span class="required">*</span><font color="red"></font></td>
        </tr>
        <tr class="pb_frmtr">
          <th class="pb_frmth"><label>重复新密码:</label></th>
          <td class="pb_frmtd"><input type="text" value="" name="re_newpassword" id="re_newpassword" class="{required:true,byteMax:18, messages:{required:'请填写内容',byteMax:'不能大于18字'}} input_txt">
            <span class="required">*</span><font color="red"></font></td>
        </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
  <script type="text/javascript">     
    $(document).ready(function(){
    	$("#submitButton").bind("click",function(){
	         $.ajax({
	            form :"#admin_auth_member_edit_form",
	  			url : "${ctx}/admin/auth/member/updatePassword",
	  			type : "post",
	  			dataType : "json",
	  			data : $("form").serialize(),
	  			success : function(data){
	  				if(data.status == 'success'){
		  				alert(decodeURIComponent(data.message));
		  				window.location.reload();
	  				}else{
		  				alert(decodeURIComponent(data.message));
	  				}
	  			}
	  		});
  		});
  	});
</script>