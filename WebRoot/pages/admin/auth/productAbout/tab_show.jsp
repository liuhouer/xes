<%@page import="com.up72.auth.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	/**
	 * 删除产品
	 */
	function deleteProductAbout(id){
		confirm("确认删除该产品吗？", function(){
	  		$.ajax({
	  			url : "${ctx}/admin/auth/productAbout/deleteAbout",
	  			type : "post",
	  			data : "items=${productAbout.id}",
	  			dataType : "json",
	  			success : function(jsonDatas){
	  				alert("删除成功");
	  				showPro("${ctx}/admin/auth/productAbout/${productAbout.product.code}/tabIndex");
	  			}
  			});
  		});
  	}
</script>
<div class="up72_edit">
   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
   		<tr>
         	<th>产品：</th>
            <td><c:out value='${productAbout.product.name}'/></td>
     	 </tr>
   		<tr>
         	<th><%=ProductAbout.ALIAS_TITLE%>：</th>
            <td><c:out value='${productAbout.title}'/></td>
     	 </tr>
   		<tr>
         	<th><%=ProductAbout.ALIAS_CONTENT%>：</th>
            <td>
            <div>
            ${productAbout.content}
            </div>
            </td>
     	 </tr>
    </table>
    <div class="up72_submit">
  	  <div class="btn btn_sub" title="编辑"><input type="button" onclick="show('iframe#${ctx}/admin/auth/productAbout/${productAbout.id }/edit?productCode=${productAbout.productCode }','编辑',700,600)"  value="编辑" /></div>
      <div class="btn btn_sub" title="删除"><input type="button" onclick="deleteProductAbout();"  value="删除" /></div>
      <div class="btn btn_sub" title="返回"><input type="button" onclick="showPro('${ctx}/admin/auth/productAbout/${productAbout.productCode }/tabIndex')"  value="返回" /></div>
    </div>
 </div>