package com.up72.sys.controller;

import static com.up72.common.CommonUtils.jsonUtil;
import static com.up72.common.CommonUtils.paramUtils;
import static com.up72.common.CommonUtils.stringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.up72.base.BaseRestSpringController;
import com.up72.common.image.ImageConfig;
import com.up72.common.upload.UploadConfig;
import com.up72.exception.ServiceException;
import com.up72.framework.page.Page;
import com.up72.framework.util.ObjectUtils;
import com.up72.framework.util.holder.ApplicationContextHolder;
import com.up72.framework.web.scope.Flash;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.sys.dao.DictionaryDao;
import com.up72.sys.mail.MailManager;
import com.up72.sys.model.Dictionary;
import com.up72.sys.model.DictionaryValue;
import com.up72.sys.service.DictionaryManager;
import com.up72.sys.service.DictionaryValueManager;

/**
 * 数据字典信息数据提取跳转实现
 * 
 * @author dream
 * @link
 * 
 * @version $Revision: 1.00 $ $Date: 2009-05-12 10:02:23
 */
@Controller
@RequestMapping("/admin/sys/dictionary")
public class AdminDictionaryController extends
		BaseRestSpringController<Dictionary, java.lang.Long> {

	/** 定义数据字典模板位置,规则为模板区域下的相对路径 */
	public final static String GO_PAGE = "sys/dictionary/page";
	public final static String GO_VIEW = "sys/dictionary/view";
	public final static String GO_EDIT = "sys/dictionary/edit";
	private final String REDIRECT_GO_PAGE = "redirect:/admin/sys/dictionary/";
	
	@Autowired
	private DictionaryManager dictionaryManager;
	@Autowired
	private DictionaryValueManager dictionaryValueManager;

	
	/** 列表  
	  * @throws IOException */
	@RequestMapping
	@SuppressWarnings({ "unchecked" })
	public String index(ModelMap model,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = paramUtils.getParameter(request, "name", "");
		String code = paramUtils.getParameter(request, "code", "");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (!"".equals(code)) {
			params.put("code like '%" + code + "%' ", null);
		}
		if (!"".equals(name)) {
			params.put("name like '%" + name + "%' ", null);
		}
		int range = paramUtils.getIntParameter(request, "pageSize",	DEFAULT_RANGE);
		int pageNumber = paramUtils.getIntParameter(request, "pageNumber", 0);
		int start = (pageNumber - 1) * range;
		QueryResult queryResult = this.dictionaryManager.findPage(params,
				new Pagination(start, range), null);
		Pagination pagination = queryResult.getPagination();
		Page page = new Page(pageNumber, pagination.getRange(), pagination
				.getTotalRecord(), queryResult.getItems());

		// 获得前台显示列信息
		model.put("page", page);
		model.put("name", name);
		model.put("code", code);
		
		this.addShowLabelAttrbite("/admin/sys/dictionary",model,request,response);
		return "/admin/sys/dictionary/index";
	}
	
	/**
	 * 删除数据字典记录
	 * 
	 * @param request
	 * @param response
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String doDelete(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long[] ids = paramUtils.getLongParameters(request, "items", 0);
		if (ids.length == 1) {
			dictionaryManager.deleteDictionary(ids[0]);
		} else {
			dictionaryManager.deleteDictionarys(ids);
		}
		Flash.current().success(DELETE_SUCCESS); 
		return REDIRECT_GO_PAGE;
	}
	
	/**
	 * 创建或者修改数据字典记录 当dictionaryId为0时,进行创建操作,否则进行更新操作
	 * 
	 * @param request
	 * @param response
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doEdit")
	@ResponseBody
	public Integer doEdit(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, Dictionary dictionary)
			throws Exception {
		Integer result =0;
		long id = dictionary.getId();
		Long dictionaryId = 0L;
		Integer level = 0;
		if(dictionary.getDictionaryId() ==0){
			level =1;
		}else{
			level = dictionary.getDictionaryById(dictionary.getDictionaryId()).getLevel()+1;
			dictionaryId = dictionary.getDictionaryId();
		}
		dictionary.setDictionaryId(dictionaryId);
		dictionary.setLevel(level);
		
		try{
			if (id == 0) {
				dictionaryManager.createDictionary(dictionary);
			} else {
				dictionaryManager.editDictionary(dictionary);
				//修改email配置文件
				String key = dictionary.getDictionaryKey();
				String value = dictionary.getRefCode();
				if (null != key && !key.trim().equals("")) {
					if (key.startsWith("mail")) {
						//MailManager mailManager = (MailManager) ApplicationContextHolder.getBean("mailManager");
						MailManager mailManager = new MailManager();
						mailManager.eidteEmailProperty(key, value);
					}
				}
			}
			result =1;
		}catch(Exception e){
			e.printStackTrace();
			result =0;
		}
		return result;
	}

	
	
	/**
	 * 提取全部数据字典记录输出列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/edit")
	public String goEdit(ModelMap model, @PathVariable java.lang.Long id) throws Exception {
		if (id != 0) {
			Dictionary dictionary = dictionaryManager.getDictionary(id);
			List<Dictionary> dictionaryList = dictionaryManager.findCategoriesByTree(0);
			model.addAttribute("dictionary", dictionary);
			model.addAttribute("dicId",dictionary.getId());
			model.addAttribute("dictionaryList", dictionaryList);
		}
		return "/admin/sys/dictionary/edit";
	}
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,Dictionary dictionary,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Dictionary> dictionaryList = dictionaryManager.findCategoriesByTree(0);
		model.addAttribute("dictionaryList", dictionaryList);
		model.addAttribute("dictionary",dictionary);
		return "/admin/sys/dictionary/new";
	}
	/** 进入新增 */
	@RequestMapping(value="/{id}/addChild")
	public String addChild(ModelMap model,Dictionary dictionary,@PathVariable java.lang.Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Dictionary dic = dictionaryManager.getDictionary(id);
		List<Dictionary> dictionaryList = dictionaryManager.findCategoriesByTree(0);
		String path = dic.getPath()+"["+id+"],";
		dictionary.setDictionaryId(id);
		dictionary.setPath(path);
		dictionary.setLevel(dic.getLevel());
		model.addAttribute("dictionary",dictionary);
		model.addAttribute("dicId",dic.getId());
		model.addAttribute("dictionaryList", dictionaryList);
		return "/admin/sys/dictionary/new";
	}

	/**
	 * 提取数据字典单条信息,输出查看页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goPage")
	public String goPage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String code = paramUtils.getParameter(request, "code", "");
		String name = paramUtils.getParameter(request, "name", "");
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(!"".equals(code)){
			params.put("code like '%" + code + "%'", null);
			model.addAttribute("code", code);
		}
		if(!"".equals(name)){
			params.put("name like '%" + name + "%'", null);
			model.addAttribute("name", name);
		}
		TreeMap<String, String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		QueryResult queryResult = dictionaryManager.findPage(params,
				new Pagination(paramUtils.getIntParameter(request,
						PAGINATION_START, 0), paramUtils.getIntParameter(
						request, PAGINATION_RANGE, DEFAULT_RANGE)), orderMap);
		model.addAttribute("dictionaryList", queryResult.getItems());
		model.addAttribute("dictionaryPagination", queryResult.getPagination());
		return GO_PAGE;
	}

	/**
	 * 提取数据字典编辑信息,输出编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goView")
	public String goView(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = paramUtils.getLongParameter(request, "id", 0L);
		if (id != 0) {
			Dictionary dictionary = dictionaryManager.getDictionary(id);
			model.addAttribute("dictionary", dictionary);
		}
		return GO_VIEW;
	}
	
	/**
	 * 提取全部数据字典记录输出列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEditDiv")
	public ModelAndView goEditDiv(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionary/editDiv");
		String op = paramUtils.getParameter(request, "op", "");

		long dictionaryId = paramUtils.getLongParameter(request,
				"dictionaryId", 0L);
		if (dictionaryId != 0) {
			if (op.equals("1")) {
				Dictionary dictionary = dictionaryManager
						.getDictionary(dictionaryId);
				mav.addObject("dictionary", dictionary);
			}
			if (op.equals("2")) {
			}
			if (op.equals("3")) {
				Dictionary dictionary = dictionaryManager
						.getDictionary(dictionaryId);
				mav.addObject("dictionary", dictionary);
			}
			mav.addObject("op", op);
			mav.addObject("dictionaryId", dictionaryId);
		}

		return mav;
	}

	/**
	 * 提取数据字典分页记录输出分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goList")
	public ModelAndView goList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionary/list");

		List<Dictionary> dictionaryList = dictionaryManager.getDictionarys();
		mav.addObject("dictionaryList", dictionaryList);
		return mav;
	}



	/**
	 * 获取数据字典树
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/goXml")
	public void goXml(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String menudata = paramUtils.getParameter(request, "menudata",
				"menudata");
		Document doc = new Document(new Element("tree"));

		String searchName = paramUtils.getParameter(request, "searchName", "");
		List dictionaryList = dictionaryManager.getDictionarys();
		transfer(doc, dictionaryList, menudata, searchName);

		Format format = Format.getCompactFormat();
		format.setEncoding("utf-8");
		format.setIndent("\t");
		XMLOutputter xout = new XMLOutputter(format);
		xout.output(doc, out);
		out.flush();
		out.close();
	}

	@RequestMapping(value="/indexTree")
	public String indexTree(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("dictionaryId", 0L);
		List<Dictionary> dictionaryList = this.dictionaryManager.findList(params, 0, null);
//		List<Product> productList = this.productManager.findAll();
//		model.addAttribute("productList",productList);
		model.addAttribute("dictionaryList",dictionaryList);
//		addPermissionAttribute(model, request);
//		for(Dictionary dic : dictionaryList){
//			System.out.println(dic.getChilds(dic.getId()));
//		}
		return "/admin/sys/dictionary/indexTree";//indexTree";
	}
	
	/**
	 * 获取树形json树
	 * @author zjliu
	 * @create 2012-12-14 15:21
	 */
	@RequestMapping(value="/nodes")
	public String _getNodesJson(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Long parentId = paramUtils.getLongParameter(request, "id", 0L);
		DictionaryDao dictionaryDao = (DictionaryDao)ApplicationContextHolder.getBean("dictionaryDao");
		List<Dictionary> list = dictionaryDao.find("from Dictionary where dictionaryId = ? order by sort asc", new Object[]{parentId});
		model.addAttribute("dictionaryList",list);
		
		return "/admin/sys/dictionary/nodes_json";
	}
	
	@RequestMapping(value="/tab")
	public String tab(ModelMap model,HttpServletRequest request){
		Long id = paramUtils.getLongParameter(request, "id", 0L);
		DictionaryDao dictionaryDao = (DictionaryDao)ApplicationContextHolder.getBean("dictionaryDao");
		Dictionary dictionary = dictionaryDao.get(id);
		model.addAttribute("dictionary",dictionary);
		return "/admin/sys/dictionary/tab";
	}
	
	@RequestMapping(value="/{id}/view")
	public String view(ModelMap model,@PathVariable java.lang.Long id,HttpServletRequest request){
		DictionaryDao dictionaryDao = (DictionaryDao)ApplicationContextHolder.getBean("dictionaryDao");
		Dictionary dictionary = dictionaryDao.get(id);
		model.addAttribute("dictionary",dictionary);
		return "/admin/sys/dictionary/tab_show";
	}
	
	/** 删除 
	 * @throws IOException */
	@RequestMapping(value="/{id}/deleteDictionary")
	@ResponseBody
	public String delete(ModelMap model,@PathVariable java.lang.Long id) throws IOException {
		//dictionaryManager.deleteDictionary(id);
		List<Dictionary> dictionaryList = dictionaryManager.findCategoriesByTree(id);
		if(dictionaryList != null && dictionaryList.size()>0){
			for(Dictionary dic : dictionaryList){
				dictionaryManager.deleteDictionary(dic.getId());
			}
		}
		HashMap<String,String> jsonMap=new HashMap<String, String> ();
		jsonMap.put("status", "success");
		return jsonUtil.object2Json(jsonMap);
	}
	
	
	
	public int _delNode( Long id){
		int resultCode = 0;
		
		return resultCode;
	} 
	
	@SuppressWarnings("unchecked")
	private void transfer(Document doc, List list, String menudata,
			String searchName) {
		Dictionary dictionary = null;
		long parentId = 0;
		for (int i = 0; i < list.size(); i++) {
			dictionary = (Dictionary) list.get(i);
			if (dictionary != null) {
				Element tree = doc.getRootElement();
				Element inner = new Element("tree").setAttribute("text",
						null != dictionary.getName() ? dictionary.getName()
								: "");
				tree.addContent(inner);
				List<DictionaryValue> subDictionary = dictionaryValueManager
						.getDictionaryValues(null, dictionary.getId());
				if (null != subDictionary && subDictionary.size() > 0) {
					inner.setAttribute("src",
							"/sys/dictionaryValue/goXml.jhtml?dictionaryId="
									+ dictionary.getId());
				}
				inner.setAttribute("target", "frame_main");
				inner.setAttribute("cId", "dictionaryId="
						+ dictionary.getId().toString());
				inner.setAttribute("parentId", String.valueOf(parentId));
				inner.setAttribute("menudata", menudata);
			}
		}
	}
	/**
	 * 验证标题唯一性
	 * @author wgf
	 */
	@RequestMapping(value = "/validateKey")
	@ResponseBody
	public boolean validateKey(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long id = paramUtils.getLongParameter(request, "id", 0L);
		String dictionaryKey=paramUtils.getParameter(request, "dictionaryKey");
		if(ObjectUtils.isEmpty(dictionaryKey.trim())){
			return false;
		}
		return dictionaryManager.validate("dictionaryKey", dictionaryKey, id);
	}
	

	/** 图片设置列表  
	  * @throws IOException */
	@RequestMapping(value = "/editImage")
	public String editImage(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Dictionary dictionary = this.dictionaryManager.getDictionaryByKey(ImageConfig.IMAGE_SETTING);
		List<Dictionary> dictionarylist = new ArrayList<Dictionary>();
		if (null != dictionary) {
			dictionarylist = this.dictionaryManager.findChilds(dictionary.getId());
		}
		model.addAttribute("dictionarylist", dictionarylist);
		return "/admin/sys/dictionary/editDictionary";
	}
	/** 上传设置列表  
	 * @throws IOException */
	@RequestMapping(value = "/editUpload")
	public String editUpload(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		Dictionary dictionary = this.dictionaryManager.getDictionaryByKey(UploadConfig.UPLOAD_SETTING);
		List<Dictionary> dictionarylist = new ArrayList<Dictionary>();
		if (null != dictionary) {
			dictionarylist = this.dictionaryManager.findChilds(dictionary.getId());
		}
		model.addAttribute("dictionarylist", dictionarylist);
		return "/admin/sys/dictionary/editDictionary";
	}
	/** 更新设置列表  
	 * @throws IOException */
	@RequestMapping(value = "/updateDictionary")
	@ResponseBody
	public String updateDictionary(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String[] items = paramUtils.getParameters(request, "items", null);
		String status = SUCCESS;
		String message = "操作成功";
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try{
			if (null != items && items.length != 0) {
				for (int i = 0; i < items.length; i++) {
					String[] temValue = items[i].split("_");
					String key = temValue[1];
					String value = temValue[0];
					this.dictionaryManager.updateDictionary(key, value);
				}
			}
		}catch (ServiceException e) {
			status = ERROR;
			message = e.getMessage();
		}catch (Exception e) {
			log.error(e);
			status = ERROR;
			message = "系统错误"+e.getMessage();
		}
		jsonMap.put(STATUS, status);
		jsonMap.put(MESSAGE, stringUtil.encode(message));
			
		return jsonUtil.object2Json(jsonMap);
	}
	
}