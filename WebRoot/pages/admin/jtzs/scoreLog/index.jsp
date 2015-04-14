<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=ScoreLog.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
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
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_scoreLog_search_form" name="admin_jtzs_scoreLog_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=ScoreLog.ALIAS_SCORE%>：
          <input type="text" id="score" name="score" class="input_text" value="${query.score}">
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_SCORE_ID%>：
          <input type="text" id="scoreId" name="scoreId" class="input_text" value="${query.scoreId}">
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_ADD_TIME%>：
          <input type="text" id="addTime" name="addTime" class="input_text" value="${query.addTime}">
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_OPERATOR_ID%>：
          <input type="text" id="operatorId" name="operatorId" class="input_text" value="${query.operatorId}">
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_REMARK%>：
          <input type="text" id="remark" name="remark" class="input_text" value="${query.remark}">
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_CONTENT%>：
          <input type="text" id="content" name="content" class="input_text" value="${query.content}">
        </div>

        <div class="search_txt"><%=ScoreLog.ALIAS_SCORE_TYPE%>：
          <input type="text" id="scoreType" name="scoreType" class="input_text" value="${query.scoreType}">
        </div>
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!--end搜索-->
  
  <form id="admin_jtzs_scoreLog_page_form" name="admin_jtzs_scoreLog_page_form" method="get">
    <table id="admin_jtzs_scoreLog_table">
      <thead>
        <tr>
          <th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
          <th showColumn="index" width="20">序号</th>
          <th showColumn="option" width="30"><label>操作</label></th>
                    <th sortColumn="SCORE" showColumn="score" width="50"><%=ScoreLog.ALIAS_SCORE%></th>
                    <th sortColumn="SCORE_ID" showColumn="scoreId" width="50"><%=ScoreLog.ALIAS_SCORE_ID%></th>
                    <th sortColumn="ADD_TIME" showColumn="addTime" width="50"><%=ScoreLog.ALIAS_ADD_TIME%></th>
                    <th sortColumn="OPERATOR_ID" showColumn="operatorId" width="50"><%=ScoreLog.ALIAS_OPERATOR_ID%></th>
                    <th sortColumn="REMARK" showColumn="remark" width="50"><%=ScoreLog.ALIAS_REMARK%></th>
                    <th sortColumn="CONTENT" showColumn="content" width="50"><%=ScoreLog.ALIAS_CONTENT%></th>
                    <th sortColumn="SCORE_TYPE" showColumn="scoreType" width="50"><%=ScoreLog.ALIAS_SCORE_TYPE%></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr rel="${ctx}/pages/admin/jtzs/scoreLog/tab.jsp?id=${item.id}">
            <td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/scoreLog/${item.id}/edit','<%=ScoreLog.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
                        <td showColumn="score"><c:out value='${item.score}'/>
&nbsp; </td>
                        <td showColumn="scoreId"><c:out value='${item.scoreId}'/>
&nbsp; </td>
                        <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
&nbsp; </td>
                        <td showColumn="operatorId"><c:out value='${item.operatorId}'/>
&nbsp; </td>
                        <td showColumn="remark"><c:out value='${item.remark}'/>
&nbsp; </td>
                        <td showColumn="content"><c:out value='${item.content}'/>
&nbsp; </td>
                        <td showColumn="scoreType"><c:out value='${item.scoreType}'/>
&nbsp; </td>
             </tr>
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  </form>
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
	
	$("#admin_jtzs_scoreLog_table").rowAction({	
		"url" : "/pages/admin/jtzs/scoreLog/tab.jsp",
		"except" : ["checkbox","index","option"],
		"pop" : true,
		"poptitle" : "<%=ScoreLog.TABLE_ALIAS%>标签",
		"popw" : 600
	});
	// 表格列表处理
	$('#admin_jtzs_scoreLog_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/scoreLog/new","<%=ScoreLog.TABLE_ALIAS%>添加",600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/jtzs/scoreLog/',batchBox:'items',boxCon:'#admin_jtzs_scoreLog_page_form',form:'#admin_jtzs_scoreLog_page_form',method:'delete'})}}
		]
	});
</script> 
</up72:override>
<%@ include file="base.jsp" %>
