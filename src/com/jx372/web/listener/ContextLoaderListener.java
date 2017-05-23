package com.jx372.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
    public ContextLoaderListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    	String contextParam = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	System.out.println(contextParam);
    	System.out.println("컨테이너가 시작 하였습니다");
    }
	
}
