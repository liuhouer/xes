<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@ include file="/common/taglibs.jsp" %>
	<c:forEach items="${dictionarylist}" var="items">
		<tr class="pb_frmtr">
			<th class="pb_frmth"><label>${items.name}：</label></th>	
		 	<c:if test="${!empty items.refCode}">
		 	<td class="pb_frmtd">	
		 	<input type="text" name="${items.dictionaryKey}" id="${items.dictionaryKey}" value="${items.refCode}" class=" input_text" maxlength="125" style="width:150px"/>
			<span class="required">*</span><font color='red'><form:errors path="imageSaveSize"/></font>
			</td>
			</c:if>
			<c:if test="${items.isParent}">
				<c:forEach items="${items.childDictionary}" var="childs" varStatus="status">
					<tr class="pb_frmtr">
						<th class="pb_frmth"><label>${status.index + 1}、</label></th>	
						<td class="pb_frmtd">
					 	<input type="text" name="${childs.dictionaryKey}" id="${childs.dictionaryKey}" value="${childs.refCode}" class=" input_text" maxlength="125" style="width:150px"/>
						<span class="required">*</span><font color='red'><form:errors path="${childs.dictionaryKey}"/></font>
						</td>
					</tr>
				   </c:forEach>
			</c:if>
		 </tr>
	 </c:forEach>