<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

		<!-- 菜单权限开始 -->
		<div style="border:#666666 solid 1px;">
		<div>菜单权限</div>
		<div id="menuCtrl">
		<a title="" href="#">关闭全部</a>
		<a title="" href="#">展开全部</a>
		</div>
		<ul id="menuPermtree" class="filetree">
		<!-- 输出产品列表 start -->
		<c:forEach items="${productList}" var="product" varStatus="proSta">
			<!-- 输出权限组列表 start -->
			<c:if test="${fn:length(permissionGroupList)>0}">
				<li class="expandable">
				<span class="<c:choose><c:when test="${null != permissionGroupList && fn:length(permissionGroupList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
				<input type="checkbox" value="${product.id}" name="menuPermProductBox" />
				<c:out value="${product.name}" />
				</span>
					<ul style="display: none;">
						<c:forEach items="${permissionGroupList}" var="permGroup" varStatus="pgSta">
							<c:if test="${permGroup.productCode == product.id}">
								<!-- 输出权限列表 start -->
								<c:if test="${fn:length(permGroup.menuPermList)>0}">
									<li class="expandable">
									<span class="<c:choose><c:when test="${null != permissionList && fn:length(permissionList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
									<input type="checkbox" value="${permGroup.id}" name="menuPermGroupBox" /><c:out value="${permGroup.name}" />
									</span>
										<ul style="display: none;">
										<c:forEach items="${permGroup.menuPermList}" var="perm" varStatus="permSta">
											<c:if test="${perm.permissionGroupCode == permGroup.id}">
												<li class="expandable">
												<span class="file">
												<input type="checkbox" value="${perm.id}" name="menuPermIds" /><c:out value="${perm.name}" />
												</span>
												</li>
											</c:if>
										</c:forEach>
										</ul>
									</li>
								</c:if>
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