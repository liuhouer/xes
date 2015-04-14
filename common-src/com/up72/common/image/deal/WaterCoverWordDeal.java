package com.up72.common.image.deal;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;


/**
 * 打水印处理类，水印来源为文字，水印方式为重复平铺
 * 
 * @author bh.iune
 */

public class WaterCoverWordDeal implements ImageDeal {
	// 水印的旋转角度
	private int degree;
	// 水印之间的水平间距
	private int horizontal;
	// 水印之间的垂直间距
	private int vertical;
	// 水印图片
	private Image waterImg;
	// 水印的透明度
	private float opacity;
	// 字体样式
	private WaterWordStyle style;
	private WaterCoverImgDeal wcid;

	protected WaterCoverWordDeal() {

	}

	public WaterCoverWordDeal(String waterWord, WaterWordStyle style)
			throws FileNotFoundException, IOException {
		this(waterWord, WaterCoverImgDeal.DEFAULT_DEGREE,
				WaterCoverImgDeal.DEFAULT_VERTICAL,
				WaterCoverImgDeal.DEFAULT_HORIZONTAL,
				WaterCoverImgDeal.DEFAULT_OPACITY, style);
	}

	public WaterCoverWordDeal(String waterWord, int degree, WaterWordStyle style) {
		this(waterWord, degree, WaterCoverImgDeal.DEFAULT_VERTICAL,
				WaterCoverImgDeal.DEFAULT_HORIZONTAL,
				WaterCoverImgDeal.DEFAULT_OPACITY, style);
	}

	public WaterCoverWordDeal(String waterWord, int degree, float opacity,
			WaterWordStyle style) {
		this(waterWord, degree, WaterCoverImgDeal.DEFAULT_VERTICAL,
				WaterCoverImgDeal.DEFAULT_HORIZONTAL, opacity, style);
	}

	public WaterCoverWordDeal(String waterWord, int degree, int vertical,
			int horizontal, WaterWordStyle style) {
		this(waterWord, degree, WaterCoverImgDeal.DEFAULT_VERTICAL,
				WaterCoverImgDeal.DEFAULT_HORIZONTAL,
				WaterCoverImgDeal.DEFAULT_OPACITY, style);
	}

	public WaterCoverWordDeal(String waterWord, int degree, int vertical,
			int horizontal, float opacity, WaterWordStyle style) {
		this.degree = degree;
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.opacity = opacity;
		if(null != style){
			this.style = style;
		}else{
			this.style = new WaterWordStyle();
		}
		this.waterImg = createWatermark(waterWord, this.opacity);

		this.wcid = new WaterCoverImgDeal(this.waterImg, this.degree,
				this.vertical, this.horizontal, this.opacity);
	}

	/*
	 * 根据文字创建内存水印文件
	 */
	protected Image createWatermark(String waterWord, float opacity) {
		if (this.style.getWidth() <= 0) {
			this.style
					.setWidth((int) (this.style.getFont().getSize() * waterWord
							.length()));
		}
		if (this.style.getHeight() <= 0) {
			this.style.setHeight(this.style.getFont().getSize() + 10);
		}
		BufferedImage buffWatermark = new BufferedImage(this.style.getWidth(),
				this.style.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffWatermark.createGraphics();
		buffWatermark = g2d.getDeviceConfiguration().createCompatibleImage(
				this.style.getWidth(), this.style.getHeight(),
				Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = (Graphics2D) buffWatermark.getGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));
		g2d.setColor(this.style.getFontColor());
		g2d.setFont(this.style.getFont());
		g2d.drawString(waterWord, 2, this.style.getFont().getSize());
		g2d.dispose();
		return buffWatermark;
	}

	public Image dealImage(Image image) {
		Image result = this.wcid.dealImage(image);
		return result;
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
	
}
