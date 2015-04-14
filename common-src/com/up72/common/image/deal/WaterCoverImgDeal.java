package com.up72.common.image.deal;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.up72.common.image.ImageTools;


/**
 * 打水印处理类，水印来源为图片文件，水印方式为重复平铺
 * @author bh.iune
 */

public class WaterCoverImgDeal implements ImageDeal{

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

	protected final static int DEFAULT_DEGREE = 30;
	protected final static int DEFAULT_HORIZONTAL = 0;
	protected final static int DEFAULT_VERTICAL = 0;
	protected final static float DEFAULT_OPACITY = 0.5F;

	protected WaterCoverImgDeal() {

	}

	public WaterCoverImgDeal(String waterImg) throws FileNotFoundException,
			IOException {
		this(waterImg, DEFAULT_DEGREE, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL,
				DEFAULT_OPACITY);
	}

	public WaterCoverImgDeal(String waterImg, int degree)
			throws FileNotFoundException, IOException {
		this(waterImg, degree, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL,
				DEFAULT_OPACITY);
	}

	public WaterCoverImgDeal(String waterImg, int degree, float opacity)
			throws FileNotFoundException, IOException {
		this(waterImg, degree, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL, opacity);
	}

	public WaterCoverImgDeal(String waterImg, int degree, int vertical,
			int horizontal) throws FileNotFoundException, IOException {
		this(waterImg, degree, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL,
				DEFAULT_OPACITY);
	}

	public WaterCoverImgDeal(String waterImg, int degree, int vertical,
			int horizontal, float opacity) throws FileNotFoundException,
			IOException {
		this.waterImg = ImageTools.readImage(waterImg);
		this.degree = degree;
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.opacity = opacity;
	}

	public WaterCoverImgDeal(Image waterImg) {
		this(waterImg, DEFAULT_DEGREE, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL,
				DEFAULT_OPACITY);
	}

	public WaterCoverImgDeal(Image waterImg, int degree) {
		this(waterImg, degree, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL,
				DEFAULT_OPACITY);
	}

	public WaterCoverImgDeal(Image waterImg, int degree, int opacity) {
		this(waterImg, degree, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL, opacity);
	}

	public WaterCoverImgDeal(Image waterImg, int degree, int vertical,
			int horizontal) {
		this(waterImg, degree, DEFAULT_VERTICAL, DEFAULT_HORIZONTAL,
				DEFAULT_OPACITY);
	}

	public WaterCoverImgDeal(Image waterImg, int degree, int vertical,
			int horizontal, float opacity) {
		this.waterImg = waterImg;
		this.degree = degree;
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.opacity = opacity;
	}

	/*
	 * 根据创建内存水印文件
	 */
	protected Image watermark() {
		BufferedImage buffImage = new BufferedImage(this.waterImg
				.getWidth(null), this.waterImg.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffImage.createGraphics();
		buffImage = g2d.getDeviceConfiguration().createCompatibleImage(
				buffImage.getWidth(), buffImage.getHeight(),
				Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = (Graphics2D) buffImage.getGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				this.opacity));
		g2d.drawImage(this.waterImg.getScaledInstance(this.waterImg
				.getWidth(null), this.waterImg.getHeight(null),
				Image.SCALE_SMOOTH), 0, 0, null);
		return buffImage;
	}
	
	protected Image waterMark(Image image) {
		int iw = image.getWidth(null);
		int ih = image.getHeight(null);
		int ww = this.waterImg.getWidth(null);
		int wh = this.waterImg.getHeight(null);

		int hnum = iw % ww == 0 ? iw / ww : iw / ww + 1;
		int vnum = ih % wh == 0 ? ih / wh : ih / wh + 1;

		int x = 0;
		int y = 0;
		Graphics g = image.getGraphics();

		for (int i = 0; i < hnum; i++) {
			for (int j = 0; j < vnum; j++) {
				g.drawImage(this.waterImg.getScaledInstance(this.waterImg
						.getWidth(null), this.waterImg.getHeight(null),
						Image.SCALE_SMOOTH), x, y, null);
				y += wh + this.vertical;
			}

			y = 0;
			x += ww + this.horizontal;
		}

		return image;
	}

	public Image dealImage(Image image) {
		RotateDeal rd = new RotateDeal(this.degree, RotateDeal.IMG_TYPE_PNG);
		this.waterImg = (BufferedImage) rd.dealImage(this.waterImg);
		this.waterImg = watermark();
		Image result = waterMark(image);
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		String imageFile = "C:/jimi/12212.jpg";
		String waterFile = "C:/jimi/logo.jpg";
		String saveFile = "C:/jimi/12212_water.jpg";

		Image image = ImageTools.readImage(imageFile);

		WaterCoverImgDeal wcw = new WaterCoverImgDeal(waterFile, 300,0.3F);
		image = wcw.dealImage(image);

		ImageIO.write((BufferedImage) image, "png", new File(saveFile));
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
}
