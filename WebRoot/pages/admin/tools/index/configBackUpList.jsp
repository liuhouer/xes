<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
		<tr>
			<td>
				<span id="optMessage" style="padding-left:10px; color:#F00; display:none;"></span>
				<c:if test="${fileArray == null || fn:length(fileArray) == 0}">
					<div class="divform"><span>无备份文件！</span></div>
				</c:if>
				<c:forEach var="file" items="${fileArray}" varStatus="stat">
					<div id="itemDiv_${stat.index}" class="divform"><span id="fileSpan_${stat.index}">${file.name}</span> <a href="javascript:;" onclick="revertConfigBackUp(${stat.index});">还原</a>&nbsp;<a href="javascript:;" onclick="deleteConfigBackUp(${stat.index});">删除</a></div>
				</c:forEach>
			</td>
		</tr>
	</table>
</div>
<script language="javascript" type="text/javascript">
revertConfigBackUp = function(index){
	confirm("确认要恢复该配置么？",function(){
		var fileName = $.trim($('#fileSpan_' + index).text());
		$.ajax({
		   type: "POST",
		   url: "${ctx}/admin/tools/index/revertBackUpFile",
		   data: "fileName=" + fileName,
		   success: function(msg){
			  msg = $.trim(msg);
			  if("0" == msg){
				  $("#optMessage").html("备份还原成功！");
				  $("#optMessage").show();
				  setTimeout(" $('#optMessage').hide();",1000);
			  }else if("1" == msg){
				  $("#optMessage").html("备份文件不存在！");
				  $("#optMessage").show();
				  setTimeout(" $('#optMessage').hide();",1000);
			  }else if("2" == msg){
				  $("#optMessage").html("备份还原过程出错！");
				  $("#optMessage").show();
				  setTimeout(" $('#optMessage').hide();",1000);
			  }
		   }
		});	
	});
}
deleteConfigBackUp = function(index){
	confirm("确认要删除该配置么？",function(){
		var fileName = $.trim($('#fileSpan_' + index).text());
		$.ajax({
		   type: "POST",
		   url: "${ctx}/admin/tools/index/deleteBackUpFile",
		   data: "fileName=" + fileName,
		   success: function(msg){
			  msg = $.trim(msg);
			  if("0" == msg){
				  $("#optMessage").html("删除备份文件成功！");
				  $("#optMessage").show();
				  setTimeout(" $('#optMessage').hide();",1000);
				  $("#itemDiv_" + index).remove();
				  if($('.divform').length == 0){
			  		  closeBox();
				  }
			  }else if("1" == msg){
				  $("#optMessage").html("删除备份文件失败！");
				  $("#optMessage").show();
				  setTimeout(" $('#optMessage').hide();",1000);
			  }
		   }
		});	
	});
}
</script>
