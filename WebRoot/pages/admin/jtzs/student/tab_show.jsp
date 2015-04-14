<%@page import="com.xes.jtzs.model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${student.status}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_PROVINCE_ID%>：</th>
      <td class="frmtd"><c:out value='${student.provinceId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_NICK_NAME%>：</th>
      <td class="frmtd"><c:out value='${student.nickName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_LOGIN_NAME%>：</th>
      <td class="frmtd"><c:out value='${student.loginName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_SCHOOL_ID%>：</th>
      <td class="frmtd"><c:out value='${student.schoolId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_GRADE_ID%>：</th>
      <td class="frmtd"><c:out value='${student.gradeId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_SEX%>：</th>
      <td class="frmtd"><c:out value='${student.sex}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_IMG_PATH%>：</th>
      <td class="frmtd"><c:out value='${student.imgPath}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_LAST_LOGIN_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${student.lastLoginTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><fmt:formatDate value="${student.addTimeDate}"/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_PASSWORD%>：</th>
      <td class="frmtd"><c:out value='${student.password}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Student.ALIAS_PLATFORM%>：</th>
      <td class="frmtd"><c:out value='${student.platform}'/></td>
      </tr>
  </table>
</div>
