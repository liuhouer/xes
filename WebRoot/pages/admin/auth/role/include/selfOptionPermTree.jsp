<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

		<!-- 操作权限开始 -->
		<div style="border:#666666 solid 1px;">
		<div>操作权限</div>
		<div id="optionCtrl">
		<a title="" href="#">关闭全部</a>
		<a title="" href="#">展开全部</a>
		</div>
		<ul id="optionPermtree" class="filetree">
		<!-- 输出产品列表 start -->
		<c:forEach items="${productList}" var="product" varStatus="proSta">
			<!-- 输出权限组列表 start -->
			<c:if test="${fn:length(permissionGroupList)>0}">
			<li class="expandable">
			<span class="<c:choose><c:when test="${null != permissionGroupList && fn:length(permissionGroupList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
			<input type="checkbox" value="${product.id}" name="optionPermProductBox" /><c:out value="${product.name}" />
			</span>
				<ul style="display: none;">
					<c:forEach items="${permissionGroupList}" var="permGroup" varStatus="pgSta">
						<c:if test="${permGroup.productCode == product.id}">
							<!-- 输出权限列表 start -->
							<li class="<c:if test="${null != permGroup.optionPermList && fn:length(permGroup.optionPermList)>0}">expandable</c:if>">
							<span class="<c:choose><c:when test="${null != permGroup.optionPermList && fn:length(permGroup.optionPermList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
							<input type="checkbox" value="${permGroup.id}" name="optionPermGroupBox" /><c:out value="${permGroup.name}" />
							</span>
							<c:if test="${null != permGroup.optionPermList && fn:length(permGroup.optionPermList)>0}">
								<ul style="display: none;">
								<c:forEach items="${permGroup.optionPermList}" var="perm" varStatus="permSta">
									<c:if test="${perm.permissionGroupCode == permGroup.id}">
										<li class="expandable">
										<span class="file">
										<input type="checkbox" value="${perm.id}" name="optionPermIds" /><c:out value="${perm.name}" />
										</span>
										</li>
									</c:if>
								</c:forEach>
								</ul>
							</c:if>
							</li>
							<!-- 输出权限列表 end -->
						</c:if>
					</c:forEach>
				</ul>
			</li>
			</c:if>
			<!-- 输出权限组列表 end -->
		</c:forEach>
		<!-- 输出产品列表 end -->
		</ul>
		</div>
		<!-- 操作权限结束 -->