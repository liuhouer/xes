<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script>
	function renameFile(){
		var ctxUrl = "${ctx}";
		var createUrl = ctxUrl+"/admin/tools/fileManager/doCreate";
		var filename = $("#fileName").val().replace(/(^\s*)|(\s*$)/g, "");
		if(validateFileName(filename)){
			var filePath = $("#filePath").val();
			$.ajax({
		       type: "POST",
		       url: createUrl,
		       cache: false,
		       data: "filePath=" + filePath + "&fileName=" + filename,
		       success: onRenameCallBack,
		       error: onRenameCallBack
		    });
		}
	}
	function validateFileName(filename){
		if(isNull(filename)){
			alert("目录名不能为空！");
			return false;
		}
		if(/[\u4e00-\u9fa5\uFF00-\uFFFF]+/.exec(filename)){
			alert("目录名不合法,不能包含中文及全角字符！");
			return false;
		}
		var reg = /[./\\#%&*|:<>\"?]+/;
		if(!reg.exec(filename)){
			return true;
		}
		alert("目录名不合法,不能包含特殊字符！");
		return false;
	}
	function onRenameCallBack(result){
		result = eval("(" + result + ")");
		if(result.state==1){
			alert("创建成功！",3,reloadList);
		}else{
			if(result.state==2)alert("目录名已存在！");
			else if(result.state==3)alert("目录已不存在！",3,reloadList);
			else if(result.state==4)alert("目录或目录名不合法！");
			else alert("未知错误！");
		}
	}
	
	function reloadList(){
		window.location.reload();
	}
</script>

<div class="up72_edit">
  <form id="admin_tools_file_edit_form">
    <input type="hidden" maxlength="20" value="${filePath}" name="filePath" id="filePath">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>当前目录：</label></th>
        <td class="pb_frmtd"><c:choose>
            <c:when test="${filePath==''}">/</c:when>
            <c:otherwise>${filePath}</c:otherwise>
          </c:choose></td>
      </tr>
      <tr class="pb_frmtr">
        <th class="pb_frmth"><label>新目录名：</label></th>
        <td class="pb_frmtd"><input id="fileName" name="fileName" value="" maxlength="125" class="input_txt"  /></td>
      </tr>
    </table>
    <div class="up72_submit">
      <div class="btn btn_sub" title="完成"><input type="button" id="submitButton" name="submitButton" value="完成" onclick="renameFile();" /></div>
      <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
    </div>
  </form>
</div>
