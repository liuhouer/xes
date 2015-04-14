/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.Product;
import com.up72.auth.vo.query.ProductQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * 产品Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class ProductDao extends BaseHibernateDao<Product,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return Product.class;
	}
	
	/**
	 * @author bxmen
	 * @param query
	 * @return Page
	 * @summary 
	 */
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ProductQuery query) {
        
        StringBuilder sql = new StringBuilder("select t from Product t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getName())) {
            sql.append(" and  t.name like '%" + query.getName() + "%' ");
        }
        if(isNotEmpty(query.getDescription())) {
            sql.append(" and  t.description = :description ");
        }
        if(isNotEmpty(query.getSortColumns())) {
        	sql.append(" order by :sortColumns ");
        }	
		return pageQuery(sql.toString(),query);
	}
	
	public Product getProductByCode(String code){
		if(null != code && !code.trim().equals("")){
			return this.findByProperty("code", code);
		}else{
			return null;
		}
	}
}
