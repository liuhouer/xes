package com.up72.auth.config;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.up72.common.CommonConstants;
import com.up72.exception.UtilException;

public class SettingXmlParseHelper {
	private static final String XML_PATH = "auth-setting.xml";
	
	private static final String XML_ROOT = "auth";
	private static final String E_PRODUCTS = "products";
	private static final String E_PRODUCT = "product";
	private static final String P_A_CODE = "code";
	private static final String P_A_NAME = "name";
	private static final String P_A_PACKAGE = "package";
	private static final String P_E_SORT_ID= "sort-id";
	private static final String P_E_IMG_PATH= "img-path";
	private static final String P_E_DESCRIPTION= "description";
	private static final String P_E_STATUS= "status";
	
	static String XML_ABSOLUTE_PATH = stringUtil.parseToPath(CommonConstants.ROOTPATH+"/WEB-INF/classes/"+XML_PATH);
	
	public static List<ProductSetting> getProductSettings(){
		List<ProductSetting> result = null;
		
		try {
			Document document = new SAXReader().read(new File(XML_ABSOLUTE_PATH));
			Element root = document.getRootElement();
			Element productsE = root.element(E_PRODUCTS);
			List<Element> products = productsE.elements(E_PRODUCT);
			if(null!=products && !products.isEmpty()){
				result = new LinkedList<ProductSetting>();
				for(int i=0;i<products.size();i++){
					Element product = products.get(i);
				 	Attribute code = product.attribute(P_A_CODE);
				 	Attribute name = product.attribute(P_A_NAME);
				 	Attribute pvckage = product.attribute(P_A_PACKAGE);
				 	
				 	Element sortId = product.element(P_E_SORT_ID);
				 	Element imgPath = product.element(P_E_IMG_PATH);
				 	Element description = product.element(P_E_DESCRIPTION);
				 	Element status = product.element(P_E_STATUS);
				 	
				 	ProductSetting ps = new ProductSetting();
				 	if(code != null){
				 		ps.setCode(code.getValue());
				 	}

				 	if(name != null){
				 		ps.setName(name.getValue());
				 	}

				 	if(pvckage != null){
				 		ps.setPvckage(pvckage.getValue());
				 	}
				 	
				 	if(sortId != null){
				 		ps.setSortId(stringUtil.parseLong(sortId.getTextTrim()));
				 	}
				 	
				 	if(imgPath != null){
				 		ps.setImgPath(imgPath.getTextTrim());
				 	}
				 	
				 	if(description != null){
				 		ps.setDescription(description.getTextTrim());
				 	}
				 	
				 	if(status != null){
				 		ps.setStatus(stringUtil.parseInt(status.getTextTrim()));
				 	}
				 	
				 	result.add(ps);	
				}
			}
			
		} catch (DocumentException e) {
			throw new UtilException(e);
		}
		
		return result;
	}
	
	public static void addProductSetting(Element root,ProductSetting productSetting){
		if(null==productSetting 
				|| null==productSetting.getName() || productSetting.getName().trim().equals("")
				|| null==productSetting.getPvckage() || productSetting.getPvckage().trim().equals("")){
			return ;
		}
		Element product = root.addElement(E_PRODUCT);
		if(null!=productSetting.getCode()){
			product.addAttribute(P_A_CODE, productSetting.getCode());
		}
		product.addAttribute(P_A_NAME, productSetting.getName());
		product.addAttribute(P_A_PACKAGE, productSetting.getPvckage());
		if(null!=productSetting.getSortId()){
			product.addElement(P_E_SORT_ID).addText(productSetting.getSortId().toString());
		}
		if(null!=productSetting.getStatus()){
			product.addElement(P_E_STATUS).addText(productSetting.getStatus().toString());
		}
		if(null!=productSetting.getImgPath()){
			product.addElement(P_E_IMG_PATH).addText(productSetting.getImgPath());
		}
		if(null!=productSetting.getDescription()){
			product.addElement(P_E_DESCRIPTION).addText(productSetting.getDescription());
		}
	}

	private static Document createDocument(){
		Document result = DocumentFactory.getInstance().createDocument();
		Element root = result.addElement(XML_ROOT);
		root.addElement(E_PRODUCTS);
		return result;
	}
	
	public static void saveProductSetting(List<ProductSetting> list){
		Document doc = createDocument();
		Element root = doc.getRootElement().element(E_PRODUCTS);
		if(null!=list && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				addProductSetting(root, list.get(i));
			}
		}
		XMLWriter output = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		try{
			output = new XMLWriter(
                new FileOutputStream( new File(XML_ABSOLUTE_PATH)),format); 
			output.write( doc );
            output.close();
		}catch (IOException e) {
			throw new UtilException("写入XML错误",e);
		}finally{
			if(null != output){
				try {
					output.close();
				} catch (IOException e) {
					throw new UtilException("关闭XML输出流错误",e);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		List<ProductSetting> list = getProductSettings();
		for(int i=0;i<list.size();i++){
			list.get(i).setSortId(Long.parseLong(i+""));
			list.get(i).setStatus(1);
			list.get(i).setImgPath("img");
		}
		saveProductSetting(list);
	}
}
