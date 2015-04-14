package com.up72.common.upload.controller;

import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.stringUtil;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.up72.common.CommonConstants;
import com.up72.common.image.ImageTools;
import com.up72.common.image.deal.ZoomDeal;
import com.up72.common.upload.UploadConstant;
import com.up72.exception.UtilException;
import com.up72.util.ImgErToFileUtil;
import com.xes.jtzs.JTZSConstants;

/**
 * 文件上传公用处理
 * 
 * @author bh.iune
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/common/upload")
public class UploadController {

	/**
	 * 文件上传处理
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(@RequestParam()
	MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();

		String jsonResult = "";

		Map<String, String> fileInfo = this.uploadFile(inputStream, fileName);

		fileInfo.put("fileSize", Long.toString(file.getSize()));

		String contentPath = request.getContextPath();
		contentPath = (null == contentPath || contentPath.trim().equals("")) ? "/"
				: contentPath;
		fileInfo.put("contextPath", contentPath);
		jsonResult = jsonUtil.object2Json(fileInfo);
		return jsonResult;
	}
	
	/**
	 * 文件上传处理
	 */
	@RequestMapping(value = "/upload2")
	@ResponseBody
	public String upload2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean rlt = false;
		String code = JTZSConstants.ErrorCode.gg500;
		String str = request.getParameter("file");
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != str){
			try {
				str = str.replace("<","").replace(">", "").replace(" ","");
				String type = request.getParameter("type");
				if(null ==type || "".equals(type)){
					type = "jpg";
				}
				String saveFile = UUID.randomUUID().toString().replace("-", "")+ "."+ type.toLowerCase();
				String webSavePath = stringUtil.parseToPath(UploadConstant.UPLOAD_SAVE_PATH + "/" + dateUtils.getStrDate());
				String realPath = stringUtil.parseToPath(CommonConstants.ROOTPATH + "/" + webSavePath);
				if(fileUtil.mkdirs(realPath)){
					String filePath = stringUtil.parseToPath(realPath + "/" + saveFile);
					ImgErToFileUtil.saveToImgByStr(str, realPath , saveFile);
					if(new File(filePath).isFile() && fileUtil.isImage(filePath)){
						map.put("imgPath", stringUtil.parseToPath("/" + webSavePath + "/" + saveFile));
						code = JTZSConstants.ErrorCode.gg000;
						rlt = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileInfo.put("data", map);
		fileInfo.put("rlt", rlt+"");
		fileInfo.put("msg", JTZSConstants.ErrorCode.getMag(code));
		fileInfo.put("code", code);
		String s = jsonUtil.object2Json(fileInfo);
		return s;
	}

	// 系统路径的获取放在sys源代码包中
	private String getSavePath(String saveFile) {
		String webSavePath = stringUtil.parseToPath(CommonConstants.ROOTPATH
				+ "/" + UploadConstant.UPLOAD_SAVE_PATH + "/"
				+ dateUtils.getStrDate());

		// 创建保存路径
		File file = new File(webSavePath);
		file.mkdirs();

		String result = stringUtil.parseToPath(webSavePath + "/" + saveFile);
		return result;
	}

	public Map<String, String> uploadFile(InputStream inputStream,
			String fileName) throws FileNotFoundException, IOException {
		// 获得当前时间的long值
		// 保存文件名
		String saveFile = UUID.randomUUID().toString().replace("-", "")
				+ fileName.substring(fileName.indexOf('.')).toLowerCase();
		// 保存文件路径
		String webSaveFile = getSavePath(saveFile);

		fileUtil.saveFile(inputStream, webSaveFile, 1024 * 4);

		Map<String, String> result = new HashMap<String, String>();
		// 图片缩放
		File file = new File(webSaveFile);
		if (null != file && file.isFile() && file.exists()
				&& fileUtil.isImage(webSaveFile)) {
			Image image = ImageTools.readImage(webSaveFile);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			if (width > CommonConstants.image.MAX_WIDTH) {
				String newFile = UUID.randomUUID().toString().replace("-", "")
						+ fileName.substring(fileName.indexOf('.'))
								.toLowerCase();
				String newWebSaveFile = getSavePath(newFile);

				int maxHeight = (int) ((CommonConstants.image.MAX_WIDTH + 0.0)
						/ (width + 0.0) * (height + 0.0));
				try {
					ZoomDeal deal = new ZoomDeal(
							CommonConstants.image.MAX_WIDTH, maxHeight);
					image = deal.dealImage(image);
					ImageTools.writeImage(newWebSaveFile, image);
					file.delete();
					webSaveFile = newWebSaveFile;
				} catch (UtilException e) {
				}
			} else if (file.length() > CommonConstants.image.UPLOAD_MAXSIZE) {
				String newFile = UUID.randomUUID().toString().replace("-", "")
						+ fileName.substring(fileName.indexOf('.'))
								.toLowerCase();
				webSaveFile = getSavePath(newFile);

				try {
					ImageTools.writeImage(webSaveFile, image);
					file.delete();
				} catch (UtilException e) {
				}
			}
			result.put("width", image.getWidth(null) + "");
			result.put("height", image.getHeight(null) + "");
		}

		String savePath = webSaveFile;
		if (webSaveFile.startsWith(CommonConstants.ROOTPATH)) {
			savePath = stringUtil.parseToPath("/"
					+ webSaveFile.substring(CommonConstants.ROOTPATH.length()));
		}
		result.put("saveFile", saveFile);
		result.put("savePath", savePath);
		result.put("fileName", java.net.URLEncoder.encode(fileName, "UTF-8"));
		result.put("uploadName", java.net.URLEncoder.encode(fileName, "UTF-8"));
		result.put("uploadTime", Long.toString(new Date().getTime()));
		return result;
	}

}
