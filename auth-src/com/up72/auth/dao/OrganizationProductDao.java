/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.up72.auth.model.OrganizationProduct;
import com.up72.auth.model.Product;
import com.up72.auth.vo.query.OrganizationProductQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;

/**
 * 部门与产品Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class OrganizationProductDao extends BaseHibernateDao<OrganizationProduct,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return OrganizationProduct.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(OrganizationProductQuery query) {
        
        StringBuilder sql = new StringBuilder("select t from OrganizationProduct t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getOrganizationId())) {
            sql.append(" and  t.organizationId = :organizationId ");
        }
        if(isNotEmpty(query.getProductCode())) {
            sql.append(" and  t.productCode = :productCode ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	

	/**
	 * 获得指定权限组下的所有产品
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Product> getProductList(Long organizationId){
		String hsql = "from OrganizationProduct where organizationId=?";
		List<OrganizationProduct> list = this.findList(hsql, new Object[]{organizationId}, 0, null);
		List<Product> result = new ArrayList<Product>();
		if(null!=list && list.size() > 0){
			for(int i=0;i<list.size();i++){
				Product product = list.get(i).getProduct();
				if(product!=null){
					result.add(product);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获得integer类型的list
	 * @author wqtan
	 */
	public List<String> findIntegerList(final String hsql,final Object[] params){
		return this.getHibernateTemplate().execute(new HibernateCallback<List<String>>(){
			public List<String> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hsql);
				if(ObjectUtils.isNotEmpty(params)){
					for(int i=0;i<params.length;i++){
						query.setParameter(i,params[i]);
					}
				}
				return query.list();
			}
		});
	}
	
}
