/*	模板生成数据传递
 *  ITemplateFlt t = new TemplateFlt();
 *  
 *  模板数据填充
 * 	Map root = new HashMap();
	root.put("news", news);
	
 */
package com.up72.common.freemarker;

import java.util.HashMap;
import java.util.Map;


public class TemplateFlt implements ITemplateFlt {
	
	//站点绝对路径以/结尾 如f://website/webroot/
	private String realPath;

	//模板站内绝对路径不带/开头以/结尾 如webroot/web-inf/template/cms/
	private String templatePath;
	
	//模板名称 如list.flt
	private String templateName;
	
	//文件存储站内不带/开头以/结尾 如 cms/2007-12-1/
	private String savePath;
	
	//存储文件名 如2007-12-13.html
	private String saveFileName;
		
	//数据models注入
	private Map models = new HashMap();

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#getModels()
	 */
	public Map getModels() {
		return models;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#setModels(java.util.Map)
	 */
	public void setModels(Map models) {
		this.models = models;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#getSaveFileName()
	 */
	public String getSaveFileName() {
		return saveFileName;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#setSaveFileName(java.lang.String)
	 */
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#getSavePath()
	 */
	public String getSavePath() {
		return savePath;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#setSavePath(java.lang.String)
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#getSiteRealPath()
	 */
	public String getRealPath() {
		return realPath;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#setSiteRealPath(java.lang.String)
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#getTemplatePath()
	 */
	public String getTemplatePath() {
		return templatePath;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#setTemplatePath(java.lang.String)
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#getTemplateName()
	 */
	public String getTemplateName() {
		return templateName;
	}

	/* (non-Javadoc)
	 * @see com.eemedia.service.util.ITemplateFlt#setTemplateName(java.lang.String)
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	
}
