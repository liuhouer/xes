<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@include file="/pages/admin/include/getInnerStyleSkin.jsp" %>
  <%@include file="/pages/admin/include/default/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户中心</title>
	<c:if test="${status=='success'}">
	<script type="text/javascript">
		ctx = "${ctx}";
	</script>
	<script src="<c:url value="/scripts/jquery-1.7.2.js"/>" type="text/javascript"></script>
	<script id="mainScript" datas="ctx=${ctx}" src="<c:url value="/scripts/main.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/scripts/weebox/bgiframe.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/scripts/weebox/weebox.js"/>" type="text/javascript"></script>
	<link href="<c:url value="/scripts/weebox/weebox.css"/>" type="text/css" rel="stylesheet" />

	<!-- imageareaselector plugin start -->
	<script type="text/javascript" src="${ctx}/scripts/imgarea/jquery.imgareaselect.min.js"></script>
	<link type="text/css" href="${ctx}/scripts/imgarea/css/imgareaselect-default.css" rel="stylesheet" />
	<!-- imageareaselector plugin end -->
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	
	</c:if>
	<script type="text/javascript">
		function submitCutImage(){
			$.ajax({
				url : "${ctx}/admin/doCutImage",
				type : "post",
				data : $("#photo_cut_form").serialize(),
				dataType : "json",
				success : function(jsondatas){
					var call = "${call}";
					if(call!="" && call!="null"){
						evalFunction(call+"('"+jsondatas.imgPath+"')");
						window.parent.closeBox();
					}
				}
			});
		}
	</script>
</head>
<body>
<c:choose>
<c:when test="${status=='success'}"> 
<div style="border:#555 2px solid;width:704px;">
    <table width="702px;">
    	<tr>
        	<td>
	        <div class="myfaceup left" style="border:#555 1px solid;">
			  	<!-- 查看图片区域开始  -->
			 	<div id="cutarea" style="display:none;">
			 		<CENTER>
					<div>
					<img id="photoView" width="350px;" src="${ctx}${imgPath}" /> 
					</div>
					</CENTER>
				</div>
				<!-- 查看图片区域结束  -->
		  	</div>
			</td>
   			<td width="50%">
			  <div id="viewTable" style="border:#555 1px solid;">
					<div style="margin-left: 10px;">
						<p style="font-weight: bold; color: #333;">截取预览</p>
						<div style="width:120px; height:120px; overflow:hidden;">
							<img src="${ctx}${imgPath}" width="120" height="120" id="preview">
						</div>
						<p style="font-size:12px;color: #F00;">
							注：如果上传图片过大，可能会有一定的截取误差。
						</p>
					</div>
		            <form name="photo_cut_form" id="photo_cut_form" action="#" method="post">
						<input type="hidden" id="imgType" name="imgType" value="${imgType}" />
						<!-- 裁切的x坐标 -->
						<input type="hidden" id="cutX" name="cutX" />
						<input type="hidden" name="zoomWidth" value="${width}" />
						<input type="hidden" name="zoomHeight" value="${height}" />
						<!-- 裁切的y坐标 -->
						<input type="hidden" id="cutY" name="cutY" />
						<!-- 裁切的宽 -->
						<input type="hidden" id="cutWidth" name="cutWidth" value="" />
						<!-- 裁切的高 -->
						<input type="hidden" id="cutHeight" name="cutHeight" value="" />
						<!-- 查看图片的宽 -->
						<input type="hidden" id="viewWidth" name="viewWidth" />
						<!-- 查看图片的高 -->
						<input type="hidden" id="viewHeight" name="viewHeight" />
						<!-- 图片的服务器相对路径 -->
						<input type="hidden" id="photo_file" name="imgPath" value="${imgPath}" />
						<p>
						  	<div class="up72_edit_sub">
								<div class="btn btn_sub" title="确 定">
									<input id="submitButton" onclick="submitCutImage();" type="button" id="" value="确 定" />
								</div>
							</div>
						</p>
					</form>
				</div>
      		</td>
      	</tr>
     </table>
</div>
	<script type="text/javascript">
	function showPreview(width,height,coords){
		var imgW = width;
		var imgH = height;
		//初始化表单
		$("#viewWidth").val(width);
		$("#viewHeight").val(height);
		$("#cutWidth").val(coords.width+"");
		$("#cutHeight").val(coords.height+"");
		$("#cutX").val(coords.x1+"");
		$("#cutY").val(coords.y1+"");
		
		if (parseInt(coords.width) > 0){
			var rx = 100 / coords.width;
			var ry = 100 / coords.height;
			var x = coords.x1;
			var y = coords.y1;
			jQuery('#preview').css({
				width : Math.round(rx * imgW) + 'px',
				height : Math.round(ry * imgH) + 'px',
				marginLeft: '-' + Math.round(rx * x) + 'px',
				marginTop: '-' + Math.round(ry * y) + 'px'
			});
		}
	}
	
	function addImgAction(_imgw,_imgh) {
		var _x2 = _imgw;
		var _y2 = _imgh;
		
		if('${sw}'!='' && '${sh}'!=''){
			_x2 = parseInt('${sw}');
			_y2 = parseInt('${sh}');
		} else {
			if(_imgw/_imgh > ${width}/${height}){
				_x2 = ${width}/${height}*_imgh;
			} else if (_imgh > _imgw){
				_y2 = ${height}/${width}*_imgw;
			}
		}
		var pw = _x2;
		var ph = _y2;
		if(_x2 > _y2){
			pw = 120;
			ph = parseInt(ph / pw * 120);
		}else {
			ph = 120;
			pw = parseInt(pw / ph * 120);
		}
		jQuery('#preview').parent("div").css({	
			width : pw+"px",
			height : ph+"px"
		});
		
		$('#photoView').imgAreaSelect({ 
				x1: 0, 
				y1: 0, 
				x2: _x2, 
				y2: _y2,
				aspectRatio : "${width}:${height}", 
				resizable : true,
				onSelectChange: function(img,c){
					showPreview(_imgw,_imgh,c);
				},
				onInit : function(img,c){
					showPreview(_imgw,_imgh,c);
				}
		});
	}

	function resetImg(img,width,height,size){
		var imgW = 0;
		var imgH = 0;
		if(width / height > 1){
			imgW = size;
			imgH = height / width * size;
		}else if(height / width > 1){
			imgH = size;
			imgW = width / height * size;
		}else if(width / height == 1
			&& width != size){
			imgW = size;
			imgH = size;
		}
		imgW = Math.round(imgW);
		imgH = Math.round(imgH);
		$(img).width(imgW);
		$(img).height(imgH);
		return {"width":imgW,"height":imgH};
	}
	
  	$(document).ready(function() {
		$("#cutarea").show();
		var range = resetImg('#photoView',${imgWidth},${imgHeight},350);
		$("#viewTable").css("height",range.height+3);
		addImgAction(range.width,range.height);
	});
</script>
</c:when>
<c:otherwise>
	图片已删除。
</c:otherwise>
</c:choose>
</body>
</html>