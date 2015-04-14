package com.up72.auth.taglib;

import static com.up72.auth.AuthUtils.userUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.up72.auth.AuthConstants;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.RoleDao;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.auth.model.Permission;
import com.up72.base.UserDetails;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

public class TagPermTaglib extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前权限在系统中没有维护时，是否强制输出
	 */
	private boolean attach;
	/**
	 * 标签体中，包含权限链接的选择器
	 * 是dom元素的路径和链接所在dom节点在属性名
	 * 例如：div span p [rel]
	 */
	private String selector;
	
	/**
	 * 权限类型，TAG权限 3  ，菜单权限 1   ，操作权限   2
	 */
	private Integer type;
	/**
	 * 需要替换在字符串，以xx，xx，在形式传入
	 */
	private String replace;
	
	private String jsPermPath;
	
	public void setReplace(String replace) {
		this.replace = replace;
	}

	public void setType(String type) {
		this.type = Integer.parseInt(type);
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public void setAttach(String attach) {
		this.attach = Boolean.parseBoolean(attach);
	}
	
	public void setJsPermPath(String jsPermPath) {
		this.jsPermPath = jsPermPath;
	}

	
	public int doEndTag() throws JspException {
		this.attach = true;
		int result = SKIP_BODY;
		//获得当前登录管理员
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();

		UserDetails user = null;
		if (userUtils.isLogin(request, null)) {
			// 取session登陆用户
			user = userUtils.getSessionUser(request, null);
			// 取cookie登陆用户
			if (null == user) {
				String userName = userUtils.getLoginName(request, null);
				AuthUserManager memberManager = (AuthUserManager) ApplicationContextHolder
						.getBean("authUserManager");
				user = memberManager.getMember(userName);
			}
		}
		
		//判断管理员未登录,return
		if(null == user){
			return result;
		}else{
			//获得标签体对象
			BodyContent bodyContent = this.getBodyContent();
			//获得标签体文本
			String tagBody = bodyContent.getString();
			//获得权限在uri
			String uri = this.getPermURI(tagBody);
//			System.out.println("uri : " + uri);
			if(null != uri && !uri.trim().equals("")){
				uri = this.replaceUri(uri, request);
				uri = stringUtil.parseToPath(uri);
				//System.out.println("user.getId::::" + user.getId());
				//如果权限验证通过，则输出标签体，否则忽略标签体
				if(this.hasPermission(user,uri)){
					JspWriter out = this.bodyContent.getEnclosingWriter();
					try {
						out.print(tagBody);
					} catch (IOException e) {
						throw new JspException(e);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @author baoxin.men 2012-10-17 上午11:57:54 update 截取虚拟目录
	 * @param uri
	 * @param request
	 * @return
	 */
	protected String replaceUri(String uri, HttpServletRequest request){
		String result = uri;
		String ctx = request.getContextPath();
		if(this.replace != null && !this.replace.trim().equals("")){
			List<String> list = stringUtil.split(this.replace, ",");
			if(null != list){
				for(int i=0;i<list.size();i++){
					// 获取到要替换的字符串
					String replace = list.get(i);
					// 如果是虚拟目录路径
					if(replace.trim().equals(ctx)){
						// 将虚拟目录路径截取 为匹配数据库的权限字段
						if(result.startsWith(replace)){
							result = result.substring(replace.length(), result.length());
						}
					}else{
						result = result.replace(list.get(i), "*");
					}
				}
			}
		}
		
		return result;
	}

	public int doStartTag() throws JspException {
		// SKIP_BODY, EVAL_BODY_INCLUDE, EVAL_BODY_BUFFERED
		int result = EVAL_BODY_BUFFERED;
		return result;
	}

	// =========================选择器解析开始=================================================
	/**
	 * 解析选择器，获得选择器数组，数组最后一个元素为包含链接的属性
	 * 如果this.selector为空或是等于null字符串，则返回null
	 */
	protected String[] getSelectorArray() throws JspException {
		String[] result = null;
		if(this.selector != null && !this.selector.trim().equals("") && !this.selector.trim().equals("null")){
			result = this.selector.split(" ");
			if (null != result && result.length > 0) {
				// 去掉选择器之间的空格
				for (int i = 0; i < result.length; i++) {
					result[i] = result[i].trim();
				}
	
				String attribute = result[result.length - 1].trim();
				// 如果数组最后一个元素是属性
				if (attribute.startsWith("[")) {
					result[result.length - 1] = attribute.substring(1, attribute
							.length() - 1);
				}
				// 如果数组最后一个元素是选择器和属性组合的字符串，重新解析selector数组
				else {
					result = reParseSelectorArray(result);
				}
			} else {
				throw new JspException("标签属性[selector]不能为空！");
			}
		}
		return result;
	}

	/*
	 * 如果数组最后一个元素是选择器和属性组合的字符串，重新解析selector数组
	 */
	protected String[] reParseSelectorArray(String[] selectorArray) {
		String[] result = new String[selectorArray.length + 1];
		for (int i = 0; i < selectorArray.length; i++) {
			result[i] = selectorArray[i];
		}

		String[] attrArray = parseAttribute(selectorArray[selectorArray.length - 1]);

		result[result.length - 2] = attrArray[0];
		result[result.length - 1] = attrArray[1];
		return result;
	}

	/*
	 * 从选择器和属性组合的字符串中解析属性
	 */
	protected String[] parseAttribute(String attribute) {
		String[] result = attribute.split("\\[");
		result[0] = result[0].trim();
		result[1] = result[1].substring(0, result[1].length() - 1).trim();
		return result;
	}

	// =========================选择器解析结束=================================================

	// =========================权限uri解析开始===============================================
	/**
	 * 获得权限的uri
	 */
	protected String getPermURI(String tagBody) throws JspException {
		String result = "";
		String[] selectorArray = this.getSelectorArray();
		//如果获取selector数据为null时
		if(selectorArray != null){
			StringBuffer regex = new StringBuffer();
			for (int i = 0; i < selectorArray.length; i++) {
				if(i == selectorArray.length-1){
					regex.append(selectorArray[i]).append("\\s*=\\s*[\'\"]{1}");
				}else{
					regex.append(selectorArray[i]).append(".*");
				}
			}
			regex.append("(([a-zA-Z][0-9a-zA-Z+\\\\-\\\\.]*:)?/{0,2}[0-9a-zA-Z;/?:@&=+$\\\\.\\\\-_!~*'()%]+)?(#[0-9a-zA-Z;/?:@&=+$\\\\.\\\\-_!~*'()%]+)?");
			regex.append("[\'\"]{1}.*");
			
			result = matchPermURI(regex.toString(), tagBody);
//			System.out.println("getPermURI    result:::::" + result);
		}else{
			result = this.jsPermPath;
		}
		return result;
	}
	
	protected String matchPermURI(String regex,String html){
		String result = null;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(html);
		while (m.find()) {
			result = m.group(1);
			break;
		}
		return result;
	}
	
	// =========================权限uri解析结束===============================================
	
	/**
	 * 判断当前登录用户是否具有访问指定权限路径的权限
	 * 
	 * @author bxmen update 如果是 系统管理员用户 直接强制输出
	 */
	protected boolean hasPermission(UserDetails user,String permURI) {
		boolean result = false;
		//根据uri获得权限
		Permission permission = null;
		PermissionDao permissionDao = (PermissionDao)ApplicationContextHolder.getBean("permissionDao");
		if(null != this.type && this.type != 0){
			permission = permissionDao.getPermissionByURL(permURI, this.type);
		}else{
			permission = permissionDao.getPermissionByURL(permURI);
		}
	
		//如果权限为空，并且强制输出，则返回true
		if(null== permission){
			return this.attach;
		} 
		if(user.getCode().equals(AuthConstants.MEMBER_TYPE_SYSTEM)){
			result = this.attach;
		}
		//如果权限不为空，则判断用户是否具有访问权限
		else {
			RoleDao roleDao = (RoleDao)ApplicationContextHolder.getBean("roleDao");
			//获取roleIds的字符串     memberRoleDao.getUserRoleIds(Long id)方法是获得RoleId列表
			List<String> roleCodeList = roleDao.getUserRoleCodes(user.getId());
			List<Permission> permissionList= permissionDao.getPermissionList(roleCodeList, (null==type || type == 0) ? AuthConstants.PERMISSION_TYPE_TAB : type, permURI);
			if(ObjectUtils.isEmpty(permissionList)){
				result = false;
			}else{
				result = true;
			}
		}
		return result;
	}
		
}