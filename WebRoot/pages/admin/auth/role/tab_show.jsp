<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="up72_show">
  <table cellspacing="0" cellpadding="0" border="0" width="100%" class="show_table">
    <tr>
      <th><%=Role.ALIAS_NAME%>：</th>
      <td><c:out value='${role.name}'/></td>
    </tr>
    <!-- 角色是否为系统默认角色判断处理开始 -->
    <c:choose>
      <c:when test="${role.productCode == null}">
        <tr>
          <th><%=Role.ALIAS_ORGANIZATION_ID%>：</th>
          <td><c:out value='${role.organization.name}'/></td>
        </tr>
        <tr>
          <th><%=Role.ALIAS_WORK_GROUP_ID%>：</th>
          <td><c:out value='${role.workGroup.name}'/></td>
        </tr>
      </c:when>
      <c:when test="${role.productCode != null}">
        <input type="hidden" name="organizationId" id="organizationId" value="0" />
        <input type="hidden" name="workGroupId" id="workGroupId" value="0" />
      </c:when>
    </c:choose>
    <!-- 角色是否为系统默认角色判断处理结束 -->
    <tr>
      <th><%=Role.ALIAS_ENABLED%>：</th>
      <td><c:choose>
          <c:when test="${role.enabled == 1}">启用</c:when>
          <c:when test="${role.enabled == 0}">不启用</c:when>
        </c:choose></td>
    </tr>
    <tr>
      <th><%=Role.ALIAS_DESCRIPTION%>：</th>
      <td><c:out value='${role.description}'/></td>
    </tr>
    <tr>
      <th><%=WorkGroup.ALIAS_PRODUCT_LIST%>：</th>
      <td><c:choose>
          <c:when test="${null!=role.workGroup && null!=role.workGroup.productList}">
            <c:forEach items="${role.workGroup.productList}" var="pro">
              <label style="margin-right: 10px;">${pro.name}</label>
            </c:forEach>
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose></td>
    </tr>
  </table>
</div>
