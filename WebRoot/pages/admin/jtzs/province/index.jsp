<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.xes.jtzs.JTZSConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Province.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_jtzs_province_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_province_search_form" name="admin_jtzs_province_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=Province.ALIAS_NAME%>：
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
  
  <form id="admin_jtzs_province_page_form" name="admin_jtzs_province_page_form" method="get">
    <table id="admin_jtzs_province_table">
      <thead>
        <tr>
        <!-- 
          <th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
         -->
          <th showColumn="index" width="50">序号</th>
          <th showColumn="name" width="400"><%=Province.ALIAS_NAME%></th>
          <th showColumn="status" width="100">状态</th>          
          <th showColumn="option" width="100"><label>操作</label></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr>
          <!-- 
            <td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
           -->
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="name"><c:out value='${item.name}'/></td>
            <td showColumn="status"><c:out value='${item.statusStr}'/></td>
            <td showColumn="option">
            	<a href="javascript:;"  onclick="show('${ctx}/admin/jtzs/province/${item.id}/edit','<%=Province.TABLE_ALIAS%>编辑',600)" class="sysiconBtnNoIcon">编辑</a>
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
	$('#admin_jtzs_province_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/province/new","<%=Province.TABLE_ALIAS%>添加",600)}}
		]
	});

	function doValid(id,item){
		var $item = $(item);
		$.ajax({
			url : "${ctx}/admin/jtzs/province/"+id+"/doValid",
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
