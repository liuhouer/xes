package com.up72.common.version;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.Serializable;
import java.util.Date;

import com.up72.common.CommonConstants;

public class Version implements Serializable {
	private String versionNum;
	private Date createDate;
	private String author;
	private IVersion versionObj;
	
	public String getSavePath(){
		return getSavePathByClzId(this.versionObj.getClass(),this.versionObj.getId());
	}
	public static String getSavePathByClzId(Class clz,Long id){
		String savePath = stringUtil.parseToPath(
				CommonConstants.ROOTPATH+"/backUp/"+
				clz.getSimpleName()+"/"
				+id+".obj");
		return savePath;
	}
	
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Object getVersionObj() {
		return versionObj;
	}
	public void setVersionObj(IVersion versionObj) {
		this.versionObj = versionObj;
	}
	

}
