<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Question.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
  <script src="${ctx}/scripts/clearbox/clearbox.js" type="text/javascript"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_question_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_question_search_form" name="admin_jtzs_question_search_form" method="get">
      <div class="search_con"> 
        <div class="search_txt"><%=Question.ALIAS_GRADE_ID%>：
	   		<select id="gradeId" name="gradeId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.gradeId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${gradeList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.gradeId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
        </div>

        <div class="search_txt"><%=Question.ALIAS_SUBJECT_ID%>：
       	   	<select id="subjectId" name="subjectId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.subjectId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${subjectList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.subjectId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
        </div>

        <div class="search_txt"><%=Question.ALIAS_KNOWLEDGE_ID%>：
        	<select id="isKnowledge" name="isKnowledge" style="width: 120px">
	   			<option value="" <c:if test="${empty query.isKnowledge}">selected="selected"</c:if> >全部</option>
	   			<option value="0" <c:if test="${0 == query.isKnowledge}">selected="selected"</c:if> >否</option>
	   			<option value="1" <c:if test="${1 == query.isKnowledge}">selected="selected"</c:if> >是</option>
 			</select>
        </div>

        <div class="search_txt"><%=Question.ALIAS_STATUS%>：
        	<select id="status" name="status" style="width: 120px">
        		<option value="" <c:if test="${empty query.status}">selected="selected"</c:if> >全部</option>
        		<option value="0" <c:if test="${0 == query.status}">selected="selected"</c:if> >无人作答</option>
	   			<option value="1" <c:if test="${1 == query.status}">selected="selected"</c:if> >正在作答</option>
	   			<option value="2" <c:if test="${2 == query.status}">selected="selected"</c:if> >作答完毕</option>
        	</select>
        </div>
        <div class="search_txt"><%=Question.ALIAS_IS_LOCK%>：
        	<select id="isLock" name="isLock" style="width: 120px">
        		<option value="" <c:if test="${empty query.isLock}">selected="selected"</c:if> >全部</option>
	   			<option value="0" <c:if test="${0 == query.isLock}">selected="selected"</c:if> >正常</option>
        		<option value="1" <c:if test="${1 == query.isLock}">selected="selected"</c:if> >已冻结</option>
        	</select>
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
  
  <form id="admin_jtzs_question_page_form" name="admin_jtzs_question_page_form" method="get">
    <table id="admin_jtzs_question_table">
      <thead>
        <tr>
          <th showColumn="index" width="20">序号</th>
          <th showColumn="status" width="70"><%=Question.ALIAS_STATUS%></th>
          <th showColumn="content" width="200"><%=Question.ALIAS_CONTENT%></th>
          <th showColumn="imgPath" width="50"><%=Question.ALIAS_IMG_PATH%></th>
          <th showColumn="gradeId" width="40"><%=Question.ALIAS_GRADE_ID%></th>
          <th showColumn="subjectId" width="40"><%=Question.ALIAS_SUBJECT_ID%></th>
          <th showColumn="knowledgeId" width="40"><%=Question.ALIAS_KNOWLEDGE_ID%></th>
          <th sortColumn="ADD_TIME" showColumn="addTime" width="140"><%=Question.ALIAS_ADD_TIME%></th>
          <th sortColumn="STOP_TIME" showColumn="stopTime" width="80">剩余作答时间</th>
          <th showColumn="answerTeacherId" width="80"><%=Question.ALIAS_ANSWER_TEACHER_ID%></th>
          <th showColumn="sourceType" width="80"><%=Question.ALIAS_SOURCE_TYPE%></th>
          <th showColumn="option" width="120"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr rel="${ctx}/pages/admin/jtzs/question/tab.jsp?id=${item.id}">
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="status">
              <c:if test="${item.isLock==1}">
              	已冻结
              </c:if>
              <c:if test="${item.isLock!=1}">
            	<c:out value='${item.statusStr}'/>
              </c:if>
            </td>
            <td showColumn="content"><c:out value='${item.content}'/></td>
            <td showColumn="imgPath"><a href="${ctx}${item.imgPath}" rel="clearbox" ><img src="${ctx}${item.imgPath}" width="15px" height="15px"/></a></td>
            <td showColumn="gradeId"><c:out value='${item.grade.name}'/></td>
            <td showColumn="subjectId"><c:out value='${item.subject.name}'/></td>
            <td showColumn="knowledgeId"><c:out value='${item.hasKnowledge}'/></td>
            <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td showColumn="stopTime">${item.restTimeStr}</td>
            <td showColumn="answerTeacherId">
             <c:if test="${item.status!=0 && item.isLock!=1}">
            	<c:out value='${item.teacher.nickName}'/>
             </c:if>
            </td>
            <td showColumn="sourceType"><c:out value='${item.sourceTypeStr}'/></td>
            <td showColumn="option">
            	<c:if test="${item.status==2}">
            		<a href="javascript:;"  onclick="addKnowledge(${item.id})" class="sysiconBtnNoIcon">分配知识点</a>
            	</c:if>
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
	// 排序处理
	$.sortcolumn({
		form : "#admin_jtzs_question_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_question_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_jtzs_question_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	
	function addKnowledge(id){
		show("iframe#${ctx}/admin/jtzs/question/selectKnowledge?questionId=" + id, "分配知识点", 630, 480);
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
