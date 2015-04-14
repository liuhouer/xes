package com.up72.sys.dao;

import org.springframework.stereotype.Repository;

import com.up72.base.BaseEntity;
import com.up72.base.BaseHibernateDao;

@Repository
public class SystemDao extends BaseHibernateDao<BaseEntity, java.lang.Long> {
	public Class getEntityClass() {
		return BaseEntity.class;
	}
}
