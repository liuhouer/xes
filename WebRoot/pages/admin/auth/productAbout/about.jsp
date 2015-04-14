<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
		<div class="up72_tabs" id="about">
			<div class="tabs_con"> 
				<c:forEach items="${productAboutList}" var="productAbout">
					<span rel="#about${productAbout.id}" style="width:240px"><a href="javascript:;">${productAbout.title}</a></span>
				</c:forEach>
			</div>
		</div>
		<c:forEach items="${productAboutList}" var="productAbout">
			<div id="about${productAbout.id}">${productAbout.content}</div>
		</c:forEach>
	</div>
<script language="javascript" type="text/javascript">
	$("#about").find(".tabs_con span").up72Tabs(
		"#about",
		{
			selCss : "current",
			showCss : "tabs"
		}
	);
</script>