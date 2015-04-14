package com.up72.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.up72.service.util.BaseBeanFactory;

public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1805197730109898865L;

	/**
	 * Constructor of the object.
	 */
	public StartupServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
		BaseBeanFactory.setServletContext(this.getServletContext());
	}

}
