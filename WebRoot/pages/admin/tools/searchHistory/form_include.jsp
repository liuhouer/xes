<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
	<input type="hidden" id="id" name="id" value="${searchHistory.id}"/>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=SearchHistory.ALIAS_TYPE%>:</label></th>	
		 <td class="pb_frmtd">
		 	<select id="type" name="type" class="input_txt">
				<option value="0">用户检索</option>
				<option value="1" selected="selected">后台添加</option>
			</select>
		</td>
	</tr>	
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=SearchHistory.ALIAS_KEY_WORDS%>:</label></th>	
		 <td class="pb_frmtd">
		<form:input path="keyWords" id="keyWords" class="input_txt required" maxlength="200" />
		<font color='red'><form:errors path="keyWords"/></font>
		</td>
	</tr>	
		