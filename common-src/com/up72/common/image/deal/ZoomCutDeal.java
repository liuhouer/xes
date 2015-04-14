package com.up72.common.image.deal;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.up72.common.image.ImageTools;
import com.up72.exception.UtilException;

public class ZoomCutDeal implements ImageDeal{

	private float width;
	private float height;
	
	public ZoomCutDeal(int width,int height) {
		this.width = width;
		this.height = height;
		if(this.width ==0 && this.height==0){
			throw new UtilException("参数不能同时为0");
		}
	}
	
	public Image dealImage(Image image) {
		float w = image.getWidth(null);
		float h = image.getHeight(null);
		if(this.height == 0){
			ZoomDeal zoomDeal = new ZoomDeal((int)width,(int)(h/w*width));
			image = zoomDeal.dealImage(image);
		}else if(this.width == 0){
			ZoomDeal zoomDeal = new ZoomDeal((int)(w/h*height),(int)height);
			image = zoomDeal.dealImage(image);
		}else{
			CutFixDeal cutFixDeal = null;
			if(w/h > width/height){
				cutFixDeal = new CutFixDeal(0,0,(int)(width/height*h),(int)h);
			}else if(w/h < width/height){
				cutFixDeal = new CutFixDeal(0,0,(int)w,(int)(height/width*w));
			}
			if(null != cutFixDeal){
				image = cutFixDeal.dealImage(image);
			}
			ZoomDeal zoomDeal = new ZoomDeal((int)width,(int)height);
			image = zoomDeal.dealImage(image);
		}
		
		return image;
	}

	public Image dealImage(String imageFile) throws FileNotFoundException,
			IOException {
		Image image = ImageTools.readImage(imageFile);
		return this.dealImage(image);
	}
	
}
