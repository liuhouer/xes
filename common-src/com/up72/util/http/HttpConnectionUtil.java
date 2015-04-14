package com.up72.util.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * http请求处理工具类，向指定的地址发送请求，获取请求参数
 * 
 * @author wqtan
 * @since 1.0
 * @version 1.0
 */
public class HttpConnectionUtil {
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	
	// 编码
	private String encode;
	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}

	// 超时
	private int timeout;
	public int getTimeOut() {
		return timeout;
	}
	public void setTimeOut(int timeOut) {
		this.timeout = timeOut;
	}
	private HttpConnectionUtil() {
		this.encode = "UTF-8";
	}
	
	private Proxy proxy;
	
	public Proxy getProxy(){
		return this.proxy;
	}
	public void setProxy(Proxy proxy){
		this.proxy = proxy;
	}
	
	public void setProxy(String addr,int port){
		this.proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(addr,port));
	}
	
	public static HttpConnectionUtil instants(){
		return new HttpConnectionUtil();
	}
	
	/**
	 * 发送一个http请求，获取请求后的回执信息
	 * 
	 * @param method 请求的方式，post/get，建议使用本类的METHOD_POST和METHOD_GET静态常量
	 * @param url 请求的地址
	 * @param params 请求的参数列表
	 * @return 请求结束的回执
	 * @throws HTTPException 
	 */
	public String send(String method,String url,Map<String,String> params) throws HTTPException{
		if(null == method || method.trim().equals("")){
			method = METHOD_GET;
		}
		method = method.trim().toUpperCase();

		if(METHOD_POST.equals(method)){
			HttpURLConnection connection = getConnection(url,METHOD_POST);
			return sendByPostMethod(connection, params);
		}else if(METHOD_GET.equals(method)){
			return sendByGetMethod(url, params);
		}else{
			throw new HTTPException("request method is error. you only use 'post' or 'get'.");
		}
	}

	public InputStream sendStream(String method,String url,Map<String,String> params){
		if(null == method || method.trim().equals("")){
			method = METHOD_GET;
		}
		method = method.trim().toUpperCase();

		if(METHOD_POST.equals(method)){
			HttpURLConnection connection = getConnection(url,METHOD_POST);
			// 初始化post请求属性
			this.initPostProperties(connection);
			
			// 发送参数
			this.sendParams(connection, params);
			try {
				return connection.getInputStream();
			} catch (IOException e) {
				throw new HTTPException(e);
			}
		}else if(METHOD_GET.equals(method)){
			// 拼接url
			String paramString = this.parseUrlParams(params);
			if(!paramString.trim().equals("")){
				if(url.indexOf("?") != -1){
					url = url + "&" + paramString;
				}else{
					url = url + "?" + paramString;
				}
			}
			
			HttpURLConnection connection = this.getConnection(url,METHOD_GET);
			try {
				connection.connect();
				return connection.getInputStream();
			} catch (IOException e) {
				throw new HTTPException(e);
			}
		}else{
			throw new HTTPException("request method is error. you only use 'post' or 'get'.");
		}
	}
	
	/**
	 * 发送一个http请求，获取请求后的回执信息
	 * 
	 * @param method 请求的方式，post/get，建议使用本类的METHOD_POST和METHOD_GET静态常量
	 * @param url 请求的地址
	 * @param params 请求的参数列表
	 * @return 请求结束的回执
	 * @throws HTTPException 
	 */
	public String send(String method,String url,String params) throws HTTPException{
		method = null == method?"":method.trim().toUpperCase();
		
		if(METHOD_POST.equals(method)){
			HttpURLConnection connection = getConnection(url,METHOD_POST);
			return sendByPostMethod(connection, params);
		}else if(METHOD_GET.equals(method)){
			return sendByGetMethod(url, params);
		}else{
			throw new HTTPException("request method is error.you only use 'post' or 'get'.");
		}
	} 
	
	// get HttpURLConnection
	private HttpURLConnection getConnection(String urlString,String method) throws HTTPException{
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			throw new HTTPException(e);
		}
		
		HttpURLConnection result = null;
		try {
			if(null != proxy){
				result = (HttpURLConnection) url.openConnection(this.proxy);
			}else{
				result = (HttpURLConnection) url.openConnection();
			}
			result.setRequestMethod(method);
			if(this.timeout > 0){
				result.setConnectTimeout(timeout);
			}
		} catch (IOException e) {
			throw new HTTPException(e);
		}
		
		if(null == result){
			throw new HTTPException("HttpURLConnection is null.");
		}
		return result;
	}
	
	// post request method
	private String sendByPostMethod(HttpURLConnection connection,Map<String,String> params) throws HTTPException{
		// 初始化post请求属性
		this.initPostProperties(connection);
		
		// 发送参数
		this.sendParams(connection, params);
		
		// 获取服务器返回结果
		String result = getResponseResult(connection);
		
		return result;
	}
	
	//post request method
	private String sendByPostMethod(HttpURLConnection connection,String params) throws HTTPException{
		// 初始化post请求属性
		this.initPostProperties(connection);
		
		DataOutputStream out = null;
		try {
			connection.connect();
			out = new DataOutputStream(connection
	                .getOutputStream());
			out.writeBytes(params);
			out.flush();
		} catch (IOException e) {
			throw new HTTPException(e);
		} finally{
			close(out);
		}
		
		// 获取服务器返回结果
		String result = getResponseResult(connection);
		return result;
	}
	
	// 初始化post请求属性
	private void initPostProperties(HttpURLConnection connection) {
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);	
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	}
	
	// 发送参数
	private void sendParams(HttpURLConnection connection,
			Map<String, String> params) throws HTTPException {
		DataOutputStream out = null;
		try {
			connection.connect();
			out = new DataOutputStream(connection
	                .getOutputStream());
			String stringParam = this.parseUrlParams(params);
			out.writeBytes(stringParam);
			out.flush();
		} catch (IOException e) {
			throw new HTTPException("Send param error : "+e);
		} finally{
			close(out);
		}
	}
	
	// get request method
	private String sendByGetMethod(String url,Map<String,String> params) throws HTTPException{
		// 拼接url
		String paramString = this.parseUrlParams(params);
		if(!paramString.trim().equals("")){
			if(url.indexOf("?") != -1){
				url = url + "&" + paramString;
			}else{
				url = url + "?" + paramString;
			}
		}
		
		HttpURLConnection connection = this.getConnection(url,METHOD_GET);
		try {
			connection.connect();
		} catch (IOException e) {
			throw new HTTPException(e);
		}
		
		// 获取服务器返回结果
		String result = getResponseResult(connection);

		return result;
	}
	
	// get request method
	private String sendByGetMethod(String url,String params) throws HTTPException{
		// 拼接url
		if(!params.trim().equals("")){
			if(url.indexOf("?") != -1){
				url = url + "&" + params;
			}else{
				url = url + "?" + params;
			}
		}

		HttpURLConnection connection = this.getConnection(url,METHOD_GET);
		try {
			connection.connect();
		} catch (IOException e) {
			throw new HTTPException(e);
		}
		
		// 获取服务器返回结果
		String result = getResponseResult(connection);
		
		return result;
	}

	// 格式化param Map为url的参数字符串
	private String parseUrlParams(Map<String,String> params) throws HTTPException{
		String result = "";
		if(null != params
				&& !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			StringBuilder paramBuffer = new StringBuilder();
			while(it.hasNext()){
				String param = it.next();
				String value = params.get(param);
				paramBuffer.append("&"+param+"="+value);
			}
			result = paramBuffer.toString();
			result = result.substring(1);
		}
		return result;
	}

	// 获取服务器返回结果
	private String getResponseResult(HttpURLConnection connection) throws HTTPException{
		StringBuilder result = new StringBuilder();
		InputStream in = null;
		try {
			in = connection.getInputStream();
			byte[] buff = new byte[1024];
			int flag = -1;
			
			while((flag = in.read(buff))!=-1){
				if(null != this.encode && !this.encode.trim().equals("")){
					result.append(new String(buff,0,flag,this.encode));
				}else{
					result.append(new String(buff,0,flag));
				}
			}
			in.close();
		} catch (IOException e) {
			throw new HTTPException(e);
		}finally{
			close(in);
		}
		return result.toString();
	}
	
	// close input stream
	private void close(InputStream in){
		if(null != in){
			try {
				in.close();
			} catch (IOException e) {}
		}
	}
	
	// close output stream
	private void close(OutputStream out){
		if(null != out){
			try {
				out.close();
			} catch (IOException e) {}
		}
	}
	
	public InputStream openFileInputStream(String url){
		HttpURLConnection connection = getConnection(url,METHOD_GET);
		// 初始化post请求属性
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);	
		connection.setDoOutput(true);  
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type","application/octet-stream");

		try {
			return connection.getInputStream();
		} catch (IOException e) {
			throw new HTTPException(e);
		}
	}
}
