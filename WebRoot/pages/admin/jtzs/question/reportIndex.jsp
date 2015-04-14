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
			$("#reloadBtn").click(setReload);
			var isRelode = $("#isReload").val();
			if(isRelode==1){
				doReload();
			}
		});
		
		function setReload(){
			var isReload = $("#isReload").val();
			if(isReload == 0){
				$("#isReload").val(1);
				$("#reloadBtn span").text("停止");
			}else{
				$("#isReload").val(0);
				$("#reloadBtn span").text("开启");
			}
			doReload();
		}
		
		function doReload(){
			var isReload = $("#isReload").val();
			if(isReload == 1){
				var time = $("#reloadTime option:selected").val();
				window.setTimeout(reloadPage,time*60*1000);
			}else{
				clearTimeout(reloadPage);
			}
		}
		
		function reloadPage(){
			$("#admin_jtzs_question_search_form").submit();
		}
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_question_search_form" action="${ctx}/admin/jtzs/question/reportIndex" name="admin_jtzs_question_search_form" method="get">
      <div class="search_con"> 
 		<div class="search_txt"><%=Question.ALIAS_REPORT_TIME%>：
          <input value="<fmt:formatDate value="${query.reportTimeBeginDate}" pattern="yyyy-MM-dd HH:mm"/>"
			onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="reportTimeBeginString" name="reportTimeBeginString"
			class="input_text"  type="text" readonly="readonly" style="width:120px;" /> 至 
		  <input value="<fmt:formatDate value="${query.reportTimeEndDate}" pattern="yyyy-MM-dd HH:mm"/>"
			onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})"
			id="reportTimeEndString" name="reportTimeEndString"
			class="input_text" type="text" readonly="readonly" style="width:120px;" />
        </div>

        <div class="search_txt"><%=Question.ALIAS_AUDIT_STATE%>：
         	<select id="auditState" name="auditState" style="width: 80px">
	   			<option value="" <c:if test="${empty query.auditState}">selected="selected"</c:if> >全部</option>
         		<c:forEach items="${auditStateList}" var="item" varStatus="status">
		   			<option value="${item.index}" <c:if test="${item.index == query.auditState}">selected="selected"</c:if> >${item.name}</option>
	         	</c:forEach>
	   		</select>
        </div>

        <div class="search_txt"><%=Question.ALIAS_IS_LOCK%>：
        	<select id="isLock" name="isLock" style="width: 80px">
        		<option value="" <c:if test="${empty query.isLock}">selected="selected"</c:if> >全部</option>
	   			<option value="0" <c:if test="${0 == query.isLock}">selected="selected"</c:if> >正常</option>
        		<option value="1" <c:if test="${1 == query.isLock}">selected="selected"</c:if> >已冻结</option>
        	</select>
        </div>
        
        <div class="search_txt"><%=Question.ALIAS_IS_DEL%>：
        	<select id="isDel" name="isDel" style="width: 80px">
        		<option value="" <c:if test="${empty query.isDel}">selected="selected"</c:if> >全部</option>
        		<option value="0" <c:if test="${0 == query.isDel}">selected="selected"</c:if> >正常</option>
	   			<option value="1" <c:if test="${1 == query.isDel}">selected="selected"</c:if> >已删除</option>
        	</select>
        </div>
        

        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button>
          </div>
        </div>
        <div class="search_txt">自动刷新：
          	<select id="reloadTime" name="reloadTime" style="width: 80px">
	   			<option value="1" <c:if test="${reloadTime==1}"> selected="selected"</c:if> >1分钟</option>
	   			<option value="3" <c:if test="${reloadTime==3}"> selected="selected"</c:if> >3分钟</option>
				<option value="5" <c:if test="${reloadTime==5}"> selected="selected"</c:if> >5分钟</option>
	   			<option value="10" <c:if test="${reloadTime==10}"> selected="selected"</c:if> >10分钟</option>
	   			<option value="20" <c:if test="${reloadTime==20}"> selected="selected"</c:if> >20分钟</option>
	   			<option value="30" <c:if test="${reloadTime==30}"> selected="selected"</c:if> >30分钟</option>
	   			<option value="60" <c:if test="${reloadTime==60}"> selected="selected"</c:if> >60分钟</option>
	   		</select>
        </div>
        <div class="search_btn">
          <div class="input_button">
	   		<input type="hidden" id="isReload" name="isReload" value="${isReload}" />
            <button name="btnU" id="reloadBtn" type="button" class="button">
            <span>
            	<c:if test="${isReload==0}">开启</c:if> 
            	<c:if test="${isReload==1}">停止</c:if> 
            </span>
            </button>
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
          <th showColumn="status" width="30">状态</th>
          <th showColumn="content" width="250"><%=Question.ALIAS_CONTENT%></th>
          <th showColumn="imgPath" width="50"><%=Question.ALIAS_IMG_PATH%></th>
          <th showColumn="reportId" width="50"><%=Question.ALIAS_REPORT_ID%></th>
          <th showColumn="reportContent" width="120"><%=Question.ALIAS_REPORT_CONTENT%></th>
          <th showColumn="reportTime" width="140"><%=Question.ALIAS_REPORT_TIME%></th>
          <th sortColumn="AUDIT_STATE" showColumn="auditState" width="50"><%=Question.ALIAS_AUDIT_STATE%></th>
          <th sortColumn="REPORT_RESULT" showColumn="reportResult" width="50"><%=Question.ALIAS_REPORT_RESULT%></th>
          <th showColumn="option" width="120"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr <c:if test="${item.auditState==0}">style="color:red;"</c:if> >
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="status">
            	<c:choose>
            		<c:when test="${item.isLock==1}">冻结</c:when>
            		<c:when test="${item.isDel==1}">删除</c:when>
	            	<c:when test="${item.isDel==0 && item.isLock==0}">正常</c:when>
            	</c:choose>
            </td>
            <td showColumn="content"><c:out value='${item.content}'/></td>
            <td showColumn="imgPath"><a href="${ctx}${item.imgPath}" rel="clearbox" ><img src="${ctx}${item.imgPath}" width="15px" height="15px"/></a></td>
            <td showColumn="reportId"><c:out value='${item.reportUser.realName}'/></td>
            <td showColumn="reportContent"><c:out value='${item.reportContent}'/></td>
            <td showColumn="reportTime"><fmt:formatDate value="${item.reportTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td showColumn="auditState"><c:out value='${item.auditStateStr}'/></td>
            <td showColumn="reportResult"><c:out value='${item.reportResultStr}'/></td>
            <td showColumn="option">
            	<c:if test="${item.auditState==0}">
	            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/question/${item.id}/reportEdit','<%=Question.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;
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
</script> 
</up72:override>
<%@ include file="base.jsp" %>
