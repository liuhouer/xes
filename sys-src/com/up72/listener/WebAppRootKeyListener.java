/** configuration in web.xml get rootpath
 *  <listener>
    	<listener-class>com.up72.listener.WebAppRootKeyListener</listener-class>
  	</listener>
 */
package com.up72.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.up72.common.CommonConstants;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.SystemConfig;
import com.up72.sys.service.SystemManager;

/**
 * 系统初始化 1、设置服务器中当前WebRoot的物理路径 2、系统自动检测/修复
 * 
 * @author jhe
 * @author wqtan
 * @date 2012-03-17
 */

public class WebAppRootKeyListener extends HttpServlet implements
		ServletContextListener {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(WebAppRootKeyListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		// 初始化当前WebRoot的物理路径
		initContentPath(sce);

		// 系统检测
		systemCheck();
	}

	/**
	 * 初始化当前WebRoot的物理路径
	 * 
	 * @author jhe
	 */
	private void initContentPath(ServletContextEvent sce) {
		String rootpath = sce.getServletContext().getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\\\", "/");
		} else {
			rootpath = "/";
		}
		if (!rootpath.endsWith("/")) {
			rootpath = rootpath + "/";
		}
		CommonConstants.ROOTPATH = rootpath;
		CommonConstants.HOST = SystemConfig.instants().getValue("server.host");
		if (logger.isInfoEnabled()) {
			logger.info("InitContentPath:[ROOTPATH=" + CommonConstants.ROOTPATH
					+ ";HOST=" + CommonConstants.HOST);
		}
	}

	/**
	 * 系统检测
	 * 
	 * @author wqtan
	 */
	private void systemCheck() {
		if (logger.isInfoEnabled()) {
			logger.info("Runtime Environment checking...");
		}
		SystemManager systemManager = (SystemManager) ApplicationContextHolder
				.getBean("systemManager");
		systemManager.checkRuntime();
		systemManager.checkDatabase();
		systemManager.setStartTime();
		if (logger.isInfoEnabled()) {
			logger.info("Runtime Environment checked!");
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}
