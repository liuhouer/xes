<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp"%>
<up72:override name="head">
	<title><%=Dictionary.TABLE_ALIAS%> 维护</title>
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
		<link rel="stylesheet" href="${ctx}/scripts/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  	<script type="text/javascript" src="${ctx}/scripts/ztree/js/jquery.ztree.core-3.2.js"></script>
	<style type="text/css">
	
.name {
	font-weight: bold;
}

.org {
	color: #006;
}

.workGroup {
	color: #060;
}

.role {
	font-style: italic;
	color: #666;
}
</style>

	<script type="text/javascript">
	
	var setting = {
			view: {
				selectedMulti: false
			},
			async: {
				enable: true,
				url:"${ctx}/admin/sys/dictionary/nodes",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"method":"nodes"},
				dataFilter: filter
			},
			callback: {
				onClick: zTreeDblClick,
				onRightClick: OnRightClick
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		
		function OnRightClick(event, treeId, treeNode){
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}
		function zTreeDblClick(e, treeId, treeNode){
			$.ajax({
			  type: "POST",
			  url: "${ctx}/admin/sys/dictionary/tab",
			  data: "id=" + treeNode.id,
			  dataType: "html",
			  cache: false,
			  success: function(html){
			    	$("#editDiv").html(html);
			  }
			});
		}
		
		function showRMenu(type, x, y) {
			if("root" == type){
				$("#m_addRoot").show();
				$("#m_add").hide();
				$("#m_edit").hide();
				$("#m_del").hide();
			}else{
				$("#m_addRoot").hide();
				$("#m_add").show();
				$("#m_edit").show();
				$("#m_del").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#dicTree"), setting);
			zTree = $.fn.zTree.getZTreeObj("dicTree");
			rMenu = $("#rMenu");
		});
</script>
	<style type="text/css">
.name {
	font-weight: bold;
}

.pro {
	color: #006;
}

.permissionGroup {
	color: #060;
}

.permission {
	color: #666;
}
</style>
</up72:override>

<up72:override name="content">
	<!-- 当前位置 -->
	<div class="head_content">
		<div class="navBar" style="display: none">
			»11
			<a class="" href="${ctx}/admin/auth/product"><%=Dictionary.TABLE_ALIAS%>设置</a>
		</div>
	</div>
	<!-- END  当前位置 -->
	<!--end查询-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
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
								<ul id="dicTree" class="ztree">
        						</ul>
							</div>
						</form>
					</div>
				</div>
			</td>
			<td id="editDiv">
				<div class="flexigrid">
					 <div class="tDiv">
						<div class="tDiv2">
							<div class="fbutton">
								<div>
									<span class="addorder" style="padding-left: 20px;"
										onclick="show('${ctx}/admin/sys/dictionary/new','<%=Dictionary.TABLE_ALIAS%>添加',600);">添加字典</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function deletePro(id){
			confirm("确认删除该字典吗？", function(){
	  		$.ajax({
	  			url : "${ctx}/admin/sys/dictionary/"+id+"/deleteDictionary",
	  			dataType : "json",
	  			success : function(jsonDatas){
	  				alert("删除成功");
	  				window.location.reload();
	  			}
	  		});
	  		}
  		);
  	}
	</script>
</up72:override>
<%@ include file="base.jsp"%>