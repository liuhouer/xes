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
 * 按固定尺寸裁剪图片
 * @author bh.iune
 */
public class CutFixDeal implements ImageDeal{
	//开始截取点，距离图片的左边距 
	private int left;
	//开始截取点，距离图片的上边距
	private int top;
	//图片截取的宽
	private int width;
	//图片截取的高
	private int height;
	
	/**
	 * 构造方法，左边距与上边距为0
	 * @param width 宽
	 * @param height 高
	 */
	public CutFixDeal(int width,int height){
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
	public CutFixDeal(int left,int top,int width,int height){
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}
	
	
	private Image cut(Image source){
		// 获得剪切后的Image对象 根据图像裁剪过滤器产生过滤器
		ImageFilter filter = new CropImageFilter(this.left, this.top, this.width, this.height);
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

	public Image dealImage(String imageFile) throws FileNotFoundException, IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}

}
