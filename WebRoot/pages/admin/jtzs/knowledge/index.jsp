<%@page import="com.xes.jtzs.model.*" %>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Knowledge.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动 作   
			window.simpleTable = new SimpleTable('admin_jtzs_knowledge_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_knowledge_search_form" name="admin_jtzs_knowledge_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=Knowledge.ALIAS_GRADE%>：
          <select id="gradeId" name="gradeId" style="width: 120px">
            <option value="" <c:if test="${knowledge.gradeId ==''}">selected="selected"</c:if> ><c:out value="全部"></c:out></option>
       		<c:forEach items="${gradeList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == query.gradeId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		  </select>
        </div>

        <div class="search_txt"><%=Knowledge.ALIAS_SUBJECT%>：
           <select id="subjectId" name="subjectId" style="width: 120px">
           <option value="" <c:if test="${knowledge.subjectId ==''}">selected="selected"</c:if> ><c:out value="全部"></c:out></option>
       		<c:forEach items="${subjectList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == query.subjectId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
	      </select>
        </div>

        <div class="search_txt"><%=Knowledge.ALIAS_KNOWLEDGE%>：
          <input type="text" id="knowledge" name="knowledge" class=" input_txt" value="${query.knowledge}">
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
  
  <form id="admin_jtzs_knowledge_page_form" name="admin_jtzs_knowledge_page_form" method="get">
    <table id="admin_jtzs_knowledge_table">
      <thead>
        <tr>
          <th showColumn="index" width="50" align="center">序号</th>
          <th sortColumn="GRADE_ID" showColumn="gradeId" width="80"      align="center"><%=Knowledge.ALIAS_GRADE%></th>
          <th sortColumn="SUBJECT_ID" showColumn="subjectId" width="80"  align="center"><%=Knowledge.ALIAS_SUBJECT%></th>
          <th sortColumn="KNOWLEDGE1" showColumn="knowledge1" width="80" align="center"><%=Knowledge.ALIAS_KNOWLEDGE1%></th>
          <th sortColumn="KNOWLEDGE2" showColumn="knowledge2" width="80" align="center"><%=Knowledge.ALIAS_KNOWLEDGE2%></th>
          <th sortColumn="KNOWLEDGE3" showColumn="knowledge3" width="80" align="center"><%=Knowledge.ALIAS_KNOWLEDGE3%></th>
          <th sortColumn="ADD_TIME" showColumn="addTime" width="120" align="center"><%=Knowledge.ALIAS_ADD_TIME%></th>
          <th showColumn="option" width="30"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
                        <td showColumn="gradeId">
	                        <c:forEach items="${gradeList}" var="itemss" varStatus="status">
	                        <c:out value="${knowledge.gradeId}"></c:out>
				 	        <c:if test="${itemss.id == item.gradeId}"><c:out value='${itemss.name}'/> </c:if>  
	       		            </c:forEach>
                        </td>
                       
                        <td showColumn="subjectId">

                        <c:forEach items="${subjectList}" var="itemss" varStatus="status">
			 	        <c:if test="${itemss.id == item.subjectId}"><c:out value='${itemss.name}'/> </c:if>  
       		            </c:forEach>
                 &nbsp; </td>
                 
                        <td showColumn="knowledge1"><c:out value='${item.knowledge1}'/>
                 &nbsp; </td>
                        
                        <td showColumn="knowledge2"><c:out value='${item.knowledge2}'/>
                 &nbsp; </td>
                        
                        <td showColumn="knowledge3"><c:out value='${item.knowledge3}'/>
                 &nbsp; </td>
                        
                        <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
                 &nbsp; </td>
            <td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/knowledge/${item.id}/edit','<%=Knowledge.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
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
	$('#admin_jtzs_knowledge_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/knowledge/new","<%=Knowledge.TABLE_ALIAS%>添加",600)}}
		]
	});
</script> 
</up72:override>
<%@ include file="base.jsp" %>
