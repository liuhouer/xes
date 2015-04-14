<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="up72_search">
  <form method="get" id="admin_tools_searchHistory_top_search_form" name="admin_tools_searchHistory_top_search_form">
	<div class="search_con">
		<div class="search_txt">关键词：<input type="text" id="keyWords" name="keyWords" class="input_txt" value="${query.keyWords}"></div>
		<div class="search_txt">时间：<input type="text" id="startAddTimeStr" name="startAddTimeStr" class="input_txt" value="${startAddTimeStr}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'});" readonly="readonly">-<input type="text" id="endAddTimeStr" name="endAddTimeStr" class="input_txt" value="${endAddTimeStr}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly"></div>
		<div class="search_txt">类型：<select id="type" name="type">
				<option value="-1" <c:if test="${-1 == query.type}">selected</c:if>>所有</option>
				<option value="0" <c:if test="${0 == query.type}">selected</c:if>>用户检索</option>
				<option value="1" <c:if test="${1 == query.type}">selected</c:if>>后台添加</option>
			</select>
		</div>
		<div class="search_txt">TOP：<select id="topNum" name="topNum">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="50">50</option>
			</select>
		</div>
		<div class="search_txt">粗粒度分词：<select id="isMaxLength" name="isMaxLength">
				<option value="on">启用</option>
				<option value="off" selected="selected">关闭</option>
			</select>
		</div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="button" class="button" value="统计" onclick="searchHistory_top();"><span>统计</span></button></div></div>
		<div class="search_btn"><div class="input_button"><button name="btnU" type="button" class="button" value="导出" onclick="alert($('#resultJson').val());">导出</button></div></div>
	</div>
  </form>
</div>

<input id="resultJson" name="resultJson" value="" type="hidden" />
<div class="up72_show">
  <table id="dataContener" cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
	<tr>
		<td>无统计结果！
		</td>
	</tr>
</table>
</div>
<script language="javascript" type="text/javascript">
searchHistory_top = function(){
	$.ajax({
	   type: "POST",
	   url: "${ctx}/admin/tools/searchHistory/top",
	   dataType: "text",
	   data: $("#admin_tools_searchHistory_top_search_form").serialize(),
	   success: function(msg){
		  var htmlStr = "";
		  if(msg == ''){
			   htmlStr = "<tr><td style='vertical-align:top;'><div class='divform'><span>无统计结果！</span></div></td></tr>";
		  }else{
			  var dataObj = eval('(' + msg + ')');
			  $('#resultJson').val(msg);
			  var index = 0;
			  for(var pro in dataObj){
				  htmlStr += "<tr><td style='vertical-align:top;'><div class='divform'><span>" + (++index) + "、" +  pro + " | " + dataObj[pro] + "</span></div></td></tr>";
			  }
			  //if(htmlStr == ''){
			  //htmlStr = "<tr><td style='vertical-align:top;'><div class='divform'><span>无统计结果！</span></div></td></tr>";
			  //}
		  }
		  $('#dataContener').html(htmlStr);
	   }
	});	
}
</script>
