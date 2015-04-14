<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${systemMessage.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=SystemMessage.ALIAS_QUESTION_ID%>:</label></th>
  <td class="frmtd">    <form:input path="questionId" id="questionId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="questionId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SystemMessage.ALIAS_USER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="userId" id="userId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="userId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SystemMessage.ALIAS_USER_ROLE%>:</label></th>
  <td class="frmtd">    <form:input path="userRole" id="userRole" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="userRole"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SystemMessage.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${systemMessage.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SystemMessage.ALIAS_IS_READ%>:</label></th>
  <td class="frmtd">    <form:input path="isRead" id="isRead" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="isRead"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=SystemMessage.ALIAS_ADD_USER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="addUserId" id="addUserId" class=" input_txt" maxlength="50" />
    <font color='red'>
    <form:errors path="addUser"/>
    </font></td>
  </tr>
