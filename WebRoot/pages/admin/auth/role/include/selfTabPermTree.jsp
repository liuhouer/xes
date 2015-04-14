<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

		<!-- Tag权限开始 -->
		<div style="border:#666666 solid 1px;">
		<div>Tag权限</div>
		<div id="tagCtrl">
		<a title="" href="#">关闭全部</a>
		<a title="" href="#">展开全部</a>
		</div>
		<ul id="tagPermtree" class="filetree">
		<!-- 输出产品列表 start -->
		<c:forEach items="${productList}" var="product" varStatus="proSta">
			<!-- 输出权限组列表 start -->
			<c:if test="${fn:length(permissionGroupList)>0}">
				<li class="expandable">
				<span class="<c:choose><c:when test="${null != permissionGroupList && fn:length(permissionGroupList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
				<input type="checkbox" value="${product.id}" name="tagPermProductBox" />
				<c:out value="${product.name}" />
				</span>
					<ul style="display: none;">
						<c:forEach items="${permissionGroupList}" var="permGroup" varStatus="pgSta">
							<c:if test="${permGroup.productCode == product.id}">
								<!-- 输出权限列表 start -->
								<li class="<c:if test="${null != permGroup.tagPermList && fn:length(permGroup.tagPermList)>0}">expandable</c:if>">
								<span class="<c:choose><c:when test="${null != permGroup.tagPermList && fn:length(permGroup.tagPermList)>0}">folder</c:when><c:otherwise>file</c:otherwise></c:choose>">
								<input type="checkbox" value="${permGroup.id}" name="tagPermGroupBox" /><c:out value="${permGroup.name}" />
								</span>
								<c:if test="${null != permGroup.tagPermList && fn:length(permGroup.tagPermList)>0}">
									<ul style="display: none;">
									<c:forEach items="${permGroup.tagPermList}" var="perm" varStatus="permSta">
										<c:if test="${perm.permissionGroupCode == permGroup.id}">
											<li class="expandable">
											<span class="file">
											<input type="checkbox" value="${perm.id}" name="tagPermIds" /><c:out value="${perm.name}" />
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
		<!-- Tag权限结束 -->