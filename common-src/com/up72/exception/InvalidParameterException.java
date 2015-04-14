package com.up72.exception;

/**
 * 无效参数，属于数据异常
 */
public class InvalidParameterException extends DataException {
	private static final long serialVersionUID = 2994304244436416111L;

	/**
	 * Create a new InvalidParameterException with the specified message.
	 * @param msg the detail message
	 */
	public InvalidParameterException(String msg) {
		super(msg);
	}

	/**
	 * Create a new InvalidIDException with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public InvalidParameterException(String msg, Throwable ex) {
		super(msg, ex);
	}

}	
