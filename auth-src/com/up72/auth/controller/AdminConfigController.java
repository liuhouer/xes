/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.controller;


import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.auth.annotation.PermissionAnnotation;
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.config.ProductSetting;
import com.up72.auth.service.ConfigManager;

/**
 * 权限扫描相关
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/auth/config")
@PermissionGroupAnnotation(name="权限扫描",description="")
public class AdminConfigController extends AuthController<Object, java.lang.Long> {
	@Autowired
	private ConfigManager configManager;
	
	/**
	 * 权限扫描开始
	 */
	@RequestMapping(value = "/doScan")
	@ResponseBody
	@PermissionAnnotation(name="权限扫描")
	public String index(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String,String> jsonMap = new HashMap<String, String>();
		String status = SUCCESS;
		String messsage = "操作成功";
		try{
			this.configManager.scan();
		}catch (Exception e) {
			status = ERROR;
			messsage = e.getMessage();
		}
		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, messsage);
		return jsonUtil.object2Json(jsonMap);
	}
	
	/**
	 * 产品权限配置
	 */
	@RequestMapping(value = "/setting")
	public String setting(ModelMap model, HttpServletRequest request,
			HttpServletResponse response){
		model.addAttribute("settingList", this.configManager.getProductSetting());
		return "/admin/auth/config/setting";
	}
	
	@RequestMapping(value = "/doSaveSetting")
	@ResponseBody
	public String doSaveSetting(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Map<String,String> jsonMap = new HashMap<String, String>();
		String status = SUCCESS;
		String messsage = "操作成功";
		try{
			this.configManager.saveProductSetting(this.getProductSettingList(request));
		}catch (Exception e) {
			status = ERROR;
			messsage = e.getMessage();
		}
		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, messsage);
		return jsonUtil.object2Json(jsonMap);
	}
	
	private List<ProductSetting> getProductSettingList(HttpServletRequest request){
		List<ProductSetting> result = new LinkedList<ProductSetting>();
		String[] indexs = request.getParameterValues("index");
		if(null!=indexs && indexs.length>0){
			for(int i=0;i<indexs.length;i++){
				String code = paramUtils.getParameter(request,"code_"+indexs[i]);
				String name = paramUtils.getParameter(request,"name_"+indexs[i]);
				String pvckage = paramUtils.getParameter(request,"pvckage_"+indexs[i]);
				Long sortId = paramUtils.getLongParameter(request,"sortId_"+indexs[i],0L);
				String imgPath = paramUtils.getParameter(request,"imgPath_"+indexs[i]);
				Integer status = paramUtils.getIntParameter(request,"status_"+indexs[i],2);
				String description = paramUtils.getParameter(request,"description_"+indexs[i]);
				
				ProductSetting setting = new ProductSetting();
				setting.setCode(code);
				setting.setName(name);
				setting.setPvckage(pvckage);
				setting.setSortId(sortId);
				setting.setImgPath(imgPath);
				setting.setStatus(status);
				setting.setDescription(description);
				
				result.add(setting);
			}
		}
		
		return result;
	}
}
