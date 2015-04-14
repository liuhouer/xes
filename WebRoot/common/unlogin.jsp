<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	ctx = "${ctx}";
</script>
<script src="${ctx }/scripts/main.js" type="text/javascript"></script>
<script type="text/javascript">
	getTopWindow().location.href="${ctx }/admin/authUser/logout";
</script>
