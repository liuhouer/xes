package org.apache.jsp.pages.admin.auth.permission;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.up72.auth.model.*;

public final class exportAndImportPerm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/pages/admin/auth/permission/base.jsp");
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
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      //  up72:override
      com.up72.framework.web.tags.OverrideTag _jspx_th_up72_005foverride_005f0 = (com.up72.framework.web.tags.OverrideTag) _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.get(com.up72.framework.web.tags.OverrideTag.class);
      _jspx_th_up72_005foverride_005f0.setPageContext(_jspx_page_context);
      _jspx_th_up72_005foverride_005f0.setParent(null);
      // /pages/admin/auth/permission/exportAndImportPerm.jsp(6,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
          out.write("<title>");
          out.print(Product.TABLE_ALIAS);
          out.write(" 维护</title>\r\n");
          out.write("<script src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/rest.js\" ></script>\t\t\r\n");
          out.write("<link href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/simpletable/simpletable.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
          out.write("<script type=\"text/javascript\" src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/simpletable/simpletable.js\"></script>\r\n");
          out.write("<link href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/grid/css/flexigrid.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
          out.write("<script type=\"text/javascript\" src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/scripts/grid/flexigrid-source.js\"></script>\r\n");
          out.write("<script type=\"text/javascript\">\r\n");
          out.write("\tfunction toExport(){\r\n");
          out.write("\t\twindow.location.href = \"/admin/auth/permission/exportPerm\";\r\n");
          out.write("\t}\r\n");
          out.write("\tfunction importPerm(){\r\n");
          out.write("\t\t\tshowCommonUpload({\r\n");
          out.write("\t\t   \t\twidth:450,\r\n");
          out.write("\t\t   \t\theight:150,\r\n");
          out.write("\t\t   \t\tsizeLimit: 1024*1024*50,\r\n");
          out.write("\t\t   \t\tcallBack:\"window.parent.importPermCall(event, ID, fileObj, response, data)\",\r\n");
          out.write("\t\t   \t\tfileExt:\"*.zip;*.xml\"\r\n");
          out.write("\t\t  \t});\r\n");
          out.write("\t}\r\n");
          out.write("\t\r\n");
          out.write("\tfunction importPermCall(event, ID, fileObj, response, data){\r\n");
          out.write("\t\tvar file = response.savePath;\r\n");
          out.write("\t\twindow.location.href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/auth/permission/importPerm?filePath=\"+file;\r\n");
          out.write("  \t\t/*$.ajax({\r\n");
          out.write("\t\t\turl : \"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/cbox/plan/importPlanXML\",\r\n");
          out.write("\t\t\ttype: \"post\",\r\n");
          out.write("\t\t\tdataType:\"json\",\r\n");
          out.write("\t\t\tdata:\"filePath=\"+file,\r\n");
          out.write("\t\t\tsuccess : function (data){\r\n");
          out.write("\t\t\t\tif(data.status==\"noLogin\"){\r\n");
          out.write("\t\t\t\t\twindow.location.href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("/admin/login\";\r\n");
          out.write("\t\t\t\t}else{\r\n");
          out.write("\t\t\t\t\tif(data.message==\"success\"){\r\n");
          out.write("\t\t\t\t\t\talert(\"导入成功！\", 0, \"window.location.reload();\")\r\n");
          out.write("\t\t\t\t\t}else if(data.message==\"error\"){\r\n");
          out.write("\t\t\t\t\t\talert(\"文件错误，请确认数据正确后重新导入。\",0,\"window.location.reload();\");\r\n");
          out.write("\t\t\t\t\t}else{\r\n");
          out.write("\t\t\t\t\t\tvar msg = decodeURI(data.message);\r\n");
          out.write("\t\t\t\t\t\tvar m = msg.split('Wrap');\r\n");
          out.write("\t\t\t\t   \t \tvar str ='';\r\n");
          out.write("\t\t\t\t   \t \t$(m).each(function(i,e){\r\n");
          out.write("\t\t\t\t   \t \t\tstr +=\te +\"<br>\";\r\n");
          out.write("\t\t\t\t   \t \t});\r\n");
          out.write("\t\t\t\t   \t \talert(str,0,function(){\r\n");
          out.write("\t\t\t   \t \t \t\twindow.location.href=reload();\r\n");
          out.write("\t\t\t   \t \t \t});\r\n");
          out.write("\t\t\t\t\t}\r\n");
          out.write("\t\t\t\t}\r\n");
          out.write("\t\t\t}\r\n");
          out.write("\t\t});\r\n");
          out.write("\t\tcloseBox();*/\r\n");
          out.write("  \t}\r\n");
          out.write("</script>\r\n");
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
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_up72_005foverride_005f1(_jspx_page_context))
        return;
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

  private boolean _jspx_meth_up72_005foverride_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  up72:override
    com.up72.framework.web.tags.OverrideTag _jspx_th_up72_005foverride_005f1 = (com.up72.framework.web.tags.OverrideTag) _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.get(com.up72.framework.web.tags.OverrideTag.class);
    _jspx_th_up72_005foverride_005f1.setPageContext(_jspx_page_context);
    _jspx_th_up72_005foverride_005f1.setParent(null);
    // /pages/admin/auth/permission/exportAndImportPerm.jsp(61,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
        out.write("<div class=\"dashboard_info\">\r\n");
        out.write("\t     <div class=\"promptsnews_tit\">\r\n");
        out.write("\t        \t导入导出\r\n");
        out.write("\t      </div>\r\n");
        out.write("\t        <div class=\"promptsnews_con\">\r\n");
        out.write("\t\t\t\t<div class=\"up72_quickbtns\">\r\n");
        out.write(" \t\t\t\t\t<div class=\"btn btn_sub\" title=\"导出\"><input type=\"button\"  value=\"导出\" onclick=\"toExport();\"/></div>\r\n");
        out.write("\t\t\t\t\t<div class=\"btn btn_sub\" title=\"导入\"><input type=\"button\"  value=\"导入\" onclick=\"importPerm();\" /></div>\r\n");
        out.write("\t\t\t\t</div>\r\n");
        out.write("\t\t\t</div>\r\n");
        out.write("\t\t\t\r\n");
        out.write("\t\t</div>\r\n");
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
      return true;
    }
    _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.reuse(_jspx_th_up72_005foverride_005f1);
    return false;
  }
}
