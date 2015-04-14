<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
  <title><%=EventSendToUser.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
    <link href="${ctx}/styles/default/structs_inner.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('listform',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
  
   <!--搜索-->
  	<div class="up72_edit"> 
  <div class="up72_search">
    <form id="admin_jtzs_eventSendToUser_search_form" name="admin_jtzs_eventSendToUser_search_form"  action="${ctx}/admin/jtzs/eventSendToUser/list" >
      <div class="search_con"> 
        
        <div class="search_txt">昵称：
          <input type="text" id="nickName" name="nickName" class="input_text" value="${querys.nickName}">
        </div>

        <div class="search_txt">手机号：
          <input type="text" id="loginName" name="loginName" class="input_text" value="${querys.loginName}">
        </div>

        <div class="search_txt"><%=EventSendToUser.ALIAS_USER_ROLE%>：
          <select name="userRole">
           <option id="userRole0" name="userRole0" value="0"  <c:if test="${querys.userRole == 0}">selected="selected"</c:if>>学生</option>
           <option id="userRole1" name="userRole1" value="1"  <c:if test="${querys.userRole == 1}">selected="selected"</c:if>>老师</option>
          </select>
        </div>
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit"   class="button" value="查询"><span>查询</span></button>
          </div>
          
        </div>
      </div>
    </form>
  </div>
<!--end搜索-->
  <form action="${ctx}/admin/jtzs/eventSendToUser/${eventId}/edit" id="listform"></form>
  <form id="sform" name="sform"  >
   <input type="hidden" name="eventId" id="eventId" value="${eventId }"/>
   <input type="hidden" name="role" id="role" value="${role }"/>
   <input type="hidden" name="idss" id="idss" value=""/>
    <table id="admin_jtzs_eventSendToUser_table">
      <thead>
           <tr align="center">
           <td colspan="3"><font color="purple" size="3">人员列表</font></td>
           <td><button name="ss"  onclick="savess()"   class="button" type="button"  value="添加"><span>添加</span></button>
           </td>
           </tr>
          <tr>
             <th  align="left">
             <input type="checkbox" id="checkall" onclick="setAllCheckboxState('ids',this.checked);" /></th>
                    <th  width="100" showColumn="nickName">姓名</th>
                    <th  width="150" showColumn="loginName">手机号</th>
           </tr>
      </thead>
      <tbody>
         <c:forEach items="${page.result}" var="item" varStatus="status">
            <tr>
            <td width="25">
            <input type="checkbox" id="ids" name="ids" value="${item.id}" class="sel" tags="null">
            </td>
                        <td showColumn="nickName"><c:out value='${item.nickName}'/> </td>
                        <td showColumn="loginName"><c:out value='${item.loginName}'/></td>
             </tr>
         
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  
  <table>
           <thead>
           <tr align="center">
           <td colspan="4"><font color="purple" size="3">已选择人员列表</font></td>
           </tr>
            <tr>
                    <th  width="100" showColumn="nickName">姓名</th>
                    <th  width="150" showColumn="loginName">手机号</th>
                    <th  width="40">删除</th>
           </tr>
           </thead>
            <tbody>
        <c:forEach items="${selList}" var="item" varStatus="status">
        <tr>
                        <td showColumn="nickName"> <c:if test="${item.userRole==0}"><c:out value='${item.student.nickName}'/></c:if>
                              <c:if test="${item.userRole==1}"><c:out value='${item.teacher.nickName}'/></c:if>
                        </td>
                        <td showColumn="loginName">
                              <c:if test="${item.userRole==0}"><c:out value='${item.student.loginName}'/></c:if>
                              <c:if test="${item.userRole==1}"><c:out value='${item.teacher.loginName}'/></c:if>
                        </td>
                        <td align="center" >
                              <a href="javascript:;"   onclick="removes(${item.id})"  class="sysiconBtnNoIcon" ><font color="#eaeaea" size="4">×</font></a>
                        </td>
             </tr>
        </c:forEach>
          </tbody>
        </table>
  </form>
  <script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 排序处理
	$.sortcolumn({
		form : "#admin_jtzs_eventSendToUser_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_eventSendToUser_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	// 表格列表处理
	$('#admin_jtzs_eventSendToUser_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	
	// 列选择显示处理
	 function savess(){
	   var ids = document.getElementsByName('ids');
	   var str = "";
	   for( var i=0;i<ids.length;i++){
	     if(ids[i].checked){
	     str+=ids[i].value+","
	     }
	   }
	    if(str!=""){
	     $("#idss").val(str);
	    
	     $("#sform").attr("action", "${ctx}/admin/jtzs/eventSendToUser/saves");
		 $("#sform").submit();
		 }else{
		 alert('请选择需要发送的人！');
		 return ;
		 }
	   
	 }
	 
	 function removes(id){
	     $("#sform").attr("action", "${ctx}/admin/jtzs/eventSendToUser/"+id+"/remove?eventId="+${eventId });
		 $("#sform").submit();
	 }
</script> 
