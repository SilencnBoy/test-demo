package com.xl.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
  * TODO 获取系统属性
  * 
  * @author xl
  * @date 2017年3月3日 下午8:39:41
  */
@Configuration
public class MyEnvironment implements EnvironmentAware {

	@Value("${spring.datasource.url}") // 使用el表达式读取spring主配置文件
	private String jdbcUrl;

	@Override
	public void setEnvironment(Environment environment) {
		
//		System.out.println("springel表达式获取的值：" + jdbcUrl);// springEL表达式获取的值
//		System.out.println("JAVA_HOME" + environment.getProperty("JAVA_HOME"));	// 获取系统属性：
//		System.out.println("spring.datasource.url:" + environment.getProperty("spring.datasource.url"));// 获取spring主配置文件中的属性

		// 获取前缀是“spring.datasource”的所有属性值
//		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
//		System.out.println("通过前缀获取的url:" + propertyResolver.getProperty("url"));
//		System.out.println("通过前缀获取的driverClassName:" + propertyResolver.getProperty("driverClassName"));
	}

}
