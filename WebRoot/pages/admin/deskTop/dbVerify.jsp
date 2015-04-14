<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="up72_edit">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="edit_table">
		<c:forEach items="${resultMap}" var="item">
		<tr class="pb_frmtr">
			<th class="pb_frmth">${item.key}&nbsp;</th>
			<td class="pb_frmtd">${item.value}&nbsp;</td>
		</tr>
		</c:forEach>
	</table>
</div>