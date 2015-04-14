<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Score.ALIAS_ROLE%>：</label></th>
      <td class="frmtd">        <input id="role" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Score.ALIAS_LOGIN_ID%>：</label></th>
      <td class="frmtd">        <input id="loginId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Score.ALIAS_SCORE%>：</label></th>
      <td class="frmtd">        <input id="score" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Score.ALIAS_USE_SCORE%>：</label></th>
      <td class="frmtd">        <input id="useScore" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
  </table>
</div>
