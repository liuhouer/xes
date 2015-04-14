package com.up72.common.xml;

import com.up72.exception.UtilException;


public class XmlParseException extends UtilException {

	public XmlParseException(String msg) {
		super(msg);
	}

	public XmlParseException(String msg, Throwable e) {
		super(msg, e);
	}

	public XmlParseException(Throwable e) {
		super(e);
	}

}
