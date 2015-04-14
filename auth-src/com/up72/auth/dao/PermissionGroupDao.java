/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.PermissionGroup;
import com.up72.auth.vo.query.PermissionGroupQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * 权限分组表Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class PermissionGroupDao extends BaseHibernateDao<PermissionGroup,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return PermissionGroup.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(PermissionGroupQuery query) {
        //XsqlBuilder 
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from PermissionGroup t where 1=1 "
			  	+ "/~ and t.name = {name} ~/"
			  	+ "/~ and t.description = {description} ~/"
			  	+ "/~ and t.productCode = {productCode} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from PermissionGroup t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getDescription())) {
            sql2.append(" and  t.description = :description ");
        }
        if(isNotEmpty(query.getProductCode())) {
            sql2.append(" and  t.productCode = :productCode ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
		return pageQuery(sql,query);
	}
	

	/**
	 * 获得指定产品下的指定角色的权限组
	 * 
	 * @param productId
	 * @param roleId
	 * @return
	 */
	public List<PermissionGroup> getPermissionGroupList(String productId){
		String hsql = "from PermissionGroup where productCode=?";
		TreeMap<String,String> orders = new TreeMap<String, String>();
		orders.put("sortId", "asc");
		return this.findList(hsql, new Object[]{productId}, 0, orders);
	}
	
	public PermissionGroup getPermissionGroupByCode(String code){
		if(null != code && !code.trim().equals("")){
			return this.findByProperty("code", code);
		}else{
			return null;
		}
	}
}
