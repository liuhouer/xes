package com.up72.common.image.deal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;


/**
 * 图片旋转处理
 * 
 * @author wqtan
 */
public class RotateDeal implements ImageDeal {
	// 图片类型常量png格式
	public static final int IMG_TYPE_PNG = 1;
	// 图片类型常量jpg格式
	public static final int IMG_TYPE_JPG = 2;

	// 角度
	private int degree;
	// 图片格式
	private int imgType;

	protected RotateDeal(){
		
	}
	
	/**
	 * 指定旋转角度的构造方法，默认图片格式为jpg
	 * 
	 * @param degree
	 */
	public RotateDeal(int degree) {
		this(degree, IMG_TYPE_JPG);
	}

	/**
	 * 指定旋转角度和图片格式的构造方法，图片格式参见本类常量
	 * 
	 * @param degree
	 * @param imgType
	 */
	public RotateDeal(int degree, int imgType) {
		this.degree = degree;
		this.imgType = imgType;
		
		this.degree = Math.abs(this.degree) % 360;
		// 图片类型不符合标准数据处理
		if(this.imgType != IMG_TYPE_JPG && this.imgType != IMG_TYPE_PNG){
			this.imgType = IMG_TYPE_JPG;
		}
	}

	/**
	 * 获得旋转以后的图片宽高
	 * @param image
	 * @return Point
	 */
	protected Point getWH(Image image) {
		// 计算旋转以后图片的宽和高
		double wh = Math.hypot(image.getWidth(null), image.getHeight(null));

		double sina1 = image.getWidth(null) / wh;
		double cosa1 = image.getHeight(null) / wh;

		double sina2 = image.getHeight(null) / wh;
		double cosa2 = image.getWidth(null) / wh;

		double sinb = Math.abs(Math.sin(Math.PI / 180 * this.degree));
		double cosb = Math.abs(Math.cos(Math.PI / 180 * this.degree));

		double sinab1 = sina1 * cosb + sinb * cosa1;
		double sinab2 = sina2 * cosb + sinb * cosa2;

		int width = (int) (sinab1 * wh);
		int height = (int) (sinab2 * wh);
		// 计算旋转以后图片的宽和高 end
		return new Point(width, height);
	}

	/**
	 * 绘制png图片
	 * 
	 * @return Image
	 */
	protected Image drawPngImage(Image image) {
		Point wh = this.getWH(image);
		BufferedImage result = new BufferedImage(wh.x, wh.y,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = result.createGraphics();

		// png处理 start
		result = g2d.getDeviceConfiguration().createCompatibleImage(wh.x, wh.y,
				Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = result.createGraphics();
		// png处理end

		// 旋转设置
		g2d.rotate(Math.toRadians(degree), wh.x / 2, wh.y / 2);
		g2d.drawImage(image.getScaledInstance(image.getWidth(null), image
				.getHeight(null), Image.SCALE_SMOOTH), (wh.x - image
				.getWidth(null)) / 2, (wh.y - image.getHeight(null)) / 2, null);
		g2d.dispose();

		return result;
	}

	/**
	 * 绘制jpg图片
	 * 
	 * @return Image
	 */
	protected Image drawJpgImage(Image image) {
		Point wh = this.getWH(image);
		BufferedImage result = new BufferedImage(wh.x, wh.y,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = result.createGraphics();

		// jpg处理 start
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, wh.x, wh.y);
		// jpg处理end
		
		// 旋转设置
		g2d.rotate(Math.toRadians(degree), wh.x / 2, wh.y / 2);
		g2d.drawImage(image.getScaledInstance(image.getWidth(null), image
				.getHeight(null), Image.SCALE_SMOOTH), (wh.x - image
				.getWidth(null)) / 2, (wh.y - image.getHeight(null)) / 2, null);
		g2d.dispose();

		return result;
	}

	public Image dealImage(Image image) {
		Image result = null;
		
		switch (this.imgType) {
		case IMG_TYPE_JPG:
			result = this.drawJpgImage(image);
			break;
		case IMG_TYPE_PNG:
			result = this.drawPngImage(image);
			break;
		}

		return result;
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
}
