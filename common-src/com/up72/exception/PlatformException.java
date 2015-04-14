package com.up72.exception;

/**
 * 平台异常，直接继承自BaseRuntimeException
 */
public class PlatformException extends BaseRuntimeException {

	public PlatformException(String msg) {
		super(msg);
	}
	
	public PlatformException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public PlatformException(Throwable ex) {
		super(ex);
	}
}
