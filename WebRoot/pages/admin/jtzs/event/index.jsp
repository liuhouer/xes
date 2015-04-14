<%@page import="com.xes.jtzs.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<up72:override name="head">
  <title><%=Event.TABLE_ALIAS%> 维护</title>
  <script src="${ctx}/scripts/rest.js" ></script>
  <link href="${ctx}/scripts/simpletable/simpletable.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/simpletable/simpletable.js"></script> 
  <script type="text/javascript" src="${ctx}/scripts/columnshow.js"></script>
  <link href="${ctx}/scripts/grid/css/flexigrid.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/scripts/grid/flexigrid-source.js"></script> 
  <script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化  动  作
			window.simpleTable = new SimpleTable('admin_jtzs_event_search_form',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
		function yulan(id){
			show('iframe#${ctx}/admin/jtzs/event/preview?eventId='+id,'频道预览',500,960);
		}
	</script> 
  <script type="text/javascript" src="<c:url value="/scripts/extend.div.1.0.js"/>"></script> 
</up72:override>
<up72:override name="content"> 
  
  <!--搜索-->
  <div class="up72_search">
    <form id="admin_jtzs_event_search_form" name="admin_jtzs_event_search_form" method="get">
      <div class="search_con"> 
        <div class="search_txt"><%=Event.ALIAS_PROVINCE_ID%>：
     	<select id="provinceId" name="provinceId" style="width: 120px">
     	        <option value="" <c:if test="${query.provinceId == ''}">selected="selected"</c:if> >请选择</option>
       		<c:forEach items="${provinceList}" var="item" varStatus="status">
			 	<option value="${item.id}" <c:if test="${item.id == query.provinceId}">selected="selected"</c:if> >${item.name}</option>
       		</c:forEach>
		</select>
        </div>
        
        <div class="search_txt"><%=Event.ALIAS_TITLE%>：
          <input type="text" id="title" name="title" class="input_text" value="${query.title}">
        </div>

        <div class="search_txt"><%=Event.ALIAS_SEND_TO%>：
          <select id="sendTo" name="sendTo" style="width: 120px">
                <option value="" <c:if test="${query.sendTo == ''}">selected="selected"</c:if> >请选择</option>
		  	 	<option value="0" <c:if test="${query.sendTo == 0}">selected="selected"</c:if> >学生</option>
			 	<option value="1" <c:if test="${query.sendTo == 1}">selected="selected"</c:if> >老师</option>
		</select>
        </div>
        <div class="search_txt">有效期：
        <input value="<fmt:formatDate value="${query.startTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="startTime" name="startTime" class=" input_text" />至
        <input value="<fmt:formatDate value="${query.endTimeDate}" pattern="yyyy-MM-dd HH:mm"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" id="endTime" name="endTime"   class=" input_text" />
         
         
         
        </div>

   
       
        <div class="search_btn">
          <div class="input_button">
            <button name="btnU" type="submit" onclick="$(this).parents('form').submit();" class="button" value="查询"><span>查询</span></button>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!--end搜索-->
  
  <form id="admin_jtzs_event_page_form" name="admin_jtzs_event_page_form" method="get">
    <table id="admin_jtzs_event_table">
      <thead>
        <tr>
          <th showColumn="checkbox" width="25"><input type="checkbox" id="checkall" onclick="setAllCheckboxState('items',this.checked);" /></th>
         			<th showColumn="index" width="20">序号</th>
                    <th sortColumn="PROVINCE_ID" showColumn="provinceId" width="100"><%=Event.ALIAS_PROVINCE_ID%></th>
                    <th sortColumn="TITLE" showColumn="title" width="100"><%=Event.ALIAS_TITLE%></th>
                    <th sortColumn="SEND_TO" showColumn="sendTo" width="50"><%=Event.ALIAS_SEND_TO%></th>
                    <th sortColumn="SEND_TIME" showColumn="sendTime" width="150"><%=Event.ALIAS_SEND_TIME%></th>
                    <th sortColumn="SEND_STATUS" showColumn="sendStatus" width="50"><%=Event.ALIAS_SEND_STATUS%></th>
                    <th sortColumn="SEND_USER" showColumn="sendUser" width="50"><%=Event.ALIAS_SEND_USER%></th>
                    <th sortColumn="START_TIME" showColumn="startTime" width="250">有效期</th>
          			<th showColumn="option" width="200"><label>操作</label></th>
           </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.result}" var="item" varStatus="status">
          <tr rel="${ctx}/pages/admin/jtzs/event/tab.jsp?id=${item.id}">
            <td showColumn="checkbox"><input type="checkbox" id="items" name="items" value="${item.id}" class="sel" tags="null"></td>
            <td showColumn="index">${page.thisPageFirstElementNumber + status.index}</td>
             <td showColumn="provinceId">
             <c:if test="${item.province.name == null}">
             <c:out value="全国"></c:out>
             </c:if>
             <c:if test="${item.province.name != null}">
             <c:out value='${item.province.name}'/>
             </c:if>
             -
             <c:if test="${item.city.name == null}">
             <c:out value="所有"></c:out>
             </c:if>
             <c:if test="${item.city.name != null}">
               <c:out value='${item.city.name}'/>
             </c:if>
&nbsp; </td>
                        <td showColumn="title"><c:out value='${item.title}'/>
&nbsp; </td>
                        <td showColumn="sendTo"><c:if test="${item.sendTo == 0}"> <c:out value='学生'/></c:if><c:if test="${item.sendTo == 1}"> <c:out value='老师 '/></c:if> <c:if test="${item.sendTo == 3}"> <c:out value='指定发送人员 '/></c:if>   
&nbsp; </td>
                        <td showColumn="sendTime"><fmt:formatDate value="${item.sendTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
&nbsp; </td>
                        <td showColumn="sendStatus"><c:if test="${item.sendStatus == 0}"> <c:out value='待发送'/></c:if><c:if test="${item.sendStatus == 1}"> <c:out value='已发送 '/></c:if>  <c:if test="${item.sendStatus == 2 }"> <c:out value='已冻结'/></c:if>
&nbsp; </td>
                        <td showColumn="sendUser"><c:out value='${item.userName}'/>
&nbsp; </td>
                        <td showColumn="startTime"><fmt:formatDate value="${item.startTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
                        <c:if test="${item.startTimeDate ne null}"> 至 </c:if>               
                        <fmt:formatDate value="${item.endTimeDate}" pattern="yyyy-MM-dd HH:mm"/>
&nbsp; </td>
                       
<td showColumn="option">
<a href="javascript:;"  <c:if test="${item.sendStatus != 1}">onclick="show('${ctx}/admin/jtzs/event/${item.id}/edit','<%=Event.TABLE_ALIAS%>',600)"</c:if> class="sysiconBtnNoIcon"><font <c:if test="${item.sendStatus != 1}">color="green"</c:if> <c:if test="${item.sendStatus == 1}">color="gray"</c:if> >编辑</font> </a>&nbsp;
<a href="javascript:;"  <c:if test="${item.sendTo == 3 && item.sendStatus!=1}">    onclick="show('iframe#${ctx}/admin/jtzs/eventSendToUser/${item.id}/edit','管理发送人员',1100,600)"</c:if> class="sysiconBtnNoIcon"><font <c:if test="${item.sendTo != 3 ||item.sendStatus==1}">color="gray"</c:if> <c:if test="${item.sendTo == 3 && item.sendStatus!=1}">color="green"</c:if>> 编辑发送人</font> </a>&nbsp;
<a href="javascript:;" onclick="javascript:yulan(${item.id});" class="sysiconBtnNoIcon">预览</a>&nbsp;
<a href="javascript:;"  <c:if test="${item.sendStatus != 1}">onclick="sends('${item.id}')" </c:if> <c:if test="${item.sendStatus == 1}">onclick="freeze('${item.id}')" </c:if>   class="sysiconBtnNoIcon" >  <c:if test="${item.sendStatus != 1}">发送</c:if> <c:if test="${item.sendStatus == 1}">冻结</c:if>   </a>
<a href="javascript:;"  <c:if test="${item.sendStatus != 1}">  onclick="removes('${item.id}')" </c:if>  class="sysiconBtnNoIcon" ><font <c:if test="${item.sendStatus != 1}">color="green"</c:if> <c:if test="${item.sendStatus == 1}">color="gray"</c:if> >删除</font></a>
</td>

             </tr>
        </c:forEach>
      </tbody>
    </table>
    <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
  </form>
  <form id="rmform" ></form>
  <script type="text/javascript">
	// 列选择显示处理
	$.showcolumn(${showColumn});
	// 排序处理
	$.sortcolumn({
		form : "#admin_jtzs_event_search_form",
		data : "pageNumber=${page.thisPageNumber}&pageSize=${page.pageSize}",
		columns : $("#admin_jtzs_event_table th[sortColumn]"),
		sortColumns : "${pageRequest.sortColumns}"
	});
	
	$("#admin_jtzs_event_table").rowAction({	
		"url" : "/pages/admin/jtzs/event/tab.jsp",
		"except" : ["checkbox","index","option"],
		"pop" : true,
		"poptitle" : "<%=Event.TABLE_ALIAS%>标签",
		"popw" : 600
	});
	// 表格列表处理
	$('#admin_jtzs_event_table').flexigrid({
		height: 'auto',
		striped : true,
		buttons : [
			{name: "添加", bclass: "addorder", onpress : function(){show("${ctx}/admin/jtzs/event/new","<%=Event.TABLE_ALIAS%>添加",600)}},
			{name: '删除', bclass: 'delete', onpress : function(){doRestEdit({confirmMsg:'确认删除选中的记录吗？',url:'${ctx}/admin/jtzs/event/',batchBox:'items',boxCon:'#admin_jtzs_event_page_form',form:'#admin_jtzs_event_page_form',method:'delete'})}}
		]
	});
	
	function removes(id){
	    $("#rmform").attr("action", "${ctx}/admin/jtzs/event/"+id+"/remove");
		$("#rmform").submit();
	}
	function sends(id){
	    $("#rmform").attr("action", "${ctx}/admin/jtzs/event/"+id+"/send");
		$("#rmform").submit();
	}
	function freeze(id){
	    $("#rmform").attr("action", "${ctx}/admin/jtzs/event/"+id+"/freeze");
		$("#rmform").submit();
	}
</script> 
</up72:override>
<%@ include file="base.jsp" %>
