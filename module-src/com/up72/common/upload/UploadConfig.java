package com.up72.common.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.model.Dictionary;
import com.up72.sys.service.DictionaryManager;

public class UploadConfig {

	protected Log log = LogFactory.getLog(getClass());
	/** 文件上传 */
	public static String UPLOAD_SETTING = "uploadSetting";
	/** 文件上传保存的路径 */
	public static String UPLOAD_SAVE_PATH = "uploadSavePath";
	
	/** 文件类型和大小 */
	public static String TYPE_AND_SIZE = "uploadTypeAndSize";
	
	/** 默认文件上传保存的路径 */
	static String UPLOAD_SAVE_PATH_DEFAULT = "uploads";
	
	private DictionaryManager dictionaryManager;
	
	public UploadConfig() {
		try{
			dictionaryManager = (DictionaryManager)ApplicationContextHolder.getBean("dictionaryManager");
			init();
		}catch (Exception e) {
			log.error(e);
		}
	}
	public void init(){
		initUploadSavePath();
		initTypeAndSize();
	}
	/**文件上传保存的路径 */
	public String uploadSavePath;
	/** 文件类型和大小 */
	public List<Map<String,String>> uploadTypeAndSize = new ArrayList<Map<String,String>>();
	
	/**上传大小限制 */
	public void initUploadSavePath() {
		this.uploadSavePath = dictionaryManager.getValue(UPLOAD_SAVE_PATH);
		if(null == uploadSavePath || uploadSavePath.trim().equals("")){
			this.uploadSavePath = UPLOAD_SAVE_PATH_DEFAULT;
		}
	}
	/**文件类型和大小  */
	public void initTypeAndSize() {
		Dictionary dictionary = dictionaryManager.findDictionaryByKey(TYPE_AND_SIZE);
		Map<String, String> map = new HashMap<String, String>();
		List<Dictionary> breviaryImgSizeList = new LinkedList<Dictionary>();
		if (null != dictionary) {
			breviaryImgSizeList = dictionary.getChilds(dictionary.getId());
		}
			
		if (null != breviaryImgSizeList && breviaryImgSizeList.size()!= 0) {
			for (int i = 0; i < breviaryImgSizeList.size(); i++) {
				Dictionary dict = breviaryImgSizeList.get(i);
				if (null != dict) {
					String value = dict.getRefCode();
					if (null != value) {
						String[] array = value.split(",");
						if (array.length >= 2) {
							String type = array[0];
							String size = array[1];
							map.put(type,size);
						}
					 }
				  }
				}
			this.uploadTypeAndSize.add(map);
			
		}
	}
}
