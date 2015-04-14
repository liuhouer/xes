/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.controller;

import static com.up72.common.CommonUtils.cookieUtil;
import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.objectUtils;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.app.auth.service.UserManager;
import com.up72.auth.AuthConstants;
import com.up72.auth.annotation.PermissionAnnotation;
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.bean.PermissionTree;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.service.PermissionGroupManager;
import com.up72.auth.service.PermissionManager;
import com.up72.auth.service.ProductManager;
import com.up72.auth.service.RoleManager;
import com.up72.base.UserDetails;
import com.up72.common.CommonConstants;
import com.up72.common.image.CutInfo;
import com.up72.common.image.ImageTools;
import com.up72.exception.UtilException;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.web.scope.Flash;

/**
 * 用户数据提取跳转
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin")
@PermissionGroupAnnotation(name = "菜单", description = "菜单跳转管理Controller")
public class AdminController extends AuthController<Object, java.lang.Long> {

	@Autowired
	private AuthUserManager authUserManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private PermissionManager permissionManager;
	@Autowired
	private ProductManager productManager;
	@Autowired
	private PermissionGroupManager permissionGroupManager;

	private final String INDEX_ACTION = "redirect:/admin/goProduct/18";

	/** binder用于bean属性的设置 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model, HttpServletRequest request) {
	}

	/**
	 * 欢迎页
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings( { "unchecked" })
	@RequestMapping(value = "/index")
	@PermissionAnnotation(name = "欢迎页", type = PermissionAnnotation.TYPE_MENU)
	public String index(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String result = "/admin/index";
		UserDetails user = getAdminLoginUser(request);
		// List<Permission> list = null;
		if (ObjectUtils.isEmpty(user)) {
			result = "redirect:/admin/login";
		} else {
			result = "redirect:/admin/auth/member/sys";
		}
		// else if (user.getUserName().trim().toLowerCase().equals("admin")) {
		// HashMap<String, Object> params = new HashMap<String, Object>();
		// params.put("type", 1);
		// params.put("url!='#'", null);
		// params.put("url like '/%'", null);
		// TreeMap<String, String> orderMap = new TreeMap<String, String>();
		// orderMap.put("sortId", "asc");
		// list = permissionManager.findList(params, 1, orderMap);
		// } else {
		// Long roleId = this.getSessionRoleId(request);
		// list = permissionManager.getPermissionListByRole(roleId,
		// AuthConstants.PERMISSION_TYPE_MENU, 1);
		// }
		// if (null != list && !list.isEmpty()) {
		// Permission permission = list.get(0);
		// if (null != permission && null != permission.getUrl()
		// && !permission.getUrl().trim().equals("")) {
		// result = "redirect:" + permission.getUrl()
		// + (permission.getUrl().indexOf("?") == -1 ? "?" : "&")
		// + "AUTH_PERM_ID=" + permission.getId();
		// }
		// }
		model.addAttribute("currentProduct", getCurrentProduct(request));
		return result;
	}

	/**
	 * 进入登陆
	 * 
	 * @author jhe
	 */
	@RequestMapping(value = "/login")
	public String login(ModelMap model, HttpServletRequest request) {
		AuthUser user = this.getLoginMember(request);
		model.addAttribute("loginStatusChecked", null == user ? 0 : 1);
		model.addAttribute("member", user);
		return "/admin/login";
	}

	/**
	 * 登陆
	 * 
	 * @author jhe
	 * @throws Exception
	 */
	@RequestMapping(value = "/authUser/doLogin", method = RequestMethod.POST)
	public String doLogin(ModelMap model, AuthUser user,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String page = INDEX_ACTION;

		// 获得cookie登陆状态，用以判断是否
		int loginStatus = paramUtils.getIntParameter(request, "loginStatus", 0);
		AuthUser dbAuthUser = authUserManager.getMember(user.getUserName()
				.trim().toLowerCase());

		// 登陆处理，返回结果信息
		int loginResult = authUserManager.userLogin(user, dbAuthUser);
		// 判断登录结果，做对应处理
		switch (loginResult) {
		// 成功
		case 1:
			if (loginStatus == 1) {
				this.setCookieLogin(response, dbAuthUser.getUserName(),
						AuthConstants.LOGIN_COOKIE_TIME_LENGTH, dbAuthUser
								.getCode());
			} else {
				this.setCookieLogin(response, dbAuthUser.getUserName(), 0,
						dbAuthUser.getCode());
			}
			cookieUtil.setCookie(response,
					AuthConstants.COOKIE_ADMIN_MEMBER_KEY, dbAuthUser
							.getUserName(),
					AuthConstants.LOGIN_COOKIE_TIME_LENGTH);
			this.setSessionLogin(request, dbAuthUser);

			/**
			 * 设置登录信息，暂为定值,后可从用户信息中取默认站点
			 */
			// siteManager.setLoginSite(response, request, 1l,
			// CommonConstants.LOGIN_COOKIE_TIME_LENGTH);
			// this.setCookieSite(response, "1",
			// CommonConstants.LOGIN_COOKIE_TIME_LENGTH);
			// 更新登陆状态信息
			dbAuthUser.setLastIp(request.getRemoteAddr());
			dbAuthUser.setLastVisiTime(new Date().getTime());
			authUserManager.update(dbAuthUser);
			break;
		// 用户被禁用
		case 2:
			page = "/admin/login";
			Flash.current().error("用户已被禁用！");
			model.addAttribute("loginStatusChecked", loginStatus);
			break;
		// 以下做穿透处理，如果返回值属于以下三种情况，则归于“用户名或密码错误”处理
		// 用户类型错误
		case 3:
			// 密码错误
		case 4:
			// 用户不存在
		case 5:
			page = "/admin/login";
			Flash.current().error("用户名或密码错误！");
			model.addAttribute("loginStatusChecked", loginStatus);
			break;
		}

		model.addAttribute("member", user);
		return page;
	}

	/**
	 * 退出
	 * 
	 * @author jhe
	 */
	@RequestMapping(value = "/authUser/logout")
	@PermissionAnnotation(name = "退出", type = PermissionAnnotation.TYPE_MENU)
	public String logout(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		clearSessionLogin(request);
		clearCookieLogin(response);
		return "redirect:/admin/login";
	}

	/**
	 * 后台首页头部产品标签
	 * 
	 * @author jhe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/menu/productLabel")
	@PermissionAnnotation(name = "产品列表标签", type = PermissionAnnotation.TYPE_TAG)
	public String productLabel(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Long roleId = getSessionRoleId(request);
		UserDetails userDetails = this.getAdminLoginUser(request);
		List<Product> productList = userManager
				.getProducts(roleId, userDetails);
		model.addAttribute("productList", productList);
		return "/admin/productLabel";
	}

	/**
	 * 后台首页头部角色标签
	 * 
	 * @author jhe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/menu/roleLabel")
	@PermissionAnnotation(name = "角色列表标签", type = PermissionAnnotation.TYPE_TAG)
	public String roleLabel(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		AuthUser user = this.getAdminLoginMember(request);
		model.addAttribute("loginMember", user);
		if (objectUtils.isNotEmpty(user)) {
			model.addAttribute("roleList", user.getRoles());
		}
		Long roleId = this.getSessionRoleId(request);
		if (objectUtils.isNotEmpty(roleId)) {
			model.addAttribute("role", roleManager.getById(roleId));
		}
		model.addAttribute("roleId", roleId);
		return "/admin/roleLabel";
	}

	/**
	 * 后台首页左边菜单标签
	 * 
	 * @author jhe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/menu/permissionLabel")
	@PermissionAnnotation(name = "权限标签", type = PermissionAnnotation.TYPE_TAG)
	public String permissionLabel(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		UserDetails userDetails = this.getAdminLoginUser(request);
		String productId = this.getSessionProductId(request);
		Long roleId = this.getSessionRoleId(request);
		HashMap<String, PermissionTree> permissionTreeMap = (HashMap<String, PermissionTree>) userManager
				.getMenus(roleId, productId, userDetails);
		model.addAttribute("permissionTreeMap", permissionTreeMap);

		return "/admin/permissionLabel";
	}

	/**
	 * 后台首页头部产品标签
	 * 
	 * @author jhe
	 * @update wqtan 2012-11-16 11:05 判断产品是否有dashboard地址，如果没有跳转默认dashboard地址。
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goProduct/{productId}")
	@PermissionAnnotation(name = "产品列表", type = PermissionAnnotation.TYPE_TAG)
	public String goProduct(ModelMap model, @PathVariable
	java.lang.Long productId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String result = INDEX_ACTION;
		Product product = this.productManager.getById(productId);
		request.getSession().setAttribute("AUTH_PRODUCT", product);
		String productCode = (null == product) ? "" : product.getCode();
		setSessionProductId(request, productCode);
		if(null!=product && null!=product.getDashboardUrl()&& !"".equals(product.getDashboardUrl())){
			result = "redirect:" + product.getDashboardUrl();
		}else if(null!=product && !productCode.trim().equals("")){
			result = "redirect:/admin/auth/permission/"+productCode+"/goSysSet";
		}
		
//		if (product != null && product.getDashboardUrl() != null
//				&& !"".equals(product.getDashboardUrl())) {
//			setSessionProductId(request, product.getCode());
//			result = "redirect:" + product.getDashboardUrl();
//		} else if (objectUtils.isNotEmpty(productCode)) {
//			setSessionProductId(request, product.getCode());
//			Long roleId = getSessionRoleId(request);
//			UserDetails userDetails = this.getAdminLoginUser(request);
//			Permission permission = userManager.getFirstProductPermission(
//					roleId, productCode, userDetails);
//			
//			if (null != permission && null != permission.getUrl()
//					&& !permission.getUrl().trim().equals("")) {
//				if (permission.getUrl().startsWith("http://")) {
//					result = "redirect:" + permission.getUrl();
//				} else if (permission.getUrl().startsWith("/")
//						&& permission.getUrl().indexOf("?") != -1) {
//					result = "redirect:" + permission.getUrl()
//							+ "&AUTH_PERM_ID=" + permission.getId();
//				} else if (permission.getUrl().startsWith("/")
//						&& permission.getUrl().indexOf("?") == -1) {
//					result = "redirect:" + permission.getUrl()
//							+ "?AUTH_PERM_ID=" + permission.getId();
//				} else {
//					String url = request.getHeader("Referer");
//					result = "redirect:" + url;
//				}
//			} else {
//				String url = request.getHeader("Referer");
//				result = "redirect:" + url;
//			}
//		}
		return result;
	}

	@RequestMapping(value = "/permGroup/{permGroupId}")
	@PermissionAnnotation(name = "权限组列表", type = PermissionAnnotation.TYPE_TAG)
	public String permGroup(ModelMap model, @PathVariable
	java.lang.Long permGroupId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long roleId = getSessionRoleId(request);
		UserDetails userDetails = this.getAdminLoginUser(request);
		if (objectUtils.isEmpty(userDetails)) {
			return "redirect:/admin/login";
		}
		String result = "/admin/index";
		PermissionGroup permissionGroup = this.permissionGroupManager
				.getById(permGroupId);
		String permGroupCode = null == permissionGroup ? "" : permissionGroup
				.getCode();
		if (objectUtils.isNotEmpty(permGroupCode)) {
			List<Permission> permissionList = userManager.getPermissions(
					roleId, permGroupCode, userDetails);
			model.addAttribute("AUTH_PERMISSIONLIST", permissionList);
			model.addAttribute("permGroupId", permGroupId);

			model.addAttribute("currentProduct", getCurrentProduct(request));

			Permission permission = userManager.getFirstPermission(roleId,
					permGroupCode, userDetails);
			if (null != permission && null != permission.getUrl()
					&& !permission.getUrl().trim().equals("")) {
				if (permission.getUrl().startsWith("http://")) {
					result = "redirect:" + permission.getUrl();
				} else if (permission.getUrl().startsWith("/")
						&& permission.getUrl().indexOf("?") != -1) {
					result = "redirect:" + permission.getUrl()
							+ "&AUTH_PERM_ID=" + permission.getId();
				} else if (permission.getUrl().startsWith("/")
						&& permission.getUrl().indexOf("?") == -1) {
					result = "redirect:" + permission.getUrl()
							+ "?AUTH_PERM_ID=" + permission.getId();
				} else {
					String url = request.getHeader("Referer");
					result = "redirect:" + url;
				}
			} else {
				String url = request.getHeader("Referer");
				result = "redirect:" + url;
			}
		}
		return result;
	}

	/**
	 * 后台当前位置一层（当前产品），及左侧栏目选中样式控制
	 * 
	 * @author jhe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/setCurrentProduct")
	@PermissionAnnotation(name = "设置当前产品", type = PermissionAnnotation.TYPE_TAG)
	public String setCurrentProduct(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		model.addAttribute("currentProduct", getCurrentProduct(request));
		return "/admin/currentProduct";
	}

	/**
	 * 后台首页头部角色标签
	 * 
	 * @author jhe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setRoleId/{roleId}")
	@PermissionAnnotation(name = "设置角色", type = PermissionAnnotation.TYPE_TAG)
	public String setRoleId(ModelMap model, @PathVariable
	java.lang.Long roleId, HttpServletRequest request,
			HttpServletResponse response) {
		AuthUser user = this.getAdminLoginMember(request);
		if (user != null && objectUtils.isNotEmpty(roleId)) {
			setSessionRoleId(request, roleId);
		}

		return INDEX_ACTION;
	}

	/**
	 * 后台首页菜单标签
	 * 
	 * @author jhe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/menu/nav")
	@PermissionAnnotation(name = "头部菜单", type = PermissionAnnotation.TYPE_TAG)
	public String nav(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Long roleId = getSessionRoleId(request);
		UserDetails userDetails = this.getAdminLoginUser(request);
		List<Product> productList = userManager
				.getProducts(roleId, userDetails);
		model.addAttribute("productList", productList);
		return "/admin/menu/nav";
	}

	/**
	 * 后台首页左边菜单标签
	 */
	@RequestMapping(value = "/menu/left")
	@PermissionAnnotation(name = "左侧菜单", type = PermissionAnnotation.TYPE_TAG)
	public String left(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Long permissionGroupId = paramUtils.getLongParameter(request,
				"AUTH_PERMISSION_GROUP_ID", 0L);
		UserDetails userDetails = this.getAdminLoginUser(request);
		if (null != userDetails) {
			String productCode = this.getSessionProductId(request);
			Long roleId = this.getSessionRoleId(request);
			
			if(null==productCode || productCode.trim().equals("")){
				Product product = productManager.findAll().get(0);
				productCode = product.getCode();
			}
			
			List<PermissionGroup> permissionGroupList = userManager
					.getPermissionGroups(roleId, productCode, userDetails);
			model.addAttribute("permissionGroupList", permissionGroupList);
			model.addAttribute("AUTH_PERMISSION_GROUP_ID", permissionGroupId);
		} else {
			return "redirect:/admin/login";
		}

		String productCode = this.getSessionProductId(request);
		Long roleId = this.getSessionRoleId(request);

		if (null != productCode) {
			String permJson = getPermissionJsonDatas(userDetails, productCode,
					roleId);
			request.setAttribute("permJson", permJson);
		}
		String result = "";
		// 风格 影响页面架构
		String styles = getStyle(request);
		// 皮肤 影响页面渲染
		String skin = getSkin(request);
		model.addAttribute("skin",skin);
		model.addAttribute("styles",styles);
		
		if(null!=styles && !styles.trim().equals("")){
			result = "/admin/menu/"+styles+"/left";
		}else{
			result = "/admin/menu/left";
		}
//		result = "/admin/menu/left";
		return result;
	}

	/**
	 * 获得指定产品下权限json串
	 * 
	 * @param userDetails
	 * @param productCode
	 * @param roleId
	 * @return String
	 */
	private String getPermissionJsonDatas(UserDetails userDetails,
			String productCode, Long roleId) {
		StringBuffer jsonObject = new StringBuffer("[");
		List<PermissionGroup> permissionGroupList = userManager
				.getPermissionGroups(roleId, productCode, userDetails);
		for (PermissionGroup group : permissionGroupList) {
			List<Permission> permList = userManager.getPermissions(roleId,
					group.getCode(), userDetails);
			// if(null!=permList && !permList.isEmpty()){
			jsonObject.append("{");
			jsonObject.append("\"name\":\"" + group.getName() + "\",");
			jsonObject.append("\"code\":\"" + group.getCode() + "\",");
			jsonObject.append("\"imgPath\":\"" + group.getImgPath() + "\",");
			jsonObject.append("\"id\":\"" + group.getId() + "\",");
			jsonObject.append("\"sortId\":\"" + group.getSortId() + "\",");
			if (null != permList && !permList.isEmpty()) {
				jsonObject.append("\"permList\":[");
				for (int j = 0; j < permList.size(); j++) {
					Permission perm = permList.get(j);
					jsonObject.append("{");
					jsonObject.append("\"name\":\"" + perm.getName() + "\",");
					jsonObject.append("\"code\":\"" + perm.getCode() + "\",");
					jsonObject.append("\"imgPath\":\"" + perm.getImgPath()
							+ "\",");
					jsonObject.append("\"id\":\"" + perm.getId() + "\",");
					jsonObject.append("\"sortId\":\"" + perm.getSortId()
							+ "\",");
					jsonObject.append("\"url\":\"" + perm.getUrl() + "\"");
					if (j < permList.size() - 1) {
						jsonObject.append("},");
					} else {
						jsonObject.append("}");
					}
				}
				jsonObject.append("]");
			}
			jsonObject.append("},");
			// }
		}
		String permJson = jsonObject.toString();
		if (permJson.endsWith(",")) {
			permJson = permJson.substring(0, permJson.length() - 1);
		}
		permJson += "]";
		return permJson;
	}

	/**
	 * 系统设置统一跳转，设置产品code
	 */
	@RequestMapping(value = "/sysCofig/{permissionId}")
	public String sysCofig(ModelMap model, @PathVariable
	Long permissionId, HttpServletRequest request, HttpServletResponse response) {
		String url = paramUtils.getParameter(request, "url", "");
		Permission permission = permissionManager.getById(permissionId);
		setSessionProductId(request, permission.getProductCode());
		if (!"".equals(url)) {
			return "redirect:" + url + "?AUTH_PERM_ID=" + permissionId;
		} else {
			return INDEX_ACTION;
		}
	}
	
	
	
	/**
	 * base页面
	 * @author wqtan update 2012-08-25 创建
	 */
	@RequestMapping(value = "/adminBase")
	public String adminBase(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String result = "/admin/adminBase";
		// 风格 影响页面架构
		String style = getStyle(request);
		// 皮肤 影响页面渲染
		String skin = getSkin(request);
		// 获取system.properties配置文件中的风格名称
		List<String> styleNames = getStyleNames();
		// 根据不同的styles名称调用不同的adminBase页面
		result = getAdminBase(style, styleNames);
		model.addAttribute("skin",skin);
		model.addAttribute("style",style);
		
		AuthUser user = this.getLoginMember(request);
		model.addAttribute("loginUser", user);
		return result;
	}
	
	/**
	 * 跳转图片裁剪处理
	 * 
	 * @author wqtan
	 */
	@RequestMapping(value = "/cutImage")
	public String goCutImage(ModelMap model, HttpServletRequest request){
		String status = "success";
		
		String imgPath = paramUtils.getParameter(request, "imgPath","");
		model.addAttribute("imgPath", imgPath);
		
		File imgFile = new File(stringUtil.parseToPath(CommonConstants.ROOTPATH+"/"+imgPath));
		if(imgFile.isFile() && imgFile.exists()){
			Image image = null;
			try {
				image = ImageTools.readImage(imgFile.getPath());
			} catch (UtilException e) {
				status = "error";
				return "/admin/cutImage";
			}

			int imgWidth = image.getWidth(null);
			int imgHeight = image.getHeight(null);
			model.addAttribute("imgWidth", imgWidth);
			model.addAttribute("imgHeight", imgHeight);
		}else{
			status = "error";
			return "/admin/qmt/magzine/cutImage";
		}
		model.addAttribute("status", status);
		
		int width = paramUtils.getIntParameter(request, "width", 0);
		model.addAttribute("width", width);
		
		int height = paramUtils.getIntParameter(request, "height", 0);
		model.addAttribute("height", height);
		
		// 回调只需要传函数名，参数为filepath
		String call = paramUtils.getParameter(request, "call","");
		model.addAttribute("call", call);

		// 截取宽高
		String sw = paramUtils.getParameter(request, "sw","");
		model.addAttribute("sw", sw);
		String sh = paramUtils.getParameter(request, "sh","");
		model.addAttribute("sh", sh);
		// 图片存储类型
		String imgType = paramUtils.getParameter(request, "imgType","");
		model.addAttribute("imgType", imgType);
		return "/admin/cutImage";
	}
	
	/**
	 * 图片裁剪处理
	 * 
	 * @author wqtan
	 */
	@RequestMapping(value = "/doCutImage")
	@ResponseBody
	public String doCutImage(ModelMap model, CutInfo cutInfo, HttpServletRequest request) throws IOException{
		// 获得当前登陆用户
		String imgPath = paramUtils.getParameter(request, "imgPath","");
		String imgType = paramUtils.getParameter(request, "imgType","");
		if(null==imgType || imgType.trim().equals("")){
			imgType = fileUtil.getSuffix(imgPath);
		}else{
			imgType = "."+imgType;
		}
		String message = "修改成功！";
		String status = "success";
		HashMap<String,Object> jsonMap = new HashMap<String, Object>();
		if(cutInfo.getCutHeight()<=0 
				|| cutInfo.getCutWidth() <= 0
				|| cutInfo.getCutX() < 0
				|| cutInfo.getCutY() < 0){
			status = "error";
			message = "请选择裁剪区域！";
		}else{// 获得要裁剪的图片
			// 更新头像
			// 获得原图的绝对路径
			imgPath = CommonConstants.ROOTPATH + imgPath;
			// 获得保存图的绝对路径
			String saveFile = imgPath + "_photo" + imgType;
			// 裁剪处理
			ImageTools.cutImage(cutInfo, imgPath, saveFile);
			// 获得头像的相对路径
			saveFile = stringUtil.parseToPath("/" + saveFile.substring(CommonConstants.ROOTPATH.length()));
			jsonMap.put("imgPath", saveFile);
		}
		jsonMap.put("status", status);
		jsonMap.put("message", stringUtil.encode(message));
		return jsonUtil.object2Json(jsonMap);
	}
	
	@RequestMapping(value="/formSubmitError")
	public String formSubmitError(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		String referer = paramUtils.getParameter(request, "referer","");
		if (!referer.equals("")) {
			model.addAttribute("referer", referer);
		}
		return "/admin/formSubmitError";
	}
}
