package com.up72.common.image.deal;


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;


/**
 * 按最大边长，缩放图片
 * @author  bh.iune
 *
 */
public class ZoomMaxSideDeal implements ImageDeal{
	//最大宽高值
	private int maxSide;
	//是否留白
	private boolean isMargins;

	public ZoomMaxSideDeal(int maxSide){
		this(maxSide, false);
	}
	
	public ZoomMaxSideDeal(int maxSide,boolean isMargins){
		this.maxSide = maxSide;
		this.isMargins = isMargins;
	}
	
	/**
	 * 图片在MaxSideZoomDeal类中的处理入口
	 * 
	 * @param width 缩放后的图片宽
	 * @param height 缩放后的图片高
	 * @param image 原图片
	 * @return Image 缩放后的图片
	 */
	 protected Image zoom(int width,int height,Image image){
		Image tag = null;
		int size = width > height ? width : height;
		if (isMargins) {
			tag = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().setColor(Color.WHITE);
			tag.getGraphics().fillRect(0, 0, size, size);
			tag = drawImageHasMargins(tag, image, width, height, isMargins);
		} else {
			tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			tag = drawImage(tag, image, width, height, 0, 0);
		}
		return tag;
	}
	
	/**
	 * 绘制有留白图片
	 * 
	 * @param tag
	 * @param src
	 * @param width
	 * @param height
	 * @param isMargins
	 */
	 protected Image drawImageHasMargins(Image tag, Image src,
			int width, int height, boolean isMargins) {
		if (width > height) {
			int margin = (width - height) / 2;
			tag = drawImage(tag, src, width, height, 0, margin);
		} else {
			int margin = (height - width) / 2;
			tag = drawImage(tag, src, width, height, margin, 0);
		}
		return tag;
	}
	/**
	 * 绘图
	 * @param tag 内存图片
	 * @param src 原图image对象
	 * @param iWidth 宽
	 * @param iHeight 高
	 * @param x 横坐标
	 * @param y 纵坐标
	 */
	 protected Image drawImage(Image tag, Image src, int width,
			int height, int x, int y) { 
		tag.getGraphics().fillRect(0, 0, width, height);
		tag.getGraphics().drawImage(
				src.getScaledInstance(width, height, Image.SCALE_SMOOTH), x,
				y, null);
		return tag;
	}

	public Image dealImage(Image image) {
		//计算缩放后的宽高
		int width = (image.getWidth(null) > image.getHeight(null))?
				this.maxSide : (int) ((image.getWidth(null) / (image.getHeight(null) + 0.0)) * this.maxSide);
		int height = (image.getWidth(null) > image.getHeight(null))?
				(int) ((image.getHeight(null) / (image.getWidth(null) + 0.0)) * this.maxSide) : this.maxSide;
		//缩放处理
		image = zoom(width, height, image);
		return image;
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
}
