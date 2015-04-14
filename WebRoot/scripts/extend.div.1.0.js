// JavaScript Document
/**
 * extend-div.js
 * @category   javascript
 * @package    jquery
 * @author     bh.iune <twq0824@yeah.net>
 * @version    1.1 
 */ 
 
(function($) {

/**
 * 列表的点击支持ajax的展开处理UI插件。
 *
 * options
 * position : 定位方式(relative默认，absolute浮动，static弹出)
 * showStyle : 弹出层的样式
 * reloadable : true/false 是否每次都加载最新
 * selCss : 选中时的样式
 * openCss : 展开样式
 * time : ui效果的时间
 * effect : false ui显示效果，是直接闪出下拉出，还是带有缓冲下拉效果的。
 * startIndex : 0 开始下标，从指定下标开始添加效果，之前放过。
 * isPop : 默认true，是否弹出，该参数为true，需要引入main.js，使用main.js的show方法
 * width : 500  默认值
 * height : 400  默认值
 * showFun : 显示的函数，参数顺序，url，title，width，height，只能传函数名
 * onopen : 页面加载以后的回调函数
 */
 
 /*
 弹出的参数openCss:标识选中状态，width:标识宽度，height: 标识高度startIndex:标识事件添加dom
 */
$.fn.extend({
	extendDiv : function(content, options) {
	  	this.options = options || {};
		var _self = this;
		_self.links = new Array();
		_self.clickArray = new Array();
		_self.array = $(_self);
		
		//初始化操作
		this.init = function(){
			//初始化参数
			this.initParam();
			
			this.setContainer();
			$(_self.array).each(
				function(i,obj){
					var rel = _self.getLink(obj);
					if(_self.isNull(rel)){
						rel = i;
						_self.links.push(rel);
					}else{
						_self.links.push(rel);
						$(obj).css("cursor","pointer");
						if(_self.options.isPop){
							_self.popDiv(i);
						}else{
							_self.bindClickAction(i);
						}
						
						if(!_self.isNull(_self.options.selCss)){
							_self.bindMouseAction(i);
						}
					}
				}
			);
		}
		
		this.setContainer = function(){
			if(!_self.isNull(content)){
				_self.container = $(content);
			}
		}
		
		//获得链接地址
		this.getLink = function(dom){
			var tagName = $(dom).attr("tagName").toLowerCase();
			var link = null;
			if(tagName == "a"){
				link = $(dom).attr("href");
			}else{
				link = $(dom).attr("rel");
			}
			return link;
		}
		
		this.popDiv = function(index){
			var dom = _self.array[index];
			var rel = _self.links[index];
			var title = $(dom).attr("title");
			if(_self.isNull(title)){
				title = "";
			}
			$(dom).find("div").each(
				function(i,obj){
					if(i >= _self.options.startIndex){
						$(obj).bind(
							"click",
							function(){
								if(!_self.isNull(_self.options.openCss)){
									$(_self.array).each(
										function(j,_obj){
											$(_obj).removeClass(_self.options.openCss);
										}
									);
									$(dom).addClass(_self.options.openCss);
								}
								if(_self.isNull(_self.options.showFun)){
									show(rel,title,_self.options.width, _self.options.height,_self.options.onopen);
								}else{
									eval(_self.options.showFun+"('"+rel+"','"+title+"',"+_self.options.width+","+_self.options.height+","+_self.options.onopen+")");
								}
								//showConfirm(rel,title,_self.options.width, _self.options.height);
							}
						);
					}
				}
			);
		}
		
		//添加click事件
		this.bindClickAction = function(index){
			var dom = _self.array[index];
			var rel = _self.links[index];
			
			if(_self.startWith(rel,"#")){
				$(_self.array[index]).find("div").each(
					function(i,obj){
						
					}
				);
				$(_self.array[index]).find("div").each(
						function(i,obj){
							if(i >= _self.options.startIndex){
								$(obj).bind(
									"click",
									function(){
										_self.hideAll(index);
										_self.slideToggle(rel);
									}
								);
							}
						}
				);
			}else{
				$(_self.array[index]).find("div").each(
						function(i,obj){
							if(i >= _self.options.startIndex){
								$(obj).bind(
									"click",
									function(){
										_self.showDiv(index);
									}
								);
							}
						}
				);
			}
			
		}
		
		//绑定鼠标事件，添加选中样式
		this.bindMouseAction = function(index){
			var dom = _self.array[index];
			/*$(dom).hover(
				function(){
					$(dom).addClass(_self.options.selCss);
				},
				function(){
					$(dom).removeClass(_self.options.selCss);
				}
			);*/
			$(dom).bind("mouseover",
				function(){
					$(dom).addClass(_self.options.selCss);
				}
			);
			$(dom).bind("mouseout",
				function(){
					$(dom).removeClass(_self.options.selCss);
				}
			);
		}
		
		//显示需要加载的页面
		this.showDiv = function(index){
			var dom = _self.array[index];
			var showdom = "#extend_div_up72_"+index;
			_self.hideAll(index);
			if(!_self.isNull(_self.options.openCss)){
				$(dom).addClass(_self.options.openCss);
			}
			if(_self.isNull($(showdom)) || 
						  _self.isNull($(showdom).attr("tagName"))){
				showdom = _self.createDiv(index);
				_self.loadPage(index,showdom);
				return ;
			}
			
			if(_self.options.reloadable){
				_self.loadPage(index,showdom);
			}else{
				_self.slideToggle(showdom);
			}
		}
		
		//为现实的层添加样式，并显示
		this.addStyle = function(dom,nextDom){
			if(_self.isNull(_self.options.showStyle)){
				_self.options.showStyle = $(dom).attr("className");
			}
			if( _self.options.position == "relative" ){
				$(nextDom).attr("className",_self.options.showStyle);
				$(nextDom).removeClass(_self.options.selCss);
			}else if( _self.options.position == "absolute" ){
				$(nextDom).attr("className",_self.options.showStyle);
				$(nextDom).removeClass(_self.options.selCss);
				$(nextDom).css({position:"absolute"});
			}else if( _self.options.position == "static" ){
				
			}
		}
		
		//获得标签的字符串格式
		this.createDiv = function(index){
			var dom = _self.array[index];
			var tagName = $(dom).attr("tagName").toLowerCase();
			var tagString = null;
			var id = "extend_div_up72_"+index;
			if(tagName == "tr"){
				tagString = "<tr id='"+id+"' style='display:none'><td colspan='"+$(dom).children("td").length+"' ></td></tr>";
			}else{
				tagString = "<"+tagName+" id='"+id+"' style='display:none'></"+tagName+">";
			}
			$(dom).after(tagString);
			id = "#"+id;
			_self.addStyle(dom,id);
			return id;
		}
		
		this.getParent = function(index){
			var parent = null;
			if(!_self.isNull(_self.container)){
				parent = _self.container[index];
			}else{
				parent = $(_self.array[index]).parent();
			}
		}
		
		//加载页面
		this.loadPage = function(index,showdom){
			var url = _self.links[index];
			var ran = Math.random()+"";
			ran = ran.substr(ran.indexOf(".")+1);
			url = (url.indexOf("?")==-1)?(url+"?ran="+ran):(url+"&ran="+ran);
			/*
			$(showdom).load(url,
				function(data){
					_self.slideDownShow(showdom);
				}
			);*/
			$.ajax({
				type : "POST",
				url : url,
				success : function(data){
					$(showdom).html(data);
					_self.slideDownShow(showdom);
				}
			});
		}
		
		//隐藏除下标index以外的其它需要显示的层
		this.hideAll = function(index){
			$(_self.array).each(
				function(i,obj){
					var rel = _self.links[i];
					if(!_self.isNull(rel) && (i != index)){
						if(!_self.isNull(_self.options.openCss)){
							$(obj).removeClass(_self.options.openCss);
						}	
						if(_self.startWith(rel,"#")){
							$(rel).hide();
						}else{
							$("#extend_div_up72_"+i).hide();
						}
					}
				}
			);
		}
		
		//获得需要现实的层或链接
		this.getHref = function(dom){
			var href = null;
			if($(dom).attr("tagName").toLowerCase() == "a"){
				href = $(dom).attr("href");
			}else{
				href = $(dom).attr("rel");
			}
			return href;
		}
		
		//显示层，改变显示的ui修改该方法
		this.slideDownShow = function(showdom){
			if(_self.options.effect){
				$(showdom).slideDown(_self.options.time);
			}else{
				$(showdom).show();
			}
		}
		
		//更换层的显示状态，修改该ui
		this.slideToggle = function(showdom){
			if(_self.options.effect){
				$(showdom).slideToggle(_self.options.time);
			}else{
				$(showdom).toggle();
			}
		}
		
		this.initParam = function(){
			if(_self.isNull(_self.options.reloadable)){
				_self.options.reloadable = false;
			}
			if(_self.isNull(_self.options.position)){
				_self.options.position = "relative";
			}
			if(_self.isNull(_self.options.effect)){
				_self.options.effect = false;
			}
			if(_self.isNull(_self.options.startIndex)){
				_self.options.startIndex = 3;
			}
			if(_self.isNull(_self.options.time)){
				_self.options.time = 300;
			}
			if(_self.isNull(_self.options.isPop)){
				_self.options.isPop = true;
			}
			if(_self.isNull(_self.options.width)){
				_self.options.width = 500;
			}
			if(_self.isNull(_self.options.height)){
				_self.options.height = 400;
			}
		}
		
		/**
		 * 判断字符串是否以某一个字符串开头
		 * @param source : 原串
		 * @param str : 校验串
		 */
	  this.startWith= function(source,str) {
		 if(_self.isNull(str) || _self.isNull(source)){
		   return false;
		 }
		 if(source.substr(0,str.length)==str){
			 return true;
		 } else {
			 return false;
		 }
	  }

		/**
		 * 判断对象是否为空
		 * @param obj : 校验对象
		 */
		this.isNull = function(obj){
				var result = true;
				var type = typeof(obj);
				//undefined or null return false
				if(type == "undefined"
					|| obj == null){
					result = true;
				} 
				//type is string 
				else if (type == "string"){
					obj = $.trim(obj);
					if( obj==""
						|| obj == "undefined"){
						result = true;
					}else {
						result = false;
					}
				} 
				//other object 
				else{
					result = false;
				}
				return result;
			}
		this.init();
	}
});
})(jQuery);