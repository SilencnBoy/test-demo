package com.xl.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getApplicationContext()获取applicationContext对象,applicationContext="+ SpringUtil.applicationContext + "========");
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	} 

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> Object getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	public static <T> Object getBean(Class<T> clazz, String beanName) {

		return applicationContext.getBean(clazz, beanName);
	}
}
