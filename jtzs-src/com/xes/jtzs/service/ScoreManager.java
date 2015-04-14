/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.base.UserDetails;
import com.up72.framework.page.Page;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.dao.CommonRuleDao;
import com.xes.jtzs.dao.ScoreDao;
import com.xes.jtzs.dao.ScoreLogDao;
import com.xes.jtzs.model.CommonRule;
import com.xes.jtzs.model.Score;
import com.xes.jtzs.model.ScoreLog;
import com.xes.jtzs.vo.query.ScoreQuery;
/**
 * 业务处理
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class ScoreManager extends BaseManager<Score,java.lang.Long>{
	@Autowired
	private CommonRuleDao commonRuleDao;
	@Autowired
	private ScoreLogDao scoreLogDao;
	private ScoreDao scoreDao;

	public void setScoreDao(ScoreDao dao) {
		this.scoreDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.scoreDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(ScoreQuery query) {
		return scoreDao.findPage(query);
	}
	
	/**
	 * 后台添加积分
	 * @return
	 */
	@Transactional
	public synchronized String addScore(Long scoreId,int addScore,int scoreType,String remark,UserDetails user){
		String str = "操作失败";
		if(null!=scoreId){
			Score score = scoreDao.getById(scoreId);
			if(scoreType==JTZSConstants.ScoreType.ADD.getIndex()){
				score.setScore(score.getScore()+addScore);
				ScoreLog sl = new ScoreLog();
				sl.setContent("管理员'"+user.getUserName()+"'为您增加了"+addScore+"积分，请查收！");	
				sl.setOperatorId(user.getId());
				sl.setRemark(remark);
				sl.setScoreType(JTZSConstants.ScoreType.ADD.getIndex());
				sl.setScore(addScore);
				sl.setUserId(score.getUserId());
				sl.setUserRole(score.getUserRole());
				sl.setAddTime(new Date().getTime());
				scoreDao.update(score);
				scoreLogDao.save(sl);
				str = "操作成功";
			}else if(scoreType==JTZSConstants.ScoreType.DEL.getIndex()){
				ScoreLog sl = new ScoreLog();
				sl.setOperatorId(user.getId());
				sl.setRemark(remark);
				sl.setScoreType(JTZSConstants.ScoreType.DEL.getIndex());
				sl.setUserId(score.getUserId());
				sl.setUserRole(score.getUserRole());
				sl.setAddTime(new Date().getTime());
				if(score.getRemainScore() >= addScore ){
					score.setUseScore(score.getUseScore() + addScore);
					sl.setContent("管理员'"+user.getUserName()+"'删除了您"+addScore+"积分！");
					sl.setScore(addScore);
					str = "操作成功";
				}else{
					score.setUseScore(score.getUseScore() + score.getRemainScore());
					sl.setContent("管理员'"+user.getUserName()+"'删除了您"+score.getRemainScore()+"积分！");
					sl.setScore(score.getRemainScore().intValue());
					str = "积分不够,值扣除剩余积分";
				}
				scoreDao.update(score);
				scoreLogDao.save(sl);
			}
		}
		return str;
	}
	
	/**
	 * 添加扣除学生积分
	 * @author liutongling
	 * 
	 */
	@Transactional
	public synchronized boolean addStudentScore(long studentId,CommonRule commonRule){
		boolean result = false;
		Score score = getStudentScore(studentId);
		if(null == score || null==score.getId()){
			score = new Score();
			score.setUserId(studentId);
			score.setUserRole(JTZSConstants.ROLE_STUDENT);
			score.setScore(0L);
			score.setUseScore(0L);
		}
		if(commonRuleDao.isValid(commonRule)){
			ScoreLog  scoreLog = new ScoreLog();
			scoreLog.setAddTime(new Date().getTime());
			scoreLog.setUserRole(JTZSConstants.ROLE_STUDENT);
			scoreLog.setUserId(studentId);
			scoreLog.setScore(commonRule.getScore());
			scoreLog.setContent(getScoreLogContent(JTZSConstants.ROLE_STUDENT, commonRule));
			if(commonRule.getScoreType()== JTZSConstants.ScoreType.ADD.getIndex()){
				scoreLog.setScoreType(JTZSConstants.ScoreType.ADD.getIndex());
				score.setScore(score.getScore() + commonRule.getScore());
				scoreDao.saveOrUpdate(score);
				scoreLogDao.save(scoreLog);
				result = true;
			}else if(commonRule.getScoreType()== JTZSConstants.ScoreType.DEL.getIndex()){
				if(score.getScore() >= score.getUseScore() + commonRule.getScore()){
					scoreLog.setScoreType(JTZSConstants.ScoreType.DEL.getIndex());
					score.setUseScore(score.getUseScore() + commonRule.getScore());
					scoreDao.saveOrUpdate(score);
					scoreLogDao.save(scoreLog);
					result = true;
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 添加老师积分
	 * @author liutongling
	 * 
	 */
	@Transactional
	public synchronized void addTeacherScore(long teacherId,CommonRule commonRule){
		Score score = getTeacherScore(teacherId);
		if(null == score){
			score = new Score();
			score.setUserId(teacherId);
			score.setUserRole(JTZSConstants.ROLE_TEACHER);
			score.setScore(0L);
			score.setUseScore(0L);
		}
		if(commonRuleDao.isValid(commonRule)){
			ScoreLog  scoreLog = new ScoreLog();
			scoreLog.setAddTime(new Date().getTime());
			scoreLog.setUserRole(JTZSConstants.ROLE_TEACHER);
			scoreLog.setUserId(teacherId);
			scoreLog.setScore(commonRule.getScore());
			scoreLog.setContent(getScoreLogContent(JTZSConstants.ROLE_TEACHER, commonRule));
			if(commonRule.getScoreType()== JTZSConstants.ScoreType.ADD.getIndex()){
				scoreLog.setScoreType(JTZSConstants.ScoreType.ADD.getIndex());
				score.setScore(score.getScore() + commonRule.getScore());
				scoreDao.saveOrUpdate(score);
				scoreLogDao.save(scoreLog);
			}else if(commonRule.getScoreType()== JTZSConstants.ScoreType.DEL.getIndex()){
				scoreLog.setScoreType(JTZSConstants.ScoreType.DEL.getIndex());
				if(score.getScore() >= score.getUseScore() + commonRule.getScore()){
					score.setUseScore(score.getUseScore() + commonRule.getScore());
				}else{
					score.setUseScore(score.getScore());
				}
				scoreDao.saveOrUpdate(score);
				scoreLogDao.save(scoreLog);
			}
		}
	}
	
	private String getScoreLogContent(int userRole,CommonRule commonRule){
		StringBuilder sb = new StringBuilder();
		if(userRole == JTZSConstants.ROLE_STUDENT.intValue() ){
			if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.Expert.getIndex()){
				sb.append("您刚刚提问了一道问题 ").append("限时：专家解答");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.QuestionScore.getIndex()){
				sb.append("您刚刚提问了一道问题 ").append("限时：").append(commonRule.getMinute()).append("分钟");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.StudentComment.getIndex()){
				sb.append("您刚刚评论了一道问题 ");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.StudentEveryDayLogin.getIndex()){
				sb.append("您今天登录了本软件 ");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.StudentFirstRegist.getIndex()){
				sb.append("您首次注册成功本软件 ");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.StudentInviteFriends.getIndex()){
				sb.append("您刚刚邀请了好友 ");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.StudentShareQuestion.getIndex()){
				sb.append("您刚刚分享了问题 ");
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.StudentShareSoftware.getIndex()){
				sb.append("您刚刚分享了本软件 ");
			}
			if(commonRule.getScoreType()==JTZSConstants.ScoreType.ADD.getIndex()){
				sb.append("，获得积分：").append(commonRule.getScore());
			}else if(commonRule.getScoreType()==JTZSConstants.ScoreType.DEL.getIndex()){
				sb.append("，消耗积分：").append(commonRule.getScore());
			}
		}else if(userRole == JTZSConstants.ROLE_TEACHER.intValue()){
			if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.Expert.getIndex()){
				sb.append("您刚刚解答了一道问题 ").append("限时：专家解答").append("，获得积分：").append(commonRule.getScore());
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.QuestionScore.getIndex()){
				sb.append("您刚刚解答了一道问题 ").append("限时：").append(commonRule.getMinute()).append("分钟");
				sb.append("，获得积分：").append(commonRule.getScore());
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.TeacherShare.getIndex()){
				sb.append("您刚刚分享了本软件给学生 ").append("，获得积分：").append(commonRule.getScore());
			}else if(commonRule.getRuleType() == JTZSConstants.CommonRuleType.TeacherQuitAnswer.getIndex()){
				sb.append("您刚刚放弃了一道问题 ").append("，扣除积分：").append(commonRule.getScore());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取对应角色的积分
	 */
	@Transactional(readOnly = true)
	public Score getScoreByRole(long userId,int userRole) {
		Score score = null;
		List<Score> list = scoreDao.findList("FROM Score WHERE userId = ? AND userRole = ?", new Object[]{userId,userRole});
		if(list.size()>0){
			score = list.get(0);
		}	
		if(null == score){
			score = new Score();
		}
		return score;
	}
	
	/**
	 * 获取学生个人信息de积分
	 * @author liutongling
	 * @param studentId
	 * @return null
	 */
	@Transactional(readOnly = true)
	public Score getStudentScore(long studentId) {
		return getScoreByRole(studentId, JTZSConstants.ROLE_STUDENT);
	}
	
	/**
	 * 获取老师个人信息de积分
	 * @author liutongling
	 * @param teacherId
	 * @return null
	 */
	@Transactional(readOnly = true)
	public Score getTeacherScore(long teacherId) {
		return getScoreByRole(teacherId, JTZSConstants.ROLE_TEACHER);
	}

}
