/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import com.up72.base.*;
import com.up72.framework.page.*;
import com.xes.jtzs.model.*;
import com.xes.jtzs.dao.*;
import com.xes.jtzs.vo.query.*;
/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class TeacherStatisticsManager extends BaseManager<TeacherStatistics,java.lang.Long>{
	@Autowired
	private TeacherDao teacherDao;
	private TeacherStatisticsDao teacherStatisticsDao;

	public void setTeacherStatisticsDao(TeacherStatisticsDao dao) {
		this.teacherStatisticsDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.teacherStatisticsDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(TeacherStatisticsQuery query) {
		return teacherStatisticsDao.findPage(query);
	}
	
	public void delTeacherStatisticsByCommentId(Long commentId){
		TeacherStatistics teacherStatistics = teacherStatisticsDao.findByProperty("commentId", commentId);
		if(null!=teacherStatistics){
			teacherStatisticsDao.deleteById(teacherStatistics.getId());
		}
	}
	
	/**
	 * 添加浏览量
	 * @param teacherId
	 */
	@Transactional
	public void addShow(Long teacherId) {
		TeacherStatistics teacherStatistics = new TeacherStatistics();
		teacherStatistics.setAddTime(new Date().getTime());
		teacherStatistics.setTeacherId(teacherId);
		teacherStatistics.setShowNum(1);
		teacherStatisticsDao.save(teacherStatistics);
	}
	
	/**
	 * 添加回答量
	 * @param teacherId
	 */
	@Transactional
	public void addAnswer(Long teacherId,CommonRule commonRule) {
		TeacherStatistics teacherStatistics = new TeacherStatistics();
		teacherStatistics.setAddTime(new Date().getTime());
		teacherStatistics.setTeacherId(teacherId);
		teacherStatistics.setAnswerNum(1);
		if(null!=commonRule){
			if(commonRule.getMinute()==20){
				teacherStatistics.setTwentyMinNum(1);
			}else if(commonRule.getMinute()==30){
				teacherStatistics.setHalfHourNum(1);
			}else if(commonRule.getMinute()==60){
				teacherStatistics.setOneHourNum(1);
			}
		}
		
		teacherStatisticsDao.save(teacherStatistics);
	}
	
	/**
	 * 添加专家回答量
	 * @param teacherId
	 */
	@Transactional
	public void addExpertAnswer(Long teacherId) {
		TeacherStatistics teacherStatistics = new TeacherStatistics();
		teacherStatistics.setAddTime(new Date().getTime());
		teacherStatistics.setTeacherId(teacherId);
		teacherStatistics.setExpertNum(1);
		teacherStatisticsDao.save(teacherStatistics);
	}
	
	/**
	 * 添加放弃回答量
	 * @param teacherId
	 */
	@Transactional
	public void addQuitAnswer(Long teacherId) {
		TeacherStatistics teacherStatistics = new TeacherStatistics();
		teacherStatistics.setAddTime(new Date().getTime());
		teacherStatistics.setTeacherId(teacherId);
		teacherStatistics.setQuitNum(1);
		teacherStatisticsDao.save(teacherStatistics);
	}
	
	/**
	 * 添加满意
	 * @param teacherId
	 */
	@Transactional
	public void addSatisfy(Long teacherId,Long commentId) {
		TeacherStatistics teacherStatistics = new TeacherStatistics();
		teacherStatistics.setAddTime(new Date().getTime());
		teacherStatistics.setTeacherId(teacherId);
		teacherStatistics.setSatisfy(1);
		teacherStatistics.setCommentId(commentId);
		teacherStatisticsDao.save(teacherStatistics);
	}
	
	/**
	 * 添加不满意
	 * @param teacherId
	 */
	@Transactional
	public void addUnsatisfy(Long teacherId,Long commentId) {
		TeacherStatistics teacherStatistics = new TeacherStatistics();
		teacherStatistics.setAddTime(new Date().getTime());
		teacherStatistics.setTeacherId(teacherId);
		teacherStatistics.setUnsatisfy(1);
		teacherStatistics.setCommentId(commentId);
		teacherStatisticsDao.save(teacherStatistics);
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findStatisticsPage(String loginName, int level, long startAddTime, long stopAddTime,int pageStartNum,int pageSize,String order) {
		StringBuilder countSql = new StringBuilder("select count(t.id) from Teacher t WHERE 1=1 ");
		StringBuilder sql = new StringBuilder("select t.ID AS id,t.LEVEL AS level,t.login_name, sum(ts.SATISFY) AS satisfy,sum(ts.UNSATISFY) AS unsatisfy");
		sql.append(", sum(ts.SHOW_NUM) AS showNum, sum(ts.ANSWER_NUM) AS answerNum, sum(ts.TWENTY_MIN_NUM) AS twentyMinNum");
		sql.append(", sum(ts.HALF_HOUR_NUM) AS halfHourNum, sum(ts.ONE_HOUR_NUM) AS oneHourNum, sum(ts.EXPERT_NUM) AS expertNum, sum(ts.QUIT_NUM) AS quitNum");
		sql.append(" from ( xes_jtzs_teacher as t left join xes_jtzs_teacher_statistics as ts ");
		sql.append(" on( t.ID = ts.TEACHER_ID ");
		if(stopAddTime > 0 && startAddTime > 0){
			sql.append(" and (ts.add_time>=").append(startAddTime).append(" and ts.add_time<=").append(stopAddTime).append(")");
		}
		sql.append(")) ");
		if(!"".equals(loginName)){
			sql.append(" where login_name like '%").append(loginName).append("%'");
			countSql.append(" and t.loginName like '%").append(loginName).append("%'");
		}
		if(level > 0){
			sql.append(" and t.level=").append(level);
			countSql.append(" and t.level=").append(level);
		}
		sql.append(" group by t.ID");
		if(!"".equals(order)){
			sql.append(" order by ").append(order);
		}
		List<Object[]> obj = teacherStatisticsDao.findListBySQL(sql.toString(), null, 0, null);
		Long count = teacherDao.countByAggregate(countSql.toString());
		int pageNum = pageStartNum % pageSize == 0 ? pageStartNum / pageSize : pageStartNum / pageSize + 1;
		pageNum++;
		return new Page(pageNum, pageSize, count.intValue(),obj);
	}
}
