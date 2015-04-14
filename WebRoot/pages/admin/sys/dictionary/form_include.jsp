<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@ include file="/common/taglibs.jsp" %>
	<input type="hidden" id="id" name="id" value="<c:out value='${dictionary.id}' default='0'/>"/>
	<!-- <input type="hidden" id="dictionaryId" name="dictionaryId" value="<c:out value='${dictionary.dictionaryId}' default='0'/>"/> -->
	<input type="hidden" id="level" name="level" value="<c:out value='${dictionary.level}' default='1'/>"/>
	<input type="hidden" id="path" name="path" value="<c:out value='${dictionary.path}' default=''/>"/>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Dictionary.ALIAS_NAME %>：</label></th>	
		 <td class="pb_frmtd">
				<form:input path="name" id="name" class="required input_txt" maxlength="19" />
				<span class="required">*</span><font color='red'><form:errors path="name"/></font>
		</td>
	 </tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Dictionary.ALIAS_DICTIONARY_ID %>：</label></th>	
		 <td class="pb_frmtd">
			 <select id="dictionaryId" name="dictionaryId" >
				<option value="0">根节点</option>
			 	<c:forEach items="${dictionaryList}" var="t_cat">
			 	<option value="${t_cat.id}"<c:if test="${t_cat.id == dictionary.dictionaryId}"> selected="selected"</c:if>>
			 	<c:forEach begin="2" end="${t_cat.level}">&nbsp;&nbsp;</c:forEach>
			 	<c:if test="${t_cat.level > 1}">|-</c:if>${t_cat.name}
			 	</option>
			 	</c:forEach>
			</select>
		</td>
	 </tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Dictionary.ALIAS_DICTIONARY_KEY %>：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="dictionaryKey" id="dictionaryKey" class="{required:true,remote:'${ctx}/admin/sys/dictionary/validateKey?id=${dictionary.id}',messages:{remote:'该标识码已经存在，请重新输入'}}  input_text" maxlength="125" />
		<span class="required">*</span><font color='red'><form:errors path="dictionaryKey"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Dictionary.ALIAS_REF_CODE %>：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="refCode" id="refCode" class=" input_txt" maxlength="125" />
		<span class="required"></span><font color='red'><form:errors path="refCode"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Dictionary.ALIAS_DESCRIPTION %>：</label></th>	
		 <td class="pb_frmtd">
		<form:textarea rols="5" cols="25" path="description" id="description" class="input_txt" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />
		<font color='red'><form:errors path="description"/></font>
		</td>
	</tr>	
 	<tr class="pb_frmtr">
		<th class="pb_frmth"><label><%=Dictionary.ALIAS_SORT %>：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="sort" id="sort" class="digits input_txt" maxlength="125" />
		<font color='red'><form:errors path="sort"/></font>
		</td>
	</tr>