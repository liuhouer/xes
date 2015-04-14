package com.up72.app.auth.model;

import java.util.List;

import javax.persistence.Transient;

import com.up72.auth.dao.RoleDao;
import com.up72.auth.dao.WorkGroupMemberDao;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.model.WorkGroupMember;
import com.up72.base.UserDetails;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;

/**
 * 用户
 * 
 * @author jhe
 * @version 1.0
 * @since 1.0
 */

public class User implements UserDetails{
	private static final long serialVersionUID = 5278485485001124590L;
	
	private Long id;
	private String userName;
	private String password;
    private Integer enabled;

    private Integer code;
    private List<Role> roles;
    
    private String style;
    private String skin;

    
    public User(){
    }
    
	public User(Long id, String userName, String password, Integer enabled,
			Integer code, List<Role> roles)  {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.code = code;
		this.roles = roles;
	}

	public String getPassword() {
		return this.password;
	}
	

	
	public String getUserName() {
		return this.userName;
	}
	public Integer getEnabled() {
		return this.enabled;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Transient
	public WorkGroup getWorkGroup(){
		WorkGroupMemberDao workGroupMemberDao = (WorkGroupMemberDao) ApplicationContextHolder.getBean("workGroupMemberDao");
		List<WorkGroupMember> list = workGroupMemberDao.getWorkGroupMember(this.id);
		
		WorkGroup result = null;
		if(null!=list && list.size() > 0){
			result = list.get(0).getWorkGroup();
		}
		return result;
	}
	
	@Transient
	public List<Role> getRoles() {
		if(ObjectUtils.isNotEmpty(this.getId())){
			RoleDao roleDao = (RoleDao)ApplicationContextHolder.getBean("roleDao");
			this.roles =  roleDao.getUserRoles(this.id);
		}
		return this.roles;
	}

	@Transient
	public List<WorkGroupMember> getWorkGroupList() {
		WorkGroupMemberDao workGroupMemberDao = (WorkGroupMemberDao) ApplicationContextHolder.getBean("workGroupMemberDao");
		List<WorkGroupMember> list = null;
		if(null!=this.id && this.id!=0){
			list = workGroupMemberDao.getWorkGroupMember(this.id);
		}
//		WorkGroup result = null;
//		if(null!=list && list.size() > 0){
//			result = list.get(0).getWorkGroup();
//		}
		return list;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

}
