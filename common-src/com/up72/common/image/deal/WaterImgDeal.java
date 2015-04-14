package com.up72.common.image.deal;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;


/**
 * 打水印处理类，水印来源为图片文件
 * @author bh.iune
 */
public class WaterImgDeal implements ImageDeal{
	//水印位置，对应WaterBean的变量
	private int location;
	//左偏移
	private int left;
	//上偏移
	private int top;
	//水印图片
	private Image waterImg;
	//水印的透明度
	private float opacity;
	
	protected WaterImgDeal(){
		
	}
	
	public WaterImgDeal(int location,Image waterImg) {
		this(location, 10, 10,0.5F,waterImg);
	}
	
	public WaterImgDeal(int location,float opacity, Image waterImg) {
		this(location, 10, 10,opacity,waterImg);
	}
	
	public WaterImgDeal(int location,float opacity,String waterImg) throws FileNotFoundException, IOException {
		this(location, 10, 10,opacity,waterImg);
	}
	
	public WaterImgDeal(int location,int left,int top,float opacity,String waterImg) throws FileNotFoundException, IOException {
		this(location, left, top,opacity, ImageTools.readImage(waterImg));
	}
	
	public WaterImgDeal(int location,int left,int top,float opacity,Image waterImg) {
		this.location = location;
		this.left = left;
		this.top = top;
		this.waterImg = waterImg;
		this.opacity = opacity;
	}
	
	
	protected Point getDrawPoint(Image image){
		int x = 0;
		int y = 0;
		switch (this.location) {
		case WaterBean.LEFT_TOP:
			break;
		case WaterBean.LEFT_BOTTOM:
			y = image.getHeight(null);
			y -= this.waterImg.getHeight(null);
			break;
		case WaterBean.RIGHT_TOP:
			x = image.getWidth(null);
			x -= this.waterImg.getWidth(null);
			break;
		case WaterBean.RIGHT_BOTTOM:
			x =  image.getWidth(null);
			y = image.getHeight(null);
			x -= this.waterImg.getWidth(null);
			y -= this.waterImg.getHeight(null);
			break;
		case WaterBean.LEFT_CENTER:
			y = image.getHeight(null) / 2;
			y -= this.waterImg.getHeight(null) / 2;
			break;
		case WaterBean.RIGHT_CENTER:
			x = image.getWidth(null);
			x -= this.waterImg.getWidth(null);
			y = image.getHeight(null) / 2;
			y -= this.waterImg.getHeight(null) / 2;
			break;
		case WaterBean.TOP:
			x = image.getWidth(null) / 2;
			x -= this.waterImg.getWidth(null) / 2;
			break;
		case WaterBean.BOTTOM:
			x = image.getWidth(null) / 2;
			x -= this.waterImg.getWidth(null) / 2;
			y = image.getHeight(null);
			y -= this.waterImg.getHeight(null);
			break;
		case WaterBean.CENTER:
			x = image.getWidth(null) / 2;
			y = image.getHeight(null) / 2;
			x -= this.waterImg.getWidth(null) / 2;
			y -= this.waterImg.getHeight(null) / 2;
			break;
		}
		x += this.left;
		y += this.top;
		return new Point(x,y);
	}

	/*
	 * 根据文字创建内存水印文件
	 */
	private Image watermark(){
		BufferedImage buffImage = new BufferedImage(this.waterImg.getWidth(null), this.waterImg.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffImage.createGraphics(); 
		buffImage = g2d.getDeviceConfiguration().createCompatibleImage(buffImage.getWidth(), buffImage.getHeight(), Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = (Graphics2D) buffImage.getGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacity));
	    g2d.drawImage(this.waterImg.getScaledInstance(this.waterImg.getWidth(null), 
	    		this.waterImg.getHeight(null), Image.SCALE_SMOOTH), 0,0, null);
		return buffImage;
	}

	protected Image waterMark(Point point,Image image){
		image.getGraphics().drawImage(
				this.waterImg.getScaledInstance(
					this.waterImg.getWidth(null), 
					this.waterImg.getHeight(null
				), 
				Image.SCALE_SMOOTH), 
				point.x,
				point.y, 
				null
			);
		return image;
	}
	
	public Image dealImage(Image image) {
		Point point = this.getDrawPoint(image);
		this.waterImg = watermark();
		return waterMark(point,image);
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
}
