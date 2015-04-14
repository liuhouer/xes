package com.up72.exception;

/**
 * 无效请求，属于系统异常
 */
public class InvalidRequestException extends SystemException {
	private static final long serialVersionUID = 2994304244436416111L;

	/**
	 * Create a new InvalidParameterException with the specified message.
	 * @param msg the detail message
	 */
	public InvalidRequestException(String msg) {
		super(msg);
	}

	/**
	 * Create a new InvalidIDException with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public InvalidRequestException(String msg, Throwable ex) {
		super(msg, ex);
	}
}	
