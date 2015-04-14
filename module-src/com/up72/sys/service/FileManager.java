package com.up72.sys.service;

import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.jimi.core.JimiException;
import com.up72.common.CommonConstants;
import com.up72.common.image.CutInfo;
import com.up72.common.image.ImageDealTool;
import com.up72.common.image.ImageTools;
import com.up72.common.image.deal.WaterImgDeal;
import com.up72.common.image.deal.WaterWordDeal;
import com.up72.common.image.deal.WaterWordStyle;
import com.up72.common.image.deal.ZoomMaxSideDeal;
import com.up72.common.image.deal.ZoomScaleDeal;
import com.up72.exception.UtilException;
import com.up72.sys.SysConstants;
import com.up72.sys.model.FileInfo;
import com.up72.sys.model.WaterInfo;

@Service
@Transactional
public class FileManager {

	/**
	 * 获得指定路径下所有文件类型的文件信息列表
	 * @param filePath 路径
	 * @param 排序规则
	 * @return List<FileInfo>
	 */
	@Transactional(readOnly=true)
	public List<FileInfo> listFiles(String filePath,int sortType,int sortRule){
		//获得文件路径
		filePath = parseFilePath(filePath);
		List<FileInfo> result = null;
		/*获得指定条件的文件列表*/
		File[] files = fileUtil.listFiles(filePath,"");
		
		files = filesSort(files, sortType,sortRule);
		/*转换成指定的文件信息列表*/
		result = this.getFileInfo(files);
		return result;
	}
	
	/**
	 * 获得指定路径下指定文件类型的文件信息列表
	 * 
	 * @param filePath 路径
	 * @param ext 过滤扩展名（.xx1,.xx2...）
	 * @return List<FileInfo>
	 */
	@Transactional(readOnly=true)
	public List<FileInfo> listFiles(String filePath, String type){
		//获得文件路径
		filePath = parseFilePath(filePath);
		
		List<FileInfo> result = null;
		if(null!=type && !type.trim().equals("")){
			type = type.trim().toLowerCase();
			/*获得文件的过滤后缀*/
			String ext = null;
			if(SysConstants.FILE_TYPE_CSS.equals(type)){
				ext = SysConstants.FILE_EXT_CSS;
			}else if(SysConstants.FILE_TYPE_IMG.equals(type)){
				ext = SysConstants.FILE_EXT_IMG;
			}else if(SysConstants.FILE_TYPE_SCRIPT.equals(type)){
				ext = SysConstants.FILE_EXT_SCRIPT;
			}else if(SysConstants.FILE_TYPE_ACTION.equals(type)){
				ext = SysConstants.FILE_EXT_ACTION;
			}else{
				ext = type;
			}
			/*获得指定条件的文件列表*/
			File[] files = fileUtil.listFiles(filePath, ext);
			/*转换成指定的文件信息列表*/
			result = this.getFileInfo(files);
		}
		
		return result;
	}
	
	/**
	 * 获得指定路径下的所有文件夹
	 * 
	 * @param filePath 路径
	 * @return List<FileInfo>
	 */
	@Transactional(readOnly=true)
	public List<FileInfo> listFilePaths(String filePath){
		//获得文件路径
		filePath = parseFilePath(filePath);
		
		List<FileInfo> result = null;
		/*获得指定条件的文件列表*/
		File[] files = fileUtil.listFilePaths(filePath);
		/*转换成指定的文件信息列表*/
		result = this.getFileInfo(files);
		return result;
	}
	
	/**
	 * 过滤指定文件目录
	 */
	@Transactional(readOnly=true)
	public List<FileInfo> filterFilePath(List<FileInfo> files,Set<String> filePathName){
		for (int i = 0; i < files.size(); i++) {
			FileInfo file = files.get(i);
			if(file.getType()==2){
				if(filePathName.contains(file.getFilePath())){
					files.remove(i);
					i--;
				}
			}
		}
		return files;
	}
	
	/**
	 * 过滤指定文件目录
	 */
	@Transactional(readOnly=true)
	public String[] filterFilePath(String[] files,Set<String> filePathName){
		for (int i = 0; i < files.length; i++) {
			String path = files[i];
			if(filePathName.contains(path)){
				files[i] = null;
			}
		}
		return files;
	}
	/**
	 * 文件是否为可编辑类型
	 * @author wgf
	 * @param filePath 文件路径
	 * @param type 可编辑类型数组
	 * @return
	 */
	public boolean isEdite(String filePath,String[] type){
		boolean flag = false;
		String suffix = "";
		if (null != filePath && !"".equals(filePath)) {
			suffix = fileUtil.getExtension(filePath);//文件后缀
		}
		if (null != type && type.length > 0) {
			for (int i = 0; i < type.length; i++) {
				String allowedSuffix = type[i];
				if (suffix.equals(allowedSuffix)) {
					flag = true;
				}
			}
		}
		return flag;
		
	}
	/**
	 * 文件排序方法
	 * @param files
	 * @param sortType 排序类型
	 * @param sortTule 排序规则 true是正序
	 * @return
	 */
	public File[] filesSort(File[] files,int sortType,int sortTule){
		if(files !=null && files.length>1){
			for (int i = 0; i < files.length-1; i++) {
				for (int j = i+1; j < files.length; j++) {
					if(sortTule == 1){
						if(compare(files[i], files[j], sortType)>0){
							File tempFile = files[i];
							files[i] = files[j];
							files[j] = tempFile;
						}
					}else{
						if(compare(files[i], files[j], sortType)<0){
							File tempFile = files[i];
							files[i] = files[j];
							files[j] = tempFile;
						}
					}
				}
			}
		}
		return files;
		
	}
	
	public int compare(Object o1, Object o2,int mode){
	      File f1 = (File)o1;
	      File f2 = (File)o2;
	      if (f1.isDirectory()){
	        if (f2.isDirectory()){
	          switch(mode){
	            case 1:return f1.getAbsolutePath().toUpperCase().compareTo(f2.getAbsolutePath().toUpperCase());
	            case 2:return new Long(f1.length()).compareTo(new Long(f2.length()));
	            case 3:return new Long(f1.lastModified()).compareTo(new Long(f2.lastModified()));
	            default:return 1;
	          }
	        }
	        else return -1;
	      }
	      else if (f2.isDirectory()) return 1;
	      else{
	          switch(mode){
	            case 1:return f1.getAbsolutePath().toUpperCase().compareTo(f2.getAbsolutePath().toUpperCase());
	            case 2:return new Long(f1.length()).compareTo(new Long(f2.length()));
	            case 3:return new Long(f1.lastModified()).compareTo(new Long(f2.lastModified()));
	            default:return 1;
	          }
	      }
	}

	protected String parseFilePath(String filePath){
		String result = null;
		if(null == filePath || filePath.trim().equals("")){
			result = CommonConstants.ROOTPATH;
		}else{
			result = stringUtil.parseToPath(CommonConstants.ROOTPATH +"/"+ filePath);
		}
		return result;
	}
	
	/*转换成指定的文件信息列表*/
	@Transactional(readOnly=true)
	protected List<FileInfo> getFileInfo(File[] files){
		List<FileInfo> result = new ArrayList<FileInfo>();
		
		if(null!= files && files.length > 0){
			for(int i=0;i<files.length;i++){
				FileInfo fileInfo = new FileInfo();
				fileInfo.setName(files[i].getName());
				fileInfo.setEditTime(new Date(files[i].lastModified()));
				String filePath = files[i].getPath();
				filePath = filePath.substring(filePath.indexOf(CommonConstants.ROOTPATH)+CommonConstants.ROOTPATH.length());
				filePath = filePath.replace('\\', '/');
				fileInfo.setFilePath(filePath);
				if(files[i].isFile()){
					fileInfo.setSize(fileUtil.convertFileSize(files[i]));
					fileInfo.setType(SysConstants.FILE_TYPE_FILE);
				}else{
					fileInfo.setType(SysConstants.FILE_TYPE_PATH);
					File[] filelist = files[i].listFiles();
					int count = filelist.length;
					for (int j = 0; j < filelist.length; j++) {
						if(filelist[j].isHidden()){
							count--;
						}
					}
					fileInfo.setFileCount(count);
				}
				File[] fs = files[i].listFiles(new FileFilter(){
					public boolean accept(File pathname) {
						if(pathname.isDirectory() && !pathname.isHidden()){
							return true;
						}
						return false;
					}
				});
				fileInfo.setChildFileCount(null==fs?0:fs.length);
				result.add(fileInfo);
			}
		}
		
		return result;
	}
	
	/**
	 * 执行缩放处理
	 * @author wqtan
	 * @throws JimiException 
	 */
	public void zoomImage(int pattern,int value,int isCover,String file) throws JimiException{
		ImageDealTool idt = new ImageDealTool();
		if(pattern == SysConstants.IMAGE_ZOOM_SCALE){
			idt.regDeal("zoom", new ZoomScaleDeal((float) (value / (100+0.0))));
		}else if(pattern == SysConstants.IMAGE_ZOOM_MAXSIDE){
			idt.regDeal("zoom", new ZoomMaxSideDeal(value));
		}
		// 缩放处理
		file = CommonConstants.ROOTPATH+"/"+file;
		Image image = idt.deal(file);
		//保存图片
		if(isCover == 1){
			ImageTools.writeImage(file, image);
		}else{
			file += "_"+image.getWidth(null)+"x"+image.getHeight(null)+file.substring(file.lastIndexOf('.'));
			System.out.println(file);
			ImageTools.writeImage(file, image);
		}
	}
	
	/**
	 * 给图片打水印
	 * @author wqtan
	 * @return int 1为成功，2为找不到水印图片，3为IO异常
	 */
	public int water(String file,WaterInfo waterInfo){
		int result = 1;
		// 图片路径转换为相对web路径
		file = CommonConstants.ROOTPATH+"/"+file;
		
		// 加图片水印
		if(waterInfo.getPattern() == SysConstants.IMAGE_WATER_IMAGE){
			waterInfo.setValue(CommonConstants.ROOTPATH+"/"+waterInfo.getValue());
			result = waterByImage(file,waterInfo);
		}
		// 加文字水印
		else if(waterInfo.getPattern() == SysConstants.IMAGE_WATER_WORD){
			result = waterByWord(file, waterInfo);
		}
		return result;
	}
	
	/*
	 * 加图片水印
	 */
	private int waterByImage(String file ,WaterInfo waterInfo){
		int result = 1;

		float op = (float) (waterInfo.getOpacity() / 100.0);
		
		ImageDealTool idt = new ImageDealTool();
		try {
			idt.regDeal("water",new WaterImgDeal(waterInfo.getLocation(),waterInfo.getLeft(),waterInfo.getTop(),op,waterInfo.getValue()));
			Image image = idt.deal(file);
			file += "_water"+waterInfo.getLocation()+file.substring(file.lastIndexOf('.'));
			ImageTools.writeImage(file, image);
		} catch (FileNotFoundException e) {
			result = 2;
		} catch (IOException e) {
			result = 3;
		}catch (UtilException e) {
			result = 3;
		}
		
		return result;
	}
	
	/*
	 * 加文字水印
	 */
	private int waterByWord(String file,WaterInfo waterInfo){
		int result = 1;
		
		ImageDealTool idt = new ImageDealTool();
		// 文字样式
		WaterWordStyle style = getWaterWordStyle(waterInfo.getFontParam(),waterInfo.getValue());
		
		idt.regDeal("water",new WaterWordDeal(waterInfo.getLocation(),waterInfo.getLeft(),waterInfo.getTop(),waterInfo.getOpacity()
				,waterInfo.getValue(),style));
		Image image = idt.deal(file);
		file += "_water"+waterInfo.getLocation()+file.substring(file.lastIndexOf('.'));
		try {
			ImageTools.writeImage(file, image);
		} catch (UtilException e) {
			return 3;		
		}
		return result;
	}
	
	/*
	 * 解析文字样式map成WaterWordStyle对象
	 */
	private WaterWordStyle getWaterWordStyle(Map<String,Object> fontParam,String value){
		WaterWordStyle result = new WaterWordStyle();
		
		int size = (Integer) fontParam.get("size");
		int[] styleArray = (int[]) fontParam.get("style");
		String colorString = fontParam.get("color").toString();
		String font = fontParam.get("font").toString();
		
		int style = getStyle(styleArray);
		
		Font f = new Font(font,style,size);
		result.setFont(f);
		Color color = getColor(colorString);
		result.setFontColor(color);
		
		result.setWidth(size + size * value.length());
		result.setHeight((int)(1.5 * size));
		
		return result;
	}
	
	private int getStyle(int[] styleArray){
		int result = Font.PLAIN;
		if(null != styleArray 
				&& styleArray.length > 0 
				&& styleArray[0] != 0){
			for(int i=0;i<styleArray.length;i++){
				result = result | styleArray[i];
			}
		}
		return result;
	}
	
	private Color getColor(String colorString){
		String[] rgb = colorString.split("\\|");
		int r = 0;
		int g = 0;
		int b = 0;
		if(null != rgb && rgb.length > 2){
			r = stringUtil.parseInt(rgb[0]);
			g = stringUtil.parseInt(rgb[1]);
			b = stringUtil.parseInt(rgb[2]);
		}
		return new Color(r,g,b);
	}
	
	/**
	 * 裁剪图片
	 * @author wqtan
	 * @return String saveFile
	 */
	public String cutImage(CutInfo cutInfo,String file){
		file = CommonConstants.ROOTPATH + file;
		String saveFile = getImageSaveName(file);
		
		ImageTools.cutImage(cutInfo, file, saveFile);
		
		saveFile = "/"+saveFile.substring( CommonConstants.ROOTPATH.length());
		
		return saveFile;
	}
	
	/*
	 * 获得图片的保存名称
	 */
	protected String getImageSaveName(String imageFile){
		StringBuffer result = new StringBuffer(CommonConstants.ROOTPATH);
		if(imageFile.startsWith("/")){
			imageFile = imageFile.substring(1);
		}
		result.append(imageFile);
		result.append("_cut");
		result.append(fileUtil.getSuffix(imageFile));
		
		return result.toString();
	}
	/**
	 * 保存文件到指定目录
	 * @param request
	 * @param userName
	 * @param isSave
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public Map<String, Object> uploadFileToPath(HttpServletRequest request,
			String userName, int isSave) {
		Map<String, Object> result = new HashMap<String, Object>();
		String folder = paramUtils.getParameter(request, "folder");
		try {
			folder = URLDecoder.decode(folder.replace("&#37", "%"),"utf-8");
		} catch (Exception e) {
		}
		String fileext = paramUtils.getParameter(request, "fileext");
		
		StringBuffer statuses = new StringBuffer("[");
		StringBuffer filePaths = new StringBuffer("[");
		StringBuffer fileNames = new StringBuffer("[");
		StringBuffer fileSizes = new StringBuffer("[");
		StringBuffer uploadFileNames = new StringBuffer("[");
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String fileName = it.next();
			MultipartFile file = multipartRequest.getFile(fileName);
			Map<String, Object> upFileResult = this.uploadFileToPath(file, userName,
					folder, fileext, isSave);
			statuses.append(upFileResult.get("status").toString() + ",");
			filePaths.append("'" + upFileResult.get("filePath").toString()
					+ "',");
			fileNames.append("'" + upFileResult.get("fileName").toString()
					+ "',");
			fileSizes.append("'" + upFileResult.get("fileSize").toString()
					+ "'");
			try {
				uploadFileNames.append("'"
						+  URLEncoder.encode(upFileResult.get("uploadFileName").toString(),"utf8") + "',");
			} catch (UnsupportedEncodingException e) {
				uploadFileNames.append("'"
						+ upFileResult.get("uploadFileName").toString() + "',");
			}
		}
		result.put("statuses",
				(statuses.substring(0, statuses.length() - 1) + "]"));
		result.put("fileSizes",
				(fileSizes.substring(0, fileSizes.length() - 1) + "]"));
		result.put("filePaths",
				(filePaths.substring(0, filePaths.length() - 1) + "]"));
		result.put("fileNames",
				(fileNames.substring(0, fileNames.length() - 1) + "]"));
		result.put("uploadFileNames", (uploadFileNames.substring(0,
				uploadFileNames.length() - 1) + "]"));
		return result;
	}
	
	/**
	 * 保存文件到指定目录
	 * @param request
	 * @param userName
	 * @param isSave
	 * @return
	 */
	public Map<String, Object> uploadFileToPath(MultipartFile file, String userName,
			String uploadPath, String extList, int isSave) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = 1;
		if (null != file) {
			String savePath = CommonConstants.ROOTPATH + "/" + uploadPath;
			File path = new File(savePath);
			if (!path.exists()) {
				path.mkdirs();
			}
			savePath = savePath.replace('\\', '/');
			savePath = savePath.replaceAll("/{2,}", "/");
			if (!savePath.endsWith("/")) {
				savePath = savePath + "/";
			}
			String fileName = file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf('.'));
			String name = fileName.substring(0,fileName.lastIndexOf('.')).replaceAll("([(]\\S*[)])+$", "").trim();
			int number = 0;
			String tempName = name;
			while(new File(savePath + tempName +ext).exists()){
				number++;
				tempName = name;
				tempName += " ("+number+")";
			}
			if(number!=0){
				fileName = tempName + ext;
			}
			try {
				uploadPath = uploadPath.startsWith("/") ? uploadPath : "/"
					+ uploadPath;
				uploadPath = uploadPath.endsWith("/") ? uploadPath
						: (uploadPath + "/");
				String fileSize = fileUtil.convertFileSize(file.getSize());
				FileOutputStream fos = new FileOutputStream(new File(savePath + fileName));
				fos.write(file.getBytes());
				fos.close();
				result.put("filePath", (uploadPath + fileName));
				result.put("fileName", fileName);
				result.put("fileSize", fileSize);
				result.put("uploadFileName", (fileName));
			} catch (Exception e) {
				status = 2;
			}
		}
		result.put("status", status);
		result.put("isSave", isSave);
		return result;
	}
	

}
