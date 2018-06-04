package com.xl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
  * TODO MyInterceptor 自定义拦截器
  * 
  * @author xl
  * @date 2017年3月3日 下午7:30:03
  */
public class MyInterceptor implements HandlerInterceptor {


	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {

		System.out.println("afterCompletion...");
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle...");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		System.out.println("preHandle...");
		return true;
	}

}
