package org.apache.jsp.pages.admin.auth.organization;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.up72.sys.model.Dictionary;
import com.up72.auth.model.Organization;

public final class indexTree_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/pages/admin/auth/organization/base.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      //  up72:override
      com.up72.framework.web.tags.OverrideTag _jspx_th_up72_005foverride_005f0 = (com.up72.framework.web.tags.OverrideTag) _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.get(com.up72.framework.web.tags.OverrideTag.class);
      _jspx_th_up72_005foverride_005f0.setPageContext(_jspx_page_context);
      _jspx_th_up72_005foverride_005f0.setParent(null);
      // /pages/admin/auth/organization/indexTree.jsp(7,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_up72_005foverride_005f0.setName("head");
      int _jspx_eval_up72_005foverride_005f0 = _jspx_th_up72_005foverride_005f0.doStartTag();
      if (_jspx_eval_up72_005foverride_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_up72_005foverride_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_up72_005foverride_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_up72_005foverride_005f0.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("\t<title>");
          out.print(Dictionary.TABLE_ALIAS);
          out.write(" 维护</title>\r\n");
          out.write("\t<script src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/rest.js\"></script>\r\n");
          out.write("\t<link href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/simpletable/simpletable.css\" type=\"text/css\"\r\n");
          out.write("\t\trel=\"stylesheet\">\r\n");
          out.write("\t<script type=\"text/javascript\"\r\n");
          out.write("\t\tsrc=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/simpletable/simpletable.js\"></script>\r\n");
          out.write("\t<link href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/grid/css/flexigrid.css\" type=\"text/css\"\r\n");
          out.write("\t\trel=\"stylesheet\">\r\n");
          out.write("\t<script type=\"text/javascript\"\r\n");
          out.write("\t\tsrc=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/jq_tree/jquery.treeview.js\"></script>\r\n");
          out.write("\t<link type=\"text/css\" href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/jq_tree/jquery.treeview.css\"\r\n");
          out.write("\t\trel=\"stylesheet\" />\r\n");
          out.write("\t\t<link rel=\"stylesheet\" href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/ztree/css/zTreeStyle/zTreeStyle.css\" type=\"text/css\">\r\n");
          out.write("  \t<script type=\"text/javascript\" src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/ztree/js/jquery.ztree.core-3.2.js\"></script>\r\n");
          out.write("\t<style type=\"text/css\">\r\n");
          out.write("\t\r\n");
          out.write(".name {\r\n");
          out.write("\tfont-weight: bold;\r\n");
          out.write("}\r\n");
          out.write("\r\n");
          out.write(".org {\r\n");
          out.write("\tcolor: #006;\r\n");
          out.write("}\r\n");
          out.write("\r\n");
          out.write(".workGroup {\r\n");
          out.write("\tcolor: #060;\r\n");
          out.write("}\r\n");
          out.write("\r\n");
          out.write(".role {\r\n");
          out.write("\tfont-style: italic;\r\n");
          out.write("\tcolor: #666;\r\n");
          out.write("}\r\n");
          out.write("</style>\r\n");
          out.write("\r\n");
          out.write("\t<script type=\"text/javascript\">\r\n");
          out.write("\t\r\n");
          out.write("\tvar setting = {\r\n");
          out.write("\t\t\tview: {\r\n");
          out.write("\t\t\t\tselectedMulti: false\r\n");
          out.write("\t\t\t},\r\n");
          out.write("\t\t\tasync: {\r\n");
          out.write("\t\t\t\tenable: true,\r\n");
          out.write("\t\t\t\turl:\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/organization/nodes\",\r\n");
          out.write("\t\t\t\tautoParam:[\"id\", \"name=n\", \"level=lv\"],\r\n");
          out.write("\t\t\t\totherParam:{\"method\":\"nodes\"},\r\n");
          out.write("\t\t\t\tdataFilter: filter\r\n");
          out.write("\t\t\t},\r\n");
          out.write("\t\t\tcallback: {\r\n");
          out.write("\t\t\t\tonClick: zTreeDblClick,\r\n");
          out.write("\t\t\t\tonRightClick: OnRightClick\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t};\r\n");
          out.write("\r\n");
          out.write("\t\tfunction filter(treeId, parentNode, childNodes) {\r\n");
          out.write("\t\t\tif (!childNodes) return null;\r\n");
          out.write("\t\t\tfor (var i=0, l=childNodes.length; i<l; i++) {\r\n");
          out.write("\t\t\t\tchildNodes[i].name = childNodes[i].name.replace(/\\.n/g, '.');\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t\treturn childNodes;\r\n");
          out.write("\t\t}\r\n");
          out.write("\t\t\r\n");
          out.write("\t\tfunction OnRightClick(event, treeId, treeNode){\r\n");
          out.write("\t\t\tif (!treeNode && event.target.tagName.toLowerCase() != \"button\" && $(event.target).parents(\"a\").length == 0) {\r\n");
          out.write("\t\t\t\tzTree.cancelSelectedNode();\r\n");
          out.write("\t\t\t\tshowRMenu(\"root\", event.clientX, event.clientY);\r\n");
          out.write("\t\t\t} else if (treeNode && !treeNode.noR) {\r\n");
          out.write("\t\t\t\tzTree.selectNode(treeNode);\r\n");
          out.write("\t\t\t\tshowRMenu(\"node\", event.clientX, event.clientY);\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t}\r\n");
          out.write("\t\tfunction zTreeDblClick(e, treeId, treeNode){\r\n");
          out.write("\t\t\t$.ajax({\r\n");
          out.write("\t\t\t  type: \"POST\",\r\n");
          out.write("\t\t\t  url: \"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/organization/tab\",\r\n");
          out.write("\t\t\t  data: \"id=\" + treeNode.id,\r\n");
          out.write("\t\t\t  dataType: \"html\",\r\n");
          out.write("\t\t\t  cache: false,\r\n");
          out.write("\t\t\t  success: function(html){\r\n");
          out.write("\t\t\t    \t$(\"#editDiv\").html(html);\r\n");
          out.write("\t\t\t  }\r\n");
          out.write("\t\t\t});\r\n");
          out.write("\t\t}\r\n");
          out.write("\t\t\r\n");
          out.write("\t\tfunction showRMenu(type, x, y) {\r\n");
          out.write("\t\t\tif(\"root\" == type){\r\n");
          out.write("\t\t\t\t$(\"#m_addRoot\").show();\r\n");
          out.write("\t\t\t\t$(\"#m_add\").hide();\r\n");
          out.write("\t\t\t\t$(\"#m_edit\").hide();\r\n");
          out.write("\t\t\t\t$(\"#m_del\").hide();\r\n");
          out.write("\t\t\t}else{\r\n");
          out.write("\t\t\t\t$(\"#m_addRoot\").hide();\r\n");
          out.write("\t\t\t\t$(\"#m_add\").show();\r\n");
          out.write("\t\t\t\t$(\"#m_edit\").show();\r\n");
          out.write("\t\t\t\t$(\"#m_del\").show();\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t\trMenu.css({\"top\":y+\"px\", \"left\":x+\"px\", \"visibility\":\"visible\"});\r\n");
          out.write("\t\t\t$(\"body\").bind(\"mousedown\", onBodyMouseDown);\r\n");
          out.write("\t\t}\r\n");
          out.write("\t\tfunction hideRMenu() {\r\n");
          out.write("\t\t\tif (rMenu) rMenu.css({\"visibility\": \"hidden\"});\r\n");
          out.write("\t\t\t$(\"body\").unbind(\"mousedown\", onBodyMouseDown);\r\n");
          out.write("\t\t}\r\n");
          out.write("\t\tfunction onBodyMouseDown(event){\r\n");
          out.write("\t\t\tif (!(event.target.id == \"rMenu\" || $(event.target).parents(\"#rMenu\").length>0)) {\r\n");
          out.write("\t\t\t\trMenu.css({\"visibility\" : \"hidden\"});\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t}\r\n");
          out.write("\t\tvar addCount = 1;\r\n");
          out.write("\t\t\r\n");
          out.write("\t\tvar zTree, rMenu;\r\n");
          out.write("\t\t$(document).ready(function(){\r\n");
          out.write("\t\t\t$.fn.zTree.init($(\"#dicTree\"), setting);\r\n");
          out.write("\t\t\tzTree = $.fn.zTree.getZTreeObj(\"dicTree\");\r\n");
          out.write("\t\t\trMenu = $(\"#rMenu\");\r\n");
          out.write("\t\t});\r\n");
          out.write("</script>\r\n");
          out.write("\t<style type=\"text/css\">\r\n");
          out.write(".name {\r\n");
          out.write("\tfont-weight: bold;\r\n");
          out.write("}\r\n");
          out.write("\r\n");
          out.write(".pro {\r\n");
          out.write("\tcolor: #006;\r\n");
          out.write("}\r\n");
          out.write("\r\n");
          out.write(".permissionGroup {\r\n");
          out.write("\tcolor: #060;\r\n");
          out.write("}\r\n");
          out.write("\r\n");
          out.write(".permission {\r\n");
          out.write("\tcolor: #666;\r\n");
          out.write("}\r\n");
          out.write("</style>\r\n");
          int evalDoAfterBody = _jspx_th_up72_005foverride_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_up72_005foverride_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_up72_005foverride_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.reuse(_jspx_th_up72_005foverride_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.reuse(_jspx_th_up72_005foverride_005f0);
      out.write("\r\n");
      out.write("\r\n");
      //  up72:override
      com.up72.framework.web.tags.OverrideTag _jspx_th_up72_005foverride_005f1 = (com.up72.framework.web.tags.OverrideTag) _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.get(com.up72.framework.web.tags.OverrideTag.class);
      _jspx_th_up72_005foverride_005f1.setPageContext(_jspx_page_context);
      _jspx_th_up72_005foverride_005f1.setParent(null);
      // /pages/admin/auth/organization/indexTree.jsp(143,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_up72_005foverride_005f1.setName("content");
      int _jspx_eval_up72_005foverride_005f1 = _jspx_th_up72_005foverride_005f1.doStartTag();
      if (_jspx_eval_up72_005foverride_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_up72_005foverride_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_up72_005foverride_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_up72_005foverride_005f1.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("\t<!-- 当前位置 -->\r\n");
          out.write("\t<div class=\"head_content\">\r\n");
          out.write("\t\t<div class=\"navBar\" style=\"display: none\">\r\n");
          out.write("\t\t\t»\r\n");
          out.write("\t\t\t<a class=\"\" href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/product\">");
          out.print(Organization.TABLE_ALIAS);
          out.write("设置</a>\r\n");
          out.write("\t\t</div>\r\n");
          out.write("\t</div>\r\n");
          out.write("\t<!-- END  当前位置 -->\r\n");
          out.write("\t<!--end查询-->\r\n");
          out.write("\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
          out.write("\t\tclass=\"up72_treeperm\">\r\n");
          out.write("\t\t<tr>\r\n");
          out.write("\t\t\t<td class=\"up72_filetree\" valign=\"top\" style=\"width: 260px;\">\r\n");
          out.write("\t\t\t\t<div class=\"filetree_scr\">\r\n");
          out.write("\t\t\t\t\t<div class=\"filetree\">\r\n");
          out.write("\t\t\t\t\t\t<form id=\"admin_auth_product_page_form\"\r\n");
          out.write("\t\t\t\t\t\t\tname=\"admin_auth_product_page_form\">\r\n");
          out.write("\t\t\t\t\t\t\t<div>\r\n");
          out.write("\t\t\t\t\t\t\t\t<div id=\"treecontrol\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a title=\"\" href=\"#\">关闭全部</a>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<a title=\"\" href=\"#\">展开全部</a>\r\n");
          out.write("\t\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t\t\t<ul id=\"dicTree\" class=\"ztree\">\r\n");
          out.write("        \t\t\t\t\t\t</ul>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t</form>\r\n");
          out.write("\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t</div>\r\n");
          out.write("\t\t\t</td>\r\n");
          out.write("\t\t\t<td id=\"editDiv\">\r\n");
          out.write("\t\t\t</td>\r\n");
          out.write("\t\t</tr>\r\n");
          out.write("\t</table>\r\n");
          out.write("\t<script type=\"text/javascript\">\r\n");
          out.write("\t\t$.ajax({\r\n");
          out.write("\t\t\turl : \"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/organization/dashboard\",\r\n");
          out.write("\t\t\tsuccess : function(html){\r\n");
          out.write("\t\t\t\t$(\"#editDiv\").html(html);\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t});\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\tfunction deleteOrganization(id){\r\n");
          out.write("\t\t\tconfirm(\"确认删除该机构吗？\", function(){\r\n");
          out.write("\t  \t\t$.ajax({\r\n");
          out.write("\t  \t\t\turl : \"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/organization/\"+id+\"/deleteOrganization\",\r\n");
          out.write("\t  \t\t\tdataType : \"json\",\r\n");
          out.write("\t  \t\t\tsuccess : function(jsonDatas){\r\n");
          out.write("\t  \t\t\t\talert(\"删除成功\");\r\n");
          out.write("\t  \t\t\t\twindow.location.reload();\r\n");
          out.write("\t  \t\t\t}\r\n");
          out.write("\t  \t\t});\r\n");
          out.write("\t  \t\t}\r\n");
          out.write("  \t\t);\r\n");
          out.write("  \t}\r\n");
          out.write("  \tfunction deleteWorkGroup(id){\r\n");
          out.write("\t\t\tconfirm(\"确认删除该部门吗？\", function(){\r\n");
          out.write("\t  \t\t$.ajax({\r\n");
          out.write("\t  \t\t\turl : \"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/workGroup/\"+id+\"/deleteWorkGroup\",\r\n");
          out.write("\t  \t\t\tdataType : \"json\",\r\n");
          out.write("\t  \t\t\tsuccess : function(jsonDatas){\r\n");
          out.write("\t  \t\t\t\talert(\"删除成功\");\r\n");
          out.write("\t  \t\t\t\twindow.location.reload();\r\n");
          out.write("\t  \t\t\t}\r\n");
          out.write("\t  \t\t});\r\n");
          out.write("\t  \t\t}\r\n");
          out.write("  \t\t);\r\n");
          out.write("  \t}\r\n");
          out.write("  \t\r\n");
          out.write("  \t/**\r\n");
          out.write("\t * 删除角色\r\n");
          out.write("\t */\r\n");
          out.write("  \tfunction deleteRole(id,parentType,orgId){\r\n");
          out.write("\t\tconfirm(\"确认删除该角色吗？\", function(){\r\n");
          out.write("\t  \t\t$.ajax({\r\n");
          out.write("\t  \t\t\turl : \"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/role/\"+id+\"/delete\",\r\n");
          out.write("\t  \t\t\ttype : \"post\",\r\n");
          out.write("\t  \t\t\tdataType : \"json\",\r\n");
          out.write("\t  \t\t\tsuccess : function(jsonDatas){\r\n");
          out.write("\t  \t\t\t\talert(\"删除成功\");\r\n");
          out.write("\t  \t\t\t\tif(parentType==\"wg\")\r\n");
          out.write("\t  \t\t\t\t{\r\n");
          out.write("\t  \t\t\t\t\tgetClass(\"work_group_tab3\",\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/workGroup/\"+orgId+\"/roleList\");\r\n");
          out.write("\t  \t\t\t\t}else if (parentType==\"org\")\r\n");
          out.write("\t  \t\t\t\t{\r\n");
          out.write("\t  \t\t\t\t\tgetClass(\"organization_tab3\",\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/organization/\"+orgId+\"/roleList\");\r\n");
          out.write("\t  \t\t\t\t}\r\n");
          out.write("\t  \t\t\t\t\r\n");
          out.write("\t  \t\t\t}\r\n");
          out.write("\t  \t\t});\r\n");
          out.write("  \t\t});\r\n");
          out.write("  \t}\r\n");
          out.write("\t</script>\r\n");
          int evalDoAfterBody = _jspx_th_up72_005foverride_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_up72_005foverride_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_up72_005foverride_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.reuse(_jspx_th_up72_005foverride_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.reuse(_jspx_th_up72_005foverride_005f1);
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/admin/adminBase", out, false);
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /common/taglibs.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /common/taglibs.jsp(10,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }
}
