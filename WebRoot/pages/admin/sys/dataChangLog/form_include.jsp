<%@page import="com.up72.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${dataChangLog.id}"/>

	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_TABLE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="changTable" id="changTable" class="input_txt" maxlength="256" />
		<font color='red'><form:errors path="changTable"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_ID%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="changId" id="changId" class="digits  input_txt" maxlength="19" />
		<font color='red'><form:errors path="changId"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_TIME%>:</label></th>	
		 <td class="pb_frmtd"><input value="<fmt:formatDate value="${dataChangLog.changTimeDate}" pattern="yyyy-MM-dd HH:ss"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:ss'})" id="changTimeString" name="changTimeString"  maxlength="0" class="input_txt" />
		<font color='red'><form:errors path="changTime"/></font>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=DataChangLog.ALIAS_CHANG_TYPE%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="changType" id="changType" class="input_txt" maxlength="50" />
		<font color='red'><form:errors path="changType"/></font>
		</td>
	</tr>	
