<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${menu.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_PARENT_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="parentId" id="parentId" class="digits  input_txt" maxlength="19" />
		<font color='red'><form:errors path="parentId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_PERMISSION_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="permissionId" id="permissionId" class="digits  input_txt" maxlength="19" />
		<font color='red'><form:errors path="permissionId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_NAME%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="name" id="name" class="input_txt" maxlength="256" />
		<font color='red'><form:errors path="name"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_ICON%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="icon" id="icon" class="input_txt" maxlength="256" />
		<font color='red'><form:errors path="icon"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_SORT_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="sortId" id="sortId" class="digits  input_txt" maxlength="10" />
		<font color='red'><form:errors path="sortId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_LEVEL%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="level" id="level" class="digits  input_txt" maxlength="10" />
		<font color='red'><form:errors path="level"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_ROLE_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="roleId" id="roleId" class="digits  input_txt" maxlength="19" />
		<font color='red'><form:errors path="roleId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Menu.ALIAS_ADD_TIME%>:</label></th>	
		 <td class="pb_frmtd"><input value="<fmt:formatDate value="${menu.addTimeDate}" pattern="yyyy-MM-dd HH:ss"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:ss'})" id="addTimeString" name="addTimeString"  maxlength="0" class="input_txt" />
		<font color='red'><form:errors path="addTime"/></font>
		</td>
	</tr>	
