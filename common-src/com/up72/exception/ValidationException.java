package com.up72.exception;


/**
 * 验证异常，直接继承BaseRuntimeException
 */
public class ValidationException extends DataException {
	private static final long serialVersionUID = 3709493307303114001L;

	/**
	 * Create a new ServiceException with the specified message.
	 * @param msg the detail message
	 */
	public ValidationException(String msg) {
		super(msg);
	}

	/**
	 * Create a new ServiceException with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public ValidationException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public ValidationException(Throwable ex) {
		super(ex);
	}
}
