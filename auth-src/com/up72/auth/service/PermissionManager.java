/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;
import static com.up72.common.CommonUtils.stringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.bean.PermissionCodeHelper;
import com.up72.auth.dao.OrganizationDao;
import com.up72.auth.dao.PermissionDao;
import com.up72.auth.dao.PermissionGroupDao;
import com.up72.auth.dao.ProductAboutDao;
import com.up72.auth.dao.ProductDao;
import com.up72.auth.model.Permission;
import com.up72.auth.model.PermissionGroup;
import com.up72.auth.model.Product;
import com.up72.auth.model.ProductAbout;
import com.up72.auth.vo.query.OrganizationQuery;
import com.up72.auth.vo.query.PermissionGroupQuery;
import com.up72.auth.vo.query.PermissionQuery;
import com.up72.auth.vo.query.ProductQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.common.CommonConstants;
import com.up72.common.xml.DatasXmlVo;
import com.up72.common.xml.ReadXml;
import com.up72.common.xml.WriteXml;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 权限表（菜单，资源）业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class PermissionManager extends BaseManager<Permission,java.lang.Long>{

	private PermissionDao permissionDao;
	private PermissionGroupDao permissionGroupDao;
	private OrganizationDao organizationDao;
	private ProductDao productDao;
	private ProductManager productManager;
	private ProductAboutDao productAboutDao;
	private ProductAboutManager productAboutManager;
	private PermissionGroupManager permissionGroupManager;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setPermissionGroupDao(PermissionGroupDao permissionGroupDao) {
		this.permissionGroupDao = permissionGroupDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setPermissionDao(PermissionDao dao) {
		this.permissionDao = dao;
	}
	
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setProductAboutDao(ProductAboutDao productAboutDao) {
		this.productAboutDao = productAboutDao;
	}

	public void setProductAboutManager(ProductAboutManager productAboutManager) {
		this.productAboutManager = productAboutManager;
	}

	public void setPermissionGroupManager(
			PermissionGroupManager permissionGroupManager) {
		this.permissionGroupManager = permissionGroupManager;
	}

	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.permissionDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(PermissionQuery query) {
		return permissionDao.findPage(query);
	}
	
	/**
	 * 获得机构列表
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findOrganization(OrganizationQuery query){
		return organizationDao.findPage(query);
	}
	/**
	 * 获得权限组列表
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPermissionGroup(PermissionGroupQuery query){
		return permissionGroupDao.findPage(query);
	}
	/**
	 * 获得权限组列表
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findProduct(ProductQuery query){
		return productDao.findPage(query);
	}
	
	/**
	 * 获取指定角色，指定产品，指定类型的权限列表
	 * @param roleId 角色id
	 * @param productCode 产品码
 	 * @param permType 权限类型 
 	 * AuthConstants.PERMISSION_TYPE_MENU 
 	 * AuthConstants.PERMISSION_TYPE_OPTION 
 	 * AuthConstants.PERMISSION_TYPE_TAB
	 * @param size 获取数量
	 * @return List<Permission>
	 */
	public List<Permission> getPermissionListByRole(long roleId,String productCode,int permType,int size){
		List<Permission> result = null;
		if(roleId > 0){
			StringBuffer hsql = new StringBuffer("from Permission p where 1=1");
			hsql.append(" and p.id in(");
			hsql.append("select rp.permissionId from RolePermission rp where rp.roleId=?");
			hsql.append(")");
			hsql.append(" and p.type=?");
			hsql.append(" and p.productCode=?");
			
			TreeMap<String,String> orders = new TreeMap<String, String>();
			orders.put("sortId", "asc");
			
			result = this.permissionDao.find(hsql.toString(), new Object[]{roleId,permType,productCode}, size, orders);
		}
		return result;
	}
	
	public List<Permission> getPermissionListByRole(long roleId,int permType,int size){
		List<Permission> result = null;
		if(roleId > 0){
			StringBuffer hsql = new StringBuffer("from Permission p where 1=1");
			hsql.append(" and p.id in(");
			hsql.append("select rp.permissionId from RolePermission rp where rp.roleId=?");
			hsql.append(")");
			hsql.append(" and p.type=?");
			hsql.append(" and p.url!='#'");
			
			TreeMap<String,String> orders = new TreeMap<String, String>();
			orders.put("sortId", "asc");
			
			result = this.permissionDao.find(hsql.toString(), new Object[]{roleId,permType}, size, orders);
		}
		return result;
	}
	
	/**
	 * 根据角色ID获取权限列表
	 */
	public List<Permission> getPermissionListByRoleIds(String roleIds,int permType,int size){
		List<Permission> result = null;
		if(ObjectUtils.isNotEmpty(roleIds)){
			StringBuffer hsql = new StringBuffer("from Permission p where 1=1");
			hsql.append(" and p.id in(");
			hsql.append("select rp.permissionId from RolePermission rp where rp.roleId in (" + roleIds + ")");
			hsql.append(")");
			hsql.append(" and p.type=?");
			
			TreeMap<String,String> orders = new TreeMap<String, String>();
			orders.put("sortId", "asc");
			result = this.permissionDao.find(hsql.toString(), new Object[]{permType}, size, orders);
		}
		return result;
	}
	
	//===============================重置权限码 start============================================
	public void resetPermissionCode(){
		List<Product> productList = this.productDao.findAll();
		if(null != productList && !productList.isEmpty()){
			for(int i=0;i<productList.size();i++){
				Product product = productList.get(i);
				resetProduct(product);
				List<PermissionGroup> permissionGourpList = 
					this.permissionGroupDao.getPermissionGroupList(product.getCode());
				resetPermissionGroup(permissionGourpList);
			}
		}
	}
	
	private void resetProduct(Product product){
		// 创建code
		String code = PermissionCodeHelper.createCode(product.getName());
		
		// 更新权限组
		StringBuffer hsql = new StringBuffer("update PermissionGroup pg set pg.productCode='"+code+"'"); 
		hsql.append(" where pg.productId="+product.getId());
		Object[] params = null;
		if(null != product.getCode() && !product.getCode().equals("")){
			hsql.append(" or pg.productCode=?");
			params = new Object[]{product.getCode()};
		}
		this.productDao.executeHsql(hsql.toString(), params);
		
		// 更新产品
		product.setCode(code);
		this.productDao.update(product);
	}
	
	private void resetPermissionGroup(List<PermissionGroup> permissionGourpList){
		if(null != permissionGourpList && !permissionGourpList.isEmpty()){
			for(int i=0;i<permissionGourpList.size();i++){
				PermissionGroup permissionGroup = permissionGourpList.get(i);
				String code = PermissionCodeHelper.createGroupCode(permissionGroup);
				StringBuffer hsql = new StringBuffer("update Permission p set p.permissionGroupCode='"+code+"'"); 
				hsql.append(" where p.permissionGroupId="+permissionGroup.getId());
				Object[] params = null;
				if(null != permissionGroup.getCode() && !permissionGroup.getCode().equals("")){
					hsql.append(" or p.permissionGroupCode=?");
					params = new Object[]{permissionGroup.getCode()};
				}
				this.permissionDao.executeHsql(hsql.toString(),params);
				permissionGroup.setCode(code);
				this.permissionGroupDao.update(permissionGroup);
				
				List<Permission> permissionList = this.permissionDao.getPermissionListByGroupId(permissionGroup.getId(), null);
				resetPermission(permissionList,permissionGroup.getProductCode());
			}
		}
	}
	
	private void resetPermission(List<Permission> permissionList,String productCode){
		if(null != permissionList && !permissionList.isEmpty()){
			for(int i=0;i<permissionList.size();i++){
				Permission permission = permissionList.get(i);
				String code = PermissionCodeHelper.createPermissionCode(permission);
				permission.setCode(code);
				permission.setProductCode(productCode);
				permissionDao.update(permission);
			}
		}
	}
	//===============================重置权限码 end============================================
	
	public Permission getPermissionByCode(String code){
		return this.permissionDao.getPermissionByCode(code);
	}
	
	/**
	 * 导出权限
	 * @author wgf
	 * @param codes
	 */
	@SuppressWarnings("unchecked")
	public String exportPerm(String[] productCodes,String xmlPath){
		List<Product> productList = new ArrayList<Product>();
		List<ProductAbout> producAboutList = new ArrayList<ProductAbout>();
		List<Permission> permissionList = new ArrayList<Permission>();
		List<PermissionGroup> permissionGroupList = new ArrayList<PermissionGroup>();
		List<String> list = new ArrayList<String>();
		LinkedHashMap<String,List> map=new LinkedHashMap<String,List>();
		LinkedHashMap<String,String> pkMap=new LinkedHashMap<String,String>();
		String message = "success";
		if(null != productCodes && productCodes.length > 0){
			for (int i = 0; i < productCodes.length; i++) {
				Product product = this.productManager.getProductByCode(productCodes[i]);
				if(null != product){
					productList.add(product);
				}
				List<ProductAbout> prductAboutList =this.productAboutDao.getProductAboutByProductCode(productCodes[i]);
				if(null != prductAboutList && prductAboutList.size() > 0){
					producAboutList.addAll(prductAboutList);
				}
				List<Permission> permissionsList = this.permissionDao.getPermissionByProductCode(productCodes[i]);
				if(null != permissionsList && permissionsList.size() > 0){
					permissionList.addAll(permissionsList);
				}
				List<PermissionGroup> permissionGroups = this.permissionGroupManager.getPermissionGroupList(productCodes[i]);
				if(null != permissionGroups && permissionGroups.size() > 0){
					permissionGroupList.addAll(permissionGroups);
				}
				
			}
			if(null != productList && productList.size() > 0){
				map.put(Product.class.getName(), productList);
				pkMap.put(Product.class.getName(), "code");
				list.add(Product.class.getName());
			}
			if(null != producAboutList && producAboutList.size() > 0){
				map.put(ProductAbout.class.getName(), producAboutList);
				pkMap.put(ProductAbout.class.getName(), "id");
				list.add(ProductAbout.class.getName());
			}
			if(null != permissionGroupList && permissionGroupList.size() > 0){
				map.put(PermissionGroup.class.getName(), permissionGroupList);
				pkMap.put(PermissionGroup.class.getName(), "code");
				list.add(PermissionGroup.class.getName());
			}
			if(null != permissionList && permissionList.size() > 0){
				map.put(Permission.class.getName(), permissionList);
				pkMap.put(Permission.class.getName(), "code");
				list.add(Permission.class.getName());
			}
			
			DatasXmlVo dxv = new DatasXmlVo();
			dxv.addTablenamelist(list);
			dxv.addRecodeMap(map);
			dxv.addPkMap(pkMap);
			WriteXml xml=new WriteXml();
			try {
				xml.createDatas(dxv, xmlPath);
			} catch (Exception e) {
				e.printStackTrace();
				message=stringUtil.encode("导出失败，请确认数据后重试");
			} 
			
			/*try {
				xml.createDatas(dxv, xmlPath);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		return message;
	}
	/**
	 * 读取xml 获得数据集合对象
	 * @author wgf
	 * @param filePath为xml路径
	 * @return
	 */
	public DatasXmlVo importPermXML(String filePath){
		DatasXmlVo dxv = new DatasXmlVo();
		String endChar = filePath.substring((filePath.lastIndexOf("."))+1);
		filePath = stringUtil.parseToPath(CommonConstants.ROOTPATH +"/"+ filePath);
		ReadXml readXml = new ReadXml();
		if(null != endChar && !"".equals(endChar.trim())){
			if("xml".equals(endChar)){//上传文件为XML
			    dxv = readXml.readXml(filePath);
			}
			if("zip".equals(endChar)){//上传文件为zip
				
			}
		}
		return dxv;
	}
	/**导入或者更新
	 * @author wgf
	 * @param permissionList导入的所有数据集合 code选择的产品code
	 * @param code
	 */
	public void savePermission(List<Permission> permissionList,String code){
		List<Permission> oldPermissionList = this.permissionDao.getPermissionByProductCode(code);
		if(null != oldPermissionList && oldPermissionList.size() > 0){
			for (int i = 0; i < oldPermissionList.size(); i++) {
				Permission oldPermission = oldPermissionList.get(i);
				if(null != oldPermission){
					this.permissionDao.delete(oldPermission);
//					this.permissionDao.getHibernateTemplate().clear();
				}
			}
		}
		if(null != permissionList && permissionList.size() > 0){
			for (int j = 0; j < permissionList.size(); j++) {
				Permission permission = permissionList.get(j);
				if(null != permission){
					String permissionProductCode = permission.getProductCode();
					if(null != permissionProductCode){
						if(permissionProductCode.equals(code)){
							permission.setId(null);
							this.save(permission);
						}
					}
				}
				
			}
		}
		
		
	}
	/**
	 * 导入已选择的产品
	 * @author wgf
	 * @param productCodes为产品code dxv为导入的xml数据对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void doImportPerm(String[] productCodes,DatasXmlVo dxv){
		if(null != productCodes && productCodes.length > 0 && null != dxv ){
			LinkedHashMap<String, List> recodeMap = dxv.getRecodeMap();
			List<Product> productList = recodeMap.get(Product.class.getName());
			List<ProductAbout> productAboutList = recodeMap.get(ProductAbout.class.getName());
			List<Permission> permissionList = recodeMap.get(Permission.class.getName());
			List<PermissionGroup> permissionGroupList =  recodeMap.get(PermissionGroup.class.getName());
				for (int i = 0; i < productCodes.length; i++) {
					String code = productCodes[i];
					this.productManager.saveProduct(productList, code);
					this.productAboutManager.saveProductAbout(productAboutList, code);
					this.savePermission(permissionList, code);
					this.permissionGroupManager.savePermissionGroup(permissionGroupList, code);
			}
		}
	}
	/**
	 * flag 1 代表上移 0代表下移
	 * @param sortId
	 * @param type
	 * @return
	 */
	public Permission getAnotherPermssion(Integer sortId,String code,String permissionGroupCode,int type){
		Permission result = null;
		String hql = "";
		if(type==1){
			hql = "from Permission where sortId < ?  and permissionGroupCode = ? order by sortId desc";
		}else{
			hql = "from Permission where sortId > ?  and permissionGroupCode = ? order by sortId asc";
		}
		TreeMap<String,String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		List<Permission> list = permissionDao.findList(hql, new Object[]{sortId,permissionGroupCode},0,orderMap);
		if(null!=list && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
}
