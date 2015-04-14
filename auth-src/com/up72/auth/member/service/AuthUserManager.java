/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2010
 */

package com.up72.auth.member.service;

import static com.up72.common.CommonUtils.desPlus;
import static com.up72.common.CommonUtils.encryptUtil;
import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.objectUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.up72.auth.AuthConstants;
import com.up72.auth.dao.MemberRoleDao;
import com.up72.auth.dao.RoleDao;
import com.up72.auth.dao.WorkGroupMemberDao;
import com.up72.auth.member.AuthUserConstants;
import com.up72.auth.member.dao.AuthUserDao;
import com.up72.auth.member.model.AuthUser;
import com.up72.auth.member.vo.query.AuthUserQuery;
import com.up72.auth.model.MemberRole;
import com.up72.auth.model.Role;
import com.up72.auth.model.WorkGroupMember;
import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.common.CommonConstants;
import com.up72.common.freemarker.TemplateBuilder;
import com.up72.common.freemarker.TemplateBuilderUtil;
import com.up72.common.image.CutInfo;
import com.up72.common.image.ImageTools;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.sys.SystemConfig;

import freemarker.template.TemplateException;

/**
 * 用户业务处理
 * 
 * @author UP72
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class AuthUserManager extends BaseManager<AuthUser, java.lang.Long> {

	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private MemberRoleDao memberRoleDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private WorkGroupMemberDao workGroupMemberDao;

	@SuppressWarnings( { "unchecked" })
	public EntityDao getEntityDao() {
		return this.authUserDao;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings( { "unchecked" })
	public Page findPage(AuthUserQuery query) {
		return authUserDao.findPage(query);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings( { "unchecked" })
	public Page findRecyclePage(AuthUserQuery query) {
		query.setDelStatus(1);
		return authUserDao.findPage(query);
	}

	/**
	 * 批量修改
	 * 
	 * @author bxmen
	 */
	public void batchUpdaterMember(String batchItems, Map<String, String> map) {
		StringBuffer hsql = new StringBuffer("UPDATE AuthUser set ");
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String column = it.next();
			String value = map.get(column);
			if (it.hasNext()) {
				hsql.append(column + "=" + value + ",");
			} else {
				hsql.append(column + "=" + value);
			}
		}
		hsql.append(" where id in (" + batchItems + ")");
		authUserDao.executeHsql(hsql.toString());
	}

	/**
	 * 将会员加入到回收站，还原回收站
	 * 
	 * @author 蒋华梯
	 */
	public void recycleMember(Long[] memberIds, int status) {
		StringBuffer hsql = new StringBuffer("UPDATE AuthUser");
		hsql.append(" set delStatus = " + status); // 1表示删除到回收站 , 0表示还原
		if (memberIds.length == 1) {
			hsql.append(" where id =" + memberIds[0]);
		} else {
			hsql.append(" where id in (" + parseToSQLStringComma(memberIds)
					+ ")");
		}
		authUserDao.executeHsql(hsql.toString());

	}


	/**
	 * 获取用户信息
	 * 
	 * @author jhe
	 * @param userName
	 *            用户名
	 * @return 返回用户信息
	 */
	@Transactional(readOnly = true)
	public AuthUser getMember(String userName) {
		return authUserDao.findByProperty("userName", userName.trim()
				.toLowerCase());
	}
	
	/**
	 * 获取管理员
	 */
	@Transactional(readOnly = true)
	public AuthUser getAdminMember() {
		return authUserDao.findByProperty("userName", "admin");
	}

	/**
	 * 用户登陆
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param code
	 *            用户编码
	 * @return 验证通过返回用户信息
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public AuthUser login(String userName, String password, Integer code)
			throws Exception {
		AuthUser member = getMember(userName.trim().toLowerCase());

		if (objectUtils.isNotEmpty(member)) {
			if (!CommonConstants.PUBILC_ENABLED.equals(member.getEnabled())) {
				throw new Exception("该用户已被禁用！");
			} else if (!member.getCode().equals(code)
					&& !member.getCode().equals(
							AuthConstants.MEMBER_TYPE_SYSTEM)) {
				throw new Exception("用户不存在！");
			} else {
				if (!member.getPassword().toLowerCase().equals(
						encryptUtil.md5(password).toLowerCase())) {
					throw new Exception("密码不正确！");
				} else {
					return member;
				}
			}
		} else {
			throw new Exception("用户不存在！");
		}
	}

	/**
	 * 用户登陆
	 * 
	 * @author wqtan
	 * @param member
	 * @return 登陆结果 1 成功 2 用户被禁用 3 用户类型错误 4 密码错误 5 用户不存在 6 用户未激活
	 */
	@Transactional(readOnly = true)
	public int userLogin(AuthUser member, AuthUser dbMember) {
		int result = 1;
		if (objectUtils.isNotEmpty(dbMember)) {
			if (CommonConstants.PUBILC_UNACTIVATE.equals(dbMember.getEnabled())) {
				result = 6;
			} else if (!CommonConstants.PUBILC_ENABLED.equals(dbMember
					.getEnabled())) {
				result = 2;
			} else if (!dbMember.getPassword().toLowerCase().equals(
					encryptUtil.md5(member.getPassword()).toLowerCase())
					&& !dbMember.getPassword().toLowerCase().equals(
							member.getPassword().toLowerCase())) {
				result = 4;
			}
		} else {
			result = 5;
		}
		return result;
	}

	/**
	 * 注册操作并创建用户部门关系
	 * 
	 * @param member
	 * @return 注册结果 1 成功 2 用户名已存在 3 邮件地址不存在 4 激活邮件发送失败
	 */
	public int register(AuthUser member, Long workGroupId) {
		AuthUser dbMember = this.getMember(member.getUserName().trim()
				.toLowerCase());
		int result = 1;
		if (null != dbMember) {
			result = 2;
		} else {
			member.setPassword(encryptUtil.md5(member.getPassword()));
			this.save(member);
			WorkGroupMember wgm = new WorkGroupMember();
			wgm.setIsManager(AuthConstants.workGroupMember.IS_MANAGER_FALSE);//默认不是部门管理员
			wgm.setMemberId(member.getId());
			wgm.setWorkGroupId(workGroupId);
			wgm.setAddTime(new Date().getTime());
			wgm.setStatus(AuthConstants.workGroupMember.STATUS_UN_PASS);//默认不通过部门审核
			workGroupMemberDao.save(wgm);
			try {
				this.sendRegisterEmail(member);
			} catch (SendFailedException e) {
				result = 3;
			} catch (Exception e) {
				result = 4;
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送注册激活邮件
	 * 
	 * @param member
	 */
	private void sendRegisterEmail(AuthUser member)
			throws SMTPAddressFailedException, AddressException,
			UnsupportedEncodingException, MessagingException {
		String templateFile = SystemConfig.instants().getValue(
				AuthConstants.REGISTER_TEMPLATE);
		if (null == templateFile || templateFile.trim().equals("")
				|| !templateFile.endsWith(".ftl")) {
			throw new RuntimeException("注册模板格式正确请检查配置文件配置是否准确");
		}
		String templatePath = CommonConstants.ROOTPATH
				+ templateFile.substring(0, templateFile.lastIndexOf('/'));
		String templateFileName = templateFile.substring(templateFile
				.lastIndexOf('/') + 1);

		TemplateBuilder tbu = null;
		try {
			tbu = TemplateBuilderUtil.newInstance(templatePath,
					AuthConstants.DEFAULT_ENCODE);
		} catch (IOException e) {
			throw new RuntimeException("读取注册模板错误", e);
		}
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("member", member);
		String keyString = "";
		try {
			keyString = desPlus.encrypt(member.getId() + "");
		} catch (Exception e) {
			throw new RuntimeException("加密用户数据错误", e);
		}
		models.put("key", keyString);
		models.put("regCofirmUrl", SystemConfig.instants().getValue(
				AuthConstants.REGISTER_CONFIRM_URL));
		models
				.put("ctx", SystemConfig.instants().getValue("server.host")
						+ "/");
		try {
			tbu.buildByTemplate(models, templateFileName);
		} catch (TemplateException e) {
			throw new RuntimeException("编辑注册模板错误", e);
		} catch (IOException e) {
			throw new RuntimeException("编辑注册模板错误", e);
		}
//		new MailManager().sendMail(member.getEmail(), SystemConfig.instants()
//				.getValue(AuthConstants.REGISTER_TITLE), content, 0);
	}

	/**
	 * 发送密码重设邮件
	 * 
	 * @param member
	 */
	public void sendFindPwdEmail(AuthUser member)
			throws SMTPAddressFailedException, AddressException,
			UnsupportedEncodingException, MessagingException {
		String templateFile = SystemConfig.instants().getValue(
				AuthConstants.BACK_PASSWORD_TEMPLATE);
		if (null == templateFile || templateFile.trim().equals("")
				|| !templateFile.endsWith(".ftl")) {
			throw new RuntimeException("注册模板格式正确请检查配置文件配置是否准确");
		}
		String templatePath = CommonConstants.ROOTPATH
				+ templateFile.substring(0, templateFile.lastIndexOf('/'));
		String templateFileName = templateFile.substring(templateFile
				.lastIndexOf('/') + 1);

		TemplateBuilder tbu = null;
		try {
			tbu = TemplateBuilderUtil.newInstance(templatePath,
					AuthConstants.DEFAULT_ENCODE);
		} catch (IOException e) {
			throw new RuntimeException("读取注册模板错误", e);
		}
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("member", member);
		String keyString = "";
		try {
			keyString = desPlus.encrypt(member.getId() + "|" + new Date().getTime());
		} catch (Exception e) {
			throw new RuntimeException("加密用户数据错误", e);
		}
		models.put("key", keyString);
		models.put("regCofirmUrl", SystemConfig.instants().getValue(
				AuthConstants.BACK_PASSWORD_URL));
		models
				.put("ctx", SystemConfig.instants().getValue("server.host")
						+ "/");
		try {
			tbu.buildByTemplate(models, templateFileName);
		} catch (TemplateException e) {
			throw new RuntimeException("编辑注册模板错误", e);
		} catch (IOException e) {
			throw new RuntimeException("编辑注册模板错误", e);
		}
//		new MailManager().sendMail(member.getEmail(), SystemConfig.instants()
//				.getValue(AuthConstants.BACK_PASSWORD_TITLE), content, 0);
	}

	/**
	 * 编辑用户信息
	 * 
	 * @author wqtan
	 */
	public AuthUser memberinfo(AuthUser member) {

		AuthUser dbMember = this.getMember(member.getUserName());

		dbMember.setEmail(member.getEmail());
		if (null == member.getNickName() || member.getNickName().equals("")) {
			dbMember.setNickName(member.getUserName());
		} else {
			dbMember.setNickName(member.getNickName());
		}
		
		this.update(dbMember);
		return dbMember;
	}

	/**
	 * 修改密码
	 * 
	 * @author wqtan
	 */
	public void resetPwd(String oldPassword, String newPassword, AuthUser member) {
		AuthUser dbMember = this.getMember(member.getUserName());

		if (!encryptUtil.md5(oldPassword).equals(dbMember.getPassword())
				&& !(oldPassword.equals(dbMember.getPassword()))) {
			throw new ServiceException("原密码错误！");
		} else {
			if(newPassword.length()>18 ||newPassword.length()<6){
				throw new ServiceException("密码需为6-18位");
			}else{
				dbMember.setPassword(encryptUtil.md5(newPassword));
				this.update(dbMember);
			}
		}
	}

	/**
	 * 上传头像业务处理
	 * 
	 * @author wqtan
	 * @return 上传的文件信息
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Transactional(readOnly = true)
	public Map<String, String> uploadPhoto(InputStream inputStream,
			String fileName) throws FileNotFoundException, IOException {
		// 获得当前时间的long值
		Long uploadTime = new Date().getTime();

		// 获得项目绝对路径
		String webPath = fileUtil.getWebPath(this.getClass());

		// 保存文件路径
		String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		String savePath = AuthUserConstants.MEMBER_PHOTO_UPLOAD_PATH+"/"+date;
		String webSavePath = stringUtil.parseToPath(webPath + "/" + savePath);
		// 保存文件名
		String saveFile = uploadTime
				+ fileName.substring(fileName.indexOf('.'));
		String webSaveFile = stringUtil.parseToPath(webSavePath + "/"
				+ saveFile);

		// 创建保存路径
		File file = new File(webSavePath);
		file.mkdirs();

		ImageTools.writeToJPG(inputStream, webSaveFile, 100);
		Image image = null;
		image = ImageTools.readImage(webSaveFile);

		Map<String, String> result = new HashMap<String, String>();
		result.put("width", Integer.toString(image.getWidth(null)));
		result.put("height", Integer.toString(image.getHeight(null)));
		result.put("saveFile", saveFile);
		result.put("savePath", savePath);
		result.put("fileName", fileName);
		result.put("uploadName", fileName);
		result.put("uploadTime", Long.toString(uploadTime));
		return result;
	}

	// ----------------------修改头像处理开始----------------------------------------------------------

	/**
	 * 修改头像
	 * 
	 * @author wqtan
	 */
	public AuthUser changPhoto(CutInfo cutInfo, String photoFile, AuthUser member) {
		// 获得原图的绝对路径
		photoFile = CommonConstants.ROOTPATH + photoFile;
		// 获得保存图的绝对路径
		String saveFile = photoFile + "_photo" + fileUtil.getSuffix(photoFile);
		// 裁剪处理
		ImageTools.cutImage(cutInfo, photoFile, saveFile);
		// 获得头像的相对路径
		saveFile = "/" + saveFile.substring(CommonConstants.ROOTPATH.length());

		// 更新用户信息
		member = this.getMember(member.getUserName());

		member.setImgPath(saveFile);
		this.update(member);

		return member;
	}

	// ----------------------修改头像处理结束----------------------------------------------------------\

	/**
	 * 为指定用户添加指定角色
	 * 
	 * @author wqtan
	 * @return MemberRole
	 */
	public MemberRole addMemberRole(Long memberId, Long roleId) {
		MemberRole result = null;
		AuthUser member = this.authUserDao.getById(memberId);
		Role role = this.roleDao.getById(roleId);
		if (null != member && null != role) {
			result = new MemberRole();
			result.setMemberId(memberId);
			result.setRoleId(roleId);
			this.memberRoleDao.save(result);
		}
		return result;
	}
	
	/**
	 * 编辑用户所属部门及角色
	 */
	public void editMemberWorkGroupAndRole(long memberId,Long organizationId,long[] workgroupIds,long[] roleIds){
		if(memberId <= 0){
			return;
		}
		
		if(null != workgroupIds && workgroupIds.length > 0){
			// 清空原所属部门及角色
			clearMemberWorkGroupAndRole(memberId);
			// 重新分配用户所属部门
			for(int i=0;i<workgroupIds.length;i++){
				createWorkGroupMember(memberId, workgroupIds, i);
			}
		}
		
		if(null != organizationId && organizationId >0 ){
			AuthUser authUser = authUserDao.getById(memberId);
			if(authUser != null){
				authUser.setOrganizationId(organizationId);
				authUserDao.update(authUser);
			}
		}
		
		if(null != roleIds && roleIds.length > 0){
			for(int i=0;i<roleIds.length;i++){
				createMemberRole(memberId, roleIds, i);
			}
		}
	}

	private void createMemberRole(long memberId, long[] roleIds, int i) {
		MemberRole mr = new MemberRole();
		mr.setMemberId(memberId);
		mr.setRoleId(roleIds[i]);
		memberRoleDao.save(mr);
	}

	private void createWorkGroupMember(long memberId, long[] workgroupIds, int i) {
		WorkGroupMember temp = new WorkGroupMember();
		temp.setMemberId(memberId);
		temp.setWorkGroupId(workgroupIds[i]);
		temp.setStatus(1);
		temp.setIsManager(0);
		temp.setAddTime(new Date().getTime());
		workGroupMemberDao.save(temp);
	}
	
	private void clearMemberWorkGroupAndRole(long memberId){
		this.clearMemberWorkGroup(memberId);
		this.clearMemberRole(memberId);
	}
	
	private void clearMemberWorkGroup(long memberId){
		String hsql = "delete from WorkGroupMember where memberId=?";
		this.workGroupMemberDao.executeHsql(hsql,new Object[]{memberId});
	}
	
	private void clearMemberRole(long memberId){
		String hsql = "delete from MemberRole where memberId=?";
		this.workGroupMemberDao.executeHsql(hsql,new Object[]{memberId});
	}
	
}
