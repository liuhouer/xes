package com.up72.common.image.deal;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 图片处理接口，规定处理图片方法
 * @author bh.iune
 * @version 1.0
 */
public interface ImageDeal {
	/**
	 * 处理图片入口方法
	 * @param image 处理前图片
	 * @return image 处理后图片
	 */
	Image dealImage(Image image);
	
	/**
	 * 处理图片入口方法
	 * @param image 处理前图片
	 * @return image 处理后图片
	 */
	Image dealImage(String imageFile) throws FileNotFoundException, IOException;
	
	
}
