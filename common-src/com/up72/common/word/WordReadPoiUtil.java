package com.up72.common.word;

import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import com.up72.common.CommonConstants;

public class WordReadPoiUtil {
	public static void main(String[] args) {
		 WordReadPoiUtil wpu = new WordReadPoiUtil();
	}

	/**
	 * 抽取word2003文件文本内容 (纯文本)
	 */
	public String extractWord2003Content(File document) {
		String text2003 = "";
		InputStream is;
		WordExtractor ex;
		try {
			is = new FileInputStream(document);
			ex = new WordExtractor(is);
			text2003 = ex.getText();
		} catch (OfficeXmlFileException e) {
			// if(e.toString().contains("The supplied data appears to be in the Office 2007+ XML")){
			// System.out.println("OfficeXmlFileException");
			// }
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text2003;
	}

	/**
	 * 抽取word2007+文件文本内容 (纯文本)
	 */
	public String extractWord2007Content(File document) {
		String contents = "";
		String wordDocxPath = document.toString();
		try {
			// 载入文档
			OPCPackage opcPackage = POIXMLDocument.openPackage(wordDocxPath);
			XWPFDocument xwpfd = new XWPFDocument(opcPackage);
			POIXMLTextExtractor ex = new XWPFWordExtractor(xwpfd);
			// 读取文字
			contents = ex.getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	public List<String> extractWord2003Image(File document,String imgPath) {
		List<String> imgList = new ArrayList<String>();
		// hwpfDocument是专门处理word的， 在poi中还有处理其他office文档的类
		HWPFDocument doc;
		try {
			doc = new HWPFDocument(new FileInputStream(document));
			// 看看此文档有多少个段落
			Range range = doc.getRange();

			// 得到word的数据流
			byte[] dataStream = doc.getDataStream();
			int numChar = range.numCharacterRuns();
			PicturesTable pTable = new PicturesTable(doc, dataStream, dataStream);
			for (int j = 0; j < numChar; ++j) {
				CharacterRun cRun = range.getCharacterRun(j);
				// 看看有没有图片
				boolean has = pTable.hasPicture(cRun);
//				System.out.println("hasPicture " + has);
				if (has) {
					Picture zhou = pTable.extractPicture(cRun, true);
					// 目录你就自己设了，像保存什么的格式都可以
					try {
						String newFile = UUID.randomUUID().toString().replace("-", "");
						zhou.writeImageContent(new FileOutputStream(imgPath + "/"+ newFile+ ".jpg"));
						imgList.add(imgPath + "/"+ newFile+ ".jpg");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return imgList;
	}

	/**
	 * 抽取文件中的图片，并保存
	 */
	public List<String> extractWord2007Image(File document,String imgPath) {
		List<String> imgList = new ArrayList<String>();
		try {
			String wordDocxPath = document.toString();
			// 载入文档
			OPCPackage opcPackage = POIXMLDocument.openPackage(wordDocxPath);
			XWPFDocument xwpfd = new XWPFDocument(opcPackage);
			// 建立图片文件目录
			File imgFile = new File(imgPath);
			if (!imgFile.exists()) {
				imgFile.mkdir();
			}
			// 获取所图片
			List piclist = xwpfd.getAllPictures();
			for (int j = 0; j < piclist.size(); j++) {
				XWPFPictureData pic = (XWPFPictureData) piclist.get(j);
				// 获取图片数据流
				byte[] picbyte = pic.getData();
				// 将图片写入本地文件
				String newFile = UUID.randomUUID().toString().replace("-", "");
				FileOutputStream fos = new FileOutputStream(imgPath + "/"+ newFile + ".jpg");
				fos.write(picbyte);
				imgList.add(imgPath + "/"+ newFile + ".jpg");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgList;
	}
}
