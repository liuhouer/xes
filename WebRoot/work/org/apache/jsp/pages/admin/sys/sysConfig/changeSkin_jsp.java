package org.apache.jsp.pages.admin.sys.sysConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.up72.auth.model.*;
import com.up72.auth.member.model.AuthUser;

public final class changeSkin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/common/taglibs.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
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
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" >\r\n");
      out.write("\tfunction changeStyle(obj, name, isInit) {\r\n");
      out.write("\t\tif($(\"#\" + obj).attr(\"class\") == \"layout_sel\"){\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#style_layout li\").each(function(i, dom){\r\n");
      out.write("\t\t\tif($.trim($(dom).attr(\"id\")) == $.trim($.trim(name)+\"_style\")){\r\n");
      out.write("\t\t\t\t$(dom).addClass(\"layout_sel\");\r\n");
      out.write("\t\t\t\thideOther(name + \"_skin\");\r\n");
      out.write("\t\t\t\tcheckStyleRadio($(dom).attr(\"id\"));\r\n");
      out.write("\t\t\t\t//初始化即为编辑情况，不清除皮肤的radio checked\r\n");
      out.write("\t\t\t\t//if(isInit != 1){\r\n");
      out.write("\t\t\t\t//\tclearSkinRadio();\r\n");
      out.write("\t\t\t\t//}\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(dom).removeClass(\"layout_sel\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function changeSkin(obj, name){\r\n");
      out.write("    \tif($(\"#\" + obj).attr(\"class\") == \"skin_sel\"){\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#frmtd_skin div ul li\").each(function(i, dom){\r\n");
      out.write("\t\t\tif($.trim($(dom).attr(\"id\")) == $.trim($.trim(name)+\"_skin\")){\r\n");
      out.write("\t\t\t\t$(dom).addClass(\"skin_sel\");\r\n");
      out.write("\t\t\t\tcheckSkinRadio($(dom).attr(\"id\"));\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(dom).removeClass(\"skin_sel\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function hideOther(visible){\r\n");
      out.write("    \t$(\"#\" + visible).show();\r\n");
      out.write("    \t$(\"#frmtd_skin div\").each(function(i, dom){\r\n");
      out.write("    \t\tif($.trim($(dom).attr(\"id\")) != $.trim(visible)){\r\n");
      out.write("    \t\t\t$(dom).hide();\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function checkStyleRadio(styleRadio){\r\n");
      out.write("    \t$(\"input[name='style_radio']\").each(function(i, obj){\r\n");
      out.write("    \t\tif($(obj).attr(\"id\") == $.trim(styleRadio + \"_radio\")){\r\n");
      out.write("    \t\t\t$(obj).attr(\"checked\",true);\r\n");
      out.write("    \t\t}else{\r\n");
      out.write("    \t\t\t$(\"input[name='style_radio']\").each(function(i, dom){\r\n");
      out.write("\t\t    \t\tif($.trim($(dom).attr(\"id\")) != $.trim(styleRadio + \"_radio\")){\r\n");
      out.write("\t\t    \t\t\t$(dom).removeAttr(\"checked\");\r\n");
      out.write("\t\t    \t\t}\r\n");
      out.write("\t\t    \t});\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function checkSkinRadio(skinRadio){\r\n");
      out.write("    \t$(\"input[name='skin_radio']\").each(function(i, obj){\r\n");
      out.write("    \t\tif($(obj).attr(\"id\") == $.trim(skinRadio + \"_radio\")){\r\n");
      out.write("    \t\t\t$(obj).attr(\"checked\",true);\r\n");
      out.write("    \t\t\tsubmitChangeStyle();\r\n");
      out.write("    \t\t}else{\r\n");
      out.write("    \t\t\t$(\"input[name='skin_radio']\").each(function(i, dom){\r\n");
      out.write("\t\t    \t\tif($.trim($(dom).attr(\"id\")) != $.trim(skinRadio + \"_radio\")){\r\n");
      out.write("\t\t    \t\t\t$(dom).removeAttr(\"checked\");\r\n");
      out.write("\t\t    \t\t}\r\n");
      out.write("\t\t    \t});\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function clearSkinRadio(){\r\n");
      out.write("    \t$(\"input[name='skin_radio']\").each(function(i, obj){\r\n");
      out.write("\t\t    $(obj).removeAttr(\"checked\");\r\n");
      out.write("\t\t    //清除skin的选择样式。\r\n");
      out.write("\t\t    $($(obj).parent()).removeClass(\"skin_sel\");\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    $(document).ready(function(){\r\n");
      out.write("    \tvar styleHtml = \"\";\r\n");
      out.write("    \tvar skinHtml = \"<h4>选择主页展示皮肤：</h4>\";\r\n");
      out.write("    \t\r\n");
      out.write("    \tvar styleSkinJson = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${styleJson}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("    \tfor(i=0;i<styleSkinJson.length;i++){\r\n");
      out.write("    \t\tvar styles = styleSkinJson[i];\r\n");
      out.write("    \t\tstyleHtml += \"<li class=\\\"\\\" onclick=\\\"changeStyle('\" + styles.style + \"_style','\" + styles.style + \"', 0)\\\" id=\\\"\" + styles.style + \"_style\\\"><input type=\\\"radio\\\" name=\\\"style_radio\\\" id=\\\"\" + styles.style + \"_style_radio\\\" value=\\\"\" + styles.style + \"\\\" style=\\\"display:none;\\\"><a href=\\\"javascript:;\\\"><span><img src=\\\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" + styles.styleImgPath + \"\\\"></span></a></li>\";\r\n");
      out.write("    \t\tvar skins = styles.skins;\r\n");
      out.write("    \t\tvar checked = \"\";\r\n");
      out.write("    \t\tvar skinClass = \"\";\r\n");
      out.write("    \t\tskinHtml += \"<div class=\\\"style_skin\\\" id=\\\"\" + styles.style + \"_skin\\\" style=\\\"display: none;\\\">\";\r\n");
      out.write("    \t\tskinHtml += \"<ul>\";\r\n");
      out.write("    \t\tfor(j=0;j<skins.length;j++){\r\n");
      out.write("    \t\t\tvar skin = skins[j];\r\n");
      out.write("    \t\t\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginUser.skin}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("' == skin.skinName){\r\n");
      out.write("    \t\t\t\tskinHtml += \"<li class=\\\"skin_sel\\\" onclick=\\\"changeSkin('\" + skin.skinName + \"_skin', '\" + skin.skinName + \"')\\\" id=\\\"\" + skin.skinName + \"_skin\\\"><input type=\\\"radio\\\" name=\\\"skin_radio\\\" id=\\\"\" + skin.skinName + \"_skin_radio\\\" value=\\\"\" + skin.skinName + \"\\\" checked=\\\"checked\\\" style=\\\"display:none;\\\"><a href=\\\"javascript:;\\\"><img src=\\\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" + skin.imgPath + \"\\\"><span>\" + skin.viewName + \"</span></a></li>\";\r\n");
      out.write("    \t\t\t}else{\r\n");
      out.write("    \t\t\t\tskinHtml += \"<li onclick=\\\"changeSkin('\" + skin.skinName + \"_skin', '\" + skin.skinName + \"')\\\" id=\\\"\" + skin.skinName + \"_skin\\\"><input type=\\\"radio\\\" name=\\\"skin_radio\\\" id=\\\"\" + skin.skinName + \"_skin_radio\\\" value=\\\"\" + skin.skinName + \"\\\" style=\\\"display:none;\\\"><a href=\\\"javascript:;\\\"><img src=\\\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" + skin.imgPath + \"\\\"><span>\" + skin.viewName + \"</span></a></li>\";\r\n");
      out.write("    \t\t\t}\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t\tskinHtml += \"</ul>\"\r\n");
      out.write("    \t\tskinHtml += \"</div>\";\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("    \t//风格\r\n");
      out.write("    \t$(\"#style_layout\").html(styleHtml);\r\n");
      out.write("    \t//皮肤\r\n");
      out.write("    \t$(\"#frmtd_skin\").html(skinHtml);\r\n");
      out.write("    \t\r\n");
      out.write("\t\tchangeStyle('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginUser.style}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("_style', '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginUser.style}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("', 1);\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("\tfunction submitChangeStyle(){\r\n");
      out.write("\t\tif(!isSelect(\"style_radio\", \"\")){\r\n");
      out.write("\t\t\talert(\"请选择风格！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}else if(!isSelect(\"skin_radio\", \"\")){\r\n");
      out.write("\t\t\talert(\"请选择皮肤\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\turl: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/admin/sys/sysConfig/saveStyleSkin\",\r\n");
      out.write("\t\t\tdata: $(\"#iscs_change_style_skin_form\").serialize(),\r\n");
      out.write("\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\tcache: false,\r\n");
      out.write("\t\t\tsuccess: function(msg){\r\n");
      out.write("\t\t\t\tvar message = decodeURI(msg.message);\r\n");
      out.write("\t\t\t\tif(msg.status == \"success\"){\r\n");
      out.write("\t\t\t\t\t//alert(message);\r\n");
      out.write("\t\t\t\t\twindow.location.reload();\r\n");
      out.write("\t\t\t\t}else if(msg.status == \"unLogin\"){\r\n");
      out.write("\t\t\t\t\talert(message);\r\n");
      out.write("\t\t\t\t\twindow.location.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/admin/authUser/logout\";\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\talert(message);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<form name=\"iscs_change_style_skin_form\" id=\"iscs_change_style_skin_form\" method=\"post\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/admin/auth/member/sysConfig/saveStyleSkin\">\r\n");
      out.write("<div class=\"up72_show layout_skin\">\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"show_table\">\r\n");
      out.write("    <tbody>\r\n");
      out.write("      <tr class=\"frmtr\">\r\n");
      out.write("        <th class=\"frmth\">\r\n");
      out.write("            <h4>请选择主页布局：</h4>\r\n");
      out.write("            <ul class=\"style_layout\" id=\"style_layout\">\r\n");
      out.write("            </ul>\r\n");
      out.write("        </th>\r\n");
      out.write("        <td class=\"frmtd\" id=\"frmtd_skin\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </tbody>\r\n");
      out.write("  </table>\r\n");
      out.write("</div>\r\n");
      out.write("</form>");
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
