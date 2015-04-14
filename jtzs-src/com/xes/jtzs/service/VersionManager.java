/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.xes.jtzs.service;

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
public class VersionManager extends BaseManager<Version,java.lang.Long>{

	private VersionDao versionDao;

	public void setVersionDao(VersionDao dao) {
		this.versionDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.versionDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(VersionQuery query) {
		return versionDao.findPage(query);
	}
	
}
