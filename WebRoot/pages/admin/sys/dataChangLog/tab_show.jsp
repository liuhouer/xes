<%@page import="com.up72.sys.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
   		<tr>
         	<th><%=DataChangLog.ALIAS_CHANG_TABLE%>：</th>
            <td><c:out value='${dataChangLog.changTable}'/></td>
     	 </tr>
   		<tr>
         	<th><%=DataChangLog.ALIAS_CHANG_ID%>：</th>
            <td><c:out value='${dataChangLog.changId}'/></td>
     	 </tr>
   		<tr>
         	<th><%=DataChangLog.ALIAS_CHANG_TIME%>：</th>
            <td><fmt:formatDate value="${dataChangLog.changTimeDate}"/></td>
     	 </tr>
   		<tr>
         	<th><%=DataChangLog.ALIAS_CHANG_TYPE%>：</th>
            <td><c:out value='${dataChangLog.changType}'/></td>
     	 </tr>
    </table>
	
 </div>