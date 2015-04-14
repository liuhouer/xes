package com.up72.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author up72
 */
@Transactional
public abstract class BaseManager <E,PK extends Serializable>{
	
	protected Log log = LogFactory.getLog(getClass());

	@SuppressWarnings({ "unchecked" })
	protected abstract EntityDao getEntityDao();

	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public E getById(PK id) throws DataAccessException{
		return (E)getEntityDao().getById(id);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public List<E> findAll() throws DataAccessException{
		return getEntityDao().findAll();
	}
	
	/** 根据id检查是否插入或是更新数据 */
	@SuppressWarnings({ "unchecked" })
	public void saveOrUpdate(E entity) throws DataAccessException{
		getEntityDao().saveOrUpdate(entity);
	}
	
	/** 插入数据 */
	@SuppressWarnings({ "unchecked" })
	public Long save(E entity) throws DataAccessException{
		return (Long)getEntityDao().save(entity);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void removeById(PK id) throws DataAccessException{
		getEntityDao().deleteById(id);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void update(E entity) throws DataAccessException{
		getEntityDao().update(entity);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
	
	protected String parseToSQLStringComma(Long[] strArray) {
        if (strArray == null || strArray.length == 0)
            return "";
        String myStr = "";
        for (int i = 0; i < strArray.length - 1; i++) {
            myStr += "'" + strArray[i] + "',";
        }
        myStr += "'" + strArray[strArray.length - 1] + "'";
        return myStr;
    }
	
	@SuppressWarnings("unchecked")
	public List<E> findList(HashMap<String,Object> params,Integer num,TreeMap<String,String> orderMap){
		return getEntityDao().findList(params, num, orderMap);
	}

	
}
