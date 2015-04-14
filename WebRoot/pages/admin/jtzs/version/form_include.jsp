<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${version.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_TYPE%>:</label></th>
  <td class="frmtd">    <form:input path="type" id="type" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="type"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_VERSION%>:</label></th>
  <td class="frmtd">    <form:input path="version" id="version" class=" input_txt" maxlength="50" />
    <font color='red'>
    <form:errors path="version"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_SIZE%>:</label></th>
  <td class="frmtd">    <form:input path="size" id="size" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="size"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_APP_URL%>:</label></th>
  <td class="frmtd">    <form:input path="appUrl" id="appUrl" class="url  input_txt" maxlength="200" />
    <font color='red'>
    <form:errors path="appUrl"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_UPDATE_INFO%>:</label></th>
  <td class="frmtd">    <form:input path="updateInfo" id="updateInfo" class=" input_txt" maxlength="500" />
    <font color='red'>
    <form:errors path="updateInfo"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${version.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Version.ALIAS_UPDATE_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${version.updateTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="updateTimeString" name="updateTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="updateTime"/>
    </font></td>
  </tr>
