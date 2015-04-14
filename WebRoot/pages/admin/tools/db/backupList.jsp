<%@page import="com.bruce.model.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="up72_edit">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
    <c:if test="${num == 0}">
      
        <td width="50">&nbsp;</td>
        <td colspan="4"> 该数据库暂时无备份历史！ </td>
    </c:if>
    <c:if test="${num > 0}">
      <tr>
        <td width="50"> 序号 </td>
        <td width="200"> 备份名称 </td>
        <td width="100"> 文件大小 </td>
        <td width="80"> 日志 </td>
        <td width="100"> 操作 </td>
      </tr>
      <c:set var="num" value="0" />
      <c:forEach items="${dataMap}" var="item" varStatus="status">
        <c:set var="num" value="${status.index + 1}" />
        <tr id="th_${status.index + 1}">
          <td> ${status.index + 1} </td>
          <td> ${item.value[0]} </td>
          <td> ${item.value[1]}K </td>
          <td><c:if test="${'1' eq item.value[2]}"> <a href="javascript:" onclick="admin_tools_db_loadLog('${item.value[0]}');">日志</a> </c:if>
            <c:if test="${'0' eq item.value[2]}">无错误日志</c:if></td>
          <td><a href="javascript:" onclick="admin_tools_db_revert(${dblink.id},'${item.value[0]}');">还原</a>&nbsp; <a href="javascript:" onclick="admin_tools_db_delBackUp(${status.index + 1}, '${item.value[0]}');">删除</a>&nbsp; </td>
        </tr>
      </c:forEach>
    </c:if>
  </table>
  <div id="message" style="display:none; color:red; width:80%; text-align:right;"></div>
  <div id="log" style="display:none;"></div>
</div>
<script>
//删除数据库备份
function admin_tools_db_delBackUp(thIndex, fileName){
	confirm("确认要删除该备份么？此操作无法恢复！",
		function(){
			$.ajax({
				type: "POST",
				url: "${ctx}/admin/tools/db/delBackUp",
				data: "fileName=" + fileName,
				dataType: "text",
				success: function(code){
					if($.trim(code) == '0'){
						$("#message").html("删除成功！");
						$("#message").show().delay(2000).fadeOut(400);
						$("#th_" + thIndex).remove();
					}else{
						$("#message").html("删除失败！");
						$("#message").show().delay(2000).fadeOut(400);
					}
				} 
			});
		}
	);
}
//加载错误日志
function admin_tools_db_loadLog(fileName){
	$.ajax({
		type: "POST",
		url: "${ctx}/admin/tools/db/loadLog",
		data: "fileName=" + fileName,
		dataType: "html",
		success: function(html){
			$("#log").html(html).show();
		} 
	});
}

//还原数据库
function admin_tools_db_revert(linkId, fileName){
	confirm("确认使用该备份还原数据库么？此操作无法恢复！",
		function(){
			$.ajax({
				type: "POST",
				async: "false",
				url: "${ctx}/admin/tools/db/revert",
				data: "id=" + linkId + "&fileName=" + fileName,
				dataType: "json",
				success: function(result){
					var html = "<div style='color: red; heigh: 200px; padding: 0 20px;'> Notice:" + result.resultMessage + "</div>";
					$("#log").html(html).show();
				} 
			});
		}
	);
}
</script> 
