(function($) {
/**
 * options
 * list : 要操作的列名列表
 * box : 列表项过滤checkbox的name，默认为show_lable_value
 * tagName : 页面自定义tagname值，默认为showColumn
 */
$.fn.extend({
	columnShow : function(options) {
		this.options = options || {};
		var self = this;
		
		this.init = function(){
			if(self.isNull(self.options.tagName)){
				self.options.tagName = "showColumn";
			}
			if(self.isNull(self.options.box)){
				self.options.box = "show_lable_value";
			}
			
			self.boxes = $("input[name="+self.options.box+"]");
			$(self.boxes).attr("checked","");
			
			self.hideColumns = self.parseToArray(self.options.list);
			if(self.isNull(self.hideColumns)){
				return ;
			}
			$(self.hideColumns).each(function(i,obj){
				$("["+self.options.tagName+"="+obj+"]").hide();
			});
		}
		
		this.parseToArray = function(){
			if(self.isNull(self.options.list)){
				return null;
			}	
			/*
			var array = self.options.list.split(",");
			*/
			var array = eval(self.options.list);
			$(array).each(function(i,obj){
				if(self.isNull(obj) || $.trim(obj)==","){
					array.pop(obj);
				}else{
					if(!self.isNull(self.boxes)){
						$(self.boxes).each(function(j,box){
							if($(box).val()==obj){
								$(box).attr("checked","checked");
							}
						});
					}
				}
			});
			
			var result = new Array();
			$(self).each(function(i,obj){
				var column = $(obj).attr(self.options.tagName);
				var isPush = true;
				$(array).each(function(j,_obj){
					if(column == _obj){
						isPush = false;
						return;
					}
				});
				if(isPush){
					//alert(self.options.list+"\t->"+column);
					result.push(column);
				}
			});
			
			return result;
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