<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<meta content="" name="description">
		<meta content="" name="author">
		<link rel="stylesheet" href="${ctx}/pages/jtzs/css/style.css">
		<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/xes_jtzs.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/jquery.lazyload.js" ></script>
		<script type="text/javascript" src="${ctx}/scripts/jquery.tmpl.min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/xes_jtzs.js"></script>
		<up72:block name="head" />
	</head>
	<body>
		<up72:block name="content" />
		<script type="text/javascript">
			function questionFun(dom) {
				var id = "";
				while(null!=dom){
					id= dom.id;
					if(/status=\d+_id=\d+/.test(id)){
		                dom = null;
						return id;
					}else{
						dom = dom.parentNode;
					}
				}
				return id="";
			}
		</script>
	</body>
</html>