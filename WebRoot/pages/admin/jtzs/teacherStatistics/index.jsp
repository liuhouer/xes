<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=TeacherStatistics.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_teacherStatistics_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_teacherStatistics_search_form" name="admin_jtzs_teacherStatistics_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt">教师等级：
          <select id="level" name="level">
			 	<option value="0" <c:if test="${level==0}">selected="selected"</c:if> >全部</option>
			 	<option value="1" <c:if test="${level==1}">selected="selected"</c:if> >教师</option>
			 	<option value="2" <c:if test="${level==2}">selected="selected"</c:if> >专家</option>
			</select>
        </div>

        <div class="search_txt">登录帐号：
          <input type="text" id="loginName" name="loginName" class="input_text" value="${loginName}">
        </div>

        <div class="search_txt">查询时间：
   			<input value="<fmt:formatDate value="${startAddTimeDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
				id="startAddTimeString" name="startAddTimeString"
				class="input_text" type="text" readonly="readonly" style="width:100px;" />- 
			<input value="<fmt:formatDate value="${stopAddTimeDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
				id="stopAddTimeString" name="stopAddTimeString"
				class="input_text" type="text" readonly="readonly" style="width:100px;" />
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
  
  <form id="admin_jtzs_teacherStatistics_page_form" name="admin_jtzs_teacherStatistics_page_form" method="get">
    <table id="admin_jtzs_teacherStatistics_table">
      <thead>
        <tr>
          <th showColumn="index" width="50">序号</th>
          <th showColumn="level" width="50">等级</th>
          <th showColumn="loginName" width="120">登录帐号</th>
          <th sortColumn="SATISFY" showColumn="satisfy" width="80"><%=TeacherStatistics.ALIAS_SATISFY%></th>
          <th sortColumn="UNSATISFY" showColumn="unsatisfy" width="80"><%=TeacherStatistics.ALIAS_UNSATISFY%></th>
          <th sortColumn="SHOW_NUM" showColumn="showNum" width="80"><%=TeacherStatistics.ALIAS_SHOW_NUM%></th>
          <th sortColumn="ANSWER_NUM" showColumn="answerNum" width="80"><%=TeacherStatistics.ALIAS_ANSWER_NUM%></th>
          <th sortColumn="TWENTY_MIN_NUM" showColumn="twentyMinNum" width="80"><%=TeacherStatistics.ALIAS_TWENTY_MIN_NUM%></th>
          <th sortColumn="HALF_HOUR_NUM" showColumn="halfHourNum" width="80"><%=TeacherStatistics.ALIAS_HALF_HOUR_NUM%></th>
          <th sortColumn="ONE_HOUR_NUM" showColumn="oneHourNum" width="80"><%=TeacherStatistics.ALIAS_ONE_HOUR_NUM%></th>
          <th sortColumn="EXPERT_NUM" showColumn="expertNum" width="80"><%=TeacherStatistics.ALIAS_EXPERT_NUM%></th>
          <th sortColumn="QUIT_NUM" showColumn="quitNum" width="80"><%=TeacherStatistics.ALIAS_QUIT_NUM%></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="level">
            	<c:if test="${item[1] ==1}">教师</c:if>
            	<c:if test="${item[1] ==2}">专家</c:if>
            </td>
            <td showColumn="loginName">
            	<c:out value='${item[2]}'/>
            </td>
            <td showColumn="satisfy">
            	<c:if test="${empty item[3]}">0</c:if>
            	<c:if test="${!empty item[3]}"><a href="javascript:;" onclick="show('iframe#${ctx}/admin/jtzs/comment/iframeIndex?isSatisfied=1&teacherId='+${item[0]}, '查看评论', 860, 480);"><c:out value='${item[3]}'/></a></c:if>
            </td>
            <td showColumn="unsatisfy">
            	<c:if test="${empty item[4]}">0</c:if>
            	<c:if test="${!empty item[4]}"><a href="javascript:;" onclick="show('iframe#${ctx}/admin/jtzs/comment/iframeIndex?isSatisfied=0&teacherId='+${item[0]}, '查看评论', 860, 480);"><c:out value='${item[4]}'/></a></c:if>
            </td>
            <td showColumn="showNum">
            	<c:if test="${empty item[5]}">0</c:if>
            	<c:if test="${!empty item[5]}"><c:out value='${item[5]}'/></c:if>
            </td>
            <td showColumn="answerNum">
            	<c:if test="${empty item[6]}">0</c:if>
            	<c:if test="${!empty item[6]}"><c:out value='${item[6]}'/></c:if>
            </td>
            <td showColumn="twentyMinNum">
            	<c:if test="${empty item[7]}">0</c:if>
            	<c:if test="${!empty item[7]}"><c:out value='${item[7]}'/></c:if>
            </td>
            <td showColumn="halfHourNum">
            	<c:if test="${empty item[8]}">0</c:if>
            	<c:if test="${!empty item[8]}"><c:out value='${item[8]}'/></c:if>
            </td>
            <td showColumn="oneHourNum">
            	<c:if test="${empty item[9]}">0</c:if>
            	<c:if test="${!empty item[9]}"><c:out value='${item[9]}'/></c:if>
            </td>
            <td showColumn="expertNum">
            	<c:if test="${empty item[10]}">0</c:if>
            	<c:if test="${!empty item[10]}"><c:out value='${item[10]}'/></c:if>
           	</td>
            <td showColumn="quitNum">
            	<c:if test="${empty item[11]}">0</c:if>
            	<c:if test="${!empty item[11]}"><c:out value='${item[11]}'/></c:if>
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
		form : "#admin_jtzs_teacherStatistics_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_teacherStatistics_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_jtzs_teacherStatistics_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	
	function exports(){
		var loginName          = $("#loginName").val();
		var level           = $("#level").val();
		var startAddTimeString          = $("#stastartAddTimeStringtus").val();
		var stopAddTimeString          = $("#stopAddTimeString").val();
		
		$("#ff").attr("action","${ctx}/admin/jtzs/teacherStatistics/exportData?loginName="+loginName+"&level="+level+"&startAddTimeString="+startAddTimeString+"&stopAddTimeString="+stopAddTimeString).submit();
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
