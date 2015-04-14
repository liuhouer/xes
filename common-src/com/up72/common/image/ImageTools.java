package com.up72.common.image;

import static com.up72.common.CommonUtils.fileUtil;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;
import com.sun.jimi.core.options.OptionException;
import com.sun.jimi.core.options.PNGOptions;
import com.up72.common.image.deal.CutFixDeal;
import com.up72.common.image.deal.ZoomMaxSideDeal;
import com.up72.exception.UtilException;

public final class ImageTools {
	
	
	/**
	 * 保存图片
	 * @param fileName
	 * @param image
	 * @throws JimiException
	 */
	public static String writeImage(String fileName,Image image)  {
		return writeImage(fileName, image, true);
	}
	
	/**
	 * 保存图片
	 * @param fileName 文件路径
	 * @param image 图片对象
	 * @param format 是否强制输出成jpg
	 * @return 
	 * @throws JimiException
	 */
	public static String writeImage(String fileName,Image image,boolean format)  {
		if(null == fileName){
			throw new UtilException("文件名为空");
		}
		String suffix = fileUtil.getSuffix(fileName);
		if(null!=suffix && suffix.toLowerCase().equals(".png") && format){
			writeToPNG(image, fileName);
		}else{
			writeToJPG(image, fileName, 75);
		}
		return fileName;
	}
	
	/**
	 * 从硬盘读取一张图片进入内存
	 * 
	 * @param fileName 文件路径
	 * @return 内存图片
	 */
	public static Image readImage(String fileName){
		File file = new File(fileName);
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}
	
	/**
	 * @param source
	 *            原图路径
	 * @param desc
	 *            转换后的存储路径
	 * @param quality
	 *            图片质量 0-100默认75
	 */
	public static void writeToJPG(String source, String desc, int quality) {
		if(quality < 0){
			quality = 75;
		}
		if(quality > 100){
			quality = 100;
		}
		if(null == desc || desc.trim().equals("")){
			desc = source+".jpg";
		}
		JPGOptions options = new JPGOptions();
		try {
			options.setQuality(quality);
		} catch (OptionException e) {
			throw new UtilException(e);
		}
		ImageProducer image = Jimi.getImageProducer(source);
		JimiWriter writer;
		try {
			writer = Jimi.createJimiWriter(desc);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
		try {
			writer.setSource(image);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
		writer.setOptions(options);
		try {
			writer.putImage(desc);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
	}
	
	/**
	 * @param source
	 * @param quality
	 *            图片质量 0-100默认75
	 */
	public static void writeToJPG(Image source, String desc, int quality){
		if(quality < 0){
			quality = 75;
		}
		if(quality > 100){
			quality = 100;
		}
		if(null == desc || desc.trim().equals("")){
			desc = source+".jpg";
		}
		JPGOptions options = new JPGOptions();
		try {
			options.setQuality(quality);
		} catch (OptionException e) {
			throw new UtilException(e);
		}
	
		JimiWriter writer = null;
		try {
			writer = Jimi.createJimiWriter(desc);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
		try {
			writer.setSource(source);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
		writer.setOptions(options);
		try {
			writer.putImage(desc);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
	}
	
	public static void writeToPNG(Image source, String desc){
		BufferedImage bufferedImage = new BufferedImage(
				source.getWidth(null),source.getHeight(null),
				BufferedImage.TYPE_USHORT_555_RGB);
		bufferedImage.getGraphics().drawImage(source, 0, 0, null);
		if(null == desc || desc.trim().equals("")){
			desc = source+".png";
		}
		
		PNGOptions options = new PNGOptions();
		try {
			options.setCompressionType(PNGOptions.COMPRESSION_FAST);
		} catch (OptionException e) {
			throw new UtilException(e);
		}
		
		JimiWriter writer = null;
		try {
			writer = Jimi.createJimiWriter(desc);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
		try {
			writer.setSource(bufferedImage);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
		writer.setOptions(options);
		try {
			writer.putImage(desc);
		} catch (JimiException e) {
			throw new UtilException(e);
		}
	}
	
	/**
	 * @param source
	 * @param quality
	 *            图片质量 0-100默认75
	 */
	public static void writeToJPG(InputStream inputStream, String desc, int quality) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(desc));

		int flag = 0;
		byte[] buff = new byte[4*1024];
		
		while((flag = inputStream.read(buff,0,buff.length))!=-1){
			fos.write(buff, 0, flag);
		}
		
		inputStream.close();
		fos.close();
	}
	

	/*
	 * 图片裁剪处理
	 */
	public static boolean cutImage(CutInfo cutInfo, 
			String imageFile,String saveFile) {
		boolean result = true;
		// 读取头像图片
		Image image = null;
		try {
			image = ImageTools.readImage(imageFile);
		} catch (UtilException e1) {
			result = false;
		}
		if(null != image){
			// 计算起始裁剪坐标
			Point point = getCutXY(cutInfo, image);
			// 计算裁剪大小，这里仅处理正方形
			Point cutPoint = getCutSize( cutInfo, image);
			
			// 创建图片处理工厂
			ImageDealTool idt = new ImageDealTool();
			// 注册裁剪处理方法
			idt.regDeal("cutdeal", new CutFixDeal(point.x,point.y,cutPoint.x,cutPoint.y));
			// 注册缩放处理算法
			int maxSize = 0;
			int zoomw = cutInfo.getZoomWidth();
			int zoomh = cutInfo.getZoomHeight();

			if(zoomw==0 && zoomh==0){
				zoomw = cutInfo.getCutWidth();
				zoomh = cutInfo.getCutHeight();
			}
			if(zoomw > zoomh){
				maxSize = zoomw;
			}else{
				maxSize = zoomh;
			}
			idt.regDeal("zoomdeal",new ZoomMaxSideDeal(maxSize));
			// 处理图片
			Image cutImage = idt.deal(image);
			
			// 保存图片
			try {
				ImageTools.writeImage(saveFile, cutImage, true);
			} catch (UtilException e) {
				result = false;
			}
		}
		return result;
	}
	
	/*
	 * 计算起始裁剪坐标
	 */
	protected static Point getCutXY(CutInfo cutInfo,Image image){
		int _x  = 
			new Long(Math.round(cutInfo.getCutX() * (0.0 + image.getWidth(null)) / cutInfo.getViewWidth())).intValue();
		int _y = 
			new Long(Math.round((cutInfo.getCutY() * (0.0 + image.getHeight(null)) / cutInfo.getViewHeight()))).intValue();
		return new Point(_x,_y);
	}
	
	/*
	 * 计算裁剪大小，这里仅处理正方形
	 */
	protected static Point getCutSize(CutInfo cutInfo,Image image){
		int _x = new Long(Math.round(cutInfo.getCutWidth() * (0.0+image.getWidth(null)) / cutInfo.getViewWidth())).intValue();
		int _y = new Long(Math.round(cutInfo.getCutHeight() * (0.0+image.getHeight(null)) / cutInfo.getViewHeight())).intValue();
		return new Point(_x,_y);
	}

	/**
	 * 获得图片的宽高
	 * @param imgFile
	 * @return int[] 0为宽 1为高
	 */
	public static int[] getImageWH(File imgFile){
		int[] result = new int[2];
		try {
			Image image = ImageIO.read(new FileInputStream(imgFile));
			result[0] = image.getWidth(null);
			result[1] = image.getHeight(null);
		} catch (FileNotFoundException e) {
			result = null;
		} catch (IOException e) {
			result = null;
		}
		return result;
	}
	
	public static void main(String[] args) {//剪切图片并且压缩
		CutInfo cutInfo = new CutInfo();
		String imgPath = "d:/2.jpg";
		int [] wh = getImageWH(new File(imgPath));
		cutInfo.setViewWidth(2000);
		cutInfo.setViewHeight(2000);
		cutInfo.setCutWidth(1000);
		cutInfo.setCutHeight(1000);
		cutInfo.setCutX(100);
		cutInfo.setCutY(100);
		
		String saveFile = "d:/5.jpg";
		
		cutImage(cutInfo, imgPath, saveFile);
	}
}
