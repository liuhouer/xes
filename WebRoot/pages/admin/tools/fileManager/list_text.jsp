<%@page import="com.bruce.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
  <title>文件管理</title>
</up72:override>
<up72:override name="content">
<div class="workground">
  <div class="head_content">
    <div class="navBar"> <a href="#">桌面</a> » <a class="" href="${ctx}/admin/tools/fileManager/img">文件管理</a> </div>
    <div class="search mainHead">
      <table cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
          <td><div class="op_area">
              <table cellspacing="2" cellpadding="0" border="0" width="100%">
                <tr>
                  <td class="functop">&nbsp;</td>
                </tr>
                <tr>
                  <td class="func"><a onclick="alert('上传')" href="javascript:;" class="sysiconBtn addorder">上传</a></td>
                </tr>
              </table>
            </div></td>
          <td><div class="op_area">
              <table cellspacing="0" cellpadding="0" border="0" width="100%">
                <tr>
                  <td class="functop"><h3>默认操作区</h3></td>
                </tr>
                <tr>
                  <td class="func"><span  onclick="alert('删除')" class="sysiconBtn delete">删除</span><a  onclick="alert('批量编辑')"  class="sysiconBtn edit">批量编辑</a></td>
                </tr>
              </table>
            </div></td>
        </tr>
      </table>
    </div>
  </div>
  <!---文件夹遍历开始-->
  <div>
   	<c:forEach items="${filePathList}" var="item" varStatus="status">
 		<a href="${ctx}/admin/tools/fileManager/${type}?dir=${item.filePath}">${item.name}</a>
 	</c:forEach>
	<br />
	<a href="${ctx}/admin/tools/fileManager/${type}?dir=${dir}">返回</a>
  </div>
  <!---文件夹遍历结束-->
  
  <!--文件开始-->
  <div class="mainHead">
    <div class="headContent">
      <div class="finder_head">
        <div class="span_2">
          <input type="checkbox" id="option1" onclick="setAllCheckboxState('items',this.checked)">
          全选</div>
        <div class="span_3"> 操作</div>
        <div class="span_4"><%=FileInfo.ALIAS_NAME%></div>
        <div class="span_2"><%=FileInfo.ALIAS_SIZE%></div>
        <div class="span_6"><%=FileInfo.ALIAS_FILE_PATH%></div>
        <div class="span_3"><%=FileInfo.ALIAS_EDITY_TIME%></div>
      </div>
    </div>
  </div>
  <div class="pb_main" style="visibility:visible; opacity:1;">
    <div class="finder">
      <div class="finder_list">
        <c:forEach items="${fileList}" var="item" varStatus="status">
          <div class="highlight_row row">
            <div class="row_line hand" rel="">
              <div class="span_2">
                <input type="checkbox" name="items" value="${item.filePath}" class="sel" tags="null">
              </div>
              <div class="span_3"><a href="javascript:;"  onclick="alert('编辑')" class="sysiconBtnNoIcon">编辑</a>&nbsp;</div>
              <div class="span_4">
                <c:out value='${item.name}'/>
                &nbsp;</div>
              <div class="span_2">
                <c:out value='${item.size}'/>
                &nbsp;</div>
              <div class="span_6" title="<c:out value='${item.filePath}'/>">
                <c:out value='${item.filePath}'/>
                &nbsp;</div>
              <div class="span_3">
                <fmt:formatDate value='${item.editTime}' pattern='yyyy-MM-dd HH:mm'/>
                &nbsp;</div>
            </div>
          </div>
        </c:forEach>
        <div style="display:block; height:0px;"></div>
      </div>
    </div>
  </div>
  <!--文件结束-->
</div>
<script type="text/javascript">
$(".kt_Mleft li").menuMouse({selCss:"on",overCss:"over",index:"/admin/tools/fileManager/img"});
</script>
</up72:override>
<%@ include file="base.jsp" %>
