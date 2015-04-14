<%@page import="com.up72.auth.model.*" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<form:form id="admin_auth_member_tab_edit_form" method="put" action="${ctx}/admin/auth/member/${AuthUser.id}" modelAttribute="member">
  <div class="up72_edit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <input type="hidden" id="id" name="id" value="${AuthUser.id}"/>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_USER_NAME%>：</label></th>
        <td class="pb_frmtd"><input type="text" class="input_txt" disabled="disabled" value="${AuthUser.userName}" />
          <form:hidden path="userName" id="userName" />
          <span class="required">*</span><font color='red'>
          <form:errors path="userName"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_PASSWORD%>：</label></th>
        <td class="pb_frmtd"><input type="text" class="input_txt" disabled="disabled" value="${AuthUser.password}" />
          <form:hidden path="password" id="password"  />
          <span class="required">*</span><font color='red'>
          <form:errors path="password"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_NICK_NAME%>：</label></th>
        <td class="pb_frmtd"><form:input path="nickName" id="nickName" class="input_txt" maxlength="20" />
          <span class="required"></span><font color='red'>
          <form:errors path="nickName"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_EMAIL%>：</label></th>
        <td class="pb_frmtd"><form:input path="email" id="email" class="{required:true,email:true} input_txt" />
          <span class="required">*</span><font color='red'>
          <form:errors path="email"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_LOGIN_ANSWER%>：</label></th>
        <td class="pb_frmtd"><form:input path="loginAnswer" id="loginAnswer" class="{minlength:4,maxlength:50} input_txt" />
          <font color='red'>
          <form:errors path="loginAnswer"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_SECQUES%>：</label></th>
        <td class="pb_frmtd"><form:input path="secques" id="secques" class="{minlength:4,maxlength:50} input_txt" />
          <font color='red'>
          <form:errors path="secques"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_MOBILE_VALIDATE%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="mobileValidate" id="mobileValidate" class="required digits:" value="1" checked="checked" />
          可见
          <form:radiobutton path="mobileValidate" id="mobileValidate" class="required digits:" value="0" />
          不可见 <font color='red'>
          <form:errors path="mobileValidate"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_CODE%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="code" label="管理员" value="2" class="required digits:" />
          <form:radiobutton path="code" label="系统管理员" value="5" class="required digits:" />
          <span class="required"></span><font color='red'>
          <form:errors path="code"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_IMG_PATH%>：</label></th>
        <td class="pb_frmtd"><form:input path="imgPath" id="imgPath" class="input_txt" />
          <span class="required"></span><font color='red'>
          <form:errors path="imgPath"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_MOBILE%>：</label></th>
        <td class="pb_frmtd"><form:input path="mobile" id="mobile" class="mobile input_txt" maxlength="15" />
          <font color='red'>
          <form:errors path="mobile"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_PROBLEM%>：</label></th>
        <td class="pb_frmtd"><form:input path="problem" id="problem" class="{minlength:4,maxlength:50,cname:true} input_txt" />
          <font color='red'>
          <form:errors path="problem"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_ANSER%>：</label></th>
        <td class="pb_frmtd"><form:input path="anser" id="anser" class="{minlength:4,maxlength:50,cname:true} input_txt" />
          <font color='red'>
          <form:errors path="anser"/>
          </font></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_ENABLED%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="enabled" id="enabled" checked="checked" class="required digits:" value="1" />
          启用
          <form:radiobutton path="enabled" id="enabled" class="required digits:" value="0" />
          禁用 <font color='red'>
          <form:errors path="enabled"/>
          </font></td>
        <th class="pb_frmth"><label><%=AuthUser.ALIAS_EMAIL_VISIBLE%>：</label></th>
        <td class="pb_frmtd"><form:radiobutton path="emailVisible" id="emailVisible" checked="checked" class="required digits:" value="1" />
          可见
          <form:radiobutton path="emailVisible" id="emailVisible" class="required digits:" value="0" />
          不可见 <span class="required"></span><font color='red'>
          <form:errors path="emailVisible"/>
          </font></td>
      </tr>
      <form:hidden path="lastIp" id="lastIp" />
      <form:hidden path="lastVisiTime" id="lastVisiTime" />
      <form:hidden path="delStatus" id="delStatus" />
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin_auth_member_tab_edit_form").validate();	
	});
</script> 
