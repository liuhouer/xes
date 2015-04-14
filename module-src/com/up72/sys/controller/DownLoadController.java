package com.up72.sys.controller;

import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.stringUtil;
import static com.up72.common.CommonUtils.paramUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fileDownload")
public class DownLoadController {
	/**
	 * 文件下载
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commonDownload")
	public String commonDownload(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		// 下载设置
		response.setContentType("application/x-msdownload");
		String noSourcePage = "/commonError";
		model.addAttribute("message", "您下载的文件可能已被删除！");
		String fileName = paramUtils.getAttribute(request, "fileName");
		String filePath = paramUtils.getAttribute(request, "filePath");
		String realPath=stringUtil.parseToPath(filePath);
		// 资源是否存在
		if(!fileUtil.exists(realPath)){
			return noSourcePage;
		}
		InputStream is =null;
		OutputStream os =null;
		try{
		fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName );
		
		 is = new FileInputStream(new File(realPath));
		 os = response.getOutputStream();
		
		int flag = 0;
		byte[] buff = new byte[1024*4];
		
		while((flag = is.read(buff, 0, buff.length))!=-1){
			os.write(buff, 0, flag);
		}
		os.flush();
		is.close();
		os.close();
		fileUtil.deleteFile(realPath);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
}