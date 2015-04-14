<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${comment.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Comment.ALIAS_CONTENT%>:</label></th>
  <td class="frmtd">    <form:input path="content" id="content" class=" input_txt" maxlength="500" />
    <font color='red'>
    <form:errors path="content"/>
    </font></td>
  </tr>
<tr class="frmtr">
  <th class="frmth"><label><%=Comment.ALIAS_STUDENT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="studentId" id="studentId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="studentId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Comment.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${comment.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Comment.ALIAS_IS_SATISFIED%>:</label></th>
  <td class="frmtd">    <form:input path="isSatisfied" id="isSatisfied" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="isSatisfied"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Comment.ALIAS_ANSWER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="answerId" id="answerId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="answerId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Comment.ALIAS_IS_DEL%>:</label></th>
  <td class="frmtd">    <form:input path="isDel" id="isDel" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="isDel"/>
    </font></td>
  </tr>
