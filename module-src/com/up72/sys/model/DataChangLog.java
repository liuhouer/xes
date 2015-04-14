/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.model;

import static com.up72.common.CommonUtils.dateUtils;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import com.up72.dao.hibernate.CommonDAOHibernate;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 
 * 
 * @author up72
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "sys_data_chang_log")
public class DataChangLog extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "数据变更日志";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_CHANG_TABLE = "变更表";
	public static final String ALIAS_CHANG_ID = "变更ID";
	public static final String ALIAS_CHANG_TIME = "变更时间";
	public static final String ALIAS_CHANG_TYPE = "变更类型"; // insert update delete
	
	public static final String CHANG_TYPE_CREATE = "insert";
	public static final String CHANG_TYPE_DELETE = "delete";
	public static final String CHANG_TYPE_UPDATE = "update";

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** */
	
	private java.lang.Long id;
	
	/** */
	@Length(max=256)
	private java.lang.String changTable;
	
	/** */
	
	private java.lang.Long changId;
	
	/** */
	private java.lang.Long changTime;
	
	/** */
	@Length(max=50)
	private java.lang.String changType;
	
	//columns END


	public DataChangLog(){
	}

	public DataChangLog(
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
	
	@Column(name = "CHANG_TABLE", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getChangTable() {
		return this.changTable;
	}
	
	public void setChangTable(java.lang.String value) {
		this.changTable = value;
	}
	
	@Column(name = "CHANG_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getChangId() {
		return this.changId;
	}
	
	public void setChangId(java.lang.Long value) {
		this.changId = value;
	}
	
	@Transient
	public Date getChangTimeDate() {
		return dateUtils.longToDate(this.changTime);
	}
	
	@Column(name = "CHANG_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getChangTime() {
		return this.changTime;
	}
	
	public void setChangTime(java.lang.Long value) {
		this.changTime = value;
	}
	
	@Column(name = "CHANG_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getChangType() {
		return this.changType;
	}
	
	public void setChangType(java.lang.String value) {
		this.changType = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ChangTable",getChangTable())
			.append("ChangId",getChangId())
			.append("ChangTime",getChangTime())
			.append("ChangType",getChangType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DataChangLog == false) return false;
		if(this == obj) return true;
		DataChangLog other = (DataChangLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public Object getChangObject(){
		Object result = null;
		if(null == changId || null == changTable || changTable.trim().equals("")){
			return result;
		}
		else {
			try{
				CommonDAOHibernate commonDAOHibernate = (CommonDAOHibernate) ApplicationContextHolder.getBean("commonDAOHibernate");
				String hsql = "from "+this.changTable+" where id=?";
				List list = commonDAOHibernate.find(hsql, new Object[]{this.changId});
				if(null != list && !list.isEmpty()){
					result = list.get(0);
				}
			}catch (Exception e) {
				result = null;
			}
		}
		return result;
	}
}

