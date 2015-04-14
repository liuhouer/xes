/**
 * api for RESTful operation
 */

/**
 * use case: <a href="/user/12" onclick="doRestDelete(this,'confirm delete?');return false;">delete</a>
 */
function doRestDelete(anchor,confirmMsg) {
	if (confirmMsg) {
		confirm(confirmMsg,function(){
			var f = document.createElement("form");
			f.style.display = "none";
			anchor.parentNode.appendChild(f);
			f.method = "POST";
			f.action = anchor.href;
			var m = document.createElement("input");
			m.setAttribute("type", "hidden");
			m.setAttribute("name", "_method");
			m.setAttribute("value", "delete");
			f.appendChild(m);
			f.submit();
		});
	}
}

function doRestBatchDelete(action,checkboxName,form) {
	if (!hasOneChecked(checkboxName)) {
		alert("请选择你要删除的记录!");
		return;
	}
	confirm("确认要删除选定记录吗？",function() {
		//confirm("您所选择删除的栏目为一级栏目，删除此栏目会将其下所有子栏目一并删除，是否继续？", function(){
			if(!endWith(action,"/")){
				form.action = action+"/";
			}else{
				form.action = action;
			}
			if(false){
				alert("|"+form.action+"|");
				return ;
			}
			
			form.method = 'POST';
			//添加操作类型
			var m = document.createElement("input");
			m.setAttribute("type", "hidden");
			m.setAttribute("name", "_method");
			m.setAttribute("value", "delete");
			form.appendChild(m);
			/*
			if($("input[name='"+checkboxName+"']").length > 0
				&& isNull($($("input[name='"+checkboxName+"']")[0]).parent("form").attr("tagName"))){
				var selOptions = getSelectedDom(checkboxName);
				$(selOptions).each(
					function(i,obj){
						var option = "<input style='display:none' type='"+$(obj).attr("type")+"' name='"+checkboxName+"' value='"+$(obj).val()+"' checked='checked' />";
						$(form).append(option);
					}
				);
			}*/
			//alert(action+"\n"+$(form).serialize());
			form.submit();
		//});
	});
}

//为jsp提供删除
function doBatchDelete(action,checkboxName,form) {
	if (!hasOneChecked(checkboxName)) {
		alert("请选择你要删除的对象!");
		return;
	}
	confirm("确认要删除选定记录吗？",function() {
			form.action = action;
			form.method = 'POST';
			//添加操作类型
			var m = document.createElement("input");
			m.setAttribute("type", "hidden");
			m.setAttribute("name", "method");
			m.setAttribute("value", "del");
			form.appendChild(m);
			if($("input[name='"+checkboxName+"']").length > 0
				&& isNull($($("input[name='"+checkboxName+"']")[0]).parent("form").attr("tagName"))){
				var selOptions = getSelectedDom(checkboxName);
				$(selOptions).each(
					function(i,obj){
						var option = "<input style='display:none' type='"+$(obj).attr("type")+"' name='"+checkboxName+"' value='"+$(obj).val()+"' checked='checked' />";
						$(form).append(option);
					}
				);
			}
			//alert(action+"\n"+$(form).serialize());
			form.submit();
	});
}

