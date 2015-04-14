<%@page import="com.up72.auth.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>
<up72:override name="head">
	<title><%=Product.TABLE_ALIAS%> 维护</title>
	<script src="${ctx}/scripts/rest.js"></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css"
		rel="stylesheet">
	<script type="text/javascript"
		src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css"
		rel="stylesheet">
	<script type="text/javascript"
		src="${ctx}/scripts/jq_tree/jquery.treeview.js"></script>
	<link type="text/css" href="${ctx}/scripts/jq_tree/jquery.treeview.css"
		rel="stylesheet" />
	<style type="text/css">
		.name {font-weight: bold;}
		.org {color: #006;}
		.workGroup {color: #060;}
		.role {font-style: italic;color: #666;}
	</style>

	<script type="text/javascript">
	$(document).ready(function() {
		var proId = "${proId}";
		var perGroupId = "${perGroupId}"
		if(proId!=""){
			$("#"+proId).children().first().removeClass("expandable-hitarea");
			$("#"+proId).children().first().addClass("collapsable-hitarea");
			$("#"+proId).removeClass("expandable");
			$("#"+proId).addClass("collapsable");
			$("#ul"+proId).attr("style","display:block;")
		}
		if(perGroupId!=""){
			$("#"+proId).children().first().removeClass("expandable-hitarea");
			$("#"+proId).children().first().addClass("collapsable-hitarea");
			$("#"+proId).removeClass("expandable");
			$("#"+proId).addClass("collapsable");
			$("#ul"+proId).attr("style","display:block;")
			$("#"+perGroupId).children().first().removeClass("expandable-hitarea");
			$("#"+perGroupId).children().first().addClass("collapsable-hitarea");
			$("#"+perGroupId).removeClass("expandable");
			$("#"+perGroupId).addClass("collapsable");
			$("#ul"+perGroupId).attr("style","display:block;")
		}
	});
	function uploadIconPath(){
		showCommonUpload({
	   		width : 450,
	   		height : 250,
	   		queueLimit : 1,
	   		sizeLimit : 1024*1024*1,
	   		callBack : "window.parent.uploadFileCall(event, ID, fileObj, response, data)",
	   		fileExt : "*.jpg;*.gif;*.png"
   		});
	}
	
	function uploadIconPathCall(event, ID, fileObj, response, data){
		var file = response.savePath;
		$("#imgPath").val(file);
		if(data.fileCount <= 0){
			window.parent.closeBox();
		}
	}
	
	function uploadFileCall(event, ID, fileObj, response, data){
	closeBox();
	confirm("是否对图片进行裁剪处理？",function(){
		var url = ctx+"/admin/cutImage?imgPath="+response.savePath+"&imgType=png&sw=100&sh=100&width=100&height=100&call=window.parent.upload_cut_call";
		show("iframe#"+url,"裁剪处理",730,400);
		
	},function(){
		upload_cut_call(response.savePath);
	});
}
	
	function upload_cut_call(imgPath){
		$("#imgPath").val(imgPath);
		window.parent.closeBox();
	}
	
	function loadViewImg(){
		var imgPath = $("#imgPath").val();
		if(!isNull(imgPath)){
			$("#viewImg").attr("href",imgPath);
			$("#viewImg").show();
		}
	}
	
	function exportPermission(){
		window.open("${ctx}/admin/auth/product/exportPermission");
	}
	
	/**
	 * 删除产品
	 */
	function deletePro(id){
		confirm("确认删除该产品吗？", function(){
	  		$.ajax({
	  			url : "${ctx}/admin/auth/product/"+id+"/delete",
	  			type : "post",
	  			dataType : "json",
	  			success : function(jsonDatas){
	  				alert("删除成功");
	  				window.location.reload();
	  			}
  			});
  		});
  	}
  	/**
	 * 删除角色
	 */
  	function deleteRole(id,productId,AUTH_PERM_ID){
		confirm("确认删除该角色吗？", function(){
	  		$.ajax({
	  			url : "${ctx}/admin/auth/role/"+id+"/delete",
	  			type : "post",
	  			dataType : "json",
	  			success : function(jsonDatas){
	  				alert("删除成功");
	  				window.location.reload();
	  				
	  			}
	  		});
  		});
  	}
  	/**
	 * 删除权限组
	 */
  	function deletePermissionGroup(id){
  		confirm("确认删除该权限组吗？", function(){
	  		$.ajax({
	  			url : "${ctx}/admin/auth/permissionGroup/"+id+"/delete",
	  			type : "post",
	  			dataType : "json",
	  			success : function(jsonDatas){
	  				alert("删除成功");
	  				window.location.reload();
	  			}
	  		});
  		});
  	}
  	/**
	 * 删除权限
	 */
  	function deletePermission(id){
  		confirm("确认删除该权限吗？", function(){
	  		$.ajax({
	  			url : "${ctx}/admin/auth/permission/"+id+"/delete",
	  			type : "post",
	  			dataType : "json",
	  			success : function(jsonDatas){
	  				alert("删除成功");
	  				window.location.reload();
	  			}
	  		});
  		});
  	}
  	
	function showProductTab(_this,id,AUTH_PERM_ID,productTabId){
		$(".selected").each(function (){
			$(this).removeClass("selected")
		})
		//$(_this).addClass("selected")
		$("#proa"+id).addClass("selected");
		$.ajax({
			url : "${ctx}/admin/auth/product/"+id+"/tabProduct?AUTH_PERM_ID="+AUTH_PERM_ID+"&productTabId="+productTabId,
			dataType : "html",
			success : function(html){
				$("#tab").html(html);
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
	}
	function showPermissionTab(_this,id,AUTH_PERM_ID){
		$(".selected").each(function (){
			$(this).removeClass("selected")
		})
		$(_this).addClass("selected")
		$.ajax({
			url : "${ctx}/admin/auth/product/"+id+"/tabPermission?AUTH_PERM_ID="+AUTH_PERM_ID+"",
			dataType : "html",
			success : function(html){
				$("#tab").html(html);
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
	}
	function showPermissionGroupTab(_this,id,AUTH_PERM_ID,code){
		$(".selected").each(function (){
			$(this).removeClass("selected")
		})
		$(_this).addClass("selected")
		$.ajax({
			url : "${ctx}/admin/auth/product/"+id+"/tabPermissionGroup?code="+code+"&AUTH_PERM_ID="+AUTH_PERM_ID+"",
			dataType : "html",
			success : function(html){
				$("#tab").html(html);
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
	}
	function updateSortId(_this,id,type,genres){
		var url = "";
		if(genres == "pro"){
			url = "/admin/auth/product/"+id+"/updateSortId"
		}else if(genres == "perGroup" ){
			url = "/admin/auth/permissionGroup/"+id+"/updateSortId"
		}else if(genres == "per"){
			url = "/admin/auth/permission/"+id+"/updateSortId"
		}
		$.ajax({
			url : url,
			dataType : "json",
			data : "type="+type,
			success : function(data){
				if(data.message =  "success"){
					if(genres == "pro"){
						window.location.href="${ctx}/admin/auth/product/indexTree?AUTH_PERM_ID=${AUTH_PERM_ID}";
					}else if(genres == "perGroup"){
						var proId = $(_this).parents("li").last().attr("id");
						window.location.href="${ctx}/admin/auth/product/indexTree?proId="+proId+"&AUTH_PERM_ID=${AUTH_PERM_ID}";
					}else if(genres == "per"){
						var parents = $(_this).parents("li");
						var proId = $(parents[2]).attr("id");
						var perGroupId = $(parents[1]).attr("id");
						window.location.href="${ctx}/admin/auth/product/indexTree?proId="+proId+"&perGroupId="+perGroupId+"&AUTH_PERM_ID=${AUTH_PERM_ID}";
					}	
				}else{
					alert("排序失败，请重试。");
				}
			},
			error : function(){
				alert("网络错误，请稍后重试。");
			}
		});
	}
</script>
</up72:override>

<up72:override name="content">
	<!-- 当前位置 -->
	<div class="head_content">
		<div class="navBar" style="display: none">
			»
			<a class="" href="${ctx}/admin/auth/product"><%=Product.TABLE_ALIAS%>设置</a>
		</div>
	</div>
	<!-- END  当前位置 -->
	<!--end查询-->
	<table  width="100%" border="0" cellspacing="0" cellpadding="0"
		class="up72_treeperm">
		<tr>
			<td class="up72_filetree" valign="top" style="width: 260px;">
				<div class="filetree_scr">
					<div class="filetree">
						<form id="admin_auth_product_page_form"
							name="admin_auth_product_page_form">
							<div>
								<div id="treecontrol">
									<a title="" href="#">关闭全部</a>
									<a title="" href="#">展开全部</a>
								</div>

								<ul id="pro_tree" class="filetree">
									<c:forEach items="${productList}" var="pro" varStatus="proStatus">
										<li class="expandable" id="proId${pro.id }">
											<span class="<c:choose><c:when test="${null != pro.permissionGroupList && fn:length(pro.permissionGroupList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
												&nbsp;<a href="javascript:;" id="proa${pro.id }" onclick="showProductTab(this,${pro.id },${AUTH_PERM_ID});" class="pro name">${pro.name}</a>&nbsp;<c:if test="${proStatus.count != 1}"><a  href="javascript:;" onclick="updateSortId(this,${pro.id},1,'pro')" >上移</a>&nbsp;</c:if><c:if test="${proStatus.count != fn:length(productList)}"><a  href="javascript:;" onclick="updateSortId(this,${pro.id},0,'pro')" >下移</a></c:if>
											</span>
											<c:if
												test="${null != pro.permissionGroupList && fn:length(pro.permissionGroupList)>0}">
												<ul style="display: none;" id="ulproId${pro.id }" >
													<c:forEach items="${pro.permissionGroupList}"
														var="permissionGroup" varStatus="perGroup">
														<li class="expandable" id="perGroupId${permissionGroup.id }">
															<span
																class="<c:choose><c:when test="${null != permissionGroup.permissionList && fn:length(permissionGroup.permissionList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
																&nbsp;&nbsp;&nbsp;<a
																href="javascript:;" id="perGroupa${permissionGroup.id }" onclick="showPermissionGroupTab(this,${permissionGroup.id },${AUTH_PERM_ID},'${pro.code }');" class="permissionGroup name">${permissionGroup.name}</a>&nbsp;<c:if test="${perGroup.count != 1}"><a  href="javascript:;" onclick="updateSortId(this,${permissionGroup.id},1,'perGroup')" >上移</a>&nbsp;</c:if><c:if test="${perGroup.count != fn:length(pro.permissionGroupList)}"><a  href="javascript:;" onclick="updateSortId(this,${permissionGroup.id},0,'perGroup')" >下移</a></c:if>
															</span>
															<c:if
																test="${null != permissionGroup.permissionList && fn:length(permissionGroup.permissionList)>0}">
																<ul style="display: none;" id="ulperGroupId${permissionGroup.id }">
																	<c:forEach items="${permissionGroup.permissionList}"
																		var="permission" varStatus="per">
																		<li>
																			<span class="file"> &nbsp;&nbsp;&nbsp;<a
																				href="javascript:;" onclick="showPermissionTab(this,${permission.id },${AUTH_PERM_ID});" class="permission name">${permission.name}</a>&nbsp;<c:if test="${per.count != 1}"><a  href="javascript:;" onclick="updateSortId(this,${permission.id},1,'per')" >上移</a>&nbsp;</c:if><c:if test="${per.count != fn:length(permissionGroup.permissionList)}"><a  href="javascript:;" onclick="updateSortId(this,${permission.id},0,'per')" >下移</a></c:if>
																			</span>
																		</li>
																	</c:forEach>
																</ul>
															</c:if>
														</li>
													</c:forEach>
												</ul>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</div>
						</form>
					</div>
				</div>
			</td>
			<td id="tab"></td>
		</tr>
	</table>
	<script type="text/javascript">
	  	/**
		 * 初始化权限树
		 */
		$("#pro_tree").treeview({
			control: "#treecontrol"
		});
		$.ajax({
			url : "${ctx}/admin/auth/product/dashboard?AUTH_PERM_ID=${AUTH_PERM_ID}",
			success : function(html){
				$("#tab").html(html);
			}
		});
	</script>
</up72:override>
<%@ include file="base.jsp"%>