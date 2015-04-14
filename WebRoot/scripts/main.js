if(typeof(ctx)=='undefined' || null==ctx){
	ctx = "";
}

/**
 * 系统共用js，提供常用的工具方法和错误信息验证方法
 *
 * author : bh.iune
 * version : 1.0
 * create : 2010.07.24
 */



/**
  * 关闭顶级弹出层
  */
function closeBox(){
	if(!isNull($)
		&& !isNull($.weeboxs)
		&& !isNull($.weeboxs.getTopBox())){
		try{
			$.weeboxs.getTopBox().close();
		}catch(e){
			$(".weedialog").remove();
			$(".dialog-mask").remove();
		}
	}
}

/** 
 * 关闭所有弹出框
 */
function closeAllBox(){
	$(".weedialog").remove();
	$(".dialog-mask").remove();
}

/** 
 * 关闭所有弹出框
 */
function hiddenAllBox(){
	$(".weedialog").hide();
	$(".dialog-mask").hide();
}


/*********************************************************************************/

/*
 * 空值判断处理函数，当传入的对象为string时会做空字符校验
 * 需要依赖jquery的trim函数
 *
 * @param obj : 需要判断的对象
 * @return : 空位true，非空为false
 */
function isNull(obj){
	var result = true;
	var type = typeof(obj);
	/*undefined or null return false*/
	if(type == "undefined"
		|| obj == null){
		result = true;
	} 
	/*type is string */
	else if (type == "string"){
		obj = $.trim(obj);
		if( obj==""
			|| obj == "undefined"){
			result = true;
		}else {
			result = false;
		}
	} 
	/*other object */
	else{
		result = false;
	}
	return result;
}

/**
 * 获得最顶级window对象
 */
function getTopWindow(){
	var result = window.parent;
	while(!isNull(result.parent)
		&& result != result.parent){
		result = result.parent;
	}
	return result;
}

function gogo(msg){
	var w = getTopWindow();
	w.jQuery.weeboxs.open(msg, {title: '提示', type:'alert'});
}
/**
 * 覆盖js内置alert函数 ，需要引入jquery.js，bgiframe.js,weebox.js以及weebox.css
 * @ param msg : 显示的消息信息
 * @ param time : 弹出框存在的时间
 * @ param onclosecallBack : 窗口关闭的回调函数
 */
function alert(msg,time,onclosecallBack){
	var w = getTopWindow();
	msg = "<div class='dialog-content-message'>" + msg + "<div>";
	if(isNull(time) || time <= 0){
		return w.jQuery.weeboxs.open(msg, {title: '提示', type:'alert',
				onclose : function(box){
					try{
					if(!isNull(onclosecallBack)){
						evalFunction(onclosecallBack);
					}}catch(e){}
				}});
				
	}else{
		return w.jQuery.weeboxs.open(
			msg, 
			{
				title:"提示<font color='red'>"+time+"</font>", 
				type:'alert', 
				timeout:time,
				onopen:function(box){
					var handle = setInterval(function(){
						time = time - 1;
						box.dt.find('font').html(time+'');
						if (time<=0) {
							clearInterval(handle);
						}
					}, 1000);
				},
				onclose : function(box){
					try{
					if(!isNull(onclosecallBack)){
						evalFunction(onclosecallBack);
					}}catch(e){}
				}
			}
		);
	}
}


/**
 * options 
 * html : 弹出层的html
 * width : 宽度
 * height : 高度
 * onclose : 关闭的回调
 */
function wait(options){
	if(isNull(options.html)){
		options.html = "";
	}
	if(isNull(options.width) || options.width === 0 ){
		options.width = 400;
	}
	if(isNull(options.height) || options.height === 0 ){
		options.height = 300;
	}
	
	if(startWith(options.html,"#") || startWith(options.html,".")) {
		jQuery.weeboxs.open(options.html, {
			contentType:'selector',showButton:false,width:options.width,height:options.height,
			onclose : function(box){
				if(!isNull(options.onclose)){
					evalFunctdion(options.onclose);
				}
			}
		});
	}else{
		jQuery.weeboxs.open(options.html, {
			type:'alert',showButton:false,width:options.width,height : options.height,
			onclose : function(box){
				if(!isNull(options.onclose)){
					evalFunctdion(options.onclose);
				}
			}
		});
	}
}

/*
 * 覆盖js内置的confirm函数，需要引入 jquery.js，bgifrmae.js，weebox.js以及weebox.css
 *
 * @param msg : 显示的消息信息
 * @param okCall : 点击确定的回执函数
 * @param cancelCall : 点击取消后的回执函数
 */
function confirm(msg,okCall,cancelCall){
	var w = getTopWindow();
	msg = "<div class='dialog-content-message'>" + msg + "<div>";
	w.jQuery.weeboxs.open(msg, {
		title:'提示',
		type:'confirm',
		onok:function(box){
			evalFunction(okCall);
			box.close();
		},
		oncancel:function(box){
			evalFunction(cancelCall);
			box.close();
		}
	});
}

/**
 * 还原为系统内置confirm
 */
/*
var sysConfirm = window.confirm;

window.confirm = function confirmSelf(msg,okCall,cancelCall){
	if(sysConfirm(msg)){
		evalFunction(okCall);	
	}else{
		evalFunction(cancelCall);
	}
}
*/

/**
 * ajax打开指定页面
 * 
 * param 
 * url : 请求的url
 * title : 标题
 * width : 宽(默认300)
 * height : 高(默认200)
 * _absHeight : 距离浏览器顶部高度(默认100)
 */
function show(url,title,width,height,_absHeight){
	var w = getTopWindow();
	if(isNull(title)){
		title = "";
	}
	if(isNull(width) || width === 0 ){
		width = 400;
	}
	//if(isNull(height) || height === 0 ){
	//	height = 300;
	//}}
	if(isNull(_absHeight)){
		_absHeight = "100";
	}
	if(startWith(url,"#") || startWith(url,".")) {
		w.jQuery.weeboxs.open(url, {absHeight:_absHeight,title:title, contentType:'selector',showButton:false,width : width,height : height});
	} else if(startWith(url,"iframe#")) {
		url = url.substr("iframe#".length);
		w.jQuery.weeboxs.open(url, {absHeight:_absHeight,title:title, contentType:'iframe',showButton:false,width : width,height : height});
	} else {
		w.jQuery.weeboxs.open(url, {absHeight:_absHeight,title:title, contentType:'ajax',showButton:false,width : width,height : height});
	}
}

/**
 * ajax打开指定页面
 * 
 * param 
 * url : 请求的url
 * title : 标题
 * width : 宽(默认300)
 * height : 高(默认200)
 * onclose : 关闭的回调
 */
function optionShow(options){
	var w = getTopWindow();
	if(isNull(options.title)){
		title = "";
	}
	if(isNull(options.width) || options.width === 0 ){
		width = 400;
	}
	//if(isNull(options.height) || options.height === 0 ){
	//	height = 300;
	//}
	
	if(startWith(options.url,"#") || startWith(options.url,".")) {
		return w.jQuery.weeboxs.open(options.url, 
		{
			title:options.title,
			contentType:'selector',
			showButton:false,
			width:options.width,
			height:options.height,
			onclose : function(box){
				if(!isNull(options.onclose)){
					evalFunction(options.onclose);
				}
			}
		});
	} else if(startWith(options.url,"iframe#")) {
		url = url.substr("iframe#".length);
		return w.jQuery.weeboxs.open(options.url, 
		{
			title:options.title,
			contentType:'iframe',
			showButton:false,
			width:options.width,
			height:options.height,
			onclose : function(box){
				if(!isNull(options.onclose)){
					evalFunction(options.onclose);
				}
			}
		});
	} else {
		return w.jQuery.weeboxs.open(options.url, 
		{
			title : options.title, 
			contentType : 'ajax',
			showButton : false,
			width : options.width,
			height : options.height,
			onclose : function(box){
				if(!isNull(options.onclose)){
					evalFunction(options.onclose);
				}
			}
		});
	}
}


/**
 * ajax打开指定页面
 * 
 * param 
 * url : 请求的url
 * title : 标题
 * width : 宽(默认300)
 * height : 高(默认200)
 */
function showConfirm(url,title,width,height,okcallback,cancelcallback){
	var w = getTopWindow();
	if(isNull(title)){
		title = "";
	}
	if(isNull(width) || width === 0 ){
		width = 300;
	}
	//if(isNull(height) || height === 0 ){
	//	height = 200;
	//}
	if(startWith(url,"#") || startWith(url,".")) {
		return w.jQuery.weeboxs.open(url, {title:title,contentType:'selector',width:width,height:height,
				onok:function(box){
					evalFunction(okcallback,box);
					//box.close();
				},
				oncancel:function(box){
					evalFunction(cancelcallback,box);
					box.close();
				}
		});
	} else {
		return w.jQuery.weeboxs.open(url, {cache:true,title:title, contentType:'ajax',width:width,height : height,
				onok:function(box){
					evalFunction(okcallback,box);
					/*box.close();*/
				},
				oncancel:function(box){
					evalFunction(cancelcallback,box);
					box.close();
				}
			});
	}
}

/************************************************************************/


function startWith(source,str) {
	if(isNull(str) || isNull(source)){
		return false;
	}
	if(source.substr(0,str.length)==str){
		return true;
	} else {
		return false;
	}
}

function endWith(source,str) {
	if(isNull(str) || isNull(source)){
		return false;
	}
	if(source.substr(source.length-str.length)==str){
		return true;
	} else {
		return false;
	}
}
/********************************************************************************/

/**
 * 执行一个js方法，可以是一个方法名，也可以是一个js方法对象
 *
 * @param fun : js方法
 * @return 执行后的返回值
 */
function evalFunction(fun,obj){
	var funType = typeof(fun);
	if(funType=="function"){
		fun.call(obj);
	}else if(funType=="string"){
		eval(fun);
	}
}

/********************************************************************************/

/**
 * ajax调用，服务器异常捕获前台处理js方法，可以对服务器回执的错误进行分析和显示
 *
 * @param response : ajax回执数据对象
 * @param callback : 执行完该方法后，服务器未发生异常以后的回执方法
 * @return 没有错误返回true，发生错误false，并alert出错误信息
 */
function processResponse(response, callback) {	
	var result = false;
	/*如果response为空，返回true*/
	if(isNull(response)){
		result = true;
	}else{
		var responseData = $.parseJSON(response);
		/*如果返回的数据结果中result和message都为空，返回true*/
		if(isNull(responseData.message)
			&& isNull(responseData.result)){
			result = true;
		}else{
			/*显示服务器错误信息*/
			function showMessage(msg){
				if(isNull(responseData.message)){
					alert(msg);
				}else{
					alert(responseData.message);
				}
			}
			/*打印后台服务消息*/
			switch (responseData.result) {
			case 0 : /*成功*/
				result = true;
				break;
			case 1: /*未登录*/
				showMessage("请登录");
				break;
			case 2:	/*没有权限*/
				showMessage("您没有该权限");
				break;
			case 3: /*验证异常*/
				showMessage("验证未通过");
				break;
			case 4: /*服务错误*/
				showMessage("服务错误");
				break;
			case 5: /*服务器内部错误*/
				showMessage("服务器内部错误");
				break;
			default:
				showMessage("服务器异常，请联系管理员！");
			}
		}
	}
	
	/*如果返回为真，则执行回调函数*/
	if(result && !isNull(callback)){
		evalFunction(callback);
	}
	
	return result;
}

/**************************select start****************************************************/

/**
 * 判断指定select是否选中
 * 
 * @param name : select的name
 * @return : 有选中true，否则为false
 */
function isSelect(name,form){
	var flag = false;
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	
	$(selector).each(
		function(i,n){
			if($(n).attr("checked")){
				flag = true;
				return;
			}
		}
	);
	return flag;
}

/**
 * 获得select的选中值
 * 
 * @param name : seelct的name
 * @return : 形如id1，id2，id3的字符串
 */
function getSelected(name,form){
	var ids = "";
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	$(selector).each(
		function(i,n){
			if($(n).attr("checked")){
				ids += $(n).val()+",";
			}
		}
	);
	if(ids.indexOf(",")!=-1){
		ids = ids.substr(0,ids.length-1);
	}
	return ids;
}

/**
 * 获得select的选中的dom
 * 
 * @param name : seelct的name
 * @return : 形如id1，id2，id3的字符串
 */
function getSelectedDom(name,form){
	var array = new Array();
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	$(selector).each(
		function(i,n){
			if($(n).attr("checked")){
				array.push($(n));
			}
		}
	);
	return array;
}

/**
 * 获得select的选中的值的数组
 * 
 * @param name : seelct的name
 * @return : 形如id1，id2，id3的字符串
 */
function getSelectedArray(name,form){
	var array = new Array();
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	$(selector).each(
		function(i,n){
			if($(n).attr("checked")){
				array.push($(n).val());
			}
		}
	);
	return array;
}

/** 
 * chengbox的全选，全取消的轮换操作，需要有触发toggle事件的dom 
 *
 * @param dom : 触发操作的dom
 * @param name : checkbox name
 */
function toggleSelect(dom,name,form){
	if($(dom).attr("checked")){
		selectAll(name,form);
	}else{
		cancelAll(name,form);
	}
}

/**
 * 全选
 * 
 * @param name : seelct的name
 */
function selectAll(name,form){
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	$(selector).each(
		function(i,n){
			if(!$(n).attr("checked")){
				$(n).attr("checked","checked");
			}
		}
	);
}

/*
 * 全部取消
 */
function cancelAll(name,form){
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	$(selector).each(
		function(i,n){
			if($(n).attr("checked")){
				$(n).attr("checked","");
			}
		}
	);
}

/**
 * 反选
 * @param name : seelct的name
 */
function selectInvert(name,form){
	var selector = "input[name='"+name+"']";
	if(!isNull(form)){
		selector = form+" input[name='"+name+"']";
	}
	$(selector).each(
		function(i,n){
			if(!$(n).attr("checked")){
				$(n).attr("checked","checked");
			}else{
				$(n).attr("checked","");
			}
		}
	);
}
/**************************select end****************************************************/

/**
 * 不带遮罩的弹出层
 *
 * @param options 
 * showCss : 显示层时的CSS样式
 * offsetX : 横坐标偏移量
 * offsetY : 纵坐标偏移量
 * width : 300px;
 * height : 200px;
 */
(function($) {
$.fn.extend({
	showRel : function(options) {
		this.options = options || {};
		_default = {
			showCss : "pop_up",
			width : 300,
			height : 200,
			offsetX : 0,
			offsetY : 0,
			openCall : null
		};
		var self = this;
		self.rel = $(self).attr("rel");
		
		this.initOptions = function(){
			if(isNull(self.options.showCss)){
				self.options.showCss = _default.showCss;
			}
			if(isNull(self.options.width)){
				self.options.width = _default.width;
			}
			if(isNull(self.options.height)){
				self.options.height = _default.height;
			}
			if(isNull(self.options.offsetX)){
				self.options.offsetX = _default.offsetX;
			}
			if(isNull(self.options.offsetY)){
				self.options.offsetY = _default.offsetY;
			}
		}
		self.initOptions();
		
		this.init = function(){
			var showDom = null;
			if(startWith(self.rel,"#")){
				showDom = self.rel;
			}else{
				showDom = self.createDom();
			}
			$(showDom).hide(0);
			/*设置样式*/
			$(showDom).attr("className",self.options.showCss);
			/*设置宽高*/
			$(showDom).css({width : self.options.width+"px" ,height:self.options.height+"px"});
				
			$(self).bind("click",
				function(event){
					var x = event.clientX;
					var y = event.clientY +"px";
					var clientWidth = document.body.clientWidth;
					
					if(x > (clientWidth / 2)){
						$(showDom).css({marginTop: self.options.offsetY+"px"});
					}else{
						$(showDom).css({marginTop: self.options.offsetY+"px"});
					}
					$(showDom).toggle();
					if(!isNull(self.options.openCall)){
						evalFunction(self.options.openCall);
					}
				}
			);
		}
		
		this.createDom = function(){
			var id = "show_create_rel_div";
			
			if(isNull(dom)){
				$(self).after("<div id='"+id+"'></div>");
				$.ajax({
					type : "post",
					url : "tab.jsp",
					success : function(data){
						$("#"+id).html(data);
					}
				});
			}
			return "#"+id;
		}
		
		self.init();
	}
});
})(jQuery);

/*======================dom元素数据同步处理开始========================*/
/*
 * dom元素数据同步处理方法，该方法主要用于带有快捷方式和高级查询的输入事件同步处理。select元素添加onchang事件，input元素添加onchang事件和onkeyup事件
 *
 * @param source : 触发dom
 * @param desc   : 目标dom
 * @param syncInput : 是否同步inupt，值为flase则同步select后的input，true则根据name查找表单中需要同步的input
 */
syncValue = function(source,desc){
	var type = $(source).attr("tagName").toLowerCase();
	if(type == 'select'){
		selSyncValue(source,desc);
	} else if(type == 'textarea'){
		$(desc).val($(source).val());
	} else if(type == 'input'){
		var inputType = $(source).attr("type").toLowerCase();
		if (inputType == 'checkbox' || inputType == 'radio') {
			if($(source).attr("checked")){
				$(desc).attr("checked","checked");
			} else {
				$(desc).attr("checked","");
			}
		} else {
			inputSyncValue(source,desc);
		}
	}
}

/*
 * 同步input
 */
inputSyncValue = function(source,desc){
	var inputTear = "Value";
	var descType = $(desc).attr("tagName").toLowerCase();
	if(descType == 'select'){
		var inputName = $(source).attr("name");
		
		$(desc).find("option").each(
			function(i,obj){
				
				if(inputName == $(obj).val()){
					$(obj).attr("selected","selected");
				}else{
					$(obj).attr("selected","");
				}
			}
		);
		var descInput = $("#"+$(desc).attr("id")+inputTear);
		$(descInput).val($(source).val());
		$(descInput).removeAttr("name");
	}else{
		var sourceId = $(source).attr("id");
		if(endWith(sourceId,inputTear)){
			var selId = "#"+sourceId.substr(0,sourceId.indexOf(inputTear));
			
			if(!isNull($(selId).attr("tagName"))
				&& !isNull($("#searchColumnInputCreate").attr("tagName")
			    && $(selId).attr("tagName").toLowerCase()=='select')){
				$("#searchColumnInputCreate").val($(source).val());
			}
		}
		$(desc).val($(source).val());
	}
}

/*
 * 同步select
 */
selSyncValue = function(source,option){
	var inputTear = "Value";
	var typeOption = typeof(option);
	var sourceInput = $("#"+$(source).attr("id")+inputTear);
	/*$(sourceInput).attr("name",$(source).attr("name"));*/
	
	if(typeOption == 'boolean' && option==true){
		$(source).find("option").each(
			function(i,obj){
				if($(obj).attr("selected")){
					$("input[name='"+$(obj).val()+"']").val($(sourceInput).val());
				}else{
					$("input[name='"+$(obj).val()+"']").val("");
				}
			}
		);
	}else{
		var selValue = null;
		$(source).find("option").each(
			function(i,obj){
				if($(obj).attr("selected")){
					selValue = $(obj).val();
					return ;
				}
			}
		);
		var desc = option;
		$(desc).find("option").each(
			function(i,obj){
				if($(obj).val() == selValue){
					$(obj).attr("selected","selected");
				}else{
					$(obj).attr("selected","");
				}
			}
		);
		var descInput = $("#"+$(desc).attr("id")+inputTear);
		$(descInput).val($(sourceInput).val());
		/*$(descInput).attr("name",selValue);*/
		var id = "searchColumnInputCreate";
		if(isNull($("#"+id).attr("tagName"))){
			$(source).after("<input type='hidden' id='"+id+"' name='"+selValue+"' value='"+$(sourceInput).val()+"' />");
		}else{
			$("#"+id).attr("name",selValue);
			$("#"+id).val($(sourceInput).val());
		}
	}
}
/*======================dom元素数据同步处理结束========================*/

/*==================点击页面任意位置关闭页面弹出层效果处理开始======================*/
/**
 * 为document添加click事件，关闭指定的弹出层。是bindCloseAction和removeCloseAction方法的核心调用函数。
 	使用方法为接入函数:bindCloseAction，调用添加document的click事件，事件激发时调用该方法，关闭要关闭的层。调用完毕后调用removeCloseAction移除事件。
	注：该方法会和与document有关的click事件冲突。
 * @param array : 指定层的数组
 */
closeDiv = function(array){
	if(typeof(array) == "string"){
		array = eval(array);
	}
	$(array).each(
		function(i,obj){
			if($.isArray($(obj))){
				$(obj).each(
					function(j,_obj){
						try{$(_obj).hide();}catch(e){};
					}
				);
			}else{
				try{$(obj).hide();}catch(e){};
			}
		}
	);
	removeCloseAction();
}

bindCloseAction = function(source){
/*方法无效处理开始*/
	if(true){
		return ;
	}
/*方法无效处理结束*/
	
	removeCloseAction();
	var intl = window.setInterval(
		function(){
			window.clearInterval(intl);
			$(document).bind("click",function(event){
				var eventSource = event.srcElement;
				closeDiv(source);
			});			   
		}
		,100);
}

removeCloseAction = function(){
	$(document).unbind("click");
}
/*======================点击页面任意位置关闭页面弹出层效果处理结束======================*/

/**
 * 菜单的收起展开
 */
function toggleMenu(dom,menu,ed){
	$(dom).find(menu).toggle();
	/*opned  clded*/
	$(ed).toggleClass("clded");
	$(ed).toggleClass("opned");
}

/**
 * 批量编辑处理方法
 * options
 * url 处理的地址 
 * width 宽
 * height 高 
 * batchBox checkbox的name
 * boxCon : checkbox所在的dom选择器
 * form 表单
 */
doRestBatchEdit = function(options){
	if(!isSelect(options.batchBox,options.boxCon)){
		alert("请先从列表中打勾选择需要操作的记录!");
		return ;
	}
	if(isNull(options.width)){
		options.width = 500;
	}
	//if(isNull(options.height)){
	//	options.height = 300;
	//}
	showConfirm(
		options.url,
		'批量编辑',
		options.width,
		options.height,
		function(){
			if(!isSelect("batchNames",options.form)){
				alert("请勾选要修改的列！");
				return ;
			}
			var btachItems = getSelected(options.batchBox,options.boxCon);
			var box = "<input type='hidden' name='batchItems' checked='checked' value='"+btachItems+"' />";
			$(options.form).append(box);
			$(options.form).submit();
		}
	);
}

/**
 * 批量修改页面效果方法
 * options
 * form : form表单
 * batchBox : checkbox的name
 */
doRestBatchEditUI = function(options){
	$(options.form+" input[name='"+options.batchBox+"']").each(
		function(i,obj){
			$(obj).bind("click",
				function(){
					if($(obj).attr("checked")){
						$("[name='"+$(obj).val()+"']").attr("disabled","");
						$("#"+$(obj).val()).focus();
					}else{
						$("[name='"+$(obj).val()+"']").attr("disabled","disabled");
					}
				}
			);
		}
	);
}

/**
 * 批量删除，修改处理方法
 * options
 * url : 处理地址
 * batchBox : checkbox的name
 * boxCon : checkbox所在的dom选择器
 * form : 表单
 * confirmMsg : 提示信息，为空或不填则不会弹出提示
 * method : 执行方法
 * type : 提交方式，form/ajax
 */
 doRestEdit = function(options){
	var toRestEdit = function(){
		if(isNull(options.type) || options.type == "form"){
			doRestEditForm(options);
		}else if(options.type == "ajax"){
			doRestEditAjax(options);
		}
	}
	
	if(isNull(options.boxCon)){
		options.boxCon = options.form;
	}
	
	
	if (!isSelect(options.batchBox,options.boxCon)) {
		alert("请选择你要操作的数据!");
		return;
	}
	
	if(!isNull(options.confirmMsg)){
		confirm(options.confirmMsg,function() {
			toRestEdit();
		});
	}else{
		toRestEdit();
	}
}


doRestEditForm = function(options){
	$(options.form).attr("action",options.url);
	$(options.form).attr("method",'POST');
	
	/*添加操作类型*/
	if(!isNull(options.method)){
		var methodHtml = "<input type='hidden' name='_method' value='"+options.method+"' />";
		$(options.form).append(methodHtml);
	}
	
	var selOptions = getSelectedDom(options.batchBox,options.boxCon);
	var boxform = $($(selOptions)[0]).parents("form").attr("id");
	var subform = options.form;
	if(startWith(subform,"#")
		|| startWith(subform,".")){
		subform = subform.substr(1);
	}
	
	if(boxform != subform){
		$(selOptions).each(
			function(i,obj){
				var option = "<input style='display:none' type='"+$(obj).attr("type")+"' name='"+options.batchBox+"' value='"+$(obj).val()+"' checked='checked' />";
				$(options.form).append(option);
			}
		);
	}
	$(options.form).submit();
}

/**
 * 批量删除，修改处理方法
 * options
 * url : 处理地址
 * batchBox : checkbox的name
 * boxCon : checkbox所在的dom选择器
 * form : 表单
 * confirmMsg : 提示信息，为空或不填则不会弹出提示
 * method : 执行方法
 * type : 提交方式，form/ajax
 * onok : function 成功回调，为空则执行window.location.reload();
 */
doRestEditAjax = function(options){
	var params = "ran="+Math.random();
	if(!isNull(options.method)){
		params += "&_method="+options.method;
	}
	
	var selOptions = getSelectedDom(options.batchBox,options.boxCon);
	$(selOptions).each(
		function(i,obj){
			params += "&"+options.batchBox+"="+$(obj).val();
		}
	);
	$.ajax({
		url : options.url,
		type : "post",
		data : params,
		dataType : "json",
		success : function(jsondatas){
			if(!isNull(options.onok) && typeof(options.onok)=="function"){
				options.onok.call(this,jsondatas);
			}else{
				window.location.reload();
			}
		}
	});
}

/**
 * 弹出框，显示需要选择的id
 * dom : 出发事件的dom元素
 * options
 * url : 弹出地址
 * title : 标题
 * width : 宽默认450
 * height : 高默认350
 * selBox : 需要获取值的checkbox
 * selList : 要添加选择单选事件的列表
 * selId : 选中在id dom
 * selName : 外显在name dom
 * callFun : 选择完成后的回调函数
 */
showSelectPage = function(dom,options){
	if(isNull(options.width)){
		options.width = 350;
	}
	//if(isNull(options.height)){
	//	options.height = 150;
	//}
	
	var showdom = jQuery.weeboxs.open(options.url, 
		{
			title : options.title,
			contentType:'ajax',
			width : options.width,
			height : options.height,
			showOk : false,
			onopen : function(wb){
				$(options.selList).each(function(i,obj){
					$(obj).bind("click",function(){
						var id = $(obj).find("input[name='"+options.selBox+"']");
						var name = $(id).next("input");
						$(options.selId).val($(id).val());
						$(options.selName).val($(name).val());
						wb.close();
						if(!isNull(options.callFun)){
							evalFunction(options.callFun);
						}
					});
				});
			}
		}
	);
}


/**
 * 最大长度的验证，如果超出侧截取
 * 根据maxlength的值去截取
 * 例如：<input type="text" id="description" name="description" maxlength="100" onkeypress="ismaxlength(this)" onkeydown="ismaxlength(this)" onkeyup="ismaxlength(this)" />   最大长度不能超过100
 */
function ismaxlength(obj){
   var mlength = obj.getAttribute ? parseInt(obj.getAttribute("maxlength")) : "";
   if (obj.getAttribute && obj.value.length>mlength) {   
       obj.value = obj.value.substring(0,mlength)
   }
}


/**
 * 获得表单所含有的参数列表，
 * form ：提交的form
 * @return 形如：param1=value1&param2=value3的字符串
 */
 function paraseForm(frm){
	 var params = "";
 	$(frm+" :text[name]").each(
		function(i,obj){
			if(!$(obj).attr("disabled")){
				params += $(obj).attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	$(frm+" input[type='hidden'][name]").each(
		function(i,obj){
			if(!$(obj).attr("disabled")){
				params += $(obj).attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	$(frm+" textarea[name]").each(
		function(i,obj){
			if(!$(obj).attr("disabled")){
				params += $(obj).attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	$(frm+" :password[name]").each(
		function(i,obj){
			if(!$(obj).attr("disabled")){
				params += $(obj).attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	$(frm+" :radio[name]").each(
		function(i,obj){
			if($(obj).attr("checked") && !$(obj).attr("disabled")){
				params += $(obj).attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	$(frm+" :checkbox[name]").each(
		function(i,obj){
			if($(obj).attr("checked") && !$(obj).attr("disabled")){
				params += $(obj).attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	$(frm+" select[name] option").each(
		function(i,obj){
			if($(obj).attr("selected") && !$(obj).attr("disabled")){
				params += $(obj).parent("select").attr("name")+"="+ $(obj).val() + "&";
			}
		}
	);
	if(params != ""){
		params = params.substr(0,params.length - 1);
	}
	return params;
 }
 

/**
 * 公用上传处理
 * options
 * width:宽 默认500
 * height:高 默认450
 * callBack:每一个文件上传完以后执行的回调，参数必须为event, ID, fileObj, response, data
 * onclose : 窗口关闭调用事件
 * queueLimit:可上传文件数的最大值
 * sizeLimit:可上传文件大小的最大值
 * fileExt:可上传文件扩展列表，格式如*.jpg;*.gif;*.png
 * autoUpload:是否自动上传
 * uploadFolder:上传的保存目录
 * isSave:是否做数据库保存大于0为保存，小于等于0为不保存
 */
	function commonUpload(options){
  	var uploadPage = "/common/uploadFile/upload.jsp?ran="+Math.random();
	if(!isNull(options)){
	  	if(!isNull(options.callBack)){
	  		uploadPage += "&callBack="+options.callBack;
	  	}
	  	if(!isNull(options.queueLimit)){
	  		uploadPage += "&queueLimit="+options.queueLimit;
	  	}
	  	if(!isNull(options.sizeLimit)){
	  		uploadPage += "&sizeLimit="+options.sizeLimit;
	  	}
	  	if(!isNull(options.fileExt)){
	  		uploadPage += "&fileExt="+options.fileExt;
	  	}
	  	if(!isNull(options.autoUpload)){
	  		uploadPage += "&autoUpload="+options.autoUpload;
	  	}
	  	if(!isNull(options.uploadFolder)){
	  		uploadPage += "&uploadFolder="+options.uploadFolder;
	  	}
	  	if(!isNull(options.isSave)){
	  		uploadPage += "&isSave="+options.isSave;
	  	}
	  	// 设置默认宽
	  	if(isNull(options.width)){
	  		options.width = 500; 
	  	}
	  	// 设置默认高
	  	if(isNull(options.height)){
	  		options.height = 300; 
	  	}
	}else{
		options = {'width':500,'height':300};
	}
	
	var frameDivId = "#uploadFrameDiv"; 
	var frameDiv = $(frameDivId);
	if(isNull(frameDiv)
		|| isNull(frameDiv.attr("tagName"))){
		var html = "<div id='uploadFrameDiv'></div>";
		$(document.body).append(html);
	}
	
	$(frameDivId).hide();
	var framehtml = "<iframe id=\"uploadFrame\" name=\"uploadFrame\" frameborder=\"0\" src=\""
		+uploadPage
		+"\" width=\"100%\" height=\""
		+(options.height)
		+"\"></iframe>";
		
	$(frameDivId).html(framehtml);
	show(frameDivId,"文件上传",options.width,options.height);
}

/**
 * 公用上传处理
 * options
 * width:宽 默认500
 * height:高 默认450
 * url : 上传处理的url
 * callBack:每一个文件上传完以后执行的回调，参数必须为event, ID, fileObj, response, data
 * queueLimit:可上传文件数的最大值
 * sizeLimit:可上传文件大小的最大值
 * fileExt:可上传文件扩展列表，格式如*.jpg;*.gif;*.png
 * autoUpload:是否自动上传
 * uploadFolder:上传的保存目录
 * isSave:是否做数据库保存
 */
function showCommonUpload(options){
  	var uploadPage = ctx+"/pages/admin/tools/uploadFile/upload.jsp?ran="+Math.random();
	if(!isNull(options)){
	  	if(!isNull(options.callBack)){
	  		uploadPage += "&callBack="+options.callBack;
	  	}
	  	if(!isNull(options.queueLimit)){
	  		uploadPage += "&queueLimit="+options.queueLimit;
	  	}
	  	if(!isNull(options.sizeLimit)){
	  		uploadPage += "&sizeLimit="+options.sizeLimit;
	  	}
	  	if(!isNull(options.fileExt)){
	  		uploadPage += "&fileExt="+options.fileExt;
	  	}
	  	if(!isNull(options.autoUpload)){
	  		uploadPage += "&autoUpload="+options.autoUpload;
	  	}
	  	if(!isNull(options.uploadFolder)){
	  		uploadPage += "&uploadFolder="+options.uploadFolder;
	  	}
	  	if(!isNull(options.isSave)){
	  		uploadPage += "&isSave="+options.isSave;
	  	}
	  	if(!isNull(options.url)){
	  		uploadPage += "&url="+options.url;
	  	}
	  	// 设置默认宽
	  	if(isNull(options.width)){
	  		options.width = 500; 
	  	}
	  	// 设置默认高
	  	if(isNull(options.height)){
	  		options.height = 470; 
	  	}
	}else{
		options = {'width':500,'height':470};
	}
	
	var frameDivId = "uploadFrameDiv"; 
	var frameDiv = $("#"+frameDivId);
	
	if(isNull(frameDiv)
		|| isNull($(frameDiv).attr("tagName"))){
		$(document.body).append("<div id='"+frameDivId+"'></div>");
		frameDiv = $("#"+frameDivId);
	}
	
	$(frameDiv).hide();
	$(frameDiv).html("<iframe id=\"uploadFrame\" name=\"uploadFrame\" frameborder=\"0\" src=\""
		+uploadPage
		+"\" width=\"100%\" height=\""
		+(options.height-2)
		+"\"></iframe>");
		
  	show("#"+frameDivId,"上传文件",options.width,options.height);
}

/**
 * 获取内存iframe
 * param _frames：页面内iframe name的路径数组
 */
function getInnerFrame(_frames){
	var w = window;
	if(null!=_frames && _frames.length>0){
		for(var i=0;i<_frames.length;i++){
			var temp = w.frames[_frames[i]];
			if(typeof(temp)!='undefined' && temp!=null){
				w = temp;
			}else{
				w = null;
			}
		}
	}
	return w;
}
 

(function($) {
$.fn.extend({

	/** 
	 * 提示
	 * options
	 * msg : 信息
	 * type : alert / confirm / img
	 * top : 上偏移量
	 * left : 左偏移量
	 * width : 宽
	 * closeTime : 关闭提示延时
	 */
	tip : function(msg,options){
		var self = this;
		
		if(isNull(options)){
			options = {};
		}
		if(isNull(options.top)){
			options.top = 0;
		}
		if(isNull(options.left)){
			options.left = 0;
		}
		if(isNull(options.width)){
			options.width = 250;
		}
		if(isNull(options.closeTime)){
			options.closeTime = 3000;
		}
		if(isNull(options.type)){
			options.type = "alert";
		}
		this.createMsgTip = function(){
			var tipHtml = "";
			tipHtml += "<div tip=\""+options.type+"\" class=\"erorr-tips-layout\" style=\"display:none;\">";
			tipHtml	+= "	<div class=\"erorr-tips\">";
			tipHtml	+= "		<a href=\"javascript:;\" onclick=\"$(this).closeTip();\" class=\"tips-closed\"></a>";
			tipHtml	+= "		<div class=\"erorr-tips-trl\"></div>";
			tipHtml	+= "		<div class=\"erorr-tips-main\">";
			tipHtml	+= "			<div class=\"erorr-tips-l\"></div>";
			tipHtml	+= "			<div class=\"erorr-tips-c\"><h4></h4></div>";
			tipHtml	+= "			<div class=\"erorr-tips-r\"></div>";
			tipHtml	+= "		</div>";
			tipHtml	+= "	</div>";
			tipHtml	+= "</div>";
			return $(tipHtml);
		}
		
		this.createImgTip = function(){
			var tipHtml = "";
			tipHtml += "<div tip=\""+options.type+"\" class=\"tips-layout-userp\" style=\"display:;\">";
			tipHtml += "	<div class=\"tips-userphoto-bg\">";
			tipHtml += "		<div class=\"tips-userphoto\">";
			tipHtml += "			<a href=\"javascript:;\" onclick=\"$(this).closeTip();\" class=\"tips-closed\"></a>";
			tipHtml += "			<div class=\"tips-user-photo\"><a href=\""+msg+"\" target=\"block\" ><img src=\"\" /></a></div>";
			tipHtml += "		</div>";
			tipHtml += "	</div>";
			tipHtml += "</div>";
			return $(tipHtml);
		}
		
		this.setTipStyle = function(tip){
			var top = $(self).offset().top + options.top;
			var left = $(self).offset().left + options.left;
			
			if(options.width > 0 && (options.type == "alert"
				|| options.type == "confirm")){
				$(tip).css({
					top : top + $(self).height()+5,
					left : left,
					width : options.width,
					zIndex : 999999
				});
			}else{
				$(tip).css({
					top : top + $(self).height()+5,
					left : left,
					zIndex : 999999
				});
			}
		}
		
		this.getTip = function(){
			var result = $("[tip='"+options.type+"']");
			if(isNull(result) || isNull($(result).attr("tagName"))){
				if(options.type == "alert"
					|| options.type == "confirm"){
					result = this.createMsgTip();
				}else if(options.type == "img"){
					result = this.createImgTip();
				}
			}
			return result;
		}
		
		this.tipInit = function(){
			var tip = self.getTip();
			$(document.body).append(tip);
			
			self.setTipStyle(tip);
			if(options.type == "alert"){	
				$(tip).find("h4").html(msg);
			}else if(options.type == "confirm"){
				$(tip).find("h4").html(msg
					+ "&nbsp;&nbsp;<a id='tipConfirmOkBtn' style='color:#FFF;font-size:12px;' href='javascript:;'>确定</a>"
					+ "&nbsp;&nbsp;<a id='tipConfirmCancelBtn' style='color:#FFF;font-size:12px;' href='javascript:;'>取消</a>");
				
				if(!isNull(options.onok)){
					$(tip).find("#tipConfirmOkBtn").bind("click",function(){
						evalFunction(options.onok);
						//$.closeTips();
					});
				} else {
					$(tip).find("#tipConfirmOkBtn").bind("click",function(){
						//$.closeTips();
					});
				}
				if(!isNull(options.oncancel)){
					$(tip).find("#tipConfirmCancelBtn").bind("click",function(){
						evalFunction(options.oncancel);
						//$.closeTips();
					});
				} else {
					$(tip).find("#tipConfirmOkBtn").bind("click",function(){
						$.closeTips();
					});
				}
			}else if(options.type == "img"){
				$(tip).find("img").attr("src",msg);
			}
			$(tip).fadeIn(100);
			if(options.closeTime > 100){
				window.setTimeout(function(){
					$(tip).fadeOut(300);
				},options.closeTime);
			}
		}
		self.tipInit();
	},
	closeTip : function(){
		goNext = true;
		$(this).parents("[tip]").hide();
		try{$("#imgPath").val("");}catch(e){}
	},
	/**
	 * event : 事件 
	 * options :
	 * handler : 触发对象执行的函数指针
	 * beforeClose : 关闭前的操作 
	 * afterClose : 关闭后的操作
	 */
	closeHandler : function(event,options){
		var xs = $(this).offset().left;
		var ys = $(this).offset().top;
		var xe = $(this).offset().left + $(this).width();
		var ye = $(this).offset().top + $(this).height();
		var ex = event.clientX;
		var ey = event.clientY;
		//$("#message").append("("+xs +","+ ys +")" + "<br>" + "("+ex +","+ ey +")" 
		//	+ "<br>" + "("+xe +","+ ye +")<br><hr>");
		if(isNull(options)){
			options = {};
		}
		if(ex > 0 && ey > 0 && !(ex > xs && ex < xe 
			&& ey > ys && ey < ye)){
			if(!isNull(options.beforeClose)){
				evalFunction(options.beforeClose);
			}
			$(this).slideUp("fast",function(){
				if(isNull(options.handler)){
					$(document).unbind("click");
				}else{
					$(document).unbind("click",options.handler);
				}
				if(!isNull(options.afterClose)){
					evalFunction(afterClose);
				}
			});
		}
	}
	,
	/**
	 * dom : 显示的dom元素
	 * options 
	 * clickHandler : true/false 关闭代理，点击页面任意位置关闭
	 * 
	 * dir : 弹出的方向，left/right 
	 * left : 左侧偏移量
	 * top ： 右侧偏移量
	 * speed : 下拉的速度
	 */
	showFlow : function(dom,options){
		if(isNull(options)){
			options = {};
		}
		
		var dir = "left";
		var s_left = $(this).offset().left;
		if(!isNull(options) 
			&& !isNull(options.dir)
			&& options.dir == "right"){
			dir = "right";
			s_left = s_left - $(dom).width() + $(this).width();
		}
		var s_top = $(this).offset().top + $(this).height();
		if(!isNull(options)){
			if(!isNull(options.left)){
				s_left += options.left;
			}
			if(!isNull(options.top)){
				s_top += options.top;
			}
		}
		$(dom).offset({ top: s_top, left: s_left });
		$(dom).css({
			"position":"absolute",
			"left":s_left,
			"top":s_top,
			"zIndex":998
		});
		if(isNull(options.clickHandler)){
			options.clickHandler = true;
		}
		var speed = 'normal';
		if(!isNull(options) && !isNull(options.speed)){
			speed = options.speed;
		}
		
		$(dom).slideDown(speed,function(){
			if(options.clickHandler){
				var clickHandler = function(event){
					$(dom).closeHandler(event,{handler : clickHandler});
				};
				$(document).bind("click",clickHandler);
			}
		});
	},
	/**
	 * 保存页面列选配置
	 * 配置方法$.getSaveConfig 返回map 数据格式如下：
	 * url : 提交处理的url
	 * data : 另外需要提交的数据
	 * form : 附加需要提交的form数据
	 * method : 发送方式get/post
	 * 
	 * 标准示例
	 *	(function($) {
	 *		$.extend({
	 *			getSaveConfig : function(){
	 *				return {method:"post"};
	 *			}
	 *		});
	 * 	})(jQuery);
	 */
	savePageConfig : function(selector){
		var self = this;
		var options = {};
		// 获取url
		this.getUrl = function(){
			// url处理
			var url = window.location.href;
			if(url.indexOf("?") != -1){
				url = url.substr(0,url.indexOf("?"));
			}
			url += "?ran="+Math.random();
			return url;
		}
		
		// 获取列选择数据
		this.getColumnData = function(){
			var tb = $(this).parents("tbody");
			var ths = $(".flexigrid .hDivBox th");
			var paramdata = "";
			
			$(tb).find(selector+" input:checked").each(function(i,obj){
				var index = $(obj).val();
				paramdata += "&showColumn="+$(ths[parseInt(index)]).attr("showColumn");
			});
			return paramdata;
		}
		
		// get处理
		this.getMethod = function(){
			var url = options.url;
			// url数据拼接
			if(!isNull(options.data)){
				url += "&"+options.data;
			}
			if(!isNull(options.form)){
				url += "&"+$(options.form).serialize();
			}
			url += self.getColumnData();
			// 跳转页面
			window.location.href = url;
		}
		// post处理
		this.postMethod = function(){
			var url = options.url;
			var frm = "<form id='op_show_column_form' name='op_show_column_form' method='get' action='"+url+"'>";
			
			var data = "";
			if(!isNull(options.data)){
				data += options.data;
			}
			if(!isNull(options.form)){
				data += $(options.form).serialize();
			}
			data += self.getColumnData();
			
			var dataArray = data.split("&");
			for(var i=0;i<dataArray.length;i++){
				if(!isNull(dataArray[i]) && dataArray[i].indexOf("=") != -1){
					var key = dataArray[i].substr(0,dataArray[i].indexOf("="));
					var value = dataArray[i].substr(dataArray[i].indexOf("=")+1);
					frm += "<input type='hidden' name='"+key+"' value='"+value+"' />";
				}
			}
			frm += "</form>";
			$(document.body).append(frm);
			$("#op_show_column_form").submit();
		}
		// 初始化
		this.init = function(){
			if(!isNull($.getSaveConfig)){
				options = $.getSaveConfig();
			}else{
				options = {
					url : self.getUrl(),
					method : "get"
				};
			}
			if(isNull(options.url)){
				options.url = self.getUrl();
			}
			if(isNull(options.method)){
				options.method = "get";
			}
			if(options.method == "get"){
				self.getMethod();
			}else{
				self.postMethod();
			}
		}
		this.init();
	},
	/***
	 * 行点击添加事件
	 * @param options
	 *  url 地址或id或样式
	 *  except : 不包含的列，showcolumn的
	 *  pop : true/false 是否弹出
	 *  idname : 当前选择行传递的id name值
	 *  popw : 弹出框的宽
	 *  poph : 弹出框的高
	 *  poptitle : 弹出框的标题
	 */
	rowAction : function(options){
		var self = this;
		this.isBind = function(column){
			var result = false;
			if(!isNull(options.except) && !isNull(options.except).length > 0){
				for(var i=0;i<options.except.length;i++){
					if(options.except[i] == column){
						result = true;
						break;
					}
				}
			}else{
				result = true;
			}
			return result;
		}
		
		this.bindClickAction = function(td,id){
			$(td).bind("click",function(){
				var s_url = options.url;
				if(s_url.indexOf("?") == -1){
					s_url += "?"+options.idname+"="+id;
				}else{
					s_url += "&"+options.idname+"="+id;
				}
				if(options.pop){
					show(s_url,options.poptitle,options.popw,options.poph);
				}else{
					window.location.href = s_url;
				}
			});
		}
		
		this.bindAction = function(tr){
			if(isNull($(tr).children("td")) || $(tr).children("td").length == 0){
				return ;
			}
			var id = $(tr).children("[showColumn='checkbox']").children("input[type='checkbox']").val();
			$(tr).find("td").each(function(i,td){
				if(!self.isBind($(td).attr("showColumn"))){
					self.bindClickAction(td,id);
				}
			});
		}
		if(isNull(options)){
			options = {pop:false};
		}
		if(isNull(options.pop)){
			options.pop = false;
		}
		if(isNull(options.idname)){
			options.idname = "id";
		}
		if(options.pop && isNull(options.popw)){			
			options.popw = 500;
		}
		//if(options.pop && isNull(options.poph)){			
		//	options.poph = 300;
		//}
		if(isNull(options.poptitle)){
			options.poptitle = "";
		}
		$(this).find("tr").each(function(i,obj){
			self.bindAction(obj);
		});
	}
});
})(jQuery);



(function($) {
$.extend({
	closeTips : function(){
		$("[tip]").remove();
		goNext = true;
	},
 	/**
 	 * 列显示处理js
 	 */
	showcolumn : function(columnjson){
		if(!isNull(columnjson)){
			var allColumn = $("[showColumn]");
			for(var i=0;i<allColumn.length;i++){
				var isCanshow = false;
				for(var j=0;j<columnjson.length;j++){
					if($(allColumn[i]).attr("showColumn") == columnjson[j]){
						isCanshow = true;
						break;
					}
				}
				if(isCanshow){
					$(allColumn[i]).show();
				}else{
					$(allColumn[i]).hide();
				}
			}
		}
	},
	
	/***
	 * 列排序处理
	 * options
	 * url : 发送地址
	 * method : 传值方式 get/post
	 * form : 附带的form信息
	 * data : 附带的传送数据
	 * columns : 操作列
	 * sortColumns : 当前排序列 
	 */
	sortcolumn : function(options){
		var self = this;
		// 获取url
		this.getUrl = function(){
			// url处理
			var url = window.location.href;
			if(url.indexOf("?") != -1){
				url = url.substr(0,url.indexOf("?"));
			}
			url += "?ran="+Math.random();
			return url;
		}
		
		// get处理
		this.getMethod = function(scolumn,sortType){
			var url = options.url;
			
			// url数据拼接
			if(!isNull(options.data)){
				url += "&"+options.data;
			}
			if(!isNull(options.form)){
				var params = $(options.form).serialize();
				if(!isNull(params)){
					url += "&"+params;
				}
			}
			if(sortType != ""){
				url += "&sortColumns="+scolumn+"+"+sortType;
			}
			// 跳转页面
			window.location.href = url;
		}
		// post处理
		this.postMethod = function(scolumn,sortType){
			var url = options.url;
			var frm = "<form id='op_sort_column_form' name='op_sort_column_form' method='get' action='"+url+"'>";
			
			var data = "";
			if(!isNull(options.data)){
				data += options.data;
			}
			if(!isNull(options.form)){
				data += $(options.form).serialize();
			}
			if(sortType != ""){
				url += "&sortColumns="+scolumn+"+"+sortType;
			}
			
			var dataArray = data.split("&");
			for(var i=0;i<dataArray.length;i++){
				if(!isNull(dataArray[i]) && dataArray[i].indexOf("=") != -1){
					var key = dataArray[i].substr(0,dataArray[i].indexOf("="));
					var value = dataArray[i].substr(dataArray[i].indexOf("=")+1);
					frm += "<input type='hidden' name='"+key+"' value='"+value+"' />";
				}
			}
			frm += "</form>";
			$(document.body).append(frm);
			$("#op_sort_column_form").submit();
		}
		
		this.bindClickAction = function(sclumn,sortType){
			$(sclumn).bind("click",function(){
				if(options.method == "get"){
					self.getMethod($(sclumn).attr("sortColumn"),sortType);
				}else{
					self.postMethod($(sclumn).attr("sortColumn"),sortType);
				}
			});
		}
		// 初始化
		this.init = function(){
			if(isNull(options)){
				options = {
					url : getUrl(),
					method : "get"
				};
			}
			if(isNull(options.url)){
				options.url = self.getUrl();
			}
			if(isNull(options.method)){
				options.method = "get";
			}
			
			var sortColumnName = "";
			var sortType = "";
			var styleSort = "";
			if(!isNull(options.sortColumns)){
				sortColumnName = options.sortColumns.substr(0,options.sortColumns.indexOf(" "));
				sortType = options.sortColumns.substr(options.sortColumns.indexOf(" ")+1);
				styleSort = sortType;
				if(sortType == "desc"){
					sortType = "asc";
				}else if(sortType == "asc"){
					sortType = "";
				}else{
					sortType = "desc";
				}
			}
			
			$(options.columns).each(function(i,obj){
				var sclumn = $(obj).attr("sortcolumn");
				if(!isNull(sortColumnName) && (sclumn == sortColumnName)){
					$(obj).addClass("sorted");
					$(obj).html("<span class='s"+styleSort+"'>"+ $(obj).html() +"</span>");
					$("td[showColumn='"+sclumn+"']").addClass("sorted");
					self.bindClickAction(obj,sortType);
				}else{
					self.bindClickAction(obj,"desc");
				}
			});
		}
		this.init();
	},
	forward : function (url,permissionId){
		if(startWith(url,"http://")){
			window.open(url);
		}else if(startWith(url,"/")){
			if(url.indexOf("?")!=-1){
				url += "&AUTH_PERM_ID="+permissionId;
			}else{
				url += "?AUTH_PERM_ID="+permissionId;
			}
			window.location.href=url;
		}else{
			alert("对不起，地址:'"+url+"'无法解析，请检查权限配置是否正确。");
		}
	}
});
	
})(jQuery);

// 初始化绑定事件
$(document).ready(function(){
	// 隐藏左侧处理
	if($("#hiddenLeftBtn").length>0){
		$("#hiddenLeftBtn").bind("click",function(){
			var status = $("#hiddenLeftBtn").parents("div").find("#permMenu").css("display");
			var w = 180;
			var falgStyle = "&laquo;";
			if(status == 'block'){
				status = "none";
				w = 35;
				falgStyle = "&raquo;";
			}else{
				status = "block";
			}
			$("#hiddenLeftBtn").parents("div").find("#permMenu").css({
				display : status
			});
			$("#hiddenLeftBtn").parents(".up72_mleft").css({
				width : w
			});
			$("#hiddenLeftBtn").html(falgStyle);
		});
	}
});