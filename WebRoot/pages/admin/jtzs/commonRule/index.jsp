<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=CommonRule.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_commonRule_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_commonRule_search_form" name="admin_jtzs_commonRule_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=CommonRule.ALIAS_TITLE%>：
          <input type="text" id="title" name="title" class="input_text" value="${query.title}">
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
  
  <form id="admin_jtzs_commonRule_page_form" name="admin_jtzs_commonRule_page_form" method="get">
    <table id="admin_jtzs_commonRule_table">
      <thead>
        <tr>
          <th showColumn="index" width="50">序号</th>
          <th showColumn="title" width="220"><%=CommonRule.ALIAS_TITLE%></th>
          <th showColumn="score" width="300">值</th>
          <th showColumn="stopTime" width="210">有效期</th>
          <th showColumn="editUserId" width="80"><%=CommonRule.ALIAS_EDIT_USER_ID%></th>
          <th showColumn="editTime" width="120"><%=CommonRule.ALIAS_EDIT_TIME%></th>
          <th showColumn="status" width="50"><%=CommonRule.ALIAS_STATUS%></th>
          <th showColumn="option" width="80"><label>操作</label></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
	        <td showColumn="title"><c:out value='${item.title}'/></td>
	        <td showColumn="score">
		        <c:if test="${item.ruleType==4 || item.ruleType==3}">
					${item.beginTimeStr}&nbsp;至&nbsp;${item.endTimeStr}
		        </c:if>
    		    <c:if test="${item.ruleType==5}">
					${item.minute}分钟
		        </c:if>
		        <c:if test="${!empty item.num && item.ruleType==4}">
		        	数量：${item.num}
		        </c:if>
		        <c:if test="${!empty item.scoreType && item.scoreType==1}">
		        	增加：<c:out value='${item.score}'/>分
		        </c:if>
				<c:if test="${!empty item.scoreType && item.scoreType==2}">
		        	扣除：<c:out value='${item.score}'/>分
		        </c:if>	
			</td>
			<td showColumn="stopTime">
			<c:if test="${item.validStartTime!=null && item.validStopTime!=null}">
				<fmt:formatDate value="${item.validStartTimeDate}" pattern="yyyy-MM-dd"/>&nbsp;至&nbsp;<fmt:formatDate value="${item.validStopTimeDate}" pattern="yyyy-MM-dd"/>
			</c:if>
			</td>
			<td showColumn="editUserId"><c:out value='${item.editUser.nickName}'/></td>
	        <td showColumn="editTime"><fmt:formatDate value="${item.editTimeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td showColumn="status"><c:out value='${item.statusStr}'/></td>
	        </td>
             <td showColumn="option">
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/commonRule/${item.id}/edit','<%=CommonRule.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;
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
  <script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 表格列表处理
	$('#admin_jtzs_commonRule_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
		]
	});
	function doValid(id,item){
		var $item = $(item);
		$.ajax({
			url : "${ctx}/admin/jtzs/commonRule/"+id+"/doValid",
			type : "post",
			dataType : "json",
			success : function(jsondatas){
				if(jsondatas.status=='success'){
					if(jsondatas.valid=='<%=JTZSConstants.Pubilc.DISABLE.getIndex()%>'){
						$item.text('<%=JTZSConstants.Pubilc.ENABLED.getName()%>');
						$item.closest("tr").find("td[showColumn='status'] div").text('<%=JTZSConstants.Pubilc.DISABLE.getName()%>');
					}else{
						$item.text('<%=JTZSConstants.Pubilc.DISABLE.getName()%>');
						$item.closest("tr").find("td[showColumn='status'] div").text('<%=JTZSConstants.Pubilc.ENABLED.getName()%>');
					}
				}
			},
			error : function(){
			}
		});
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
