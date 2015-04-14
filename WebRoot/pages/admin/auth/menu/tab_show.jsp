<%@page import="com.up72.auth.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
   		<tr>
         	<th><%=Menu.ALIAS_PARENT_ID%>：</th>
            <td><c:out value='${menu.parentId}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_PERMISSION_ID%>：</th>
            <td><c:out value='${menu.permissionId}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_NAME%>：</th>
            <td><c:out value='${menu.name}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_ICON%>：</th>
            <td><c:out value='${menu.icon}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_SORT_ID%>：</th>
            <td><c:out value='${menu.sortId}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_LEVEL%>：</th>
            <td><c:out value='${menu.level}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_ROLE_ID%>：</th>
            <td><c:out value='${menu.roleId}'/></td>
     	 </tr>
   		<tr>
         	<th><%=Menu.ALIAS_ADD_TIME%>：</th>
            <td><fmt:formatDate value="${menu.addTimeDate}"/></td>
     	 </tr>
    </table>
	
 </div>