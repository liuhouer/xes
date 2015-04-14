<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Teacher.TABLE_ALIAS%> 维护</title>
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
			window.simpleTable = new SimpleTable('admin_jtzs_teacher_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_teacher_search_form" name="admin_jtzs_teacher_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=Teacher.ALIAS_LOGIN_NAME%>：
          <input type="text" id="loginName" name="loginName" class="input_text" value="${query.loginName}">
        </div>

        <div class="search_txt"><%=Teacher.ALIAS_REAL_NAME%>：
          <input type="text" id="realName" name="realName" class="input_text" value="${query.realName}">
        </div>
        
        <div class="search_txt"><%=Teacher.ALIAS_STATUS%>：
            <select id="status" name="status" style="width: 120px">
	   			<option value="" <c:if test="${empty query.status}">selected="selected"</c:if> >全部</option>
	   			<option value="0" <c:if test="${0 == query.status}">selected="selected"</c:if> >冻结</option>
	   			<option value="1" <c:if test="${1 == query.status}">selected="selected"</c:if> >正常</option>
	   		</select>
        </div>
        
        <div class="search_txt"><%=Teacher.ALIAS_PROVINCE_ID%>：
	   		<select id="provinceId" name="provinceId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.provinceId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${provinceList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.provinceId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
        </div>

        <div class="search_txt"><%=Teacher.ALIAS_EXPERT_GRADE_IDS%>：
       		<select id="expertGradeId" name="expertGradeId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.expertGradeId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${gradeList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.expertGradeId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
        </div>

        <div class="search_txt"><%=Teacher.ALIAS_EXPERT_SUBJECT_ID%>：
	   		<select id="expertSubjectId" name="expertSubjectId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.expertSubjectId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${subjectList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.expertSubjectId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
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
  
  <form id="admin_jtzs_teacher_page_form" name="admin_jtzs_teacher_page_form" method="get">
    <table id="admin_jtzs_teacher_table">
      <thead>
        <tr>
          <th showColumn="index" width="20">序号</th>
          <th sortColumn="STATUS" showColumn="status" width="50"><%=Teacher.ALIAS_STATUS%></th>
          <th sortColumn="PROVINCE_ID" showColumn="provinceId" width="40"><%=Teacher.ALIAS_PROVINCE_ID%></th>
          <th sortColumn="LEVEL" showColumn="level" width="40"><%=Teacher.ALIAS_LEVEL%></th>
          <th sortColumn="NICK_NAME" showColumn="nickName" width="50"><%=Teacher.ALIAS_NICK_NAME%></th>
          <th sortColumn="REAL_NAME" showColumn="realName" width="50"><%=Teacher.ALIAS_REAL_NAME%></th>
          <th sortColumn="LOGIN_NAME" showColumn="loginName" width="100"><%=Teacher.ALIAS_LOGIN_NAME%></th>
          <th sortColumn="EXPERT_GRADE_IDS" showColumn="expertGradeIds" width="200"><%=Teacher.ALIAS_EXPERT_GRADE_IDS%></th>
          <th sortColumn="EXPERT_SUBJECT_Id" showColumn="expertSubjectId" width="50"><%=Teacher.ALIAS_EXPERT_SUBJECT_ID%></th>
          <th sortColumn="SEX" showColumn="sex" width="30"><%=Teacher.ALIAS_SEX%></th>
          <th sortColumn="IMG_PATH" showColumn="imgPath" width="30"><%=Teacher.ALIAS_IMG_PATH%></th>
          <th sortColumn="LAST_LOGIN_TIME" showColumn="lastLoginTime" width="120"><%=Teacher.ALIAS_LAST_LOGIN_TIME%></th>
          <th sortColumn="ADD_TIME" showColumn="addTime" width="120"><%=Teacher.ALIAS_ADD_TIME%></th>
          <th showColumn="option" width="120"><label>操作</label></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="status"><c:out value='${item.statusStr}'/></td>
            <td showColumn="provinceId"><c:out value='${item.province.name}'/></td>
            <td showColumn="level"><c:out value='${item.levelStr}'/></td>
            <td showColumn="nickName"><c:out value='${item.nickName}'/></td>
            <td showColumn="realName"><c:out value='${item.realName}'/></td>
            <td showColumn="loginName"><c:out value='${item.loginName}'/></td>
            <td showColumn="expertGradeIds">
            	<c:forEach items="${item.expertGradeList}" var="grade">
	            	<c:out value='${grade.name}'/>&nbsp;&nbsp;
            	</c:forEach>
            </td>
            <td showColumn="expertSubjectId"><c:out value='${item.expertSubject.name}'/></td>
			<td showColumn="sex"><c:out value='${item.sexStr}'/></td>
            <td showColumn="imgPath"><a href="${ctx}${item.imgPath}" rel="clearbox" ><img src="${ctx}${item.imgPath}" width="15px" height="15px"/></a></td>
            <td showColumn="lastLoginTime"><fmt:formatDate value="${item.lastLoginTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td showColumn="option">
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/teacher/${item.id}/edit','<%=Teacher.TABLE_ALIAS%>',820)" class="sysiconBtnNoIcon">编辑</a>&nbsp;
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/teacher/${item.id}/editPassword','<%=Teacher.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">修改密码</a>&nbsp;
        	    <c:forEach items="${statusArray}" var="item2">
            		<c:if test="${item2.index != item.status}">
		            	<a href="javascript:;" onclick="doValid(${item.id},this)" id="statusBtn" class="sysiconBtnNoIcon">${item2.name}</a>
            		</c:if>
            	</c:forEach>
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
		form : "#admin_jtzs_teacher_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_teacher_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_jtzs_teacher_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/teacher/new","<%=Teacher.TABLE_ALIAS%>添加",820)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/jtzs/teacher/',batchBox:'items',boxCon:'#admin_jtzs_teacher_page_form',form:'#admin_jtzs_teacher_page_form',method:'delete'})}}
		]
	});
	
	function doValid(id,item){
		var $item = $(item);
		$.ajax({
			url : "${ctx}admin/jtzs/teacher/"+id+"/doValid",
			type : "post",
			dataType : "json",
			success : function(jsondatas){
				if(jsondatas.status=='success'){
					if(jsondatas.valid=='<%=JTZSConstants.Status.NORMAL.getIndex()%>'){
						$item.text('<%=JTZSConstants.Status.FREEZE.getName()%>');
						$item.closest("tr").find("td[showColumn='status'] div").text('<%=JTZSConstants.Status.NORMAL.getName()%>');
					}else{
						$item.text('<%=JTZSConstants.Status.NORMAL.getName()%>');
						$item.closest("tr").find("td[showColumn='status'] div").text('<%=JTZSConstants.Status.FREEZE.getName()%>');
					}
				}
			},
			error : function(){
			}
		});
	}
	
	function exports(){
		var loginName          = $("#loginName").val();
		var realName           = $("#realName").val();
		var status          = $("#status").val();
		var provinceId          = $("#provinceId").val();
		var expertGradeId          = $("#expertGradeId").val();
		var expertSubjectId          = $("#expertSubjectId").val();
		
		$("#ff").attr("action","${ctx}/admin/jtzs/teacher/exportData?loginName="+loginName+"&status="+status+"&provinceId="+provinceId+"&realName="+realName+"&expertGradeId="+expertGradeId+"&expertSubjectId="+expertSubjectId).submit();
		
	
		
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
