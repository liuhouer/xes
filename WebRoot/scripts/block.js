(function($) {
/**
 * options
 * url : 后台获得json数据的地址
 * id : 如果有默认值，id为默认选中的值，会将父级数据也同时取出，做选中状态
 * idLevel:传递id时附带的level参数
 * idParam : 查询时，传递id时的参数名，默认为id
 * param : 查询时，select的value的参数名，默认为selId
 * value : 查询时的初始值
 * name : 创建dom时的name名，id会以已经创建的dom编号,默认selId
 * level : 显示的级别
 * selectCall : 选择后的回调 
 */
$.fn.extend({
	block : function(_options) {
		this.sel = this;
		this.options = _options || {};
		
		this.isNull = function(obj){
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
		
		/*
		 * 初始化方法
		 */
		this.init = function(){
			if(this.isNull(this.options)){
				this.options = {};
			}
			if(this.isNull(this.options.param)){
				this.options.param = "selId";
			}
			if(this.isNull(this.options.name)){
				this.options.name = "selId";
			}
			if(this.isNull(this.options.id)){
				this.loadJson(null,this.options.value);
			}else{
				if(this.isNull(this.options.idParam)){
					this.options.idParam = "id";
				}
				this.loadParent();
			}
		}
		
		/*
		 * 引导父类
		 */
		this.loadParent = function(){
			var self = this;
			
			var params = this.options.idParam+"="+this.options.id;
			if(!self.isNull(this.options.idLevel)){
				params += "&idLevel="+self.options.idLevel;
			}
			
			$.ajax({
				url : this.options.url,
				data : params,
				dataType : "json",
				success : function(json){
					if(!self.isNull(json)
						&& json.length > 0){
						var callBackFun = function(index){
							if(index > json.length - 1){
								return;
							}
							var obj = json[index];
							
							self.loadJson(obj.value,obj.value,function(){
								callBackFun(index + 1);
								var opts = $(self.sel).find("#"+self.options.name+obj.value).children("option");
								
								$(opts).each(function(i,opt){
									$(json).each(function(i,jsonobj){
										if($(opt).val() == jsonobj.value){	
											$(opt).attr("selected","selected");
											return;
										}
									});
								});
							});
						}
							
						callBackFun(0);
					}
				}
			});
		}
		
		/*
		 * 加载json数据
		 */
		this.loadJson = function(id,param,callBack){
			if(this.isNull(id)){
				id = "";
			}
			var self = this;
			var dom = this.sel;
			if(!self.isNull(this.options.level)
				&& $(dom).find("span").length >= this.options.level){
				return;
			}
			
			var domName = self.options.name;
			
			var params = self.options.param+"="+param;
			
			if(!self.isNull(callBack)){
				params += "&"+self.options.idParam+"="+self.options.id;
			}
			
			$.ajax({
				url : this.options.url,
				data : params,
				dataType : "json",
				success : function(json){
					if(!self.isNull(json)
						&& json.length > 0){
						var selHtml = "<span id='sel_span"+id+"'><select id='"+(domName+id)+"' name='"+domName+"'>";
						selHtml += "<option value=''>请选择</option>";
						$(json).each(function(i,obj){
							selHtml += "<option value='"+obj.value+"'>"+obj.key+"</option>";
						});
						
						selHtml += "</select></span>";
						
						$(dom).append(selHtml);
						
						var seldom = $(dom).find("#"+domName+id);
						
						if(!self.isNull(seldom)){
							$(seldom).bind("change",function(){
								$(this).parent("span").nextAll("span").remove();
								if(typeof($(this).val()) != "undefined"
									&& null != $(this).val()
									&& "" != $.trim($(this).val()))
								self.loadJson($(this).val(),$(this).val());
								
							});
						}
					}
					
					if(!self.isNull(callBack)){
						if(typeof(callBack) == 'string'){
							eval(callBack);
						}else{
							callBack.call();
						}
					}
					
					if(!isNull(_options.selectCall)){
						evalFunction(self.options.selectCall);
					}
				}
			});
		}
		
		this.init();
	}
});
})(jQuery);