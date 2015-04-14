package com.up72.page;

import java.util.List;

/**
 * 查询结果对象
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class QueryResult implements java.io.Serializable{
	
	private static final long serialVersionUID = 6377333821376847830L;

	public QueryResult(){}
	
	private String hql;
	private Pagination pagination;
	@SuppressWarnings("unchecked")
	private List items = null;
	private int qtime;
	
	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@SuppressWarnings("unchecked")
	public QueryResult(Pagination pagination,List items){
		this.pagination = pagination;
		this.items = items;
	}
	
	@SuppressWarnings("unchecked")
	public QueryResult(Pagination pagination,List items,int qtime){
		this.pagination = pagination;
		this.items = items;
		this.qtime = qtime;
	}
		
	@SuppressWarnings("unchecked")
	public List getItems() {
		return this.items;
	}
	@SuppressWarnings("unchecked")
	public void setItems(List items){
		this.items = items;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public int getQtime() {
		return qtime;
	}

	public void setQtime(int qtime) {
		this.qtime = qtime;
	}

}
