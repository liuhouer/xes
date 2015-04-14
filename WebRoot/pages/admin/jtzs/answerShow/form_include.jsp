<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${answerShow.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=AnswerShow.ALIAS_QUESTION_ID%>:</label></th>
  <td class="frmtd">    <form:input path="questionId" id="questionId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="questionId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=AnswerShow.ALIAS_ANSWER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="answerId" id="answerId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="answerId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=AnswerShow.ALIAS_STUDENT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="studentId" id="studentId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="studentId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=AnswerShow.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${answerShow.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
