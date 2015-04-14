<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<input id="className" name="className" value="${configFile.name}" type="hidden" />
<c:if test="${configFile == null}">
<tr class="pb_frmtr">
	<td><label>您要配置的模型不存在!</label></td>
</tr>
</c:if>
<c:if test="${configFile != null}">
<tr class="pb_frmtr">
	<th class="pb_frmth"><label>对象:</label></th>
	<td class="pb_frmtd">${configFile.name}</td>
</tr>
<tr class="pb_frmtr">
	<th class="pb_frmth"><label>索引ID生成方式:</label></th>
	<td class="pb_frmtd">
		<select id="generateType" name="generateType">
			<option value="1">
				对象ID生成
			</option>
		</select>
	</td>
</tr>
<c:forEach var="method" items="${configFile.fieldMap}" varStatus="stat">
<tr class="pb_frmtr">
	<th class="pb_frmth"><label>${method.key}:</label><input id="fields" name="fields" value="${method.key}" type="hidden" /></th>
	<td class="pb_frmtd">
		<c:forEach var="type" items="${method.value}" varStatus="index">
			<c:if test="${index.index == 0}">
			索引方式：<select id="${method.key}_indexType" name="${method.key}_indexType">
				<option value="0" <c:if test="${'0' eq type}">selected</c:if>>
					不索引
				</option>
				<option value="1" <c:if test="${'1' eq type}">selected</c:if>>
					不分词索引
				</option>
				<option value="2" <c:if test="${'2' eq type}">selected</c:if>> 
					分词索引
				</option>
			</select>
			&nbsp; 
			</c:if>
			<c:if test="${index.index == 1}">
			存储方式：<select id="${method.key}_storageType" name="${method.key}_storageType">
				<option value="0" <c:if test="${'0' eq type}">selected</c:if>>
					不存储
				</option>
				<option value="1" <c:if test="${'1' eq type}">selected</c:if>>
					存储
				</option>
			</select>
			&nbsp;
			</c:if>
			<c:if test="${index.index == 2}">
			<input type="hidden" id="${method.key}_fieldType" name="${method.key}_fieldType" value="${type}"/>
			</c:if>
		</c:forEach>
	</td>
</tr>
</c:forEach>
</c:if>
	
		