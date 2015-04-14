/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentManager extends BaseManager<Comment,java.lang.Long>{
	@Autowired
	private TeacherStatisticsManager teacherStatisticsManager;
	@Autowired
	private CommonRuleManager commonRuleManager;
	@Autowired
	private ScoreManager scoreManager;
	private CommentDao commentDao;

	public void setCommentDao(CommentDao dao) {
		this.commentDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.commentDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(CommentQuery query) {
		return commentDao.findPage(query);
	}
	
	/**
	 * 提交评论
	 * @return
	 */
	@Transactional
	public String commitComment(Comment comment){
		String code = JTZSConstants.ErrorCode.gg500;
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("answerId", comment.getAnswerId());
		params.put("studentId", comment.getStudentId());
		Comment hasComment = commentDao.findByProperties(params);
		if(null == hasComment){
			commentDao.save(comment);
			scoreManager.addStudentScore(comment.getStudentId(),commonRuleManager.getCacheRuleByType(JTZSConstants.CommonRuleType.StudentComment.getIndex()));
			if(comment.getIsSatisfied()==JTZSConstants.SatisfiedStatus.SATISFIED.getIndex()){
				teacherStatisticsManager.addSatisfy(comment.getTeacherId(),comment.getId());
			}else{
				teacherStatisticsManager.addUnsatisfy(comment.getTeacherId(),comment.getId());
			}
			code = JTZSConstants.ErrorCode.gg000;
		}else{
			code = JTZSConstants.ErrorCode.xs004;
		}
		return code;
	}
	
}
