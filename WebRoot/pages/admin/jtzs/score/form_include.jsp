<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${score.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Score.ALIAS_USER_ROLE%>:</label></th>
  <td class="frmtd">    <form:input path="userRole" id="userRole" class=" input_txt" maxlength="50" />
    <font color='red'>
    <form:errors path="role"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Score.ALIAS_USER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="userId" id="userId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="loginId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Score.ALIAS_SCORE%>:</label></th>
  <td class="frmtd">    <form:input path="score" id="score" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="score"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Score.ALIAS_USE_SCORE%>:</label></th>
  <td class="frmtd">    <form:input path="useScore" id="useScore" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="useScore"/>
    </font></td>
  </tr>
