<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${gradeSubject.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=GradeSubject.ALIAS_SUBJECT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="subjectId" id="subjectId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="subjectId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=GradeSubject.ALIAS_GRADE_ID%>:</label></th>
  <td class="frmtd">    <form:input path="gradeId" id="gradeId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="gradeId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=GradeSubject.ALIAS_STATUS%>:</label></th>
  <td class="frmtd">    <form:input path="status" id="status" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="status"/>
    </font></td>
  </tr>
