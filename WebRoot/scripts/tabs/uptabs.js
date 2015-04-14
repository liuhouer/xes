// JavaScript Document
/**
 * 选项卡切换效果，支持ajax
 * 
 * author : bh.iune
 * create : 公安知识服务平台 www.up72.com
 * version : 1.0
 */
		 
(function($) {
		
/** 
 * container : 选择的数组，默认会以选择器的根类下创建ajax调用现实的层
 * options
 * selCss : 选项卡选中样式
 * index : 初始选中
 * showCss : 选项卡显示的层样式
 * overCss : 鼠标悬停样式
 * reloadable : 如果是ajax调用是否每次重新加载，默认为false
 */
$.fn.extend({
  	up72Tabs : function(container,options) {
		this.links = new Array();
		this.options = options || {};
		var self = this;
		self.array = $(this);
		
		//alert($(this).length);
			
		//初始化操作
		this.init = function(){
			if(self.isNull(self.options.reloadable)){
				self.options.reloadable = false;
			}
			self.setSuperContainer();
			$(self.array).each(
				function(i,obj){
					self.links.push(self.getLink(obj));
					self.bindClickAction(i);
					if(!self.isNull(self.options.overCss)){
						self.bindMouseAction(i);
					}
				}
			);
			self.selectDefault();
		}
		
		//获得选项卡所在容器dom
		this.setSuperContainer = function(){
			self.superContent = container;
		}
		
		//选中默认的选项卡，并显示
		this.selectDefault = function(){
			if(self.isNull(self.options.index)){
				self.options.index = 0;
			} else if(self.options.index < 0){
				self.options.index = 0;
			}else if(self.options.index > (self.array.length-1)){
				self.options.index = self.array.length-1;
			}
			self.tab(self.options.index);
			var link = self.links[self.options.index];
			if(self.startWith(link,"#")){
				self.showDiv(self.links[self.options.index]);
			}else{
				self.loadPage(self.options.index);	
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
		
		//绑定鼠标悬停和移出事件
		this.bindMouseAction = function(index){
			var dom = $(self.array[index]);
			$(dom).bind(
				"mouseover",
				function(){
					$(dom).addClass(self.options.overCss);
				}
			);
			$(dom).bind(
				"mouseout",
				function(){
					$(dom).removeClass(self.options.overCss);
				}
			);
			
		}
		 
		this.bindClickAction = function(index){
			 var link = self.links[index];
			 var dom = self.array[index];
			 if(self.startWith(link,"#")){
				 if(!self.isNull(self.options.showCss)){
					$(self.links[index]).attr("className",self.options.showCss);
				 }
				 $(dom).bind("click",
					function(){
					  self.tab(index);
					  self.showDiv(self.links[index]);
					}
				 );
			 }else{
				  $(dom).bind("click",
					function(){
					  	self.loadPage(index);
					}
				 );
			 }
		}
		
		this.loadPage = function(index){
			var href = self.links[index];
			self.tab(index);
			var showdom = null;
			if(self.isNull(self.getShowDiv(index).attr("tagName"))){
				self.createDiv(index);
				showdom = self.getShowDiv(index);
				self.ajaxLoad(showdom,href);
				self.showDiv(showdom);
				return ;
			}
			showdom = self.getShowDiv(index);
			if(self.options.reloadable){
				self.ajaxLoad(showdom,href);
			}else {
				showdom = self.getShowDiv(index);
			}
			self.showDiv(showdom);
		}
		
		this.ajaxLoad = function(showdom,href){
			href = (href.indexOf("?")!=-1) ? (href+"&") : (href+"?");
			href = href + "ran="+Math.random();
			$(showdom).load(
			 	href,
				function(data){
					self.showDiv(showdom);
				}
			);
		}
		
		this.getShowDiv = function(index){
			return $(self.superContent).find("#uptabs_"+index);
		}
		
		this.createDiv = function(index){
			var id = "uptabs_"+index;
			var divString = "<div id='"+id+"' style='display:none'></div>";
			id = "#"+id;
			$(self.superContent).append(divString);
			if(!self.isNull(self.options.showCss)){
				self.getShowDiv(index).addClass(self.options.showCss);
			}
			return id;
		}
		
		this.tab = function(index){
			$(self.array).each(
				function(i,obj){
					if(i == index){
						$(obj).addClass(self.options.selCss);
					}else{
						$(obj).removeClass(self.options.selCss);
					}
				}
			);
		}
		
		this.hideAll = function(){
			$(self.array).each(
				function(i,obj){
					var link = self.links[i];
					if(self.startWith(link,"#")){
						$(link).hide();
					}else{
						$(self.getShowDiv(i)).hide();
					}
				}
			);
		}
		
		this.showDiv = function(showdom){
			self.hideAll();
			$(showdom).show();
		}
		
		
		this.startWith = function(source,str) {
			if(self.isNull(str)){
			  return false;
			}
			if(source.substr(0,str.length)==str){
				return true;
			} else {
				return false;
			}
		}
		
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