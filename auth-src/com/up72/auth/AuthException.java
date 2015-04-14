package com.up72.auth;

import com.up72.exception.PlatformException;

public class AuthException extends PlatformException {
	public AuthException(String message,Throwable e) {
		super(message,e);
	}

	public AuthException(String message) {
		super(message);
	}

	public AuthException(Throwable e) {
		super(e);
	}
}
