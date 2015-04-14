<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Comment.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_comment_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_comment_search_form" name="admin_jtzs_comment_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=Comment.ALIAS_CONTENT%>：
          <input type="text" id="content" name="content" class="input_text" value="${query.content}">
        </div>

        <div class="search_txt"><%=Comment.ALIAS_STUDENT_ID%>：
          <input type="text" id="studentId" name="studentId" class="input_text" value="${query.studentId}">
        </div>

        <div class="search_txt"><%=Comment.ALIAS_ADD_TIME%>：
          <input type="text" id="addTime" name="addTime" class="input_text" value="${query.addTime}">
        </div>

        <div class="search_txt"><%=Comment.ALIAS_IS_SATISFIED%>：
          <input type="text" id="isSatisfied" name="isSatisfied" class="input_text" value="${query.isSatisfied}">
        </div>

        <div class="search_txt"><%=Comment.ALIAS_ANSWER_ID%>：
          <input type="text" id="answerId" name="answerId" class="input_text" value="${query.answerId}">
        </div>

        <div class="search_txt"><%=Comment.ALIAS_IS_DEL%>：
          <input type="text" id="isDel" name="isDel" class="input_text" value="${query.isDel}">
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
  
  <form id="admin_jtzs_comment_page_form" name="admin_jtzs_comment_page_form" method="get">
    <table id="admin_jtzs_comment_table">
      <thead>
        <tr>
          <th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
          <th showColumn="index" width="20">序号</th>
          <th showColumn="option" width="30"><label>操作</label></th>
                    <th sortColumn="CONTENT" showColumn="content" width="50"><%=Comment.ALIAS_CONTENT%></th>
                    <th sortColumn="STUDENT_ID" showColumn="studentId" width="50"><%=Comment.ALIAS_STUDENT_ID%></th>
                    <th sortColumn="ADD_TIME" showColumn="addTime" width="50"><%=Comment.ALIAS_ADD_TIME%></th>
                    <th sortColumn="IS_SATISFIED" showColumn="isSatisfied" width="50"><%=Comment.ALIAS_IS_SATISFIED%></th>
                    <th sortColumn="ANSWER_ID" showColumn="answerId" width="50"><%=Comment.ALIAS_ANSWER_ID%></th>
                    <th sortColumn="IS_DEL" showColumn="isDel" width="50"><%=Comment.ALIAS_IS_DEL%></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr rel="${ctx}/pages/admin/jtzs/comment/tab.jsp?id=${item.id}">
            <td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/comment/${item.id}/edit','<%=Comment.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
                        <td showColumn="content"><c:out value='${item.content}'/>
&nbsp; </td>
                        <td showColumn="studentId"><c:out value='${item.studentId}'/>
&nbsp; </td>
                        <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
&nbsp; </td>
                        <td showColumn="isSatisfied"><c:out value='${item.isSatisfied}'/>
&nbsp; </td>
                        <td showColumn="answerId"><c:out value='${item.answerId}'/>
&nbsp; </td>
                        <td showColumn="isDel"><c:out value='${item.isDel}'/>
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
		form : "#admin_jtzs_comment_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_comment_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	

	// 表格列表处理
	$('#admin_jtzs_comment_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/comment/new","<%=Comment.TABLE_ALIAS%>添加",600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/jtzs/comment/',batchBox:'items',boxCon:'#admin_jtzs_comment_page_form',form:'#admin_jtzs_comment_page_form',method:'delete'})}}
		]
	});
</script> 
</up72:override>
<%@ include file="base.jsp" %>
