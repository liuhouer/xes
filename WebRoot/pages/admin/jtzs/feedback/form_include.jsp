<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${feedback.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Feedback.ALIAS_CONTENT%>:</label></th>
  <td class="frmtd">    <form:input path="content" id="content" class=" input_txt" maxlength="500" />
    <font color='red'>
    <form:errors path="content"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Feedback.ALIAS_USER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="userId" id="userId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="userId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Feedback.ALIAS_USER_ROLE%>:</label></th>
  <td class="frmtd">    <form:input path="userRole" id="userRole" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="userRole"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Feedback.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${feedback.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
