/**
 * 
 */
package com.up72.common.xml;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.up72.common.CommonConstants;
import com.up72.exception.UtilException;
import com.up72.framework.util.holder.ApplicationContextHolder;

/** 
 * @author 作者:DexinWang
 * @version 创建时间：2012-9-4 下午01:40:29
 * @manual 说明：读取xml类
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ReadXml {
	private String name = "";
	public ReadXml(){
		new ReadXml(name);
	}
	public ReadXml(String userName){
		this.name = userName;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-5 下午04:07:50
	  * @manual 说明：读取数据后做保存修改操作
	  * @param  参数：DatasXmlVo这是xml文件对象
	 */
	public void saveRecords(DatasXmlVo dxv){
			List<String> classNameList=dxv.getTablenamelist();
			LinkedHashMap<String, List> recordMap=dxv.getRecodeMap();
			LinkedHashMap<String, String> pkMap=dxv.getPkMap();
			if(null!=classNameList&&classNameList.size()>0&&null!=recordMap&&null!=pkMap){
				for (int i = 0; i < classNameList.size(); i++) {
					String className=classNameList.get(i);
					if(null!=className && !className.trim().equals("")){
						List<Object> recordList=recordMap.get(className);
						if(recordList!=null && recordList.size()>0){
							for (int k = 0; k < recordList.size(); k++) {
								Object obj=recordList.get(k);
								Object dbDao=this.getDao(obj.getClass().getSimpleName());
								if(dbDao!=null){
									String pk=pkMap.get(className);
									Object returnObj=this.checkUnique(dbDao, pk, obj);
									this.doSaveOrUpdate(dbDao, returnObj, obj);
								}
							}
						}
					}
				}
			}
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午04:54:18
	  * @manual 说明：读取xml一个DatasXmlVo对象
	 */

	public DatasXmlVo readXml(String filePath) {
		DatasXmlVo result=new DatasXmlVo();
		try{
			if(null!=filePath&&!"".equals(filePath)){
				File file=new File(filePath);
				if(file.exists()){
					List<Object> tableList=this.readDatas(filePath);
					if(null!=tableList&&tableList.size()>0){
						List<String> resultClassNameList=this.getClassNameList(tableList);
						result.addTablenamelist(resultClassNameList);
						LinkedHashMap<String,Object> recodesMap=this.readTable(tableList);
						LinkedHashMap<String, String> resultPkMap=this.getPkMap(tableList);
						if(null!=resultPkMap){
							result.addPkMap(resultPkMap);
						}
						if(null!=resultClassNameList&&resultClassNameList.size()>0&&null!=recodesMap){
							LinkedHashMap<String,List> resultRecordMap=this.readRecode(recodesMap, resultClassNameList);
							result.addRecodeMap(resultRecordMap);
						}
					}
				}
			}
		}catch (Exception e) {
			throw new UtilException(e);
		}
		return result;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午03:25:39
	  * @manual 说明：得到所有的tablelist
	 */
	public List<Object> readDatas(String filePath){
		List<Object> list=null;
		try{
			SAXReader saxReader = new SAXReader(); 
			saxReader.setEncoding("UTF-8");
			org.dom4j.Document document = saxReader.read(new File(filePath)); 
			if(null!=document){
				Element datas=document.getRootElement();
				List<Object> tablelist=datas.elements("table");
				if(null!=tablelist&&tablelist.size()>0){
					list=tablelist;
				}
			}
		}catch (Exception e) {
			throw new UtilException(e);
		}
		return list;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午03:25:06
	  * @manual 说明：得到table对应的records(未转换为model为Object)放入map
	 */
	public LinkedHashMap<String,Object> readTable(List<Object> tablelist){
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		if(null!=tablelist&&tablelist.size()>0){
			for (int i = 0; i < tablelist.size(); i++) {
				Element tableElement=(Element)tablelist.get(i);
				if(null!=tableElement){
					String key=tableElement.attributeValue("id");
					List<Object> recodelist=tableElement.elements("record");
					if(null!=recodelist&&recodelist.size()>0){
						map.put(key, recodelist);
					}
				}
			}
		}
		return map;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午04:25:56
	  * @manual 说明：通过反射得到每一条记录对应的实体
	 */
	public LinkedHashMap<String,List> readRecode(LinkedHashMap<String,Object> recodesMap,List<String> classnamelist){
		LinkedHashMap<String,List> resultRecodeMap=new LinkedHashMap<String, List>();
		try{
			if(null!=classnamelist&&classnamelist.size()>0&&null!=recodesMap){
				for (String string : classnamelist) {
					List<Object> resultRecords=new ArrayList<Object>();
					List<Object> recordlist=(List<Object>) recodesMap.get(string);
					if(null!=recordlist&&recordlist.size()>0){
						for (int i = 0; i < recordlist.size(); i++) {
							Element recordElement=(Element) recordlist.get(i);
							Object obj = Class.forName(string).newInstance();
							Field[] fields=obj.getClass().getDeclaredFields();
							for (Field field : fields) {
								/***得到修饰符 根据修饰符判断model内的属性，方法，变量**/
								int mod=field.getModifiers(); 
								if(!Modifier.toString(mod).equals("public static final")&&!Modifier.toString(mod).equals("private static final")){
									if(!field.getName().startsWith(CommonConstants.sync.ENTITY_SYNC_FILE_PATH)){
										field.setAccessible(true);//设置私有属性打开
										String setMethodName = "set" + stringUtil.alifUpperCase(field.getName());
										Method setMethod = obj.getClass().getDeclaredMethod(setMethodName,field.getType());
										Object value=this.changValue(null==recordElement.elementText(field.getName())?"":recordElement.elementText(field.getName()),field.getType().getName());
										setMethod.invoke(obj, value);
									}
								}
							}
							obj=this.saveUpdateTime(obj);//调用添加更新时间方法
							resultRecords.add(obj);	
						}
					}
					resultRecodeMap.put(string, resultRecords);
				}
			}
		}catch (Exception e) {
			throw new UtilException(e);
		}
		return resultRecodeMap;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午03:29:19
	  * @manual 说明：得到装有所有id的集合
	 */
	public  List<String> getClassNameList(List<Object> tablelist){
		List<String> list=new ArrayList<String>();
		if(null!=tablelist&&tablelist.size()>0){
			for (int i = 0; i < tablelist.size(); i++) {
				Element tableElement=(Element)tablelist.get(i);
				if(null!=tableElement){
					String id=tableElement.attributeValue("id");
					if(id!=null&&!"".equals(id)){
						list.add(id);
					}
				}
			}
		}
		return list;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午04:42:44
	  * @manual 说明：得到每个table的PkMap
	 */
	public LinkedHashMap<String,String> getPkMap(List<Object> tablelist){
		LinkedHashMap<String,String> resultPkMap=new LinkedHashMap<String, String>();
		if(null!=tablelist&&tablelist.size()>0){
			for (int i = 0; i < tablelist.size(); i++) {
				Element tableElement=(Element)tablelist.get(i);
				if(null!=tableElement){
					String id=tableElement.attributeValue("id");
					String pk=tableElement.attributeValue("pk");
					if(id!=null&&!"".equals(id)&&pk!=null&&!"".equals(pk)){
						resultPkMap.put(id, pk);
					}
				}
			}
		}
		return  resultPkMap;
	}
	/**
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-4 下午03:59:02
	  * @manual 说明：字符串类型转换
	 */
	public Object changValue(String string,String type){
		Object object=null;
		if(string!=null&&!"".equals(string.trim())){
			if(type.equalsIgnoreCase("Boolean")||type.equalsIgnoreCase("java.lang.Boolean")){
				if(string.equalsIgnoreCase("true") || string.equals("1"))  
					object=true;
				else  
					object=false;
			}else if(type.equalsIgnoreCase("Integer")||type.equalsIgnoreCase("java.lang.Integer")){
				object=Integer.parseInt(string);
			}else if(type.equalsIgnoreCase("Long")||type.equalsIgnoreCase("java.lang.Long")){
				object=Long.parseLong(string);
			}else if(type.equalsIgnoreCase("String")||type.equalsIgnoreCase("java.lang.String")){
				object=string;
			}else if(type.equalsIgnoreCase("Float")||type.equalsIgnoreCase("java.lang.Float")){
				object=Float.parseFloat(string);
			}else{
				throw new UtilException("不支持的数据类型");
			}
		}else{
			if(type.equalsIgnoreCase("Boolean")||type.equalsIgnoreCase("java.lang.Boolean")){
				string = "0";
				if(string.equalsIgnoreCase("true") || string.equals("1"))  
					object=true;
				else  
					object=false;
			}else if(type.equalsIgnoreCase("Integer")||type.equalsIgnoreCase("java.lang.Integer")){
				string = "-1";
				object=Integer.parseInt(string);
			}else if(type.equalsIgnoreCase("Long")||type.equalsIgnoreCase("java.lang.Long")){
				string = "-1";
				object=Long.parseLong(string);
			}else if(type.equalsIgnoreCase("String")||type.equalsIgnoreCase("java.lang.String")){
				string = "";
				object=string;
			}else if(type.equalsIgnoreCase("Float")||type.equalsIgnoreCase("java.lang.Float")){
				string = "-1";
				object=Float.parseFloat(string);
			}else{
				throw new UtilException("不支持的数据类型");
			}
		}
		return object;
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-5 下午03:34:50
	  * @manual 说明：获得对应的Dao
	 */
	private Object getDao(String dbDaoString){
		Object dbDao=null;
		if(null!=dbDaoString&&!"".equals(dbDaoString)){
			dbDaoString=stringUtil.alifLowerCase(dbDaoString)+"Dao";
			dbDao=ApplicationContextHolder.getBean(dbDaoString);
		}
		return dbDao;
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-5 下午03:34:50
	  * @manual 说明：验证数据是否与数据库重复
	  * @author 参数:dbDao对象,装有验证参数的HashMap,xml得到的数据对象
	 */
	private Object checkUnique(Object dbDao,String pk,Object obj){
		Object returnObj=null;
		try{
			HashMap<String, Object> params = new HashMap<String, Object>();
			String[] pks=pk.split(",");
			for (int j = 0; j < pks.length; j++) {
				Field field=obj.getClass().getDeclaredField(pks[j]);
				Method getPkMethod=obj.getClass().getDeclaredMethod("get"+stringUtil.alifUpperCase(pks[j]));
				params.put(pks[j], getPkMethod.invoke(obj));
			}
			/***获得dao内的检查是否唯一方法**/
			Method findEntityMethod=dbDao.getClass().getMethod("findByProperties", HashMap.class);
			returnObj=findEntityMethod.invoke(dbDao,params);
		}catch (Exception e) {
			throw new UtilException(e);
		}
		return returnObj;
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-5 下午04:00:49
	  * @manual 说明：做保存或者修改操作
	  * @param  参数：dbDao对象,检查是否数据重复返回的对象，xml得到的数据对象
	 */
	private void doSaveOrUpdate(Object dbDao,Object returnObj,Object obj){
		try{
			/**获得getId(),setId()方法**/
			Field idField=obj.getClass().getDeclaredField("id");
			Method setIdMethod=obj.getClass().getDeclaredMethod("setId", idField.getType());
			if(null!=returnObj){
				/**交换Id准备做修改操作*/
				Method getIdMethod=returnObj.getClass().getDeclaredMethod("getId");
				Object returnValue=getIdMethod.invoke(returnObj);
				setIdMethod.invoke(obj, returnValue);
				Field[] fields=obj.getClass().getDeclaredFields();
				/**获得dao内的修改方法并调用*/
				Method updateMethod=dbDao.getClass().getMethod("merge", Object.class);
				updateMethod.invoke(dbDao, obj);
			}else{
	//			setIdMethod.invoke(obj, null);
				/**获得dao内的修改方法并调用*/
				Method saveMethod=dbDao.getClass().getMethod("save", Object.class);
				saveMethod.invoke(dbDao, obj);
			}
		}catch (Exception e) {
			throw new UtilException(e);
		}
	}
	/****
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-15 下午07:12:39
	  * @manual 说明：判断状态和模块是否匹配
	 */
	public boolean isConform(String filePath,String status,String modular) {
		boolean result=true;
		if(null!=filePath&&!"".equals(filePath)){
			SAXReader saxReader = new SAXReader(); 
			saxReader.setEncoding("UTF-8");
			org.dom4j.Document document = null;
			try {
				document = saxReader.read(new File(filePath));
			} catch (DocumentException e) {
				throw new UtilException(e);
			} 
			String xmlStatus=document.getRootElement().attributeValue("status");
			String xmlModular=document.getRootElement().attributeValue("modular");
			if(null!=xmlStatus&&!"".equals(xmlStatus.trim())){
				if(!status.equals(xmlStatus.trim())){
					result=false;
				}
			}else{
				result=false;
			}
			if(null!=xmlModular&&!"".equals(xmlModular.trim())){
				if(!modular.equals(xmlModular.trim())){
					result=false;
				}
			}else{
				result=false;
			}
		}else{
			result=false;
		}
		return result;
	}
	/***
	  * @author 作者:DexinWang
	  * @version 创建时间：2012-9-16 下午04:00:50
	  * @manual 说明：设置更新时间，和更新人
	 */
	public Object saveUpdateTime(Object obj){
		Method[] methods=obj.getClass().getMethods();
		try {
			Method updateByMethod = obj.getClass().getMethod("setUpdateBy", String.class);
			updateByMethod.invoke(obj, this.name);
			Method updateTimeMethod=obj.getClass().getMethod("setUpdateTime",Long.class);
			updateTimeMethod.invoke(obj, new Date().getTime());
		} catch (Exception e) {
		}
		return obj;
	}
}
