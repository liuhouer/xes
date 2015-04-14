<%@page import="com.up72.sys.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=SysCategory.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('admin_sys_sysCategory_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--当前位置-->
  <div class="head_content">
    <div class="navBar"> » <a class="" href="${ctx}/admin/sys/sysCategory/"><%=SysCategory.TABLE_ALIAS%>管理</a> </div>
  </div>
  <!--end当前位置--> 
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_sys_sysCategory_search_form" name="admin_sys_sysCategory_search_form" method="get">
      <div class="search_con"> 

        <div class="search_txt"><%=SysCategory.ALIAS_GUID%>：
          <input type="text" id="guid" name="guid" class="input_text" value="${query.guid}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_CAT_NAME%>：
          <input type="text" id="catName" name="catName" class="input_text" value="${query.catName}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_PARENT_GUID%>：
          <input type="text" id="parentGuid" name="parentGuid" class="input_text" value="${query.parentGuid}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_CONFIG_SOURCE%>：
          <input type="text" id="configSource" name="configSource" class="input_text" value="${query.configSource}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_CONFIG_ID%>：
          <input type="text" id="configId" name="configId" class="input_text" value="${query.configId}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_CONTENT_MODEL_ID%>：
          <input type="text" id="contentModelId" name="contentModelId" class="input_text" value="${query.contentModelId}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_LIST_URL_PATH%>：
          <input type="text" id="listUrlPath" name="listUrlPath" class="input_text" value="${query.listUrlPath}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_CONFIG_URL_PATH%>：
          <input type="text" id="configUrlPath" name="configUrlPath" class="input_text" value="${query.configUrlPath}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_SORT_ID%>：
          <input type="text" id="sortId" name="sortId" class="input_text" value="${query.sortId}">
        </div>

        <div class="search_txt"><%=SysCategory.ALIAS_ADD_TIME%>：
          <input type="text" id="addTime" name="addTime" class="input_text" value="${query.addTime}">
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
  
  <form id="admin_sys_sysCategory_page_form" name="admin_sys_sysCategory_page_form" method="get">
    <table id="admin_sys_sysCategory_table">
      <thead>
        <tr>
          <th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
          <th showColumn="index" width="20">序号</th>
          <th showColumn="option" width="30"><label>操作</label></th>
                    <th sortColumn="GUID" showColumn="guid" width="50"><%=SysCategory.ALIAS_GUID%></th>
                    <th sortColumn="CAT_NAME" showColumn="catName" width="50"><%=SysCategory.ALIAS_CAT_NAME%></th>
                    <th sortColumn="PARENT_GUID" showColumn="parentGuid" width="50"><%=SysCategory.ALIAS_PARENT_GUID%></th>
                    <th sortColumn="CONFIG_SOURCE" showColumn="configSource" width="50"><%=SysCategory.ALIAS_CONFIG_SOURCE%></th>
                    <th sortColumn="CONFIG_ID" showColumn="configId" width="50"><%=SysCategory.ALIAS_CONFIG_ID%></th>
                    <th sortColumn="CONTENT_MODEL_ID" showColumn="contentModelId" width="50"><%=SysCategory.ALIAS_CONTENT_MODEL_ID%></th>
                    <th sortColumn="LIST_URL_PATH" showColumn="listUrlPath" width="50"><%=SysCategory.ALIAS_LIST_URL_PATH%></th>
                    <th sortColumn="CONFIG_URL_PATH" showColumn="configUrlPath" width="50"><%=SysCategory.ALIAS_CONFIG_URL_PATH%></th>
                    <th sortColumn="SORT_ID" showColumn="sortId" width="50"><%=SysCategory.ALIAS_SORT_ID%></th>
                    <th sortColumn="ADD_TIME" showColumn="addTime" width="50"><%=SysCategory.ALIAS_ADD_TIME%></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr rel="${ctx}/pages/admin/sys/sysCategory/tab.jsp?id=${item.id}">
            <td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
            <td showColumn="option"><a href="javascript:;"  onclick="show('${ctx}/admin/sys/sysCategory/${item.id}/edit','<%=SysCategory.TABLE_ALIAS%>',600)" class="sysiconBtnNoIcon">编辑</a>&nbsp;</td>
                        <td showColumn="guid"><c:out value='${item.guid}'/>
&nbsp; </td>
                        <td showColumn="catName"><c:out value='${item.catName}'/>
&nbsp; </td>
                        <td showColumn="parentGuid"><c:out value='${item.parentGuid}'/>
&nbsp; </td>
                        <td showColumn="configSource"><c:out value='${item.configSource}'/>
&nbsp; </td>
                        <td showColumn="configId"><c:out value='${item.configId}'/>
&nbsp; </td>
                        <td showColumn="contentModelId"><c:out value='${item.contentModelId}'/>
&nbsp; </td>
                        <td showColumn="listUrlPath"><c:out value='${item.listUrlPath}'/>
&nbsp; </td>
                        <td showColumn="configUrlPath"><c:out value='${item.configUrlPath}'/>
&nbsp; </td>
                        <td showColumn="sortId"><c:out value='${item.sortId}'/>
&nbsp; </td>
                        <td showColumn="addTime"><fmt:formatDate value="${item.addTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
&nbsp; </td>
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
		form : "#admin_sys_sysCategory_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_sys_sysCategory_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	$("#admin_sys_sysCategory_table").rowAction({	
		"url" : "/pages/admin/sys/sysCategory/tab.jsp",
		"except" : ["checkbox","index","option"],
		"pop" : true,
		"poptitle" : "<%=SysCategory.TABLE_ALIAS%>标签",
		"popw" : 600
	});
	// 表格列表处理
	$('#admin_sys_sysCategory_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/sys/sysCategory/new","<%=SysCategory.TABLE_ALIAS%>添加",600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/sys/sysCategory/',batchBox:'items',boxCon:'#admin_sys_sysCategory_page_form',form:'#admin_sys_sysCategory_page_form',method:'delete'})}}
		]
	});
</script> 
</up72:override>
<%@ include file="base.jsp" %>
