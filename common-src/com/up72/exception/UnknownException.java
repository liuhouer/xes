package com.up72.exception;

/**
 * 未知异常，直接继承自BaseRuntimeException
 */
public class UnknownException extends BaseRuntimeException {
	private static final long serialVersionUID = 2994304244436416111L;

	/**
	 * Create a new InvalidParameterException with the specified message.
	 * @param msg the detail message
	 */
	public UnknownException(String msg) {
		super(msg);
	}

	/**
	 * Create a new InvalidIDException with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public UnknownException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public UnknownException(Throwable ex) {
		super(ex);
	}
}	
