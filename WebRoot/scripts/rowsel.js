// JavaScript Document
/**
 * singlesel.js
 * @category   javascript
 * @package    jquery
 * @author     bh.iune <twq0824@yeah.net>
 * @version    1.0
 */ 

/**
 * 单行选中处理方法
 * options
 * overCss 鼠标悬停css
 * outCss 鼠标移出css
 * clickCss 点击以后css
 * overAction 悬停处理事件
 * outAction 移出处理事件
 * clickAction 点击以后处理事件
 */
(function($) {
$.fn.extend({
	rowsel : function(options) {
		this.options = options || {};
		
		var self = this;
		
		this.init = function(){
			$(self).each(
				function(i,obj){
					self.bindAction(obj);
				}	
			);
		}
		
		this.bindAction = function(obj){
			//鼠标悬停处理事件
			$(obj).bind("mouseover",
				function(){
					self.select(obj);	
					$(obj).css("cursor","pointer");
					if(!self.isNull(self.options.overAction)){
						self.evalFunction(self.options.overAction,obj);
					}
				}
			);
			//鼠标移出处理事件
			$(obj).bind("mouseout",
				function(){
					self.unselect(obj);	 
					if(!self.isNull(self.options.outAction)){
						self.evalFunction(self.options.outAction,obj);
					}
				}
			);
			//鼠标点击处理事件
			if(!self.isNull(self.options.clickCss) || !self.isNull(self.options.clickAction)){
				$(obj).bind("click",
					function(){
						if(!self.isNull(self.options.clickCss)){
							$(self).removeClass(self.options.clickCss);
							$(obj).addClass(self.options.clickCss);
						}
						if(!self.isNull(self.options.clickAction)){
							self.evalFunction(self.options.clickAction,obj);
						}
					}
				);
			}
		}
		
		this.select = function(obj){
			if(self.isNull(self.options.outCss)){
				$(obj).addClass(self.options.overCss);
			}else{
				$(obj).attr("className",self.options.overCss);
			}
		}
		
		this.unselect = function(obj){
			if(self.isNull(self.options.outCss)){
				$(obj).removeClass(self.options.overCss);
			}else{
				$(obj).attr("className",self.options.outCss);
			}
		}
		
		//执行指定的方法
		this.evalFunction = function(fun,obj){
			var funType = typeof(fun);
			if(funType=="function"){
				fun.call(obj);
			}else if(funType=="string"){
				eval(fun);
			}
		}
		
		// 判断对象是否为空
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
		
		self.init();
	}
});
})(jQuery);