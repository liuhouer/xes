<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty categoryList}">
[
	<c:forEach items="${categoryList}" var="category" varStatus="status">
	{
		"text": "${category.name}【${category.id}】&nbsp;(${category.allNum},${category.subNum},${category.issueedNum})<span style=\"margin-right:5px;float:left;\"><input title=\"转移栏目\" type=\"checkbox\" id=\"cateogryId_${category.id}\" name=\"ids\" value=\"${category.id}\" />&nbsp;<a href=\"javascript:;\" onclick=\"addCategory(${category.id})\">添加子项</a>&nbsp;|&nbsp;<a href=\"javascript:;\" onclick=\"editCategory(${category.id})\">编辑</a>&nbsp;|&nbsp;<a href=\"javascript:;\" onclick=\"deleteCategory(${category.id})\">删除</a>&nbsp;|&nbsp;<a href=\"javascript:;\" onclick=\"issueCategory(${category.id})\">发布</a>&nbsp;<c:if test="${category.issued}"><a href=\"${category.listIndexPath}?adminView=true&pageTemplateId=${category.listPageTemplate.id}\" target=\"_blank\">|&nbsp;查看</a></c:if></span>",
		"id": "${category.id}",
		"classes" : "<c:choose><c:when test="${category.childCount > 0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>",
		"hasChildren":<c:choose><c:when test="${category.childCount > 0}">true</c:when><c:otherwise>false</c:otherwise></c:choose>
	}<c:if test="${!(status.last)}">,</c:if>
	</c:forEach>
]
</c:if>