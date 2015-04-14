/*
 * Created on 2012-8-28
 */
package com.up72.util;

import javax.servlet.http.HttpServletRequest;

import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.SysLogConstants;
import com.up72.sys.model.SysLogBusiness;
import com.up72.sys.service.SysLogBusinessManager;

/**
 * 操作日志工具类
 * @author baoxin.men
 */
public class SystemLogUtil {
	// debug 模式 false不记录日志 
	private boolean debug = true;
	private SysLogBusinessManager logBusinessManager = null;
	
	public SystemLogUtil(){
		try{
			if(null == logBusinessManager){
				logBusinessManager = (SysLogBusinessManager) ApplicationContextHolder.getBean("sysLogBusinessManager");
			}
		}catch (Exception e) {
			
		}
	}
	
	/**
	 * 日志-登陆成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @author baoxin.men
	 */
	public void login(String userGuid, HttpServletRequest request){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.LOGIN);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(SysLogConstants.systemLog.function.LOGIN_SYSTEM);
		entity.setDescription("");
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-登陆失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void loginError(String userGuid, HttpServletRequest request, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.LOGIN);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(SysLogConstants.systemLog.function.LOGIN_SYSTEM);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-创建成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @author baoxin.men
	 */
	public void create(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.CREATE);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-创建失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void createError(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.CREATE);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	
	/**
	 * 日志-删除成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @author baoxin.men
	 */
	public void delete(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.DELELTE);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-删除失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void deleteError(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.DELELTE);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	

	/**
	 * 日志-修改成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @author baoxin.men
	 */
	public void edit(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.EDIT);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-修改失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void editError(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.EDIT);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	

	/**
	 * 日志-退出系统成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @author baoxin.men
	 */
	public void logout(String userGuid, HttpServletRequest request){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.LOGOUT);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(SysLogConstants.systemLog.function.LOGOUT_SYSTEM);
		entity.setDescription("");
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-退出系统失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void logoutError(String userGuid, HttpServletRequest request, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.LOGOUT);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(SysLogConstants.systemLog.function.LOGOUT_SYSTEM);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-导出成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @author baoxin.men
	 */
	public void export(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.EXPORT);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-导出失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void exportError(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.EXPORT);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-导入成功
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @author baoxin.men
	 */
	public void importLog(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.IMPORT);
		entity.setResult(SysLogConstants.systemLog.result.SUCCESS);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-导入失败
	 * @param userDetails		操作用户
	 * @param ip			操作IP
	 * @param function		操作模块
	 * @param description	操作失败说明
	 * @author baoxin.men
	 */
	public void importLogError(String userGuid, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(SysLogConstants.systemLog.type.IMPORT);
		entity.setResult(SysLogConstants.systemLog.result.FAIL);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 日志-统用操作
	 * @param userDetails			操作用户
	 * @param type				操作动作（如：导入，导出，新建，删除）
	 * @param result			操作结果（成功or失败）
	 * @param ip				操作IP
	 * @param function			操作模块（如：创建计划、检查计划导出）
	 * @param description		操作说明（目前用户失败原因，比如登陆失败：用户已被禁用、用户名或密码错误）
	 * @author baoxin.men
	 */
	public void common(String userGuid, String type, String result, HttpServletRequest request, String function, String description){
		SysLogBusiness entity = new SysLogBusiness();
		entity.setUserGuid(userGuid);
		entity.setTime(System.currentTimeMillis());
		entity.setType(type);
		entity.setResult(result);
		entity.setIp(getIpAddress(request));
		entity.setFunction(function);
		entity.setDescription(description);
		if(debug){
			logBusinessManager.save(entity);
		}
	}
	
	/**
	 * 获取IP地址
	 */
	public String getIpAddress(HttpServletRequest request){
        String result = request.getHeader("x-forwarded-for");    
        if(result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {    
            result = request.getHeader("Proxy-Client-result");    
        }    
        if(result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {    
            result = request.getHeader("WL-Proxy-Client-result");    
        }    
        if(result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {    
            result = request.getRemoteAddr();
        }
        return result;    
	}
	
	/**
	 * 获取服务端IP地址
	 */
	public String getServerIpAddress(HttpServletRequest request){
		int port = request.getServerPort();
		String path = request.getContextPath();
		String result = request.getServerName();
		if(port!=80){
			result = result + ":" + port;
		}
		if(null != path && !"".equals(path.trim())){
			result = result + path;
		}
		return result;
	}
}
