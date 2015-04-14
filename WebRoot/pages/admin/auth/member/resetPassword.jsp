<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
  <title><%=AuthUser.TABLE_ALIAS%>维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
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
</up72:override>
<up72:override name="content">
<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
  <tr>
    <td class="up72_mleft"><!--mainleft start-->
      
      <div class="up72_domleft">
        <div class="up72_sidebar skin_sidebar">
          <div class="up72_cnav">
            <ul class="up72_folders">
              <li><a href="${ctx}/admin/auth/member/home">首页</a></li>
              <li><a href="${ctx}/admin/auth/member/editInfo">个人信息</a></li>
              <li><a href="${ctx}/admin/auth/member/resetPassword" class="navover">修改密码</a></li>
              <li><a href="${ctx}/admin/authUser/logout">退出管理</a></li>
            </ul>
          </div>
        </div>
      </div>
      
      <!--mainleft end--></td>
    <td class="up72_mright"><!--mainright start-->
      
      <div class="up72_domright">
        <div id="up72_iframe" class="up72_content">
          <div class="up72_edit">
            <form method="post" id="admin_auth_member_edit_form" name= "admin_auth_member_edit_form" action="${ctx}/admin/auth/member/updatePassword">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label>旧密码:</label></th>
                  <td class="pb_frmtd"><input type="text" value="" name="oldpassword" id="oldpassword" class="input_txt">
                    <span class="required">*</span><font color="red"></font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label>新密码:</label></th>
                  <td class="pb_frmtd"><input type="text" value="" name="newpassword" id="newpassword" class="input_txt">
                    <span class="required">*</span><font color="red"></font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label>重复新密码:</label></th>
                  <td class="pb_frmtd"><input type="text" value="" id="re_newpassword" class="input_txt">
                    <span class="required">*</span><font color="red"></font></td>
                </tr>
              </table>
              <div class="up72_submit">
                  <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
                  <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
                </div>
            </form>
          </div>
        </div>
      </div>
      
      <!--mainright end--></td>
  </tr>
</table>
</up72:override>
<%@ include file="/pages/admin/adminNoPermBase.jsp" %>
