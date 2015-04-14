package com.up72.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * zip文件解压缩
 * 注：zip中不能有中文目录或文件，否则会报错
 * @author tlliu
 * 
 */
public class ZipUtil {

	 public void unzip(String srcPath) throws Exception { 
		 File file = new File(srcPath);
		 unzip(srcPath, file.getParent());
	 }
	
	    /** 
	     * 解压缩 
	     *  
	     * @param srcFile 
	     * @param destFile 
	     * @throws Exception 
	     */  
	    public void unzip(String srcPath,String destDir) throws Exception {
	    	 BufferedInputStream bi; 
	    	  FileInputStream fi = new FileInputStream(srcPath);   
	          CheckedInputStream csumi = new CheckedInputStream(fi, new CRC32());   
	          ZipInputStream in2 = new ZipInputStream(csumi);   
	          bi = new BufferedInputStream(in2);   
	          java.util.zip.ZipEntry ze;//压缩文件条目   
	          //遍历压缩包中的文件条目   
	          while ((ze = in2.getNextEntry()) != null) {   
	              String entryName = ze.getName();   
	              if (ze.isDirectory()) {   
	                  File decompressDirFile = new File(destDir + "/" + entryName);   
	                  if (!decompressDirFile.exists()) {   
	                      decompressDirFile.mkdirs();   
	                  }   
	              } else {   
	                  BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(   
	                		  destDir + "/" + entryName));   
	                  byte[] buffer = new byte[1024];   
	                  int readCount = bi.read(buffer);   
	    
	                  while (readCount != -1) {   
	                      bos.write(buffer, 0, readCount);   
	                      readCount = bi.read(buffer);   
	                  }   
	                  bos.close();   
	              }   
	          }   
	          bi.close(); 
	  
	    }  
	   
	  
	/**
	 * 读取zip文件列表
	 * @param zipFilePath
	 * @throws IOException
	 * @author tlliu
	 */
	public List<String> readZipFileList(String zipFilePath) throws IOException {
		List<String> fileList = new ArrayList<String>();
		ZipFile zf = new ZipFile(zipFilePath);
		Enumeration e = zf.entries();
		while (e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry)e.nextElement();
			String entryName = ze2.getName();
			fileList.add(entryName);
		}
		zf.close();
		return fileList;
	}
	

	public static void main(String[] args) throws Exception {
		ZipUtil zu = new ZipUtil();
//		List<String> list = zu.readZipFileList("C:\\zip 中纬度  002.zip");
//		for (String str : list) {
//			System.out.println(str);
//		}
		zu.unzip("C:\\zip 中纬度  002.zip", "C:\\rrr\\");
//		zu.decompress("C:\\zip 中文  002.zip","C:\\rrr");
	}

}
