package com.hoyatod.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	
	private static Logger log = Logger.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	/**public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String url = request.getRequestURI();
		log.info(url);
		if (url.indexOf("index") >= 0) {
			return true;
		}
		HttpSession session = request.getSession(); // 获取Session
		String username = (String) session.getAttribute("username");
		if (username != null) {
			return true;
		}
		request.getRequestDispatcher("/app/index").forward(request, response);
		return false;
	}  

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		log.info("postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		log.info("afterCompletion");
	}**/

}
