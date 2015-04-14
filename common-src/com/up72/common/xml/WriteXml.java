/**
 * 
 */
package com.up72.common.xml;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.up72.common.CommonConstants;


/** 
 * @author 作者:DexinWang
 * @version 创建时间：2012-9-3 下午05:18:07
 * @manual 说明：根据表集合导出xml数据
 */
@SuppressWarnings({ "unchecked", "unused" })
public class WriteXml {
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 上午11:20:58
	  * @manual 说明：创建导出的每条记录
	  * @param  参数：参数为tableElement，装有记录的recodelist，tableName就是类名
	 */
	public String createRecode(Element tableElement,List recodelist,String tableName,List<String> syncFileList) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String pk="";
		for(int i=0;i<recodelist.size();i++){
				if(null!=recodelist&&recodelist.size()>0){
					Element recodeElement=tableElement.addElement("record");
					Object obj=Class.forName(tableName).newInstance();
					if(null!=obj){
						Field[] recodes=obj.getClass().getDeclaredFields();
						for (int j = 0; j < recodes.length; j++) {
							recodes[j].setAccessible(true);
							/***得到注释 根据注释判断Pk***/
							Annotation[] annotations=recodes[j].getDeclaredAnnotations();
								for (Annotation annotation : annotations) {
									if(null!=annotation.annotationType()&&!"".equals(annotation.annotationType())){
										if(annotation.annotationType().toString().lastIndexOf("NotBlank")!=-1||annotation.annotationType().toString().lastIndexOf("NotNull")!=-1){
											pk="".equals(pk)?pk+recodes[j].getName():pk+","+recodes[j].getName();
										}
									}
								}
							/***得到修饰符 根据修饰符判断model内的属性，方法，变量**/
							int mod=recodes[j].getModifiers(); 
							if(!Modifier.toString(mod).equals("public static final")&&!Modifier.toString(mod).equals("private static final")){
								String value = null==recodes[j].get(recodelist.get(i))?"":recodes[j].get(recodelist.get(i)).toString();
								if(!recodes[j].getName().startsWith(CommonConstants.sync.ENTITY_SYNC_FILE_PATH)){
									Element valueElement=recodeElement.addElement(recodes[j].getName());
									valueElement.setText(value);
								}else if(null!=value && !value.trim().equals("")){
									java.io.File file = new java.io.File(stringUtil.parseToPath(CommonConstants.ROOTPATH+"/"+value));
									if(!file.exists()){
										file.getParentFile().mkdirs();
										try{
											file.createNewFile();
										}catch (Exception e) {
											// TODO: handle exception
										}
									}
									syncFileList.add(stringUtil.parseToPath(CommonConstants.ROOTPATH+"/"+value));
								}
							}	
						}
					}
				}
			}
			return pk;
		}

	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 上午11:20:58
	  * @manual 说明：创建导出的每个表对应的table
	  * @param  参数：参数为dataElement，装有记录的tableNameList，一个装有所有记录的map （key为对应的类名 value为记录的集合）
	 */
	public void createTable(Element dataElement,DatasXmlVo datas) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		LinkedHashMap<String, List> recodeMap = datas.getRecodeMap();;
		List tableNameList=datas.getTablenamelist();
		Map<String, List> recodes = datas.getRecodeMap();
		Map<String,String> pkMap = datas.getPkMap();
		
		List<String> syncFileList = datas.getSyncFileList();
		  
		if(null!=dataElement&&null!=tableNameList&&tableNameList.size()>0&&null!=recodes){
			String tableName="";
			String paramPk="";
			for (int i = 0; i < tableNameList.size(); i++) {
				tableName=tableNameList.get(i).toString();
				if(!"".equals(tableName.trim())){
					List recodelist=recodes.get(tableName);
					if(null!=recodelist&&recodelist.size()>0){
						/**创建table元素**/
						Element tableElement=dataElement.addElement("table");
						tableElement.addAttribute("id",tableName);
						String pk=this.createRecode(tableElement, recodelist, tableName,syncFileList);
						/**设置pk参数 如果则从数据库判断  如果不为空则为用户传递*/
						if(null==pkMap){
							tableElement.addAttribute("pk", pk);
						}else{
							paramPk=null==pkMap.get(tableName)?"":pkMap.get(tableName);
							tableElement.addAttribute("pk","".equals(paramPk.trim())?pk:paramPk);
						}
					}
				}
			}
		}
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 上午11:33:19
	  * @manual 说明：创建整个xml
	  * @param  参数：参数为数据来源的实体，和保存的路径,后两项为标志列，导入时候判断作用
	 */
	public void createDatas(DatasXmlVo datas,String savePath,String status,String modular) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
		if(null!=datas&&null!=savePath&&!"".equals(savePath)){
			List tableNameList=null;
			LinkedHashMap<String, List> recodeMap=null;
			tableNameList=datas.getTablenamelist();
			recodeMap=datas.getRecodeMap();
			if(null!=tableNameList&&tableNameList.size()>0&&null!=recodeMap){
				/**获得Document对象*/
				Document document = DocumentHelper.createDocument();
				Element dataElement = document.addElement("datas");
				dataElement.addAttribute("status",status );
				dataElement.addAttribute("modular", modular);
				this.createTable(dataElement, datas);
				/***导出文件**/
				OutputFormat format = OutputFormat.createPrettyPrint();
				// 设置编码格式
				format.setEncoding("UTF-8");
				FileOutputStream os = new FileOutputStream(savePath);
				//写入xml文件
				XMLWriter xmlWriter = new XMLWriter(os,format);
				xmlWriter.write(document);
				xmlWriter.close();
			}
		}
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 上午11:33:19
	  * @manual 说明：创建整个xml
	  * @param  参数：参数为数据来源的实体，和保存的路径
	 */
	public void createDatas(DatasXmlVo datas,String savePath) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
		if(null!=datas&&null!=savePath&&!"".equals(savePath)){
			List tableNameList=null;
			LinkedHashMap<String, List> recodeMap=null;
			tableNameList=datas.getTablenamelist();
			recodeMap=datas.getRecodeMap();
			if(null!=tableNameList&&tableNameList.size()>0&&null!=recodeMap){
				/**获得Document对象*/
				Document document = DocumentHelper.createDocument();
				Element dataElement = document.addElement("datas");
				this.createTable(dataElement, datas);
				/***导出文件**/
				OutputFormat format = OutputFormat.createPrettyPrint();
				// 设置编码格式
				format.setEncoding("UTF-8");
				FileOutputStream os = new FileOutputStream(savePath);
				//写入xml文件
				XMLWriter xmlWriter = new XMLWriter(os,format);
				xmlWriter.write(document);
				xmlWriter.close();
			}
		}
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-20 下午12:19:30
	  * @manual 说明：设置导出的附件的路径 xml
	 */
	public void createMiddelFile(String planGuid,String savePath,List<String> filesList,String datasPath) throws IOException{
		if(savePath!=null&&!"".equals(savePath)&&null!=filesList&&filesList.size()>0){
			String dataUrl=datasPath.replace(CommonConstants.ROOTPATH, "");
			filesList.remove(dataUrl);
			Document document = DocumentHelper.createDocument();
			Element filesElement = document.addElement("Files");
			filesElement.addAttribute("dataUrl", dataUrl.replace("export/", ""));
			filesElement.addAttribute("planGuid", planGuid);
			OutputFormat format = OutputFormat.createPrettyPrint();
			for (int i = 0; i < filesList.size(); i++) {
				if(filesList.get(i)!=null&&!"".equals(filesList.get(i).trim())){
					Element urlElement=filesElement.addElement("url");
					urlElement.setText(filesList.get(i).trim());
				}
			}
			// 设置编码格式
			format.setEncoding("UTF-8");
			FileOutputStream os = new FileOutputStream(savePath);
			//写入xml文件
			XMLWriter xmlWriter = new XMLWriter(os,format);
			xmlWriter.write(document);
			xmlWriter.close();
			filesList.add(dataUrl);
		}
	}
}