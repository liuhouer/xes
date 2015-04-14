<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title>文件管理</title>
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<link href="${ctx}/scripts/filelist/filelist.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript" src="${ctx}/scripts/filelist/initFileList.js"></script>
<script src="${ctx}/scripts/clearbox/clearbox.js" type="text/javascript"></script>
<script type="text/javascript" >
var ctxUrl = "${ctx}";
var delUrl = ctxUrl+"/admin/tools/file/delete";
var unzipUrl = ctxUrl+"/admin/tools/file/unzipFile";
var renameUrl = ctxUrl+"/admin/tools/file/doEdit";
var createUrl = ctxUrl+"/admin/tools/file/doCreate";
	$(document).ready(function() {
		window.simpleTable = new SimpleTable('admin_tools_file_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
	});
</script>
</up72:override>
<up72:override name="content">
<!-- 当前位置 -->
<div class="head_content">
	<div class="navBar" style="display:none;">  » <a class="" target="up71_iframe_main" href="${ctx }/admin/tools/file/">文件管理</a></div>
</div>
<!-- END 当前位置 -->
<!--查询-->
<div class="up72_search">
  <form method="get" id="admin_tools_file_search_form" name="admin_tools_file_search_form" action="${ctx}/admin/tools/file/">
	<div class="search_con">
		<input type="hidden" id="dir" name="dir" class="input_txt" value="${filePath}" />
		 <!---文件夹遍历开始-->
		  <div>
		   路径：<a href="${ctx}/admin/tools/file/">根目录</a>
		   <c:forEach items="${pathMap}" var="item" varStatus="status">
		   		&ensp;&gt;&gt;&ensp;<a href="${ctx}/admin/tools/file/?dir=${item.key }">${item.value }</a>
		   </c:forEach>
		  </div>
  		<!---文件夹遍历结束-->
  		<div class="search_txt">排序：
  			<select id="sortType" name="sortType" style="width: 160px;">
				<option <c:if test="${sortType == 1 }">selected="selected"</c:if> value="1">文件名称</option>
				<option <c:if test="${sortType == 2 }">selected="selected"</c:if> value="2">文件大小</option>
				<option <c:if test="${sortType == 3 }">selected="selected"</c:if> value="3">修改时间</option>
			</select>
  		</div>
  		<div class="search_txt">排序规则：
  			<select id="sortRule" name="sortRule" style="width: 160px;">
				<option <c:if test="${sortRule == 1 }">selected="selected"</c:if> value="1">正序</option>
				<option <c:if test="${sortRule == 2 }">selected="selected"</c:if> value="2">倒序</option>
			</select>
  		</div>
		<div class="search_btn">
			<div class="input_button">
				<button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button>
			</div>
		</div>
	</div>
  </form>
</div>
<!--end查询--> 
<form id="admin_tools_file_page_form" name="admin_tools_file_page_form">
  	<table id="gridTable">
		<thead>
			<tr>
				<th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
				<th showColumn="name" width="450">文件名</th>
				<th showColumn="size" width="80">文件大小</th>
				<th showColumn="filePath" width="300">文件路径</th>
				<th showColumn="editTime" width="120">修改日期</th>
			</tr>
		</thead>
		<tbody id="listBody" class="gNetfolder-list">
		<tr>
			<c:if test="${dir!=null}">
				<c:choose>
					<c:when test="${dir==''}">
						<td></td>
						<td colspan="4"><a href="${ctx}/admin/tools/file/" ><b class="ico ico-back"></b>返回上一级</a></td>
					</c:when>
					<c:otherwise>
						<td></td>
						<td colspan="4"><a href="${ctx}/admin/tools/file/?dir=${dir}" ><b class="ico ico-back"></b>返回上一级</a></td>
					</c:otherwise>
				</c:choose>
			</c:if>
		</tr>
	  <c:forEach items="${page.result}" var="item" varStatus="status">
			<tr>
			<c:choose>
				<c:when test="${item.type==2}">
					<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.filePath}" class="sel"></td>
					<td showColumn="name"><a href="${ctx}/admin/tools/file/?dir=${item.filePath}"><c:out value='${item.name}'/></a>
						<span class="txt-info">(${item.fileCount})</span>
						<span class="oprt txt-link disspan"></span>
					</td>
					<td showColumn="size"></td>
					<td showColumn="filePath"><c:out value='${item.filePath}'/></td>
					<td showColumn="editTime"><fmt:formatDate value='${item.editTime}' pattern='yyyy-MM-dd HH:mm'/></td>
					<td><span style="display: none;" id="filetype"><c:out value='${item.type}'/></span></td>
				</c:when>
				<c:otherwise>
					<td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.filePath}" class="sel"></td>
					<td showColumn="name"><c:out value='${item.name}'/>
						<span class="txt-info" title=""></span>
						<span class="oprt txt-link disspan"></span>
					</td>
					<td showColumn="size"><c:out value='${item.size}'/></td>
					<td showColumn="filePath"><c:out value='${item.filePath}'/></td>
					<td showColumn="editTime"><fmt:formatDate value='${item.editTime}' pattern='yyyy-MM-dd HH:mm'/></td>
					<td><span style="display: none;" id="filetype"><c:out value='${item.type}'/></span></td>
				</c:otherwise>
			</c:choose>
			</tr>
		</c:forEach>
 	 	</tbody>
	</table>
	<simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
</form>
<div id = "uploadFrameDiv"></div>
<script type="text/javascript">
	// 表格列表处理
	$('#gridTable').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: '创建目录', bclass: 'addorder', onpress : function(){show('${ctx}/admin/tools/file/create?filePath=${filePath}',"添加目录",400)}},
			{name: '上传文件', bclass: 'addorder', onpress : function(){optionShow({url:'/admin/tools/file/upload?filePath=${filePath}',width:420,title:'文件上传',onclose:reloadList})}},
			{name: '删除文件', bclass: 'delete', onpress : function(){doDeleteFile({url:'${ctx}/admin/tools/file/deleteFileList',batchBox:'items',form:'#admin_tools_file_page_form'})}},
			{name: '下载', bclass: '', onpress : function(){doDownloadFile({url:'${ctx}/pages/admin/tools/file/download.jsp',batchBox:'items',boxCon:'#admin_tools_file_page_form',form:'#admin_tools_file_page_form',method:'post'})}}
		]
	});
	
	doDeleteFile = function(options){
		if (!isSelect(options.batchBox,options.boxCon)) {
				alert("请选择你要操作的数据!");
				return;
		}
		var selOptions = getSelectedDom(options.batchBox,options.boxCon);
		var items = "";
		$(selOptions).each(
			function(i,obj){
				if(i!=0){items +="&";}
				items += options.batchBox+"="+$(obj).val();
			}
		);
		
		confirm("文件删除后不可恢复！是否确认？",function(){
		 	$.ajax({
		       type: "POST",
		       url: options.url,
		       cache: false,
		       data: items,
		       success: onDelBackMsg,
		       error: onDelBackMsg
		    });
		});
	}
	onDelBackMsg = function(result){
		result = eval("(" + result + ")");
		var msg = "";
		if(result.successFile > 0 || result.errorFile>0){
			msg += "删除文件：" + result.successFile + "个 , "+errorFile + "个失败";
		}
		if(result.successDir > 0 || result.errorDir > 0){
			msg += "<br>删除目录：" + result.successDir + "个 , " + result.errorDir + "个失败";
			if(result.errorDir > 0){
				msg += "<br>失败原因可能是目录不为空！";
			}
		}
		if(msg){
			alert(msg,2,reloadList);
		}
	}
	
	doDownloadFile = function(options){
		var toDownloadFile = function(){
			if (!isSelect(options.batchBox,options.boxCon)) {
				alert("请选择你要操作的数据!");
				return;
			}
			$(options.form).attr("action",options.url);
			$(options.form).attr("method",'POST');
			
			/*添加操作类型*/
			if(!isNull(options.method)){
				var methodHtml = "<input type='hidden' name='_method' value='"+options.method+"' />";
				$(options.form).append(methodHtml);
			}
			var selOptions = getSelectedDom(options.batchBox,options.boxCon);
			var boxform = $($(selOptions)[0]).parents("form").attr("id");
			var subform = options.form;
			if(startWith(subform,"#")|| startWith(subform,".")){
				subform = subform.substr(1);
			}
			
			if(boxform != subform){
				$(selOptions).each(
					function(i,obj){
						var option = "<input style='display:none' type='"+$(obj).attr("type")+"' name='"+options.batchBox+"' value='"+$(obj).val()+"' checked='checked' />";
						$(options.form).append(option);
					}
				);
			}
			$(options.form).submit();
		}
		
		if(isNull(options.boxCon)){
			options.boxCon = options.form;
		}
		toDownloadFile();
	}
	$(document).ready(fWpInitList());
</script>
</up72:override>
<%@ include file="base.jsp" %>