<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${student.status}'/></td>
      
      <th class="frmth"><%=Student.ALIAS_PROVINCE_ID%>：</th>
      <td class="frmtd"><c:out value='${student.provinceId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_NICK_NAME%>：</th>
      <td class="frmtd"><c:out value='${student.nickName}'/></td>
      
      <th class="frmth"><%=Student.ALIAS_LOGIN_NAME%>：</th>
      <td class="frmtd"><c:out value='${student.loginName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_SCHOOL_ID%>：</th>
      <td class="frmtd"><c:out value='${student.schoolId}'/></td>
      
      <th class="frmth"><%=Student.ALIAS_GRADE_ID%>：</th>
      <td class="frmtd"><c:out value='${student.gradeId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_SEX%>：</th>
      <td class="frmtd"><c:out value='${student.sex}'/></td>
      
      <th class="frmth"><%=Student.ALIAS_IMG_PATH%>：</th>
      <td class="frmtd"><c:out value='${student.imgPath}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_LAST_LOGIN_TIME%>：</th>
      <td class="frmtd"><c:out value='${student.lastLoginTimeString}'/></td>
      
      <th class="frmth"><%=Student.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${student.addTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_PASSWORD%>：</th>
      <td class="frmtd"><c:out value='${student.password}'/></td>
      
      <th class="frmth"><%=Student.ALIAS_PLATFORM%>：</th>
      <td class="frmtd"><c:out value='${student.platform}'/></td>
      </tr>
  </table>
</div>
