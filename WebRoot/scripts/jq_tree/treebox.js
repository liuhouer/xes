// JavaScript Document
/**
 * permtree.js
 * @category   javascript
 * @package    jquery
 * @author     bh.iune <twq0824@yeah.net>
 * @version    1.0
 */ 
 
/**
 * 权限树添加选取事件，基于jquery treeview插件
 * options
 * invert : 是否存在反选，默认true
 * invertCss : 反选样式
 * checked : {box:xx,array:[]} json
 */
(function($) {
$.fn.extend({
	permtree : function(options) {
		// 初始化方法，入口方法
		this.options = options || {};
		var self = this;

		this.init = function(){
			//初始化invert参数
			if(self.isNull(self.options.invert)){
				self.options.invert = false;
			}
			//判断该checkbox是否选中
			self.checkedBox();
			//遍历树
			self.loopTree($(this));
		}
		
		//判断该指定的checkbox列表是否选中
		this.checkedBox = function(){
			var permBoxes = $("input[name='"+self.options.checked.box+"']");
			var permArray = eval(self.options.checked.array);
			//如果权限树中，不包含，任何节点，则设置整棵树不可编辑
			if(self.isNull(permBoxes) || permBoxes.length == 0){
				$(self).find("input[name]").attr("disabled","disabled");
				return ;
			}
			//如果选中的节点为空则，返回
			if(self.isNull(self.options.checked.array)){
				return ;
			}
			
			$(permBoxes).each(
				function(i,obj){					
					var boxVal = parseInt($(obj).val());
					$(permArray).each(
						function(j,_obj){
							if(boxVal == _obj){
								self.checkedParentBox(obj);
								return;
							}
						}
					);
				}
			);
		}
		
		this.checkedParentBox = function(box){
			$(box).attr("checked","checked");
			var parentBox = $(box).parent("li").parent("ul").parent("li").children("input");
			$(parentBox).attr("checked","checked");
			var parentPaBox = $(parentBox).parent("li").parent("ul").parent("li").children("input");
			$(parentPaBox).attr("checked","checked");
		}
		
		//递归遍历这棵树，为每一个节点添加click事件
		this.loopTree = function(ul){
			$(ul).children("li").each(
				function(i,obj){
					//为checkbox绑定click事件
					self.bindChangAction(obj);
					if(self.options.invert){
						self.bindInvertAction(obj);
					}
					var child_ul = $(obj).children("ul");
					if(!self.isNull(child_ul)
						&& child_ul.length > 0){
						$(child_ul).each(function(j,_obj){
							self.loopTree(_obj);
						});
					}
				}
			);
		}
		
		//为节点添加onChang事件
		this.bindChangAction = function(li){
			$(li).children("input").bind("click",
				function(){
					self.selectParent(li);
					self.toggleCheckChildren(li,$(li).children("input").attr("checked"));
				}
			);
		}
		
		//判断状态改变子类是否选中，选中也同时选中父类节点
		this.selectParent = function(li){
			if($(li).children("input").attr("checked")){
				$(li).parents("li").children("input").attr("checked","checked");
			}
		}
		
		//激发chagn事件以后，反制触发事件的子节点的状态(checked->unchecked,unchecked->checked)
		this.toggleCheckChildren = function(li,checked){
			$(li).find("input").each(
				function(i,obj){
					if(checked){
						$(obj).attr("checked","checked");
					}else{
						$(obj).attr("checked","");
					}
				}
			);
			/*
			if(self.isNull($(li).children("ul"))
				|| $(li).children("ul").length == 0){
				return ;
			}
			$(li).children("ul").each(
				function(i,obj){
					$(obj).children("li").each(
						function(j,_obj){
							if(checked){
								$(_obj).children("input").attr("checked","checked");
							}else{
								$(_obj).children("input").attr("checked","");
							}
							if(!self.isNull($(_obj).children("ul"))
								&& $(_obj).children("ul").length > 0){
								self.toggleCheckChildren(_obj,checked);
							}
						}
					);
				}
			);*/
		}
		
		//创建反选节点，绑定反选事件
		this.bindInvertAction = function(li){
			//如果没有子节点，返回
			if(self.isNull($(li).children("ul"))
				|| $(li).children("ul").length ==0 ){
				return ;
			}
			//创建反选dom
			self.createInvertDom(li);
			//添加反选事件
			$(li).children("a").bind("click",
				function(){
					self.selectInvert(li);
				}
			);
		}
		
		this.createInvertDom = function(li){
			var invertdom = "<a href='javascript:void(0)'";
			if(!self.isNull(self.options.invertCss)){
				invertdom += "class='"+ self.options.invertCss +"'";
			}
			invertdom += ">反选</a>";
			$(li).children("input").after(invertdom);
		}
		
		//反选
		this.selectInvert = function(li){
			var isSelect = false;
			if(self.isNull($(li).children("ul"))
				|| $(li).children("ul").length == 0){
				return ;
			}
			$(li).children("ul").each(
				function(i,obj){
					//如果没有子节点正常反选
					$(obj).children("li").each(function(j,_obj){
						if(self.isNull($(_obj).children("ul"))
							|| $(_obj).children("ul").length == 0){
							if($(_obj).children("input").attr("checked")){
								$(_obj).children("input").attr("checked","");
							}else{
								isSelect = true;
								$(_obj).children("input").attr("checked","checked");
							}
						}
						//如果有子节点，要判断子节点是否有未被选中的，如果有则选中，如果没有则未选中
						else{
							isSelect = self.selectInvert(_obj);
						}
					});
				}
			);
			if(isSelect){
				$(li).children("input").attr("checked","checked");
			}else{
				$(li).children("input").attr("checked","");
			}
			return isSelect ;
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
		
		this.init();
	}
});
})(jQuery);