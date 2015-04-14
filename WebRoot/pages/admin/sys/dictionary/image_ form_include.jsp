<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.up72.sys.model.Dictionary"%>
<%@ include file="/common/taglibs.jsp" %>
	<input type="hidden" id="id" name="id" value=""/>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>上传大小限制：</label></th>	
		 <td class="pb_frmtd">
		 	<form:input path="imageSaveSize" id="imageSaveSize" class=" input_text" maxlength="125" />
			<span class="required">*</span><font color='red'><form:errors path="imageSaveSize"/></font>
		</td>
	 </tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>存储大小限制：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imageSaveSize" id="imageSaveSize"  class="input_text" maxlength="125" />
		<span class="required">*</span><font color='red'><form:errors path="imageSaveSize"/></font>
		</td>
	</tr>
	 <tr class="pb_frmtr">
		<th class="pb_frmth"><label>最大宽度：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imageMaxWidth" id="imageMaxWidth" class=" input_txt" maxlength="125" />
		<span class="required"></span><font color='red'><form:errors path="imageMaxWidth"/></font>
		</td>
	</tr>
	<tr class="pb_frmtr">
		<th class="pb_frmth"><label>最大高度：</label></th>	
		 <td class="pb_frmtd">
		<form:textarea rols="5" cols="25" path="imageMaxHeigth" id="imageMaxHeigth" class="input_txt" maxlength="100" />
		<font color='red'><form:errors path="imageMaxHeigth"/></font>
		</td>
	</tr>	
 	<tr class="pb_frmtr">
		<th class="pb_frmth"><label>是否强制转换：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imageIsConvert" id="imageIsConvert" class=" input_txt" maxlength="125" />
		<font color='red'><form:errors path="imageIsConvert"/></font>
		</td>
	</tr>
 	<tr class="pb_frmtr">
		<th class="pb_frmth"><label>缩略图方案：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imageBreviaryImgSize" id="imageBreviaryImgSize" class=" input_txt" maxlength="125" />
		<font color='red'><form:errors path="imageBreviaryImgSize"/></font>
		</td>
	</tr>
 	<tr class="pb_frmtr">
		<th class="pb_frmth"><label>水印图片尺寸：</label></th>	
		 <td class="pb_frmtd">
		<form:input path="imageWaterMarkSize" id="imageWaterMarkSize" class=" input_txt" maxlength="125" />
		<font color='red'><form:errors path="imageWaterMarkSize"/></font>
		</td>
	</tr>