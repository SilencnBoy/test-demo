package com.xul.Interceptor;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("unused")
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String[] IGNORE_URI = {"/login","/file/*","/3d","love"};  //忽略的URI
//	private static final String LOGIN_URI = "/login";   //登陆URI
	private static final String mappingURL =null;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
		//针对性过滤
    	String url = request.getRequestURL().toString();
		  for (String s : IGNORE_URI) { if (url.contains(s)) {
		  System.out.printf(url + "=>>>>>=>拦截器已自动忽略!" + "\n"); return true; } }
		 
    	//按照时间拦截时间戳
    	/*if(mappingURL==null || url.matches(mappingURL)){    
            Calendar c=Calendar.getInstance();    
            c.setTime(new Date());    
            int now=c.get(Calendar.HOUR_OF_DAY);    
            if(now<9 || now>12){
            	System.out.println("拦截到了");
                request.setAttribute("login", "注册开放时间：9：00-12：00");    
                request.getRequestDispatcher("/login").forward(request, response);    
                return false;    
            }    
            return true;    
        }*/
        
		//设置缓存--此时运用的是session
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("login_status") != null){
            System.out.printf(url + "=>>>>>拦截器=>已登录,欢迎访问!" + "\n");
            return true;
        }else{
        	String contextPath = request.getContextPath();
            System.out.printf(url + "=>>>>>拦截器=>未登录,已拦截!" + "\n");
            response.sendRedirect(contextPath + "/login");
            return false;
        }
    }
    
    //可以适当修改ModeAndview
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        System.out.println("可以适当修改ModeAndview返回接口-->postHandle");
        
    }
    
	//可以进行日志记录
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {    
    	System.out.println("可以进行日志记录-->afterCompletion");
    }
    
}
