package com.up72.common.json;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * json解析工具类
 * @author jhe
 * 
 */
public class JsonUtil {
	
	/**
	 * object对象转换为 json格式字符串
	 * @param object 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked" })
	public  String object2Json(Object object) throws IOException{
		if(!isNotEmpty(object)){
			return "";
		}
		ObjectMapper mapper = JacksonMapper.getInstance();
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, object);
		gen.close();
		return sw.toString();
	}
	
	/**
	 * json格式字符串转换为object对象
	 * @param json
	 * @param cls
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked" })
	public  Object json2Object(String json, Class cls) throws  IOException{
		if(!isNotEmpty(json)){
			return null;
		}
		ObjectMapper mapper = JacksonMapper.getInstance();
		return mapper.readValue(json, cls);
	}
	
	

}
