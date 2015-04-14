/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2013
 */

package com.xes.jtzs.model;

import static com.up72.common.CommonUtils.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.up72.base.*;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.xes.jtzs.JTZSConstants;
import com.xes.jtzs.dao.AnswerDao;
import com.xes.jtzs.dao.CommentDao;
import com.xes.jtzs.service.CityManager;
import com.xes.jtzs.service.GradeManager;
import com.xes.jtzs.service.ProvinceManager;
import com.xes.jtzs.service.ScoreManager;
import com.xes.jtzs.service.SubjectManager;

/**
 * 
 * 
 * @author 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "xes_jtzs_teacher")
public class Teacher extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "教师";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PROVINCE_ID = "省份";
	public static final String ALIAS_CITY_ID = "地区";
	public static final String ALIAS_AREA_ID = "地区";
	public static final String ALIAS_LEVEL = "级别";//1为普通老师，2为专家
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_REAL_NAME = "真实姓名";
	public static final String ALIAS_LOGIN_NAME = "登录帐号";
	public static final String ALIAS_EXPERT_GRADE_IDS = "擅长年级";
	public static final String ALIAS_EXPERT_SUBJECT_ID = "擅长学科";
	public static final String ALIAS_SEX = "性别";//0为女，1为男
	public static final String ALIAS_IMG_PATH = "头像";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_ADD_TIME = "添加时间";
	public static final String ALIAS_PASSWORD = "登录密码";
	public static final String ALIAS_FREE_CYCLE = "空闲周期";
	public static final String ALIAS_FREE_START_TIME = "空闲开始时间";
	public static final String ALIAS_FREE_STOP_TIME = "空闲结束时间";
	public static final String ALIAS_VALID_START_TIME = "有效开始时间";
	public static final String ALIAS_VALID_STOP_TIME = "有效结束时间";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	public static final String FORMAT_LAST_LOGIN_TIME = DATE_FORMAT;
	public static final String FORMAT_ADD_TIME = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** 省份ID*/
	
	private java.lang.Long provinceId;
	
	/** 地区ID*/
	
	private java.lang.Long areaId;
	/** 城市ID*/
	
	private java.lang.Long cityId;
	
	/** 老师级别，1为普通老师，2为专家*/
	@Max(127)
	private Integer level;
	
	/** 昵称*/
	@Length(max=50)
	private java.lang.String nickName;
	
	/** 真实姓名*/
	@Length(max=50)
	private java.lang.String realName;
	
	/** 登录帐号*/
	@Length(max=50)
	private java.lang.String loginName;
	
	/** 擅长年级*/
	@Length(max=200)
	private java.lang.String expertGradeIds;
	
	/** 擅长学科*/
	
	private java.lang.Long expertSubjectId;
	
	/** 性别,0为女，1为男*/
	@Max(127)
	private Integer sex;
	
	/** 头像*/
	@Length(max=200)
	private java.lang.String imgPath;
	
	/** 最后登录时间*/
	
	private java.lang.Long lastLoginTime;
	
	/** 添加时间*/
	
	private java.lang.Long addTime;
	
	/** 登录密码*/
	@Length(max=50)
	private java.lang.String password;
	
	/** 状态,0为正常，1为冻结*/
	@Max(127)
	private Integer status;
	
	/** 空闲周期*/
	@Length(max=100)
	private java.lang.String freeCycle;
	/** 空闲开始时间*/
	private java.lang.Long freeStartTime;
	/** 空闲结束时间*/
	private java.lang.Long freeStopTime;
	/** 有效开始时间*/
	private java.lang.Long validStartTime;
	/** 有效结束时间*/
	private java.lang.Long validStopTime;
	@Length(max=500)
	private java.lang.String token;
	
	@Transient
	public String getShengfen(){
		return this.getProvince().getName();
	}
	
	@Transient
	public String getScnj(){
		String str = "";
		for(int i=0;i<this.getExpertGradeList().size();i++){
		  str+=this.getExpertGradeList().get(i).getName()+" ";
		}
		return str;
	}
	
	@Transient
	public String getScxk(){
		return this.getExpertSubject().getName();
	}
	
	
	//columns END

	@Transient
	public String  getAddTimeStrs() {
		if(this.addTime!=null){
		Date d = new Date(this.addTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss");
			return sdf.format(d);
		}else{
			return null;
		}
	}
	
	@Transient
	public String getLastLoginTimeStrs() {
		if(this.lastLoginTime!=null){
		Date d =  new Date(this.lastLoginTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss");
			return sdf.format(d);
		}else{
			return null;
		}
	}

	public Teacher(){
	}

	public Teacher(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public java.lang.Long getId() {
		return this.id;
	}
	
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Long value) {
		this.provinceId = value;
	}
	
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.Long value) {
		this.areaId = value;
	}
	
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Long value) {
		this.cityId = value;
	}
	
	@Column(name = "LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(Integer value) {
		this.level = value;
	}
	
	@Column(name = "NICK_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	@Column(name = "TOKEN", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getToken() {
		return this.token;
	}
	
	public void setToken(java.lang.String value) {
		this.token = value;
	}
	
	@Column(name = "REAL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	@Column(name = "LOGIN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	@Column(name = "EXPERT_GRADE_IDS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getExpertGradeIds() {
		return this.expertGradeIds;
	}
	
	public void setExpertGradeIds(java.lang.String value) {
		this.expertGradeIds = value;
	}
	
	@Column(name = "EXPERT_SUBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getExpertSubjectId() {
		return this.expertSubjectId;
	}
	
	public void setExpertSubjectId(java.lang.Long value) {
		this.expertSubjectId = value;
	}
	
	@Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSex() {
		return this.sex;
	}
	
	public void setSex(Integer value) {
		this.sex = value;
	}
	
	@Column(name = "IMG_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Transient
	public Date getLastLoginTimeDate() {
		return dateUtils.longToDate(this.lastLoginTime);
	}
	@Column(name = "LAST_LOGIN_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.lang.Long value) {
		this.lastLoginTime = value;
	}
	
	@Transient
	public Date getAddTimeDate() {
		return dateUtils.longToDate(this.addTime);
	}
	@Column(name = "ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.lang.Long value) {
		this.addTime = value;
	}
	
	@Column(name = "PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "FREE_CYCLE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getFreeCycle() {
		return this.freeCycle;
	}
	
	public void setFreeCycle(java.lang.String value) {
		this.freeCycle = value;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Transient
	public String getFreeStartTimeStr() {
		return null != this.freeStartTime ? dateUtils.longToTime(this.freeStartTime).substring(0, 5):"";
	}
	@Column(name = "FREE_START_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getFreeStartTime() {
		return this.freeStartTime;
	}
	
	public void setFreeStartTime(java.lang.Long value) {
		this.freeStartTime = value;
	}
	
	@Transient
	public String getFreeStopTimeStr() {
		return null != this.freeStopTime ? dateUtils.longToTime(this.freeStopTime).substring(0, 5):"";
	}
	@Column(name = "FREE_STOP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getFreeStopTime() {
		return this.freeStopTime;
	}
	
	public void setFreeStopTime(java.lang.Long value) {
		this.freeStopTime = value;
	}
	
	@Transient
	public Date getValidStartTimeDate() {
		return dateUtils.longToDate(this.validStartTime);
	}
	@Column(name = "VALID_START_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getValidStartTime() {
		return this.validStartTime;
	}
	
	public void setValidStartTime(java.lang.Long value) {
		this.validStartTime = value;
	}
	
	@Transient
	public Date getValidStopTimeDate() {
		return dateUtils.longToDate(this.validStopTime);
	}
	@Column(name = "VALID_STOP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getValidStopTime() {
		return this.validStopTime;
	}
	
	public void setValidStopTime(java.lang.Long value) {
		this.validStopTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProvinceId",getProvinceId())
			.append("AreaId",getAreaId())
			.append("CityId",getCityId())
			.append("Level",getLevel())
			.append("NickName",getNickName())
			.append("RealName",getRealName())
			.append("LoginName",getLoginName())
			.append("ExpertGradeIds",getExpertGradeIds())
			.append("ExpertSubjectId",getExpertSubjectId())
			.append("Sex",getSex())
			.append("ImgPath",getImgPath())
			.append("LastLoginTime",getLastLoginTime())
			.append("AddTime",getAddTime())
			.append("Password",getPassword())
			.append("Status",getStatus())
			.append("FreeCycle",getFreeCycle())
			.append("FreeStartTime",getFreeStartTime())
			.append("FreeStopTime",getFreeStopTime())
			.append("ValidStartTime",getValidStartTime())
			.append("ValidStopTime",getValidStopTime())
			.append("Token",getToken())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Teacher == false) return false;
		if(this == obj) return true;
		Teacher other = (Teacher)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Province getProvince(){
		ProvinceManager provinceManager = (ProvinceManager)ApplicationContextHolder.getBean("provinceManager");
		Province province = null;
		if(null != this.getProvinceId()){
			province = provinceManager.getById(this.getProvinceId());
		}
		if(null == province){
			province = new Province();
		}
		return province;
	}
	
	@Transient
	public City getCity(){
		CityManager cityManager = (CityManager)ApplicationContextHolder.getBean("cityManager");
		City city = null;
		if(null != this.cityId){
			city = cityManager.getById(this.cityId);
		}
		if(null == city){
			city = new City();
		}
		return city;
	}
	
	@Transient
	public List<Grade> getExpertGradeList(){
		GradeManager gradeManager = (GradeManager)ApplicationContextHolder.getBean("gradeManager");
		List<Grade> result = new ArrayList<Grade>();
		Grade grade = null;
		if(null != this.getExpertGradeIds()){
			String[] temp = this.getExpertGradeIds().replaceAll("[\\]\\[]", "").split(",");
			for (int i = 0; i < temp.length; i++) {
				String gradeId = temp[i];
				if(gradeId.matches("\\d+")){
					grade = gradeManager.getById(Long.parseLong(gradeId));
					if(null!=grade){
						result.add(grade);
					}
				}
			}
		}
		return result;
	}
	
	@Transient
	public Subject getExpertSubject(){
		SubjectManager subjectManager = (SubjectManager)ApplicationContextHolder.getBean("subjectManager");
		Subject subject = null;
		if(null != this.getExpertSubjectId()){
			subject = subjectManager.getById(this.getExpertSubjectId());
		}
		if(null == subject){
			subject = new Subject();
		}
		return subject;
	}
	
	
	@Transient
	public String getStatusStr(){
		String result = JTZSConstants.Status.FREEZE.getName();
		if(null != this.status && JTZSConstants.Status.NORMAL.getIndex()==this.status){
			result = JTZSConstants.Status.NORMAL.getName();
		}
		return result;
	}
	
	
	@Transient
	public String getSexStr(){
		String result = JTZSConstants.Sex.MEN.getName();
		if(null != this.status && JTZSConstants.Sex.WOMEN.getIndex()==this.status){
			result = JTZSConstants.Sex.WOMEN.getName();
		}
		return result;
	}
	
	@Transient
	public String getLevelStr(){
		String result = "教师";
		if(null != this.level && JTZSConstants.ROLE_EXPERT.equals(this.level)){
			result = "专家";
		}
		return result;
	}
	
	/** 星期一 */
	@Transient
	public boolean getFreeMonday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Monday.getIndex()); 
	}
	
	/** 星期二 */
	@Transient
	public boolean getFreeTuesday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Tuesday.getIndex()); 
	}
	
	/** 星期三 */
	@Transient
	public boolean getFreeWednesday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Wednesday.getIndex()); 
	}
	
	/** 星期四 */
	@Transient
	public boolean getFreeThursday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Thursday.getIndex()); 
	}
	
	/** 星期五 */
	@Transient
	public boolean getFreeFriday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Friday.getIndex()); 
	}
	
	/** 星期六 */
	@Transient
	public boolean getFreeSaturday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Saturday.getIndex()); 
	}
	
	/** 星期日 */
	@Transient
	public boolean getFreeSunday(){
		return isFree(freeCycle, JTZSConstants.FreeCycle.Sunday.getIndex()); 
	}
	
	/**
	 * 判断是否空闲时段
	 * @return
	 */
	public static boolean isFree(String freeCycle,String weekIndex){
		boolean result = false;
		if(null!=freeCycle && null!=weekIndex && freeCycle.matches("\\d+") && weekIndex.matches("\\d+")){
			BigInteger week = new BigInteger(weekIndex,2);
			BigInteger free = new BigInteger(freeCycle,2);
			BigInteger src = new BigInteger((week.byteValue()&free.byteValue())+"");
			if(src.intValue() > 0){
				result = true;
			}
		}
		return result;
	}
	
	@Transient
	public Score getScore(){
		ScoreManager scoreManager = (ScoreManager)ApplicationContextHolder.getBean("scoreManager");
		return scoreManager.getScoreByRole(this.id, JTZSConstants.ROLE_TEACHER);
	}
	
	@Transient
	public Map<String,Object> getAnswerCount(){
		AnswerDao answerDao = (AnswerDao)ApplicationContextHolder.getBean("answerDao");
		Map<String,Object> result = new HashMap<String,Object>();
		List<Object[]> list =  answerDao.findListBySQL("select status,count(id) from xes_jtzs_answer where teacher_id=? group by status", new Object[]{this.id}, 0, null);
		if(null != list && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = list.get(i);
				if(JTZSConstants.AnswerStatus.QUIT.getIndex()== (Byte)obj[0]){
					result.put("quitCount", obj[1]);
				}else if(JTZSConstants.AnswerStatus.FINISH.getIndex()==(Byte)obj[0]){
					result.put("answerCount", obj[1]);
				}
			}
		}
		if(null == result.get("quitCount")){
			result.put("quitCount", 0);
		}
		if(null == result.get("answerCount")){
			result.put("answerCount", 0);
		}
		return result;
	}
	
	@Transient
	public Map<String,Object> getCommentCount(){
		CommentDao commentDao = (CommentDao)ApplicationContextHolder.getBean("commentDao");
		Map<String,Object> result = new HashMap<String,Object>();
		List<Object[]> list =  commentDao.findListBySQL("select is_satisfied,count(id) from xes_jtzs_comment where teacher_id=? and is_del=? group by is_satisfied", 
				new Object[]{this.id,JTZSConstants.IsDel.UNDELETE.getIndex()}, 0, null);
		if(null != list && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = list.get(i);
				if(JTZSConstants.SatisfiedStatus.SATISFIED.getIndex()== (Byte)obj[0]){
					result.put("satisfied", obj[1]);
				}else if(JTZSConstants.SatisfiedStatus.UNSATISFIED.getIndex()==(Byte)obj[0]){
					result.put("unsatisfied", obj[1]);
				}
			}
		}
		if(null == result.get("satisfied")){
			result.put("satisfied", 0);
		}
		if(null == result.get("unsatisfied")){
			result.put("unsatisfied", 0);
		}
		return result;
	}
}

