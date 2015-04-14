package com.up72.auth.controller;

import static com.up72.auth.AuthUtils.userUtils;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.framework.util.ObjectUtils.isEmpty;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.ui.ModelMap;

import com.up72.app.auth.service.UserManager;
import com.up72.auth.AuthConstants;
import com.up72.auth.member.dao.AuthUserDao;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.service.ProductManager;
import com.up72.base.BaseRestSpringController;
import com.up72.base.UserDetails;
import com.up72.common.CommonConstants;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.SystemConfig;


public abstract class AuthController<T,PK> extends BaseRestSpringController<T,PK> implements IAuthController<T, PK>{
	private  final ProductManager productManager = (ProductManager)ApplicationContextHolder.getBean("productManager");
	
	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getCurrentProduct(javax.servlet.http.HttpServletRequest)
	 */
	public  Product getCurrentProduct(HttpServletRequest request) {
		String productId = getSessionProductId(request);
		return productManager.getProductByCode(productId);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#setSessionLogin(javax.servlet.http.HttpServletRequest, com.up72.base.UserDetails)
	 */
	public  void setSessionLogin(HttpServletRequest request, UserDetails user) {
		userUtils.setSessionUser(request, user);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#setCookieLogin(javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public  void setCookieLogin(HttpServletResponse response, String value,
			Integer cookieTime, Integer userType) {
		userUtils.setLoginName(response, value, cookieTime, userType);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getLoginUser(javax.servlet.http.HttpServletRequest)
	 */
	public  UserDetails getLoginUser(HttpServletRequest request) {
		return getUser(request, AuthConstants.MEMBER_TYPE_USUALLY);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getLoginMember(javax.servlet.http.HttpServletRequest)
	 */
	public  AuthUser getLoginMember(HttpServletRequest request) {
		UserDetails userDetails = getLoginUser(request);
		if (isNotEmpty(userDetails)) {
			return (AuthUser) userDetails;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getAdminLoginMember(javax.servlet.http.HttpServletRequest)
	 */
	public  AuthUser getAdminLoginMember(HttpServletRequest request) {
		UserDetails userDetails = getAdminLoginUser(request);
		if (isNotEmpty(userDetails)) {
			return (AuthUser) userDetails;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getAdminLoginUser(javax.servlet.http.HttpServletRequest)
	 */
	public  UserDetails getAdminLoginUser(HttpServletRequest request) {
		UserDetails userDetails = getUser(request,
				AuthConstants.MEMBER_TYPE_ADMIN);
		if (isEmpty(userDetails)) {
			userDetails = getUser(request, AuthConstants.MEMBER_TYPE_SYSTEM);
		}

		return userDetails;
	}

	protected  UserDetails getUser(HttpServletRequest request, Integer userType) {
		UserDetails currentUser = null;
		// 先从cookie中查找
		if (null == currentUser) {
			String userName = userUtils.getLoginName(request, userType);
			if (userName != null && !("".equals(userName))) {
				AuthUserDao memberDao = (AuthUserDao) ApplicationContextHolder
						.getBean("authUserDao");
				currentUser = memberDao.findByProperty("userName", userName);
			}
		}
		if (currentUser == null && isSessionLogin(request, userType)) {
			currentUser = userUtils.getSessionUser(request, userType);
		}
		return currentUser;
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#isSessionLogin(javax.servlet.http.HttpServletRequest, java.lang.Integer)
	 */
	public  boolean isSessionLogin(HttpServletRequest request, Integer userType) {
		return userUtils.isLogin(request, userType);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#isCookieLogin(javax.servlet.http.HttpServletRequest, java.lang.Integer)
	 */
	public  boolean isCookieLogin(HttpServletRequest request, Integer userType) {
		return userUtils.isCookieLogin(request, userType);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#clearSessionLogin(javax.servlet.http.HttpServletRequest)
	 */
	public  void clearSessionLogin(HttpServletRequest request) {
		userUtils.clearSessionLogin(request);
		userUtils.clearSessionProductId(request);
		userUtils.clearSessionRoleId(request);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#clearCookieLogin(javax.servlet.http.HttpServletResponse)
	 */
	public  void clearCookieLogin(HttpServletResponse response) {
		userUtils.clearCookieLogin(response);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public  void logout(HttpServletRequest request, HttpServletResponse response) {
		clearSessionLogin(request);
		userUtils.clearCookieLogin(response);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#setSessionRoleId(javax.servlet.http.HttpServletRequest, java.lang.Long)
	 */
	public  void setSessionRoleId(HttpServletRequest request, Long roleId) {
		userUtils.setSessionRoleId(request, roleId);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getSessionRoleId(javax.servlet.http.HttpServletRequest)
	 */
	public  Long getSessionRoleId(HttpServletRequest request) {
		Long roleId = userUtils.getSessionRoleId(request);
		if (roleId == null) {
			UserDetails user = getUser(request, null);
			if (isNotEmpty(user)) {
				List<Role> roleList = user.getRoles();
				if (isNotEmpty(roleList)) {
					roleId = roleList.get(0).getId();

					setSessionRoleId(request, roleId);
				}
			}
		}
		return roleId;
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#setSessionProductId(javax.servlet.http.HttpServletRequest, java.lang.Long)
	 */
	public  void setSessionProductId(HttpServletRequest request, String productId) {
		userUtils.setSessionProductId(request, productId);
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getSessionProductId(javax.servlet.http.HttpServletRequest)
	 */
	public String getSessionProductId(HttpServletRequest request) {
		String productCode = userUtils.getSessionProductId(request);
		if (productCode == null) {
			Long roleId = getSessionRoleId(request);
			if (isNotEmpty(roleId)) {
				UserManager userManager = (UserManager) ApplicationContextHolder
						.getBean("userManager");
				UserDetails userDetails = getAdminLoginUser(request);

				List<Product> prodcutList = userManager.getProducts(roleId,
						userDetails);
				if (isNotEmpty(prodcutList)) {
					//productCode = prodcutList.get(0).getCode();

					setSessionProductId(request, productCode);
				}
			}

		}
		return productCode;
	}

	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getPermissionId(javax.servlet.http.HttpServletRequest)
	 */
	public  String getPermissionId(HttpServletRequest request){		
		return paramUtils.getParameter(request, AuthConstants.common.AUTH_PERM_ID);
	}
	
	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#getUrl(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	public  String getUrl(String url,HttpServletRequest request){
		if(url.indexOf("?") != -1){
			url = url + "&"+AuthConstants.common.AUTH_PERM_ID+"="+getPermissionId(request);
		}else{
			url = url + "?"+AuthConstants.common.AUTH_PERM_ID+"="+getPermissionId(request);
		}
		return url;
	}
	
	/* (non-Javadoc)
	 * @see com.up72.auth.controller.IAuthController#addPermissionAttribute(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	public  void addPermissionAttribute(ModelMap model,HttpServletRequest request){
		model.addAttribute(AuthConstants.common.AUTH_PERM_ID, 
				paramUtils.getParameter(request, AuthConstants.common.AUTH_PERM_ID));
	}
	

	
	
	/**
	 * 获取adminBase页面
	 * @author bxmen
	 * @param style
	 * @param styleNames
	 * @return 
	 * @summary 
	 */
	public String getAdminBase(String style, List<String> styleNames){
		String result = "/admin/adminBase";
		if(style.trim().equals("default")){
			result = "/admin/adminBase";
		}else{
			for(int i=0;i<styleNames.size();i++){
				String name = styleNames.get(i);
				if(name.trim().equals(style.trim())){
					result = "/admin/" + style + "AdminBase";
				}
			}
		}
		return result;
	}
	

	/**
	 * 获取风格
	 * @author bxmen
	 * @param request
	 * @return 
	 * @summary 
	 */
	public String getStyle(HttpServletRequest request){
		AuthUser user = this.getLoginMember(request);
		String result = "system";
		//用户存在
		if(null != user && !"".equals(user.getUserName().trim())){
			if(null != user.getStyle() && !"".equals(user.getStyle().trim())){
				result = user.getStyle();
			}
		}
		return result;
	}
	
	/**
	 * 获取皮肤
	 * @author bxmen
	 * @param request
	 * @return 
	 * @summary 
	 */
	public String getSkin(HttpServletRequest request){
		AuthUser user = this.getLoginMember(request);
		// 皮肤 影响页面渲染
		String result = "mac";
		if(null!=user && !"".equals(user.getUserName().trim())){
			if(null != user.getSkin() && !"".equals(user.getSkin().trim())){
				result = user.getSkin();
			}
		}
		return result;
	}
	
	/**
	 * 获取皮肤的json字符串
	 * @author bxmen
	 * @return 
	 * @summary 
	 */
	public List<String> getStyleNames(){
		String styleJson = SystemConfig.instants().getValue(CommonConstants.sysConfig.CHANGE_STYLE_KEY);
		List<String> result = new ArrayList<String>();
		JSONArray array = JSONArray.fromObject(styleJson);
		if(null != array && array.size() > 0){
			for(int i=0;i<array.size();i++){
				JSONObject object = array.getJSONObject(i);
				String key = object.get("style").toString();
				result.add(key);
			}
		}
		return result;
	}
}
