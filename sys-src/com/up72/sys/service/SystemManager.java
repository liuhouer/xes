package com.up72.sys.service;

import static com.up72.common.CommonUtils.dateUtils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.common.CommonConstants;
import com.up72.dao.hibernate.CommonDAOHibernate;
import com.up72.util.PropertiesUtil;

/**
 * 系统检测service，检测系统可能存在的问题，以及修补
 * 
 * @author wqtan
 */
@Service
@Transactional
@Component
public class SystemManager {
	private static Logger logger = Logger.getLogger(SystemManager.class);

	@Autowired
	private CommonDAOHibernate commonDAOHibernate;

	/**
	 * 系统运行时检测
	 * 
	 * @author wqtan
	 */
	public void checkRuntime() {

	}

	/**
	 * 数据库校验 根据实体类，对比数据库字段是否一致，不一致补全
	 * 
	 * @author wqtan
	 */
	public void checkDatabase() {

	}

	/**
	 * 设置系统运行的时间
	 * 
	 * @author wgf
	 */
	public void setStartTime() {
		try {
			if (logger.isInfoEnabled()) {
				logger.info("系统启动时间:"
						+ dateUtils.formatLongDate(System.currentTimeMillis()));
			}
			Properties properties = PropertiesUtil
					.loadAllPropertiesFromClassLoader(
							CommonConstants.sysConfig.SYSTEM_CONFIG)
					.getProperties();
			properties.setProperty(
					CommonConstants.sysConfig.PROJECT_STARTIME_KEY, String
							.valueOf(System.currentTimeMillis()));
			PropertiesUtil.save(properties,
					CommonConstants.sysConfig.SYSTEM_CONFIG);
		} catch (IOException e) {
			logger.error("设置登录时间失败", e);
		}
	}

	/**
	 * 链接数据库定时，用来解决，没过8小时没有访问数据库连接池关闭问题
	 */
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void databaseAccessTimerTask() {
		if (logger.isInfoEnabled()) {
			logger.info("connect database " + dateUtils.getStrDateTime());
		}
		try {
			long count = commonDAOHibernate
					.findcount("select count(id) from com.up72.auth.model.Product");
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
