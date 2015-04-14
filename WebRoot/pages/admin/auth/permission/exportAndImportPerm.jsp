<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
<title><%=Product.TABLE_ALIAS%> 维护</title>
<script src="${ctx}/scripts/rest.js" ></script>		
<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script>
<script type="text/javascript">
	function toExport(){
		window.location.href = "/admin/auth/permission/exportPerm";
	}
	function importPerm(){
			showCommonUpload({
		   		width:450,
		   		height:150,
		   		sizeLimit: 1024*1024*50,
		   		callBack:"window.parent.importPermCall(event, ID, fileObj, response, data)",
		   		fileExt:"*.zip;*.xml"
		  	});
	}
	
	function importPermCall(event, ID, fileObj, response, data){
		var file = response.savePath;
		window.location.href="${ctx}/admin/auth/permission/importPerm?filePath="+file;
  		/*$.ajax({
			url : "${ctx}/admin/cbox/plan/importPlanXML",
			type: "post",
			dataType:"json",
			data:"filePath="+file,
			success : function (data){
				if(data.status=="noLogin"){
					window.location.href="${ctx}/admin/login";
				}else{
					if(data.message=="success"){
						alert("导入成功！", 0, "window.location.reload();")
					}else if(data.message=="error"){
						alert("文件错误，请确认数据正确后重新导入。",0,"window.location.reload();");
					}else{
						var msg = decodeURI(data.message);
						var m = msg.split('Wrap');
				   	 	var str ='';
				   	 	$(m).each(function(i,e){
				   	 		str +=	e +"<br>";
				   	 	});
				   	 	alert(str,0,function(){
			   	 	 		window.location.href=reload();
			   	 	 	});
					}
				}
			}
		});
		closeBox();*/
  	}
</script>
</up72:override>
<up72:override name="content">
<div class="dashboard_info">
	     <div class="promptsnews_tit">
	        	导入导出
	      </div>
	        <div class="promptsnews_con">
				<div class="up72_quickbtns">
 					<div class="btn btn_sub" title="导出"><input type="button"  value="导出" onclick="toExport();"/></div>
					<div class="btn btn_sub" title="导入"><input type="button"  value="导入" onclick="importPerm();" /></div>
				</div>
			</div>
			
		</div>
</up72:override>
<%@ include file="base.jsp" %>