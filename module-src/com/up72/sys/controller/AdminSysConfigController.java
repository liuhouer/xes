package com.up72.sys.controller;

import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;
import static com.up72.util.SystemUtils.systemUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.auth.controller.AuthController;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.service.AuthUserManager;
import com.up72.base.UserDetails;
import com.up72.common.CommonConstants;
import com.up72.framework.web.scope.Flash;
import com.up72.sys.SystemConfig;
import com.up72.util.PropertiesUtil;

@Controller
@RequestMapping("/admin/sys")
public class AdminSysConfigController extends
		AuthController<Object, java.lang.Long> {
	@Autowired
	private AuthUserManager authUserManager;

	@RequestMapping(value = "/exportPage")
	public String exportPage(HttpServletRequest request,
			HttpServletResponse response) {

		return "/admin/sys/sysConfig/exportPage";
	}

	@RequestMapping(value = "/sysConfig")
	public String sysConfig(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		return "/admin/sys/sysConfig/index";
	}

	/**
	 * 系统设置系统换肤
	 * 
	 * @author baoxin.men create 2012-09-06
	 */
	@RequestMapping(value = "/sysConfig/changeSkin")
	public String changeSkin(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String styleJson = SystemConfig.instants().getValue(
				CommonConstants.sysConfig.CHANGE_STYLE_KEY);
		model.addAttribute("styleJson", styleJson);
		AuthUser authUser = this.getAdminLoginMember(request);
		if (null == authUser) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("loginUser", authUser);
		}
		return "/admin/sys/sysConfig/changeSkin";
	}

	/**
	 * 保存风格及皮肤
	 * 
	 * @author baoxin.men create 2012-09-06
	 * @throws IOException
	 */
	@RequestMapping(value = "/sysConfig/saveStyleSkin")
	@ResponseBody
	public String saveStyleSkin(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String style = paramUtils.getParameter(request, "style_radio",
				"default");
		String skin = paramUtils
				.getParameter(request, "skin_radio", "deepblue");
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		String status = "";
		String message = "";

		UserDetails userDetails = this.getAdminLoginUser(request);
		if (null != userDetails) {
			AuthUser authUser = authUserManager.getById(userDetails.getId());
			authUser.setStyle(style);
			authUser.setSkin(skin);
			try {
				authUserManager.saveOrUpdate(authUser);
				this.setSessionLogin(request, authUser);
				status = "success";
				message = "风格皮肤更新成功！";
			} catch (Exception e) {
				status = "error";
				message = "风格皮肤更新失败！";
			}
		} else {
			status = "unLogin";
			message = "用户未登录，请登录重试！";
		}
		jsonMap.put("status", status);
		jsonMap.put("message", stringUtil.encode(message));
		return jsonUtil.object2Json(jsonMap).toString();
	}

	@RequestMapping(value = "/sysConfig/changeProjectName")
	public String changeProjectName(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		// SMIS 名称
		String projectName = "";
		try {
			projectName = PropertiesUtil.loadAllPropertiesFromClassLoader(
					CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(
					CommonConstants.sysConfig.PROJECT_NAME_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("projectName", projectName);
		return "/admin/sys/sysConfig/changeProName";
	}

	@RequestMapping(value = "/sysConfig/saveProjectName")
	@ResponseBody
	public String saveProjectName(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "";
		String status = "success";
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		String projectName = paramUtils
				.getParameter(request, "projectName", "");
		if (!projectName.trim().equals("")) {
			try {
				Properties properties = PropertiesUtil
						.loadAllPropertiesFromClassLoader(
								CommonConstants.sysConfig.SYSTEM_CONFIG)
						.getProperties();
				properties
						.setProperty(
								CommonConstants.sysConfig.PROJECT_NAME_KEY,
								projectName);
				PropertiesUtil.save(properties,
						CommonConstants.sysConfig.SYSTEM_CONFIG);
			} catch (IOException e) {
				status = "error";
				e.printStackTrace();
			}
		}
		jsonMap.put("status", status);
		try {
			result = jsonUtil.object2Json(jsonMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 下载
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download")
	public String download(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String filePath = paramUtils.getParameter(request, "filePath");
		String fileName = paramUtils.getParameter(request, "fileName");
		String url = paramUtils.getParameter(request, "url", "");
		response.setContentType("application/x-download");
		if (null != filePath && !"".trim().equals(filePath)) {
			String realPath = CommonConstants.ROOTPATH
					+ filePath.substring(1, filePath.length());
			File file = new File(realPath);
			if (file.exists()) {
				try {
					fileName = fileName.replaceAll(" ", "");
					fileName = new String(fileName.getBytes("gb2312"),
							"ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
				}
				response.setHeader("Content-Disposition",
						"attachment; filename=" + fileName);

				try {
					FileInputStream fis = new FileInputStream(realPath);

					int flag = 0;
					byte[] buff = new byte[1024 * 4];

					ServletOutputStream sos = response.getOutputStream();
					while ((flag = fis.read(buff, 0, buff.length)) != -1) {
						sos.write(buff, 0, flag);
					}

					fis.close();
					sos.close();
				} catch (Exception e) {
					try {
						response.getWriter().print("下载出现错误！");
					} catch (IOException e1) {
						Flash.current().error("下载错误，请重试");
						if (null != url && !url.trim().equals("")) {
							return "redirect:" + url;
						}
					}
				}
			} else {
				Flash.current().error("下载失败，未找到文件");
				if (null != url && !url.trim().equals("")) {
					return "redirect:" + url;
				}
			}
		}
		return null;
	}

	/**
	 * 系统信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sysInfo")
	public String sysInfo(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = systemUtil.getSystemInfo();
		model.addAttribute("result", result);
		return "/admin/sys/sysConfig/sysInfo";
	}
}
