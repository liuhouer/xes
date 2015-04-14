package com.up72.exception;


/**
 * 业务异常，直接继承自BaseRuntimeException
 */
public class ServiceException extends BaseRuntimeException {
	private static final long serialVersionUID = 3709493307303114001L;

	/**
	 * Create a new ServiceException with the specified message.
	 * @param msg the detail message
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Create a new ServiceException with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}
	public ServiceException(Throwable ex) {
		super( ex);
	}
}
