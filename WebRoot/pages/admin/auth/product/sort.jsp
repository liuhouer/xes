<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资源排序</title>
<jsp:include page="/pages/admin/include/getInnerStyleSkin.jsp"></jsp:include>
<script src="<c:url value="/scripts/jquery.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/main.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/application.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/bgiframe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/weebox.js"/>" type="text/javascript"></script>
<link href="<c:url value="/scripts/weebox/weebox.css"/>
" type="text/css" rel="stylesheet" />
<script src="${ctx}/scripts/rest.js" ></script>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	$(document).ready(function() {
		// 分页需要依赖的初始化动作
		window.simpleTable = new SimpleTable('admin_auth_product_page_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</head>
<body>
<table width="100%" border="0">
  <tr>
    <td height="35" width="35"><img width="32" height="32" src="${ctx}/images/window.gif" /></td>
    <td><div style="font-weight: bold;">调整文档顺序</div>
      <div>调整文档顺序，使文档排列在当前对话框中选中的文档之前。</div></td>
  </tr>
</table>
<form id="admin_auth_product_page_form" name="admin_auth_product_page_form">
  <table border="0" cellpadding="0" cellspacing="0" id="gridTable">
    <thead>
      <tr>
        <th showColumn="checkbox" width="25"></th>
        <th showColumn="index" width="25">序号</th>
        <th showColumn="name" sortColumn="NAME" width="150"><%=Product.ALIAS_NAME%></th>
        <th showColumn="description" sortColumn="DESCRIPTION" width="300"><%=Product.ALIAS_DESCRIPTION%></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${page.result}" var="item" varStatus="status">
        <tr>
          <td showColumn="checkbox"><input type="radio" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
          <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
          <td showColumn="name"><img style="vertical-align: top;" height="15" src="${item.imgPath}" onerror="$(this).hide();" />
            <c:out value='${item.name}'/>
            &nbsp;</td>
          <td showColumn="description"><c:out value='${item.description}'/>
            &nbsp;</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
</form>
<div class="up72_edit">
  <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
  </div>
</div>
<script type="text/javascript">
	$('#gridTable tr').bind("click",function(){
		var radio = $(this).find("[type='radio']");
		$(radio).attr("checked","checked");
		// $("#gridTable tr").removeClass("trSelected");
		// $(this).addClass("trSelected");
		// alert($(this).attr("className"));
	});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true
	});
</script>
</body>
</html>