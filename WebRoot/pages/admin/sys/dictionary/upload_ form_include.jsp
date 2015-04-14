<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@ include file="/common/taglibs.jsp" %>
	<input type="hidden" id="id" name="id" value=""/>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>上传保存位置：</label></th>	
		 <td class="pb_frmtd">
		 	<input type="text" name="uploadSavePath" id="uploadSavePath" value="${uploadSavePath}" class=" input_text" maxlength="125" style="width:150px"/>
			<span class="required">*</span><font color='red'><form:errors path="uploadSavePath"/></font>
		</td>
	 </tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>类型和大小：</label></th>	
		 <td class="pb_frmtd">
		<input type="text" name="uploadTypeAndSize" id="uploadTypeAndSize" value="${uploadTypeAndSize}" class="input_text" maxlength="125" style="width:150px"/>
		<span class="required">*</span><font color='red'><form:errors path="uploadTypeAndSize"/></font>
		</td>
	</tr>
	 