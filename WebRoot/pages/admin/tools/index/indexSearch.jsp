<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_edit">
  <form:form id="admin_tools_index_addDicStopWord_form" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>查询语句:</label></th>
        <td class="pb_frmtd"><textarea name="ikQueryExp" rows="2" id="ikQueryExp" class="input_txt required"></textarea>
          <span class="edit_comment">例：class='com.up72.cms.model.Resource'、Title:'策略'、Content:'策略'、CategoryId='200'</span></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>查询结果:</label></th>
        <td class="pb_frmtd"><div name="result" id="result" class="input_txt required" style="width:420px; display:none;"></div>
          <span class="edit_comment" id="optMessage" style="display:none;"></span></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="submit" id="submitButton" name="submitButton" value="完成" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form:form>
</div>
<script type="text/javascript">
index_doSearch = function(){
	var ikQueryExp = $.trim($("#ikQueryExp").val());	
	if("" != ikQueryExp){
		$.ajax({
		   type: "POST",
		   dataType: "text",
		   url: "${ctx}/admin/tools/index/doSearchTest",
		   data: "ikQueryExp=" + ikQueryExp,
		   success: function(msg){
			  msg = $.trim(msg);
			  if("1" == msg){
				  $("#optMessage").html("查询表达式不正确！");
				  $("#optMessage").show();
				  setTimeout(" $('#optMessage').hide();",1000);
			  }else{
				  if("" == msg){
					  $("#result").html("查无数据！");
					  $("#result").show();
				  }else{
					  $("#result").html(msg);
					  $("#result").show();
				  }
			  }
		   }
		});	
	}else{
		  $("#optMessage").html("请输入查询语句！");
		  $("#optMessage").show();
		  setTimeout(" $('#optMessage').hide();",1000);
	}
}

</script> 
