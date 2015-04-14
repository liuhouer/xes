package com.up72.common.image;

import static com.up72.common.CommonUtils.stringUtil;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.sys.model.Dictionary;
import com.up72.sys.service.DictionaryManager;


public class ImageConfig {

	protected Log log = LogFactory.getLog(getClass());
	/**图片设置 */
	public static String IMAGE_SETTING = "imageSetting";
	/**上传大小限制 */
	public static String UPLOAD_SIZE = "imageUploadSize";
	/** 保存大小限制 */
	public static String SAVE_SIZE = "imageSaveSize";
	/** 最大宽度 */
	public static String MAX_WIDTH = "imageMaxWidth";
	/**最大高度 */
	public static String MAX_HEIGTH = "imageMaxHeigth";
	/** 是否强制转换 */
	public static String IS_CONVERT = "imageIsConvert";
	/** 缩略图 */
	public static String BREVIARY_IMG = "imageBreviaryImg";
	/** 缩略图尺寸 */
	public static String BREVIARY_IMG_SIZE = "imageBreviaryImgSize";
	/** 水印图片尺寸 */
	public static String WATERMARK_SIZE = "imageWaterMarkSize";
	
	/**默认上传大小限制 */
	static String UPLOAD_SIZE_DEFAULT = "1024K";
	/** 默认保存大小限制 */
	static String SAVE_SIZE_DEFAULT = "512K";
	/** 默认最大宽度 */
	static String MAX_WIDTH_DEFAULT = "1024";
	/**默认最大高度 */
	static String MAX_HEIGTH_DEFAULT = "768";
	/** 默认是否强制转换 */
	static String IS_CONVERT_DEFAULT = "false";
	/** 默认水印图片尺寸 */
	static String WATERMARK_SIZE_DEFAULT = "127x56";
	
	
	private DictionaryManager dictionaryManager;
	
	public ImageConfig() {
		try{
			dictionaryManager = (DictionaryManager)ApplicationContextHolder.getBean("dictionaryManager");
			init();
		}catch (Exception e) {
			log.error(e);
		}
	}
	public void init() {
		initUploadSize();
		initSaveSize();
		initMaxWidth();
		initMaxHeigth();
		initIsConvert();
		initBreviaryImgSize();
		initWaterMarkSize();
	}

	/**上传大小限制 */
	public long imageUploadSize;
	/** 保存大小限制 */
	public long imageSaveSize;
	/** 最大宽度 */
	public int imageMaxWidth;
	/**最大高度 */
	public int imageMaxHeigth;
	/** 是否强制转换 */
	public boolean imageIsConvert;
	/** 缩略图尺寸 */
	public List<Point> imageBreviaryImgSize = new ArrayList<Point>();
	/** 水印图片尺寸 */
	public Point imageWaterMarkSize;
	

	/**上传大小限制 */
	public void initUploadSize() {
		String imageUploadSizeString = dictionaryManager.getValue(UPLOAD_SIZE);
		if(null == imageUploadSizeString || imageUploadSizeString.trim().equals("")){
			imageUploadSizeString = UPLOAD_SIZE_DEFAULT;
		}
		imageUploadSizeString = this.subStr(imageUploadSizeString);
		this.imageUploadSize = 1024*stringUtil.parseLong(imageUploadSizeString);
	}
	
	/**保存大小限制*/
	public void initSaveSize(){
		String imageSaveSizeString = dictionaryManager.getValue(SAVE_SIZE);
		if(null == imageSaveSizeString || imageSaveSizeString.trim().equals("")){
			imageSaveSizeString = SAVE_SIZE_DEFAULT;
		}
		imageSaveSizeString = this.subStr(imageSaveSizeString);
		this.imageSaveSize = 1024*stringUtil.parseLong(imageSaveSizeString);
	}
	
	/** 最大宽度*/
	public void initMaxWidth(){
		String IntimageMaxWidth = dictionaryManager.getValue(MAX_WIDTH);
		if(null == IntimageMaxWidth || IntimageMaxWidth.trim().equals("")){
			IntimageMaxWidth = MAX_WIDTH_DEFAULT;
		}
		this.imageSaveSize = stringUtil.parseInt(IntimageMaxWidth);
	}
	
	/** 最大高度*/
	public void initMaxHeigth(){
		String IntimageMaxHeigth = dictionaryManager.getValue(MAX_HEIGTH);
		if(null == IntimageMaxHeigth || IntimageMaxHeigth.trim().equals("")){
			IntimageMaxHeigth = MAX_WIDTH_DEFAULT;
		}
		this.imageSaveSize = stringUtil.parseInt(IntimageMaxHeigth);
	}
	
	/** 是否强制转换*/
	public void initIsConvert(){
		String imageIsConvertString = dictionaryManager.getValue(IS_CONVERT);
		if(null == imageIsConvertString || imageIsConvertString.trim().equals("")){
			imageIsConvertString = IS_CONVERT_DEFAULT;
		}
		if (imageIsConvertString.equals("true")) {
			imageIsConvert = true;
		}
		if (imageIsConvertString.equals("false")) {
			imageIsConvert = false;
		}
	}
	
	/** 缩略图尺寸*/
	public void initBreviaryImgSize(){
		Dictionary dictionary = dictionaryManager.findDictionaryByKey(BREVIARY_IMG);
		List<Dictionary> breviaryImgSizeList = new LinkedList<Dictionary>();
		if (null != dictionary) {
			breviaryImgSizeList = dictionary.getChilds(dictionary.getId());
		}
			
		
		for (int i = 0; i < breviaryImgSizeList.size(); i++) {
			Dictionary dict = breviaryImgSizeList.get(i);
			if (null != dict) {
				Point p = null;
				String[] array = dict.getRefCode().split("x");
				if(array.length==2){
					p = new Point(stringUtil.parseInt(array[0]),stringUtil.parseInt(array[1]));
				}
				if(null!=p){
					imageBreviaryImgSize.add(p);
				}
			}
		}
	}
	
	/**水印图片尺寸*/
	public void initWaterMarkSize(){
		String stringWaterMarkSize = dictionaryManager.getValue(WATERMARK_SIZE);
		if(null == stringWaterMarkSize || stringWaterMarkSize.trim().equals("")){
			stringWaterMarkSize = WATERMARK_SIZE_DEFAULT;
		}
		String[] array = stringWaterMarkSize.split("x");
		if(array.length==2){
			this.imageWaterMarkSize = new Point(stringUtil.parseInt(array[0]),stringUtil.parseInt(array[1]));
		}
	}
	
	public String subStr(String str){
		String result = null;
		if (null != str && !str.trim().equals("")) {
			if (str.endsWith("K")) {
				result = str.substring(0, str.length()-1);
			}else{
				return str;
			}
		}
		return result;
	}
}
