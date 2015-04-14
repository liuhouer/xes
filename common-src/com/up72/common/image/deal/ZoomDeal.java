package com.up72.common.image.deal;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageDealTool;
import com.up72.common.image.ImageTools;



public class ZoomDeal implements ImageDeal {

	/**
	 * 最大宽
	 */
	private int maxWidth;
	/**
	 * 最大高
	 */
	private int maxHeight;
	/**
	 * 留白的方式，1为横向留白，2为纵向留白
	 */
	private boolean isMargins;

	public ZoomDeal(int maxWidth,int maxHeight) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}
	
	public ZoomDeal(int maxWidth,int maxHeight,boolean isMargins) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
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
	protected Image zoom(int width, int height,int marginsType, Image image) {
		int marginWidth =  0;
		int marginHeight =  0;
		switch (marginsType) {
		// 横向留白
			case 1:
				marginWidth = (this.maxWidth - width) / 2;
				height = this.maxHeight;
				width = this.maxWidth;
			break;
		// 纵向留白
			case 2:
				marginHeight = (this.maxHeight - height) / 2;
				width = this.maxWidth;
				height = this.maxHeight;
			break;
		}
		Image tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		tag = drawImage(tag, image, width, height, marginWidth, marginHeight);
		return tag;
	}

	/**
	 * 绘图
	 * 
	 * @param tag 内存图片
	 * @param src 原图image对象
	 * @param iWidth 宽
	 * @param iHeight 高
	 * @param x 横坐标
	 * @param y 纵坐标
	 */
	protected Image drawImage(Image tag, Image src, int width, int height,
			int x, int y) {
		tag.getGraphics().setColor(Color.WHITE);
		tag.getGraphics().fillRect(0, 0, width, height);
		tag.getGraphics().drawImage(
				src.getScaledInstance((width - 2*x), (height - 2* y), Image.SCALE_SMOOTH), x, y,
				null);
		return tag;
	}

	public Image dealImage(Image image) {
		// 计算缩放后的宽高
		double maxP = (this.maxWidth + 0D) / this.maxHeight;
		double imgP = (image.getWidth(null) + 0D) / image.getHeight(null);

		int width = 0;
		int height = 0;

		int marginsType = 0;
		if(this.isMargins){
			marginsType = getMarginsType(maxP, imgP);
		}
		if (maxP > imgP) {
			width = (int) ((this.maxHeight + 0F) / image.getHeight(null) * image
					.getWidth(null));
			height = this.maxHeight;
		} else {
			height = (int) ((this.maxWidth + 0F) / image.getWidth(null) * image
					.getHeight(null));
			width = this.maxWidth;
		}
		//缩放处理
		image = zoom(width, height,marginsType, image);
		return image;
	}

	// 获得留白类型，横向或纵向
	private int getMarginsType(double maxP, double imgP) {
		int marginsType;
		if (maxP > imgP) {
			marginsType = 1;
		} else {
			marginsType = 2;
		}
		return marginsType;
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
	public static void main(String[] args) throws Exception {
		
		Image image = ImageTools.readImage("D:/2.jpg");
		int side = 80;
		ImageDeal cut = new CutFixDeal(side,side,
				(image.getWidth(null) - side*2),(image.getHeight(null) - side*2));
		ImageDeal zoom = new ZoomDeal(200,300);

		ImageDealTool tool = new ImageDealTool();
		tool.regDeal("cut", cut);
		tool.regDeal("zoom",zoom);
		image = tool.deal(image);
		
		ImageTools.writeImage("D:/222.jpg", image);
	}
}
