/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.auth.AuthConstants;
import com.up72.auth.dao.WorkGroupMemberDao;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.model.WorkGroup;
import com.up72.auth.model.WorkGroupMember;
import com.up72.auth.vo.query.WorkGroupMemberQuery;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.framework.page.Page;

/**
 * 工作组(部门)与用户多对多关系表业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class WorkGroupMemberManager extends
		BaseManager<WorkGroupMember, java.lang.Long> {

	private WorkGroupMemberDao workGroupMemberDao;

	public void setWorkGroupMemberDao(WorkGroupMemberDao dao) {
		this.workGroupMemberDao = dao;
	}

	@SuppressWarnings( { "unchecked" })
	public EntityDao getEntityDao() {
		return this.workGroupMemberDao;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings( { "unchecked" })
	public Page findPage(WorkGroupMemberQuery query) {
		return workGroupMemberDao.findPage(query);
	}

	public boolean isManager(AuthUser loginMember, WorkGroup workGroup) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", loginMember.getId());
		params.put("workGroupId", workGroup.getId());
		params.put("isManager",AuthConstants.workGroupMember.IS_MANAGER_TRUE);
		List<WorkGroupMember> list = workGroupMemberDao.findList(params, 1,
				null);
		return list != null && list.size() > 0 ? true : false;
	}
	

	
	public List<WorkGroupMember> getWorkGroupMember(Long userId){
		return this.workGroupMemberDao.getWorkGroupMember(userId);
	}
}
