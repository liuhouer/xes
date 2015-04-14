package com.up72.common.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * freemarker 页面静态化处理工具类
 * 
 * @author wqtan
 */
public final class TemplateBuilderUtil {

	private static TemplateBuilder templateBuilder;

	public static TemplateBuilder newInstance(Configuration configuration) throws IOException {
		if (null == templateBuilder) {
			templateBuilder = new TemplateBuilder(configuration);
		}else {
			templateBuilder.setCfg(configuration);
		}
		return templateBuilder;
	}
	
	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @throws IOException 
	 */
	public static TemplateBuilder newInstance(String templateRootPath) throws IOException {
		if (null == templateBuilder) {
			templateBuilder = new TemplateBuilder(templateRootPath);
		}else {
			templateBuilder.setTemplateRootPath(templateRootPath);
		}
		return templateBuilder;
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @param locale
	 *            国际化的地区对象
	 * @throws IOException 
	 */
	public static TemplateBuilder newInstance(String templateRootPath,
			Locale locale) throws IOException {
		if (null == templateBuilder) {
			templateBuilder = new TemplateBuilder(templateRootPath, locale);
		}else{
			templateBuilder.setTemplateRootPath(templateRootPath);
			templateBuilder.setLocale(locale);
		}
		return templateBuilder;
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @param builderEncoding
	 *            生成页面编码
	 * @throws IOException 
	 */
	public static TemplateBuilder newInstance(String templateRootPath,
			String builderEncoding) throws IOException {
		if (null == templateBuilder) {
			templateBuilder = new TemplateBuilder(templateRootPath,
					builderEncoding);
		}else{
			templateBuilder.setTemplateRootPath(templateRootPath);
			templateBuilder.setBuilderEncoding(builderEncoding);
		}
		return templateBuilder;
	}

	/**
	 * @author wqtan
	 * @param templateRootPath
	 *            模板所在绝对路径
	 * @param builderEncoding
	 *            生成页面编码
	 * @param locale
	 *            国际化的地区对象
	 * @throws IOException 
	 */
	public static TemplateBuilder newInstance(String templateRootPath,
			String builderEncoding, Locale locale) throws IOException {
		if (null == templateBuilder) {
			templateBuilder = new TemplateBuilder(templateRootPath,
					builderEncoding, locale);
		}else{
			templateBuilder.setTemplateRootPath(templateRootPath);
			templateBuilder.setBuilderEncoding(builderEncoding);
			templateBuilder.setLocale(locale);
		}
		return templateBuilder;
	}

	public static void main(String[] args) throws TemplateException,
			IOException {
		//String testPath = "C:/freemarker/";
		String testPath = "D:/workspace/upapp/WebRoot/template/contentModel/contetnModelTemplate/";
		TemplateBuilder tbu = TemplateBuilderUtil.newInstance(testPath);

		Map<String, Object> models = new HashMap<String, Object>();
		models.put("name", "嗬嗬嗬嗬 ~");
		String templatePage = "edit.ftl";
		String savePage = testPath + "builder.html";

		tbu.buildFileByTemplate(models, templatePage, savePage);
		// String str = tbu.buildByTemplate(models, templatePage);
		// System.out.println(str);

		// System.out.println(content);
		// tbu.buildFileByContent(models, "sdfdfkl${(name)!}", savePage);
		String str = tbu.buildByContent(models, "sdfdfkl${(name)!}");
		System.out.println(str);
	}
}
