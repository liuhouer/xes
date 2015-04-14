<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Score.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_score_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_score_search_form" name="admin_jtzs_score_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=Score.ALIAS_USER_ROLE%>：
	         <select id="userRole" name="userRole" style="width: 120px"  >
                <option value="" >全部</option>
			 	<option value="<%=JTZSConstants.ROLE_STUDENT %>" <c:if test="${query.userRole == 0}">selected="selected"</c:if>>学生</option>
			 	<option value="<%=JTZSConstants.ROLE_TEACHER %>" <c:if test="${query.userRole == 1}">selected="selected"</c:if>>老师</option>
			</select>
        </div>

        <div class="search_txt"><%=Score.ALIAS_USER_ID%>：
          <input type="text" id="loginName" name="loginName" class="input_text" value="${query.loginName}">
        </div>

        <div class="search_txt"><%=Score.ALIAS_SCORE%>：
          <input type="text" id="score" name="score" class="input_text" value="${query.score}">
        </div>

        <div class="search_txt"><%=Score.ALIAS_USE_SCORE%>：
          <input type="text" id="useScore" name="useScore" class="input_text" value="${query.useScore}">
        </div>
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button>
          </div>
           <div class="input_button">
            <button name="exp" type="button" onclick="exports()" class="button" value="导出"><span>导出</span></button>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!--end搜索-->
  <form id="admin_jtzs_score_page_form" name="admin_jtzs_score_page_form" method="get">
    <table id="admin_jtzs_score_table">
      <thead>
        <tr>
          <th showColumn="index" width="50">序号</th>
              <th sortColumn="USER_ROLE" showColumn="userRole" width="50"><%=Score.ALIAS_USER_ROLE%></th>
              <th sortColumn="USER_ID" showColumn="userId" width="120"><%=Score.ALIAS_USER_ID%></th>
              <th sortColumn="SCORE" showColumn="score" width="50"><%=Score.ALIAS_SCORE%></th>
              <th sortColumn="USE_SCORE" showColumn="useScore" width="50"><%=Score.ALIAS_USE_SCORE%></th>
              <th showColumn="remainScore" width="50">可用积分</th>
	          <th showColumn="option" width="150"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            
                 <td showColumn="userRole">
                 <c:if test="${item.userRole==0}">                                  
                                                    学生
            	 </c:if>
              	 <c:if test="${item.userRole==1}">
               	    老师
               	 </c:if>
                 </td>
                 <td showColumn="userId">
                 <c:if test="${item.userRole==0}"> <c:out value='${item.student.loginName}'/> </c:if>
                 <c:if test="${item.userRole==1}"><c:out value='${item.teacher.loginName}'/></c:if>
                 </td>
            
            
                 <td showColumn="score"><c:out value='${item.score}'/></td>
                 <td showColumn="useScore"><c:out value='${item.useScore}'/></td>
                 <td showColumn="remainScore"><c:out value='${item.remainScore}'/></td>
	            <td showColumn="option">
	                
	            	<a href="javascript:;"  
	            	<c:if test="${item.userRole==1}">onclick="show('iframe#${ctx}/admin/jtzs/score/selectScoreLog?userRole='+${item.userRole}+'&userId='+${item.teacher.id}+'&remainScore='+${item.remainScore},'积分明细',1000,800)" </c:if>
	            	<c:if test="${item.userRole==0}">onclick="show('iframe#${ctx}/admin/jtzs/score/selectScoreLog?userRole='+${item.userRole}+'&userId='+${item.student.id}+'&remainScore='+${item.remainScore},'积分明细',1000,800)" </c:if>
	            	 class="sysiconBtnNoIcon">积分明细</a>&nbsp;
            		<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/score/${item.id}/addScore','<%=Score.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">添加积分</a>&nbsp;
	            </td>
             </tr>
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  </form>
  <form id="ff" method="post"></form>
  <script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 排序处理
	$.sortcolumn({
		form : "#admin_jtzs_score_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_score_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_jtzs_score_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	
	function exports(){
		var loginName = $("#loginName").val();
		var score = $("#score").val();
		var useScore = $("#useScore").val();
		var userRole = $("#userRole").val();
		
		$("#ff").attr("action","${ctx}/admin/jtzs/score/exportData?loginName="+loginName+"&score="+score+"&useScore"+useScore+"&userRole="+userRole).submit();
		
	
		
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
