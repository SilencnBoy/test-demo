package com.xl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xl.interceptor.MyInterceptor;
/**
 * TODO 重写webmvc配置  spring mvc
 * 
 * @author xl
 */
@Configuration
public class MyWebAppConfig extends WebMvcConfigurerAdapter {

	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");// 重写addInterceptors方法并为拦截器配置拦截规则
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/index");//排除路径
		super.addInterceptors(registry);
	}
}
