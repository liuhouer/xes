package com.up72.page;

import org.apache.log4j.Logger;


/**
 * 分页BEAN
 * @author Administrator
 *
 */
public class Pagination implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Pagination.class);
	
	public Pagination(int start,int range){
		init(start,range);
	}
	public Pagination(int start,int range,int maxIndexPages){
		init(start,range,maxIndexPages);
	}
	/**
	 * 初始化分页信
	 * @param parameters 参数Map
	 * @param range 每页显示信息
	 */
	private void init(int start,int range){
		this.range = range;
		if( start == 0 ){
			this.start = 0;
			return;
		}
		else{
			this.start = start;
		}
	}
	/**
	 * 初始化分页信
	 * @param parameters 参数Map
	 * @param range 每页显示信息
	 */
	private void init(int start,int range,int maxIndexPages){
		this.range = range;
		if( start == 0 ){
			this.start = 0;
			return;
		}
		else{
			this.start = start;
		}
		this.maxIndexPages = maxIndexPages;
	}
	
	public Pagination(int start){
		init(start,20);
	}
	
	private int start;
	private int range;
	private int totalPage = 0;
	private int totalRecord = 0;
	private int maxIndexPages = 5;//分页bar条数
	
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public int getMaxIndexPages() {
		return maxIndexPages;
	}

	public void setMaxIndexPages(int maxIndexPages) {
		this.maxIndexPages = maxIndexPages;
	}

	public void setTotalRecord(int totalRecord) {
		if(logger.isDebugEnabled()){
			logger.debug("总记录数 = " + totalRecord);
		}
		int temp = totalRecord % range;
		if (temp == 0){
			totalPage = totalRecord / range;
		}
		else{
			totalPage = totalRecord / range + 1;
		}
		if(logger.isDebugEnabled()){
			logger.debug("totalPage = " + totalPage);
		}
		this.totalRecord = totalRecord;
	}
	
}
