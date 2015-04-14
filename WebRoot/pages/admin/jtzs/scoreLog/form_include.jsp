<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${scoreLog.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE%>:</label></th>
  <td class="frmtd">    <form:input path="score" id="score" class="digits  input_txt" maxlength="10" />
    <font color='red'>
    <form:errors path="score"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE_ID%>:</label></th>
  <td class="frmtd">    <form:input path="scoreId" id="scoreId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="scoreId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${scoreLog.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_OPERATOR_ID%>:</label></th>
  <td class="frmtd">    <form:input path="operatorId" id="operatorId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="operatorId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_REMARK%>:</label></th>
  <td class="frmtd">    <form:input path="remark" id="remark" class=" input_txt" maxlength="200" />
    <font color='red'>
    <form:errors path="remark"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_CONTENT%>:</label></th>
  <td class="frmtd">    <form:input path="content" id="content" class=" input_txt" maxlength="200" />
    <font color='red'>
    <form:errors path="content"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=ScoreLog.ALIAS_SCORE_TYPE%>:</label></th>
  <td class="frmtd">    <form:input path="scoreType" id="scoreType" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="scoreType"/>
    </font></td>
  </tr>
