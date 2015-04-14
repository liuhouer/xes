package com.up72.base;

import static com.up72.common.CommonUtils.cookieUtil;
import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.tokenUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.up72.framework.beanutils.BeanUtils;
import com.up72.framework.page.Page;
import com.up72.framework.page.PageRequest;
import com.up72.page.PageRequestFactory;
import com.up72.util.ConvertRegisterHelper;

/**
 * 标准的rest方法列表
 * 
 * <pre>
 * /userinfo                =&gt; index()  
 * /userinfo/new            =&gt; _new()  注意: 不使用/userinfo/add =&gt; add()的原因是ad会被一些浏览器当做广告URL拦截
 * /userinfo/{id}           =&gt; show()  
 * /userinfo/{id}/edit      =&gt; edit()  
 * /userinfo        POST    =&gt; create()  
 * /userinfo/{id}   PUT     =&gt; update()  
 * /userinfo/{id}   DELETE  =&gt; delete()  
 * /userinfo        DELETE  =&gt; batchDelete()  
 * </pre>
 * 
 * @author up72
 */
public class BaseRestSpringController<Entity, PK> {
	protected final static String SUCCESS = "success";
	protected final static String ERROR = "error";
	
	protected final static Boolean RLT_SUCCESS = true;
	protected final static Boolean RLT_ERROR = false;
	
	protected final static String STATUS = "status";
	protected final static String MESSAGE = "message";
	
	protected final static String SAVE_SUCCESS = "保存成功";
	protected final static String CREATED_SUCCESS = "创建成功";
	protected final static String UPDATE_SUCCESS = "更新成功";
	protected final static String DELETE_SUCCESS = "删除成功";
	protected final static String OPRATE_SUCCESS = "操作成功";
	protected final static String SEND_SUCCESS =   "发送成功";
	protected final static String FREEZE_SUCCESS =  "冻结成功";
	protected final static String EXPORT_SUCCESS =  "导出成功";
	
	protected Log log = LogFactory.getLog(getClass());

	/** 定义分页传参 PAGINATION_START 页面起始数据 PAGINATION_RANGE分页大小 */
	public final static String PAGINATION_START = "start";
	public final static String PAGINATION_RANGE = "range";
	/** 定义系统数据字典类别默认分页大小 */

	public final static int DEFAULT_RANGE = 20;

	static {
		// 注册converters
		ConvertRegisterHelper.registerConverters();
	}

	public static void copyProperties(Object target, Object source) {
		BeanUtils.copyProperties(target, source);
	}

	public static <T> T copyProperties(Class<T> destClass, Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}

	@SuppressWarnings( { "unchecked" })
	public ModelMap toModelMap(Page page, PageRequest pageRequest) {
		return toModelMap("", page, pageRequest);
	}

	@SuppressWarnings( { "unchecked" })
	public ModelMap toModelMap(String tableId, Page page,
			PageRequest pageRequest) {
		ModelMap model = new ModelMap();
		saveIntoModelMap(tableId, page, pageRequest, model);
		return model;
	}

	/**
	 * 用于一个页面有多个extremeTable是使用
	 * 
	 * @param tableId
	 *            等于extremeTable的tableId属性
	 */
	@SuppressWarnings( { "unchecked" })
	public static void saveIntoModelMap(String tableId, Page page,
			PageRequest pageRequest, ModelMap model) {
		Assert.notNull(tableId, "tableId must be not null");
		Assert.notNull(page, "page must be not null");
		
		model.addAttribute(tableId + "page", page);
		model.addAttribute(tableId + "totalRows", new Integer(page
				.getTotalCount()));
		model.addAttribute(tableId + "pageRequest", pageRequest);
		model.addAttribute(tableId + "query", pageRequest);
	}

	@SuppressWarnings( { "unchecked" })
	public static PageRequest bindPageRequest(HttpServletRequest request,
			PageRequest pageRequest, String defaultSortColumns) {
		return PageRequestFactory.bindPageRequest(pageRequest, request,
				defaultSortColumns);
	}

	@SuppressWarnings( { "unchecked" })
	public static <T> T getOrCreateRequestAttribute(HttpServletRequest request,
			String key, Class<T> clazz) {
		Object value = request.getAttribute(key);
		if (value == null) {
			try {
				value = clazz.newInstance();
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
			request.setAttribute(key, value);
		}
		return (T) value;
	}

	/** 显示列属性值 */
	private static final String SHOW_LABLE_VALUE ="showColumn";
	/** 显示列标签字段 */
	private static final String SHOW_COLUMN ="showColumn";
	/** 显示列标签存储cookie时长 */
	private static final Integer SHOW_COLUMN_COOKIE_TIME = 3600*24*30;
	/**
	 * 列排序处理方法
	 * 
	 * @param url
	 *            需要排序处理的页面url
	 * @return 需要排序
	 * @author wqtan
	 */
	private String getJsonShowLable(String url, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (null != url && !url.trim().equals("")) {
			if (url.indexOf('?') != -1) {
				url = url.substring(0, url.indexOf('?'));
			}
			url = url.replace('/', '_');
		}
		String[] showlables = request
				.getParameterValues(SHOW_LABLE_VALUE);
		String result = "";
		
		if (null != showlables && showlables.length > 0) {
			result = jsonUtil.object2Json(showlables);
			cookieUtil.setCookie(response, 
					url + SHOW_LABLE_VALUE, result,
					SHOW_COLUMN_COOKIE_TIME);
		} else {
			result = cookieUtil.getString(request, 
					url + SHOW_LABLE_VALUE);
		}
		return result;
	}
	
	/**
	 * 前台列表显示列处理
	 */
	protected void addShowLabelAttrbite(String key,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		model.addAttribute(SHOW_COLUMN,this.getJsonShowLable(key,request, response));
	}
	/**
	 * 前台列表显示列处理
	 */
	protected void addShowLabelAttrbite(String key,HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setAttribute(SHOW_COLUMN,this.getJsonShowLable(key,request, response));
	}
	
	
	/**
	 * 生成Token值
	 * @author bxmen
	 * @param request 
	 * @summary 
	 */
	public static void createToken(HttpServletRequest request){
		tokenUtil.createToken(request);
	}
	
	/**
	 * 验证Token是否有效，有效可以处理数据，无效跳转到错误提示页面
	 * @author bxmen
	 * @param request
	 * @return boolean
	 * @summary 
	 */
	public static boolean validateToken(HttpServletRequest request){
		boolean result = false;
		if(tokenUtil.isTokenValid(request)){
			tokenUtil.destroyToken(request);
			result = true;
		}
		return result;
	}
}
