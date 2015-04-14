package com.up72.common.freemarker;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 模版内容生成工具类
 * 
 * @author xxh
 * @link 
 * 
 * @version $Revision: 1.00 $ $Date: 2009-07-16
 */

public class CreateHtml {
	
	/**
	 * xxh 将源对象的属性值copy到目标对象，只copy当前表单的元素
	 * @param content 模版内容字符串
	 * @param beanName 对象名
	 * @param obj 对象
	 * @param root 需生成的值对
	 * @return String 生成后的内容
	 * */
	public static String create(String content, String beanName, Object obj, Map root)
	{
		
		if(root == null) return "";
		
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new StringTemplateLoader(content));
		cfg.setDefaultEncoding("UTF-8");

		Template template;
		try {
			template = cfg.getTemplate("");
			//Map root = new HashMap();
			root.put(beanName, obj);


			StringWriter writer = new StringWriter();
			template.process(root, writer);
			String str = writer.toString();
			writer.close();
			return str;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * xxh 生成配置文件
	 * @param content 模版内容字符串
	 * @param beanName 对象名
	 * @param obj 对象
	 * @param root 需生成的值对
	 * @return String 生成后的内容
	 * */
	public static String createXmlString(String content, Map root)
	{
		
		if(root == null) return "";
		
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new StringTemplateLoader(content));
		cfg.setDefaultEncoding("UTF-8");

		Template template;
		try {
			template = cfg.getTemplate("");

			StringWriter writer = new StringWriter();
			template.process(root, writer);
			String str = writer.toString();
			writer.close();
			return str;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	
	public static List<String> getConfigedKeys(List<String> keys, List<String> values)
	{
		List<String> configKeys = new ArrayList<String>();
		for(int i = 0; i < values.size(); i++)
		{
			if(values.get(i).equals("1"))
			{
				configKeys.add(keys.get(i));
			}
			//System.out.println(keys.get(i) + "=" + values.get(i));
		}
		
		return configKeys;
		
	}
	
	public  static  void main(String args[])
	{
//		Map map = new HashMap();
//		//map.put("name", "ddd");
//		OSellIn sellIn = new OSellIn();
//		sellIn.setK1("dkjf");
//		System.out.println(create("${(sellIn.k1)!}","sellIn",sellIn, map));
//		
	}
}
