package com.up72.common.json;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author jhe
 * 
 */
public class JacksonMapper {

	private static final ObjectMapper mapper = new ObjectMapper();

	private JacksonMapper(){
	}

	public static ObjectMapper getInstance(){
		return mapper;
	}

}