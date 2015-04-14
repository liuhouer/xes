/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.up72.common.CommonUtils.*;
import com.up72.framework.beanutils.BeanUtils;

import java.util.*;

import com.up72.base.*;
import com.up72.util.*;

import com.up72.framework.util.*;
import com.up72.framework.web.util.*;
import com.up72.framework.page.*;
import com.up72.framework.page.impl.*;

import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.service.*;
import com.xes.jtzs.vo.query.*;/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class KnowledgeManager extends BaseManager<Knowledge,java.lang.Long>{

	private KnowledgeDao knowledgeDao;

	public void setKnowledgeDao(KnowledgeDao dao) {
		this.knowledgeDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.knowledgeDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(KnowledgeQuery query) {
		return knowledgeDao.findPage(query);
	}
	
	/**
	 * 判断是否唯一
	 * @author 
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUniqueName(Long id,String knowledge1,Long subjectId,Long gradeId) {
		boolean unique = true;
		Long size = null;
		if(StringUtils.isNotEmpty(knowledge1)){
			StringBuilder sb = new StringBuilder("select count(id) from Knowledge where knowledge1=? and subjectId=? and gradeId=?");
			if(null != id){//id不是空，编辑
				sb.append(" and id!=?");
				size = knowledgeDao.countByAggregate(sb.toString(), new Object[]{knowledge1,subjectId,gradeId,id});
			}else{//新增
				size = knowledgeDao.countByAggregate(sb.toString(), new Object[]{knowledge1,subjectId,gradeId});
			}
		}
		if( null == size || size > 0){
			unique = false;
		}
		return unique;
	}
	
}
