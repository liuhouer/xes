<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	function deleteOrg(id){
		if(!isNull(id)){
			confirm("确认删除该机构吗？",function (){
				$.ajax({
					type : "post", 
					url : "${ctx}/admin/auth/organization/"+id+"/delete",
					dataType : "json",
					success : function(jsondatas){
						if(jsondatas.status == "success"){
							alert("删除成功",3);
							window.location.reload();
						}else{
							alert("删除失败，请稍后重试！",3);
						}
					},
					error: function(){
						alert("请求失败。请检查网络是否连接。",3);
					}
				});
			});
		}
	}
</script>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Organization.ALIAS_NAME%>：</th>
      <td><c:out value='${organization.name}'/></td>
    </tr>
    <!--
    <tr>
      <th><%=Organization.ALIAS_PARENT%>：</th>
      <td><c:out value='${organization.parent}'/></td>
    </tr>
    -->
    <tr>
      <th><%=Organization.ALIAS_DOMAIN%>：</th>
      <td><c:out value='${organization.domain}'/></td>
    </tr>
    <tr>
      <th><%=Organization.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${organization.enabled == 1}">启用</c:when>
          <c:when test="${organization.enabled == 0}">禁用</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th>用户职能组：</th>
      <td><c:forEach items="${organization.productList}" var="opItm" varStatus="status">
				${opItm.name }
				<c:if test="${!status.last}">/</c:if>
			</c:forEach>
			</td>
    </tr>
    <tr>
      <th><%=Organization.ALIAS_REMARK%>：</th>
      <td><c:out value='${organization.remark}'/></td>
    </tr>
    <!--
    <tr>
      <th><%=Organization.ALIAS_OLEVEL%>：</th>
      <td><c:out value='${organization.olevel}'/></td>
    </tr>
    -->
  </table>
  <div class="up72_submit">
  	  <div class="btn btn_sub" title="编辑"><input type="button" onclick="showPro('${ctx }/admin/auth/organization/${organization.id }/tabEdit');"  value="编辑" /></div>
      <div class="btn btn_sub" title="删除"><input type="button" onclick="deleteOrganization(${organization.id });"  value="删除" /></div>
    </div>
</div>
