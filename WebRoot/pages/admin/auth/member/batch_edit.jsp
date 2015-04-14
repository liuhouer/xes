<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
<form:form id="admin_auth_member_batchEdit_form" method="put" action="${ctx}/admin/auth/member/batchUpdate">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <!--<tr class="pb_frmtr">
        <th class="pb_frmth">
		<input type="checkbox" name="batchNames" value="userName" />
          <label><%=AuthUser.ALIAS_USER_NAME%>：</label></th>
        <td class="pb_frmtd"><input id="userName" disabled="disabled" name="userName" class="required input_txt" maxlength="20" />
          <span class="required"></span> </td>
      </tr>-->
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="password" />
          <label><%=AuthUser.ALIAS_PASSWORD%>：</label></th>
        <td class="pb_frmtd"><input name="password" disabled="disabled" id="password" class="{required:true,alnum:true,minlength:6,maxlength:16} required input_txt" maxlength="20" />
          <span class="required"></span> </td>
      </tr>
      <!--<tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="email" />
          <label><%=AuthUser.ALIAS_EMAIL%>：</label></th>
        <td class="pb_frmtd"><input name="email" disabled="disabled" id="email" class="email input_txt" maxlength="25" /></td>
      </tr>-->
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="nickName" />
          <label><%=AuthUser.ALIAS_NICK_NAME%>：</label></th>
        <td class="pb_frmtd"><input name="nickName" disabled="disabled" id="nickName" class="{required:true,cname:true,byteMin:4,byteMax:14} input_txt" maxlength="20" />
          <span class="required"></span> </td>
      </tr>
      <!--<tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="imgPath" />
          <label><%=AuthUser.ALIAS_IMG_PATH%>：</label></th>
        <td class="pb_frmtd"><input name="imgPath" disabled="disabled" id="imgPath" class="input_txt" maxlength="20" />
          <span class="required"></span> </td>
      </tr>-->
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="loginAnswer" />
          <label><%=AuthUser.ALIAS_LOGIN_ANSWER%>：</label></th>
        <td class="pb_frmtd"><input name="loginAnswer" disabled="disabled" id="loginAnswer" class="{required:true,minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" /></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="secques" />
          <label><%=AuthUser.ALIAS_SECQUES%>：</label></th>
        <td class="pb_frmtd"><input name="secques" disabled="disabled" id="secques" class="{required:true,minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" /></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="code" />
          <label><%=AuthUser.ALIAS_CODE%>：</label></th>
        <td class="pb_frmtd">
		<select disabled="disabled" id="code" class="digits input_txt" name="code">
			<option value="2">管理员</option>
			<option value="5">系统管理员</option>
		</select>
          <span class="required"></span> </td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="mobile" />
          <label><%=AuthUser.ALIAS_MOBILE%>：</label></th>
        <td class="pb_frmtd"><input name="mobile" disabled="disabled" id="mobile" class="mobile input_txt" maxlength="15" /></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="problem" />
          <label><%=AuthUser.ALIAS_PROBLEM%>：</label></th>
        <td class="pb_frmtd"><input name="problem" disabled="disabled" id="problem" class="{required:true,minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" /></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="anser" />
          <label><%=AuthUser.ALIAS_ANSER%>：</label></th>
        <td class="pb_frmtd"><input name="anser" disabled="disabled" id="anser" class="{required:true,minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" /></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="enabled" />
          <label><%=AuthUser.ALIAS_ENABLED%>：</label></th>
        <td class="pb_frmtd">
		<select disabled="disabled" id="enabled" class="required digits input_txt" name="enabled">
			<option value="0">禁用</option>
			<option value="1">可用</option>
		</select>
        </td>
      </tr>
      <!-- 
	  <tr class="pb_frmtr">
        <th class="pb_frmth"><input type="checkbox" name="batchNames" value="mobileValidate" />
          <label>AuthUser.ALIAS_MOBILE_VALIDATE：</label></th>
        <td class="pb_frmtd">
		<select disabled="disabled" id="emailVisible" class="required digits input_txt" name="emailVisible">
			<option value="1">否</option>
			<option value="0">是</option>
		</select>
        </td>
      </tr>
       -->
    </table>
</form:form>
</div>
<script type="text/javascript">
	doRestBatchEditUI({form:"#admin_auth_member_batchEdit_form",batchBox:"batchNames"});
	
	$("#admin_auth_member_batchEdit_form").validate();
</script>
