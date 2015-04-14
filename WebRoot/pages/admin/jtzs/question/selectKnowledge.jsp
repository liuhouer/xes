<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xes.jtzs.model.Knowledge"%>
<%@include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<jsp:include page="/pages/admin/include/getOutterStyleSkin.jsp"></jsp:include>
		<%@include file="/pages/admin/include/default/resource.jsp" %>
		  <script src="${ctx}/scripts/rest.js" ></script>
		  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
		  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
		  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
		  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
		  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
		  <script src="${ctx}/scripts/clearbox/clearbox.js" type="text/javascript"></script> 
		<script type="text/javascript">
			ctx = "${ctx}";
		</script>  
		<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动 作   
			window.simpleTable = new SimpleTable('admin_jtzs_knowledge_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	   </script> 
       <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
	</head>
	<body>
		<div class="up72_edit"> 
		 <!--搜索-->
		  <div class="up72_search">
		    <form id="admin_jtzs_knowledge_search_form" name="admin_jtzs_knowledge_search_form" method="get">
		    	<input type="hidden" id="questionId" value="${question.id}"/>
		    </form>
		  </div>
		  <!--end搜索-->
		
		  <form id="admin_jtzs_knowledge_page_form" name="admin_jtzs_knowledge_page_form" method="get" action="${ctx}/admin/jtzs/question/addKnowledge">
		    <table id="admin_jtzs_knowledge_table">
		  	<input type="hidden" id="questionId" name="questionId" value="${question.id}"/>
		      <thead>
		        <tr>
		          <th showColumn="radio" width="25"></th>
		          <th showColumn="index" width="50" align="center">序号</th>
		          <th sortColumn="GRADE_ID" showColumn="gradeId" width="80"      align="center"><%=Knowledge.ALIAS_GRADE%></th>
		          <th sortColumn="SUBJECT_ID" showColumn="subjectId" width="80"  align="center"><%=Knowledge.ALIAS_SUBJECT%></th>
		          <th sortColumn="KNOWLEDGE1" showColumn="knowledge1" width="80" align="center"><%=Knowledge.ALIAS_KNOWLEDGE1%></th>
		          <th sortColumn="KNOWLEDGE2" showColumn="knowledge2" width="80" align="center"><%=Knowledge.ALIAS_KNOWLEDGE2%></th>
		          <th sortColumn="KNOWLEDGE3" showColumn="knowledge3" width="80" align="center"><%=Knowledge.ALIAS_KNOWLEDGE3%></th>
		           </tr>
		      </thead>
		      <tbody>
		        <c:forEach items="${page.result}" var="item" varStatus="status">
		          <tr>
		            <td showColumn="radio"><input type="radio" id="items" name="items" value="${item.id}" <c:if test="${item.id == question.knowledgeId}">checked="checked"</c:if>  class="sel" tags="null"></td>
		            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
		               <td showColumn="gradeId"><c:out value="${item.grade.name}"></c:out></td>
		               <td showColumn="subjectId"><c:out value='${item.subject.name}'/></td>
		               <td showColumn="knowledge1"><c:out value='${item.knowledge1}'/></td>
		               <td showColumn="knowledge2"><c:out value='${item.knowledge2}'/></td>
		               <td showColumn="knowledge3"><c:out value='${item.knowledge3}'/></td>
		             </tr>
		        </c:forEach>
		      </tbody>
		    </table>
		    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
		    <div class="up72_submit">
		      <div class="btn btn_sub" title="完成"><input type="button" onclick="commitForm()" id="submitButton" name="submitButton" value="完成" /></div>
		      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="window.parent.closeBox();" /></div>
		    </div>	
		  </form>
		</div>
		  <script type="text/javascript">
			// 表格列表处理
			$('#admin_jtzs_knowledge_table').flexigrid({
				height: 'auto',
				striped : true,
				buttons : []
			});
			// 列选择显示处理
			$.showcolumn(${showColumn});
			
			function commitForm(){
				$.ajax({
					url : "${ctx}/admin/jtzs/question/addKnowledge",
					type : "get",
					data : $("#admin_jtzs_knowledge_page_form").serialize(),
					success : function(html){
						window.parent.location.reload();
					}
				});
			}
		</script> 
	</body>
</html>
