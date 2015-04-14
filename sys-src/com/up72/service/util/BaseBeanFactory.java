package com.up72.service.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class BaseBeanFactory {
	
	private static ApplicationContext ctx = null;
	
	public static void setServletContext(ServletContext servletContext) {
		ctx = (ApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}
	
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ctx = applicationContext;
	}
	
    public static <T> T getBean(Class<T> clz) {
    	if (ctx == null) {
    		throw new RuntimeException("application context wasn't intialized");
    	}
    	     
    	String beanName = clz.getSimpleName();
    	
    	return (T)ctx.getBean(beanName);
    }
    
    public static Object getBean(String beanName) {
    	if (ctx == null) {
    		throw new RuntimeException("application context wasn't intialized");
    	}
    	return ctx.getBean(beanName);
    }
    
//    public static void initOnline(){
//    	System.out.println("信息: Online's Info is deleted success!");
//    	IOnlineService onlineService = (IOnlineService)EEMediaBeanFactory.getService(IOnlineService.class);
//    	onlineService.initOnline();
//    }
}
