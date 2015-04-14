<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<#include "/resource.ftl" />
<script type="text/javascript">
do_uploadFile_delete_all = function(){
	if(window.confirm("确认删除选择的数据吗？")){
		var opt = {
			method: "post", 
			postBody: Form.serialize($("uploadFile_page_form")), 
			onSuccess: function(response){
				if (!processResponse(response)) {
					return;
				}
				window.location.reload();
			}, 
			onFailure: processFailure
		};
		new Ajax.Request(toUTF8("/common/uploadFile/doDelete.jhtml"), opt);
	}
}
</script>
</head>

<body>
<div id="Col2">

		<div class="Block-List">
			<div class="BlockBar">
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="BlockBorder">
                <tr>
                  <td class="BlockBorder_l"></td>
                  <td class="BlockBorder_c"><span class="ico_t"></span>上传文件列表</td>
                  <td class="BlockBorder_r"></td>
                </tr>
              </table>
		  </div>
			<div class="BlockEdit">
			<form id="uploadFile_page_form" name="uploadFile_page_form" >
				<table cellspacing="1" cellpadding="4" class="frm">
								<tr class="sub align-center">
									<td width="40"><a href="javascript:goSelectAll('ids')">全选</a></td>
									<td width="30">ID</td>
									<td>上传文件名</td>
									<td>保存文件名</td>
									<td>文件大小</td>
									<td>上传用户</td>
									<td>上传日期</td>
								</tr>
								<#list (uploadFileList)! as upfile>
									<tr class="row align-center" onMouseOver="this.className='row pop align-center';" onMouseOut="this.className='row align-center';">
										<td><input type="checkbox" class="normal" name="ids" value="${upfile.id?c}"></td>
										<td class="row-id"><a href="javascript:" class="id" title="${upfile.id?c}">${upfile_index+1}</a></td>
										<td><a target="_blank" href="${(upfile.path)!''}${(upfile.fileName)!''}">${(upfile.uploadName)!''}</a></td>
										<td><a target="_blank" href="${(upfile.path)!''}${(upfile.fileName)!''}">${(upfile.fileName)!''}</a></td>
										<td>${(upfile.fileSize)!''}</td>
										<td>${(upfile.userName)!''}</td>
										<td>${(upfile.dateUploadTime?string('yyyy-MM-dd'))!''}</td>
									</tr>
								</#list>
								
								<tr class="row">
							<td colspan="7" class="colbut">							
							<a href="javascript:goSelectAll('ids');">[全选]</a>
							<a href="javascript:do_uploadFile_delete_all();">[册除]</a>
							</td>
							</tr>
					<tr class="colsp">
            			<td colspan="7" class="paging"><@fn.generalPager1 pagination=pagination href="/common/uploadFile/goPage.jhtml?r=1"/>
           				</td>
          			</tr>
			  </table>
			  </form>
			</div>
			
						
			<div class="BlockBot">
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="BlockBoter">

                <tr>
                  <td class="BlockBot_l"></td>
                  <td class="BlockBot_c"></td>
                  <td class="BlockBot_r"></td>
                </tr>
              </table>			
			</div>
			
		</div>
</div>
</body>
</html>