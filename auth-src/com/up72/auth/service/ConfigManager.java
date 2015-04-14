/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.service;

import static com.up72.common.CommonUtils.classScanUtil;
import static com.up72.common.CommonUtils.reflectUtil;
import static com.up72.common.CommonUtils.stringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.up72.auth.annotation.PermissionAnnotation;
import com.up72.auth.annotation.PermissionGroupAnnotation;
import com.up72.auth.bean.PermissionCodeHelper;
import com.up72.auth.config.ProductSetting;
import com.up72.auth.config.SettingXmlParseHelper;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
/**
 * 业务处理
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class ConfigManager extends BaseManager<Object,java.lang.Long>{
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private PermissionGroupDao permissionGroupDao;
	@Autowired
	private ProductDao productDao;
	
	protected EntityDao getEntityDao() {
		return this.permissionDao;
	}
	
	public List<ProductSetting> getProductSetting(){
		List<ProductSetting> result = SettingXmlParseHelper.getProductSettings();
		return result;
	}
	
	public void saveProductSetting(List<ProductSetting> settingList){
		if(null!=settingList && !settingList.isEmpty()){
			for(int i=0;i<settingList.size();i++){
				
			}
		}
	}
	
	public void scan(){
		List<ProductSetting> productList = SettingXmlParseHelper.getProductSettings();
		if(null!=productList && !productList.isEmpty()){
			for(int i=0;i<productList.size();i++){
				ProductSetting setting = productList.get(i);
				Product product = getProduct(setting);
					
				// 扫描产品下的权限
				scanPermissionGroup(product, setting);
			}
		}
	}
	
	private List<PermissionGroup> scanPermissionGroup(Product product,ProductSetting setting){
		List<PermissionGroup> result = new ArrayList<PermissionGroup>();
		String pvckageName = setting.getPvckage();
		Iterator<String> classes = classScanUtil.getClasses(pvckageName).iterator();
		while(classes.hasNext()){
			String className = classes.next();
			Class c = reflectUtil.loadClass(className);
			Annotation classAnno = reflectUtil.getClassAnnotation(c, PermissionGroupAnnotation.class);
			if(null == classAnno){
				continue;
			}
			PermissionGroup permissionGroup = getPermissionGroup(classAnno,product);
			scanPermission(permissionGroup, c);
		}
		
		return result;
	}

	private List<Permission> scanPermission(PermissionGroup permissionGroup,Class c){
		List<Permission> result = new ArrayList<Permission>();
		Map<Method,Annotation> methodAnnoMap = reflectUtil.findMethodByAnnotation(c, PermissionAnnotation.class);
		if(null==methodAnnoMap || methodAnnoMap.isEmpty()){
			return result;
		}
		Annotation controllerAnno = c.getAnnotation(Controller.class);
		if(null == controllerAnno){
			return result;
		}
		RequestMapping requestMappingAnno = (RequestMapping)c.getAnnotation(RequestMapping.class);
		if(null==requestMappingAnno 
				|| null==requestMappingAnno.value()
				|| requestMappingAnno.value().length==0){
			return result;
		}
		
		String pUri = ((RequestMapping)requestMappingAnno).value()[0];
		
		Iterator<Method> it = methodAnnoMap.keySet().iterator();
		while(it.hasNext()){
			Method method = it.next();
			RequestMapping metMapping = (RequestMapping)reflectUtil.getMethodAnnotation(method,RequestMapping.class);
			if(null!=metMapping 
					&& null!=metMapping.value() 
					&& metMapping.value().length>0){
				String uri = stringUtil.parseToPath(pUri+"/"+metMapping.value()[0]);
				PermissionAnnotation anno = (PermissionAnnotation)methodAnnoMap.get(method);
				getPermission(anno, permissionGroup,uri);
			}
		}
		return result;
	}
	
	private Permission getPermission(PermissionAnnotation permissionAnnotation,PermissionGroup permissionGroup,String uri){
		String code = PermissionCodeHelper.createPermissionCode(permissionGroup.getCode(), uri, permissionAnnotation.name());
		Permission result = this.permissionDao.getPermissionByCode(code);
		if(null == result){
			result = new Permission();
			result.setCode(code);
			result.setName(permissionAnnotation.name());
			result.setDescription(permissionAnnotation.description());
			result.setEnabled(1);
			result.setPermissionGroupCode(permissionGroup.getCode());
			result.setPermissionGroupId(permissionGroup.getId());
			result.setProductCode(permissionGroup.getProductCode());
			result.setProductId(permissionGroup.getProductId());
			result.setUrl(uri);
			int status = (null==permissionAnnotation.status() || permissionAnnotation.status().trim().equals("")) ? 
					permissionGroup.getStatus():stringUtil.parseInt(permissionAnnotation.status());
			result.setStatus(status);
			int type = (null==permissionAnnotation.type() || permissionAnnotation.type().trim().equals("")) ?
					stringUtil.parseInt(PermissionAnnotation.TYPE_OPTION) : stringUtil.parseInt(permissionAnnotation.type());
 			result.setType(type);
 			result.setSortId(0);
			this.permissionDao.save(result);
		}
		return result;
	}
	
	private PermissionGroup getPermissionGroup(Annotation permissionGroupAnnotation,Product product){
		PermissionGroupAnnotation groupAnnotation = (PermissionGroupAnnotation)permissionGroupAnnotation;
		String code = PermissionCodeHelper.createGroupCode(product.getCode(), groupAnnotation.name());
		PermissionGroup result = this.permissionGroupDao.getPermissionGroupByCode(code);
		if(null == result){
			result = new PermissionGroup();
			result.setName(groupAnnotation.name());
			result.setDescription(groupAnnotation.description());
			result.setProductCode(product.getCode());
			result.setProductId(product.getId());
			result.setSortId(0L);
			int status = (null==groupAnnotation.status()||groupAnnotation.status().trim().equals("")) ? 
					product.getStatus():stringUtil.parseInt(groupAnnotation.status());
			result.setStatus(status);
			result.setCode(code);
			this.permissionGroupDao.save(result);
		}
		return result;
	}
	
	private Product getProduct(ProductSetting setting){
		String code = PermissionCodeHelper.createCode(setting.getName());
		// 校验产品是否已存在
		Product result = productDao.getProductByCode(code);
		if(null==result){
			result = new Product();
			result.setName(setting.getName());
			result.setCode(PermissionCodeHelper.createCode(setting.getName()));
			result.setDescription(setting.getDescription());
			result.setImgPath(setting.getImgPath());
			result.setSortId(setting.getSortId());
			result.setStatus(setting.getStatus());
		}
		this.productDao.save(result);
		return result;
	}
}
