<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title><%=CrawlerResource.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	 
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	<link href="${ctx}/common.css" type="text/css" rel="stylesheet">
	<link href="${ctx}/default.css" type="text/css" rel="stylesheet">
	
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.js"></script>
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.async.js"></script>
  <link type="text/css" href="${ctx}/scripts/jq_tree/jquery.treeview.css" rel="stylesheet" />
</up72:override>

<up72:override name="content">
<table width="100%" height="*" cellspacing="0" cellpadding="0" border="0" class="js_layoutTable" id="js_layoutTable">
	<tbody>
	  <tr valign="top">
		<td width="350" height="100%" class="centerColumnWrap" style="height: 701px;">
		  <table width="100%" height="*" cellspacing="0" cellpadding="0" border="0" class="js_layoutTable" id="js_layoutTable2">
			<tbody>
			  <tr>
				<td height="33">
					<input type="button" onclick="doSearch()" value="新增调查" id="Submitbutton" name="Submitbutton"></div>
					<input type="button" onclick="doSearch()" value="修改" id="Submitbutton" name="Submitbutton"></div>
					<input type="button" onclick="doSearch()" value="删除" id="Submitbutton" name="Submitbutton"></div>
					<input type="button" onclick="doSearch()" value="预览" id="Submitbutton" name="Submitbutton"></div>
					<input type="button" onclick="doSearch()" value="发布" id="Submitbutton" name="Submitbutton"></div>
         	   </td>
			 </tr>
			 <tr>
				<td style="padding: 0 1px">
					<div style="padding: 4px;" class="gradient">名称:
					<input type="text" style="width: 110px" onfocus="delKeyWord();" placeholder="调查名称" id="SearchContent" name="SearchContent">
					<input type="button" onclick="doSearch()" value="搜索" id="Submitbutton" name="Submitbutton"></div>
				</td>
			</tr>
			<tr>
				<td height="*" style="padding: 0px 1px; height: 639px;">
				   <div ztype="_DataGridWrapper" class="z-datagrid dg_scrollable dg_nobr" id="dg1_Wrap" style="height: 99.4%;">
				   		<div class="dg_head" id="dg1_Wrap_head">
				   			<table width="100%" cellspacing="0" cellpadding="0" class="dg_headTable" cachesize="0" lazy="false" scroll="true" autopagesize="true" autofill="true" multiselect="true" method="Vote.getVoteDataGrid" size="0" page="true" sortstring="">
								<tbody><tr class="dg_headTr" ztype="head">
									<th width="10%" field="id" ztype="selector"><div class="dg_th" id="dataTable0_th0"><input type="checkbox" onclick="Zving.DataGrid.onAllCheckClick('dg1')" id="dg1_AllCheck" value="*"></div></th>
									<th width="40%"><div class="dg_th" id="dataTable0_th1">名称</div></th>
									<th width="30%"><div class="dg_th" id="dataTable0_th2">别名</div></th>
								</tr>
								</tbody>
							</table>
						</div>
						<div style="height: 577px;" class="dg_body" id="dg1_Wrap_body">
							<table width="100%" cellspacing="0" cellpadding="0" cachesize="0" lazy="false" scroll="true" autopagesize="true" autofill="true" multiselect="true" method="Vote.getVoteDataGrid" size="0" page="true" id="dg1" sortstring="">
								<tbody>
									<tr class="dg_headTr" ztype="head">
										<th width="10%" field="id" ztype="selector"><div class="dg_th" id="dataTable0_th0"><input type="checkbox" onclick="Zving.DataGrid.onAllCheckClick('dg1')" id="dg1_AllCheck" value="*"></div></th>
										<th width="40%"><div class="dg_th" id="dataTable0_th1">名称</div></th>
										<th width="30%"><div class="dg_th" id="dataTable0_th2">别名</div></th>
									</tr>
									<tr oncontextmenu="Zving.DataGrid._onContextMenu(this,event)" onclick="Zving.DataGrid.onRowClick(this,event);showVoteItems();" ondblclick="edit();">
										<td ondblclick="stopEvent(event);" onclick="Zving.DataGrid.onSelectorClick(this,event);" class="selector"><input type="checkbox" value="11" id="dg1_RowCheck1" name="dg1_RowCheck"></td>
										<td>专题投票</td>
										<td>zttp</td>
									</tr>
									<tr oncontextmenu="Zving.DataGrid._onContextMenu(this,event)" onclick="Zving.DataGrid.onRowClick(this,event);showVoteItems();" ondblclick="edit();" style="background-color: rgb(216, 247, 157);">
										<td ondblclick="stopEvent(event);" onclick="Zving.DataGrid.onSelectorClick(this,event);" class="selector"><input type="checkbox" value="24" id="dg1_RowCheck2" name="dg1_RowCheck"></td>
										<td>首页调查控件</td>
										<td>indexvote</td>
									</tr>
							</tbody>
						  </table>
					</div>
				</div>
				</td>
			</tr>
		</tbody>
	</table>
		</td>
		<td style="height: 701px;">
		<div class="z-splitter-v shadow-v" id="splitter1"></div>
		<iframe width="100%" scrolling="no" height="100%" frameborder="0" allowtransparency="true" src="${ctx}/admin/sys/vote/voteItem" id="TabFrame"></iframe></td>
	</tr>
</tbody></table>
</up72:override>
<%@ include file="base.jsp" %>