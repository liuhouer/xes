package com.up72.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.GenericManager;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.sys.dao.DictionaryValueDao;
import com.up72.sys.model.DictionaryValue;

/**
 * 数据值信息业务处理实现
 * 
 * @author dream
 * @link
 * 
 * @version $Revision: 1.00 $ $Date: 2009-05-12 10:02:23
 */
@Service
@Transactional
public class DictionaryValueManager extends GenericManager {
	
	@Autowired
	private DictionaryValueDao dictionaryValueDao;

	/**
	 * 创建数据值
	 * 
	 * @param dictionaryValue
	 *            数据值信息
	 */
	public void createDictionaryValue(DictionaryValue dictionaryValue) {
		dictionaryValueDao.save(dictionaryValue);
	}

	/**
	 * 修改数据值
	 * 
	 * @param dictionaryValue
	 *            数据值信息
	 */
	public void editDictionaryValue(DictionaryValue dictionaryValue) {
		DictionaryValue tempDictionaryValue = dictionaryValueDao
				.findDictionaryValue(dictionaryValue.getId());
		BeanUtils.copyProperties(dictionaryValue, tempDictionaryValue);
		dictionaryValueDao.updateDictionaryValue(tempDictionaryValue);
	}

	/**
	 * 根据数据值编号删除数据值(物理删除)
	 * 
	 * @param dictionaryValueId
	 *            数据值编号
	 */
	public void deleteDictionaryValue(long dictionaryValueId) {
		dictionaryValueDao.deleteDictionaryValue(dictionaryValueId);
	}

	/**
	 * 根据数据值编号批量删除数据值(物理删除)
	 * 
	 * @param dictionaryValueId
	 *            数据值编号
	 * @return long 数据操作行数
	 */
	public void deleteDictionaryValues(long[] dictionaryValueIds) {
		dictionaryValueDao.deleteDictionaryValues(dictionaryValueIds);
	}

	/**
	 * 根据数据值编号取得数据值信息
	 * 
	 * @param dictionaryValueId
	 *            数据值编号
	 * @return DictionaryValue 数据值对象
	 */
	public DictionaryValue getDictionaryValue(long dictionaryValueId) {
		DictionaryValue dictionaryValue = new DictionaryValue();
		// dictionaryValue.setValue("");
		if (dictionaryValueId != 0) {
			dictionaryValue = dictionaryValueDao
					.findDictionaryValue(dictionaryValueId);
			return dictionaryValue;
		}
		return dictionaryValue;
	}

	/**
	 * 取得数据值总数
	 * 
	 * @return @
	 */
	public long countDictionaryValue() {
		return dictionaryValueDao.countDictionaryValue();
	}

	/**
	 * 取得数据值总数
	 * 
	 * @return @
	 */
	public long countDictionaryValue(long dictionaryId) {
		return dictionaryValueDao.countDictionaryValue(dictionaryId);
	}

	/**
	 * 取得数据值总数
	 * 
	 * @return @
	 */
	public List<DictionaryValue> getDictionaryValues() {
		return dictionaryValueDao.loadAll();
	}

	/**
	 * 根据上级取下级分页列表
	 * 
	 * @param orderMap
	 * @param pagination
	 * @param parentId
	 * @return @
	 */
	public QueryResult getDictionaryValuesByParent(Map<String, String> orderMap,
			Pagination pagination, long parentId) {
		return dictionaryValueDao.findDictionaryValuesByParent(orderMap,
				pagination, parentId);
	}

	/**
	 * 根据类别获取数据分页列表
	 * 
	 * @param orderMap
	 * @param pagination
	 * @param dicitionaryId
	 * @return @
	 */
	public QueryResult getDictionaryValues(Map<String, String> orderMap, Pagination pagination,
			Integer dicitionaryId) {
		return dictionaryValueDao.findDictionaryValues(orderMap, pagination,
				dicitionaryId);
	}

	public List<DictionaryValue> getDictionaryValues(
			HashMap<String, Object> params, Integer num,
			TreeMap<String, String> orderMap) {
		return dictionaryValueDao.findList(params, num, orderMap);
	}

	/**
	 * 根据类别获取数据列表
	 * 
	 * @param orderMap
	 * @param dictionaryId
	 * @return @
	 */
	public List<DictionaryValue> getDictionaryValues(Map<String, String> orderMap,
			long dictionaryId) {
		return dictionaryValueDao.findDictionaryValues(orderMap, dictionaryId);
	}

	/**
	 * 根据上级取下级列表
	 * 
	 * @param parentId
	 * @return @
	 */
	public List<DictionaryValue> getDictionaryValuesByParent(long parentId) {
		return dictionaryValueDao.findDictionaryValuesByParent(parentId);
	}

	/**
	 * 
	 * @param ids
	 * @return @
	 */
	public List<DictionaryValue> getDictionaryValues(String ids) {
		return dictionaryValueDao.findDictionaryValue(ids);
	}

	/**
	 * 根据字典VALUE求ID
	 * 
	 * @param value
	 * @return
	 */
	public long getIdByValue(String value) {
		return dictionaryValueDao.findIdByValue(value);
	}

	public List<DictionaryValue> findList(HashMap<String, Object> params,
			int num, TreeMap<String, String> orderMap) {
		return dictionaryValueDao.findList(params, num, orderMap);
	}

	public QueryResult findPage(HashMap<String, Object> params,
			Pagination pagination, TreeMap<String, String> orderMap) {
		return dictionaryValueDao.findPage(params, pagination, orderMap);
	}

}
