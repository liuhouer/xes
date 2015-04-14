package com.up72.common.image.deal;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;


/**
 * 按比例，缩放图片
 * 
 * @author bh.iune
 */
public class ZoomScaleDeal implements ImageDeal{
	//缩放比例
	private float zoom;
	//是否留白
	private boolean isMargins;
	
	private ZoomMaxSideDeal mszd ;
	
	public ZoomScaleDeal(float zoom){
		this(zoom, false);
	}
	
	public ZoomScaleDeal(float zoom,boolean isMargins){
		this.zoom = zoom;
		this.isMargins = isMargins;
		mszd = new ZoomMaxSideDeal(0,this.isMargins);
	}
	
	public Image dealImage(Image image) {
		//计算缩放后的宽高
		int width = (int) (this.zoom * image.getWidth(null));
		int height = (int) (this.zoom * image.getHeight(null));
		//缩放处理
		image = mszd.zoom(width, height, image);
		return image;
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {//压缩图像大小
		ZoomScaleDeal zs = new ZoomScaleDeal((float)0.6);
		ImageTools.writeImage("d:/222.jpg", zs.dealImage("d:/2.jpg"));
	}
}
