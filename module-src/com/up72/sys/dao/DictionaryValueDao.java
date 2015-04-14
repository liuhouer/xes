package com.up72.sys.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.up72.base.GenericDAOHibernate;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.sys.model.DictionaryValue;

/**
 * 数据值信息Hibernate数据处理实现 
 * 
 * @author dream
 * @link 
 * 
 * @version $Revision: 1.00 $ $Date: 2009-05-12 10:02:23
 */
@Repository
public class DictionaryValueDao extends GenericDAOHibernate<DictionaryValue>{
	

	/**
	 * 根据数据值编号查找数据值信息
	 * 
	 * @param dictionaryValueId
	 *            数据值编号
	 * @return DictionaryValue 数据值对象
	 */
	public DictionaryValue findDictionaryValue(long dictionaryValueId){
		return super.get(dictionaryValueId);
	}

	/**
	 * 物理删除数据值(不可恢复)
	 * 
	 * @param dictionaryValueId
	 * @throws DAOException
	 */
	public void deleteDictionaryValues(long[] dictionaryValueIds){
		if (dictionaryValueIds == null || dictionaryValueIds.length == 0)
			return;
		super.executeHql("delete from DictionaryValue where id in ("
				+ parseToSQLStringComma(dictionaryValueIds) + ")");
	}

	/**
	 * 物理删除数据值(不可恢复)
	 * 
	 * @param DictionaryValueId
	 * @throws DAOException
	 */
	public void deleteDictionaryValue(long DictionaryValueId) {
		super.delete(DictionaryValueId);
	}

	/**
	 * 保存数据值(不可恢复)
	 * 
	 * @param DictionaryValueId
	 * @throws DAOException
	 */
	public Serializable saveDictionaryValue(DictionaryValue dictionaryValue){
		return super.getHibernateTemplate().save(dictionaryValue);
	}

	/**
	 * 更新数据值(不可恢复)
	 * 
	 * @param DictionaryValueId
	 * @throws DAOException
	 */
	public void updateDictionaryValue(DictionaryValue dictionaryValue){
		super.update(dictionaryValue);
	}

	/**
	 * 取得数据值总数
	 * 
	 * @return
	 * @throws DAOException
	 */
	public long countDictionaryValue() {
		return super.findcount("select count(id) from DictionaryValue");
	}

	/**
	 * 取得数据值总数
	 * 
	 * @return
	 * @throws DAOException
	 */
	public long countDictionaryValue(long dictionaryId) {
		return super.findcount("select count(id) from DictionaryValue where dictionary.id = ?",
						new Long[] { dictionaryId });
	}

	/**
	 * 根据上级取下级分页列表
	 * 
	 * @param orderMap
	 * @param pagination
	 * @param parentId
	 * @return
	 * @throws DAOException
	 */
	public QueryResult findDictionaryValuesByParent(Map<String, String> orderMap,
		Pagination pagination, long parentId) {
		String hql = "from DictionaryValue d where d.dictionaryValue.id = ?";
		return super.findPage(hql, new Object[] { parentId },pagination,orderMap);
	}

	/**
	 * 根据类别获取数据字典分页列表
	 * 
	 * @param orderMap
	 * @param pagination
	 * @param dictionaryId
	 * @return
	 * @throws DAOException
	 */
	public QueryResult findDictionaryValues(Map<String, String> orders, Pagination pagination,
			long dictionaryId) {
			String hql = "select dv from DictionaryValue dv,Dictionary d where dv.dictionaryId = d.id and  d.id = ?";
			return super.findPage(hql,dictionaryId,pagination,orders);
	}

	/**
	 * 根据类别获取数据字典列表
	 * 
	 * @param orderMap
	 * @param dictionaryId
	 * @return
	 * @throws DAOException
	 */
	public List<DictionaryValue> findDictionaryValues(Map<String, String> orders,
			long dictionaryId) {
		String hql = "select dv from DictionaryValue dv,Dictionary d where dv.dictionaryId = d.id and  d.id = ?";
		return super.findList(hql, dictionaryId, 0, orders);
	}

	/**
	 * 根据上级取下级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws DAOException
	 */
	public List<DictionaryValue> findDictionaryValuesByParent(long parentId) {
		String hql = "from DictionaryValue d where d.parentId = ? order by sortId asc";
		return super.findList(hql, parentId, 0);
	}

	/**
	 * 
	 * @param ids
	 * @return
	 * @throws DAOException
	 */
	public List<DictionaryValue> findDictionaryValue(String ids) {
		String hql = "select dv from DictionaryValue dv where dv.id in ("
				+ ids + ")";
		return this.getHibernateTemplate().find(hql, null);
	}

	/**
	 * 根据字典VALUE求ID
	 */
	public long findIdByValue(String value){
		long id = 0;
		if(!value.equals("")){
			String hql = "from DictionaryValue d where d.value = '"+value+"'";
			List<DictionaryValue> list = super.find(hql);
			if(list != null && list.size() > 0){
				DictionaryValue dv = (DictionaryValue)list.get(0);
				id = dv.getId().longValue();
			}
		}
		return id;
	}
}