<%@ page contentType="text/html;charset=UTF-8" %>

<script src="<c:url value="/scripts/jquery-1.7.2.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	ctx = "${ctx}";
</script>		
<script src="<c:url value="/scripts/main.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/application.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/bgiframe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/weebox/weebox.js"/>" type="text/javascript"></script>
<link href="<c:url value="/scripts/weebox/weebox.css"/>" type="text/css" rel="stylesheet" />
<script src="<c:url value="/scripts/validate/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/messages_cn.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/jquery.metadata.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/validate/validate.extend.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/scripts/uptabs.js"></script>
<script src="<c:url value="/scripts/up72menumouse.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${ctx}/scripts/dropdown/dropdown.js"></script>
<link href="<c:url value="/scripts/dropdown/dropmenu.css"/>" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${ctx}/scripts/macdock/macdock.js"></script>
<link href="<c:url value="/scripts/macdock/macdock.css"/>" type="text/css" rel="stylesheet" />



<style type="text/css">
.menusetting{ display:none;	background:#000; background:url(${ctx}/images/navigation.jpg) no-repeat 0 center; width:70px; padding-top:28px; margin-top:-20px; border-radius:2px; z-index:1; }
.menusetting a{	padding-left:2px; padding-top:2px; padding-bottom:2px; width:70px; height:23px; }
.menusetting a:hover{ background:url(${ctx}/images/more_menu_li.gif) repeat-x; width:62px; height:23px; padding-top:2px; color:#000;}
</style>