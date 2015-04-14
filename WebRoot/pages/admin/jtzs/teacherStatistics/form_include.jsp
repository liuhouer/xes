<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${teacherStatistics.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_TEACHER_ID%>:</label></th>
  <td class="frmtd">    <form:input path="teacherId" id="teacherId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="teacherId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_TWENTY_MIN_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="twentyMinNum" id="twentyMinNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="twentyMinNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_HALF_HOUR_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="halfHourNum" id="halfHourNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="halfHourNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_ONE_HOUR_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="oneHourNum" id="oneHourNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="oneHourNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_EXPERT_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="expertNum" id="expertNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="expertNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_QUIT_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="quitNum" id="quitNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="quitNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_ANSWER_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="answerNum" id="answerNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="answerNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_SHOW_NUM%>:</label></th>
  <td class="frmtd">    <form:input path="showNum" id="showNum" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="showNum"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_SATISFY%>:</label></th>
  <td class="frmtd">    <form:input path="satisfy" id="satisfy" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="satisfy"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=TeacherStatistics.ALIAS_UNSATISFY%>:</label></th>
  <td class="frmtd">    <form:input path="unsatisfy" id="unsatisfy" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="unsatisfy"/>
    </font></td>
  </tr>
