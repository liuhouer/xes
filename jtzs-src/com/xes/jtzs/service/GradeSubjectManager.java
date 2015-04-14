/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.dao.GradeDao;
import com.xes.jtzs.dao.GradeSubjectDao;
import com.xes.jtzs.dao.SubjectDao;
import com.xes.jtzs.model.Grade;
import com.xes.jtzs.model.GradeSubject;
import com.xes.jtzs.model.Subject;
import com.xes.jtzs.vo.query.GradeSubjectQuery;
/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class GradeSubjectManager extends BaseManager<GradeSubject,java.lang.Long>{
	@Autowired 
	private GradeDao gradeDao;
	@Autowired 
	private SubjectDao subjectDao;
	private GradeSubjectDao gradeSubjectDao;

	public void setGradeSubjectDao(GradeSubjectDao dao) {
		this.gradeSubjectDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.gradeSubjectDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(GradeSubjectQuery query) {
		return gradeSubjectDao.findPage(query);
	}
	
	public void addGradeSubject(Grade grade){
		List<Subject> subjectList = subjectDao.findAll();
		if(null != subjectList){
			for (int i = 0; i < subjectList.size(); i++) {
				Subject subject = subjectList.get(i);
				GradeSubject gradeSubject = new GradeSubject();
				gradeSubject.setSubjectId(subject.getId());
				gradeSubject.setGradeId(grade.getId());
				gradeSubject.setStatus(JTZSConstants.Pubilc.DISABLE.getIndex());
				gradeSubjectDao.save(gradeSubject);
			}
		}
	}
	
	public void addGradeSubject(Subject subject){
		List<Grade> gradeList = gradeDao.findAll();
		if(null != gradeList){
			for (int i = 0; i < gradeList.size(); i++) {
				Grade grade = gradeList.get(i);
				GradeSubject gradeSubject = new GradeSubject();
				gradeSubject.setSubjectId(subject.getId());
				gradeSubject.setGradeId(grade.getId());
				gradeSubject.setStatus(JTZSConstants.Pubilc.DISABLE.getIndex());
				gradeSubjectDao.save(gradeSubject);
			}
		}
	}

	public void clearStatus(Long subjectId){
		String hql = "update GradeSubject set status = 0 where subjectId = "+subjectId;
		gradeSubjectDao.executeHsql(hql);
	}
	public void updateStatus(Long subjectId,Long gradeId){
		String hql = "update GradeSubject set status = 1 where subjectId = "+subjectId+" and gradeId = "+gradeId;
		gradeSubjectDao.executeHsql(hql);
	}
	
	public void clearStatusByGrade(Long gradeId){
		String hql = "update GradeSubject set status = 0 where gradeId = "+gradeId;
		gradeSubjectDao.executeHsql(hql);
	}
	public List<GradeSubject> getListBySubId(Long subjectId) {
		// TODO Auto-generated method stub
		String hql="from GradeSubject where subjectId=?";
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("gradeId", "asc");
		List<GradeSubject> list = gradeSubjectDao.findList(hql, new Object[]{subjectId}, 0, orders);
		return list;
	}
	public List<GradeSubject> getListByGradeId(Long gradeId) {
		// TODO Auto-generated method stub
		String hql="from GradeSubject where gradeId=?";
		HashMap<String,String> orders = new HashMap<String,String>();
		orders.put("subjectId", "asc");
		List<GradeSubject> list = gradeSubjectDao.findList(hql, new Object[]{gradeId}, 0, orders);
		return list;
	}
	
}
