package com.up72.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.up72.base.GenericDAOHibernate;
import com.up72.sys.model.Dictionary;

/**
 * 数据字典信息Hibernate数据处理实现 
 * 
 * @author dream
 * @link 
 * 
 * @version $Revision: 1.00 $ $Date: 2009-05-12 10:02:23
 */
@Repository
public class DictionaryDao extends GenericDAOHibernate<Dictionary>{
	
	/**
	 * 根据数据字典编号查找数据字典信息
	 * 
	 * @param dictionaryId
	 *            数据字典编号
	 * @return Dictionary 数据字典对象
	 */
	public Dictionary findDictionary(long dictionaryId) {
			return super.get(dictionaryId);
	}

	/**
	 * 物理删除数据字典(不可恢复)
	 * 
	 * @param dictionaryId
	 * @throws DAOException
	 */
	public void deleteDictionarys(long[] dictionaryIds) {
			if (dictionaryIds == null || dictionaryIds.length == 0)
				return;
			super.executeHql("delete from Dictionary where id in ("+ parseToSQLStringComma(dictionaryIds) + ")");
	}

	/**
	 * 物理删除数据字典(不可恢复)
	 * 
	 * @param DictionaryId
	 * @throws DAOException
	 */
	public void deleteDictionary(long DictionaryId) {
			super.delete(DictionaryId);
	}

	/**
	 * 保存数据字典(不可恢复)
	 * 
	 * @param DictionaryId
	 * @throws DAOException
	 */
	public void saveDictionary(Dictionary dictionary) {
			super.save(dictionary);
	}

	/**
	 * 更新数据字典(不可恢复)
	 * 
	 * @param DictionaryId
	 * @throws DAOException
	 */
	public void updateDictionary(Dictionary dictionary) {
			super.update(dictionary);
	}

	/**
	 * 取得数据字典总数
	 * 
	 * @return
	 * @throws DAOException
	 */
	public long countDictionary() {
			return super.findcount("select count(id) from Dictionary");
	}

	
	public List<Dictionary> findCategoriesByParentAndSite(long parentId)  {
		List<Dictionary> result = null;
		StringBuffer hql = new StringBuffer("from Dictionary where 1=1");
		if(parentId != 0){
			hql.append(" and dictionaryId = ? order by sort asc");
			result = super.find(hql.toString(), new Object[]{parentId});
		}else{
			hql.append(" and (dictionaryId = 0 or dictionaryId is null) order by sort asc");
			result = super.find(hql.toString(),null);
		}
		return result;
	}
	
	/**
	 * 根据数据字典信息编号取得数据字典信息信息
	 * 
	 * @param code
	 * @return
	 * @throws DAOException
	 */
	public Dictionary findDictionary(String code) {
		List<Dictionary> list = super.find("from Dictionary where code = ?", code);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 根据数据字典信息名称取得数据字典信息信息
	 * 
	 * @param name
	 * @return
	 * @throws DAOException
	 */
	public Dictionary findDictionaryByName(String name) {
		List<Dictionary> list = super.find("from Dictionary where name = ?", name);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}
	/**
	 * 根据数据key取得数据字典信息信息
	 * 
	 * @param key
	 * @return
	 * @throws DAOException
	 */
	public Dictionary findDictionaryByKey(String key) {
		List<Dictionary> list = super.find("from Dictionary where dictionaryKey = ?", key);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}
	/**
	 * 取得数据字典信息信息
	 * @return
	 * @throws DAOException
	 */
	public Dictionary findDictionaryByHql(String hql) {
		List<Dictionary> list = super.find(hql);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}
}