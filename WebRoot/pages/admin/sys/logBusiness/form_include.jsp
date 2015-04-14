<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<input type="hidden" id="id" name="id" value="${logBusiness.id}"/>

<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_USER_GUID%>:</label></th>
  <td class="pb_frmtd">    <form:input path="userGuid" id="userGuid" class=" input_txt" maxlength="48" />
    <font color='red'>
    <form:errors path="userGuid"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_TIME%>:</label></th>
  <td class="pb_frmtd"><input value="<fmt:formatDate value="${logBusiness.timeDate}" pattern="yyyy-MM-dd HH:ss"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:ss'})" id="timeString" name="timeString" maxlength="20" class=" input_txt" />
    <font color='red'>
    <form:errors path="time"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_TYPE%>:</label></th>
  <td class="pb_frmtd">    <form:input path="type" id="type" class=" input_txt" maxlength="16" />
    <font color='red'>
    <form:errors path="type"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_RESULT%>:</label></th>
  <td class="pb_frmtd">    <form:input path="result" id="result" class=" input_txt" maxlength="255" />
    <font color='red'>
    <form:errors path="result"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_IP%>:</label></th>
  <td class="pb_frmtd">    <form:input path="ip" id="ip" class=" input_txt" maxlength="16" />
    <font color='red'>
    <form:errors path="ip"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_FUNCTION%>:</label></th>
  <td class="pb_frmtd">    <form:input path="function" id="function" class=" input_txt" maxlength="48" />
    <font color='red'>
    <form:errors path="function"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_LEVEL%>:</label></th>
  <td class="pb_frmtd">    <form:input path="level" id="level" class=" input_txt" maxlength="1" />
    <font color='red'>
    <form:errors path="level"/>
    </font></td>
   </tr>
<tr class="pb_frmtr"> 
  <th class="pb_frmth"><label><%=LogBusiness.ALIAS_DESCRIPTION%>:</label></th>
  <td class="pb_frmtd">    <form:input path="description" id="description" class=" input_txt" maxlength="255" />
    <font color='red'>
    <form:errors path="description"/>
    </font></td>
   </tr>
