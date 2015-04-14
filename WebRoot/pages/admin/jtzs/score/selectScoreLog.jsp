<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
  <title><%=ScoreLog.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
   <%@include file="/pages/admin/include/default/resource.jsp" %>
  <link href="${ctx}/styles/default/structs_inner.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_scoreLog_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_scoreLog_search_form" name="admin_jtzs_scoreLog_search_form"  action="${ctx}/admin/jtzs/score/selectScoreLog">
      <div class="search_con"> 

        <input type="hidden" id="userRole" name="userRole" value="<c:out value="${query.userRole }"></c:out>"/> 
        <input type="hidden" id="userId" name="userId" value="<c:out value="${query.userId }"></c:out>"/> 
        <input type="hidden" id="remainScore" name="remainScore" value="<c:out value="${remainScore }"></c:out>"/> 
        <div class="search_txt"><%=ScoreLog.ALIAS_ADD_TIME%>：
         <input value="<fmt:formatDate value="${query.startTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="startTime" name="startTime" class=" input_text" />至
         <input value="<fmt:formatDate value="${query.endTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="endTime" name="endTime"   class=" input_text" />
         
         
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_SCORE_TYPE%>：
          <select id="scoreType" name="scoreType"> 
            <option  value=""  <c:if test="${query.scoreType==null}">selected="selected"</c:if>>全部</option>
            <option  value="1" <c:if test="${query.scoreType==1}">selected="selected"</c:if>>获得</option>
            <option  value="2" <c:if test="${query.scoreType==2}">selected="selected"</c:if>>消耗</option>
          </select>
        </div>
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit"  class="button" value="查询"><span>查询</span></button>
          </div>
           <div class="input_button">
            <button name="exp" type="button" onclick="exports()" class="button" value="导出"><span>导出</span></button>
          </div>
        </div>
        
      </div>
    </form>
  </div>
  <!--end搜索-->
  
  <form id="admin_jtzs_scoreLog_page_form" name="admin_jtzs_scoreLog_page_form" method="get">
    <table id="admin_jtzs_scoreLog_table">
                         <tr>剩余积分:
                         <c:out value="${remainScore }"></c:out>
                         </tr>
     <thead>
           <tr>
                    <th sortColumn="SCORE_TYPE" showColumn="scoreType" width="100"><%=ScoreLog.ALIAS_SCORE_TYPE%></th>
                    <th sortColumn="CONTENT" showColumn="content" width="300"><%=ScoreLog.ALIAS_CONTENT%></th>
                    <th sortColumn="SCORE" showColumn="score" width="100"><%=ScoreLog.ALIAS_SCORE%></th>
                    <th sortColumn="ADD_TIME" showColumn="addTime" width="100"><%=ScoreLog.ALIAS_ADD_TIME%></th>
                    <th sortColumn="REMARK" showColumn="remark" width="200"><%=ScoreLog.ALIAS_REMARK%></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
              <tr >
                  <td showColumn="scoreType">
                  <c:if test="${item.scoreType == 1}">获得</c:if>
                  <c:if test="${item.scoreType == 2}">消耗</c:if>
                  </td>
                  
                  <td showColumn="content"><c:out value='${item.content}'/></td>
                  
                  <td showColumn="score" align="center"><c:out value='${item.score}'/></td>
 
           
                  <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
           
           
                  <td showColumn="remark"><c:out value='${item.remark}'/></td>
           
           
           
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
		form : "#admin_jtzs_scoreLog_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_scoreLog_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_jtzs_scoreLog_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	function exports(){
		var userId = $("#userId").val();
		var userRole = $("#userRole").val();
		var startTime = $("#startTime").val();
		var endTime  = $("#endTime").val();
		
		$("#ff").attr("action","${ctx}/admin/jtzs/scoreLog/exportData?userId="+userId+"&userRole="+userRole+"&startTime="+startTime+"&endTime="+endTime).submit();
		
	
		
	}
</script> 
