<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
.overcss {
	color:#FF0000;
	background:#999999;
}
.clickcss {
	background:#006600;
	color:#000099;
}
</style>

<div class="up72_tabs">
  <div class="tabs_con">
    <div id="member_workGroupSel">
      <div class="up72_show">
        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
          <tr>
            <td><%=WorkGroup.ALIAS_NAME%></td>
            <td><%=WorkGroup.ALIAS_ORGANIZATION_ID%></td>
          </tr>
          <c:forEach items="${workGroupList}" var="item" varStatus="status">
            <tr class="selrow">
              <td>${item.name}&nbsp;<input style="" type="radio" name="workGroupBox" value="${item.id}" 
                <c:if test="${item.id == workGroupId}"> checked="checked"</c:if>
                />
                <input type="hidden" value="${item.name}" /></td>
              <td>${item.organization.name}&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
$("#member_workGroupSel .selrow").rowsel({overCss:"overcss",clickCss:"clickcss"});
</script>