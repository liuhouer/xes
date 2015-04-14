package com.up72.common.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class StringTemplateLoader implements TemplateLoader {
	private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";
	private Map templates = new HashMap();

	public StringTemplateLoader(String defaultTemplate) {
		if (defaultTemplate != null && !defaultTemplate.equals("")) {
			templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
		}
	}

	public void AddTemplate(String name, String template) {
		if (name == null || template == null || name.equals("")
				|| template.equals("")) {
			return;
		}
		if (!templates.containsKey(name)) {
			templates.put(name, template);
		}
	}

	public void closeTemplateSource(Object templateSource) throws IOException {

	}

	public Object findTemplateSource(String name) throws IOException {
		if (name == null || name.equals("")) {
			name = DEFAULT_TEMPLATE_KEY;
		}
		return templates.get(name);
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return new StringReader((String) templateSource);
	}

	public static void main(String[] args) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new StringTemplateLoader("<textarea name=\"${(name)!}\" id=\"${(name)!}\">@^@{( ${(beanName)!}.${(name)!})!}</textarea>"));
		cfg.setDefaultEncoding("UTF-8");
		Template template = cfg.getTemplate("");
		Map root = new HashMap();
		root.put("name", "k1");
		root.put("beanName", "oSellIn");
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		System.out.println(writer.toString());
	}

}
