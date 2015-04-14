package org.apache.jsp.pages.admin.sys.sysConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.up72.auth.model.*;
import com.up72.auth.member.model.AuthUser;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/pages/admin/sys/sysConfig/base.jsp");
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
      if (_jspx_meth_up72_005foverride_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_up72_005foverride_005f1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/admin/adminBase", out, false);
      out.write('\r');
      out.write('\n');
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

  private boolean _jspx_meth_up72_005foverride_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  up72:override
    com.up72.framework.web.tags.OverrideTag _jspx_th_up72_005foverride_005f0 = (com.up72.framework.web.tags.OverrideTag) _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.get(com.up72.framework.web.tags.OverrideTag.class);
    _jspx_th_up72_005foverride_005f0.setPageContext(_jspx_page_context);
    _jspx_th_up72_005foverride_005f0.setParent(null);
    // /pages/admin/sys/sysConfig/index.jsp(7,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
        out.write("  <title>系统配置</title>\r\n");
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
      return true;
    }
    _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.reuse(_jspx_th_up72_005foverride_005f0);
    return false;
  }

  private boolean _jspx_meth_up72_005foverride_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  up72:override
    com.up72.framework.web.tags.OverrideTag _jspx_th_up72_005foverride_005f1 = (com.up72.framework.web.tags.OverrideTag) _005fjspx_005ftagPool_005fup72_005foverride_0026_005fname.get(com.up72.framework.web.tags.OverrideTag.class);
    _jspx_th_up72_005foverride_005f1.setPageContext(_jspx_page_context);
    _jspx_th_up72_005foverride_005f1.setParent(null);
    // /pages/admin/sys/sysConfig/index.jsp(10,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
        out.write("<!-- 当前位置 -->\r\n");
        out.write("<div class=\"up72_dashboard\">\r\n");
        out.write("  <div class=\"dashboard_info\">\r\n");
        out.write("    <div class=\"promptsnews_tit\">主题设置</div>\r\n");
        out.write("    <div class=\"promptsnews_con\">\r\n");
        out.write("      ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/admin/sys/sysConfig/changeSkin", out, true);
        out.write("\r\n");
        out.write("    </div>\r\n");
        out.write("    <div class=\"promptsnews_tit\">系统名称设置</div>\r\n");
        out.write("    <div class=\"promptsnews_con\">\r\n");
        out.write("      ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/admin/sys/sysConfig/changeProjectName", out, true);
        out.write("\r\n");
        out.write("    </div>\r\n");
        out.write("  </div>\r\n");
        out.write("</div>\r\n");
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
