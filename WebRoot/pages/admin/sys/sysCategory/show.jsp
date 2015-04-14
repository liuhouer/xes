<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr class="frmtr"> 
      <th class="frmth"><%=SysCategory.ALIAS_GUID%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.guid}'/></td>
      
      <th class="frmth"><%=SysCategory.ALIAS_CAT_NAME%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.catName}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SysCategory.ALIAS_PARENT_GUID%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.parentGuid}'/></td>
      
      <th class="frmth"><%=SysCategory.ALIAS_CONFIG_SOURCE%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.configSource}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SysCategory.ALIAS_CONFIG_ID%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.configId}'/></td>
      
      <th class="frmth"><%=SysCategory.ALIAS_CONTENT_MODEL_ID%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.contentModelId}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SysCategory.ALIAS_LIST_URL_PATH%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.listUrlPath}'/></td>
      
      <th class="frmth"><%=SysCategory.ALIAS_CONFIG_URL_PATH%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.configUrlPath}'/></td>
      </tr>
    <tr class="frmtr"> 
      <th class="frmth"><%=SysCategory.ALIAS_SORT_ID%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.sortId}'/></td>
      
      <th class="frmth"><%=SysCategory.ALIAS_ADD_TIME%>：</th>
      <td class="frmtd"><c:out value='${sysCategory.addTimeString}'/></td>
      </tr>
  </table>
</div>
