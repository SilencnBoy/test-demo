package com.hoyatod.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/app")
public class IndexController {

	@RequestMapping(value = "/index" , method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		 System.out.println("Hello,Apache and Tomcat!!!!!!!!====="+request.getSession().getAttribute("myName"));
		return "index1";
	}
	
	@RequestMapping(value = "/home.html" , method = RequestMethod.GET)
	public String home(){
		
		return "home";
	}
}
