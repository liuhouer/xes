package com.up72.util;

import static com.up72.common.CommonUtils.dateUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.up72.common.CommonConstants;
import com.up72.exception.ServiceException;
import com.up72.framework.util.holder.ApplicationContextHolder;


public class SystemUtil {
	protected Log log = LogFactory.getLog(getClass());
	private Connection conn = null;

	/* 资源解析器 */
	private static ResourcePatternResolver RESOLVER = null;
	/* Meta信息Reader Factory.用于创建MetaReader */
	private static MetadataReaderFactory READER_FACTORY = null;
	
	public SystemUtil() {
		RESOLVER = new PathMatchingResourcePatternResolver();
		READER_FACTORY = new SimpleMetadataReaderFactory();
		if (conn == null) {
			try{
			conn = DataSourceUtils
					.getConnection((DataSource) ApplicationContextHolder
							.getBean("dataSource"));
			}catch (Exception e) {
				log.error(e);
			}
		}
	}
	
	public void getSystemInfo(Map<String, Object> result) {
		Sigar sigar = new Sigar();
		OperatingSystem system = OperatingSystem.getInstance();
		result.put("操作系统", system.getDescription() + " "
				+ system.getPatchLevel() + " " + system.getArch() + " "
				+ system.getDataModel() + "位");
		CpuPerc perc = null;
		try {
			perc = sigar.getCpuPerc();
		} catch (SigarException e) {
			throw new ServiceException(e);
		}
		CpuInfo cpuInfo;
		try {
			cpuInfo = sigar.getCpuInfoList()[0];
		} catch (SigarException e) {
			throw new ServiceException(e);
		}
		Mem mem;
		try {
			mem = sigar.getMem();
		} catch (SigarException e) {
			throw new ServiceException(e);
		}
		result.put("CPU", cpuInfo.getVendor() + " " + cpuInfo.getTotalCores()
				+ "核 " + cpuInfo.getMhz() / 1000 + "GHz, 当前占用"
				+ NumberFormat.getPercentInstance().format(perc.getCombined()));
		result.put("内存", "共" + mem.getTotal() / (1024l * 1024l) + "M , 剩余"
				+ mem.getFree() / (1024l * 1024l) + "M");
		result.put("JDK", System.getProperty("java.vendor") + " "
				+ System.getProperty("java.version"));
		result.put("JAVA_HOME", System.getProperty("java.home"));
		try {
			result.put("数据库", conn.getMetaData().getDatabaseProductName() + " "
					+ conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 系统信息
	 * @author wgf
	 * @return
	 */
	public Map<String, Object> getSystemInfo() {
		Map<String, Object> result = new HashMap<String, Object>();
		Sigar sigar = new Sigar();
		//OperatingSystem system = OperatingSystem.getInstance();
		CpuPerc perc = null;
		try {
			perc = sigar.getCpuPerc();
		} catch (SigarException e) {
			throw new ServiceException(e);
		}
		CpuInfo cpuInfo;
		try {
			cpuInfo = sigar.getCpuInfoList()[0];
		} catch (SigarException e) {
			throw new ServiceException(e);
		}
		Mem mem;
		try {
			mem = sigar.getMem();
		} catch (SigarException e) {
			throw new ServiceException(e);
		}
		/*操作系统信息*/
		result.put("osName", System.getProperty("os.name","unknown"));
		result.put("osVersion",System.getProperty("os.version","unknown"));
		result.put("userLanguage",System.getProperty("user.language","unknown"));
		/*CPU信息*/
		result.put("cpuInfo", cpuInfo.getVendor() + " " + cpuInfo.getTotalCores()+ "核 " + cpuInfo.getMhz() / 1000 + "GHz");
		result.put("cpuRunTime","当前占用 "+ NumberFormat.getPercentInstance().format(perc.getCombined()));
		/*内存信息*/
		result.put("mem", "共" + mem.getTotal() / (1024l * 1024l) + "M , 剩余"+ mem.getFree() / (1024l * 1024l) + "M");
		/*JDK信息*/
		result.put("jdkVendor", System.getProperty("java.vendor","unknown"));
		result.put("jdkVersion", System.getProperty("java.version","unknown"));
		result.put("java_home", System.getProperty("java.home","unknown"));
		result.put("timeZone", TimeZone.getDefault().getID());
		/*数据库信息*/
		try {
			result.put("DBname", conn.getMetaData().getDatabaseProductName());
			result.put("DBversion",conn.getMetaData().getDatabaseProductVersion());
			result.put("DBuser",conn.getMetaData().getUserName());
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
		/*应用信息 begin*/
		String projectCode = "";
		String projectName = "";
		String projectVersion = "";
		String projectStarTime = "";
		String projectLastUpdateTime = "";
		String projectisDebug = "";
		try {
			projectCode = PropertiesUtil.loadAllPropertiesFromClassLoader(CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(CommonConstants.sysConfig.PROJECT_CODE_KEY);
			projectName = PropertiesUtil.loadAllPropertiesFromClassLoader(CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(CommonConstants.sysConfig.PROJECT_NAME_KEY);
			projectVersion = PropertiesUtil.loadAllPropertiesFromClassLoader(CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(CommonConstants.sysConfig.PROJECT_VERSION_KEY);
			String longProjectStarTime = PropertiesUtil.loadAllPropertiesFromClassLoader(CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(CommonConstants.sysConfig.PROJECT_STARTIME_KEY);
			String longProjectLastUpdateTime = PropertiesUtil.loadAllPropertiesFromClassLoader(CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(CommonConstants.sysConfig.PROJECT_LASTUPDATETIME_KEY);
			if(!"".equals(longProjectStarTime)){
				projectStarTime = dateUtils.formatLongDate(Long.parseLong(longProjectStarTime));
			}
			if(!"".equals(longProjectLastUpdateTime)){
				projectLastUpdateTime = dateUtils.formatLongDate(Long.parseLong(longProjectLastUpdateTime));
			}
			projectisDebug = PropertiesUtil.loadAllPropertiesFromClassLoader(CommonConstants.sysConfig.SYSTEM_CONFIG).getProperty(CommonConstants.sysConfig.PROJECT_ISDEBUG_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		result.put("projectCode", projectCode);
		result.put("projectName", projectName);
		result.put("projectVersion", projectVersion);
		result.put("projectStarTime", projectStarTime);
		result.put("projectLastUpdateTime", projectLastUpdateTime);
		result.put("projectisDebug", projectisDebug);
		/*应用信息 end*/
		return result;
	}
	public Resource[] getResources(String scanPath){
		try {
			return RESOLVER.getResources(scanPath);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}
	
	public MetadataReader getMetadataReader(Resource res){
		try {
			return READER_FACTORY.getMetadataReader(res);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}
}
