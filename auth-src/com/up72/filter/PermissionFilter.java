package com.up72.filter;

import static com.up72.auth.AuthUtils.userUtils;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.up72.app.auth.service.UserManager;
import com.up72.auth.AuthConstants;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.service.PermissionGroupManager;
import com.up72.auth.service.PermissionManager;
import com.up72.auth.service.ProductManager;
import com.up72.base.UserDetails;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 资源控制器
 * 
 * @author jhe
 */
public class PermissionFilter extends OncePerRequestFilter {
	private static boolean debug = false;
	private static final String DEFAULT_EXECUDE_EXTENTIONS = "js,css,bmp,png,gif,jpg";
	private String[] excludePrefixes = new String[0];
	private Set<String> excludeExtentions = new HashSet<String>();

	private UserManager userManager;
	private AuthUserManager memberManager;
	private PermissionManager permissionManager;
	private ProductManager productManager;
	private PermissionGroupManager permissionGroupManager ;
	
	protected void initFilterBean() throws ServletException {
		try {
			initParameter(getFilterConfig());
		} catch (IOException e) {
			throw new ServletException("init paramerter error", e);
		}
		userManager = (UserManager) ApplicationContextHolder.getBean("userManager");
		permissionManager = (PermissionManager) ApplicationContextHolder.getBean("permissionManager");
		productManager = (ProductManager) ApplicationContextHolder.getBean("productManager");
		memberManager = (AuthUserManager) ApplicationContextHolder.getBean("authUserManager");
		permissionGroupManager = (PermissionGroupManager) ApplicationContextHolder.getBean("permissionGroupManager");
	}

	private void initParameter(FilterConfig filterConfig) throws IOException {
		debug = getBooleanParameter(filterConfig, "debug", false);
		String excludeExtentionsString = getStringParameter(filterConfig,
				"excludeExtentions", DEFAULT_EXECUDE_EXTENTIONS);
		excludeExtentions = new HashSet<String>((Arrays
				.asList(excludeExtentionsString.split(","))));

		String excludePrefixsString = getStringParameter(filterConfig,
				"excludePrefixes", null);
		if (org.springframework.util.StringUtils.hasText(excludePrefixsString)) {
			excludePrefixes = excludePrefixsString.split(",");
		}

		// System.out.println();
		// System.out.println("PermissionFilter.debug=" + debug);
		// System.out.println("PermissionFilter.excludeExtentions=["+
		// excludeExtentionsString + "]");
		// System.out.println("PermissionFilter.excludePrefixes=[" +
		// excludePrefixsString + "]");
		// System.out.println();

	}

	@SuppressWarnings("deprecation")
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			// //示例为一个固定的登陆用户,请直接修改代码
			MDC.put("loginUserId", "demo-loginUsername");

			MDC.put("req.requestURI", StringUtils.defaultString(request
					.getRequestURI()));
			MDC.put("req.queryString", StringUtils.defaultString(request
					.getQueryString()));
			MDC.put("req.requestURIWithQueryString", request.getRequestURI()
					+ (request.getQueryString() == null ? "" : "?"
							+ request.getQueryString()));
			MDC.put("req.remoteAddr", StringUtils.defaultString(request
					.getRemoteAddr()));

			// 为每一个请求创建一个ID，方便查找日志时可以根据ID查找出一个http请求所有相关日志
			MDC.put("req.id", StringUtils.remove(UUID.randomUUID().toString(),
					"-"));

			String url = request.getRequestURI();

			// 替换contextPath
			url = url.replace(request.getContextPath(), "");

			// 将'/+' 替换成 '/'
			url = url.replaceAll("[/]+", "/");

			// 将 '/admin/ucenter/member/' 替换成 '/admin/ucenter/member'
			int i = url.indexOf("/", url.length() - 1);
			if (i == (url.length() - 1)) {
				url = url.substring(0, url.length() - 1);
			}

			// 该资源是否能访问
			boolean result = verifyAuthentication(url, request);
			if (!result) {
				String referer = request.getHeader("Referer");
				if (isNotEmpty(referer)) {
					request.setAttribute("referer", referer);
				}

				// Redirect
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/common/permissionError.jsp");
				rd.forward(request, response);
				return;
			}
			Long permId = paramUtils.getLongParameter(request, "AUTH_PERM_ID",
					0L);
			if (permId > 0) {
				Permission permission = permissionManager.getById(permId);
				request.getSession()
						.setAttribute("AUTH_PERMISSION", permission);
				initPermissionDatas(request, permission);

			} else if (request.getSession().getAttribute("AUTH_PERMISSION") != null) {
				Permission permission = (Permission) request.getSession()
						.getAttribute("AUTH_PERMISSION");
				if (!permission.getUrl().startsWith("#")) {
					initPermissionDatas(request, permission);
				}
			}else{
				Long roleId = userUtils.getSessionRoleId(request);
				Permission permission = null;
				if(null==roleId){
					TreeMap<String,String> orderMap = new TreeMap<String, String>();
					orderMap.put("sortId", "asc");
					List<Permission> list = permissionManager.findList(null, 1, orderMap);
					permission = (null==list || list.isEmpty()) ? null : list.get(0);
				}else{
					List<Permission> list = permissionManager.getPermissionListByRole(roleId, AuthConstants.PERMISSION_TYPE_MENU, 1);
					permission = (null==list || list.isEmpty()) ? null : list.get(0);
				}
				
				if (null != permission && !permission.getUrl().startsWith("#")) {
					initPermissionDatas(request, permission);
				}
			}

			chain.doFilter(request, response);
		} finally {
			clearMDC();
		}
	}

	private void initPermissionDatas(HttpServletRequest request,
			Permission permission) {
		String productCode = userUtils.getSessionProductId(request);
		Product product = productManager.getProductByCode(productCode);
		request.setAttribute("AUTH_PRODUCT", product);

		if (null != permission && null != permission.getPermissionGroupCode()
				&& !permission.getPermissionGroupCode().trim().equals("")) {
			PermissionGroup permissionGroup = permissionGroupManager
					.getPermissionGroupByCode(permission
							.getPermissionGroupCode());
			request.setAttribute("AUTH_PERMISSION_GROUP", permissionGroup);
			if (null != permissionGroup
					&& null != permissionGroup.getProductCode()
					&& !permissionGroup.getProductCode().trim().equals("")) {
				Long roleId = getRoleId(request);
				UserDetails userDetails = getLoginUser(request);
				if (null != userDetails) {
					List<Permission> permissionList = userManager
							.getPermissions(roleId, permissionGroup.getCode(),
									userDetails);
					request.setAttribute("AUTH_PERMISSIONLIST", permissionList);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		String url = request.getRequestURI();
		url = url.replace(request.getContextPath(), "");
		url = url.replaceAll("[/]+", "/");
		boolean result = shouldNotURL(url);

		if (result && debug) {
			System.out.println(new Date().toLocaleString()
					+ " PermissionFilter shouldNotFilter request: " + url);
		}
		return result;
	}

	protected boolean shouldNotURL(String url) {
		if (url.equals("/") || url.equals("/index.jsp")
				|| url.equals("/index.htm") || url.equals("/index.html")) {
			return true;
		}

		for (String excludePrefix : excludePrefixes) {
			if (url.startsWith(excludePrefix)) {
				return true;
			}
		}

		String extension = org.springframework.util.StringUtils
				.getFilenameExtension(url);
		if (isNotEmpty(extension) && excludeExtentions.contains(extension)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings( { "unchecked" })
	private void clearMDC() {
		Map map = MDC.getContext();
		if (map != null) {
			map.clear();
		}
	}

	/**
	 * 验证资源
	 * 
	 * @author jhe
	 * @param url
	 * @param request
	 * @return 返回true为用户拥有该权限
	 */
	@SuppressWarnings("deprecation")
	private boolean verifyAuthentication(String url, HttpServletRequest request) {
		UserDetails userDetails = getLoginUser(request);
		// admin直接返回
		if (null != userDetails
				&& userDetails.getUserName().toLowerCase().equals("admin")) {
			return true;
		}

		// 是否为匿名资源
		boolean anonymity = userManager.isAnonymity(url, userDetails);

		boolean result = true;

		if (!anonymity) {
			Long roleId = getRoleId(request);
			// 是否有对资源访问权限
			result = userManager.isAccessRights(url, roleId, userDetails);
		}

		if (debug) {
			String userName = isNotEmpty(userDetails) ? userDetails
					.getUserName() : "";

			System.out.println(new Date().toLocaleString()
					+ " PermissionFilter: [userName=" + userName + "] request "
					+ url + " [isAnonymity=" + anonymity
					+ "]   [isAccessRights=" + result + "]");

		}
		return result;
	}

	/**
	 * 获得登陆用户
	 * 
	 * @author jhe
	 * @param request
	 * @param loginName
	 * @return
	 */
	private UserDetails getLoginUser(HttpServletRequest request) {
		UserDetails currentUser = null;
		if (userUtils.isLogin(request, null)) {
			// 取session登陆用户
			currentUser = userUtils.getSessionUser(request, null);
			// 取cookie登陆用户
			if (null == currentUser) {
				String userName = userUtils.getLoginName(request, null);
				currentUser = memberManager.getMember(userName);
			}
		}
		// else if(CookieUtil.isLogin(request)){
		// String userName = CookieUtil.getLoginName(request);
		// currentUser = memberService.getMember(userName);
		// }
		return currentUser;
	}

	/**
	 * 获得用户当前角色
	 * 
	 * @param request
	 * @param loginName
	 * @return
	 */
	private Long getRoleId(HttpServletRequest request) {
		Long roleId = userUtils.getSessionRoleId(request);
		if (roleId == null) {
			UserDetails user = getLoginUser(request);
			if (isNotEmpty(user)) {
				List<Role> roleList = user.getRoles();
				if (isNotEmpty(roleList)) {
					roleId = roleList.get(0).getId();

					userUtils.setSessionRoleId(request, roleId);
				}
			}
		}

		return roleId;
	}

	private String getStringParameter(FilterConfig filterConfig, String name,
			String defaultValue) {
		String value = filterConfig.getInitParameter(name);
		if (value == null || "".equals(value.trim())) {
			return defaultValue;
		}
		return value;
	}

	private boolean getBooleanParameter(FilterConfig filterConfig, String name,
			boolean defaultValue) {
		String value = getStringParameter(filterConfig, name, String
				.valueOf(defaultValue));
		return Boolean.parseBoolean(value);
	}
}
