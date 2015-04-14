<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
  <title>索引管理</title>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" >
	indexClassName = function(){
		show('${ctx}/pages/admin/tools/index/add.jsp','索引配置添加',600);
	} 
	indexAdd = function(){
		$.ajax({
		   type: "POST",
		   url: "${ctx}/admin/tools/index/add",
		   data: $("#admin_tools_index_add_form").serialize(),
		   success: function(msg){
			  closeBox();
			  var obj = eval("(" + msg + ")");
			  if("1" == obj.resultCode){
				  alert("请输入类名！");  
			  }else if("2" == obj.resultCode){
				  alert("输入的类不存在！");  
			  }else if("3" == obj.resultCode){
				  indexEdit(obj.resultData);
			  }else{
				  indexNew(obj.resultData);
			  }
		   }
		});	
	}
	indexNew = function(className){
		show('${ctx}/admin/tools/index/new?className=' + className,'索引配置添加',600);
	} 
	indexEdit = function(className){
		show('${ctx}/admin/tools/index/edit?className=' + className,'索引配置修改',600);
	}
	deleteIndexData = function(){
		doRestEdit({confirmMsg:'确认删除选中配置的索引数据么？',url:'${ctx}/admin/tools/index/deleteIndexData',batchBox:'items',boxCon:'#gridTable',form:'#admin_tools_index_page_form',method:'get'});
	}
	deleteIndex = function(){
		doRestEdit({confirmMsg:'删除索引配置会同时删除索引数据，确定要删除么？',url:'${ctx}/admin/tools/index/deleteIndex',batchBox:'items',boxCon:'#gridTable',form:'#admin_tools_index_page_form',method:'get'});
	}
	indexDatabase = function(className){
		alert("索引建立中...");
		$.ajax({
		   type: "POST",
		   async: false,
		   url: "${ctx}/admin/tools/index/indexDatabase",
		   data: "className=" + className,
		   success: function(msg){
			  alert("共索引记录" + msg + "条", 3);
			  window.location.reload();
		   }
		});			
		//doRestEdit({confirmMsg:'确定索引数据库数据？',url:'${ctx}/admin/tools/index/indexDatabase',batchBox:'items',boxCon:'#gridTable',form:'#admin_tools_index_page_form',method:'post'});
	}
	indexBackUp = function(className){
		$.ajax({
		   type: "POST",
		   url: "${ctx}/admin/tools/index/indexBackUp",
		   data: "className=" + className,
		   success: function(msg){
			  msg = $.trim(msg);
			  if("0" == msg){
			  	  alert("备份配置成功！", 3);
			  }else if("1" == msg){
			  	  alert("备份配置失败！", 3);
			  }
			  window.location.reload();
		   }
		});	
	}
</script>
</up72:override>
<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
	<div class="navBar" style="display:none;">  » <a class="" href="${ctx }/admin/tools/index/">索引管理</a></div>
</div>
<!-- END 当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_tools_index_search_form" name="admin_tools_index_search_form" action="${ctx }/admin/tools/index">
	<div class="search_con">
		<div class="search_txt">类名称：<input type="text" id="className" name="className" class="input_txt" value="${className}"></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="submit" class="" value="查询"><span>查询</span></button></div></div>
	</div>
  </form>
</div>
<!--end查询--> 
<form id="admin_tools_index_page_form" name="admin_tools_index_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="index" width="25">序号</th>
				<th showColumn="option" width="130"><label>操作</label></th>
				<th showColumn="name" width="600">数据模型</th>
			</tr>
		</thead>
		<tbody id="listBody" class="gNetfolder-list">
		<c:forEach var="model" items="${modelList}" varStatus="status">
			<tr>
				<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${model}" class="sel" tags="null"></td>
				<td showColumn="index">${status.index + 1}</td>
				<td showColumn="option"><a href="javascript:;" onclick="indexEdit('${model}');">编辑</a>&nbsp;<a href="javascript:;" onclick="indexBackUp('${model}');">备份配置</a>&nbsp;<a href="javascript:;" onclick="indexDatabase('${model}');">索引测试</a></td>
				<td showColumn="name"><b>${model}</b>&nbsp;</td>
			</tr>
		</c:forEach>
 	 	</tbody>
	</table>
</form>
<div style="margin:5px;">
	<c:forEach var="info" items="${indexInfo}" varStatus="stat">
	<span style="padding-right:10px;"><label>${info.key}:</label>${info.value}&nbsp;&nbsp;</span>
	</c:forEach>
</div>
<script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '新增配置', bclass: 'addorder', onpress : function(){indexClassName();}},
			{name: '清除索引数据', bclass: '', onpress : function(){deleteIndexData();}},
			//{name: '更新索引数据', bclass: '', onpress : function(){alert('太消耗资源，不执行！');}},
			{name: '删除配置', bclass: 'delete', onpress : function(){deleteIndex();}},
			{name: '增加索引主词', bclass: '', onpress : function(){show('${ctx}/pages/admin/tools/index/addDicWord.jsp',"增加索引主词",500);}},
			{name: '增加索引停词', bclass: '', onpress : function(){show('${ctx}/pages/admin/tools/index/addDicStopWord.jsp',"增加索引停词",500);}},
			{name: '备份清单', bclass: '', onpress : function(){show('${ctx}/admin/tools/index/configBackUpList',"备份清单", 600);}}
			//{name: '索引查询', bclass: '', onpress : function(){show('${ctx}/pages/admin/tools/index/indexSearch.jsp','索引查询测试',600);}}
		]
	});
</script>
</up72:override>
<%@ include file="base.jsp" %>