<%@ include file="/common/taglibs.jsp" %>
<%-- Error Messages --%>
<c:if test="${flash.success != null}">
	<noscript>
	<div class="flash_success">
		${flash.success}<br/>
	</div>    
	</noscript>
	<script type="text/javascript">
		$(document.body).ready(function(){
			alert("${flash.success}");
		});
	</script>
</c:if>

<%-- Info Messages --%>
<c:if test="${flash.error != null}">
	<noscript>
	<div class="flash_error">
		${flash.error}<br/>
	</div> 
	</noscript>
	<script type="text/javascript">
		$(document.body).ready(function(){
			alert("${flash.error}");
		});
	</script>
</c:if>
