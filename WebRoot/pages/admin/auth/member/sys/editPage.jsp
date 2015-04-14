<%@page import="com.up72.auth.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>
<up72:override name="head">
  <title><%=AuthUser.TABLE_ALIAS%>维护</title>
</up72:override>
<up72:override name="content">
<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="up72_mtable">
  <tr>
    <td class="up72_mleft">
  <tr>
    <td class="up72_filetree"><!--mainleft start-->
      
      <div class="up72_domleft">
        <div class="up72_sidebar skin_sidebar">
          <div class="up72_cnav">
            <ul class="up72_folders">
              <li><a href="${ctx}/admin/auth/member/home">首页</a></li>
              <li><a href="${ctx}/admin/auth/member/editInfo" class="navover">个人信息</a></li>
              <li><a href="${ctx}/admin/auth/member/resetPassword">修改密码</a></li>
              <li><a href="${ctx}/admin/authUser/logout">退出管理</a></li>
            </ul>
          </div>
        </div>
      </div>
      
      <!--mainleft end--></td>
      </td>
    <td class="up72_mright"><!--mainright start-->
      
      <div class="up72_domright">
        <div id="up72_iframe" class="up72_content">
          <div class="up72_edit">
            <form:form id="admin_auth_member_edit_form" method="put" action="${ctx}/admin/auth/member/doEditInfo" modelAttribute="authUser">
              <input type="hidden" id="id" name="id" value="${authUser.id}" />
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_USER_NAME%>:</label></th>
                  <td class="pb_frmtd">${authUser.userName}</td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_NICK_NAME%>:</label></th>
                  <td class="pb_frmtd"><form:input path="nickName" id="nickName" class="{cname:true,byteMin:4,byteMax:14} input_txt" maxlength="20" />
                    <span class="required"></span><font color='red'>
                    <form:errors path="nickName" />
                    </font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_EMAIL%>:</label></th>
                  <td class="pb_frmtd"><form:input path="email" id="email" class="{required:true,email:true} input_txt" maxlength="25" />
                    <span class="required">*</span><font color='red'>
                    <form:errors path="email" />
                    </font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_MOBILE%>:</label></th>
                  <td class="pb_frmtd"><form:input path="mobile" id="mobile" class="mobile input_txt" maxlength="15" />
                    <font color='red'>
                    <form:errors path="mobile" />
                    </font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_LOGIN_ANSWER%>:</label></th>
                  <td class="pb_frmtd"><form:input path="loginAnswer" id="loginAnswer" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
                    <font color='red'>
                    <form:errors path="loginAnswer" />
                    </font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_SECQUES%>:</label></th>
                  <td class="pb_frmtd"><form:input path="secques" id="secques" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
                    <font color='red'>
                    <form:errors path="secques" />
                    </font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_PROBLEM%>:</label></th>
                  <td class="pb_frmtd"><form:input path="problem" id="problem" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
                    <font color='red'>
                    <form:errors path="problem" />
                    </font></td>
                </tr>
                <tr class="pb_frmtr">
                  <th class="pb_frmth"><label><%=AuthUser.ALIAS_ANSER%>:</label></th>
                  <td class="pb_frmtd"><form:input path="anser" id="anser" class="{minlength:4,maxlength:50,cname:true} input_txt" maxlength="20" />
                    <font color='red'>
                    <form:errors path="anser" />
                    </font></td>
                </tr>
              </table>
              <div class="up72_submit">
                <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
      
      <!--mainright end--></td>
  </tr>
</table>
</up72:override>
<%@ include file="/pages/admin/adminNoPermBase.jsp"%>
