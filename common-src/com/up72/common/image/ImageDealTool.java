package com.up72.common.image;

import java.awt.Image;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.up72.common.image.deal.ImageDeal;

public final class ImageDealTool {
	
	private Map<String,ImageDeal> dealMap = null;
	
	/**
	 * 构造方法，初始化一个图片处理类
	 */
	public ImageDealTool(){
		this.dealMap = new LinkedHashMap<String, ImageDeal>();
	}
	
	/**
	 * 构造方法，初始化一个图片处理类
	 * @param dealMap 图片处理器map
	 */
	public ImageDealTool(Map<String,ImageDeal> dealMap){
		this.dealMap = new LinkedHashMap<String, ImageDeal>();
		if(null!=dealMap && !dealMap.isEmpty()){
			this.dealMap.putAll(dealMap);
		}
	}
	
	/**
	 * 开始图片的处理
	 */
	public Image deal(String imgPath){
		Image image = deal(ImageTools.readImage(imgPath));
		return image;
	}
	
	/**
	 * 处理指定路径的图片
	 * @param 
	 * @return 处理后的内存图
	 */
	public Image deal(Image image){
		Iterator<String> it = this.dealMap.keySet().iterator();
		while(it.hasNext()){
			image = dealImage(it.next(),image);
		}
		return image;
	}
	
	private Image dealImage(String name,Image image){
		Image result = null;
		ImageDeal imageDeal = this.dealMap.get(name);
		if(null == imageDeal
				|| null == image){
			result = image;
		} else {
			result = imageDeal.dealImage(image);
		}
		return result;
	}
	
	/**
	 * 注册图片的处理
	 * @return 处理后的内存图
	 */
	public void regDeal(String name,ImageDeal deal){
		this.dealMap.put(name,deal);
	}
	
	/**
	 * 注册图片处理
	 * @param dealMap 图片处理map
	 */
	public void regDeal(Map<String,ImageDeal> dealMap){
		this.dealMap.putAll(dealMap);
	}
	
	/**
	 * 移除全部注册
	 */
	public void removeAllDeal(){
		this.dealMap.clear();
	}
	
	/**
	 * 移除指定注册
	 * @param name
	 */
	public void removeDeal(String name){
		this.dealMap.remove(name);
	}
	
}
