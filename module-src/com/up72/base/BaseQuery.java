package com.up72.base;

import com.up72.framework.page.PageRequest;

@SuppressWarnings({ "unchecked" })
public class BaseQuery extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = -360860474471966681L;
	public static final int DEFAULT_PAGE_SIZE = 15;
	
	public String searchColumn;
	public String searchColumnValue;
	
    static {
        System.out.println("BaseQuery.DEFAULT_PAGE_SIZE="+DEFAULT_PAGE_SIZE);
    }
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}

	public String getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}

	public String getSearchColumnValue() {
		return searchColumnValue;
	}

	public void setSearchColumnValue(String searchColumnValue) {
		this.searchColumnValue = searchColumnValue;
	}
	
	
	  
}
