<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script>
	function renameFile(){
		var filename = $("#fileName").val().replace(/(^\s*)|(\s*$)/g, "");
		if(validateFileName(filename)){
			var filePath = $("#filePath").val();
			$.ajax({
		       type: "POST",
		       url: renameUrl,
		       cache: false,
		       data: "filePath=" + filePath + "&fileName=" + filename,
		       success: onRenameCallBack,
		       error: onRenameCallBack
		    });
		}
	}
	function validateFileName(filename){
		if(isNull(filename)){
			alert("文件名不能为空！");
			return false;
		}
		if(/[\u4e00-\u9fa5\uFF00-\uFFFF]+/.exec(filename)){
			alert("文件名不合法,不能包含中文及全角字符！");
			return false;
		}
		var reg = /[./\\#%&*|:<>\"?]+/;
		if(!reg.exec(filename)){
			return true;
		}
		alert("文件名不合法!");
		return false;
	}
	function onRenameCallBack(result){
		result = eval("(" + result + ")");
		if(result.state==1){
			alert("操作成功！",3,reloadList);
		}else{
			if(result.state==2)alert("文件名已存在！");
			else if(result.state==3)alert("文件已不存在！",3,reloadList);
			else if(result.state==4)alert("文件或文件名不合法！");
			else alert("未知错误！");
		}
	}
	
	function reloadList(){
		window.location.reload();
	}
</script>

<div class="up72_edit">
  <form id="admin_tools_file_edit_form">
    <c:choose>
      <c:when test="${fileAllName!=null}">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
          <tr class="pb_frmtr">
            <th class="pb_frmth"><label>原名称：</label></th>
            <td class="pb_frmtd">${fileAllName}</td>
          </tr>
          <tr class="pb_frmtr">
            <th class="pb_frmth"><label>新名称：</label></th>
            <td class="pb_frmtd"><input id="fileName" class="input_txt"  name="fileName" value="${fileName}" maxlength="125" />
              ${fileExt} </td>
          </tr>
          <tr class="pb_frmtr">
            <th class="pb_frmth"><label>文件路径：</label></th>
            <td class="pb_frmtd"><input type="text" class="input_txt" maxlength="20" value="${filePath}"  disabled="disabled"  name="filePath" id="filePath"></td>
          </tr>
        </table>
        <div class="up72_submit">
		  <div class="btn btn_sub" title="完成"><input type="button" id="submitButton" name="submitButton" value="完成" onclick="renameFile();" /></div>
		  <div class="btn btn_cel" title="取消"><input type="button" id="close_button" value="取消" onclick="closeBox();" /></div>
		</div>
      </c:when>
      <c:otherwise>
	  	<div class="error_msg">源文件已删除或被重命名,请关闭窗口后刷新页面！</div>
      </c:otherwise>
    </c:choose>
  </form>
</div>
