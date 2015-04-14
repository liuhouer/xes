/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */


package com.up72.app.auth.controller;


import static com.up72.common.CommonUtils.jsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.auth.controller.AuthController;
import com.up72.auth.member.model.AuthUser;

/**
 * 数据编辑controller
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/data/edit")
public class AdminDataEditController extends AuthController<Object,java.lang.Long>{
	
	/**
	 * 判断用户是否具备编辑权限 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/hasPerm")
	@ResponseBody
	public String hasPerm(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		AuthUser user = this.getAdminLoginMember(request);
		Map jsonMap = new HashMap();
		String status = SUCCESS;
		String message = "";
		if(null == user){
			status = ERROR;
		}
		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, message);
		return jsonUtil.object2Json(jsonMap);
	}
	
	
}

