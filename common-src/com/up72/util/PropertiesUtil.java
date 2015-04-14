package com.up72.util;
/**  
 * 功能说明
 * @author jhe
 * @link <a href="http://www.up72.com">北京开拓明天科技有限公司</a>
 * @summary Properties工具类
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

public class PropertiesUtil {
	boolean isSearchSystemProperty = false;
	
	Properties p;
	
	public PropertiesUtil(Properties p) {
		this.p = p;
	}

	public PropertiesUtil(Properties p,boolean isSearchSystemProperty) {
		this.p = p;
		this.isSearchSystemProperty = isSearchSystemProperty;
	}
	
	public Properties getProperties() {
		return p;
	}
	
	public String getProperty(String key, String defaultValue) {
		String value = null;
		if(isSearchSystemProperty) {
			value = System.getProperty(key);
		}
		if(value == null || "".equals(value.trim())) {
			value = getProperties().getProperty(key);
		}
		return value == null || "".equals(value.trim()) ? defaultValue : value;
	}
	
	public String getProperty(String key) {
		return getProperty(key,null);
	}
	
	public String getRequiredProperty(String key) {
		String value = getProperty(key);
		if(value == null || "".equals(value.trim())) {
			throw new IllegalStateException("required property is blank by key="+key);
		}
		return value;
	}
	
	public Integer getInt(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Integer.parseInt(getRequiredProperty(key));
	}
	
	public int getInt(String key,int defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Integer.parseInt(getRequiredProperty(key));
	}
	
	public int getRequiredInt(String key) {
		return Integer.parseInt(getRequiredProperty(key));
	}
	
	public Boolean getBoolean(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Boolean.parseBoolean(getRequiredProperty(key));
	}
	
	public boolean getBoolean(String key,boolean defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Boolean.parseBoolean(getRequiredProperty(key));
	}
	
	public boolean getRequiredBoolean(String key) {
		return Boolean.parseBoolean(getRequiredProperty(key));
	}
	
	public String getNullIfBlank(String key) {
		String value = getProperty(key);
		if(value == null || "".equals(value.trim())) {
			return null;
		}
		return value;
	}
	
	public PropertiesUtil setProperty(String key,String value) {
		p.setProperty(key, value);
		return this;
	}

	public void clear() {
		p.clear();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return p.entrySet();
	}

	public Enumeration<?> propertyNames() {
		return p.propertyNames();
	}
	
	/**
	 * 加载所有Propertie配置文件属性
	 * @param properties properties对象
	 * @param isSearchSystemProperty 是否开启搜索系统属性
	 * @param resourceNames Properties文件名
	 * @author jhe
	 * @return 返回PropertiesUtil对象
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static PropertiesUtil loadAllPropertiesFromClassLoader(Properties properties ,boolean isSearchSystemProperty, String... resourceNames) throws IOException {
		for(String resourceName : resourceNames) {
			Enumeration urls = PropertiesUtil.class.getClassLoader().getResources(resourceName);
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				InputStream input = null;
				try {
					URLConnection con = url.openConnection();
					con.setUseCaches(false);
					input = con.getInputStream();
					if(resourceName.endsWith(".xml")){
						properties.loadFromXML(input);
					}else {
						properties.load(input);
					}
				}
				finally {
					if (input != null) {
						input.close();
					}
				}
			}
		}
		return new PropertiesUtil(properties,isSearchSystemProperty);
	}
	
	/**
	 * 加载所有Propertie配置文件属性
	 * @param resourceNames Properties文件名
	 * @param properties properties对象
	 * @author jhe
	 * @return 返回PropertiesUtil对象
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static PropertiesUtil loadAllPropertiesFromClassLoader(Properties properties , String... resourceNames) throws IOException {
		return loadAllPropertiesFromClassLoader(properties, false, resourceNames);
	}
	
	
	/**
	 * 加载所有Propertie配置文件属性
	 * @param isSearchSystemProperty 是否开启搜索系统属性
	 * @param resourceNames Properties文件名
	 * @author jhe
	 * @return 返回PropertiesUtil对象
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static PropertiesUtil loadAllPropertiesFromClassLoader(boolean isSearchSystemProperty,String... resourceNames) throws IOException {
		return loadAllPropertiesFromClassLoader(new Properties(), isSearchSystemProperty, resourceNames);
	}
	
	/**
	 * 加载所有Propertie配置文件属性
	 * @param resourceNames Properties文件名
	 * @author jhe
	 * @return 返回PropertiesUtil对象
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static PropertiesUtil loadAllPropertiesFromClassLoader(String... resourceNames) throws IOException {
		return loadAllPropertiesFromClassLoader(new Properties(), false, resourceNames);
	}
	
	/**
	 * 更新Properties文件
	 * @param resourceNames Properties文件名
	 * @author jhe
	 * @return 返回PropertiesUtil对象
	 * @throws IOException 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static void save(Properties properties,String resourceNames) throws IOException {
		Enumeration urls = PropertiesUtil.class.getClassLoader().getResources(resourceNames);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			String path = url.getFile();
			OutputStream output = null;
			try {
				output = new FileOutputStream(path);
				properties.save(output, "allSet.properties");
			}
			finally {
				if (output != null) {
					output.close();
				}
			}
		}
	}
}
