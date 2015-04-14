package com.up72.common.version;

public class SerializeException extends RuntimeException {
	public SerializeException(String message, Throwable e) {
		super(message, e);
	}

	public SerializeException(Throwable e) {
		super(e);
	}

	public SerializeException(String message) {
		super(message);
	}
}
