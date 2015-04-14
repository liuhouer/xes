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
 * 打水印处理类，水印来源为文字
 * @author bh.iune
 */
public class WaterWordDeal implements ImageDeal{
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
	//字体样式
	private WaterWordStyle style;
	private WaterImgDeal wid;

	public WaterWordDeal(int location,String waterWord,WaterWordStyle style) {
		this(location, 10, 10,0.5F,waterWord,style);
	}
	
	public WaterWordDeal(int location,float opacity, String waterWord,WaterWordStyle style) {
		this(location, 10, 10,opacity,waterWord,style);
	}
	
	public WaterWordDeal(int location,int left,int top,float opacity,String waterWord,WaterWordStyle style) {
		this.location = location;
		this.left = left;
		this.top = top;
		this.opacity = opacity;
		if(null != style){
			this.style = style;
		}else{
			this.style = new WaterWordStyle();
		}
		this.waterImg = createWatermark(waterWord, this.opacity);
		
		wid = new WaterImgDeal(this.location,this.left,this.top,this.opacity,this.waterImg);
	}
	
	/*
	 * 根据文字创建内存水印文件
	 */
	protected Image createWatermark(String waterWord,float opacity){
		if(this.style.getWidth()<=0){
			this.style.setWidth((int)(this.style.getFont().getSize() * waterWord.length()));
		}
		if(this.style.getHeight()<=0){
			this.style.setHeight(this.style.getFont().getSize()+10);
		}
		BufferedImage buffWatermark = new BufferedImage(this.style.getWidth(), this.style.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffWatermark.createGraphics(); 
		buffWatermark = g2d.getDeviceConfiguration().createCompatibleImage(this.style.getWidth(), this.style.getHeight(), Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = (Graphics2D) buffWatermark.getGraphics();
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g2d.setColor(this.style.getFontColor());
		g2d.setFont(this.style.getFont());
		g2d.drawString(waterWord, 2 ,this.style.getFont().getSize());
		g2d.dispose();
		return buffWatermark;
	}
	
	public Image dealImage(Image image) {
		Point point = wid.getDrawPoint(image);
		return wid.waterMark(point, image);
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
}
