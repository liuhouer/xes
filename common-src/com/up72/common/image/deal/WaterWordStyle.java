package com.up72.common.image.deal;

import java.awt.Color;
import java.awt.Font;

/**
 * 文字水印文字样式
 * @author bh.iune
 *
 */
public class WaterWordStyle {
	private int width;
	private int height;
	private Color fontColor;
	private Font font;
	
	public WaterWordStyle(){
		this(0, 0, Color.BLACK,new Font("宋体",Font.PLAIN,14));
	}
	
	public WaterWordStyle(Color fontColor){
		this(0, 0,fontColor,new Font("宋体",Font.BOLD,14));
	}
	
	public WaterWordStyle(Font font){
		this(0, 0,Color.BLACK,font);
	}

	public WaterWordStyle(Color fontColor,Font font){
		this(0, 0,fontColor,font);
	}
	
	public WaterWordStyle(int width,int height,Color fontColor){
		this(width, height,fontColor,new Font("宋体",Font.BOLD,14));
	}
	
	public WaterWordStyle(int width,int height,Font font){
		this(width, height,Color.BLACK,font);
	}
	
	public WaterWordStyle(int width,int height,Color fontColor,Font font){
		this.width = width;
		this.height = height;
		this.fontColor = fontColor;
		this.font = font;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getFontColor() {
		return fontColor;
	}
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	
}
