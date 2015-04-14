<%@page import="com.up72.auth.model.MemberRole"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<script language="javascript" type="text/javascript">
function chooseRole(dom, roleId){
	if(isNull($("#workGroupId").val())){
		alert("请先选择所属用户组");
		return ;
	}
	var url = "${ctx}/admin/auth/member/role?workGroupId=" + $("#workGroupId").val() + "&roleId=" + roleId;
	showSelectPage(dom,{url:url,title:'选择用户组',selBox:'roleBox'});
}
</script>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="up72_edit">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
  <tr class="pb_frmtr">
    <td class="pb_frmtd"><%=MemberRole.ALIAS_ROLE_ID %></td>
  </tr>
  <c:forEach items="${roleList}" var="item" varStatus="status">
    <tr class="pb_frmtr">
      <td class="pb_frmtd"><input type="text" name="roleName" id="roleName" readonly="readonly" onclick="chooseRole(this, $('#roleId').val())"<c:if test="${member.id!=0}"> value="${role.name }"</c:if>>
        <input type="hidden" name="roleId" id="roleId" value="${item.name}" /></td>
    </tr>
  </c:forEach>
</table>
</div>