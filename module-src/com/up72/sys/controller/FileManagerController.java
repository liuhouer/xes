package com.up72.sys.controller;

import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up72.base.BaseRestSpringController;
import com.up72.common.CommonConstants;
import com.up72.common.image.CutInfo;
import com.up72.framework.page.Page;
import com.up72.sys.SysConstants;
import com.up72.sys.model.FileInfo;
import com.up72.sys.model.WaterInfo;
import com.up72.sys.service.FileManager;
import com.up72.sys.vo.query.RegionQuery;
import com.up72.util.ZipUtil;
/**
 * 好友数据提取跳转
 * 
 * @author wqtan
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/tools/fileManager")
public class FileManagerController extends BaseRestSpringController<Object,java.lang.Long>{
	
	@Autowired
	private FileManager fileManager;
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	/** 列表  
	 * @author tlliu
	 * update wgf 2012-12-13
	 */
	@RequestMapping
	@SuppressWarnings({ "unchecked" })
	public String index(ModelMap model,RegionQuery query,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String filePath = paramUtils.getParameter(request, "dir", "");
		int sortType = paramUtils.getIntParameter(request, "sortType", SysConstants.FILE_SORT_TYPE_MODIFYTIME);
		int sortRule = paramUtils.getIntParameter(request, "sortRule", SysConstants.FILE_SORT_RULE_DESC);
		
		// 获得排序后的文件
		List<FileInfo> fileList = fileManager.listFiles(filePath,sortType,sortRule);
		
		// 获得文件夹
		List<FileInfo> filePathList = fileManager.listFilePaths(filePath);
		
		//过滤目录
		// TODO: 对不允许访问的文件进行过滤
		Set<String> set = new HashSet<String>();
		set.add("/WEB-INF");
		set.add("/pages/admin");
		filePathList = fileManager.filterFilePath(filePathList, set);
		
		model.addAttribute("filePathList", filePathList);
		
		//根据文件和文件夹数目分页
		int range = paramUtils.getIntParameter(request, "pageSize",	DEFAULT_RANGE);
		int pageNumber = paramUtils.getIntParameter(request, "pageNumber", 1);
		int start = (pageNumber-1) * range;
		int total = fileList.size()+filePathList.size();
		List<FileInfo> allfile = filePathList;
		allfile.addAll(fileList);
		
		Page page = new Page(pageNumber, range, total, allfile.subList(start, Math.min(total, start+range)));
		model.put("page", page);
		model.put("filePath", filePath);
		model.put("sortType", sortType);
		model.put("sortRule", sortRule);
		
		Map<String,String> pathMap= new LinkedHashMap<String,String>();
		int fromIndex = 1;
		String filePathTemp = filePath;
		if(!filePathTemp.endsWith("/")){
			filePathTemp+="/";
		}
		while (filePathTemp.indexOf("/",fromIndex) != -1){
			fromIndex = filePathTemp.indexOf("/",fromIndex);
			pathMap.put(filePathTemp.substring(0,fromIndex),filePathTemp.substring(filePathTemp.lastIndexOf("/",fromIndex-1)+1,fromIndex));
			fromIndex++;
		} 
		model.put("pathMap", pathMap);
		
		// 保存当前路径的上层路径
		if(null!=filePath && !filePath.trim().equals("")){
			if(filePath.endsWith("/")){
				filePath = filePath.substring(0,filePath.length()-1);
			}
			filePath = filePath.substring(0,filePath.lastIndexOf('/'));
			model.addAttribute("dir",filePath);
		}
		return "/admin/tools/fileManager/index";
	}
	@RequestMapping("/tree")
	public String tree(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		
		return "/admin/tools/fileManager/tree";
	}
	/**
	 * 文件树json
	 * @author wgf
	 */
	@RequestMapping("/catTree")
	public String catTree(ModelMap model, HttpServletRequest request,HttpServletResponse response) {
		String filePath = paramUtils.getParameter(request, "root", "");
		if("source".equals(filePath)){
			filePath = "";
		}
		// 获得文件夹
		List<FileInfo> filePathList = fileManager.listFilePaths(filePath);
		
		//过滤目录
		// TODO: 对不允许访问的文件进行过滤
		Set<String> set = new HashSet<String>();
		set.add("/WEB-INF");
		set.add("/pages/admin");
		filePathList = fileManager.filterFilePath(filePathList, set);
		
		model.addAttribute("filePathList", filePathList);
		return "/admin/tools/fileManager/fileTreeJSON";
	}
	/**
	 * 是否文件
	 * @author wgf
	 * @throws IOException 
	 */
	@RequestMapping("/isFile")
	@ResponseBody
	public String isFile(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		String filePath = paramUtils.getParameter(request, "filePath", "");
		String realPath = stringUtil.parseToPath(CommonConstants.ROOTPATH +"/"+ filePath);
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		String[] allowedSuffix ={"txt","html","htm","js"};//可编辑类型
		File file = new File(realPath);
		if(file.isDirectory()){
			fileInfo.put("message", "isDirectory");
		}else if(file.isFile()){
			fileInfo.put("message", "isFile");
			fileInfo.put("endWith", fileUtil.getExtension(realPath));
			fileInfo.put("isImage", fileUtil.isImage(filePath));
			fileInfo.put("isEdite", fileManager.isEdite(filePath,allowedSuffix));
		}else{
			fileInfo.put("message", "unknown");
		}
		return jsonUtil.object2Json(fileInfo);
	}
	/** 
	 * 获得指定路径下的指定类型的文件
	 */
	@RequestMapping(value="/{type}")
	public String list(ModelMap model,@PathVariable String type,
			HttpServletRequest request,HttpServletResponse response) {
		String filePath = paramUtils.getParameter(request, "dir", "");
		
		// 获得文件
		List<FileInfo> fileList = fileManager.listFiles(filePath, type);
		
		model.addAttribute("fileList", fileList);
		// 获得文件夹
		List<FileInfo> filePathList = fileManager.listFilePaths(filePath);
		model.addAttribute("filePathList", filePathList);
		
		model.addAttribute("type",type);
		// 保存当前路径的上层路径
		if(null!=filePath && !filePath.trim().equals("")){
			if(filePath.endsWith("/")){
				filePath = filePath.substring(0,filePath.length()-1);
			}
			filePath = filePath.substring(0,filePath.lastIndexOf('/'));
			model.addAttribute("dir",filePath);
		}
		
		// 跳转页面，如果是img则跳转到list_img.jsp，如果是其他文件则跳转到list_text
		String result = null;
		if(SysConstants.FILE_TYPE_IMG.equals(type.trim().toLowerCase())){
			result = "/admin/tools/fileManager/list_img";
		}else {
			result = "/admin/tools/fileManager/list_text";
		}
		return result;
	}
	
	
	
	/**
	 * 删除文件不可删除目录
	 * @author tlliu
	 * 
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(ModelMap model,@RequestParam("file") String file,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean result = false;
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		if(null != file && !file.trim().equals("")){
			String path = stringUtil.parseToPath(CommonConstants.ROOTPATH+"/"+file);
			File f = new File(path);
			if(f.exists()){
				if(!f.isDirectory()){
					result = fileUtil.deleteFile(f);
				}else{
					result = fileUtil.deleteFile(f);
					if(result){
						fileInfo.put("state", 2);
					}
				}
			}else{
				fileInfo.put("state", 1);
			}
		}else{
			fileInfo.put("state", 0);
		}
		fileInfo.put("success", result);
		return jsonUtil.object2Json(fileInfo);
	}
	
	/**
	 * 删除多个文件
	 */
	@RequestMapping(value="/deleteFileList")
	@ResponseBody
	public String deleteFileList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int successFile = 0;
		int errorFile = 0;
		int successDir = 0;
		int errorDir = 0;
		
		String[] filePath = request.getParameterValues("items");
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		if(filePath!=null && filePath.length>0){
			for (int i = 0; i < filePath.length; i++) {
				String filePathStr = filePath[i];
				if(null != filePathStr && !filePathStr.trim().equals("")){
					File f = new File(stringUtil.parseToPath(CommonConstants.ROOTPATH+"/"+filePathStr));
					if(f.exists()){
						if(f.isDirectory()){
							if(fileUtil.deleteFile(f)){
								successDir++;
							}else{
								errorDir++;
							}
						}else{
							if(fileUtil.deleteFile(f)){
								successFile++;
							}else{
								errorFile++;
							}
						}
					}
				}
			}
		}
		fileInfo.put("successFile", successFile);
		fileInfo.put("errorFile", errorFile);
		fileInfo.put("successDir", successDir);
		fileInfo.put("errorDir", errorDir);
		return jsonUtil.object2Json(fileInfo);
	}
	
	/**
	 * 文件改名
	 * @author tlliu
	 */
	@RequestMapping(value="/edit")
	public String edit(ModelMap model,@RequestParam("file") String file,
			HttpServletRequest request,HttpServletResponse response){
			if(fileUtil.exists(CommonConstants.ROOTPATH+"/"+file)){
				model.put("filePath",file);
				model.put("fileAllName",file.substring(file.lastIndexOf("/")+1));
				if(fileUtil.isFirectory(CommonConstants.ROOTPATH+"/"+file)){
					model.put("fileName",file.substring(file.lastIndexOf("/")+1));
					model.put("fileExt","");
				}else{
					model.put("fileName",file.substring(file.lastIndexOf("/")+1,file.lastIndexOf(".")));
					model.put("fileExt",file.substring(file.lastIndexOf(".")));
				}
			}
		return "/admin/tools/fileManager/tab_edit";
	}
	
	/**
	 * 创建文件夹
	 * @author tlliu
	 */
	@RequestMapping(value="/create")
	public String create(ModelMap model,@RequestParam("filePath") String file,
			HttpServletRequest request,HttpServletResponse response){
			model.put("filePath",file);
		return "/admin/tools/fileManager/tab_new";
	}
	/**
	 * 创建文件
	 * @author tlliu
	 */
	@RequestMapping(value="/createFile")
	public String createFile(ModelMap model,@RequestParam("filePath") String file,
			HttpServletRequest request,HttpServletResponse response){
		model.put("filePath",file);
		return "/admin/tools/fileManager/createFile";
	}
	
	/**
	 * 创建文件夹操作
	 * @author tlliu
	 */
	@RequestMapping(value="/doCreate")
	@ResponseBody
	public String create(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		String filePath = paramUtils.getParameter(request, "filePath", "").trim();
		String fileName = paramUtils.getParameter(request, "fileName", "").trim();
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		if(filePath.equals("") || !filePath.startsWith("/")){
			filePath = "/"+filePath;
		}
		int state = 0;
		if(fileUtil.isFirectory(CommonConstants.ROOTPATH+"/"+filePath)){
			if(!fileName.matches("[\u4E00-\u9FA5\uFF00-\uFFFF]+") && !fileName.matches("./\\#%&*|:<>\"?") && !fileName.equals("")){
				File file = new File(CommonConstants.ROOTPATH+"/"+filePath+"/"+fileName);
				if(!file.isDirectory()){
					if(file.mkdir()) state = 1;
				}else state = 2;
			}else state = 4;
		}else state = 3;
		fileInfo.put("state", state);
		return jsonUtil.object2Json(fileInfo);
	}
	/**
	 * 创建文件操作
	 * @author tlliu
	 * 
	 * update wgf
	 */
	@RequestMapping(value="/doCreateFile")
	@ResponseBody
	public String doCreateFile(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		String filePath = paramUtils.getParameter(request, "filePath", "").trim();
		String fileName = paramUtils.getParameter(request, "fileName", "").trim();
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		if(filePath.equals("") || !filePath.startsWith("/")){
			filePath = "/"+filePath;
		}
		int state = 0;
		if(fileUtil.isFirectory(CommonConstants.ROOTPATH+"/"+filePath)){
			if(!fileName.matches("[\u4E00-\u9FA5\uFF00-\uFFFF]+") && !fileName.matches("./\\#%&*|:<>\"?") && !fileName.equals("")){
				File file = new File(CommonConstants.ROOTPATH+"/"+filePath+"/"+fileName);
				if(!file.isDirectory()){
					if(file.createNewFile()) state = 1;
				}else state = 2;
			}else state = 4;
		}else state = 3;
		fileInfo.put("state", state);
		return jsonUtil.object2Json(fileInfo);
	}
	
	
	/** 
	 * 文件改名操作
	 * @author tlliu
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEdit")
	@ResponseBody
	public String doEdit(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String filePath = paramUtils.getParameter(request, "filePath", "").trim();
		String fileName = paramUtils.getParameter(request, "fileName", "").trim();
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		if(!filePath.equals("") && !fileName.equals("") && !fileName.matches("./\\#%&*|:<>\"?")){
			String newFileName = filePath.substring(0,filePath.lastIndexOf("/")+1)+fileName;
			if(!fileName.matches("[\u4E00-\u9FA5\uFF00-\uFFFF]+") && !fileUtil.isFirectory(CommonConstants.ROOTPATH+"/"+filePath)){
				newFileName+=filePath.substring(filePath.lastIndexOf("."));
			}
			Integer state =  fileUtil.renameFile(CommonConstants.ROOTPATH+"/"+filePath, CommonConstants.ROOTPATH+"/"+newFileName);
			fileInfo.put("state",state);
		}else{
			fileInfo.put("state",4);
		}
		return jsonUtil.object2Json(fileInfo);
	}
	
	/**
	 * 文件上传
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/upload")
	public String uploadFile(ModelMap model,HttpServletRequest request,
			HttpServletResponse response){
		String uploadFolder = paramUtils.getParameter(request, "filePath", "").trim();
		if(null != uploadFolder	&& !uploadFolder.trim().equals("") && !uploadFolder.startsWith("/")){
			uploadFolder = "/"+uploadFolder;
		}
		uploadFolder = uploadFolder.replace('\\','/');
		uploadFolder = uploadFolder.replaceAll("/{2,}","/");
		try {
			model.put("uploadFolder",uploadFolder);
		} catch (Exception e) {
		}

		model.put("fileExt",SysConstants.UPLOAD_FILE_TYPE);
		// 是否自动上传
		model.put("autoUpload","true");
		
		// 允许上传的文件大小最大 40	M
		model.put("sizeLimit",41943040);
		// 允许上传的最大队列数
		model.put("queueLimit",3);
		
		model.put("url", "/admin/tools/file/uploadFileToPath");
		
		model.put("isSave",0);
		return "/admin/tools/uploadFile/upload";
	}
	
	/**
	 * 缩放图片
	 * @throws Exception 
	 */
	@RequestMapping(value="/zoom")
	@ResponseBody
	public String zoom(ModelMap model,@RequestParam("file") String file,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(null != file && !file.trim().equals("")){
			// 获得缩放方式，1为按比例，3为按最大值
			int pattern = paramUtils.getIntParameter(request, "pattern", 0);
			// 获得缩放的大小值
			int value = paramUtils.getIntParameter(request, "value", 0);
			// 是否覆盖原有文件
			int isCover = paramUtils.getIntParameter(request, "isCover", 0);
			// 缩放图片
			fileManager.zoomImage(pattern, value, isCover, file);
		}
		return "";
	}
	
	/**
	 * 裁剪处理
	 */
	@RequestMapping(value="/cut")
	@ResponseBody
	public String cut(ModelMap model,@RequestParam("file") String file,
			CutInfo cutInfo,HttpServletRequest request,HttpServletResponse response){
		if(null != file && !file.trim().equals("")){
			String cutImage = fileManager.cutImage(cutInfo, file);
			System.out.println(cutImage);
		}
		return "";
	}
	
	/**
	 * 文件编辑
	 * @author tlliu
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/editFile")
	public String editText(HttpServletRequest request, ModelMap model)
			throws IOException {
		String filePath = paramUtils.getParameter(request, "filePath");
		File file = new File(CommonConstants.ROOTPATH+filePath);
		String source = "";
		if(file.exists() && file.isFile()){
			String charset = fileUtil.getCharset(file);
			source = fileUtil.readFileByLines(file,charset);
//			source = new String(source.getBytes("utf-8"),charset);
			model.addAttribute("charset", charset);
			
		}
		model.addAttribute("source", source);
		model.addAttribute("filePath", filePath);
		model.addAttribute("fileName", filePath.substring(filePath.lastIndexOf('/') + 1));
		String[] allowedSuffix ={"txt","html","htm","js"};//可编辑类型
		model.addAttribute("isEdite", fileManager.isEdite(filePath, allowedSuffix));
		return "/admin/tools/fileManager/editFile";
	}
	
	/**
	 * 保存编辑文件
	 * @author tlliu
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/doUpdateFile")
	@ResponseBody
	public String doUpdateFile(HttpServletRequest request, ModelMap model)
			throws IOException {
		String filePath = paramUtils.getParameter(request, "filePath");
		String source = paramUtils.getParameter(request, "source");
		String charset = paramUtils.getParameter(request, "charset","UTF-8");
		if(!SysConstants.FILE_CHARSET_GBK.equals(charset)){
			charset = SysConstants.FILE_CHARSET_UTF8;
		}
		Map<String,Object> fileInfo = new HashMap<String,Object>();
		if(fileUtil.saveFileByCharset(CommonConstants.ROOTPATH+filePath, source,charset)){
			fileInfo.put("success", true);
		}else{
			fileInfo.put("success", false);
		}
		return jsonUtil.object2Json(fileInfo);
	}
	
	/**
	 * 加水印
	 */
	@RequestMapping(value="/water")
	public String water(ModelMap model,@RequestParam("file") String file,
			@RequestParam("waterInfo") WaterInfo waterInfo,
			HttpServletRequest request,HttpServletResponse response){
		//获得字体的样式map
		Map<String,Object> fontParam = null;
		if(waterInfo.getPattern() == SysConstants.IMAGE_WATER_WORD){
			fontParam = this.getFontParam(request);
			waterInfo.setFontParam(fontParam);
		}
		
		//为图片加水印
		fileManager.water(file,waterInfo);
		
		return "";
	}
	
	/*
	 * 获得字体的样式map
	 * color r|g|b
	 * style Font.style的数组   
	 * font Font.font
	 */
	protected Map<String,Object> getFontParam(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		// 文字颜色
		result.put("color", paramUtils.getParameter(request, "color", "0|0|0"));
		// 文字大小
		result.put("size", paramUtils.getIntParameter(request,"size",14));
		// 字体样式，普通，粗体，斜体
		result.put("style", paramUtils.getIntParameters(request,"style",0));
		// 文字字体 宋体，楷体...
		result.put("font", paramUtils.getParameter(request,"font","宋体"));
		return result;
	}
	
	/**
	 * 文件上传到指定目录目录
	 */
	@RequestMapping(value="/uploadFileToPath")
	@ResponseBody
	@SuppressWarnings( { "unchecked" })
	public String doUploadFileToPath(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userName = paramUtils.getParameter(request, "userName", "");
		int isSave = paramUtils.getIntParameter(request, "isSave", 0);
		if(isSave <= 0){
			isSave = 0;
		}else{
			isSave = 1;
		}
		Map<String,Object> map = fileManager.uploadFileToPath(request, userName,isSave);
		HashMap jsonMap = new HashMap();
		jsonMap.putAll(map);
		String result = jsonUtil.object2Json(jsonMap);
		return result;
	}
	
	/**
	 * 解压zip文件
	 */
	@RequestMapping(value="/unzipFile")
	@ResponseBody
	@SuppressWarnings( { "unchecked" })
	public String unzipFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String zipFile = paramUtils.getParameter(request, "zipFile", "");
		boolean state = false;
		if(!"".equals(zipFile)){
			ZipUtil zu = new ZipUtil();
			try {
				zu.unzip(stringUtil.parseToPath(CommonConstants.ROOTPATH+zipFile));
				state = true;
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		HashMap jsonMap = new HashMap();
		jsonMap.put("state", state);
		String result = jsonUtil.object2Json(jsonMap);
		return result;
	}
	
	
}
