<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="id" name="id" value="${question.id}"/>

<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_CONTENT%>:</label></th>
  <td class="frmtd">    <form:input path="content" id="content" class=" input_txt" maxlength="500" />
    <font color='red'>
    <form:errors path="content"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_IMG_PATH%>:</label></th>
  <td class="frmtd">    <form:input path="imgPath" id="imgPath" class=" input_txt" maxlength="200" />
    <font color='red'>
    <form:errors path="imgPath"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_GRADE_ID%>:</label></th>
  <td class="frmtd">    <form:input path="gradeId" id="gradeId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="gradeId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_SUBJECT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="subjectId" id="subjectId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="subjectId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_KNOWLEDGE_ID%>:</label></th>
  <td class="frmtd">    <form:input path="knowledgeId" id="knowledgeId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="knowledgeId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_ADD_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${question.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="addTimeString" name="addTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="addTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_STUDENT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="studentId" id="studentId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="studentId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_SOURCE_TYPE%>:</label></th>
  <td class="frmtd">    <form:input path="sourceType" id="sourceType" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="sourceType"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_STATUS%>:</label></th>
  <td class="frmtd">    <form:input path="status" id="status" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="status"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_PLATFORM%>:</label></th>
  <td class="frmtd">    <form:input path="platform" id="platform" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="platform"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_REPORT_ID%>:</label></th>
  <td class="frmtd">    <form:input path="reportId" id="reportId" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="reportId"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_REPORT_TIME%>:</label></th>
  <td class="frmtd">
    <input value="<fmt:formatDate value="${question.reportTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="reportTimeString" name="reportTimeString"  maxlength="0" class=" input_text" />
    <font color='red'>
    <form:errors path="reportTime"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_AUDIT_STATE%>:</label></th>
  <td class="frmtd">    <form:input path="auditState" id="auditState" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="auditState"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_REPORT_RESULT%>:</label></th>
  <td class="frmtd">    <form:input path="reportResult" id="reportResult" class="digits  input_txt" maxlength="19" />
    <font color='red'>
    <form:errors path="reportResult"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_REPORT_CONTENT%>:</label></th>
  <td class="frmtd">    <form:input path="reportContent" id="reportContent" class=" input_txt" maxlength="200" />
    <font color='red'>
    <form:errors path="reportContent"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_IS_DEL%>:</label></th>
  <td class="frmtd">    <form:input path="isDel" id="isDel" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="isDel"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_IS_QUIT%>:</label></th>
  <td class="frmtd">    <form:input path="isQuit" id="isQuit" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="isQuit"/>
    </font></td>
  </tr>
<tr class="frmtr"> 
  <th class="frmth"><label><%=Question.ALIAS_IS_LOCK%>:</label></th>
  <td class="frmtd">    <form:input path="isLock" id="isLock" class="digits  input_txt" maxlength="3" />
    <font color='red'>
    <form:errors path="isLock"/>
    </font></td>
  </tr>
