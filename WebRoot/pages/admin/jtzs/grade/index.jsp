<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Grade.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_grade_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_grade_search_form" name="admin_jtzs_grade_search_form" method="get">
      <div class="search_con"> 
        <div class="search_txt"><%=Grade.ALIAS_NAME%>：
          <input type="text" id="name" name="name" class="input_text" value="${query.name}">
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
  
  <form id="admin_jtzs_grade_page_form" name="admin_jtzs_grade_page_form" method="get">
    <table id="admin_jtzs_grade_table">
      <thead>
        <tr>
          <th showColumn="index" width="50">序号</th>
          <th showColumn="division" width="200"><%=Grade.ALIAS_DIVISION%></th>
          <th showColumn="name" width="200"><%=Grade.ALIAS_NAME%></th>
          <th showColumn="option" width="200"><label>操作</label></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="division"><c:out value='${item.divisionName}'/></td>
            <td showColumn="name"><c:out value='${item.name}'/></td>
            <td showColumn="option">
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/grade/${item.id}/edit','<%=Grade.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/grade/${item.id}/editRela','<%=Grade.TABLE_ALIAS%>',200)" class="sysiconBtnNoIcon">编辑与学科关系</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  </form>
  <script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	
	// 表格列表处理
	$('#admin_jtzs_grade_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/grade/new","<%=Grade.TABLE_ALIAS%>添加",600)}}
		]
	});
</script> 
</up72:override>
<%@ include file="base.jsp" %>
