package com.up72.sys.model;

public interface IModelAccess
{
	/**
	 * 是否能编辑
	 * @param loginMemberId
	 * @param loginMemberName
	 * @return
	 */
	public boolean isEdit(Integer loginMemberId,String loginMemberName);
	/**
	 * 是否能删除
	 * @param loginMemberId
	 * @param loginMemberName
	 * @return
	 */
	public boolean isDelete(Integer loginMemberId,String loginMemberName);
}
