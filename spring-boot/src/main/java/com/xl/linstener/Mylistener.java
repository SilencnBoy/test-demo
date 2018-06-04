package com.xl.linstener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//自定义监听器
@WebListener
public class Mylistener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent contextEvent) {

		System.out.println("contextDestroyed");

	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		
		System.out.println("contextInitialized");
	}

}
