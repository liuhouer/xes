<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_PROVINCE_ID%>：</th>
      <td class="frmtd"><c:out value='${teacher.provinceId}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_AREA_ID%>：</th>
      <td class="frmtd"><c:out value='${teacher.areaId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_LEVEL%>：</th>
      <td class="frmtd"><c:out value='${teacher.level}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_NICK_NAME%>：</th>
      <td class="frmtd"><c:out value='${teacher.nickName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_RELE_NAME%>：</th>
      <td class="frmtd"><c:out value='${teacher.releName}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_LOGIN_NAME%>：</th>
      <td class="frmtd"><c:out value='${teacher.loginName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_EXPERT_GRADE%>：</th>
      <td class="frmtd"><c:out value='${teacher.expertGrade}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_EXPERT_SUBJECT%>：</th>
      <td class="frmtd"><c:out value='${teacher.expertSubject}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_SEX%>：</th>
      <td class="frmtd"><c:out value='${teacher.sex}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_IMG_PATH%>：</th>
      <td class="frmtd"><c:out value='${teacher.imgPath}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_LAST_LOGIN_TIME%>：</th>
      <td class="frmtd"><c:out value='${teacher.lastLoginTimeString}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${teacher.addTimeString}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=Teacher.ALIAS_PASSWORD%>：</th>
      <td class="frmtd"><c:out value='${teacher.password}'/></td>
      
      <th class="frmth"><%=Teacher.ALIAS_STATUS%>：</th>
      <td class="frmtd"><c:out value='${teacher.status}'/></td>
      </tr>
  </table>
</div>
