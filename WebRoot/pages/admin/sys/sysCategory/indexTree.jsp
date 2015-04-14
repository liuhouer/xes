<%@page import="com.up72.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>

<up72:override name="head">
<title></title>
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
	<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	 
	<link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
	
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.js"></script>
  <script type="text/javascript" src="${ctx}/scripts/jq_tree/jquery.treeview.async.js"></script>
  <link type="text/css" href="${ctx}/scripts/jq_tree/jquery.treeview.css" rel="stylesheet" />
  <script type="text/javascript">
  	function target2Resource(catId){
		window.resource_frame.target="";
	}
  </script>
</up72:override>
<up72:override name="content">
<div class="up72_search">
  <form id="admin_cms_category_search_form" name="admin_cms_category_search_form" method="get" action="${ctx}/admin/sys/sysCategory/search">
    <div class="search_con">
   	   <div class="search_txt"><%=SysCategory.ALIAS_PARENT_GUID%>：
	   	<select id="parentGuid" name="parentGuid">
	  	  <option value="">请选择上级分类</option>
	  	  <c:forEach items="${categoryList}" var="item">
	  	  	<option value="${item.guid}">
	  	  	<c:forEach begin="2" end="${item.level}">&nbsp;&nbsp;</c:forEach>
            <c:if test="${item.level > 1}">|-</c:if>
	  	  	${item.catName}</option>
	  	  </c:forEach>
	  	</select>
   		</div>
      <div class="search_txt"><%=SysCategory.ALIAS_CAT_NAME%>：
        <input type="text" id="catName" name="catName" class="input_txt" value="${catName}">
      </div>
      <div class="search_btn">
        <div class="input_button">
          <button name="btnU" type="submit" id="btnU" class="button" value="查询"><span>查询</span></button>
        </div>
      </div>
    </div>
  </form>
</div>
 <div class="flexigrid">
    <div class="tDiv">
      <div class="tDiv2">
        <div class="fbutton">
          <div><span class="addorder" style="padding-left:20px;" onclick="addCategory()">添加</span></div>
        </div>
        <div class="fbutton">
          <div><span class="delete" style="padding-left:20px;" onclick="deleteCheckedCategory();">删除</span></div>
        </div>
      </div>
    </div>
  </div>
   <form id="admin_cms_category_tree_form" name="admin_cms_category_tree_form">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="up72_treeperm">
	  <tr>
	    <td class="up72_filetree"><div class="filetree_scr"><div class="filetree" id="filetree"><ul id="category_tree"></ul></div></div></td>
	    <td><iframe style="border:none; height:690px; width:100%;" scrolling="no" frameborder="0" id="resource_frame" name="resource_frame" src="${ctx}/admin/sys/sysCategory/indexForm"></iframe></td>
	  </tr>
	</table>
  </form>
<script type="text/javascript">
	$("#category_tree").treeview({
		url: "${ctx}/admin/sys/sysCategory/catTree"
	});

function addCategory(){
	
	show("${ctx}/admin/sys/sysCategory/new","<%=SysCategory.TABLE_ALIAS%>添加",600)
	
}
function deleteCheckedCategory(){
	if(isSelect('category_ids')){
		confirm('确定删除吗?',function(){
		datas = $("#admin_cms_category_tree_form").serialize();
  		$.ajax({
  			url : "${ctx}/admin/sys/sysCategory/doDelete",
  			type : "post",
  			data : datas,
  			success : function(response){
  			window.location.reload();
  				closeBox();
  				alert("删除成功",3,function(){
  					window.location.reload();
  				});
  			} 
  		});
		},closeBox());
		}else{
			alert("请选择数据");
		}
	
}
</script>
</up72:override>
<%@ include file="base.jsp" %>