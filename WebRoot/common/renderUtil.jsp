<%@ page language="java" import="java.util.List,com.bruce.util.*" pageEncoding="utf-8"%>
<%!
/**
 * 获取select渲染
 * @param id 为html元素id与name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为选中值
 */
static final String $select(String id,List <Object[]>items,String defaultValue){
	StringBuffer resultBuffer = new StringBuffer("");
	resultBuffer.append("<select id=\"" + id + "\" name=\"" + id +	"\">");
	resultBuffer.append("<option value=\"\">无</option>");
	for (Object[] item : items) {
		resultBuffer.append("<option value=\"" + item[0] + "\"");
		if(defaultValue.equals(item[0].toString())){resultBuffer.append(" selected = \"selected\" ");}
		resultBuffer.append(">" + item[1] + "</option>");
	}
	resultBuffer.append("</select>");
	return resultBuffer.toString();
}

/**
 * 获取select复选渲染
 * @param id 为html元素id与name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为复选选中值
 */
private static final String $select(String id,List <Object[]>items,Object[] defaultValues,Integer size){
	StringBuffer resultBuffer = new StringBuffer("");
	String tempSize = "size=\"5\"";
	if(size != null){
		tempSize = "size=\"" + size + "\"";
	}
	resultBuffer.append("<select " + tempSize + " multiple id=\"" + id + "\" name=\"" + id +	"\">");
	for (Object[] item : items) {
		resultBuffer.append("<option value=\"" + item[0] + "\"");
		for (Object defaultValue : defaultValues) {
			if(defaultValue.equals(item[0].toString())){resultBuffer.append(" selected ");}
		}
		resultBuffer.append("/>" + item[1] + "</option>");
	}
	resultBuffer.append("</select>");
	return resultBuffer.toString();
}
private static final String $select(String id,List <Object[]>items,Object[] defaultValues){
	return  $select(id,items,defaultValues,null);
}
/**
 * 获取select复选渲染
 * @param id 为html元素id与name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为复选选中值
 */
private static final String $select(String id,List <Object[]>items,List defaultValues,Integer size){
	StringBuffer resultBuffer = new StringBuffer("");
	String tempSize = "size=\"5\"";
	if(size != null){
		tempSize = "size=\"" + size + "\"";
	}
	resultBuffer.append("<select " + tempSize + " multiple id=\"" + id + "\" name=\"" + id +	"\">");
	for (Object[] item : items) {
		resultBuffer.append("<option value=\"" + item[0] + "\"");
		for (Object defaultValue : defaultValues) {
			if(defaultValue.equals(item[0])){resultBuffer.append(" selected ");}
		}
		resultBuffer.append("/>" + item[1] + "</option>");
	}
	resultBuffer.append("</select>");
	return resultBuffer.toString();
}

private static final String $select(String id,List <Object[]>items,List defaultValues){
	return  $select(id,items,defaultValues,null);
}
/**
 * 获取checkBox复选渲染
 * @param name 为html元素name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为复选选中值
 */
private static final String $checkbox(String name,List <Object[]>items,Object[] defaultValues){
	StringBuffer resultBuffer = new StringBuffer("");
	for (Object[] item : items) {
		resultBuffer.append("<input type=\"checkbox\" name=\"" + name + "\" value=\"" + item[0] + "\"");
		for (Object defaultValue : defaultValues) {
			if(defaultValue.equals(item[0].toString())){resultBuffer.append(" checked ");}
		}
		resultBuffer.append(">" + item[1] + " ");
	}
	return resultBuffer.toString();
}
/**
 * 获取checkBox单选渲染
 * @param name 为html元素name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为复选选中值
 */
private static final String $checkbox(String name,List <Object[]>items,String defaultValues){
	return $checkbox(name,items,new Object[]{defaultValues});
}

/**
 * 获取checkBox复选渲染
 * @param name 为html元素id与name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为复选选中值
 */
private static final String $checkbox(String name,List <Object[]>items,List defaultValues){
	StringBuffer resultBuffer = new StringBuffer("");
	for (Object[] item : items) {
		resultBuffer.append("<input type=\"checkbox\" name=\"" + name + "\" value=\"" + item[0] + "\"");
		for (Object defaultValue : defaultValues) {
			if(defaultValue.equals(item[0])){resultBuffer.append(" checked ");}
		}
		resultBuffer.append(">" + item[1] + " ");
	}
	return resultBuffer.toString();
}
/**
 * 获取checkBox单选渲染
 * @param name 为html元素name名称
 * @param items items为下拉数据源 item[0]为value,item[1]为显示内容
 * @param defaultValue为复选选中值
 */
private static final String $radio(String name,List <Object[]>items,String defaultValue){
	StringBuffer resultBuffer = new StringBuffer("");
	for (Object[] item : items) {
		resultBuffer.append("<input type=\"radio\" name=\"" + name + "\" value=\"" + item[0] + "\"");
		if(defaultValue.equals(item[0].toString())){resultBuffer.append(" checked ");}
		resultBuffer.append(">" + item[1] + " ");
	}
	return resultBuffer.toString();
}
/**
 * 获取li渲染
 * @param items 为数据清单items[0]为传参，item[1]为显示
 * @param href 列表链接规则
 * @param target 列表链接目标
 * @param titleLength 标题截取长度
 * @param end 多余部分end符号
 * @param renders 渲染列表 使用方式：new Object[]{new Object[]{1,"style=color:red"},new Object[]{2,"style=color:blue"},new Object[]{3,"style=color:green"},new Object[]{4,"style=color:black"},new Object[]{5,"style=color:#0ff000;font-weight:bold"}}
 * @return
 */
private static final String $li(List <Object[]>items,Object[] href,Object[] target,Integer titleLength,String end,Object[] renders){
	StringBuffer resultBuffer = new StringBuffer("");
	for (Object[] item : items) {
		String tempId = item[0].toString();
		//处理标题长度
		String tempTitle = item[1].toString();
		if(titleLength != null && titleLength != 0){
			tempTitle = HTMLParser.subStringHTML(item[1].toString(), titleLength , end);
		}
		//处理标题长度
		//渲染列表颜色
		
		String randerStyle = "";
		if(renders != null){
			for (Object render : renders) {
				Object[] tempObj =((Object[])render);
				int indexStyle = 0;
				indexStyle = Integer.valueOf(tempObj[0].toString());
				if(indexStyle == items.indexOf(item)){
					randerStyle = tempObj[1].toString();
				}
			}
		}
		//渲染列表颜色
		//处理span额外标签
		String tempSpan = "";
		if(item.length == 3){
			tempSpan = "<span>" + item[2] + "</span>";
		}
		if(item.length == 4 && href.length == 2){
			tempSpan = "<span><a href=\"" + href[1] + tempId + "\" title=\"" + item[1] + "\" target=\"" + target[1] + "\">" + item[2] + "</a></span>";
		}
		//处理span额外标签
		resultBuffer.append("<li><a " + randerStyle + " href=\"" + href[0] + tempId + "\" title=\"" + item[1] + "\" target=\"" + target[0] + "\">" + tempTitle + "</a>" + tempSpan + "</li>");
	}
	return resultBuffer.toString();
}
private static final String $li(List <Object[]>items,Object[] href,Object[] target,Integer titleLength,String end){
	return $li(items, href, target, titleLength,end,null);
}
private static final String $li(List <Object[]>items,Object[] href,Object[] target,Integer titleLength){
	return $li(items, href, target, titleLength,"",null);
}
private static final String $li(List <Object[]>items,Object[] href,Object[] target){
	return $li(items, href, target, null , "",null);
}
/**以下提供单列li--------------------------**/
private static final String $li(List <Object[]>items,String href,String target,Integer titleLength,String end,Object[] renders){
	return $li(items, new Object[]{href}, new Object[]{target}, titleLength,end,renders);
}
private static final String $li(List <Object[]>items,String href,String target,Integer titleLength,String end){
	return $li(items, new Object[]{href}, new Object[]{target}, titleLength,end,null);
}
private static final String $li(List <Object[]>items,String href,String target,Integer titleLength){
	return $li(items, new Object[]{href}, new Object[]{target}, titleLength,"",null);
}
private static final String $li(List <Object[]>items,String href,String target){
	return $li(items, new Object[]{href}, new Object[]{target}, null,"",null);
}
/**
 * 获取li渲染
 * @param items 为数据清单items[0]为传参，item[1]为显示
 * @param href 列表链接规则
 * @param target 列表链接目标
 * @param titleLength 标题截取长度
 * @param end 多余部分end符号
 * @param renders 渲染列表 使用方式：new Object[]{new Object[]{1,"style=color:red"},new Object[]{2,"style=color:blue"},new Object[]{3,"style=color:green"},new Object[]{4,"style=color:black"},new Object[]{5,"style=color:#0ff000;font-weight:bold"}}
 * @return
 */
private static final String $tr(List <Object[]>items,Object[] href,Object[] target,Integer titleLength,String end,Object[] renders){
	StringBuffer resultBuffer = new StringBuffer("");
	for (Object[] item : items) {
		String tempId = item[0].toString();
		//处理标题长度
		String tempTitle = item[1].toString();
		if(titleLength != null && titleLength != 0){
			tempTitle = HTMLParser.subStringHTML(item[1].toString(), titleLength , end);
		}
		//处理标题长度
		//渲染列表颜色
		
		String randerStyle = "";
		if(renders != null){
			for (Object render : renders) {
				Object[] tempObj =((Object[])render);
				int indexStyle = 0;
				indexStyle = Integer.valueOf(tempObj[0].toString());
				if(indexStyle == items.indexOf(item)){
					randerStyle = tempObj[1].toString();
				}
			}
		}
		//渲染列表颜色
		//处理span额外标签
		String tempSpan = "";
		if(item.length == 3){
			tempSpan = "<span>" + item[2] + "</span>";
		}
		if(item.length == 4 && href.length == 2){
			tempSpan = "<span><a href=\"" + href[1] + tempId + "\" title=\"" + item[1] + "\" target=\"" + target[1] + "\">" + item[2] + "</a></span>";
		}
		//处理span额外标签
		resultBuffer.append("<tr><td><a " + randerStyle + " href=\"" + href[0] + tempId + "\" title=\"" + item[1] + "\" target=\"" + target[0] + "\">" + tempTitle + "</a>" + tempSpan + "</td></tr>");
	}
	return resultBuffer.toString();
}
private static final String $tr(List <Object[]>items,Object[] href,Object[] target,Integer titleLength,String end){
	return $tr(items, href, target, titleLength,end,null);
}
private static final String $tr(List <Object[]>items,Object[] href,Object[] target,Integer titleLength){
	return $tr(items, href, target, titleLength,"",null);
}
private static final String $tr(List <Object[]>items,Object[] href,Object[] target){
	return $tr(items, href, target, null , "",null);
}
/**以下提供单列tr--------------------------**/
private static final String $tr(List <Object[]>items,String href,String target,Integer titleLength,String end,Object[] renders){
	return $tr(items, new Object[]{href}, new Object[]{target}, titleLength,end,renders);
}
private static final String $tr(List <Object[]>items,String href,String target,Integer titleLength,String end){
	return $tr(items, new Object[]{href}, new Object[]{target}, titleLength,end,null);
}
private static final String $tr(List <Object[]>items,String href,String target,Integer titleLength){
	return $tr(items, new Object[]{href}, new Object[]{target}, titleLength,"",null);
}
private static final String $tr(List <Object[]>items,String href,String target){
	return $tr(items, new Object[]{href}, new Object[]{target}, null,"",null);
}
%>