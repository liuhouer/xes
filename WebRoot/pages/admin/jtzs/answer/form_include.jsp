<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${answer.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Answer.ALIAS_QUESTION_ID%>:</label></th>
  <td class="frmtd">    <form:input path="questionId" id="questionId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="questionId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Answer.ALIAS_TEACHER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="teacherId" id="teacherId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="teacherId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Answer.ALIAS_ANSWER%>:</label></th>
  <td class="frmtd">    <form:input path="answer" id="answer" class=" input_txt" maxlength="500" />
    <font color='red'>
    <form:errors path="answer"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Answer.ALIAS_IDER%>:</label></th>
  <td class="frmtd">    <form:input path="ider" id="ider" class=" input_txt" maxlength="500" />
    <font color='red'>
    <form:errors path="ider"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Answer.ALIAS_ANSWER_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${answer.answerTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="answerTimeString" name="answerTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="answerTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Answer.ALIAS_STATUS%>:</label></th>
  <td class="frmtd">    <form:input path="status" id="status" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="status"/>
    </font></td>
  </tr>
