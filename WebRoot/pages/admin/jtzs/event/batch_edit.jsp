<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_edit">
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="edit_table">
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_TITLE%>：</label></th>
      <td class="frmtd">        <input id="title" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_SEND_TO%>：</label></th>
      <td class="frmtd">        <input id="sendTo" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_SEND_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Event.FORMAT_SEND_TIME%>'})" id="sendTimeString" name="sendTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_SEND_STATUS%>：</label></th>
      <td class="frmtd">        <input id="sendStatus" class="digits  input_txt" maxlength="3" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_SEND_USER%>：</label></th>
      <td class="frmtd">        <input id="sendUser" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_IMG_PATH%>：</label></th>
      <td class="frmtd">        <input id="imgPath" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_SUMMARY%>：</label></th>
      <td class="frmtd">        <input id="summary" class=" input_txt" maxlength="200" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_CONTENT%>：</label></th>
      <td class="frmtd">        <input id="content" class=" input_txt" maxlength="65535" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION1%>：</label></th>
      <td class="frmtd">        <input id="question1" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION2%>：</label></th>
      <td class="frmtd">        <input id="question2" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION3%>：</label></th>
      <td class="frmtd">        <input id="question3" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION4%>：</label></th>
      <td class="frmtd">        <input id="question4" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION5%>：</label></th>
      <td class="frmtd">        <input id="question5" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION6%>：</label></th>
      <td class="frmtd">        <input id="question6" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_QUESTION7%>：</label></th>
      <td class="frmtd">        <input id="question7" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_ANSWER%>：</label></th>
      <td class="frmtd">        <input id="answer" class=" input_txt" maxlength="50" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_START_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Event.FORMAT_START_TIME%>'})" id="startTimeString" name="startTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_END_TIME%>：</label></th>
      <td class="frmtd">
        <input onclick="WdatePicker({dateFmt:'<%=Event.FORMAT_END_TIME%>'})" id="endTimeString" name="endTimeString"  maxlength="0" class=" input_txt" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_ADD_SCORE%>：</label></th>
      <td class="frmtd">        <input id="addScore" class="digits  input_txt" maxlength="10" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_DEL_SCORE%>：</label></th>
      <td class="frmtd">        <input id="delScore" class="digits  input_txt" maxlength="10" />
         </td>
    </tr>
    <tr class="frmtr">
      <th class="frmth"><label><%=Event.ALIAS_PROVINCE_ID%>：</label></th>
      <td class="frmtd">        <input id="provinceId" class="digits  input_txt" maxlength="19" />
         </td>
    </tr>
  </table>
</div>
