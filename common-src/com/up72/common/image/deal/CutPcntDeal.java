package com.up72.common.image.deal;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;

/**
 * 按固定比例裁剪图片
 * @author bh.iune
 */
public class CutPcntDeal implements ImageDeal{
	
	private float left;
	private float top;
	private int width;
	private int height;
	
	/**
	 * 构造方法，左边距与上边距为0
	 * @param width 宽
	 * @param height 高
	 */
	public CutPcntDeal(int width,int height){
		this.left = 0;
		this.top = 0;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 构造方法 
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 */
	public CutPcntDeal(float left,float top,int width,int height){
		if(left > 1){
			this.left = 1;
		}else if(left < 0){
			this.left = 0;
		}else{
			this.left = left;
		}
		
		if(top > 1){
			this.top = 1;
		}else if(top < 0){
			this.top = 0;
		}else{
			this.top = top;
		}
		
		this.width = width;
		this.height = height;
	}
	

	private Image cut(Image source){
		int left = (int) (source.getWidth(null) * this.left);
		int top = (int) (source.getHeight(null) * this.top);
		// 获得剪切后的Image对象 根据图像裁剪过滤器产生过滤器
		ImageFilter filter = new CropImageFilter(left, top, this.width, this.height);
		// 下面根据过滤器产生图像生产者
		ImageProducer producer = new FilteredImageSource(source.getSource(),
				filter);
		// 根据图像生产者产生新图像
		Image croppedImage = Toolkit.getDefaultToolkit().createImage(producer);
		// 生成图片文件
		Image result = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		result.getGraphics().fillRect(0, 0, width, height);
		result.getGraphics().drawImage(
				croppedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
				0, null);
		return result;
	}
	
	public Image dealImage(Image image) {
		return cut(image);
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}

}
