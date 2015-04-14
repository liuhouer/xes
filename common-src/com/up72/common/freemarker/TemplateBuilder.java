package com.up72.common.freemarker;

import static com.up72.common.CommonUtils.fileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarker 页面静态化处理类
 * 
 * @author wqtan
 */
public class TemplateBuilder {
	private String templateEncoding = "UTF-8";
	private String templateRootPath = "C:/freemarker/";
	private String configurationEncoding = "UTF-8";
	private String builderEncoding = "UTF-8";
	private Locale locale;

	private Configuration cfg;

	/**
	 * 根据指定的configuration创建TemplateBuilder
	 * 
	 * @param configuration
	 */
	protected TemplateBuilder(Configuration cfg) {
		this.cfg = cfg;
		this.locale = Locale.getDefault();
	}

	/**
	 * 根据指定的configuration创建TemplateBuilder
	 * 
	 * @param configuration
	 */
	protected TemplateBuilder(Configuration cfg, Locale locale) {
		this.cfg = cfg;
		this.locale = locale;
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 */
	protected TemplateBuilder(String templateRootPath) {
		this.templateRootPath = templateRootPath;
		this.locale = Locale.getDefault();
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @param locale
	 *            国际化的地区对象
	 */
	protected TemplateBuilder(String templateRootPath, Locale locale) {
		this.templateRootPath = templateRootPath;
		this.locale = locale;
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @param builderEncoding
	 *            生成页面编码
	 */
	protected TemplateBuilder(String templateRootPath, String builderEncoding) {
		this.templateRootPath = templateRootPath;
		this.builderEncoding = builderEncoding;
		this.locale = Locale.getDefault();
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @param builderEncoding
	 *            生成页面编码
	 * @param locale
	 *            国际化的地区对象
	 */
	protected TemplateBuilder(String templateRootPath, String builderEncoding,
			Locale locale) {
		this.templateRootPath = templateRootPath;
		this.builderEncoding = builderEncoding;
		this.locale = locale;
	}

	/**
	 * 根据给定的模板页面，生成一个页面
	 * 
	 * @author wqtan
	 * @param models
	 *            生成页面需要的数据
	 * @param templatePage
	 *            模板页面路径
	 * @param savePage
	 *            保存页面路径
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void buildFileByTemplate(Map<String, Object> models,
			String templatePage, String savePage) throws TemplateException,
			IOException {
		Configuration cfg = this.getConfiguration();
		Template temp = cfg.getTemplate(templatePage, this.locale,
				this.templateEncoding);
		
		fileUtil.createFile(savePage);
		Writer out = new OutputStreamWriter(new FileOutputStream(savePage),
				this.builderEncoding);
		temp.process(models, out);
		out.flush();
		out.close();
	}

	/**
	 * 根据给定的模板页面，生成一个页面
	 * 
	 * @author wqtan
	 * @param models
	 *            生成页面需要的数据
	 * @param templatePage
	 *            模板页面路径
	 * @param savePage
	 *            保存页面路径
	 * @return 生成的html代码
	 */
	public String buildByTemplate(Map<String, Object> models,
			String templatePage) throws TemplateException, IOException {
		Configuration cfg = this.getConfiguration();

		Template temp = cfg.getTemplate(templatePage, this.locale,
				this.templateEncoding);

		StringWriter out = new StringWriter();
		temp.process(models, out);
		out.flush();
		String result = out.toString();
		out.close();
		return result;
	}

	/**
	 * 根据给定的模板页面，生成一个页面
	 * 
	 * @author wqtan
	 * @param models
	 *            生成页面需要的数据
	 * @param templatePage
	 *            模板页面路径
	 * @param savePage
	 *            保存页面路径
	 * @return 执行结果，1为成功，2为TemplateException，3为IOException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void buildFileByContent(Map<String, Object> models,
			String templateContent, String savePage) throws IOException,
			TemplateException {
		Configuration cfg = this.getConfiguration();

		StringTemplateLoader stl = new StringTemplateLoader();
		stl.putTemplate("defaultTemplateLoader", templateContent);
		cfg.setTemplateLoader(stl);

		Template temp = cfg.getTemplate("defaultTemplateLoader", this.locale,
				this.templateEncoding);
		
		fileUtil.createFile(savePage);
		Writer out = new OutputStreamWriter(new FileOutputStream(savePage),
				this.builderEncoding);
		temp.process(models, out);
		out.flush();
		out.close();
	}

	/**
	 * 根据给定的模板页面，生成一个页面
	 * 
	 * @author wqtan
	 * @param models
	 *            生成页面需要的数据
	 * @param templatePage
	 *            模板页面路径
	 * @param savePage
	 *            保存页面路径
	 * @return 执行结果，1为成功，2为TemplateException，3为IOException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String buildByContent(Map<String, Object> models,
			String templateContent) throws IOException, TemplateException {
		Configuration cfg = this.getConfiguration();

		StringTemplateLoader stl = new StringTemplateLoader();
		stl.putTemplate("defaultTemplateLoader", templateContent);
		cfg.setTemplateLoader(stl);

		Template temp = cfg.getTemplate("defaultTemplateLoader", this.locale,
				this.templateEncoding);

		StringWriter out = new StringWriter();
		temp.process(models, out);
		out.flush();
		String result = out.toString();
		out.close();
		return result;
	}

	/**
	 * 获得模板的Configuration对象
	 * 
	 * @return Configuration
	 * @throws IOException
	 */
	protected Configuration getConfiguration() throws IOException {
		if (null == this.cfg) {
			this.cfg = new Configuration();
			this.cfg.setDefaultEncoding(this.configurationEncoding);
			this.cfg.setDirectoryForTemplateLoading(new File(this.templateRootPath));
			this.cfg.setObjectWrapper(new DefaultObjectWrapper());
		}
		return this.cfg;
	}
	
	protected void reset() throws IOException {
		this.cfg = new Configuration();
		this.cfg.setDefaultEncoding(this.configurationEncoding);
		this.cfg.setDirectoryForTemplateLoading(new File(this.templateRootPath));
		this.cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	public static void main(String[] args) throws TemplateException,
			IOException {
		String testPath = "C:/freemarker/";
		TemplateBuilder tbu = new TemplateBuilder(testPath);

		Map<String, Object> models = new HashMap<String, Object>();
		models.put("name", "嗬嗬嗬嗬 ~");
		String templatePage = "template.ftl";
		String savePage = testPath + "builder.html";

		tbu.buildFileByTemplate(models, templatePage, savePage);
		// String str = tbu.buildByTemplate(models, templatePage);
		// System.out.println(str);

		// System.out.println(content);
		// tbu.buildFileByContent(models, "sdfdfkl${(name)!}", savePage);
		String str = tbu.buildByContent(models, "sdfdfkl${(name)!}");
		System.out.println(str);
	}

	public String getTemplateEncoding() {
		return templateEncoding;
	}

	public void setTemplateEncoding(String templateEncoding) throws IOException {
		this.templateEncoding = templateEncoding;
		this.reset();
	}

	public String getTemplateRootPath() {
		return templateRootPath;
	}

	public void setTemplateRootPath(String templateRootPath) throws IOException {
		this.templateRootPath = templateRootPath;
		this.reset();
	}

	public String getConfigurationEncoding() {
		return configurationEncoding;
	}

	public void setConfigurationEncoding(String configurationEncoding) throws IOException {
		this.configurationEncoding = configurationEncoding;
		this.reset();
	}

	public String getBuilderEncoding() {
		return builderEncoding;
	}

	public void setBuilderEncoding(String builderEncoding) throws IOException {
		this.builderEncoding = builderEncoding;
		this.reset();
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) throws IOException {
		this.locale = locale;
		this.reset();
	}

	public Configuration getCfg() {
		return this.cfg;
	}

	public void setCfg(Configuration cfg) throws IOException {
		this.cfg = cfg;
		this.reset();
	}

}
