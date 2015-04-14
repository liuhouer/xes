package org.apache.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.commons.logging.LogFactory;

public final class error_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<title>Error Page</title>\r\n");
      out.write("\t<script language=\"javascript\">\r\n");
      out.write("\t\tfunction showDetail()\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tvar elm = document.getElementById('detail_system_error_msg');\r\n");
      out.write("\t\t\tif(elm.style.display == '') {\r\n");
      out.write("\t\t\t\telm.style.display = 'none';\r\n");
      out.write("\t\t\t}else {\r\n");
      out.write("\t\t\t\telm.style.display = '';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div id=\"content\">\r\n");
      out.write("\t");

		//Exception from JSP didn't log yet ,should log it here.
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		LogFactory.getLog(requestUri).error(exception.getMessage(), exception);
	
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t对不起,发生系统内部错误,不能处理你的请求<br />\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<b>错误信息:</b> ");
      out.print(exception.getMessage());
      out.write("\r\n");
      out.write("\t<br>\r\n");
      out.write("\r\n");
      out.write("\t<button onclick=\"history.back();\">返回</button>\r\n");
      out.write("\t<br>\r\n");
      out.write("\r\n");
      out.write("\t<p><a href=\"#\" onclick=\"showDetail();\">点击这里查看具体错误消息</a>,报告以下错误消息给系统管理员,可以更加快速的解决问题</p>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"detail_system_error_msg\" style=\"display:none\">\r\n");
      out.write("\t\t<pre>");
exception.printStackTrace(new java.io.PrintWriter(out));
      out.write("</pre>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
}
