package com.up72.sys.controller;

import static com.up72.common.CommonUtils.paramUtils;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.up72.base.BaseRestSpringController;
import com.up72.framework.page.Page;
import com.up72.framework.web.scope.Flash;
import com.up72.page.Pagination;
import com.up72.page.QueryResult;
import com.up72.sys.model.Dictionary;
import com.up72.sys.model.DictionaryValue;
import com.up72.sys.service.DictionaryManager;
import com.up72.sys.service.DictionaryValueManager;

/**
 * 数据值信息数据提取跳转实现
 * 
 * @author dream
 * @link
 * 
 * @version $Revision: 1.00 $ $Date: 2009-05-12 10:02:23
 */
@Controller
@RequestMapping("/admin/sys/dictionaryValue")
public class AdminDictionaryValueController extends
		BaseRestSpringController<DictionaryValue, java.lang.Long> {

	/** 定义数据值模板位置,规则为模板区域下的相对路径 */
	public final static String GO_PAGE = "sys/dictionaryValue/page";
	public final static String GO_VIEW = "sys/dictionaryValue/view";
	public final static String GO_EDIT = "sys/dictionaryValue/edit";

	@Autowired
	private DictionaryValueManager dictionaryValueManager;
	@Autowired
	private DictionaryManager dictionaryManager;

	/**
	 * 删除数据值记录
	 * 
	 * @param request
	 * @param response
	 * @param dictionaryValue
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String doDelete(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long dictionaryId = paramUtils.getLongParameter(request,
				"dictionaryId", 0L);
		long[] ids = paramUtils.getLongParameters(request, "items", 0);
		if (ids.length == 1) {
			dictionaryValueManager.deleteDictionaryValue(ids[0]);
		} else {
			dictionaryValueManager.deleteDictionaryValues(ids);
		}
		Flash.current().success(DELETE_SUCCESS);
		return "redirect:/admin/sys/dictionaryValue/?dictionaryId="
				+ dictionaryId;
	}

	/**
	 * 创建或者修改数据值记录 当dictionaryValueId为0时,进行创建操作,否则进行更新操作
	 * 
	 * @param request
	 * @param response
	 * @param dictionaryValue
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEdit", method=RequestMethod.POST)
	public String doEdit(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, DictionaryValue dictionaryValue)
			throws Exception {
		long id = dictionaryValue.getId();
		if (id == 0) {
			dictionaryValueManager.createDictionaryValue(dictionaryValue);
			Flash.current().success(CREATED_SUCCESS);
		} else {
			dictionaryValueManager.editDictionaryValue(dictionaryValue);
			Flash.current().success(UPDATE_SUCCESS);
		}
		return "redirect:/admin/sys/dictionaryValue/?dictionaryId="
				+ dictionaryValue.getDictionaryId();
	}

	/**
	 * 提取全部数据值记录输出列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public String goEdit(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = paramUtils.getLongParameter(request, "id", 0L);
		long dictionaryId = paramUtils.getLongParameter(request,
				"dictionaryId", 0L);
		long parentId = paramUtils.getLongParameter(request, "parentId", 0L);
		if (dictionaryId != 0) {
			Dictionary dictionary = dictionaryManager
					.getDictionary(dictionaryId);
			model.put("dictionary", dictionary);
		}
		DictionaryValue dictionaryValue = null;
		if (parentId != 0) {
			dictionaryValue = dictionaryValueManager
					.getDictionaryValue(parentId);
		}
		if (id != 0) {
			dictionaryValue = dictionaryValueManager
					.getDictionaryValue(id);
		}
		if(null == dictionaryValue){
			dictionaryValue = new DictionaryValue();
		}
		model.put("dictionaryValue", dictionaryValue);
		return "/admin/sys/dictionaryValue/edit";
	}
	
	/**
	 * 提取数据值单条信息,输出查看页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@SuppressWarnings({ "unchecked" })
	public String index(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long dictionaryId = paramUtils.getLongParameter(request,
				"dictionaryId", 0L);
		long parentId = paramUtils.getLongParameter(request, "parentId", 0L);
		String code = paramUtils.getParameter(request, "code", "");
		String value = paramUtils.getParameter(request, "value", "");
		// 查询参数
		HashMap<String, Object> params = new HashMap<String, Object>();
		// 获取所属数据字典
		if (dictionaryId != 0) {
			params.put("dictionaryId", dictionaryId);
			Dictionary dictionary = dictionaryManager
					.getDictionary(dictionaryId);
			model.addAttribute("dictionary", dictionary);
		}
		// 获取父类别数据字典
		if (parentId != 0) {
			params.put("parentId", parentId);
			DictionaryValue parentDictionaryValue = dictionaryValueManager
					.getDictionaryValue(parentId);
			model.addAttribute("parentDictionaryValue", parentDictionaryValue);
		}
		if (!"".equals(code)) {
			params.put("code like '%" + code + "%'", null);
			model.addAttribute("code", code);
		}
		if (!"".equals(value)) {
			params.put("value like '%" + value + "%'", null);
			model.addAttribute("value", value);
		}
		// 排序参数
		TreeMap<String, String> orderMap = new TreeMap<String, String>();
		orderMap.put("sortId", "asc");
		
		int range = paramUtils.getIntParameter(request, "pageSize",	DEFAULT_RANGE);
		int pageNumber = paramUtils.getIntParameter(request, "pageNumber", 0);
		int start = (pageNumber - 1) * range;
				
		QueryResult queryResult = dictionaryValueManager.findPage(params,
				new Pagination(start,range), orderMap);
		Pagination pagination = queryResult.getPagination();
		Page page = new Page(pageNumber, pagination.getRange(), pagination.getTotalRecord(), queryResult.getItems());
		model.addAttribute("dictionaryValueList", queryResult.getItems());
		model.addAttribute("dictionaryValuePagination", queryResult.getPagination());
		model.put("page", page);		
		return "/admin/sys/dictionaryValue/index";
	}


	/**
	 * 提取数据值编辑信息,输出编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goView")
	public String goView(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = paramUtils.getLongParameter(request, "id", 0L);
		if (id != 0) {
			DictionaryValue dictionaryValue = dictionaryValueManager
					.getDictionaryValue(id);
			model.addAttribute("dictionaryValue", dictionaryValue);
		}
		return GO_VIEW;
	}
	
	/**
	 * 提取全部数据值记录输出列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView goValue(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionaryValue/value");

		long id = paramUtils.getLongParameter(request, "id", 0L);
		String name = paramUtils.getParameter(request, "name", "");
		String did = paramUtils.getParameter(request, "did", "");
		if (id != 0) {
			DictionaryValue dictionaryValue = dictionaryValueManager
					.getDictionaryValue(id);
			mav.addObject("dictionaryValue", dictionaryValue);
		}
		mav.addObject("name", name);
		mav.addObject("did", did);
		return mav;
	}

	/**
	 * 提取全部数据字典记录输出列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView goEditDiv(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionaryValue/editDiv");
		String op = paramUtils.getParameter(request, "op", "");

		long dictionaryValueId = paramUtils.getLongParameter(request,
				"dictionaryValueId", 0L);
		if (dictionaryValueId != 0) {
			if (op.equals("1")) {
				DictionaryValue parentDictionaryValue = dictionaryValueManager
						.getDictionaryValue(dictionaryValueId);
				mav.addObject("parentDictionaryValue", parentDictionaryValue);
			}
			if (op.equals("2")) {
				DictionaryValue dictionaryValue = dictionaryValueManager
						.getDictionaryValue(dictionaryValueId);
				long parentId = (null != dictionaryValue.getParentId() && dictionaryValue
						.getParentId() != 0) ? dictionaryValue.getParentId()
						: 0;
				if (parentId == 0) {
					long dictionaryId2 = (null != dictionaryValue
							.getDictionary().getId()) ? dictionaryValue
							.getDictionary().getId() : 0;
					if (dictionaryId2 != 0) {
						Dictionary dictionary = dictionaryManager
								.getDictionary(dictionaryId2);
						mav.addObject("dictionary", dictionary);
					}
				} else {
					DictionaryValue parentDictionaryValue = dictionaryValueManager
							.getDictionaryValue(parentId);
					mav.addObject("parentDictionaryValue",
							parentDictionaryValue);
					mav.addObject("parentId", parentId);
				}
			}
			if (op.equals("3")) {
				DictionaryValue dictionaryValue = dictionaryValueManager
						.getDictionaryValue(dictionaryValueId);
				mav.addObject("dictionaryValue", dictionaryValue);

				long parentId = (null != dictionaryValue.getParentId() && dictionaryValue
						.getParentId() != 0) ? dictionaryValue.getParentId()
						: 0;
				if (parentId != 0) {
					DictionaryValue parentDictionaryValue = dictionaryValueManager
							.getDictionaryValue(dictionaryValue.getParentId());
					mav.addObject("parentDictionaryValue",
							parentDictionaryValue);
					mav.addObject("parentId", parentId);
				} else {
					long dictionaryId2 = (null != dictionaryValue
							.getDictionary().getId()) ? dictionaryValue
							.getDictionary().getId() : 0;
					if (dictionaryId2 != 0) {
						Dictionary dictionary = dictionaryManager
								.getDictionary(dictionaryId2);
						mav.addObject("dictionary", dictionary);
					}
				}

			}
			mav.addObject("op", op);
			mav.addObject("dictionaryValueId", dictionaryValueId);
		}

		return mav;
	}

	/**
	 * 提取数据值分页记录输出分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView goList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionaryValue/list");
		List<DictionaryValue> dictionaryValueList = dictionaryValueManager
				.getDictionaryValues();
		mav.addObject("dictionaryValueList", dictionaryValueList);
		return mav;
	}


	@SuppressWarnings("unchecked")
	public void goXml(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String menudata = paramUtils.getParameter(request, "menudata",
				"menudata");
		Document doc = new Document(new Element("tree"));

		String selectedNode = paramUtils.getParameter(request, "selectedNode",
				"");
		String searchName = paramUtils.getParameter(request, "searchName", "");

		long dictionaryValueId = paramUtils.getLongParameter(request,
				"dictionaryValueId", 0L);
		long dictionaryId = paramUtils.getLongParameter(request,
				"dictionaryId", 0L);
		String dictionaryCode = paramUtils.getParameter(request,
				"dictionaryCode", "");
		if (dictionaryValueId == 0) {
			if (!dictionaryCode.equals("")) {
				Dictionary dictionary = dictionaryManager
						.getDictionary(dictionaryCode);
				List<DictionaryValue> DictionaryValueList = dictionaryValueManager
						.getDictionaryValues(null, (null == dictionary) ? 0
								: dictionary.getId());
				transfer(doc, DictionaryValueList, menudata, selectedNode,
						searchName);
			} else {
				if (dictionaryId != 0) {
					Dictionary dictionary = dictionaryManager
							.getDictionary(dictionaryId);
					List<DictionaryValue> DictionaryValueList = dictionaryValueManager
							.getDictionaryValues(null, (null == dictionary) ? 0
									: dictionary.getId());
					transfer(doc, DictionaryValueList, menudata, selectedNode,
							searchName);
				}
			}
		} else {
			List<DictionaryValue> subCategories = dictionaryValueManager
					.getDictionaryValuesByParent(dictionaryValueId);
			transfer(doc, subCategories, menudata, selectedNode, searchName);
		}

		Format format = Format.getCompactFormat();
		format.setEncoding("utf-8");
		format.setIndent("\t");
		XMLOutputter xout = new XMLOutputter(format);
		xout.output(doc, out);
		out.flush();
		out.close();
	}

	@SuppressWarnings("unchecked")
	private void transfer(Document doc, List<DictionaryValue> list,
			String menudata, String selectedNode, String searchName) {
		DictionaryValue dictionaryValue = null;
		List<DictionaryValue> subDictionaryValueList = null;
		long parentId = 0;
		for (int i = 0; i < list.size(); i++) {
			dictionaryValue = list.get(i);
			if (dictionaryValue != null) {
				Element tree = doc.getRootElement();
				Element inner = new Element("tree").setAttribute("text",
						dictionaryValue.getValue());

				// 修改
				inner.setAttribute("selected", "false");
				if (!selectedNode.equals("")) {
					String[] selectedNodes = selectedNode.split(",");
					if (null != selectedNodes) {
						for (int k = 0; k < selectedNodes.length; k++) {
							if (selectedNodes[k].equals("["
									+ dictionaryValue.getId() + "]")) {
								inner.setAttribute("selected", "true");
							}
						}
					}
				}

				if (!searchName.equals("")
						&& dictionaryValue.getValue().indexOf(searchName) != -1) {
					inner.setAttribute("selected", "true");
				} else {
					inner.setAttribute("selected", "false");
				}

				tree.addContent(inner);
				subDictionaryValueList = dictionaryValueManager
						.getDictionaryValuesByParent(dictionaryValue.getId());
				if ((subDictionaryValueList != null)
						&& (subDictionaryValueList.size() != 0)) {
					inner.setAttribute("src",
							"/sys/dictionaryValue/goXml.jhtml?dictionaryValueId="
									+ dictionaryValue.getId());
				}
				if ((dictionaryValue.getParent() != null)) {
					parentId = dictionaryValue.getParentId();
				}
				inner.setAttribute("ids", "type");
				inner.setAttribute("flag", "29");
				inner.setAttribute("cId", "dictionaryValueId="
						+ dictionaryValue.getId().toString());
				inner.setAttribute("parentId", String.valueOf(parentId));
				inner.setAttribute("menudata", menudata);

			}
		}
	}

	/**
	 * 提取数据值 更新页面数据字典列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView goUpdateList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionaryValue/updateList");
		long id = paramUtils.getLongParameter(request, "id", 0L);
		long did = paramUtils.getLongParameter(request, "did", 0L);
		String name = paramUtils.getParameter(request, "name", "");
		String type = paramUtils.getParameter(request, "type", "");
		Map orderMap = new TreeMap();
		orderMap.put("sort", "asc");
		orderMap.put("id", "asc");
		if ((id != 0) && (!name.trim().equals(""))) {
			List<DictionaryValue> dictionaryValueList = dictionaryValueManager
					.getDictionaryValues(orderMap, id);
			mav.addObject("dictionaryValueList", dictionaryValueList);
		}
		mav.addObject("name", name);
		mav.addObject("did", did);
		mav.addObject("type", type);

		return mav;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView goUpdateListPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("sys/dictionaryValue/updatePage");
		long id = paramUtils.getLongParameter(request, "id", 0L);
		long did = paramUtils.getLongParameter(request, "did", 0L);
		String name = paramUtils.getParameter(request, "name", "");
		String type = paramUtils.getParameter(request, "type", "");
		Map orderMap = new TreeMap();
		orderMap.put("sort", "asc");
		orderMap.put("id", "asc");
		if ((id != 0) && (!name.trim().equals(""))) {
			List<DictionaryValue> dictionaryValueList = dictionaryValueManager
					.getDictionaryValues(orderMap, id);
			mav.addObject("dictionaryValueList", dictionaryValueList);
		}
		mav.addObject("name", name);
		mav.addObject("did", did);
		mav.addObject("type", type);

		return mav;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView goChileDic(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/sys/dictionaryValue/area_sel");
		Long parentId = paramUtils.getLongParameter(request, "parentId", 0L);
		Long id = paramUtils.getLongParameter(request, "id", 0L);// 市ID
		Long dictionaryId = paramUtils.getLongParameter(request,
				"dictionaryId", 0L);
		List<DictionaryValue> cityList = null;
		List<DictionaryValue> provinceList = null;
		provinceList = dictionaryValueManager.getDictionaryValues(null,
				dictionaryId);// 得到省
		cityList = dictionaryValueManager.getDictionaryValuesByParent(parentId);// 通过省id得到市

		if (id != 0) {
			DictionaryValue dictionaryValue = dictionaryValueManager
					.getDictionaryValue(id);
			if (dictionaryValue != null) {
				cityList = dictionaryValueManager
						.getDictionaryValuesByParent(dictionaryValue
								.getParentId());// 通过省id得到市
				mav.addObject("provinceId", dictionaryValue.getParent());
			}
		} else {
			cityList = dictionaryValueManager
					.getDictionaryValuesByParent(parentId);// 通过省id得到市
			mav.addObject("provinceId", parentId);
		}
		mav.addObject("cityList", cityList);
		mav.addObject("provinceList", provinceList);
		mav.addObject("cityId", id);

		return mav;
	}
}