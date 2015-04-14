<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="permMenu" class="up72_nav">
</div>
<script type="text/javascript">
	var permJson;
	<c:if test="${permJson != null && permJson != ''}">
		permJson = ${permJson};
	</c:if>
	var html = "<ul>";
	$(permJson).each(function(i,group){
		var permissionGroupId = <c:out value="${AUTH_PERMISSION_GROUP.id}" default="100"/>;
		// on_tlink 
		//if(group.id == permissionGroupId){
			html += "<li id='leftMenu_"+group.id+"'>";
		//}else{
		//	html += "<li class='nav_top'>";
		//}
		var imgPath = "${ctx}/styles/default/skins/deepblue/images/icon_default.png";
		if(group.imgPath!=''){
			imgPath = group.imgPath;
		}
		html += "<a href='${ctx}/admin/goProduct/"+group.id+"' id='topSubMenu_" + group.id + "' title='"+group.name+"'><span class=\"navIco\"><img src=\"${ctx}" + imgPath + "\" /></span>"+group.name+"</span></a>";
		
		html += "<ul class='up72_subNav'>";
		$(group.permList).each(function(j,perm){
			var em = "<em>";
			if(j<10){
				em += "0" + (j+1);
			}else{
				em += (j+1);
			}
			em += "</em>"
			html += "<li>";
			html += "<a href='javascript:;' id='leftSubMenu_" + group.id + "_" + perm.id + "' onclick=\"$.forward('${ctx}"+perm.url+"',"+perm.id+");\" title='"+perm.name+"' >";
			html += em + perm.name;
			html += "</a>";
			html += "</li>";
		});
		html += "</ul>";
		
		html += "</li>";
	});
	html += "</ul>";
	$("#permMenu").html(html);

	$(document).ready(function() {
		// Store variables
		var accordion_head = $('.up72_nav > ul > li > a'),
			accordion_body = $('.up72_nav > ul > li > .up72_subNav');
		// Open the first tab on load
		// accordion_head.first().addClass('active').next().slideDown('normal');
		// Click function
		accordion_head.on('click', function(event) {
			// Disable header links
			event.preventDefault();
			// Show and hide the tabs on click
			if ($(this).attr('class') != 'active'){
				accordion_body.slideUp('normal');
				$(this).next().stop(true,true).slideToggle('normal');
				accordion_head.removeClass('active');
				$(this).addClass('active');
			}
		});
	});
</script>