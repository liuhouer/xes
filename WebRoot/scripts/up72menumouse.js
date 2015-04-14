// JavaScript Document
/**
 * up72menumouse.js
 *
 * @category   javascript
 * @package    jquery
 * @author     bh.iune <twq0824@yeah.net>
 * @version    1.0 
 */ 
 
(function($) {

/**
 * 菜单，按钮选中，悬停状态UI效果
 *
 * options
 * index : 初始选中状态不指定没有，支持url方式和下标方式
 * selCss : 选中时的css，如果以#开头则认为是背景颜色，不传认为没有背景
 * overCss : 悬停css，如果以#开头则认为是背景颜色，不传认为没有背景
 * openAll : 是否展开全部菜单 true 是，false 否
 * ==============需要ajax刷新或者显示层时添加以下参数============================================
 * showdom : 显示层dom
 * showCss : 显示的css
 */
$.fn.extend({
	menuMouse : function(options) {
	  	this.options = options || {};
		var self = this;
		self.array = $(self);
		this.init = function(){
		
			if(self.isNull(self.options.openAll)){
				self.options.openAll = true;
			}
			if(!self.isNull(self.options.index)
				&& !self.isNull(self.options.selCss)){
				self.selectIndex(self.options.index);
			}
			if(self.isNull(self.options.overCss)){
				self.options.overCss = "#DDD";
			}
			/*
			$(self.array).each(
				function(i,obj){
					if(!self.isNull(self.options.selCss)){
						self.bindClickAction(i);
					}
					self.bindMouseAction(i);
				}
			);*/
		}
		
		this.selectIndex = function(index){
			//如果是数字类型，则按下标检索
			if(typeof(index) == "number"){
				var dom = $(self.array[index]);
				$(dom).attr("className",self.options.selCss);
			}
			//如果是string类型，则按url检索
			else{
				var selMenu = null;
				$(self.array).each(
					function(i,obj){
						var url = null;
						if($(obj).attr("tagName").toLowerCase() == "a"){
							url = $(obj).attr("href");
						} else {
							if(self.isNull($(obj).attr("rel"))){
								url = $($(obj).children("a")).attr("href");
							}else{
								url = $(obj).attr("rel");
							}
						}
						if(self.endWith(url,index)){
							$(obj).attr("className",self.options.selCss);
							selMenu = obj;
						}else if(!self.options.openAll){
							try{self.closeMenu(obj)}catch(e){}
						}
					}
				);
				
				if(!self.isNull(selMenu) && !self.options.openAll){
					try{self.openMenu(selMenu)}catch(e){}
				}
			}
		}
		
		this.openMenu = function(liObj){
			var ulObj = $(liObj).parent("ul");
			if(!self.isNull(ulObj)
					   && !isNull($(ulObj).attr("tagName"))){
				$(ulObj).show();
			}
			var menuDiv = $(ulObj).prev("div");
			var menuA = $(menuDiv).children(".fn_bg");
			if(!self.isNull(menuA)){
				$(menuA).removeClass("opned");
				$(menuA).removeClass("clded");
				$(menuA).addClass("opned");
			}
		}
		
		this.closeMenu = function(liObj){
			var ulObj = $(liObj).parent("ul");
			if(!self.isNull(ulObj)
					   && !self.isNull($(ulObj).attr("tagName"))){
				$(ulObj).hide();
			}
			var menuDiv = $(ulObj).prev("div");
			var menuA = $(menuDiv).children(".fn_bg");
			if(!self.isNull(menuA)){
				$(menuA).removeClass("opned");
				$(menuA).removeClass("clded");
				$(menuA).addClass("clded");
			}
		}
		
		this.bindClickAction = function(index){
			var dom = $(self.array[index]);
			$(dom).bind("click",
				function(){
					self.resetOthers(index);
					$(dom).attr("className",self.options.selCss);	
					var rel = self.getLink(dom);
					if(!self.isNull(rel)){
						self.showDiv (dom,rel);
					}
				}
			);
		}
		
		this.showDiv = function(dom,rel){
			//showdom
			//showCss
			var showdom = self.getShowDom(dom);
			if(!self.isNull(self.options.showCss)){
				showdom.attr("className",self.options.showCss);
			}
			self.show(showdom);
		}
		
		this.show = function(showdom,rel){
			if(self.startWith(rel,"#")){
				$(showdom).show();
			}else{
				$.ajax({
					tyep : "POST",
					url : rel,
					success : function(data){
						$(showdom).html(data);
					}
				});
			}
		}
		
		this.getShowDom = function(dom){
			if(!self.isNull(self.options.showdom)){
				return self.options.showdom;
			}else{
				var div = "<div id='menu_create_show_div'></div>";
				$(document.body).append(div);
				return "#menu_create_show_div";
			}
		}
		
		this.getLink = function(dom){
			var type = $(dom).attr("tagName").toLowerCase();
			var rel = null;
			if(type == 'a'){
				rel = $(dom).attr("href");
			}else{
				rel = $(dom).attr("rel");
			}
			if($.trim(rel) == "#"){
				rel = null;
			}
			return rel;
		}
		
		this.resetOthers = function(index){
			$(self.array).each(
				function(i,obj){
					if(i==index){
						$(obj).attr("className",self.options.selCss);	
					}else{
						$(obj).attr("className","");	
					}
				}
			);
		}
		
		this.bindMouseAction = function(index){
			var dom = $(self.array[index]);
			$(dom).bind("mouseover",
				function(){
					if($(dom).attr("className") == self.options.selCss){
						return ;
					}
					if(!self.startWith(self.options.overCss,"#")){
						$(dom).addClass(self.options.overCss);
					}else{
						$(dom).css({background:self.options.overCss});
					}
				}
			).bind("mouseout",
				function(){
					if($(dom).attr("className") == self.options.selCss){
						return ;
					}
					if(!self.startWith(self.options.overCss,"#")){
						$(dom).removeClass(self.options.overCss);
					}else{
						$(dom).css({background:""});
					}
				}
			);
		}
		
		this.isNull = function(obj){
			var result = true;
			var type = typeof(obj);
			if(type == "undefined"
				|| obj == null){
				result = true;
			} 
			else if (type == "string"){
				obj = $.trim(obj);
				if( obj==""
					|| obj == "undefined"){
					result = true;
				}else {
					result = false;
				}
			} 
			else{
				result = false;
			}
			return result;
		}
		
		this.startWith = function (source,str) {
			if(this.isNull(str) || this.isNull(source)){
				return false;
			}
			if(source.substr(0,str.length)==str){
				return true;
			} else {
				return false;
			}
		}
		
		this.endWith = function(source,str) {
			if(self.isNull(str) || self.isNull(source)){
				return false;
			}
			if(source.substr(source.length-str.length)==str){
				return true;
			} else {
				return false;
			}
		}
		
		self.init();
	}
});
})(jQuery);