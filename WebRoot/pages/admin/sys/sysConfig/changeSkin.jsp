<%@page import="com.up72.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.up72.auth.member.model.AuthUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" >
	function changeStyle(obj, name, isInit) {
		if($("#" + obj).attr("class") == "layout_sel"){
			return;
		}
		
		$("#style_layout li").each(function(i, dom){
			if($.trim($(dom).attr("id")) == $.trim($.trim(name)+"_style")){
				$(dom).addClass("layout_sel");
				hideOther(name + "_skin");
				checkStyleRadio($(dom).attr("id"));
				//初始化即为编辑情况，不清除皮肤的radio checked
				//if(isInit != 1){
				//	clearSkinRadio();
				//}
			}else{
				$(dom).removeClass("layout_sel");
			}
		});
    }
    
    function changeSkin(obj, name){
    	if($("#" + obj).attr("class") == "skin_sel"){
			return;
		}
		$("#frmtd_skin div ul li").each(function(i, dom){
			if($.trim($(dom).attr("id")) == $.trim($.trim(name)+"_skin")){
				$(dom).addClass("skin_sel");
				checkSkinRadio($(dom).attr("id"));
			}else{
				$(dom).removeClass("skin_sel");
			}
		});
    }
    
    function hideOther(visible){
    	$("#" + visible).show();
    	$("#frmtd_skin div").each(function(i, dom){
    		if($.trim($(dom).attr("id")) != $.trim(visible)){
    			$(dom).hide();
    		}
    	});
    }
    
    function checkStyleRadio(styleRadio){
    	$("input[name='style_radio']").each(function(i, obj){
    		if($(obj).attr("id") == $.trim(styleRadio + "_radio")){
    			$(obj).attr("checked",true);
    		}else{
    			$("input[name='style_radio']").each(function(i, dom){
		    		if($.trim($(dom).attr("id")) != $.trim(styleRadio + "_radio")){
		    			$(dom).removeAttr("checked");
		    		}
		    	});
    		}
    	});
    }
    
    function checkSkinRadio(skinRadio){
    	$("input[name='skin_radio']").each(function(i, obj){
    		if($(obj).attr("id") == $.trim(skinRadio + "_radio")){
    			$(obj).attr("checked",true);
    			submitChangeStyle();
    		}else{
    			$("input[name='skin_radio']").each(function(i, dom){
		    		if($.trim($(dom).attr("id")) != $.trim(skinRadio + "_radio")){
		    			$(dom).removeAttr("checked");
		    		}
		    	});
    		}
    	});
    }
    
    function clearSkinRadio(){
    	$("input[name='skin_radio']").each(function(i, obj){
		    $(obj).removeAttr("checked");
		    //清除skin的选择样式。
		    $($(obj).parent()).removeClass("skin_sel");
    	});
    }
    
    $(document).ready(function(){
    	var styleHtml = "";
    	var skinHtml = "<h4>选择主页展示皮肤：</h4>";
    	
    	var styleSkinJson = ${styleJson};
    	for(i=0;i<styleSkinJson.length;i++){
    		var styles = styleSkinJson[i];
    		styleHtml += "<li class=\"\" onclick=\"changeStyle('" + styles.style + "_style','" + styles.style + "', 0)\" id=\"" + styles.style + "_style\"><input type=\"radio\" name=\"style_radio\" id=\"" + styles.style + "_style_radio\" value=\"" + styles.style + "\" style=\"display:none;\"><a href=\"javascript:;\"><span><img src=\"${ctx}" + styles.styleImgPath + "\"></span></a></li>";
    		var skins = styles.skins;
    		var checked = "";
    		var skinClass = "";
    		skinHtml += "<div class=\"style_skin\" id=\"" + styles.style + "_skin\" style=\"display: none;\">";
    		skinHtml += "<ul>";
    		for(j=0;j<skins.length;j++){
    			var skin = skins[j];
    			if('${loginUser.skin}' == skin.skinName){
    				skinHtml += "<li class=\"skin_sel\" onclick=\"changeSkin('" + skin.skinName + "_skin', '" + skin.skinName + "')\" id=\"" + skin.skinName + "_skin\"><input type=\"radio\" name=\"skin_radio\" id=\"" + skin.skinName + "_skin_radio\" value=\"" + skin.skinName + "\" checked=\"checked\" style=\"display:none;\"><a href=\"javascript:;\"><img src=\"${ctx}" + skin.imgPath + "\"><span>" + skin.viewName + "</span></a></li>";
    			}else{
    				skinHtml += "<li onclick=\"changeSkin('" + skin.skinName + "_skin', '" + skin.skinName + "')\" id=\"" + skin.skinName + "_skin\"><input type=\"radio\" name=\"skin_radio\" id=\"" + skin.skinName + "_skin_radio\" value=\"" + skin.skinName + "\" style=\"display:none;\"><a href=\"javascript:;\"><img src=\"${ctx}" + skin.imgPath + "\"><span>" + skin.viewName + "</span></a></li>";
    			}
    		}
    		skinHtml += "</ul>"
    		skinHtml += "</div>";
    	}
    	
    	//风格
    	$("#style_layout").html(styleHtml);
    	//皮肤
    	$("#frmtd_skin").html(skinHtml);
    	
		changeStyle('${loginUser.style}_style', '${loginUser.style}', 1);
    });

	function submitChangeStyle(){
		if(!isSelect("style_radio", "")){
			alert("请选择风格！");
			return;
		}else if(!isSelect("skin_radio", "")){
			alert("请选择皮肤");
			return;
		}
		$.ajax({
			type : "post",
			url: "${ctx}/admin/sys/sysConfig/saveStyleSkin",
			data: $("#iscs_change_style_skin_form").serialize(),
			dataType : "json",
			cache: false,
			success: function(msg){
				var message = decodeURI(msg.message);
				if(msg.status == "success"){
					//alert(message);
					window.location.reload();
				}else if(msg.status == "unLogin"){
					alert(message);
					window.location.href="${ctx}/admin/authUser/logout";
				}else{
					alert(message);
				}
			}
		});
	}
</script>
<form name="iscs_change_style_skin_form" id="iscs_change_style_skin_form" method="post" action="${ctx}/admin/auth/member/sysConfig/saveStyleSkin">
<div class="up72_show layout_skin">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="show_table">
    <tbody>
      <tr class="frmtr">
        <th class="frmth">
            <h4>请选择主页布局：</h4>
            <ul class="style_layout" id="style_layout">
            </ul>
        </th>
        <td class="frmtd" id="frmtd_skin"></td>
      </tr>
    </tbody>
  </table>
</div>
</form>