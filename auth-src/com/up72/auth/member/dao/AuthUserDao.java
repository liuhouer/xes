/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.member.dao;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import org.springframework.stereotype.Repository;

import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.vo.query.AuthUserQuery;
import com.up72.base.BaseHibernateDao;
import com.up72.framework.page.Page;

/**
 * 用户Hibernate数据处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Repository
public class AuthUserDao extends BaseHibernateDao<AuthUser,java.lang.Long>{

	@SuppressWarnings({ "unchecked" })
	public Class getEntityClass() {
		return AuthUser.class;
	}
	
	/**
	 * @author bxmen
	 * @param query
	 * @return Page
	 * @summary 
	 */
	@SuppressWarnings({ "unchecked" })
	public Page findPage(AuthUserQuery query) {
        StringBuilder sql = new StringBuilder("select t from AuthUser t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getUserName())) {
            sql.append(" and userName like '%" + query.getUserName() + "%' ");
        }
        if(isNotEmpty(query.getPassword())) {
            sql.append(" and  t.password = :password ");
        }
        if(isNotEmpty(query.getNickName())) {
            sql.append(" and  t.nickName = :nickName ");
        }
        if(isNotEmpty(query.getImgPath())) {
            sql.append(" and  t.imgPath = :imgPath ");
        }
        if(isNotEmpty(query.getLoginAnswer())) {
            sql.append(" and  t.loginAnswer = :loginAnswer ");
        }
        if(isNotEmpty(query.getSecques())) {
            sql.append(" and  t.secques = :secques ");
        }
        if(isNotEmpty(query.getCode())) {
            sql.append(" and  t.code = :code ");
        }
        if(isNotEmpty(query.getLastIp())) {
            sql.append(" and  t.lastIp = :lastIp ");
        }
        if(isNotEmpty(query.getLastVisiTime())) {
            sql.append(" and  t.lastVisiTime = :lastVisiTime ");
        }
        if(isNotEmpty(query.getEmail())) {
            sql.append(" and  t.email = :email ");
        }
        if(isNotEmpty(query.getMobileValidate())) {
            sql.append(" and  t.mobileValidate = :mobileValidate ");
        }
        if(isNotEmpty(query.getMobile())) {
            sql.append(" and  t.mobile = :mobile ");
        }
        if(isNotEmpty(query.getEmailVisible())) {
            sql.append(" and  t.emailVisible = :emailVisible ");
        }
        if(isNotEmpty(query.getEnabled())) {
            sql.append(" and  t.enabled = :enabled ");
        }
        if(isNotEmpty(query.getProblem())) {
            sql.append(" and  t.problem = :problem ");
        }
        if(isNotEmpty(query.getAnser())) {
            sql.append(" and  t.anser = :anser ");
        }
        if(isNotEmpty(query.getDelStatus())) {
        	sql.append(" and  t.delStatus = :delStatus ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" order by :sortColumns ");
        }	
		return pageQuery(sql.toString(),query);
	}
	

}
