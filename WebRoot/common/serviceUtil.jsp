<%@ page language="java" import="org.springframework.web.context.support.WebApplicationContextUtils,org.springframework.web.context.WebApplicationContext,com.up72.dao.hibernate.CommonDAOHibernate,com.up72.dao.jdbc.CommonDAOJdbc,com.bruce.SeoServiceImpl" pageEncoding="utf-8"%>
<%
ServletContext context = getServletContext();
WebApplicationContext applicationContext  = WebApplicationContextUtils
  .getWebApplicationContext(context);
CommonDAOHibernate $d = (CommonDAOHibernate)applicationContext.getBean("commonDAOHibernate");
CommonDAOJdbc $$d = (CommonDAOJdbc)applicationContext.getBean("commonDAOJdbc");
SeoServiceImpl $seo = (SeoServiceImpl)applicationContext.getBean("seoService");
 %>