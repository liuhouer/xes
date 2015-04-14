/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

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

import com.xes.jtzs.JTZSConstants;
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
public class AnswerManager extends BaseManager<Answer,java.lang.Long>{

	private AnswerDao answerDao;

	public void setAnswerDao(AnswerDao dao) {
		this.answerDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.answerDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(AnswerQuery query) {
		return answerDao.findPage(query);
	}
	
	/**
	 * 获得已解答问题的答案
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Answer getAnswerByQuestionId(Long questionId) {
		Answer answer = null;
		List<Answer> list = answerDao.findList("FROM Answer WHERE questionId=? and status=?", new Object[]{questionId,JTZSConstants.AnswerStatus.FINISH.getIndex()});
		if(null != list && list.size()==1){
			answer = list.get(0);
		}
		return answer;
	}
	
	
	
}
