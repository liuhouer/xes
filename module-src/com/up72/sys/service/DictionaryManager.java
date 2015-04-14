package com.up72.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.GenericManager;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.sys.dao.DictionaryDao;
import com.up72.sys.model.Dictionary;

/**
 * 数据字典信息业务处理实现
 * 
 * @author dream
 * @link
 * 
 * @version $Revision: 1.00 $ $Date: 2009-05-12 10:02:23
 */
@Service
@Transactional
public class DictionaryManager extends GenericManager {
	
	@Autowired
	private DictionaryDao dictionaryDao;

	/**
	 * 创建数据字典
	 * 
	 * @param dictionary
	 *            数据字典信息
	 */
	public void createDictionary(Dictionary dictionary) {
		dictionaryDao.saveDictionary(dictionary);
	}

	/**
	 * 修改数据字典
	 * 
	 * @param dictionary
	 *            数据字典信息
	 */
	public void editDictionary(Dictionary dictionary) {
		Dictionary tempDictionary = dictionaryDao.findDictionary(dictionary
				.getId());
		BeanUtils.copyProperties(dictionary, tempDictionary);
		dictionaryDao.updateDictionary(tempDictionary);
	}

	/**
	 * 根据数据字典编号删除数据字典(物理删除)
	 * 
	 * @param dictionaryId
	 *            数据字典编号
	 */
	public void deleteDictionary(long dictionaryId) {
		dictionaryDao.deleteDictionary(dictionaryId);
	}

	/**
	 * 根据数据字典编号批量删除数据字典(物理删除)
	 * 
	 * @param dictionaryId
	 *            数据字典编号
	 * @return int 数据操作行数
	 */
	public void deleteDictionarys(long[] dictionaryIds) {
		dictionaryDao.deleteDictionarys(dictionaryIds);
	}

	/**
	 * 根据数据字典编号取得数据字典信息
	 * 
	 * @param dictionaryId
	 *            数据字典编号
	 * @return Dictionary 数据字典对象
	 */
	public Dictionary getDictionary(long dictionaryId) {
		return dictionaryDao.findDictionary(dictionaryId);
	}
	/**
	 * 根据数据key取得数据字典信息
	 * 
	 */
	public Dictionary getDictionaryByKey(String key) {
		return dictionaryDao.findDictionaryByKey(key);
	}

	
	public List<Dictionary> findCategoriesByTree(long dictionaryId){
		List<Dictionary> result = new LinkedList<Dictionary>();
		
		if(dictionaryId > 0){
			Dictionary dictionary = this.getDictionary(dictionaryId);
			
			if(null != dictionary){
				result.add(dictionary);
			}
		}
		List<Dictionary> childCats = dictionaryDao.findCategoriesByParentAndSite(dictionaryId);
		
		if(null != childCats && !childCats.isEmpty()){
			for(int i=0;i<childCats.size();i++){
				List<Dictionary> temp = findCategoriesByTree(childCats.get(i).getId());
				if(null != temp && !temp.isEmpty()){
					result.addAll(temp);
				}
			}
		}
		
		return result;
	}
	

	/**
	 * 取得数据字典总数
	 * 
	 * @return @
	 */
	public long countDictionary() {
		return dictionaryDao.countDictionary();
	}
	/**
	 * 根据数据key取得数据字典信息信息
	 * @author wgf
	 * 
	 */
	public Dictionary findDictionaryByKey(String key) {
		return dictionaryDao.findDictionaryByKey(key);
	}

	/**
	 * 取得数据字典总数
	 * 
	 * @return @
	 */
	public List<Dictionary> getDictionarys() {
		return dictionaryDao.loadAll();
	}

	/**
	 * 根据数据字典信息编号取得数据字典信息信息
	 * 
	 * @param code
	 * @return @
	 */
	public Dictionary getDictionary(String code) {
		return dictionaryDao.findDictionary(code);
	}

	/**
	 * 根据数据字典信息名称取得数据字典信息信息
	 * 
	 * @param name
	 * @return @
	 */
	public Dictionary getDictionaryByName(String name) {
		return dictionaryDao.findDictionaryByName(name);
	}

	public List<Dictionary> findList(HashMap<String, Object> params, int num,
			TreeMap<String, String> orderMap) {
		return dictionaryDao.findList(params, num, orderMap);
	}

	public QueryResult findPage(HashMap<String, Object> params,
			Pagination pagination, TreeMap<String, String> orderMap) {
		return dictionaryDao.findPage(params, pagination, orderMap);
	}
	
	/**
	 *标题唯一性验证
	 *@author wgf
	 */
	@Transactional(readOnly = true)
	public boolean validate(String propertyName, String value, Long id) {
		boolean result = true;
		String hql="from Dictionary where "+propertyName+" = '"+value+"'";
		Dictionary Dictionary = dictionaryDao.findDictionaryByHql(hql);
		if(null != Dictionary){
			String tempValue = Dictionary.getDictionaryKey();
			if(id.equals(Dictionary.getId())){
				result = true;
			}else if(value.trim().equals(tempValue)){
				result = false;
			}
		}else{
			result = true;
		}
		return result;
	}
	/**
	 * 根据key值获取字典值
	 * @author wgf
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		String result = null;
		Dictionary dictionary = getDictionaryByKey(key);
		if (null != dictionary) {
			result = dictionary.getRefCode();
		}
		return result;
	}
	
	/**
	 * 获得子级字典信息信息
	 * 
	 * @param key
	 * @return
	 * @throws DAOException
	 */
	public List<Dictionary> findChilds(Long id) {
		List<Dictionary> result = new ArrayList<Dictionary>();
		if (null != id && id != 0) {
			result = dictionaryDao.find("from Dictionary where dictionaryId = ?", id);
		}
			return result;
	}
	/**
	 *编辑字典信息信息
	 * 
	 */
	public void updateDictionary(String key ,String value){
		Dictionary dictionary = this.getDictionaryByKey(key);
		if (null != dictionary) {
			dictionary.setRefCode(value);
			dictionaryDao.update(dictionary);
		}
	}
}
