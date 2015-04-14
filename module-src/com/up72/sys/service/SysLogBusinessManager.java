/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2012
 */

package com.up72.sys.service;

import static com.up72.common.CommonUtils.stringUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.up72.base.BaseManager;
import com.up72.base.EntityDao;
import com.up72.common.CommonConstants;
import com.up72.common.excel.ColumnConfig;
import com.up72.common.excel.ExcelExportPoiUtil;
import com.up72.common.excel.ImportExportXML;
import com.up72.framework.page.Page;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.sys.SystemConfig;
import com.up72.sys.dao.SysLogBusinessDao;
import com.up72.sys.model.SysLogBusiness;
import com.up72.sys.vo.query.SysLogBusinessQuery;
/**
 * 业务日志表业务处理
 * 
 * @author iscs
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class SysLogBusinessManager extends BaseManager<SysLogBusiness,java.lang.Long>{

	private SysLogBusinessDao sysLogBusinessDao;

	public void setSysLogBusinessDao(SysLogBusinessDao dao) {
		this.sysLogBusinessDao = dao;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EntityDao getEntityDao() {
		return this.sysLogBusinessDao;
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked" })
	public Page findPage(SysLogBusinessQuery query) {
		return sysLogBusinessDao.findPage(query);
	}
	
	public int clearLog(){
		return sysLogBusinessDao.clearLog();
	}
	
	/**
	 * 日志导出
	 * @author baoxin.men 2012-9-7 下午05:54:29
	 * @param params
	 * @param orders
	 * @param buffSize
	 * @param fileCount
	 * @return
	 */
	public String exportSysLogBusiness(HashMap<String, Object> params, TreeMap<String, String> orders, int buffSize,int fileCount) {
		String configPath = SystemConfig.instants().getPath("import.export.xml.entity");
		String xmlPath = CommonConstants.ROOTPATH + configPath + "/" + SysLogBusiness.class.getSimpleName() + ".xml";
		List<ColumnConfig> configList = ImportExportXML.readConfigList(xmlPath);
		
		Pagination pagination = new Pagination(0,buffSize);
		QueryResult queryResult = this.getSysLogBusiness(params, orders, pagination);
		List<String> fileList = new ArrayList<String>();
		
		ExcelExportPoiUtil eepu = null;
		String file = null;
		
		String result = "";
		
		if(queryResult.getPagination().getTotalRecord() > 0){
			while(null != queryResult 
					&& null != queryResult.getItems() 
					&& !queryResult.getItems().isEmpty()){
				boolean isSave = false;
				if((pagination.getStart() % fileCount == 0) && null != eepu){
					isSave = true;
				}
				if(null == eepu){
					file = this.getXlsFileName();
					eepu = initEepu(file, configList);
				}
				
				eepu.export(queryResult.getItems());
				pagination.setStart(pagination.getStart()+pagination.getRange());
				queryResult = this.getSysLogBusiness(params, orders, pagination);
				
				if(isSave){
					if(null != eepu){
						try {
							eepu.save(file);
							fileList.add(file);
						} catch (IOException e) {
							//
						}
					}
					eepu = null;
				}
			}
			if(null != eepu){
				try {
					eepu.save(file);
					fileList.add(file);
				} catch (IOException e) {
					//
				}
			}
			result = zip(fileList);
		}
		return result;
	}
	
	/**
	 * 取得SysLogBusiness信息分页列表
	 * @param orderMap 排序map
	 * @param pagination 分页对象
	 * @return 查询结果
	 */
	@Transactional(readOnly=true)
	public QueryResult getSysLogBusiness(HashMap<String, Object> params, TreeMap<String ,String> orderMap ,Pagination pagination)  {
		return sysLogBusinessDao.findPage(params,  orderMap, pagination);
	}
	
	/**
	 * @author baoxin.men 2012-9-7 下午06:01:36
	 * @return
	 */
	private String getXlsFileName(){
		return CommonConstants.ROOTPATH + stringUtil.parseToPath("/export/"+UUID.randomUUID().toString()+".xls");
	}

	/**
	 * @author baoxin.men 2012-9-7 下午06:01:40
	 * @param file
	 * @param configList
	 * @return
	 */
	private ExcelExportPoiUtil initEepu(String file,List<ColumnConfig> configList){
		ExcelExportPoiUtil result = new ExcelExportPoiUtil(file,configList);
		result.exportHeader(new HSSFColor.GREY_40_PERCENT(),new HSSFColor.WHITE());
		return result;
	}
	
	/**
	 * 把文件打包
	 * @author baoxin.men 2012-9-7 下午06:01:43
	 * @param fileList
	 * @return
	 */
	private String zip(List<String> fileList){
		String result = CommonConstants.ROOTPATH + stringUtil.parseToPath("/export/"+UUID.randomUUID().toString()+".zip");
		if(null == fileList || fileList.isEmpty()){
			return result;
		}
		if(fileList.size() == 1){
			return fileList.get(0);
		}
		
		byte[] buffer = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(result));
			for (int j = 0; j < fileList.size(); j++) {
				FileInputStream fis = new FileInputStream(fileList.get(j));
				String fileName = fileList.get(j).substring(fileList.get(j).lastIndexOf("/")+1);
				ZipEntry entry = new ZipEntry(fileName);
				out.putNextEntry(entry);
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
		} catch (FileNotFoundException e) {
			//
		} catch (IOException e) {
			//
		}

		return result;
	}
}
