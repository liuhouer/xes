package com.up72.common.excel;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ImportExportXML {
	public static boolean DEBUG = true;
	
	public static String READ_XML_ENCODING = "UTF-8";
	public static String WRITE_XML_ENCODING = "UTF-8";

	/**
	 * 读取xml配置文件，获得配置信息
	 * 
	 * @param xmlPath
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static List<ColumnConfig> readConfigList(String xmlPath) {
		SAXReader reader = new SAXReader();
		List<ColumnConfig> result = null;
		try {
			Document document = reader.read(new File(xmlPath));
			Element root = document.getRootElement();
			List<Element> list = root.elements("column");
			if(null != list 
					&& list.size() > 0){
				result = new ArrayList<ColumnConfig>();
				for(int i=0;i<list.size();i++){
					Element config = list.get(i);
					ColumnConfig c = getColumnConfig(config);
					result.add(c);
				}
			}
		} catch (DocumentException e) {
			debug(e);
		}

		return result;
	}
	
	private static ColumnConfig getColumnConfig(Element config){
		ColumnConfig result = new ColumnConfig();
		Attribute aId = config.attribute("id");
		result.setAttribute((null == aId?null:aId.getValue()));
		
		Attribute aName = config.attribute("name");
		result.setExportName((null == aName?null:aName.getValue()));
		
		Attribute aWidth = config.attribute("width");
		result.setWidth(stringUtil.parseShort((null==aWidth?null:aWidth.getValue())));
		
		Attribute aParseHTML = config.attribute("parseHTML");
		result.setParseHTML(stringUtil.parseBoolean((null==aParseHTML?null:aParseHTML.getValue())));
		
		Element aDateFormat = config.element("dateFormat");
		String pattern = (null == aDateFormat?"":aDateFormat.getText());
		if(null != pattern
				&& !pattern.trim().equals("")){
			result.setDateFormat(new SimpleDateFormat(pattern));
		}
		if(null != config.element("dictionary")){
			result.setDictionary(getDictionary(config.element("dictionary")));
		}
		return result;
	}
	
	@SuppressWarnings({"unchecked"})
	private static Map<String,String> getDictionary(Element dic){
		Map<String,String> result = null;
		List<Element> list = dic.elements("map");
		if(null != list
				&& list.size() > 0){
			result = new HashMap<String, String>(); 
			for(int i=0;i<list.size();i++){
				Element map = list.get(i);
				Attribute aKey = map.attribute("key");
				if(null != aKey
						&& null != aKey.getValue()
						&& !aKey.getValue().trim().equals("")){
					String key = aKey.getValue();
					String value = map.getText();
					result.put(key, value);
				}
			}
		}
		return result;
	}

	/**
	 * 写入xml配置文件，存储配置信息
	 * 
	 * @param xmlPath
	 * @return
	 */
	public static void writeConfigList(String xmlPath,
			List<ColumnConfig> configList) {
		if (null != xmlPath
				&& !xmlPath.trim().equals("")
				&& null != configList 
				&& configList.size() > 0) {
			Document document = DocumentHelper.createDocument();
			
			Element root = document.addElement("config");
			for (int i = 0; i < configList.size(); i++) {
				ColumnConfig c = configList.get(i);
				Element config = root.addElement("column");
				
				if(null != c.getAttribute()
						&& !c.getAttribute().trim().equals("")){
					config.addAttribute("id", c.getAttribute().trim());
				}
				
				if(null != c.getExportName()
						&& !c.getExportName().trim().equals("")){
					config.addAttribute("name", c.getExportName().trim());
				}
				
				if(null != c.getWidth()){
					config.addAttribute("width", Integer.toString(c.getWidth()));
				}
				
				if(null != c.isParseHTML()){
					config.addAttribute("parseHTML", Boolean.toString(c.isParseHTML()));
				}
				
				String dateFormat = getDateFormtPattern(c.getDateFormat());
				if(null != dateFormat
						&& !dateFormat.trim().equals("")){
					config.addElement("dateFormat").addText(dateFormat);
				}
				
				if(null != c.getDictionary()
						&& c.getDictionary().size() > 0){
					addDictionary(config, c.getDictionary());
				}
			}
			if(xmlPath.indexOf("\\")!=-1){
				xmlPath = xmlPath.replaceAll("\\", "/");
			}
			
			String savePath = xmlPath.substring(0,xmlPath.lastIndexOf("/"));
			File file = new File(savePath);
			if(!file.exists()){
				file.mkdirs();
			}
			OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
			xmlFormat.setEncoding(WRITE_XML_ENCODING);
			try{
				XMLWriter writer = new XMLWriter(new FileOutputStream(new File(xmlPath)),xmlFormat);
				writer.write(document);
				writer.close();
			}catch (IOException e) {
				debug(e);
			}
		}else{
			debug(new Exception("传入的xml文件为空，或者要保存的配置信息为空!"));
		}
	}
	
	private static void addDictionary(Element config,Map<String,String> dic){
		Iterator<String> it = dic.keySet().iterator();
		Element dictionary = config.addElement("dictionary");
		while(it.hasNext()){
			String key = it.next();
			String value = dic.get(key);
			dictionary.addElement("map").addAttribute("key", key).addText(value);
		}
	}
	
	private static String getDateFormtPattern(DateFormat df){
		String result = null;
		if(null != df && (df instanceof SimpleDateFormat)){
			SimpleDateFormat sdf = (SimpleDateFormat)df;
			result = sdf.toPattern();
		}
		return result;
	}
	
	/**
	 * 获得系统中导入导出配置文件
	 */
	@SuppressWarnings({"unchecked"})
	public static List<ActionConfig> getActionConfigList(String filePath){
		List<ActionConfig> result = null;
		
		String config = filePath;
		
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File(config));
			Element root = document.getRootElement();
			List<Element> list = root.elements("bean");
			if(null != list && list.size() > 0){
				result = new ArrayList<ActionConfig>();
				for(int i=0;i<list.size();i++){
					Element ea = list.get(i);
					if(null != ea){
						ActionConfig ac = new ActionConfig();
						Attribute id = ea.attribute("id");
						if(null != id
								&& null != id.getValue()){
							ac.setId(id.getValue().trim());
						}
						Attribute name = ea.attribute("name");
						if(null != name
								&& null != name.getValue()){
							ac.setName(name.getValue().trim());
						}
						Attribute action = ea.attribute("action");
						if(null != action
								&& null != action.getValue()){
							ac.setAction(action.getValue().trim());
						}
						result.add(ac);
					}
				}
			}
		} catch (DocumentException e) {
			debug(e);
		}
		
		return result;
	}
	
	
	/**
	 * 获得xml的map，key为id，value为name，一般是做数据字典配置
	 * @param key xml节点名
	 * @return Map<String,String>
	 */
	@SuppressWarnings({"unchecked"})
	public static Map<String,String> getXMLMaps(String xmlPath,String key){
		Map<String,String> result = null;
		if(null !=xmlPath
				&& !xmlPath.trim().equals("")
				&& null != key 
				&& !key.trim().equals("")){
			result = new HashMap<String, String>();
			SAXReader reader = new SAXReader();
			try {
				Document document = reader.read(new File(xmlPath));
				Element root = document.getRootElement();
				List<Element> list = root.elements(key);
				if(null != list 
						&& list.size() > 0){
					for(int i=0;i<list.size();i++){
						Element e = list.get(i);
						Attribute attrId = e.attribute("id");
						Attribute attrName = e.attribute("name");
						if(null != attrId
								&& null != attrId.getValue()
								&& !attrId.getValue().trim().equals("")){
							result.put(attrId.getValue().trim(), ((null == attrName||null==attrName.getValue())?
									null : attrName.getValue().trim()));
						}
					}
				}
			} catch (DocumentException e) {
				debug(e);
			}
		}
		return result;
	}
	
	private static void debug(Exception e){
		if(DEBUG){
			e.printStackTrace();
		}
	}
	
	public static void testWriteConfig(){
		String file = "D:/workspace/fluke/conf/ServiceProduct.xml";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<ColumnConfig> configList = new ArrayList<ColumnConfig>();
		configList.add(new ColumnConfig("serviceOrderNo", "维修单号"));
		
		ColumnConfig rd = new ColumnConfig("receivedDate","到货日期");
		rd.setDateFormat(dateFormat);
		configList.add(rd);
		
		ColumnConfig pt = new ColumnConfig("productType","产品型号");
		configList.add(pt);
		
		configList.add(new ColumnConfig("productQty","实收数量"));
		configList.add(new ColumnConfig("productSeriesNo","序列号"));
		
		ColumnConfig sqd = new ColumnConfig("serviceQuoteDate","报价日期");
		sqd.setDateFormat(dateFormat);
		configList.add(sqd);
		
		ColumnConfig ocd = new ColumnConfig("orderConfirmDate","批准日期");
		ocd.setDateFormat(dateFormat);
		configList.add(ocd);
		
		ColumnConfig cd = new ColumnConfig("completedDate","修好日期");
		cd.setDateFormat(dateFormat);
		configList.add(cd);
		
		ColumnConfig spd = new ColumnConfig("shipmentDate","发货日期");
		spd.setDateFormat(dateFormat);
		configList.add(spd);
		
		configList.add(new ColumnConfig("tntNo","运单号"));
		configList.add(new ColumnConfig("serviceSummary","维修报告"));
		
		ColumnConfig esqd = new ColumnConfig("estServiceQuoteDate","预计报价日期");
		esqd.setDateFormat(dateFormat);
		configList.add(esqd);
		
		ColumnConfig eocd = new ColumnConfig("estOrderConfirmDate","预计修好日期");
		eocd.setDateFormat(dateFormat);
		configList.add(eocd);
		
		ColumnConfig ecd = new ColumnConfig("estCompletedDate","预计发货日期");
		ecd.setDateFormat(dateFormat);
		configList.add(ecd);
		
		writeConfigList(file, configList);
	}
	
	public static void testReadConfig(){
		String file = "D:/workspace/fluke/conf/ServiceProduct.xml";
		List<ColumnConfig> list = readConfigList(file);
		if(null != list 
				&& list.size() > 0){
			for(int i=0;i<list.size();i++){
				ColumnConfig c = list.get(i);
				System.out.println(c.getAttribute()+"\t"+c.getExportName()+"\t"+c.getDateFormat()+"\t"+c.getDictionary());
			}
		}
	}
	
	public static void testActionConfig(){
		//String fileName = Constants.ROOTPATH+Constants.IMPORT_EXPORT_CONFIG_FILEPATH;
		String fileName = null;
		fileName = "D:/workspace/fluke/conf/importconfig.xml";
		List<ActionConfig> list = getActionConfigList(fileName);
		if(null != list
				&& list.size() > 0){
			for(int i=0;i<list.size();i++){
				ActionConfig ac = list.get(i);
				System.out.print(ac.getId()+"\t");
				System.out.print(ac.getName()+"\t");
				System.out.println(ac.getAction());
			}
		}
	}

	public static void main(String[] args) {
	//	testWriteConfig();
	//	testReadConfig();
	//	testActionConfig();
	//	System.out.println(getXMLMaps("D:/workspace/fluke/conf/import_export/config/exceltemp.xml","template"));
	}
}
