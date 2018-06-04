package com.xl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//使用注解定义一个过滤器
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化......");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		System.out.println("执行拦截---doFilter()");
		chain.doFilter(request, response);
		System.out.println("after filter");
	}

	public void destroy() {
		System.out.println("销毁......");
	}

}
