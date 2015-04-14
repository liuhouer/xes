<%@page import="com.xes.jtzs.model.*" %>
<%@page import="com.xes.jtzs.JTZSConstants" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=WrongRules.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_wrongRules_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_wrongRules_search_form" name="admin_jtzs_wrongRules_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=WrongRules.ALIAS_ROLE%>：
         <select id="role" name="role" style="width: 120px"  >
                <option value="" >请选择</option>
			 	<option value="<%=JTZSConstants.ROLE_STUDENT %>" <c:if test="${query.role == 0}">selected="selected"</c:if>>学生</option>
			 	<option value="<%=JTZSConstants.ROLE_TEACHER %>" <c:if test="${query.role == 1}">selected="selected"</c:if>>老师</option>
	</select>
        </div>

        <div class="search_txt"><%=WrongRules.ALIAS_WRONG_NUM%>：
            <select id="wrongNum" name="wrongNum" style="width: 120px"  >
                <option value="" >请选择</option>
			 	<option value="1" <c:if test="${wrongRules.wrongNum == 1}">selected="selected"</c:if>>1</option>
			 	<option value="2" <c:if test="${wrongRules.wrongNum == 2}">selected="selected"</c:if>>2</option>
			 	<option value="3" <c:if test="${wrongRules.wrongNum == 3}">selected="selected"</c:if>>3</option>
			 	<option value="4" <c:if test="${wrongRules.wrongNum == 4}">selected="selected"</c:if>>4</option>
			 	<option value="5" <c:if test="${wrongRules.wrongNum == 5}">selected="selected"</c:if>>5</option>
	        </select>
        </div>


        <div class="search_txt">问题是否删除：   
           <select id="isDelQuestion" name="isDelQuestion" style="width: 120px">
                <option value="" >请选择</option>
			 	<option value="<%=JTZSConstants.IsDel.DELETE.getIndex() %>" <c:if test="${query.isDelQuestion == 1}">selected="selected"</c:if>>是</option>
			 	<option value="<%=JTZSConstants.IsDel.UNDELETE.getIndex() %>" <c:if test="${query.isDelQuestion == 0}">selected="selected"</c:if>>否</option>
	</select>
        </div>

        <div class="search_txt"><%=WrongRules.ALIAS_DEL_SCORE%>：
          <input type="text" name="delScoreStart" id=delScoreStart" style="width:40px" maxlength="4" value="${query.delScoreStart}"  onblur="clearNoNum(this)" /> - 
          <input type="text" name="delScoreEnd" id="delScoreEnd" style="width:40px" maxlength="4" value="${query.delScoreEnd}"  onblur="clearNoNum(this)" />
        </div>

        <div class="search_txt">是否冻结帐号：
           <select id="isStopLogin" name="isStopLogin" style="width: 120px">
                <option value="" >请选择</option>
			 	<option value="<%=JTZSConstants.Status.NORMAL.getIndex() %>" <c:if test="${query.isStopLogin == 1}">selected="selected"</c:if>>是</option>
			 	<option value="<%=JTZSConstants.Status.FREEZE.getIndex() %>" <c:if test="${query.isStopLogin == 0}">selected="selected"</c:if>>否</option>
	      </select>
        </div>

        <%-- <div class="search_txt"><%=WrongRules.ALIAS_ADD_TIME%>：
          <input type="text" id="addTime" name="addTime" class="input_text" value="${query.addTime}">
        </div> --%>
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!--end搜索-->
  
  <form id="admin_jtzs_wrongRules_page_form" name="admin_jtzs_wrongRules_page_form" method="get">
    <table id="admin_jtzs_wrongRules_table">
      <thead>
        <tr>
          <th showColumn="index" width="20">序号</th>
          <th sortColumn="ROLE" showColumn="role" width="50"><%=WrongRules.ALIAS_ROLE%></th>
          <th sortColumn="WRONG_NUM" showColumn="wrongNum" width="50"><%=WrongRules.ALIAS_WRONG_NUM%></th>
          <th sortColumn="CONTENT" showColumn="content" width="550"><%=WrongRules.ALIAS_CONTENT%></th>
          <th sortColumn="IS_DEL_QUESTION" showColumn="isDelQuestion" width="80">问题是否删除</th>
          <th sortColumn="DEL_SCORE" showColumn="delScore" width="80"><%=WrongRules.ALIAS_DEL_SCORE%></th>
          <th sortColumn="IS_STOP_LOGIN" showColumn="isStopLogin" width="80">是否冻结帐号</th>
          <th sortColumn="ADD_TIME" showColumn="addTime" width="140"><%=WrongRules.ALIAS_ADD_TIME%></th>
          <th showColumn="option" width="30"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
                        <td showColumn="role"><%-- <c:out value='${item.role}'/> --%><c:if test="${item.role == 0}">学生</c:if><c:if test="${item.role == 1}">老师</c:if>
&nbsp; </td>
                        <td showColumn="wrongNum"><c:out value='${item.wrongNum}'/>
&nbsp; </td>
                        <td showColumn="content"><c:out value='${item.content}'/>
&nbsp; </td>
                        <td showColumn="isDelQuestion"><%-- <c:out value='${item.isDelQuestion}'/> --%><c:if test="${item.isDelQuestion == 0}">否</c:if><c:if test="${item.isDelQuestion == 1}">是</c:if>
&nbsp; </td>
                        <td showColumn="delScore"><c:out value='${item.delScore}'/>
&nbsp; </td>
                        <td showColumn="isStopLogin"><%-- <c:out value='${item.isStopLogin}'/> --%><c:if test="${item.isStopLogin == 0}">否</c:if><c:if test="${item.isStopLogin == 1}">是</c:if>
&nbsp; </td>
                        <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
&nbsp; </td>
                        <td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/wrongRules/${item.id}/edit','<%=WrongRules.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
             </tr>
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  </form>
  <script type="text/javascript"><!--
	// 列选择显示处理
	$.showcolumn(${showColumn});

	// 表格列表处理
	$('#admin_jtzs_wrongRules_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/wrongRules/new","<%=WrongRules.TABLE_ALIAS%>添加",600)}}
		]
	});
	 function clearNoNum(obj){ 
		        var reg = new RegExp("^[0-9]*$");
		        var val = obj.value;
		        if(val!=null&&val!=""){
			        if(!reg.test(val)){
			        	alert("扣除分数应输入数字！");
			        	obj.value="";    
			        	document.getElementById("delScore").focus();
			        	return;
			        }
		        }
		    
		       
	  }
--></script> 
</up72:override>
<%@ include file="base.jsp" %>
