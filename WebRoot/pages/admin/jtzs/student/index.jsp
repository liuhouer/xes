<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Student.TABLE_ALIAS%> 维护</title>
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
			window.simpleTable = new SimpleTable('admin_jtzs_student_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
			makeSelectSchool();
			$("#admin_jtzs_student_search_form #provinceId").change(function(){makeSelectSchool()});
			$("#admin_jtzs_student_search_form #gradeId").change(function(){makeSelectSchool()});
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_student_search_form" name="admin_jtzs_student_search_form" method="get">
      <div class="search_con"> 
        <div class="search_txt"><%=Student.ALIAS_LOGIN_NAME%>：
          <input type="text" id="loginName" name="loginName" class="input_text" value="${query.loginName}">
        </div>

        <div class="search_txt"><%=Student.ALIAS_STATUS%>：
        	<select id="status" name="status" style="width: 120px">
	   			<option value="" <c:if test="${empty query.status}">selected="selected"</c:if> >全部</option>
	   			<option value="0" <c:if test="${0 == query.status}">selected="selected"</c:if> >冻结</option>
	   			<option value="1" <c:if test="${1 == query.status}">selected="selected"</c:if> >正常</option>
	   		</select>
        </div>

        <div class="search_txt"><%=Student.ALIAS_PROVINCE_ID%>：
	   		<select id="provinceId" name="provinceId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.provinceId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${provinceList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.provinceId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
        </div>

        <div class="search_txt"><%=Student.ALIAS_GRADE_ID%>：
	   		<select id="gradeId" name="gradeId" style="width: 120px">
	   			<option value="" <c:if test="${item.id == query.gradeId}">selected="selected"</c:if> >全部</option>
	       		<c:forEach items="${gradeList}" var="item" varStatus="status">
				 	<option value="${item.id}" <c:if test="${item.id == query.gradeId}">selected="selected"</c:if> >${item.name}</option>
	       		</c:forEach>
			</select>
        </div>
        
        <div class="search_txt"><%=Student.ALIAS_SCHOOL_ID%>：
        	<select id="schoolId" name="schoolId">
			 	<option value="">全部</option>
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
  
  <form id="admin_jtzs_student_page_form" name="admin_jtzs_student_page_form" method="get">
    <table id="admin_jtzs_student_table">
      <thead>
        <tr>
          <th showColumn="index" width="50">序号</th>
          <th sortColumn="STATUS" showColumn="status" width="50"><%=Student.ALIAS_STATUS%></th>
          <th sortColumn="PROVINCE_ID" showColumn="provinceId" width="50"><%=Student.ALIAS_PROVINCE_ID%></th>
          <th sortColumn="NICK_NAME" showColumn="nickName" width="50"><%=Student.ALIAS_NICK_NAME%></th>
          <th sortColumn="LOGIN_NAME" showColumn="loginName" width="100"><%=Student.ALIAS_LOGIN_NAME%></th>
          <th sortColumn="SCHOOL_ID" showColumn="schoolId" width="150"><%=Student.ALIAS_SCHOOL_ID%></th>
          <th sortColumn="GRADE_ID" showColumn="gradeId" width="100"><%=Student.ALIAS_GRADE_ID%></th>
          <th sortColumn="SEX" showColumn="sex" width="50"><%=Student.ALIAS_SEX%></th>
          <th sortColumn="IMG_PATH" showColumn="imgPath" width="30"><%=Student.ALIAS_IMG_PATH%></th>
          <th sortColumn="LAST_LOGIN_TIME" showColumn="lastLoginTime" width="120"><%=Student.ALIAS_LAST_LOGIN_TIME%></th>
          <th sortColumn="ADD_TIME" showColumn="addTime" width="120"><%=Student.ALIAS_ADD_TIME%></th>
          <th showColumn="option" width="120"><label>操作</label></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="status"><c:out value='${item.statusStr}'/></td>
            <td showColumn="provinceId"><c:out value='${item.province.name}'/></td>
            <td showColumn="nickName"><c:out value='${item.nickName}'/></td>
            <td showColumn="loginName"><c:out value='${item.loginName}'/></td>
            <td showColumn="schoolId"><c:out value='${item.school.name}'/></td>
            <td showColumn="gradeId"><c:out value='${item.grade.name}'/></td>
            <td showColumn="sex"><c:out value='${item.sexStr}'/></td>
            <td showColumn="imgPath"><a href="${ctx}${item.imgPath}" rel="clearbox" ><img src="${ctx}${item.imgPath}" width="15px" height="15px"/></a></td>
            <td showColumn="lastLoginTime"><fmt:formatDate value="${item.lastLoginTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td showColumn="option">
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/student/${item.id}/edit','<%=Student.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/student/${item.id}/editPassword','<%=Student.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">修改密码</a>&nbsp;
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
		form : "#admin_jtzs_student_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_student_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});

	// 表格列表处理
	$('#admin_jtzs_student_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/student/new","<%=Student.TABLE_ALIAS%>添加",600)}}
		]
	});
	
	
	function makeSelectSchool(){
	   var provinceId = $("#admin_jtzs_student_search_form #provinceId option:selected").val();
	   var gradeId = $("#admin_jtzs_student_search_form #gradeId option:selected").val();
	   $.ajax({
			url : "${ctx}/admin/jtzs/school/queryJsonSchoolList",
			type : "post",
			data : "provinceId="+provinceId+"&gradeId="+gradeId,
			dataType : "json",
			success : function(jsondatas){
				var schoolList = eval(jsondatas.schoolList);
				var html = '<option value="" <c:if test="${item.id == query.schoolId}">selected="selected"</c:if> >全部</option>';
				for (index in schoolList){
					html+='<option value="'+schoolList[index].id+'" ';
					if('${query.schoolId}'==schoolList[index].id){
						html+='selected="selected"';
					}
					html+=' >'+decodeURIComponent(schoolList[index].name)+'</option>';
				}
				$("#admin_jtzs_student_search_form #schoolId").html(html);
			},
			error : function(){
			}
		});
	}
	
	function doValid(id,item){
		var $item = $(item);
		$.ajax({
			url : "${ctx}/admin/jtzs/student/"+id+"/doValid",
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
		var status          = $("#status").val();
		var provinceId          = $("#provinceId").val();
		var gradeId          = $("#gradeId").val();
		var schoolId          = $("#schoolId").val();
		
		$("#ff").attr("action","${ctx}/admin/jtzs/student/exportData?loginName="+loginName+"&status="+status+"&provinceId="+provinceId+"&gradeId="+gradeId+"&schoolId="+schoolId).submit();
		
	
		
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
