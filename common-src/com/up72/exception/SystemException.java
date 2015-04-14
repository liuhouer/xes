package com.up72.exception;

/**
 * 系统异常，直接继承自BaseRuntimeException
 */
public class SystemException extends BaseRuntimeException{
	public SystemException(String msg,Throwable e) {
		super(msg,e);
	}
	
	public SystemException(Throwable e) {
		super(e);
	}
	
	public SystemException(String msg) {
		super(msg);
	}
}
