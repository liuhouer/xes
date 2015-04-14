/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.auth.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.up72.auth.model.ProductAbout;
import com.up72.auth.vo.query.ProductAboutQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * InnoDB free: 263168 kBHibernate数据处理
 * 
 * @author auth
 * @version 1.0
 * @since 1.0
 */
@Repository
public class ProductAboutDao extends BaseHibernateDao<ProductAbout,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return ProductAbout.class;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ProductAboutQuery query) {
        StringBuilder sql = new StringBuilder("select t from ProductAbout t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getProductCode())) {
            sql.append(" and  t.productCode = :productCode ");
        }
        if(isNotEmpty(query.getTitle())) {
            sql.append(" and  t.title = :title ");
        }
        if(isNotEmpty(query.getContent())) {
            sql.append(" and  t.content = :content ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" /~ order by [sortColumns] ~/ ");
        }	
        
		return pageQuery(sql.toString(),query);
	}
	/**
	 * 根据productCode获取ProductAbout
	 * @author wgf
	 * @param productCode
	 * @return
	 */
	public List<ProductAbout> getProductAboutByProductCode(String productCode){
		List<ProductAbout> result = null;
		if(null != productCode && !productCode.trim().equals("")){
			String hql = "from ProductAbout where productCode = ?";
			result = this.findList(hql,new Object[]{productCode});
			return result;
		}else{
			return null;
		}
	}

}
