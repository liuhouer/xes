package com.up72.util.http;

public class HTTPException extends RuntimeException{

	public HTTPException(String message) {
		super(message);
	}
	
	public HTTPException(Exception e) {
		super(e);	
	}
	
	public HTTPException(String message,Exception e) {
		super(message,e);
	}
}
