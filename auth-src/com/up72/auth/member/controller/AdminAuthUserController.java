/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.member.controller;

import static com.up72.auth.AuthUtils.userUtils;
import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.encryptUtil;
import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.app.auth.service.UserManager;
import com.up72.auth.AuthConstants;
import com.up72.auth.AuthUtils;
import com.up72.auth.controller.AuthController;
import com.up72.auth.member.AuthUserConstants;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.auth.member.vo.query.AuthUserQuery;
import com.up72.auth.model.MemberRole;
import com.up72.auth.model.Organization;
import com.up72.auth.model.Product;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.service.MemberRoleManager;
import com.up72.auth.service.OrganizationManager;
import com.up72.auth.service.ProductManager;
import com.up72.auth.service.RoleManager;
import com.up72.auth.service.WorkGroupManager;
import com.up72.auth.vo.query.RoleQuery;
import com.up72.auth.vo.query.WorkGroupQuery;
import com.up72.common.CommonConstants;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
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
@RequestMapping("/admin/auth/member")
public class AdminAuthUserController extends
		AuthController<AuthUser, java.lang.Long> {
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "id desc";

	@Autowired
	private AuthUserManager authUserManager;
	@Autowired
	private ProductManager productManager;
	@Autowired
	private MemberRoleManager memberRoleManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private WorkGroupManager workGroupManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private OrganizationManager organizationManager;

	private final String LIST_ACTION = "redirect:/admin/auth/member";

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
	 * 列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping
	@SuppressWarnings( { "unchecked" })
	public String index(ModelMap model, AuthUserQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 获得未删除数据
		query.setDelStatus(AuthUserConstants.MEMBER_DEL_STATUS);

		AuthUserConstants.PAGE_NUMBER = query.getPageNumber();
		AuthUserConstants.PAGE_SIZE = query.getPageSize();

		Page page = this.authUserManager.findPage(query);

		model.addAllAttributes(toModelMap(page, query));

		this.addShowLabelAttrbite("/admin/auth/member", model, request,
				response);
		return "/admin/auth/member/index";
	}

	/**
	 * 管理员列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/sys")
	@SuppressWarnings( { "unchecked" })
	public String sys(ModelMap model, AuthUserQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		query.setCode(AuthConstants.MEMBER_TYPE_SYSTEM);

		// 获得未删除数据
		query.setDelStatus(AuthUserConstants.MEMBER_DEL_STATUS);

		AuthUserConstants.PAGE_NUMBER = query.getPageNumber();
		AuthUserConstants.PAGE_SIZE = query.getPageSize();
		Page page = this.authUserManager.findPage(query);

		model.addAllAttributes(toModelMap(page, query));
		this.addShowLabelAttrbite("/admin/auth/member/sys", model, request,
				response);
		return "/admin/auth/member/sys/index";
	}

	/**
	 * 用户列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/user")
	@SuppressWarnings( { "unchecked" })
	public String user(ModelMap model, AuthUserQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		query.setCode(AuthConstants.MEMBER_TYPE_ADMIN);
		// 获得未删除数据
		query.setDelStatus(AuthUserConstants.MEMBER_DEL_STATUS);

		AuthUserConstants.PAGE_NUMBER = query.getPageNumber();
		AuthUserConstants.PAGE_SIZE = query.getPageSize();

		Page page = this.authUserManager.findPage(query);

		model.addAllAttributes(toModelMap(page, query));
		return "/admin/auth/member/user/index";
	}

	/** 显示 */
	@RequestMapping(value = "/{id}")
	public String show(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		model.addAttribute("authUser", authUser);
		return "/admin/auth/member/show";
	}

	/**
	 * 进入新增 添加管理员
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sys/new")
	public String sysNew(ModelMap model, AuthUser authUser,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 添加必备，不要删除
		String result = "/admin/auth/member/sys/new";
		authUser.setDelStatus(CommonConstants.PUBLIC_UNDELETE);
		authUser.setImgPath("/images/default.jpg");
		List<WorkGroup> workGroupList = userManager.getAllWorkGroup();

		model.addAttribute("workGroupList", workGroupList);
		model.addAttribute("authUser", authUser);
		
		
		//获得权限列表
		Role role = roleManager.getById(68L);
		model.addAttribute("role",new Role());
		
		//如果角色的产品id不为空或不为0，则说明为默认角色
		if(!ObjectUtils.isEmpty(role.getProductCode()) && !role.getProductCode().trim().equals("")){
			model.addAttribute("productList",new Product[]{role.getProduct()});
		}
		//如果角色的产品id为空，或者为0，则正常处理
		else{
			//获得指定角色所在机构的产品列表
			model.addAttribute("productList",roleManager.getProductList(role.getId()));
		}
		//获得指定角色的权限列表 转成id的json字符串
		List<Integer> perMenuIds = roleManager.getRolePermissionId(role.getId(), AuthConstants.PERMISSION_TYPE_MENU);
		model.addAttribute("permMenuIds",jsonUtil.object2Json(perMenuIds));
		
		model.addAttribute("workGroupId", paramUtils.getIntParameter(request,"workGroupId",0));
		model.addAttribute("organizationId", paramUtils.getIntParameter(request,"organizationId",0));
		this.addPermissionAttribute(model, request);
		return result;
	}

	/** 进入新增 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/new")
	public String userNew(ModelMap model, AuthUser authUser,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 添加必备，不要删除
		String result = "/admin/auth/member/user/new";
		authUser.setDelStatus(CommonConstants.PUBLIC_UNDELETE);
		authUser.setImgPath("/images/default.jpg");
		List<WorkGroup> workGroupList = userManager.getAllWorkGroup();

		model.addAttribute("workGroupList", workGroupList);
		model.addAttribute("authUser", authUser);
		return result;
	}

	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(method = RequestMethod.POST)
	public String create(ModelMap model, @Valid
	AuthUser authUser, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (errors.hasErrors()) {
			if (authUser.getCode() == 5) {
				return "/admin/auth/administrator/member/new";
			} else if (authUser.getCode() == 2) {
				return "/admin/auth/user/member/new";
			}
		}
		String flag = AuthUtils.paramUtils.getParameter(request, "flag", "/sys");
		authUser.setUserName(authUser.getUserName().trim().toLowerCase());
		// 设置默认头像
		authUser.setImgPath(AuthUserConstants.MEMBER_DEFAULT_PHOTO);
		// 密码加密
		authUser.setPassword(encryptUtil.md5(authUser.getPassword()));
		
		//设置默认风格 (system)
		authUser.setStyle("traditional");
		//设置默认皮肤 (mac)
		authUser.setSkin("blue");
		
		//
		authUser.setAddTime(System.currentTimeMillis());
		authUser.setOrganizationId(9L);
		// 得到新添加用户的ID
		 Long memberId = authUserManager.save(authUser);
		 
		//获得角色
		 AuthUser loginAuthUser = this.getAdminLoginMember(request);
		Role role = new Role();
		role.setCreateUserId(loginAuthUser.getId());
		role.setDescription("自动生成");
		role.setEnabled(1);
		role.setName("自动生成角色");
		role.setOrganizationId(9L);
		role.setRemark("添加用户时自动生成");
		role.setWorkGroupId(1L);
		role.setProductCode("744e8400aca4197774b0c5e148797525");
		roleManager.save(role);
		MemberRole mr = new MemberRole();
		mr.setMemberId(authUser.getId());
		mr.setRoleId(role.getId());
		memberRoleManager.save(mr);
		if(null!=role){
			//分配菜单权限
			long[] menuPermIds = paramUtils.getLongParameters(request, "menuPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
			roleManager.assignPermission(role, menuPermIds,AuthConstants.PERMISSION_TYPE_MENU);
		}

		Flash.current().success(CREATED_SUCCESS); // 存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION + flag + "?pageNumber="
				+ AuthUserConstants.PAGE_NUMBER + "&pageSize="
				+ AuthUserConstants.PAGE_SIZE;
	}

	/**
	 * 编辑用户信息
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/editInfo")
	public String editInfo(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuthUser authUser = getLoginMember(request);
		String result = "/admin/auth/administrator/member/edit";
		model.addAttribute("authUser", authUser);
		result = "/admin/auth/member/sys/editPage";
		return result;
	}

	/**
	 * 编辑用户信息
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/doEditInfo")
	public String doEditInfo(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuthUser authUser = getLoginMember(request);
		authUser.setNickName(paramUtils.getParameter(request, "nickName",
				authUser.getNickName()));
		authUser.setEmail(paramUtils.getParameter(request, "email", authUser
				.getEmail()));
		authUser.setMobile(paramUtils.getParameter(request, "mobile", authUser
				.getMobile()));
		authUser.setAnser(paramUtils.getParameter(request, "anser", authUser
				.getAnser()));
		authUser.setProblem(paramUtils.getParameter(request, "problem",
				authUser.getProblem()));
		authUser.setSecques(paramUtils.getParameter(request, "secques",
				authUser.getSecques()));
		authUser.setLoginAnswer(paramUtils.getParameter(request, "loginAnswer",
				authUser.getLoginAnswer()));
		authUserManager.update(authUser);
		Flash.current().success("编辑个人信息成功！");
		return "redirect:/admin/auth/member/editInfo";
	}

	/**
	 * 编辑用户信息
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/{id}/edit")
	public String edit(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		String result = "/admin/auth/administrator/member/edit";
		model.addAttribute("authUser", authUser);
		if (5 == authUser.getCode()) {
			result = "/admin/auth/member/sys/edit";
		} else if (2 == authUser.getCode()) {
			result = "/admin/auth/member/user/edit";
		}
		
		//获得权限列表
		Role role = roleManager.getById(memberRoleManager.getMemberRole(authUser.getId()).getRoleId());
		model.addAttribute("role",role);
		
		//如果角色的产品id不为空或不为0，则说明为默认角色
		if(!ObjectUtils.isEmpty(role.getProductCode()) && !role.getProductCode().trim().equals("")){
			model.addAttribute("productList",new Product[]{role.getProduct()});
		}
		//如果角色的产品id为空，或者为0，则正常处理
		else{
			//获得指定角色所在机构的产品列表
			model.addAttribute("productList",roleManager.getProductList(role.getId()));
		}
		//获得指定角色的权限列表 转成id的json字符串
		List<Integer> perMenuIds = roleManager.getRolePermissionId(role.getId(), AuthConstants.PERMISSION_TYPE_MENU);
		model.addAttribute("permMenuIds",jsonUtil.object2Json(perMenuIds));
		
		model.addAttribute("workGroupId", paramUtils.getIntParameter(request,"workGroupId",0));
		model.addAttribute("organizationId", paramUtils.getIntParameter(request,"organizationId",0));
		this.addPermissionAttribute(model, request);
		return result;
	}

	/** 选项卡编辑 */
	@RequestMapping(value = "/{id}/tabEdit")
	public String tabEdit(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		model.addAttribute("authUser", authUser);
		String result = "/admin/auth/member/user/tab_edit";
		if (authUser.getCode() == 5) {
			result = "/admin/auth/member/sys/tab_edit";
		} else if (authUser.getCode() == 2) {
			result = "/admin/auth/member/user/tab_edit";
		}
		return result;
	}

	/** 选项卡编辑 */
	@RequestMapping(value = "/tab")
	public String tab(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = paramUtils.getLongParameter(request, "id", 0L);
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		model.addAttribute("member", authUser);
		String result = "/admin/auth/member/user/tab";
		if (authUser.getCode() == 5) {
			result = "/admin/auth/member/sys/tab";
		} else if (authUser.getCode() == 2) {
			result = "/admin/auth/member/user/tab";
		}
		return result;
	}

	/** 选项卡显示 */
	@RequestMapping(value = "/{id}/tabShow")
	public String tabShow(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		model.addAttribute("authUser", authUser);
		String result = "/admin/auth/member/user/tab_show";
		if (authUser.getCode() == 5) {
			result = "/admin/auth/member/sys/tab_show";
		} else if (authUser.getCode() == 2) {
			result = "/admin/auth/member/user/tab_show";
		}
		return result;
	}

	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(ModelMap model, @PathVariable java.lang.Long id, @Valid AuthUser authUser, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (errors.hasErrors()) {
			if (authUser.getCode() == 5) {
				return "/admin/auth/administrator/member/edit";
			} else if (authUser.getCode() == 2) {
				return "/admin/auth/user/member/edit";
			}
		}
		String flag = AuthUtils.paramUtils.getParameter(request, "flag", "/sys");
		authUser.setId(id);
		AuthUser dbMember = authUserManager.getById(id);
		// 如果原密码长度小于32位，或者（原密码与现在密码不相同且原密码与现在加密后的密码不相同，则不加密）
		if (null!=authUser.getPassword() &&( authUser.getPassword().length() != 32
				|| (!authUser.getPassword().equals(dbMember.getPassword()) && !encryptUtil
						.md5(authUser.getPassword()).equals(
								dbMember.getPassword())))) {
			dbMember.setPassword(encryptUtil.md5(authUser.getPassword()));
		}

		// 设置用户名为无空格的不区分大小写的
//		dbMember.setUserName(authUser.getUserName().trim().toLowerCase());
		dbMember.setNickName(authUser.getNickName());
		dbMember.setEnabled(authUser.getEnabled());
		authUserManager.update(dbMember);
		
		
		//获得角色
		Long roleId = paramUtils.getLongParameter(request, "roleId", 0);
		Role role = roleManager.getById(roleId);
		if(null!=role){
			//分配菜单权限
			long[] menuPermIds = paramUtils.getLongParameters(request, "menuPermIds", AuthConstants.DEFAULT_LONG_VALUE);;
			roleManager.assignPermission(role, menuPermIds,AuthConstants.PERMISSION_TYPE_MENU);
		}

		Flash.current().success(UPDATE_SUCCESS);

		return LIST_ACTION + flag + "?pageNumber="
				+ AuthUserConstants.PAGE_NUMBER + "&pageSize="
				+ AuthUserConstants.PAGE_SIZE;
	}

	@RequestMapping(value = "/batchUpdate", method = RequestMethod.PUT)
	public String batchUpdate(ModelMap model, @RequestParam
	java.lang.String batchItems, @RequestParam
	java.lang.String[] batchNames, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// member表中字段类型为string的字段名
		String stringParam = "|userName|password|nickName|imgPath|email|mobile|country|province|city|county|postCode|addressInfo|loginAnswer|secques|problem|anser|";

		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < batchNames.length; i++) {
			if (batchNames[i].equals("birthdayTime")) {
				String birthdayTimeString = request
						.getParameter("birthdayTimeString");
				if (null != birthdayTimeString
						&& !birthdayTimeString.trim().equals("")) {
					map.put(batchNames[i], Long.toString(dateUtils
							.parseStringToDate(birthdayTimeString).getTime()));
				} else {
					map.put(batchNames[i], null);
				}
			} else if (stringParam.indexOf("|" + batchNames[i] + "|") != -1) {
				map.put(batchNames[i], "'"
						+ request.getParameter(batchNames[i]) + "'");
			} else {
				map.put(batchNames[i], request.getParameter(batchNames[i]));
			}
		}
		authUserManager.batchUpdaterMember(batchItems, map);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION + "?pageNumber=" + AuthUserConstants.PAGE_NUMBER
				+ "&pageSize=" + AuthUserConstants.PAGE_SIZE;
	}

	/** 批量删除到回收站 */
	@RequestMapping(value = "/recycleDelete")
	public String recycleDelete(ModelMap model, HttpServletRequest request,
			@RequestParam("items")
			java.lang.Long[] items) {
		authUserManager.recycleMember(items, 1);
		Flash.current().success(DELETE_SUCCESS);
		String userType = AuthUtils.paramUtils.getParameter(request,
				"userType", "");
		return LIST_ACTION + "/" + userType;
	}

	/** 批量还原回收站 */
	@RequestMapping(value = "/recycleReductive")
	public String recycleReductive(ModelMap model, HttpServletRequest request,
			@RequestParam("items")
			java.lang.Long[] items) {
		authUserManager.recycleMember(items, 0);
		Flash.current().success(OPRATE_SUCCESS);
		String userType = AuthUtils.paramUtils.getParameter(request,
				"userType", "");
		return LIST_ACTION + "/" + userType;
	}

	/** 列表 */
	@SuppressWarnings( { "unchecked" })
	@RequestMapping(value = "/recycle")
	public String recyclePage(ModelMap model, AuthUserQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		String userType = AuthUtils.paramUtils.getParameter(request,
				"userType", "");
		if ("sys".equals(userType)) {
			query.setCode(AuthConstants.MEMBER_TYPE_SYSTEM);
		} else if ("user".equals(userType)) {
			query.setCode(AuthConstants.MEMBER_TYPE_ADMIN);
		}
		Page page = this.authUserManager.findRecyclePage(query);

		AuthUserConstants.PAGE_NUMBER = query.getPageNumber();
		AuthUserConstants.PAGE_SIZE = query.getPageSize();
		model.addAllAttributes(toModelMap(page, query));
		model.addAttribute(AuthUserConstants.CURRENT_PAGE,
				AuthUserConstants.CURRENT_PAGE_TEXT_RECYCLE);
		model.addAttribute("userType", userType);
		return "/admin/auth/member/recycle";

	}

	/** 删除 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(ModelMap model, @PathVariable
	java.lang.Long id) {
		authUserManager.removeById(id);
		Flash.current().success(DELETE_SUCCESS);
		return "";
	}

	/** 批量删除 */
	@RequestMapping(method = RequestMethod.DELETE)
	public String batchDelete(ModelMap model, HttpServletRequest request,
			@RequestParam("items")
			java.lang.Long[] items) {
		for (int i = 0; i < items.length; i++) {
			authUserManager.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		String userType = AuthUtils.paramUtils.getParameter(request,
				"userType", "");
		if (!"".equals(userType) && userType.endsWith("/")) {
			userType = userType.substring(0, userType.length() - 1);
		}
		model.addAttribute("userType", userType);
		return "redirect:/admin/auth/member/recycle";
	}

	/** ****************************************************************************************************** */

	/** 选项卡 修改 */
	@RequestMapping(value = "/{id}/tabEditInfo")
	public String tabEditInfo(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		model.addAttribute("authUser", authUser);
		String result = "/admin/auth/administrator/member/tab_edit_info";
		if (authUser.getCode() == 5) {
			result = "/admin/auth/administrator/member/tab_edit_info";
		} else if (authUser.getCode() == 2) {
			result = "/admin/auth/user/member/tab_edit_info";
		}
		return result;
	}

	/** 选项卡 修改 */
	@RequestMapping(value = "/{id}/tabBuddyInfo")
	public String tabBuddyInfo(ModelMap model, @PathVariable
	java.lang.Long id) throws Exception {
		AuthUser authUser = (AuthUser) authUserManager.getById(id);
		model.addAttribute("authUser", authUser);
		String result = "/admin/auth/administrator/member/tab_buddy_info";
		if (authUser.getCode() == 5) {
			result = "/admin/auth/administrator/member/tab_buddy_info";
		} else if (authUser.getCode() == 2) {
			result = "/admin/auth/user/member/tab_buddy_info";
		}
		return result;
	}

	/**
	 * 跳转到tab.jsp页面，并且返回Member对象
	 */
	@RequestMapping(value = "/{id}/tab")
	public String tab(ModelMap model, @PathVariable
	java.lang.Long id, HttpServletRequest request, HttpServletResponse response) {
		AuthUser authUser = authUserManager.getById(id);
		model.addAttribute("authUser", authUser);
		String result = "/admin/auth/administrator/member/tab";
		if (authUser.getCode() == 5) {
			result = "/admin/auth/administrator/member/tab";
		} else if (authUser.getCode() == 2) {
			result = "/admin/auth/user/member/tab";
		}
		return result;
	}

	/**
	 * 得到所有用户组（部门）
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/workGroup")
	public String getWorkGroup(ModelMap model, WorkGroupQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId",
				AuthUserConstants.DEFAULT_LONG_VALUE);
		List<WorkGroup> workGroupList = userManager.getAllWorkGroup();

		model.addAttribute("workGroupList", workGroupList);
		model.addAttribute("workGroupId", workGroupId);
		return "/admin/auth/member/work_group";
	}

	/**
	 * 根据workGroupId得到角色
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/role")
	public String getRole(ModelMap model, RoleQuery query,
			HttpServletRequest request, HttpServletResponse response) {
		Long workGroupId = paramUtils.getLongParameter(request, "workGroupId",
				AuthUserConstants.DEFAULT_LONG_VALUE);
		Long roleId = paramUtils.getLongParameter(request, "roleId",
				AuthUserConstants.DEFAULT_LONG_VALUE);

		List<Role> roleList = userManager.getRoleByWorkGroupId(workGroupId);
		WorkGroup workGroup = userManager.getWorkGroup(workGroupId);

		model.addAttribute("roleList", roleList);
		model.addAttribute("workGroup", workGroup);
		model.addAttribute("roleId", roleId);
		return "/admin/auth/member/role";
	}

	// ========================权限开始===============================

	/**
	 * 获得某一用户所在的部门 及 角色
	 * 
	 * @author bxmen
	 */
	@RequestMapping(value = "/{userId}/tagWorkGroupMember")
	public String getWorkGroupMember(ModelMap model, @PathVariable
	Long userId) {
		AuthUser authUser = authUserManager.getById(userId);
		model.addAttribute("authUser", authUser);

		List<Organization> organizationList = this.organizationManager
				.findAll();
		model.addAttribute("organizationList", organizationList);

		Organization memberOrganization = organizationManager
				.getMemberOrganization(userId);
		model.addAttribute("memberOrganization", memberOrganization);

		model.addAttribute("memberWorkGroupList", workGroupManager
				.getMemberWorkGroupList(userId));

		model.addAttribute("memberRoleList", roleManager
				.getMemberRoleList(userId));

		return "/admin/auth/member/tag_workGroupMember";
	}

	/**
	 * 修改用户所在部门及角色
	 * 
	 * @author wqtan
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateOrganization")
	@ResponseBody
	public String updateOrganization(ModelMap model, @RequestParam("memberId")
	Long memberId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// long[] workGroupIds = paramUtils.getLongParameters(request,
		// "workGroupIds", 0L);
		long[] roleIds = paramUtils.getLongParameters(request, "roleIds", 0L);
		Long organizationId = paramUtils.getLongParameter(request,
				"organizationId", 0);
		String status = "success";
		String message = "操作成功";

		// if(null==workGroupIds
		// || workGroupIds.length==0
		// || (workGroupIds.length==1&&workGroupIds[0] == 0)){
		// status = "error";
		// message = "请选择用户所属工作组";
		// }else
		if (organizationId == 0) {
			status = "error";
			message = "请选择用户所属机构";
		} else if (null == roleIds || roleIds.length == 0
				|| (roleIds.length == 1 && roleIds[0] == 0)) {
			status = "error";
			message = "请选择用户角色";
		} else {
			this.authUserManager.editMemberWorkGroupAndRole(memberId,
					organizationId, null, roleIds);
		}

		jsonMap.put("status", status);
		jsonMap.put("message", stringUtil.encode(message));
		return jsonUtil.object2Json(jsonMap);
	}

	// ========================权限结束===============================

	/**
	 * 列表
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings( { "unchecked" })
	@RequestMapping(value = "/systemUser")
	public String systemUser(ModelMap model, AuthUserQuery query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 默认按照时间降序排列
		if (query.getSortColumns() == null || "".equals(query.getSortColumns())) {
			query.setSortColumns("REG_TIME desc");
		}

		// 获得未删除数据
		query.setDelStatus(AuthUserConstants.MEMBER_DEL_STATUS);
		// query.setCode(AuthUserConstants.USER_CODE_SYSTEM);
		Page page = this.authUserManager.findPage(query);

		model.addAllAttributes(toModelMap(page, query));

		this.addShowLabelAttrbite("/admin/ucenter/member", model, request,
				response);
		return "/admin/ucenter/member/systemUser";
	}

	/**
	 * 跳转到后台用户修改密码
	 * 
	 * @author wqtan
	 */
	@RequestMapping(value = "/resetPassword")
	public String resetPassword(ModelMap model) {
		return "/admin/auth/member/resetPassword";
	}
	
	/**
	 * 后台用户修改密码
	 * 
	 * @author wqtan
	 */
	@RequestMapping(value = "/modifyPassword")
	public String modifyPassword(ModelMap model) {
		return "/admin/auth/member/modifyPassword";
	}

	/**
	 * 后台用户修改密码
	 * 
	 * @author wqtan
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatePassword")
	@ResponseBody
	public String updatePassword(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String oldpassword = paramUtils.getParameter(request, "oldpassword","");
		String newpassword = paramUtils.getParameter(request, "newpassword","");
		String re_newpassword = paramUtils.getParameter(request, "re_newpassword","");
		AuthUser authUser = this.getLoginMember(request);
		Map<String, String> jsonMap = new HashMap<String, String>();
		String status = "error";
		String message = "系统错误";

			if(StringUtils.isNotBlank(oldpassword)){
				if(StringUtils.isNotBlank(newpassword)){
					if(newpassword.equals(re_newpassword)){
						try {
							this.authUserManager.resetPwd(oldpassword, newpassword, authUser);
							status = "success";
							message = "修改成功";
						} catch (ServiceException e) {
							message = e.getMessage();
						}
					}else{
						message = "两次密码不一致";
					}
				}else{
					message = "新密码不能为空";
				}
			}else{
				message = "原密码不能为空";
			}
		jsonMap.put("status", status);
		jsonMap.put("message", stringUtil.encode(message));

		return jsonUtil.object2Json(jsonMap);
	}

	/**
	 * 跳转到后台用户用户中心
	 * 
	 * @author wqtan
	 * @throws IOException
	 */
	@RequestMapping(value = "/home")
	public String home(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long roleId = userUtils.getSessionRoleId(request);
		if (roleId != null) {
			Role role = this.roleManager.getById(roleId);
			model.addAttribute("role", role);
			// 如果角色的产品id不为空或不为0，则说明为默认角色
			if (!ObjectUtils.isEmpty(role.getProductCode())
					&& !role.getProductCode().trim().equals("")) {
				model.addAttribute("productList", new Product[] { role
						.getProduct() });
			}
			// 如果角色的产品id为空，或者为0，则正常处理
			else {
				// 获得指定角色所在用户组的产品列表
				WorkGroup workGroup = role.getWorkGroup();
				if (null != workGroup) {
					model.addAttribute("productList", workGroup
							.getProductList());
				} else {
					model.addAttribute("productList", productManager.findAll());
				}
			}
		} else {
			model.addAttribute("productList", productManager.findAll());
		}
		this.addPermissionAttribute(model, request);
		model.addAttribute("pageName", "个人设置");
		return "/admin/auth/member/home";
	}

	@RequestMapping(value = "/homeSetting")
	@ResponseBody
	public String homeSetting(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		return "";
	}

	@RequestMapping(value = "/validateName")
	@ResponseBody
	public String validateName(HttpServletRequest request) {
		String result = Boolean.FALSE.toString();
		String userName = paramUtils.getParameter(request, "userName", "");

		AuthUser user = this.authUserManager.getMember(userName);
		if (null == user) {
			result = Boolean.TRUE.toString();
		}
		return result;
	}
	
	/** 
	 * 设置启用禁用
	 * @author liutongling
	 **/
	@RequestMapping(value="/{id}/doValid")
	@ResponseBody
	public String doValid(@PathVariable java.lang.Long id,HttpServletRequest request) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String status = ERROR;

		AuthUser user = authUserManager.getById(id);
		if(null != user){
			if(CommonConstants.PUBILC_ENABLED.intValue() == user.getEnabled()){
				user.setEnabled(CommonConstants.PUBILC_UNENABLE);
			}else{
				user.setEnabled(CommonConstants.PUBILC_ENABLED );
			}
			authUserManager.update(user);
			jsonMap.put("valid", user.getEnabled());
			status = SUCCESS;
		}
		jsonMap.put("status", status);
		return jsonUtil.object2Json(jsonMap);
	}
}