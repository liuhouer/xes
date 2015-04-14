<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xes.jtzs.model.Comment"%>
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
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_comment_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
       <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
	</head>
	<body>
 <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_comment_search_form" name="admin_jtzs_comment_search_form" method="get">
      <div class="search_con"> 
          <input type="hidden" id="isSatisfied" name="isSatisfied" class="input_text" value="${query.isSatisfied}">
          <input type="hidden" id="teacherId" name="teacherId" class="input_text" value="${query.teacherId}">
      </div>
    </form>
  </div>
  <!--end搜索-->
  
  <form id="admin_jtzs_comment_page_form" name="admin_jtzs_comment_page_form" method="get">
    <table id="admin_jtzs_comment_table">
      <thead>
        <tr>
          <th showColumn="index" width="20">序号</th>
                    <th showColumn="studentId" width="50">学生昵称</th>
                    <th showColumn="studentId" width="120">学生帐号</th>
                    <th showColumn="content" width="300"><%=Comment.ALIAS_CONTENT%></th>
                    <th sortColumn="ADD_TIME" showColumn="addTime" width="120"><%=Comment.ALIAS_ADD_TIME%></th>
                    <th sortColumn="IS_SATISFIED" showColumn="isSatisfied" width="50"><%=Comment.ALIAS_IS_SATISFIED%></th>
          <th showColumn="option" width="60"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
                        <td showColumn="studentId"><c:out value='${item.student.nickName}'/></td>
                        <td showColumn="studentId"><c:out value='${item.student.loginName}'/></td>
                        <td showColumn="content"><c:out value='${item.content}'/></td>
                        <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td showColumn="isSatisfied"><c:out value='${item.satisfiedStr}'/></td>
            <td showColumn="option">
            	<a href="javascript:;"  onclick="delComment(${item.id })" class="sysiconBtnNoIcon">删除</a>
            </td>
             </tr>
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  </form>
  <script type="text/javascript">
	
	// 表格列表处理
	$('#admin_jtzs_comment_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	function delComment(id){
		$.ajax({
			url : "${ctx}/admin/jtzs/comment/"+id+"/delComment",
			type : "post",
			dataType : "json",
			success : function(jsondatas){
				if(jsondatas.status=='success'){
					$("#admin_jtzs_comment_search_form").submit();
				}
			},
			error : function(){
			}
		});
	}
</script> 
	</body>
</html>
